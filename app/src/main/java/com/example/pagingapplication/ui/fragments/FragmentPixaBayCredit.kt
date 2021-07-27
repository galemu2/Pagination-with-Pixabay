package com.example.pagingapplication.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pagingapplication.R
import com.example.pagingapplication.databinding.PixabayCreditBinding

class FragmentPixaBayCredit : Fragment(R.layout.pixabay_credit) {

    private val url = "https://pixabay.com/"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = PixabayCreditBinding.bind(view)
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        binding.apply {
            textViewPixabay.setOnClickListener {
                it.context.startActivity(intent)
            }

            imageViewPixabay.setOnClickListener {
                it.context.startActivity(intent)
            }
        }

    }
}