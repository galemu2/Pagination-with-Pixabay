package com.example.pagingapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pagingapplication.R
import com.example.pagingapplication.data.model.Hit
import com.example.pagingapplication.databinding.ItemPhotoBinding

class PhotoAdapter(private val listener:OnItemClickListener,
    differCallback: DiffUtil.ItemCallback<Hit> = object : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

    }
) : PagingDataAdapter<Hit, PhotoAdapter.PhotoViewHolder>(differCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(hit: Hit)
    }

   inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

       init {
           binding.root.setOnClickListener {
               val position = bindingAdapterPosition
               if(position!=RecyclerView.NO_POSITION){
                   val item = getItem(position)
                   item?.let {
                       listener.onItemClicked(it)
                   }
               }
           }
       }

        fun bind(item: Hit) {

            binding.apply {
                Glide.with(imageViewItemPhoto)
                    .load(item.webformatURL)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageViewItemPhoto)

                textViewItemPhoto.text = item.user
            }
        }

    }
}