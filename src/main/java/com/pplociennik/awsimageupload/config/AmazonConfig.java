package com.pplociennik.awsimageupload.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Bean
    public AmazonS3 S3(){

        AWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey,this.secretKey);

        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.EU_CENTRAL_1)
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .withClientConfiguration(new ClientConfiguration().withSignerOverride("S3SignerType"))
            .build();
    }
}
