package io.nirahtech.ride4ever.microservices.location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;

import org.springframework.stereotype.Component;

@Component("locationService")
public final class LocationService implements LocationApi {

    private final String DATABASE_FOLDER = "ip2location";
    private final String DATABASE_CODE = "DB9LITEBIN";
    private final String DATABASAE_FILE_NAME = "IP2LOCATION-LITE-DB9.BIN";
    private final String URL = "https://www.ip2location.com/download/?token=g01TDjkhNY7TJbZVmjqU3YjmxvjQmzaigXBA1N4sOwlBqKAeBnxp9rMTHBUpyNW9&file="+DATABASE_CODE;
    private final String ZIP_FILE = "IP2LOCATION-LITE-DB9.BIN.ZIP";

    private File database = null; 

    private final File download(String code, String destination) {
        try {
            InputStream in = new URL(URL).openStream();
            Files.copy(in, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            return new File(destination);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists())
            dir.mkdirs();
        FileInputStream fis;
        File binfile = null;
        // buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                if (fileName.endsWith("BIN")) {
                    File newFile = new File(destDir + File.separator + fileName);
                    binfile = newFile;
                    System.out.println("Unzipping to " + newFile.getAbsolutePath());
                    // create directories for sub directories in zip
                    new File(newFile.getParent()).mkdirs();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                // close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            // close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return binfile;
    }

    private final void init() {
        File potentialZipFile = new File(DATABASE_FOLDER, ZIP_FILE);
        boolean mustBeDownloaded = false;
        File zipFile = null;
        if (potentialZipFile.exists()) {
            Instant lastModified = Instant.ofEpochSecond(potentialZipFile.lastModified()/1000);
            long hours = ChronoUnit.HOURS.between(lastModified, Instant.now());
            if (hours >= 2) {
                mustBeDownloaded = true;
            } else {
                zipFile = potentialZipFile;
            }
        } else {
            mustBeDownloaded = true;
        }
        if (mustBeDownloaded) {
            File folder = new File(DATABASE_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            zipFile = download(DATABASE_CODE, new File(DATABASE_FOLDER, ZIP_FILE).getAbsolutePath());
        }
        File binFile = new File(DATABASE_FOLDER, DATABASAE_FILE_NAME);
        if (!binFile.exists() || mustBeDownloaded) {
            if (zipFile != null) {
                this.database = unzip(zipFile.getAbsolutePath(), DATABASE_FOLDER);
            }
        }
    }

    public LocationService() {
        this.init();
    }

    @Override
    public IPResult resolve(String ipAddress) {
        this.init();
        IP2Location ip2Location = new IP2Location();
        IPResult result = null;
        try {
            ip2Location.Open(this.database.getAbsolutePath());
            result = ip2Location.IPQuery(ipAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
