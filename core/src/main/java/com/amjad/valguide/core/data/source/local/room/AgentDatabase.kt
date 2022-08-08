package com.amjad.valguide.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amjad.valguide.core.data.source.local.entitiy.AgentsEntity

@Database(entities = [AgentsEntity::class], version = 1, exportSchema = false)
abstract class AgentDatabase: RoomDatabase() {
    abstract fun agentsDao(): AgentsDao

}