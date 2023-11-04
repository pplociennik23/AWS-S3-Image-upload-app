# AWS-image-upload
#### Simple image storage application with a React user interface, Spring Boot backend and AWS S3 file storage.

### Table of Contents
* [General info](#General-info)
* [Technologies](#Technologies)
* [Setup](#Setup)

### General Info

AWS-image-storage is a simple application, created with the S3 image storage purpose.
Current version of the application contains basic user interface with the ability to upload userProfile image. 
Image will then be stored inside the AWS S3 bucket. Future versions of the app will enable userProfile creation and update.

### Technologies

Project was created with following technologies:

* Java JDK 17
* Spring Boot 3
* AWS java sdk 12.xxx
* React + Axios
* Maven

### Setup

In order to use application, user has to create AWS account and personal AWS secret, which will link app with private cloud storage.
When secret will be created, user has to collect unique aws access key and secret key.
These values need to be assigned to the following environmental variables:

* AWS_IMAGE_UPLOAD_APP_ACCESS_KEY_ID
* AWS_IMAGE_UPLOAD_APP_SECRET_KEY

The other option is to statically assign private access key and secret key values to the
aws.accessKeyId and aws.secretKey variables in application.properties file.

Finally, user has to create its own S3 bucket instance on AWS website. Then, the bucket name has to be
assigned to PROFILE_IMAGE("default-S3-bucket-name") inside ../bucket/BucketName.java class
(User has to replace default bucket name with its own bucket name that will store the data)

Then, run server application (run Main.java class inside src/main/java/...)

After server runs properly, run frontend app in src/main/frontend folder using command npm start (before first attempt, run npm install for libraries install)