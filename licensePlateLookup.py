import requests;
from sys import argv;

def request(license_plate):
    response = requests.get("https://www.oscaro.com/xhr/dionysos-search/fr/fr?plate={}".format(license_plate));
    response = requests.get("https://www.oscaro.com/#");
    response = requests.get("https://www.oscaro.com/xhr/init-client");
    print(response);

def lookup(license_plate):
    request(license_plate);
    print("LP: {}".format(license_plate));

def main(arguments):
    if ((arguments != None) and (len(arguments) == 2)):
        LICENSE_PLATE = arguments[1];
        VEHICULE = lookup(LICENSE_PLATE);
        print(VEHICULE);

if (__name__ == "__main__"):
    main(argv);