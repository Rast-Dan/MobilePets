package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Breeds(
    @SerializedName("secondary")
    val secondary: String? = "",
    @SerializedName("mixed")
    val mixed: Boolean = false,
    @SerializedName("primary")
    val primary: String = "",
    @SerializedName("unknown")
    val unknown: Boolean = false
)