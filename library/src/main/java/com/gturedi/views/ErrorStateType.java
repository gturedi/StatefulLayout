package com.gturedi.views;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by gturedi on 18.02.2017.
 */
public enum ErrorStateType {

    EMPTY(R.drawable.stf_ic_empty, R.string.stfEmptyMessage),
    ERROR(R.drawable.stf_ic_error, R.string.srfErrorMessage),
    OFFLINE(R.drawable.stf_ic_offline, R.string.stfOfflineMessage),
    LOCATION_OFF(R.drawable.stf_ic_location_off, R.string.stfLocationOffMessage);

    @DrawableRes
    public final int imageRes;
    @StringRes
    public final int messageRes;

    ErrorStateType(int imageRes, int messageRes) {
        this.imageRes = imageRes;
        this.messageRes = messageRes;
    }

}
