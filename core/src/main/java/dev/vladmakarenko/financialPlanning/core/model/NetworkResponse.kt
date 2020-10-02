package dev.vladmakarenko.financialPlanning.core.model


import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("url")
    val url: String?
)