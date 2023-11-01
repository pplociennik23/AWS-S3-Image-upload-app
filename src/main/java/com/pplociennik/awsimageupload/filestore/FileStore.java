package com.pplociennik.awsimageupload.filestore;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(PutObjectRequest putObjectRequest, Optional<Map<String,String>> optionalMetaData){

        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()){
                map.forEach(metadata::addUserMetadata);
            }
        });
        putObjectRequest.setMetadata(metadata);
        s3.putObject(putObjectRequest);

    }
}
