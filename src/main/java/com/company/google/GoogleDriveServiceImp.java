package com.company.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;

@Service
@Slf4j
public class GoogleDriveServiceImp implements GoogleDriverService {


    @Value("${service_account_email}")
    private String serviceAccountEmail;

    @Value("${application_name}")
    private String applicationName;

    @Value("${service_account_key}")
    private String serviceAccountKey;

    @Value("${folder_id}")
    private String folderID;

    public Drive getDriveService() {
        Drive service = null;
        try {

            URL resource = GoogleDriveServiceImp.class.getResource("/" + this.serviceAccountKey);
            assert resource != null;
            java.io.File key = Paths.get(resource.toURI()).toFile();
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
                    .setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
                    .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                    .setServiceAccountPrivateKeyFromP12File(key).build();
            service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(applicationName)
                    .setHttpRequestInitializer(credential).build();
            return service;
        } catch (Exception e) {
            log.error(e.getMessage());

        }

        return service;

    }



    @Override
    public File upload(String fileName, String filePath, String fileType) {
        File file = new File();
        try {
            java.io.File fileUpload = new java.io.File(filePath);
            File fileMetadata = new File();
            fileMetadata.setMimeType(fileType);
            fileMetadata.setName(fileName);
            fileMetadata.setParents(Collections.singletonList(folderID));
            FileContent fileContent = new FileContent(fileType, fileUpload);
            file = getDriveService().files().create(fileMetadata, fileContent)
                    .setFields("id,webContentLink,webViewLink").execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return file;
    }


    public Boolean delete(String fileId) {
        try {
            getDriveService().files().delete(fileId).execute();
            return true;
        } catch (IOException e) {
            log.error("An error occurred: " + e);
        }
        return false;
    }



}
