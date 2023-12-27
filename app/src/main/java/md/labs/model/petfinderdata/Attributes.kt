package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("shots_current")
    val shotsCurrent: Boolean? = null,
    @SerializedName("special_needs")
    val specialNeeds: Boolean? = null,
    @SerializedName("declawed")
    val declawed: Boolean? = null,
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean? = null,
    @SerializedName("house_trained")
    val houseTrained: Boolean? = null
)