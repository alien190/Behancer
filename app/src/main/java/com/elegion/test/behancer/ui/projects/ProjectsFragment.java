package com.elegion.test.behancer.ui.projects;


import android.content.Intent;
import android.os.Bundle;


import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.test.behancer.common.BaseProjectFragment;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;


public class ProjectsFragment extends BaseProjectFragment implements ProjectsView {


    @InjectPresenter
    ProjectsPresenter mPresenter;

    @ProvidePresenter
    ProjectsPresenter providePresenter() {
        return new ProjectsPresenter(mStorage);
    }

    @Override
    protected ProjectsPresenter getPresenter() {
        return mPresenter;
    }

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onItemClick(String username) {
        mPresenter.openProfileFragment(username);
    }

    @Override
    public void onRefreshData() {
        mPresenter.getProjects();
    }


    public void openProfileFragment(String username) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        Bundle args = new Bundle();
        args.putString(ProfileFragment.PROFILE_KEY, username);
        intent.putExtra(ProfileActivity.USERNAME_KEY, args);
        startActivity(intent);
    }

}
