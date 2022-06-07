package com.company;

import com.company.google.GoogleDriveServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class UzTreylerApplicationTests {

    @Autowired
    private GoogleDriveServiceImp googleDriveServiceImp;
    @Test
    void contextLoads() {
        File file = new File("C:\\Users\\Admin\\Desktop\\1043597560_284892911_131189616201796_6785058905510751377_n.mp4");

        com.google.api.services.drive.model.File upload = googleDriveServiceImp.upload(file.getName(), file.getPath(), "image/png");

        try {
            System.err.println(upload.toPrettyString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
