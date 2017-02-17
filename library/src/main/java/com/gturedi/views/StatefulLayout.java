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
        if (isInEditMode()) return;
        //setGravity(Gravity.CENTER);
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
        showLoading(getContext().getString(resId));
    }

    public void showLoading(String message) {
        hide();
        stProgress.setVisibility(VISIBLE);
        if (!TextUtils.isEmpty(message)) {
            stMessage.setVisibility(VISIBLE);
            stMessage.setText(message);
        }
    }

    public void showEmpty() {
        showEmpty("");
    }

    public void showEmpty(@StringRes int resId) {
        showLoading(getContext().getString(resId));
    }

    public void showEmpty(String message) {
        hide();
        stImage.setVisibility(VISIBLE);
        stImage.setImageResource(R.drawable.st_ic_empty);
        stMessage.setVisibility(VISIBLE);
        if (TextUtils.isEmpty(message)) {
            stMessage.setText(R.string.slEmptyMessage);
        } else {
            stMessage.setText(message);
        }
    }

    public void hide() {
        content.setVisibility(GONE);
        stContainer.setVisibility(VISIBLE);
        stProgress.setVisibility(GONE);
        stImage.setVisibility(GONE);
        stMessage.setVisibility(GONE);
        stButton.setVisibility(GONE);
    }

}
