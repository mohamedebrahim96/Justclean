/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.favourite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.justclean.task.R
import com.justclean.task.base.DataBindingActivity
import com.justclean.task.databinding.ActivityFavouriteBinding
import com.justclean.task.ui.adapters.FavouritePostsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : DataBindingActivity() {

    @VisibleForTesting
    val viewModel: FavouriteViewModel by viewModels()
    private val binding: ActivityFavouriteBinding by binding(R.layout.activity_favourite)


    override fun onCreate(savedInstanceState: Bundle?) {
        // onTransformationEndContainerApplyParams()
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@FavouriteActivity
            adapter = FavouritePostsAdapter()
            vm = viewModel
        }
    }

    companion object {
        @VisibleForTesting
        fun startActivity(context: Context) {
            if (context is Activity) {
                val intent = Intent(context, FavouriteActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}
