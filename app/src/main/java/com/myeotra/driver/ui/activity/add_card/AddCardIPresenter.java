package com.myeotra.driver.ui.activity.add_card;

import com.myeotra.driver.base.MvpPresenter;

public interface AddCardIPresenter<V extends AddCardIView> extends MvpPresenter<V> {

    void addCard(String stripeToken);
}
