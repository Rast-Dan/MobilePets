package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Pet(
    @SerializedName("website_link")
    val websiteLink: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("subject")
    val subject: String = "",
    @SerializedName("status_changed_at")
    val statusChangedAt: String = "",
    @SerializedName("description")
    val description: String?,
    @SerializedName("colors")
    val colors: Colors,
    @SerializedName("breeds")
    val breeds: Breeds,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("coat")
    val coat: String? = "",
    @SerializedName("environment")
    val environment: Environment,
    @SerializedName("size")
    val size: String = "",
    @SerializedName("contact")
    val contact: Contact,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("category")
    val category: String = "",
    @SerializedName("published_at")
    val publishedAt: String = "",
    @SerializedName("age")
    val age: String = "",
    @SerializedName("photos_url")
    val photosUrl: PhotosUrl,
    @SerializedName("status")
    val status: String = ""
)