package com.example.newsapp20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest


class fHealth : Fragment() {

    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_f_health, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.healthRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        newsAdapter = NewsAdapter(view.context)
        fetchData(view)
        recyclerView.adapter = newsAdapter
        return view;
    }
    fun fetchData(view : View){

        //val queue = Volley.newRequestQueue(this.context)
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=4c35a647251a46459d87c7a58db12aaa"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,

            { response ->
                val newsJsonArray = response.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }

                newsAdapter.updateNews(newsArray)

            },
            { _ ->

            })

        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(view.context).addToRequestQueue(jsonObjectRequest)
    }
}