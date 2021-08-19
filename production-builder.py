#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from sys import argv;
import os;
import tarfile;
import glob;

APPLICATION_CONFIGURATION_FILE = "pom.xml"

class Version:
    def __init__(self, version: int = 0, feature: int = 0, bug: int = 0, tag: str = "") -> None:
        self.version: int = version;
        self.feature: int = feature;
        self.bug: int = bug;
        self.tag: str = tag;
    
    def show(self) -> str:
        return "{}.{}.{}{}".format(self.version, self.feature, self.bug, self.tag);

    @staticmethod
    def build(value: str):
        version: Version = Version();
        raw_version = value;
        if ('-' in value):
            version.tag = value.split('-')[1];
            raw_version = value.replace("-{}".format(version.tag), "");
        if ('.' in raw_version):
            parts: list = raw_version.split('.');
            if (len(parts) == 3):
                version.version = int(parts[0]);
                version.feature = int(parts[1]);
                version.bug = int(parts[2]);
                return version;
            return None;
        else:
            return None;

class Application:
    def __init__(self, name: str = None, version: Version = Version()) -> None:
        self.name: str = name;
        self.version: Version = version;

def extract_application_informations() -> Application:
    global APPLICATION_CONFIGURATION_FILE;
    name: str = None;
    raw_version: str = None;

    with open(APPLICATION_CONFIGURATION_FILE, 'r') as package:
        lines: list = package.readlines();
        for line in lines:
            if ('<version>' in line) and ('</version>' in line):
                raw_version = line.split('>')[1].split("<")[0];
                break;
            elif ('<artifactId>' in line) and ('</artifactId>' in line):
                name = line.split('>')[1].split("<")[0];
    if (name != None and raw_version != None):
        version: Version = Version.build(value=raw_version);
        if (version != None):
            return Application(name=name, version=version);
        else:
            return None;
    return None;

def show_help():
    print("Usage: python production-builder.py [+v|+f|+b]")

def get_arguments():
    if (len(argv) == 2):
        argument = argv[1];
        if (argument in ["+v", "+f", "+b"]):
            return argument;
    return None;

def apply_strategy(application: Application, strategy: str) -> Application:
    if (strategy == '+v'):
        application.version.version += 1;
        application.version.feature = 0;
        application.version.bug = 0;
    if (strategy == '+f'):
        application.version.feature += 1;
        application.version.bug = 0;
    if (strategy == '+b'):
        application.version.bug += 1;
    return application;

def update_application_configuration(application: Application):
    global APPLICATION_CONFIGURATION_FILE;
    old_lines: list = None;
    new_lines: list = [];
    with open(APPLICATION_CONFIGURATION_FILE, 'r') as package:
        old_lines: list = package.readlines();
        for line in old_lines:
            if ('<version>' in line) and ('</version>' in line):
                left_part = line.split(">")[0]+">"
                right_part = line.split(">")[1].split("<")[1]+"<"
                updated_line = '{}{}{}'.format(left_part, application.version.show(), right_part);
                new_lines.append(updated_line);
            else:
                new_lines.append(line);
    content: str = "";
    for line in new_lines:
        content += line
    with open(APPLICATION_CONFIGURATION_FILE, 'w') as package:
        package.write(content);
        package.flush();

def compile_project(application: Application) -> bool:
    result = os.system("mvn clean install");
    target = "target";
    releases = "releases";
    files: list = [jar for jar in os.listdir(target) if (str(jar).endswith("jar") and ( "with-dependencies" in str(jar)))];
    if (len(files) > 0):
        jar_for_release = files[0];
        release_name = "{}-LATEST.jar".format(application.name)
        with open("{}/{}".format(target, jar_for_release, 'r')) as source:
            with open("{}/{}".format(releases, release_name, 'r')) as destination:
                destination.write(source.read());
    return result == 0;

def get_branch():
    branch_file: str = "git-branch.txt";
    result = os.system("git branch > {}".format(branch_file));
    branch: str = None;
    if (result == 0):
        lines: list = None;
        with open(branch_file, 'r') as file:
            lines = file.readlines();
        os.remove(branch_file);
        for line in lines:
            if '*' in line:
                branch = line.replace('*', '').replace('\r', '').replace('\n', '').strip();
                break;
    return branch;

def merge_branch(source, destination):
    os.system("git checkout {}".format(destination));
    os.system("git pull")
    os.system("git merge {}".format(source));

def is_branch_clean():
    is_clean = False;
    git_status_file = "git-status.txt";
    os.system("git status > {}".format(git_status_file));
    lines: list = None
    with open(git_status_file, 'r') as file:
        lines = file.readlines();
    os.remove(git_status_file);
    for line in lines:
        if "nothing to commit, working tree clean" in line:
            is_clean = True;
    return is_clean;

def publish(application: Application):
    os.system("git add .");
    os.system('git commit -m "[CONTINIOUS-INTEGRATION] : Build a new release version: {}"'.format(application.version.show()));
    os.system("git push");
    os.system("git tag v{}".format(application.version.show()))
    os.system("git push --tags")

def main():
    global APPLICATION_CONFIGURATION_FILE;
    strategy: str = get_arguments();
    application: Application = extract_application_informations();
    if (strategy != None):
        if (application != None):
            default_branch = get_branch();
            if (default_branch != None):
                if (is_branch_clean()):
                    merge_branch(source=default_branch, destination="master");
                    application = apply_strategy(application=application, strategy=strategy);
                    update_application_configuration(application=application);
                    is_compiled: bool = compile_project(application=application);
                    if (is_compiled):
                        publish(application=application);
                    else:
                        print("Not compiled");
                else:
                    print("Branch not clean")
            else:
                print("No branch found")
        else:
            print("Error with {}.".format(APPLICATION_CONFIGURATION_FILE));
    else:
        show_help();

if __name__ == "__main__":
    main();