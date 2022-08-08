package com.amjad.valguide.core.di

import com.amjad.valguide.core.data.source.AgentsRepository
import com.amjad.valguide.core.domain.repository.IAgentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(agentsRepository: AgentsRepository): IAgentsRepository
}
