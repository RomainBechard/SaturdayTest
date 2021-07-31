package com.romainbechard.saturdaytest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romainbechard.saturdaytest.PhotosHolder
import com.romainbechard.saturdaytest.data.MyRepository
import com.romainbechard.saturdaytest.data.model.Photo
import com.romainbechard.saturdaytest.data.tools.Result
import com.romainbechard.saturdaytest.tools.Event
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    private lateinit var repository: MyRepository
    private lateinit var photosHolder: PhotosHolder

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _badSelectionEvent = MutableLiveData<Event<Unit>>()
    val badSelectionEvent = _badSelectionEvent

    private val _goToNextPageEvent = MutableLiveData<Event<Unit>>()
    val goToNextPageEvent = _goToNextPageEvent

    private val _hitList: MutableLiveData<List<Photo>> = MutableLiveData(listOf())
    val hitList = _hitList

    fun configure(repository: MyRepository, photosHolder: PhotosHolder) {
        this.repository = repository
        this.photosHolder = photosHolder
    }

    fun launchSearch(subject: String) {
        _loading.value = true
        viewModelScope.launch {
            _hitList.value = fetchData(subject)
        }
    }

    fun validateSelection() {
        if (_hitList.value != null) {
            _hitList.value?.forEach {
                if (it.isSelected) {
                    photosHolder.selectedPhotos.add(it)
                }
            }
        }
        if (photosHolder.selectedPhotos.size <= 2) {
            _badSelectionEvent.value = Event(Unit)
        } else {
            _goToNextPageEvent.value = Event(Unit)
        }
    }


    private suspend fun fetchData(string: String): List<Photo> {
        val list = mutableListOf<Photo>()
        when (val result = repository.getSearchResult(string)) {
            is Result.Success -> {
                result.data.forEach { list.add(it) }
            }
            is Result.Error -> {
                _error.value = true
            }
        }
        _loading.value = false
        return list
    }

    init {
        _loading.value = false
        _error.value = false
    }
}