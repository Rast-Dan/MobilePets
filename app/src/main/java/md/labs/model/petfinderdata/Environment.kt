package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Environment(
    @SerializedName("cats")
    val cats: Boolean = false,
    @SerializedName("children")
    val children: Boolean = false,
    @SerializedName("dogs")
    val dogs: Boolean = false
)