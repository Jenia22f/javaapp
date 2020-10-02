package dev.vladmakarenko.financialPlanning.core.model


import com.google.gson.annotations.SerializedName

data class NetworkRequest(
    @SerializedName("language")
    val language: String,
    @SerializedName("UTM")
    val uTM: String?,
    @SerializedName("UserAgent")
    val userAgent: String,
    @SerializedName("app")
    val app: String
)