package com.vancelopes.chucknorris.model

import com.google.gson.annotations.SerializedName

data class Joke (
    val id: String,
    val value: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    val url: String
)