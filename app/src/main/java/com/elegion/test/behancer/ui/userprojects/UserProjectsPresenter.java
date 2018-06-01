package com.elegion.test.behancer.ui.userprojects;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.ui.projects.ProjectsView;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UserProjectsPresenter extends BasePresenter<UserProjectsView> {
    private Storage mStorage;

    public UserProjectsPresenter(Storage mStorage) {
        this.mStorage = mStorage;
    }

    public void getUserProjects(String userName) {
        mCompositeDisposable.add(ApiUtils.getApiService().getUserProjects(userName)
                .doOnSuccess(response -> mStorage.insertProjects(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .doFinally(() -> getViewState().hideLoading())
                .subscribe(response -> getViewState().showProjects(response.getProjects()),
                        throwable -> getViewState().showError()));
    }

}
