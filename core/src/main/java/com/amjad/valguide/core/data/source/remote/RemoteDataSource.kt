package com.amjad.valguide.core.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.amjad.valguide.core.data.source.remote.api.ApiResponse
import com.amjad.valguide.core.data.source.remote.api.ApiService
import com.amjad.valguide.core.data.source.remote.response.AgentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    val agent = MutableLiveData<AgentList>()

    suspend fun getAllAgents(): Flow<ApiResponse<List<AgentList>>> {
        return flow {
            try {
                val response = apiService.getAgents()
                val dataArray = response.data
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}