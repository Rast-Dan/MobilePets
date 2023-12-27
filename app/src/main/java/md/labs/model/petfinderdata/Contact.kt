package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName
import md.labs.model.petfinderdata.Address

data class Contact(
    @SerializedName("address")
    val address: Address = Address(),
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("email")
    val email: String = ""
)