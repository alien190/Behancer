package com.elegion.test.behancer.ui.userprojects;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.test.behancer.common.BaseProjectFragment;


public class UserProjectsFragment extends BaseProjectFragment implements UserProjectsView {

    public static final String USERNAME_KEY = "USERNAME_KEY";

    private String mUserName;

    @InjectPresenter
    UserProjectsPresenter mPresenter;

    @ProvidePresenter
    UserProjectsPresenter providePresenter() {
        return new UserProjectsPresenter(mStorage);
    }

    @Override
    protected UserProjectsPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            mUserName = getArguments().getString(USERNAME_KEY);
        }
            super.onActivityCreated(savedInstanceState);
    }

    public static UserProjectsFragment newInstance(Bundle bundle) {
        UserProjectsFragment userProjectsFragment = new UserProjectsFragment();
        userProjectsFragment.setArguments(bundle);
        return userProjectsFragment;
    }

    @Override
    public void onRefreshData() {
        mPresenter.getUserProjects(mUserName);
    }
}