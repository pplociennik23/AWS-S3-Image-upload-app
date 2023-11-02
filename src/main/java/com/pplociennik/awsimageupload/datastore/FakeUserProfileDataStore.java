package com.pplociennik.awsimageupload.datastore;

import com.pplociennik.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("2dcaa2a8-2137-4c77-bbb7-0343655abee2"),"janek",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("f367c062-6451-4dff-a5b7-e301a00f8c39"),"grigori",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("b5c9f558-6b58-42d4-aec8-7fc93d38e1e7"),"gustaw",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }

}
