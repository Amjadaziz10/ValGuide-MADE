package com.amjad.valguide.core.data.source.local.room

import androidx.room.*
import com.amjad.valguide.core.data.source.local.entitiy.AgentsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentsDao {
    @Query("SELECT * FROM agents")
    fun getAllAgents(): Flow<List<AgentsEntity>>

    @Query("SELECT * FROM agents where favorite = 1")
    fun getFavoriteAgents(): Flow<List<AgentsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgents(agent: List<AgentsEntity>)

    @Update
    fun updateFavoriteAgents(tourism: AgentsEntity)

}