package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Environment(
    @SerializedName("cats")
    val cats: Boolean? = null,
    @SerializedName("children")
    val children: Boolean? = null,
    @SerializedName("dogs")
    val dogs: Boolean? = null
)