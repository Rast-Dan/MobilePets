package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("shots_current")
    val shotsCurrent: Boolean = false,
    @SerializedName("special_needs")
    val specialNeeds: Boolean = false,
    @SerializedName("declawed")
    val declawed: Boolean = false,
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean = false,
    @SerializedName("house_trained")
    val houseTrained: Boolean = false
)