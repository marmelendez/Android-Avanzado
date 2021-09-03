package org.bedu.roomvehicles.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val model: String?,
    val brand: String?,
    @ColumnInfo(name="plates_number") val platesNumber: String?,
    @ColumnInfo(name="is_working") val isWorking: Boolean
)