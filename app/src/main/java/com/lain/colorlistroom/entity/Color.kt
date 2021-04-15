package com.lain.colorlistroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "colors")
data class Color(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int, var name: String, var color: Int) : Serializable
