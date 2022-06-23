package com.ingrid.simuladordepartidas.domain

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("nome")
    val name: String,
    @SerializedName("forca")
    var stars: Int,
    @SerializedName("imagem")
    val image: String
)