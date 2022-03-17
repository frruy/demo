package com.example.exampleest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.exampleest.ui.MainApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<BD : ViewDataBinding, ViewModel : BaseViewModel>(val bindingFactory: (LayoutInflater) -> BD) :
    AppCompatActivity(), CoroutineScope {

    protected val binding: BD by lazy { bindingFactory(layoutInflater) }

    private lateinit var job: Job
    protected lateinit var viewModel: ViewModel
    protected abstract fun getViewModelClass(): Class<ViewModel>

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        initCoroutineScope()
        setContentView(binding.root)
        enableSplash()
        initViewModel()
        setUpViews()
        observeData()
    }

    open fun initCoroutineScope() {
        launch {
            setupDatas()
        }
    }

    open suspend fun setupDatas() {

    }

    open fun setUpViews() {}

    open fun observeData() {}

    protected open fun enableSplash() {}

    override fun onLowMemory() {
        super.onLowMemory()
        finish()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(MainApplication.application)
        ).get(getViewModelClass())
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}