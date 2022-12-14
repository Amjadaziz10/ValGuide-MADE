package com.amjad.valguide.core.di

import android.content.Context
import androidx.room.Room
import com.amjad.valguide.core.data.source.local.room.AgentDatabase
import com.amjad.valguide.core.data.source.local.room.AgentsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private val passphrase: ByteArray = SQLiteDatabase.getBytes("amjadaziz".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AgentDatabase = Room.databaseBuilder(
        context,
        AgentDatabase::class.java, "Agents.db"
    ).fallbackToDestructiveMigration().openHelperFactory(factory).build()

    @Provides
    fun provideTourismDao(database: AgentDatabase): AgentsDao = database.agentsDao()
}