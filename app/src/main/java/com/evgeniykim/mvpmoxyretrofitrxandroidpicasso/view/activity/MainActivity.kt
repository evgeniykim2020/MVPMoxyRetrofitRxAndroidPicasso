package com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.R
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.adapter.MainAdapter
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.extension.OnItemClickListener
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.extension.addOnItemClickListener
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.model.Variant
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.presenter.MainPresenter
import com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MainAdapter


    var selectedId = 0
    var id = 0
    var nameHz = ""
    var namePicture = ""


    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        adapter = MainAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)


        recyclerView.addOnItemClickListener(object :
            OnItemClickListener {
            override fun onItemClicked(viewType: Int, view: View) {
                Toast.makeText(this@MainActivity, itemClicked(viewType), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun showPicture(name: String, url: String, text: String) {
        adapter.setDataPicture(name, url, text)
        this.namePicture = name
    }

    override fun showHz(name: String, text: String) {
        adapter.setDataHz(name, text)
        this.nameHz = name
    }

    override fun showSelector(name: String, selectedId: Int, variants: List<Variant>, id: Int) {
        adapter.setDataSelector(name, selectedId, variants, id)

        this.selectedId = selectedId
        this.id = id
    }

    override fun showOrderView(views: List<String>) {
        adapter.setData(views)
    }

    override fun showError() {
        Log.i("TestLoad", "Error")
    }

    fun itemClicked(viewType: Int): String {
        return when (viewType) {
            0 -> this.nameHz
            1 -> this.namePicture
            2 -> "Selected id:${this.selectedId}"
            else -> "hz"
        }
    }
}