/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.comment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.justclean.task.R
import com.justclean.task.base.DataBindingActivity
import com.justclean.task.databinding.ActivityCommentBinding
import com.justclean.task.extensions.argument
import com.justclean.task.model.Post
import com.justclean.task.ui.adapters.PostsCommentAdapter
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommentActivity : DataBindingActivity() {

    @Inject
    lateinit var commentViewModelFactory: CommentViewModel.AssistedFactory

    @VisibleForTesting
    val viewModel: CommentViewModel by viewModels {
        CommentViewModel.provideFactory(commentViewModelFactory, PostItem.id)
    }

    private val binding: ActivityCommentBinding by binding(R.layout.activity_comment)
    private val PostItem: Post by argument(EXTRA_POSTS)

    override fun onCreate(savedInstanceState: Bundle?) {
        // onTransformationEndContainerApplyParams()
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@CommentActivity
            post = PostItem
            vm = viewModel
            adapter = PostsCommentAdapter()
        }
    }

    companion object {
        @VisibleForTesting
        const val EXTRA_POSTS = "EXTRA_POSTS"

        fun startActivity(transformationLayout: TransformationLayout, post: Post) {
            val context = transformationLayout.context
            if (context is Activity) {
                val intent = Intent(context, CommentActivity::class.java)
                intent.putExtra(EXTRA_POSTS, post)
                TransformationCompat.startActivity(transformationLayout, intent)
            }
        }
    }
}
