package com.example.pagingapplication.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingapplication.api.ApiInterface
import com.example.pagingapplication.data.model.Hit

class PhotoPagingSource(
    private val api: ApiInterface,
    private val query:String
) : PagingSource<Int, Hit>() {

    private val STARTING_PAGE = 1
    private val TAG = "PagingSource()"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {

        try {
            val position = params.key ?: 1
            Log.d(TAG, ">>> Loading position: $position")
            val response = api.getPhotoApiData(position, query)

            return LoadResult.Page(
                data = response.hits,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = if (response.hits.isNullOrEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}