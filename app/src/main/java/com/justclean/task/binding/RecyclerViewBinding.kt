/**
 * Created by @mohamedebrahim96 on 03,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.justclean.task.model.Post
import com.justclean.task.model.PostComment
import com.justclean.task.ui.adapters.FavouritePostsAdapter
import com.justclean.task.ui.adapters.PostsAdapter
import com.justclean.task.ui.adapters.PostsCommentAdapter
import com.justclean.task.ui.favourite.FavouriteViewModel
import com.justclean.task.ui.main.MainViewModel
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty


object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }


    @JvmStatic
    @BindingAdapter("adapterPostList","mainViewModel")
    fun bindAdapterPostList(view: RecyclerView, postList: List<Post>?,mainViewModel: MainViewModel) {
        postList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<PostsAdapter> { adapter ->
                adapter.setPostList(itemList)
                adapter.setPostMainViewModel(mainViewModel)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterPostList","favViewModel")
    fun bindAdapterFavPostList(view: RecyclerView, postList: List<Post>?,favouriteViewModel: FavouriteViewModel) {
        postList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<FavouritePostsAdapter> { adapter ->
                adapter.setPostList(itemList)
                adapter.setPostFavViewModel(favouriteViewModel)
            }
        }
    }


    @JvmStatic
    @BindingAdapter("adapterPostCommentList")
    fun bindAdapterPostCommentList(view: RecyclerView, postList: List<PostComment>?) {
        postList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<PostsCommentAdapter> { adapter ->
                adapter.setPostCommentList(itemList)
            }
        }
    }
}