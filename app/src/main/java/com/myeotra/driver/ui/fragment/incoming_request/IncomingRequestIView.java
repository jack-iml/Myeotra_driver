package com.myeotra.driver.ui.fragment.incoming_request;

import com.myeotra.driver.base.MvpView;

public interface IncomingRequestIView extends MvpView {

    void onSuccessAccept(Object responseBody);
    void onSuccessCancel(Object object);
    void onError(Throwable e);
}
