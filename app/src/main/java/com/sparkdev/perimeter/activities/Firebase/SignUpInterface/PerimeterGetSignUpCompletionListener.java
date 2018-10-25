package com.sparkdev.perimeter.activities.Firebase.SignUpInterface;

import com.sparkdev.perimeter.models.UserProfile;

public interface PerimeterGetSignUpCompletionListener {

    public void onSuccess(UserProfile profile);

    public void onFailure();
}
