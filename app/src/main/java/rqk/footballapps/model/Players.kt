package rqk.footballapps.model

import com.google.gson.annotations.SerializedName

data class Players(
    @SerializedName("strCutout")
    var iconPlayers: String? = null,

    @SerializedName("strPlayer")
    var namePlayers: String? = null,

    @SerializedName("strPosition")
    var positionPlayers: String? = null,

    @SerializedName("strFanart1")
    var fanartPlayers: String? = null,

    @SerializedName("strWeight")
    var weightPlayers: String? = null,

    @SerializedName("strHeight")
    var heightPlayers: String? = null,

    @SerializedName("strDescriptionEN")
    var descriptionPlayers: String? = null
)