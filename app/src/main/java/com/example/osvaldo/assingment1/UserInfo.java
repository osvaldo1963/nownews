package com.example.osvaldo.assingment1;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

class Userinfo {
    private FirebaseUser user;
    Userinfo() {
        this.user = FirebaseAuth.getInstance().getCurrentUser();
    }
    public String getEmail() {
        String result = "";
        for(UserInfo profile: this.user.getProviderData()) {
            String useremail  = profile.getEmail();
            result = useremail;
            break;
        }
        return result;
    }
}
