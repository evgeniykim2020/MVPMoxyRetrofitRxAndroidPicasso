package com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.view

import com.arellomobile.mvp.MvpView
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.model.Variant


interface MainView: MvpView {

    fun showPicture(name: String, url: String, text: String)
    fun showHz(name: String, text: String)
    fun showSelector(name: String, selectedId: Int, variants: List<Variant>, id: Int)
    fun showOrderView(views: List<String>)
    fun showError()
}