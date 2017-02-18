package com.gturedi.views;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * Created by gturedi on 18.02.2017.
 */
public class CustomStateOptions implements Serializable {

    @DrawableRes private int imageRes;
    private String message;
    private String buttonText;
    private Runnable buttonAction;

    public CustomStateOptions image(@DrawableRes int val) {
        imageRes = val;
        return this;
    }

    public CustomStateOptions message(String val) {
        message = val;
        return this;
    }

    public CustomStateOptions buttonText(String val) {
        buttonText = val;
        return this;
    }

    public CustomStateOptions buttonAction(Runnable val) {
        buttonAction = val;
        return this;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getMessage() {
        return message;
    }

    public String getButtonText() {
        return buttonText;
    }

    public Runnable getButtonAction() {
        return buttonAction;
    }

}
