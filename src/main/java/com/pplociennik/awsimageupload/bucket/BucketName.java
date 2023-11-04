package com.pplociennik.awsimageupload.bucket;

import org.springframework.beans.factory.annotation.Value;

public enum BucketName {

    PROFILE_IMAGE("aws-image-upload-app-public-bucket");  //replace default bucket name with your own bucket name that will store the data

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
