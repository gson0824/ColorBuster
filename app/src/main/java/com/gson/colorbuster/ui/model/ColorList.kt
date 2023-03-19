package com.gson.colorbuster.ui.model

import android.util.Log
import com.google.gson.Gson

private const val TAG = "ColorList"

data class Phrase(val name: String, val hexString: String)

class Vocabulary : ArrayList<Phrase>()

class ColorList(jsonString: String) {
    var terms = ArrayList<Phrase>()
    init {
        val gson = Gson()
        terms = gson.fromJson(jsonString, Vocabulary::class.java)
        Log.d(TAG, terms.toString())
    }
}