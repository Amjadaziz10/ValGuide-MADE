package com.amjad.valguide.core.data.source.remote.api

import com.amjad.valguide.core.data.source.remote.response.AgentResponse
import retrofit2.http.GET

interface ApiService {
    @GET("agents?isPlayableCharacter=true")
    suspend fun getAgents(): AgentResponse
}