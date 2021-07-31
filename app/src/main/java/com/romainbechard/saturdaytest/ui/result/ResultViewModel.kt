package com.romainbechard.saturdaytest.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romainbechard.saturdaytest.PhotosHolder
import com.romainbechard.saturdaytest.data.MyRepository

class ResultViewModel : ViewModel() {

    private lateinit var repository: MyRepository
    private lateinit var photosHolder: PhotosHolder

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun configure(repository: MyRepository, photosHolder: PhotosHolder) {
        this.repository = repository
        this.photosHolder = photosHolder
    }

}