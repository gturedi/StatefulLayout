package com.gturedi.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Android layout to show most common state templates like loading, empty, error etc. To do that all you need to is
 * wrap the target area(view) with StatefulLayout. For more information about usage look
 * <a href="https://github.com/gturedi/StatefulLayout#usage">here</a>
 */
public class StatefulLayout
        extends LinearLayout {

    private static final String MSG_ONE_CHILD = "StatefulLayout must have one child!";
    private static final boolean DEFAULT_ANIM_ENABLED = true;
    private static final int DEFAULT_IN_ANIM = android.R.anim.fade_in;
    private static final int DEFAULT_OUT_ANIM = android.R.anim.fade_out;

    private LinearLayout stContainer;
    private ProgressBar stProgress;
    private ImageView stImage;
    private View content;
    private TextView stMessage;
    private Button stButton;
    private boolean animationEnabled;
    @AnimRes
    private int inAnimation;
    @AnimRes
    private int outAnimation;

    public StatefulLayout(Context context) {
        this(context, null);
    }

    public StatefulLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.stfStatefulLayout, 0, 0);
        animationEnabled = array.getBoolean(R.styleable.stfStatefulLayout_stfAnimationEnabled, DEFAULT_ANIM_ENABLED);
        inAnimation = array.getResourceId(R.styleable.stfStatefulLayout_stfInAnimation, DEFAULT_IN_ANIM);
        outAnimation = array.getResourceId(R.styleable.stfStatefulLayout_stfOutAnimation, DEFAULT_OUT_ANIM);
        array.recycle();
    }

    public boolean isAnimationEnabled() {
        return animationEnabled;
    }

    public void setAnimationEnabled(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
    }

    @AnimRes
    public int getInAnimation() {
        return inAnimation;
    }

    public void setInAnimation(@AnimRes int anim) {
        inAnimation = anim;
    }

    @AnimRes
    public int getOutAnimation() {
        return outAnimation;
    }

    public void setOutAnimation(@AnimRes int anim) {
        outAnimation = anim;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) throw new IllegalStateException(MSG_ONE_CHILD);
        setOrientation(VERTICAL);
        if (isInEditMode()) return; // to hide state views in designer
        content = getChildAt(0);
        LayoutInflater.from(getContext()).inflate(R.layout.stf_template, this, true);
        stContainer = (LinearLayout) findViewById(R.id.stContainer);
        stProgress = (ProgressBar) findViewById(R.id.stProgress);
        stImage = (ImageView) findViewById(R.id.stImage);
        stMessage = (TextView) findViewById(R.id.stMessage);
        stButton = (Button) findViewById(R.id.stButton);
    }

    // content //

    public void showContent() {
        if (isAnimationEnabled()) {
            if (stContainer.getVisibility() == VISIBLE) {
                Animation outAnim = createOutAnimation();
                outAnim.setAnimationListener(new CustomAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        stContainer.setVisibility(GONE);
                        content.setVisibility(VISIBLE);
                        content.startAnimation(createInAnimation());
                    }
                });
                stContainer.startAnimation(outAnim);
            }
        } else {
            stContainer.setVisibility(GONE);
            content.setVisibility(VISIBLE);
        }
    }

    // loading //

    public void showLoading() {
        showLoading(R.string.stfLoadingMessage);
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

    // empty //

    public void showEmpty() {
        showEmpty(R.string.stfEmptyMessage);
    }

    public void showEmpty(@StringRes int resId) {
        showEmpty(str(resId));
    }

    public void showEmpty(String message) {
        showStateByType(ErrorStateType.EMPTY, message, null);
    }

    // error //

    public void showError(OnClickListener clickListener) {
        showError(R.string.stfErrorMessage, clickListener);
    }

    public void showError(@StringRes int resId, OnClickListener clickListener) {
        showError(str(resId), clickListener);
    }

    public void showError(String message, OnClickListener clickListener) {
        showStateByType(ErrorStateType.ERROR, message, clickListener);
    }

    // offline

    public void showOffline(OnClickListener clickListener) {
        showOffline(R.string.stfOfflineMessage, clickListener);
    }

    public void showOffline(@StringRes int resId, OnClickListener clickListener) {
        showOffline(str(resId), clickListener);
    }

    public void showOffline(String message, OnClickListener clickListener) {
        showStateByType(ErrorStateType.OFFLINE, message, clickListener);
    }

    // location off //

    public void showLocationOff(OnClickListener clickListener) {
        showLocationOff(R.string.stfLocationOffMessage, clickListener);
    }

    public void showLocationOff(@StringRes int resId, OnClickListener clickListener) {
        showLocationOff(str(resId), clickListener);
    }

    public void showLocationOff(String message, OnClickListener clickListener) {
        showStateByType(ErrorStateType.LOCATION_OFF, message, clickListener);
    }

    // custom //

    /**
     * Shows custom state for given options. If you do not set buttonClickListener, the button will not be shown
     *
     * @param options customization options
     * @see com.gturedi.views.CustomStateOptions
     */
    public void showCustom(CustomStateOptions options) {
        initSate();

        if (options.getImageRes() != 0) {
            stImage.setVisibility(VISIBLE);
            stImage.setImageResource(options.getImageRes());
        }

        if (!TextUtils.isEmpty(options.getMessage())) {
            stMessage.setVisibility(VISIBLE);
            stMessage.setText(options.getMessage());
        }

        if (options.getClickListener() != null) {
            stButton.setVisibility(VISIBLE);
            stButton.setOnClickListener(options.getClickListener());
            if (!TextUtils.isEmpty(options.getButtonText())) {
                stButton.setText(options.getButtonText());
            }
        }
    }

    // helper methods //

    private void showStateByType(ErrorStateType type, String message, OnClickListener clickListener) {
        initSate();
        stImage.setVisibility(VISIBLE);
        stImage.setImageResource(type.imageRes);

        stMessage.setVisibility(VISIBLE);
        if (TextUtils.isEmpty(message)) {
            stMessage.setText(type.messageRes);
        } else {
            stMessage.setText(message);
        }

        if (clickListener == null) {
            stButton.setVisibility(GONE);
        } else {
            stButton.setVisibility(VISIBLE);
            stButton.setOnClickListener(clickListener);
        }
    }

    private void initSate() {
        stProgress.setVisibility(GONE);
        stImage.setVisibility(GONE);
        stMessage.setVisibility(GONE);
        stButton.setVisibility(GONE);

        if (isAnimationEnabled()) {
            if (stContainer.getVisibility() != VISIBLE) {
                Animation outAnim = createOutAnimation();
                outAnim.setAnimationListener(new CustomAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        content.setVisibility(GONE);
                        stContainer.setVisibility(VISIBLE);
                        stContainer.startAnimation(createInAnimation());
                    }
                });
                content.startAnimation(outAnim);
            }
        } else {
            content.setVisibility(GONE);
            stContainer.setVisibility(VISIBLE);
        }
    }

    private String str(@StringRes int resId) {
        return getContext().getString(resId);
    }

    private Animation createInAnimation() {
        return AnimationUtils.loadAnimation(getContext(), inAnimation);
    }

    private Animation createOutAnimation() {
        return AnimationUtils.loadAnimation(getContext(), outAnimation);
    }

}
