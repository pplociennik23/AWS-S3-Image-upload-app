package com.pplociennik.awsimageupload.profile;

import com.pplociennik.awsimageupload.bucket.BucketName;
import com.pplociennik.awsimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    public List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {

        //check if file is not empty
        isFileEmpty(file);

        //check if file has allowed format
        isFileAnImage(file);

        //check if given user exists
        UserProfile user = getUserWithGivenId(userProfileId);

        //create metadata
        Map<String, String> metadata = extractMetadata(file);

        //Prepare path and filename
        //String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String path = String.format("%s", BucketName.PROFILE_IMAGE.getBucketName());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        //save file to S3 and update userProfileImageLink
        try {
            fileStore.save(path, fileName, Optional.of(metadata),file);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private static Map<String, String> extractMetadata(MultipartFile file) {
        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserWithGivenId(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    private static void isFileAnImage(MultipartFile file) {
        if(!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("Given file must be an image! " + file.getContentType() + " is not accepted!");
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() +" ]");
        }
    }
}
