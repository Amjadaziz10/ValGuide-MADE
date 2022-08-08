package com.amjad.valguide.core.utils

import com.amjad.valguide.core.data.source.local.entitiy.AgentsEntity
import com.amjad.valguide.core.data.source.remote.response.AgentList
import com.amjad.valguide.core.domain.model.Agents

object DataMapper {
    fun mapResponsesToEntities(input: List<AgentList>): List<AgentsEntity> {
        val agentsList = ArrayList<AgentsEntity>()
        input.map {
            val agents = AgentsEntity(
                uuid = it.uuid,
                displayName = it.displayName,
                description = it.description,
                displayIcon = it.displayIcon,
                fullPortraitV2 = it.fullPortraitV2,
                background = it.background,
                favorite = false
            )
            agentsList.add(agents)
        }
        return agentsList
    }

    fun mapEntitiesToDomain(input: List<AgentsEntity>): List<Agents> =
        input.map {
            Agents(
                uuid = it.uuid,
                displayName = it.displayName,
                description = it.description,
                displayIcon = it.displayIcon,
                fullPortraitV2 = it.fullPortraitV2,
                background = it.background,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Agents) = AgentsEntity(
        uuid = input.uuid,
        displayName = input.displayName,
        description = input.description,
        displayIcon = input.displayIcon,
        fullPortraitV2 = input.fullPortraitV2,
        background = input.background,
        favorite = input.favorite
    )
}