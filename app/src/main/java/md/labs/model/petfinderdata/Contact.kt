package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("address")
    val address: Address,
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("email")
    val email: String = ""
)