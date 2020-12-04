package com.justclean.task.workmanager


/**
 * Created by @mohamedebrahim96 on 04,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.justclean.task.R
import com.justclean.task.repository.MainRepository
import kotlinx.coroutines.runBlocking


class SyncWorker(
    ctx: Context, params: WorkerParameters, val mainRepository: MainRepository
) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        if (runAttemptCount > 0) return Result.failure()
        runBlocking {
            mainRepository.fetchPostList(
                onSuccess = { appContext.getString(R.string.success) },
                onError = { appContext.getString(R.string.error) }
            ).asLiveData()
        }

        return Result.success()
    }
}