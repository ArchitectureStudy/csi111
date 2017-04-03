package com.sean.android.mvvmsample;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.Until;
import android.widget.LinearLayout;

import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.Assert.assertTrue;

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
        findObject(By.res(Pattern.compile(".*:id/enter_main_page_button"))).clickAndWait(Until.newWindow(), DEFAULT_TIMEOUT); //인증 Click


        //TODO API통신 성공/실패 여부 Child가 있는지 여부로 판단
        mDevice.wait(Until.hasObject(By.scrollable(true).hasChild(By.clazz(LinearLayout.class))), 10000); // RecyclerView scroll될수 있게 아이템 가져오는 것

        assertTrue(mDevice.hasObject(By.scrollable(true).hasChild(By.clazz(LinearLayout.class))));

        mDevice.performActionAndWait(new Runnable() {
            @Override
            public void run() {
                findObject(By.res(Pattern.compile(".*:id/issues_recyclerView")).scrollable(true)).scroll(Direction.DOWN, 100);

            }
        }, Until.scrollFinished(Direction.DOWN), DEFAULT_TIMEOUT);

        mDevice.click(500, 1700); //아무 위치나 클릭
        findObject(By.res(Pattern.compile(".*:id/issues_recyclerView"))).getChildren().get(0).clickAndWait(Until.newWindow(), DEFAULT_TIMEOUT); //0번째 아이템 클릭


    }
}
