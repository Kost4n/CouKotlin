package com.kost4n.coukotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kost4n.coukotlin.database.AppDataBase
import com.kost4n.coukotlin.database.Dependencies
import com.kost4n.coukotlin.database.repository.RecordRepository

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var recordRepository: RecordRepository =
        RecordRepository()
}