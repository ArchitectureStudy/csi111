package com.sean.android.mvcsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.sean.android.mvcsample.issues.IssuesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    @BindView(R.id.enter_main_page_button)
    Button mEnterMainPageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mTitleTextView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(800).alpha(1.0f).translationY(-700).setInterpolator(new AccelerateInterpolator()).start();
        mGithubIdInputLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mGithubRepoInputLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mGithubRepoAccessTokenLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mEnterMainPageButton.animate().scaleY(1.0f).scaleX(1.0f).setDuration(1000).alpha(1.0f).start();


    }


    @OnClick(R.id.enter_main_page_button)
    public void onClickMoveMain(View view) {
        startActivity(new Intent(MainActivity.this, IssuesActivity.class));
        finish();
    }
}
