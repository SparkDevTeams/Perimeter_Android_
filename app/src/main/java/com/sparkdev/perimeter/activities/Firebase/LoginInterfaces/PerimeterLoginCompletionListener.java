package com.sparkdev.perimeter.activities.Firebase.LoginInterfaces;

import com.sparkdev.perimeter.models.UserProfile;

public interface PerimeterLoginCompletionListener {


    //method call when login is successful
    public void onSuccess(UserProfile profile);

    //method call when login is failure
    public void onFailure();

}
