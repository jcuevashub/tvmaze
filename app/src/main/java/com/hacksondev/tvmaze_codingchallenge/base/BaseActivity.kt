package com.hacksondev.tvmaze_codingchallenge.base

import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel> : BaseBindingActivity() {

    protected abstract val viewModel: VM
}