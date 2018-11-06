package com.sparkdev.perimeter.models.Firebase.LoginInterfaces;

import com.sparkdev.perimeter.models.UserProfile;

public interface PerimeterLoginCompletionListener {


    //method call when login is successful
    public void onSuccess();

    //method call when login is failure
    public void onFailure();

}
