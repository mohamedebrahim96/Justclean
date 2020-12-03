/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.main

import androidx.activity.viewModels

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import com.justclean.task.R
import com.justclean.task.base.DataBindingActivity
import com.justclean.task.databinding.ActivityMainBinding
import com.justclean.task.ui.adapter.PostsAdapter
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    @VisibleForTesting val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)


    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = PostsAdapter()
            vm = viewModel
        }    }
}