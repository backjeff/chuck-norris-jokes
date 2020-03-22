package io.github.backjeff.chucknorrisjokes.data_remote.model.joke

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("total")
    val total: Int?,

    @SerializedName("result")
    val result: List<RemoteJoke>
)