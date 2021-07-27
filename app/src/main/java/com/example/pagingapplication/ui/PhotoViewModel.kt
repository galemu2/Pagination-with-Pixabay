package com.example.pagingapplication.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pagingapplication.repositories.PhotoRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        const val CURRENT_QUERY = "Current query"
        const val DEFAULT_QUERY = "cats"
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photo = currentQuery.switchMap { query ->
        repository.getSearchResult(query).cachedIn(viewModelScope)
    }

    fun searchPhoto(query: String) {
        currentQuery.value = query
    }

}