package io.github.backjeff.chucknorrisjokes.data_remote.model.joke

import com.google.gson.annotations.SerializedName

data class RemoteJokeCategory(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("value")
    val value: String?
)
