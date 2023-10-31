package com.pplociennik.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path,
                     String fileName,
                     Optional<Map<String,String>> optionalMetaData,
                     MultipartFile multipartFile) throws IOException {

        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()){
                map.forEach(metadata::addUserMetadata);
            }
        });

        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(multipartFile.getBytes());
        }
        PutObjectRequest putObjectRequest = new PutObjectRequest(path,fileName,file);
        putObjectRequest.setMetadata(metadata);

        try{
            s3.putObject(putObjectRequest);
        }catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store file to S3", e);
        }
        file.delete();
    }
}
