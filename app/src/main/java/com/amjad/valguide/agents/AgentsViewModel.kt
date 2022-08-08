package com.amjad.valguide.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amjad.valguide.core.domain.usecase.AgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(agentsUseCase: AgentsUseCase) : ViewModel() {
    val agents = agentsUseCase.getAllAgents().asLiveData()
}