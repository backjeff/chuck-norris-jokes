package io.github.backjeff.chucknorrisjokes.data_remote.model.joke

import com.google.gson.annotations.SerializedName

data class RemoteJoke(
    @SerializedName("category")
    val category: Int?,

    @SerializedName("icon_url")
    val iconUrl: String?,

    @SerializedName("id")
    val id: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("value")
    val value: String?
)
