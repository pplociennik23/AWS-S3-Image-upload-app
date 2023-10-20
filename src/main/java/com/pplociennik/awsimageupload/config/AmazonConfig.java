package com.pplociennik.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 S3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAZ6FLJXTBBZ5ZWVNS","q6WtbzfQ5VfBaKOmldpRZlga2USFcayLJyFBS1Y0");

        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.EU_CENTRAL_1)
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();
    }
}
