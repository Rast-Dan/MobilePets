package md.labs.api

import ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit: Retrofit
    init {
        val builder = Retrofit.Builder()
            //указываем свой базовый URL
            .baseUrl("http://192.168.244.144:8000/")
            //используем GSON для конвертации
            .addConverterFactory(
                GsonConverterFactory.create())
        val okHttpClient = OkHttpClient.Builder()
        //зададим таймаут соединения на случай плохого интернета
        okHttpClient.connectTimeout(1, java.util.concurrent.TimeUnit.MINUTES).build()
        retrofit = builder.client(okHttpClient.build())
        .build()
    }
fun getAuthService() : ApiService {
//создаем экземпляр для дальнейшей работы
    return retrofit.create(ApiService::class.java)
}
}