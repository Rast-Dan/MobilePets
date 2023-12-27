package md.labs.db

import kotlinx.coroutines.flow.Flow
import md.labs.model.petfinderdata.Pet

interface PetRepository {
    fun getPetsFromRoom(): Flow<List<Pet>>
    fun getPetFromRoom(id: Int): Flow<Pet>
    fun addPetToRoom(pet: Pet)
    fun addPetListToRoom(pets: List<Pet>)
    fun updatePetInRoom(pet: Pet)
    fun deletePetFromRoom(pet: Pet)
}