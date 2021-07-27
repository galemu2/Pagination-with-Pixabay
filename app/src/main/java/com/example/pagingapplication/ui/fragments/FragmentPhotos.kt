package com.example.pagingapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.pagingapplication.R
import com.example.pagingapplication.adapters.PhotoAdapter
import com.example.pagingapplication.adapters.PhotosLoadStateAdapter
import com.example.pagingapplication.data.model.Hit
import com.example.pagingapplication.databinding.FragmentPhotosBinding
import com.example.pagingapplication.ui.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPhotos : Fragment(R.layout.fragment_photos), PhotoAdapter.OnItemClickListener {

    private lateinit var adapter: PhotoAdapter
    private val viewModel by viewModels<PhotoViewModel>()

    private var _binding: FragmentPhotosBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPhotosBinding.bind(view)

        setupRecyclerView()

        viewModel.photo.observe(viewLifecycleOwner, Observer {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                val currentState = loadState.source.refresh

                progressBar.isVisible = currentState is LoadState.Loading
                recyclerViewPhotos.isVisible = currentState is LoadState.NotLoading
                textViewError.isVisible = currentState is LoadState.Error
                buttonRetry.isVisible = currentState is LoadState.Error

                // empty view
                if (currentState is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerViewPhotos.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = PhotoAdapter(this)
        binding.apply {
            setHasOptionsMenu(true)
            recyclerViewPhotos.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotosLoadStateAdapter { adapter.retry() },
                footer = PhotosLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener {
                adapter.retry()
            }
        }

    }

    override fun onItemClicked(hit: Hit) {
        val action = FragmentPhotosDirections.actionFragmentPhotosToFragmentPhotoDetails(hit)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}