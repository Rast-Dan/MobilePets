package md.labs.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import md.labs.model.petfinderdata.Pet
import md.labs.utils.Constants.Companion.PET_TABLE

@Dao
interface PetDao {
    @Query("SELECT * FROM $PET_TABLE ORDER BY id ASC")
    fun getPets(): Flow<List<Pet>>
    @Query("SELECT * FROM $PET_TABLE WHERE id = :id")
    fun getPet(id: Int): Flow<Pet>
    @Insert(onConflict = IGNORE)
    fun addPet(pet: Pet)
    @Insert(onConflict = IGNORE)
    fun addPets(pet: List<Pet>)
    @Update
    fun updatePet(pet: Pet)
    @Delete
    fun deletePet(pet: Pet)
}
