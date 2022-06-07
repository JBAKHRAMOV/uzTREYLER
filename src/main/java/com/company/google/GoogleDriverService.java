package com.company.google;

import com.google.api.services.drive.model.File;

public interface GoogleDriverService {

    File upload(String fileName,String filePath,String fileType);


    Boolean delete(String fileID);

}
