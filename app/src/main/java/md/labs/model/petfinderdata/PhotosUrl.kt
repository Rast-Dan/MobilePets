package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class PhotosUrl(
    @SerializedName("preview")
    val preview: String? = "",
    @SerializedName("all")
    val all: List<String>
)