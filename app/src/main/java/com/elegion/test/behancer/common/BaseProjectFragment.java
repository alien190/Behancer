package com.elegion.test.behancer.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.project.Project;


import java.util.List;

public abstract class BaseProjectFragment extends PresenterFragment
        implements Refreshable, BaseProjectsAdapter.OnItemClickListener {

    protected Storage mStorage;
    private RefreshOwner mRefreshOwner;
    private RecyclerView mRecyclerView;
    private BaseProjectsAdapter mBaseProjectsAdapter;
    private View mErrorView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            mStorage = ((Storage.StorageOwner) context).obtainStorage();
        }

        if (context instanceof RefreshOwner) {
            mRefreshOwner = ((RefreshOwner) context);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }

        mBaseProjectsAdapter = new BaseProjectsAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mBaseProjectsAdapter);

        onRefreshData();
    }
    @Override
    public void onDetach() {
        mStorage = null;
        mRefreshOwner = null;

        super.onDetach();
    }

    @Override
    public abstract void onRefreshData();

    @Override
    public void onItemClick(String username){

    }

    public void showProjects(List<Project> projects) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mBaseProjectsAdapter.addData(projects, true);
    }

    public void showLoading() {
        mRefreshOwner.setRefreshState(true);
    }

    public void hideLoading() {
        mRefreshOwner.setRefreshState(false);
    }

    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }
}
