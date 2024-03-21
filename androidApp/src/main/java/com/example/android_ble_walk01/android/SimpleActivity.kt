package com.example.android_ble_walk01.android

import androidx.lifecycle.ViewModelProvider
import com.example.android_ble_walk01.SimpleViewModel

class SimpleActivity : MvvmActivity<ActivitySimpleBinding, SimpleViewModel>() {
    override val layoutId: Int = R.layout.activity_simple
    override val viewModelVariableId: Int = BR.viewModel
    override val viewModelClass: Class<SimpleViewModel> = SimpleViewModel::class.java

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return createViewModelFactory { SimpleViewModel() }
    }
}