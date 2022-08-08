package com.amjad.valguide.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amjad.valguide.core.domain.usecase.AgentsUseCase

class FavoritesViewModel(agentsUseCase: AgentsUseCase) : ViewModel() {
    val agents = agentsUseCase.getFavoriteAgents().asLiveData()
}