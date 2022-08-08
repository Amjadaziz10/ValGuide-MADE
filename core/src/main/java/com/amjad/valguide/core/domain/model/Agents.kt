package com.amjad.valguide.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agents(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val fullPortraitV2: String,
    val background: String?,
    val favorite: Boolean
) : Parcelable
