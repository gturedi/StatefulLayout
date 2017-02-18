package com.gturedi.views;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by gturedi on 18.02.2017.
 */
public enum ErrorStateType {

    EMPTY(R.drawable.st_ic_empty, R.string.slEmptyMessage),
    ERROR(R.drawable.sl_ic_error, R.string.slErrorMessage),
    OFFLINE(R.drawable.sl_ic_offline, R.string.slOfflineMessage),
    LOCATION_OFF(R.drawable.ic_location_off, R.string.slLocationOffMessage);

    @DrawableRes
    public final int imageRes;
    @StringRes
    public final int messageRes;

    ErrorStateType(int imageRes, int messageRes) {
        this.imageRes = imageRes;
        this.messageRes = messageRes;
    }

}
