package com.romainbechard.saturdaytest.ui.main

import androidx.lifecycle.ViewModel
import com.romainbechard.saturdaytest.data.MyRepository

class MainViewModel : ViewModel() {

    private lateinit var repository: MyRepository

    fun configure(repository: MyRepository) {
        this.repository = repository
    }
}