package md.labs.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import md.labs.model.petfinderdata.Address
import md.labs.model.petfinderdata.Attributes
import md.labs.model.petfinderdata.Breeds
import md.labs.model.petfinderdata.Colors
import md.labs.model.petfinderdata.Contact
import md.labs.model.petfinderdata.Environment
import md.labs.model.petfinderdata.PhotosUrl

class NestedTypesConverters {
    @TypeConverter
    fun addressFromJson(value: String?): Address? {
        return Gson().fromJson(value, Address::class.java)
    }

    @TypeConverter
    fun addressToJson(value: Address?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun attributesFromJson(value: String?): Attributes? {
        return Gson().fromJson(value, Attributes::class.java)
    }

    @TypeConverter
    fun attributesToJson(value: Attributes?): String? {
        return Gson().toJson(value)
    }
    @TypeConverter
    fun breedsFromJson(value: String?): Breeds? {
        return Gson().fromJson(value, Breeds::class.java)
    }

    @TypeConverter
    fun breedsToJson(value: Breeds?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun colorsFromJson(value: String?): Colors? {
        return Gson().fromJson(value, Colors::class.java)
    }

    @TypeConverter
    fun colorsToJson(value: Colors?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun contactFromJson(value: String?): Contact? {
        return Gson().fromJson(value, Contact::class.java)
    }

    @TypeConverter
    fun contactToJson(value: Contact?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun environmentFromJson(value: String?): Environment? {
        return Gson().fromJson(value, Environment::class.java)
    }

    @TypeConverter
    fun environmentToJson(value: Environment?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun photosUrlFromJson(value: String?): PhotosUrl? {
        return Gson().fromJson(value, PhotosUrl::class.java)
    }

    @TypeConverter
    fun photosUrlToJson(value: PhotosUrl?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun listStringFromJson(value: String?): List<String>? {
        return Gson().fromJson(value, Array<String>::class.java)?.asList();
    }

    @TypeConverter
    fun listStringUrlToJson(value: List<String>?): String? {
        return Gson().toJson(value)
    }
}