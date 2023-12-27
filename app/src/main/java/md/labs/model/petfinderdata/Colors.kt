package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Colors(
    @SerializedName("secondary")
    val secondary: String? = null,
    @SerializedName("tertiary")
    val tertiary: String? = null,
    @SerializedName("primary")
    val primary: String? = null
)