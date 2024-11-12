package com.testdeymer.database.di

import android.content.Context
import androidx.room.Room
import com.testdeymer.database.constants.DataConstants.KEY_NAME_DATABASE
import com.testdeymer.database.manager.NewsPulseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsPulseDatabase::class.java,
        KEY_NAME_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideHitDao(
        database: NewsPulseDatabase
    ) = database.getHitDao()
}