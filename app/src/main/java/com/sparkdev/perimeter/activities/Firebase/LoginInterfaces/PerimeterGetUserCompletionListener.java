package com.sparkdev.perimeter.activities.Firebase.LoginInterfaces;

import com.sparkdev.perimeter.models.UserProfile;

public interface PerimeterGetUserCompletionListener {

    public void onSuccess(UserProfile profile);

    public void onFailure();

}
