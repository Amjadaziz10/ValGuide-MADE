package com.amjad.valguide.core.domain.repository

import com.amjad.valguide.core.data.Resource
import com.amjad.valguide.core.domain.model.Agents
import kotlinx.coroutines.flow.Flow

interface IAgentsRepository {
    fun getAllAgents(): Flow<Resource<List<Agents>>>

    fun getFavoriteAgents(): Flow<List<Agents>>

    fun setFavoriteAgents(agents: Agents, state: Boolean)
}