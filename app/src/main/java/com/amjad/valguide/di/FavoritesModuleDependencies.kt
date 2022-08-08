package com.amjad.valguide.di

import com.amjad.valguide.core.domain.usecase.AgentsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoritesModuleDependencies {
    fun agentsUseCase(): AgentsUseCase
}