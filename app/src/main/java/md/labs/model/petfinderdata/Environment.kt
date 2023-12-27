package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Environment(
    @SerializedName("cats")
    val cats: Boolean?,
    @SerializedName("children")
    val children: Boolean?,
    @SerializedName("dogs")
    val dogs: Boolean?
)