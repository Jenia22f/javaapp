package dev.vladmakarenko.financialPlanning.core.repository.local

interface Storage {
    fun getUrl(): String?
    fun setUrl(url: String)
}