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

In order to use application, user has to create AWS account, create S3 bucket instance
and set up three environment variables / write static variable values. 

* Bucket name (inside bucket/BucketName)
* Aws access key (inside config/AmazonConfig)
* Aws secret key (inside config/AmazonConfig)

Then, run server application (run Main.java class inside src/main/java/com/pplociennik/awsimageupload)

After server runs properly, run frontend app in src/main/frontend folder using command npm start (before first attempt, run npm install for libraries install)