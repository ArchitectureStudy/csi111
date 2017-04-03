package com.sean.android.mvvmsample;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.Until;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by Seonil on 2017-04-03.
 */

public class IssueActivityTest extends BaseTest {


    @Override
    public void before() {
        super.before();
    }

    @Test
    public void startIssueActivity() throws InterruptedException {
        findObject(By.res(Pattern.compile(".*:id/enter_main_page_button"))).clickAndWait(Until.newWindow(), DEFAULT_TIMEOUT);

        mDevice.wait(Until.hasObject(By.res(Pattern.compile(".*:id/issues_recyclerView")).scrollable(true)), 3000);

        findObject(By.res(Pattern.compile(".*:id/issues_recyclerView")).scrollable(true)).scroll(Direction.DOWN, 100);




    }
}
