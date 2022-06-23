package com.ingrid.simuladordepartidas.domain

import com.google.gson.annotations.SerializedName

data class Match(

    @SerializedName("descricao")
    val descricao: String,
    @SerializedName("local")
    val place: Place,
    @SerializedName("mandante")
    val homeTeam: Team,
    @SerializedName("visitante")
    val awayTeam: Team
)