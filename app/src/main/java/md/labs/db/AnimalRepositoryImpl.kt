package md.labs.db

import md.labs.model.petfinderdata.Pet

class PetRepositoryImpl(
    private val petDao: PetDao
) : PetRepository {
    override fun getPetsFromRoom() = petDao.getPets()
    override fun getPetFromRoom(id: Int) = petDao.getPet(id)
    override fun addPetToRoom(pet: Pet) = petDao.addPet(pet)
    override fun addPetListToRoom(pets: List<Pet>) = petDao.addPets(pets)
    override fun updatePetInRoom(pet: Pet) = petDao.updatePet(pet)
    override fun deletePetFromRoom(pet: Pet) = petDao.deletePet(pet)
}
