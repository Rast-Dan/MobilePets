package md.labs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import md.labs.model.petfinderdata.Pet

@Database(entities = [Pet::class], version = 1, exportSchema = false)
@TypeConverters(NestedTypesConverters::class)
abstract class PetsDb : RoomDatabase() {
    abstract fun petDao(): PetDao
}