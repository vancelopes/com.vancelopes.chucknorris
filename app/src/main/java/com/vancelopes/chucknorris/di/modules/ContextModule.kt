package com.vancelopes.chucknorris.di.modules

import android.app.Application
import android.content.Context
import com.vancelopes.chucknorris.view.BaseView
import dagger.Module
import dagger.Provides

@Module
object ContextModule {
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application { return context.applicationContext as Application }

    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context { return baseView.context() }
}