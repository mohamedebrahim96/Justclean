/**
 * Created by @mohamedebrahim96 on 02,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.ui.details

import android.app.Activity
import android.content.Intent
import androidx.annotation.VisibleForTesting
import com.justclean.task.model.Post
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout

class DetailActivity {

    companion object {
        @VisibleForTesting
        const val EXTRA_POST = "EXTRA_POST"

        fun startActivity(transformationLayout: TransformationLayout, post: Post) {
            val context = transformationLayout.context
            if (context is Activity) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_POST, post)
                TransformationCompat.startActivity(transformationLayout, intent)
            }
        }
    }
}
