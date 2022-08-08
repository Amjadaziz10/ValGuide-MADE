package com.amjad.valguide.core.data.source.local.entitiy

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agents")
data class AgentsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    var uuid: String,

    @ColumnInfo(name = "displayName")
    var displayName: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "displayIcon")
    var displayIcon: String,

    @ColumnInfo(name = "fullPortraitV2")
    var fullPortraitV2: String,

    @ColumnInfo(name = "background")
    var background: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean
)