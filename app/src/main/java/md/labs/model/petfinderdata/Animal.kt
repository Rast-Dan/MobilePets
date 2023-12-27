package md.labs.model.petfinderdata

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import md.labs.utils.Constants.Companion.PET_TABLE

@Entity(tableName = PET_TABLE)
data class Pet constructor (
    @SerializedName("website_link")
    val websiteLink: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("subject")
    val subject: String = "",
    @SerializedName("status_changed_at")
    val statusChangedAt: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("colors")
    val colors: Colors = Colors(),
    @SerializedName("breeds")
    val breeds: Breeds = Breeds(),
    @SerializedName("tags")
    val tags: List<String>? = emptyList(),
    @SerializedName("coat")
    val coat: String = "",
    @SerializedName("environment")
    val environment: Environment = Environment(),
    @SerializedName("size")
    val size: String = "",
    @SerializedName("contact")
    val contact: Contact = Contact(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("attributes")
    val attributes: Attributes = Attributes(),
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("category")
    val category: String = "",
    @SerializedName("published_at")
    val publishedAt: String = "",
    @SerializedName("age")
    val age: String = "",
    @SerializedName("photos_url")
    val photosUrl: PhotosUrl = PhotosUrl(),
    @SerializedName("status")
    val status: String = ""
)