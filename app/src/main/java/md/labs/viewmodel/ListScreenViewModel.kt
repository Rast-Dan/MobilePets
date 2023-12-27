package md.labs.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ListScreenViewModel : ViewModel() {

    private var petsList: ArrayList<Pet> by mutableStateOf(arrayListOf())
    var isPetsListLoaded: Boolean by mutableStateOf(false)
        private set

    private val petsListLoadedMutex = Mutex()

    suspend fun loadPetsList(context: Context) {
        if (!isPetsListLoaded) {
            viewModelScope.launch(Dispatchers.IO) {
                petsListLoadedMutex.withLock {
                    if (isPetsListLoaded)
                        return@launch
                    try {
                        // получаем строку в правильной кодировке
                        val jsonString: String = context.assets.open("data.json")
                            .bufferedReader(charset("UTF-8")).use { it.readText() }
                        val petListType = object : TypeToken<List<Pet>>() {}.type
                        petsList = Gson().fromJson(jsonString, petListType)
                        isPetsListLoaded = true
                    }
                    catch (e : IOException) {
                        val message = e.message
                        if (message != null)
                            Log.e(TAG, message)
                        e.printStackTrace()
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