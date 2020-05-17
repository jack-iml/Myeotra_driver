package com.myeotra.driver.ui.activity.profile;

import com.myeotra.driver.base.MvpView;
import com.myeotra.driver.data.network.model.UserResponse;

public interface ProfileIView extends MvpView {

    void onSuccess(UserResponse user);

    void onSuccessUpdate(UserResponse object);

    void onError(Throwable e);

    void onSuccessPhoneNumber(Object object);

    void onVerifyPhoneNumberError(Throwable e);

}
