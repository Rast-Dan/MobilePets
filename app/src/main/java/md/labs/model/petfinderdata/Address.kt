package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("country")
    val country: String = "",
    @SerializedName("address2")
    val address1: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("address1")
    val address2: String = "",
    @SerializedName("postcode")
    val postcode: String = "",
    @SerializedName("state")
    val state: String = ""
)