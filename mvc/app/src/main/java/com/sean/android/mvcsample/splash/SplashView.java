package com.sean.android.mvcsample.splash;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.issues.IssuesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Seonil on 2017-01-20.
 */

public class SplashView extends RelativeLayout implements SplashContract.View {
    @BindView(R.id.title_textView)
    TextView mTitleTextView;

    @BindView(R.id.input_github_id_layout)
    TextInputLayout mGithubIdInputLayout;

    @BindView(R.id.input_github_repo_layout)
    TextInputLayout mGithubRepoInputLayout;

    @BindView(R.id.input_github_access_token_layout)
    TextInputLayout mGithubRepoAccessTokenLayout;

    @BindView(R.id.input_github_id_editText)
    TextInputEditText mGithubIdInputText;

    @BindView(R.id.input_github_repo_editText)
    TextInputEditText mGithubRepoInputText;

    @BindView(R.id.input_github_access_token_editText)
    TextInputEditText mGithubAccessTokenInputText;

    @BindView(R.id.enter_main_page_button)
    Button mEnterMainPageButton;

    private OnSplashActionListener onSplashActionListener;

    private SplashContract.Presenter mPresenter;


    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.layout_splash, this);
        ButterKnife.bind(this);
    }

    @Override
    public void showAnimation() {
        mTitleTextView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(800).alpha(1.0f).translationY(-700).setInterpolator(new AccelerateInterpolator()).start();
        mGithubIdInputLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mGithubRepoInputLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mGithubRepoAccessTokenLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mEnterMainPageButton.animate().scaleY(1.0f).scaleX(1.0f).setDuration(1000).alpha(1.0f).start();
    }

    @Override
    public void setGithubId(@NonNull String id) {
        mGithubIdInputText.setText(id);
    }

    @Override
    public void setGithubRepository(@NonNull String repository) {
        mGithubRepoInputText.setText(repository);
    }

    @Override
    public void setGithubAccessToken(@NonNull String accessToken) {
        mGithubAccessTokenInputText.setText(accessToken);
    }

    @Override
    public String getGithubIdText() {
        return mGithubIdInputText.getText().toString();
    }

    @Override
    public String getGithubRepositoryText() {
        return mGithubRepoInputText.getText().toString();
    }

    @Override
    public String getGithubAccessTokenText() {
        return mGithubAccessTokenInputText.getText().toString();
    }

    @Override
    public void showGithubMainUI() {
        getContext().startActivity(new Intent(getContext(), IssuesActivity.class));
        if (onSplashActionListener != null) {
            onSplashActionListener.activityFinish();
        }
    }

    @Override
    public void showAlertToast(String toastMessage) {
        Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.enter_main_page_button)
    public void onClickMoveMain(View view) {
        mPresenter.openGithubMainPage();
    }

    public void setOnSplashActionListener(OnSplashActionListener onSplashActionListener) {
        this.onSplashActionListener = onSplashActionListener;
    }
}
