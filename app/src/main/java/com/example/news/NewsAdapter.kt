package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener

class NewsAdapter(private val listener:NewsItemClicked):RecyclerView.Adapter<NewsViewHolder>() {


    private val items :ArrayList<News> =ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener() {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.ImageURL).into(holder.image)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

}



class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    var image=itemView.findViewById<ImageView>(R.id.imageView)
    var title:TextView=itemView.findViewById(R.id.title)
    var author=itemView.findViewById<TextView>(R.id.author)

}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}