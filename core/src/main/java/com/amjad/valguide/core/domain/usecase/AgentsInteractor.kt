package com.amjad.valguide.core.domain.usecase

import com.amjad.valguide.core.domain.model.Agents
import com.amjad.valguide.core.domain.repository.IAgentsRepository
import javax.inject.Inject

class AgentsInteractor @Inject constructor(private val agentsRepository: IAgentsRepository): AgentsUseCase {
    override fun getAllAgents() = agentsRepository.getAllAgents()

    override fun getFavoriteAgents() = agentsRepository.getFavoriteAgents()

    override fun setFavoriteAgents(agents: Agents, state: Boolean) = agentsRepository.setFavoriteAgents(agents, state)
}