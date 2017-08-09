package com.oktrueque.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.oktrueque.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path avatarsLocation = Paths.get("src/main/resources/static/common/img/avatars");

    public String store(MultipartFile file, User user){
        String[] contentType = file.getContentType().split("/");
        String userExtension = user.getUsername() + '.' + contentType[contentType.length-1];
        try {
            Files.deleteIfExists(avatarsLocation.resolve(userExtension));
            Files.copy(file.getInputStream(), this.avatarsLocation.resolve(userExtension));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
        String[] filesRoute = avatarsLocation.toString().split("static/");
        //Devuelvo la segunda parte common/img/avatars
        return filesRoute[1] + '/' + userExtension;
    }

    public Resource loadFile(String filename) {
        try {
            Path file = avatarsLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(avatarsLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(avatarsLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
