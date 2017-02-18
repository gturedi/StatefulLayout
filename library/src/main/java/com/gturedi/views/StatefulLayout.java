package com.gturedi.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by gturedi on 17.02.2017.
 */
public class StatefulLayout
        extends LinearLayout {

    private LinearLayout stContainer;
    private ProgressBar stProgress;
    private ImageView stImage;
    private View content;
    private TextView stMessage;
    private Button stButton;

    public StatefulLayout(Context context) {
        super(context);
        init();
    }

    public StatefulLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatefulLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) throw new IllegalStateException("StatefulLayout must have one child!");
        setOrientation(VERTICAL);
        if (isInEditMode()) return; // to initSate state views in designer
        content = getChildAt(0);
        LayoutInflater.from(getContext()).inflate(R.layout.stateful_layout, this, true);
        stContainer = (LinearLayout) findViewById(R.id.stContainer);
        stProgress = (ProgressBar) findViewById(R.id.stProgress);
        stImage = (ImageView) findViewById(R.id.stImage);
        stMessage = (TextView) findViewById(R.id.stMessage);
        stButton = (Button) findViewById(R.id.stButton);
    }

    public void showContent() {
        content.setVisibility(VISIBLE);
        stContainer.setVisibility(GONE);
    }

    public void showLoading() {
        showLoading("");
    }

    public void showLoading(@StringRes int resId) {
        showLoading(str(resId));
    }

    public void showLoading(String message) {
        initSate();
        stProgress.setVisibility(VISIBLE);
        if (!TextUtils.isEmpty(message)) {
            stMessage.setVisibility(VISIBLE);
            stMessage.setText(message);
        }
    }

    public void showEmpty() {
        showStateByType(ErrorStateType.EMPTY, "", null);
    }

    public void showEmpty(@StringRes int resId) {
        showStateByType(ErrorStateType.EMPTY, "", null);
    }

    public void showError(Runnable clickAction) {
        showStateByType(ErrorStateType.ERROR, "", clickAction);
    }

    public void showError(@StringRes int resId, Runnable clickAction) {
        showStateByType(ErrorStateType.ERROR, str(resId), clickAction);
    }

    public void showOffline(Runnable clickAction) {
        showStateByType(ErrorStateType.OFFLINE, "", clickAction);
    }

    public void showOffline(@StringRes int resId, Runnable clickAction) {
        showStateByType(ErrorStateType.OFFLINE, str(resId), clickAction);
    }

    public void showLocationOff(Runnable clickAction) {
        showStateByType(ErrorStateType.LOCATION_OFF, "", clickAction);
    }

    public void showLocationOff(@StringRes int resId, Runnable clickAction) {
        showStateByType(ErrorStateType.LOCATION_OFF, str(resId), clickAction);
    }

    private void showStateByType(ErrorStateType type, String message, final Runnable clickAction) {
        initSate();
        stImage.setVisibility(VISIBLE);
        stImage.setImageResource(type.imageRes);

        stMessage.setVisibility(VISIBLE);
        if (TextUtils.isEmpty(message)) {
            stMessage.setText(type.messageRes);
        } else {
            stMessage.setText(message);
        }

        if (clickAction == null) {
            stButton.setVisibility(GONE);
        } else {
            stButton.setVisibility(VISIBLE);
            stButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickAction.run();
                }
            });
        }
    }

    private void initSate() {
        content.setVisibility(GONE);
        stContainer.setVisibility(VISIBLE);
        stProgress.setVisibility(GONE);
        stImage.setVisibility(GONE);
        stMessage.setVisibility(GONE);
        stButton.setVisibility(GONE);
    }

    private String str(@StringRes int resId) {
        return getContext().getString(resId);
    }

}
