package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Colors(
    @SerializedName("secondary")
    val secondary: String?,
    @SerializedName("tertiary")
    val tertiary: String?,
    @SerializedName("primary")
    val primary: String?
)