package com.kost4n.coukotlin.ui.home

import android.icu.text.AlphabeticIndex
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kost4n.coukotlin.database.*
import com.kost4n.coukotlin.database.dao.RecordDao
import com.kost4n.coukotlin.database.entity.RecordEntity
import com.kost4n.coukotlin.database.repository.RecordRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: RecordDao) : ViewModel() {



    fun save() {

    }

    private fun addRecord(record: RecordEntity) = viewModelScope.launch {
        repository.addRecord(record)
    }
}