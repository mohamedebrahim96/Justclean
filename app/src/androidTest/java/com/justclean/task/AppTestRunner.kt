package com.justclean.task

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Created by @mohamedebrahim96 on 12/5/20.
 */
class AppTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application  = super.newApplication(cl, HiltTestApplication::class.java.name, context)
}