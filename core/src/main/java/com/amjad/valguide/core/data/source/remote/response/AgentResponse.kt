package com.amjad.valguide.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AgentResponse(
    val status: String,
    val data: List<AgentList>
)

@Parcelize
data class AgentList(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val fullPortraitV2: String,
    val background: String?,
):Parcelable

