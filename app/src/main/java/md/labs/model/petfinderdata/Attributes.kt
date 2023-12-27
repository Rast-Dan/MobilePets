package md.labs.model.petfinderdata

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("shots_current")
    val shotsCurrent: Boolean?,
    @SerializedName("special_needs")
    val specialNeeds: Boolean?,
    @SerializedName("declawed")
    val declawed: Boolean?,
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean?,
    @SerializedName("house_trained")
    val houseTrained: Boolean?
)