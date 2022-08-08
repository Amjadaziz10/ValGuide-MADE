package com.amjad.valguide.core.data.source

import com.amjad.valguide.core.data.NetworkBoundResource
import com.amjad.valguide.core.data.Resource
import com.amjad.valguide.core.data.source.local.LocalDataSource
import com.amjad.valguide.core.data.source.remote.RemoteDataSource
import com.amjad.valguide.core.data.source.remote.api.ApiResponse
import com.amjad.valguide.core.data.source.remote.response.AgentList
import com.amjad.valguide.core.domain.model.Agents
import com.amjad.valguide.core.domain.repository.IAgentsRepository
import com.amjad.valguide.core.utils.AppExecutors
import com.amjad.valguide.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AgentsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAgentsRepository {
    override fun getAllAgents(): Flow<Resource<List<Agents>>> =
        object : NetworkBoundResource<List<Agents>, List<AgentList>>() {
            override fun loadFromDB(): Flow<List<Agents>> {
                return localDataSource.getAllAgents().map{
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Agents>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<AgentList>>> =
                remoteDataSource.getAllAgents()

            override suspend fun saveCallResult(data: List<AgentList>) {
                val agentsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAgents(agentsList)
            }
        }.asFlow()


    override fun getFavoriteAgents(): Flow<List<Agents>> {
        return localDataSource.getFavoriteAgents().map{
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAgents(agents: Agents, state: Boolean) {
        val agentsEntity = DataMapper.mapDomainToEntity(agents)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAgents(agentsEntity, state) }
    }
}