package com.company.service;

import com.company.dto.AttachDTO;
import com.company.entity.AttachEntity;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.google.GoogleDriveServiceImp;
import com.company.google.MimTypes;
import com.company.repository.AttachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachService {

    private final GoogleDriveServiceImp googleDriveServiceImp;

    private final AttachRepository attachRepository;

    @Value("${attach.upload.folder}")
    private String attachFolder;


    public AttachDTO upload(MultipartFile multipartFile, String folder) {

        File file = getFile(multipartFile);

        var uploads = googleDriveServiceImp.upload(file.getName(), file.getPath(), folder);

        AttachEntity attachEntity = new AttachEntity();
        attachEntity.setId(uploads.getId());
        attachEntity.setWebContentLink(uploads.getWebContentLink());
        attachEntity.setWebViewLink(uploads.getWebViewLink());
        attachEntity.setCreatedDate(LocalDateTime.now());

        remove(file.getPath());

        attachRepository.save(attachEntity);

        return toDTO(attachEntity);
    }

    protected AttachDTO toDTO(AttachEntity attachEntity) {
        return new AttachDTO(attachEntity.getId(), attachEntity.getWebContentLink());
    }


    public File getFile(MultipartFile file) {
        File folder = new File(attachFolder);

        if (!folder.exists()) {
            var mkdirs = folder.mkdirs();
            log.info("File created! {}", mkdirs);
        }

        try {
            byte[] bytes = file.getBytes();
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));

            Path url = Paths.get(folder.getAbsolutePath() + "/" + UUID.randomUUID() + "." + extension);

            Files.write(url, bytes);
            return url.toFile();
        } catch (IOException | RuntimeException e) {
            log.warn("Cannot Upload File {}", file.getOriginalFilename());
            throw new AppBadRequestException(e.getMessage());
        }

    }

    public void remove(String local) {

        File file = new File(local);

        if (file.delete()) {
            log.info("file deleted from local");
        } else {
            log.warn("Cannot Read");
            throw new AppBadRequestException("Cannot read the file!");
        }

    }

    public Boolean delete(String id) {
        AttachEntity entity = attachRepository.findById(id)
                .orElseThrow(() -> {
            log.warn("File not found {}", id);
            throw new AppBadRequestException("File Not Found!");
        });
        attachRepository.delete(entity);
        return googleDriveServiceImp.delete(id);
    }

    public String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public AttachEntity getByLink(String link) {
        return attachRepository.findByWebContentLink(link)
                .orElseThrow(() -> {
                    log.warn("Photo Not Found {}", link);
                    return new ItemNotFoundException("Photo Not Found!");
                });
    }

}
