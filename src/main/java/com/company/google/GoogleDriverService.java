package com.company.google;

import com.google.api.services.drive.model.File;

public interface GoogleDriverService {

    File upload(String fileName,String filePath,String fileType);

    File upload(String fileName, String filePath, String fileType, String folder);

    Boolean delete(String fileID);

}
