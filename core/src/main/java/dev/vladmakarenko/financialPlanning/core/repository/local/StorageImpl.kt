package dev.vladmakarenko.financialPlanning.core.repository.local

import android.content.Context
import javax.inject.Inject

class StorageImpl @Inject constructor(private var context: Context): Storage {

    companion object{
        private const val STORAGE_KEY = "storage_key"
        private const val KEY = "key"
    }

    private val sharedPreferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)

    init {
        context = context.applicationContext
    }

    override fun getUrl(): String? {
        return sharedPreferences.getString(KEY, null)
    }

    override fun setUrl(url: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY, url)
        editor.apply()
    }
}