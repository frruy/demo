package com.example.exampleest.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampleest.databinding.ActivityMainBinding
import com.example.exampleest.ui.base.BaseActivity
import com.example.exampleest.ui.main.adapter.CitiesAdapter
import com.example.exampleest.ui.main.viewmodel.MainViewModel
import com.example.exampleest.utils.StateLiveData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private lateinit var adapter: CitiesAdapter

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    override suspend fun setupDatas() {
        super.setupDatas()
        viewModel.getCities()
    }

    override fun observeData() {
        super.observeData()
        viewModel.liveDataCities.observe(this) {
            if (it == null || it.status == StateLiveData.StateData.DataStatus.ERROR) {
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Something wrong!", Toast.LENGTH_SHORT).show()
                return@observe
            }

            if (it.status == StateLiveData.StateData.DataStatus.SUCCESS) {
                adapter.addData(it.data!!)
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CitiesAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }
}