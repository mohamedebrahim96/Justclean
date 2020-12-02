package com.justclean.task.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
class DataBindingActivity : AppCompatActivity(){

    protected inline fun <reified T : ViewDataBinding> binding(
            @LayoutRes resId: Int): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }
}