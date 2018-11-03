package com.sparkdev.perimeter.models.Firebase.SignUpInterface;

import com.sparkdev.perimeter.models.UserProfile;

public interface PerimeterGetSignUpCompletionListener {

    public void onSuccess(UserProfile profile);

    public void onFailure();
}
