package com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.model.ChatApi
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    private fun loadData() {
        val apiService = ChatApi.create()
        apiService.loadJson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showOrderView(it.view)
                viewState.showHz(it.data[0].name, it.data[0].data.text)
                viewState.showPicture(it.data[1].name, it.data[1].data.url, it.data[1].data.text)
                viewState.showSelector(it.data[2].name, it.data[2].data.selectedId, it.data[2].data.variants, it.data[2].data.variants[0].id)
            }, {

                viewState.showError()
            })
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }
}