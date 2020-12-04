/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.adapters

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.justclean.task.R
import com.justclean.task.databinding.ItemPostBinding
import com.justclean.task.model.Post
import com.justclean.task.ui.comment.CommentActivity
import com.justclean.task.ui.main.MainViewModel
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.runBlocking


class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val items: MutableList<Post> = mutableListOf()
    private var onClickedAt = 0L
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemPostBinding>(inflater, R.layout.item_post, parent, false)
        return PostsViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    CommentActivity.startActivity(binding.transformationLayout, items[position])
                    onClickedAt = currentClickedAt
                }
            }
            binding.shareBTN.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = items[position].title
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, items[position].body)
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                parent.context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
            }
//            binding.saveBTN.whatIfNotNull {
//                val position = bindingAdapterPosition
//                if (position==0)
//                    it.setImageResource(R.drawable.ic_liked)
//            }
            binding.saveBTN.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener

                mainViewModel.whatIfNotNull {
                    runBlocking {
                        if (items[position].liked) {
                            binding.saveBTN.setImageResource(R.drawable.ic_like)
                            it.savePost(items[position].id, false)
                        } else {
                            binding.saveBTN.setImageResource(R.drawable.ic_liked)
                            it.savePost(items[position].id, true)
                        }

                    }
                }

            }
        }
    }

    fun setPostList(postList: List<Post>) {
        val previousItemSize = items.size
        items.clear()
        items.addAll(postList)
        notifyItemRangeChanged(previousItemSize, postList.size)
    }

    fun setPostMainViewModel(mainViewModel: MainViewModel) {
        this.mainViewModel = mainViewModel
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.binding.apply {
            post = items[position]
            executePendingBindings()
        }
        if (items[position].liked)
            holder.binding.saveBTN.setImageResource(R.drawable.ic_liked)
        else
            holder.binding.saveBTN.setImageResource(R.drawable.ic_like)

    }

    override fun getItemCount() = items.size

    class PostsViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)
}
