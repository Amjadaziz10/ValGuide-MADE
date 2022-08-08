package com.amjad.valguide.core.data.source.local

import com.amjad.valguide.core.data.source.local.entitiy.AgentsEntity
import com.amjad.valguide.core.data.source.local.room.AgentsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val agentsDao: AgentsDao) {

    fun getAllAgents(): Flow<List<AgentsEntity>> = agentsDao.getAllAgents()

    fun getFavoriteAgents(): Flow<List<AgentsEntity>> = agentsDao.getFavoriteAgents()

    suspend fun insertAgents(agentsList: List<AgentsEntity>) = agentsDao.insertAgents(agentsList)

    fun setFavoriteAgents(agents: AgentsEntity, newState: Boolean) {
        agents.favorite = newState
        agentsDao.updateFavoriteAgents(agents)
    }
}