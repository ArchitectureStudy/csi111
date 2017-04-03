package com.sean.android.mvvmsample;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.view.inputmethod.InputMethodManager;

import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Seonil on 2017-04-03.
 */


@RunWith(AndroidJUnit4.class)
public abstract class BaseTest {
    public static final long LAUNCH_TIMEOUT = 10000;
    public static final long DEFAULT_TIMEOUT = 1500;

    protected UiDevice mDevice;


    @Before
    public void before() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertNotNull(mDevice);
        openApp();
    }

    private void openApp() {
        mDevice.pressHome();

        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        Intent intent = context.getPackageManager().getLaunchIntentForPackage(BuildConfig.APPLICATION_ID);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        mDevice.wait(Until.hasObject(By.pkg(BuildConfig.APPLICATION_ID).depth(0)), LAUNCH_TIMEOUT);
    }

    protected UiObject2 findButton(int textResourceId) {
        return findObject(button(textResourceId));
    }

    protected BySelector button(int textResourceId) {
        return By.text(string(textResourceId)).clickable(true);
    }

    protected  UiObject2 findByText(int textResourceId) {
        return findObject(byText(textResourceId));
    }

    protected BySelector byText(int textResourceId) {
        return By.text(string(textResourceId));
    }

    protected UiObject2 findByDesc(int textResourceId) {
        return findObject(byDesc(textResourceId));
    }

    protected BySelector byDesc(int textResourceId) {
        return By.desc(string(textResourceId));
    }

    protected String string(int textResourceId) {
        return getTargetContext().getString(textResourceId);
    }


    protected UiObject2 findObject(BySelector selector) {
        mDevice.wait(Until.hasObject(selector), DEFAULT_TIMEOUT);
        return mDevice.findObject(selector);
    }

    protected void hideKeyboard() {
        InputMethodManager manager = (InputMethodManager) getTargetContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        if(manager.isAcceptingText()) {
            mDevice.pressBack();
        }
    }

    protected void assertHas(BySelector selector) {
        assertTrue(mDevice.hasObject(selector));
    }
}
