package com.example.pagingapplication.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pagingapplication.api.ApiInterface
import com.example.pagingapplication.data.PhotoPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(private val api: ApiInterface) {

    fun getSearchResult(query: String) = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            PhotoPagingSource(api, query)
        }).liveData
}
