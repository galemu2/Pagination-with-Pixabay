package com.example.pagingapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingapplication.databinding.LoadStateHeaderFooterBinding

class PhotosLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotosLoadStateAdapter.PhotosLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotosLoadStateViewHolder {
        val binding = LoadStateHeaderFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PhotosLoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class PhotosLoadStateViewHolder(private val binding: LoadStateHeaderFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }

    }

}