/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.justclean.task.R
import com.justclean.task.databinding.ItemPostCommentBinding
import com.justclean.task.model.PostComment


class PostsCommentAdapter : RecyclerView.Adapter<PostsCommentAdapter.PostsViewHolder>() {

    private val items: MutableList<PostComment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemPostCommentBinding>(
                inflater,
                R.layout.item_post_comment,
                parent,
                false
            )
        return PostsViewHolder(binding)
    }

    fun setPostCommentList(postList: List<PostComment>) {
        val previousItemSize = items.size
        items.clear()
        items.addAll(postList)
        notifyItemRangeChanged(previousItemSize, postList.size)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.binding.apply {
            postComment = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class PostsViewHolder(val binding: ItemPostCommentBinding) :
        RecyclerView.ViewHolder(binding.root)
}
