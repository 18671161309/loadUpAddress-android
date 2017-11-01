package com.china317.developlibrary.utils;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Administrator on 2016/11/3.
 */

public class ActivityStackManager {

    private static ActivityStackManager manager = new ActivityStackManager();
    private int mVisCount = 0;
    private Stack<Activity> mStack = new Stack<>();


    public static ActivityStackManager getInstance() {
        return manager;
    }

    private ActivityStackManager() {
    }


    public void addActivity(Activity activity) {
        mStack.push(activity);
    }

    public void removeActivity(Activity activity) {
        if (mStack.isEmpty()) {
            return;
        }
        mStack.remove(activity);
    }


    public void show() {
        mVisCount++;
    }

    public void hide() {
        mVisCount--;
    }

    public boolean isActivityVisible() {
        return mVisCount > 0;
    }

    public void clear() {
        if (mStack.isEmpty()) {
            return;
        }

        Iterator<Activity> iterator = mStack.iterator();

        while (iterator.hasNext()) {
            Activity remove = iterator.next();
            remove.finish();
            iterator.remove();
        }

    }

}
