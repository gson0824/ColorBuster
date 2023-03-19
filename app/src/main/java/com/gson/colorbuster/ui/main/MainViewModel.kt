package com.gson.colorbuster.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gson.colorbuster.ui.model.ColorList
import com.gson.colorbuster.ui.model.Phrase

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private lateinit var colorList: ColorList

    private val _vocabulary = MutableLiveData<List<Phrase>>()
    var vocabulary: LiveData<List<Phrase>> = _vocabulary

    init {
        val jsonString = app.assets.open("data.json").bufferedReader().use { it.readText() }
        colorList = ColorList(jsonString)
        _vocabulary.value = colorList.terms
    }

}