package com.example.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.NewsRvItemsBinding

class NewsListAdapter(private val activity: MainActivity)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = NewsRvItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = items[position]
        if(holder is MyViewHolder){
            holder.binding.tvNewsHeading.text = currentItem.title
            holder.binding.tvSubHeading.text = currentItem.description
            Glide.with(holder.itemView.context)
                .load(currentItem.imageUrl)
                .into(holder.binding.ivNews)
        }

        holder.itemView.setOnClickListener {
            activity.onItemClickedListener(currentItem)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return items.size
    }

}

private class MyViewHolder(var binding: NewsRvItemsBinding): RecyclerView.ViewHolder(binding.root)