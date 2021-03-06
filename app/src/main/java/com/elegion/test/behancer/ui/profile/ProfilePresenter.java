package com.elegion.test.behancer.ui.profile;


import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {
    private Storage mStorage;

    public ProfilePresenter(Storage mStorage) {
        this.mStorage = mStorage;
    }


    public void getProfile(String username) {
        mCompositeDisposable.add(ApiUtils.getApiService().getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(username) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .doFinally(() -> getViewState().hideLoading())
                .subscribe(
                        response -> getViewState().showUser(response.getUser()),
                        throwable -> getViewState().showError()));
    }
}
