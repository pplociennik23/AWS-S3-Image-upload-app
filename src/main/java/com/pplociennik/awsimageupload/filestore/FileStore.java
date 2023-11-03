package com.pplociennik.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(PutObjectRequest putObjectRequest){
        s3.putObject(putObjectRequest);
    }

    public byte[] download(String path, String key) {
        try {
            S3Object s3Object = s3.getObject(path,key);
            return IOUtils.toByteArray(s3Object.getObjectContent());

        }catch (AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to download file from S3", e);
        }
    }
}
