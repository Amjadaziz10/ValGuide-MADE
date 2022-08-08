package com.amjad.valguide.agents.detailagent

import androidx.lifecycle.ViewModel
import com.amjad.valguide.core.domain.model.Agents
import com.amjad.valguide.core.domain.usecase.AgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailAgentViewModel @Inject constructor(private val agentsUseCase: AgentsUseCase) : ViewModel() {
    fun setFavoriteAgent(agent: Agents, newStatus:Boolean) =
        agentsUseCase.setFavoriteAgents(agent, newStatus)
}