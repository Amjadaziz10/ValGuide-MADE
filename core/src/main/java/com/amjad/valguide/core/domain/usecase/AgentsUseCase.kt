package com.amjad.valguide.core.domain.usecase

import com.amjad.valguide.core.data.Resource
import com.amjad.valguide.core.domain.model.Agents
import kotlinx.coroutines.flow.Flow

interface AgentsUseCase {
    fun getAllAgents(): Flow<Resource<List<Agents>>>
    fun getFavoriteAgents(): Flow<List<Agents>>
    fun setFavoriteAgents(agents: Agents, state: Boolean)
}