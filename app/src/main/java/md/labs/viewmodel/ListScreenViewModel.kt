package md.labs.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import md.labs.api.RetrofitHelper
import md.labs.db.PetRepository
import md.labs.model.petfinderdata.Pet
import java.io.IOException
import javax.inject.Inject
import kotlin.math.min
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import md.labs.model.petfinderdata.Pet
import java.io.IOException
import kotlin.math.min

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class ListScreenViewModel  @Inject constructor(
    private val repo: PetRepository
) : ViewModel() {
    private var petsList: ArrayList<Pet> by mutableStateOf(arrayListOf())
    var isPetsListLoaded: Boolean by mutableStateOf(false)
        private set

    var errorMessage: String? = null

    val pets = repo.getPetsFromRoom()

    private val petsListLoadedMutex = Mutex()

    @Composable
    fun getPetById(id: Int) : Pet {
        return repo.getPetFromRoom(id).collectAsState(initial = Pet()).value
    }

    suspend fun loadPetsList(context: Context) {
        if (!isPetsListLoaded) {

            viewModelScope.launch(Dispatchers.IO) {
                petsListLoadedMutex.withLock {
                    if (isPetsListLoaded)
                        return@launch
                    val authService = RetrofitHelper.getAuthService()
                    try {
                        val response = authService.getPets()
                        petsList = response
                        isPetsListLoaded = true
                        repo.addPetListToRoom(petsList)
                    }
                    catch (e : IOException) {
                        val message = e.message
                        if (message != null)
                            Log.e(TAG, message)
                        e.printStackTrace()
                        errorMessage = message
                        isPetsListLoaded = true
                    }
                }
            }
        }
    }

    private var cachedOffset = -1
    private var cachedCount = -1
    private var cachedPetsList: MutableList<Pet>? = null

    fun getPets(offset: Int, count: Int) : MutableList<Pet>? {
        if (offset == cachedOffset && count == cachedCount)
            return cachedPetsList
        if (count <= 0 || offset < 0 || offset >= petsList.size) {
            Log.w(TAG, "getPets() called with parameters offset=${offset}, count=${count}")
            return null
        }
        val ending = min(petsList.size, offset + count)
        cachedOffset = offset
        cachedCount = count
        cachedPetsList = petsList.subList(offset, ending)
        return cachedPetsList
    }

    companion object {
        const val TAG = "ListScreenViewModel"
    }
}