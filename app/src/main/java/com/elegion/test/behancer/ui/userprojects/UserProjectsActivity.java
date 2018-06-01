package com.elegion.test.behancer.ui.userprojects;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.common.SingleFragmentActivity;

public class UserProjectsActivity extends SingleFragmentActivity {
    public static final String USERPROJECTS_KEY = "USERPROJECTS_KEY";

    @Override
    protected Fragment getFragment() {
        return UserProjectsFragment.newInstance(getIntent().getBundleExtra(USERPROJECTS_KEY));
    }
}
