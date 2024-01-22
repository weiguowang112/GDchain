package com.example.rtdataassetcoord.service;

import com.example.rtdataassetcoord.common.BusException;
import com.example.rtdataassetcoord.config.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageService {
    private final Path rootLocation;

    @Autowired
    public StorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new BusException("Could not initialize storage");
        }
    }

    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new BusException("Failed to store empty file.");
            }
            //子文件夹用uuid命名
            String uuidName = UUID.randomUUID().toString();
            File dir = new File(rootLocation.toString()+"/"+uuidName);
            if(!dir.getParentFile().exists()){
                dir.getParentFile().mkdirs();
            }
            dir.mkdir();
            Path destinationFile = this.rootLocation.resolve(dir.getAbsolutePath()+"/"+file.getOriginalFilename())
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                return uuidName+"/"+file.getOriginalFilename();
            }
        }
        catch (IOException e) {
            throw new BusException("Failed to store file.");
        }
    }

}
