package org.bedu.roomvehicles.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Vehicle::class), version = 1)
abstract class VehicleDB : RoomDatabase(){

    companion object {
        private var dbInstance : VehicleDB? = null
        const val DB_NAME = "Vehicles_DB"

        fun getInstace(context: Context) : VehicleDB? {
            if (dbInstance == null) {
                synchronized(VehicleDB::class) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        VehicleDB::class.java,
                        DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()

                }
            }
            return dbInstance
        }
    }

    abstract fun vehicleDao(): VehicleDao



}