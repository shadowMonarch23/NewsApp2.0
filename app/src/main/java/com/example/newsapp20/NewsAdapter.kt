package com.example.newsapp20

import android.content.Context
import android.net.Uri
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val mContext : Context): RecyclerView.Adapter<NewsViewHolder> (){

    private val newsItems: ArrayList<News> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.title.text = newsItems[position].title
        if(newsItems[position].author.equals("null")){
            holder.author.text = newsItems[position].url.substring(8)
        }else{
            holder.author.text = newsItems[position].author
        }
        //holder.imageView.setImageResource(R.drawable.image1)
        if(newsItems[position].imageUrl.equals("null")){
            holder.imageView.setImageResource(R.drawable.no_image)
        }else{
            Glide.with(holder.itemView.context).load(newsItems[position].imageUrl).into(holder.imageView)
        }
        holder.title.setOnClickListener {
            onItemClicked(newsItems[position])
        }
    }

    fun onItemClicked(item : News){
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(mContext, Uri.parse(item.url))
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    fun updateNews(updatedNews : ArrayList<News>){
        newsItems.clear()
        newsItems.addAll(updatedNews)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title : TextView = itemView.findViewById(R.id.title)
    val author : TextView = itemView.findViewById(R.id.author)
    val imageView : ImageView = itemView.findViewById(R.id.image)
}

