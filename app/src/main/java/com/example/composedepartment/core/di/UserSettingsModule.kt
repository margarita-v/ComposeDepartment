package com.example.composedepartment.core.di

import android.content.Context
import com.example.composedepartment.interactor.UserSettingsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserSettingsModule {

    @Provides
    fun provideUserSettingsInteractor(
        @ApplicationContext appContext: Context
    ) = UserSettingsInteractor(appContext)
}