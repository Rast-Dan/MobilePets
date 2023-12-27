import md.labs.model.petfinderdata.Pet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface ApiService {
    @GET(".")
    suspend fun getPets() : ArrayList<Pet>
}