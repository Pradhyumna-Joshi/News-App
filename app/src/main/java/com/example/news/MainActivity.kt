package com.example.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inspector.PropertyReader
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest



open class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsAdapter
    private lateinit var url:String
    lateinit var data:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBarMain=findViewById<ProgressBar>(R.id.progressBar1)
        progressBarMain.visibility=View.VISIBLE
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)

        val intent=intent
        data = intent.getStringExtra("name").toString()


        mAdapter= NewsAdapter(this)
//        findViewById<ProgressBar>(R.id.progressBar).visibility=View.INVISIBLE
        fetchData(data,mAdapter,progressBarMain)
        recyclerView.adapter=mAdapter



    }





    open fun fetchData(data : String,adapter: NewsAdapter,progressBar: ProgressBar) {

        val url="https://newsapi.org/v2/top-headlines?country=in&category=$data&apiKey=e77079614aef496ea32f1e8ef26d07ef"
        val jsonObjectRequest=object :JsonObjectRequest(
            Method.GET,
            url,
            null,
            {
                progressBar.visibility=View.GONE
                val jsonArray=it.getJSONArray("articles")
                    val list=ArrayList<News>()
                    for (i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        val news=News(
                            jsonObject.getString("title"),
                            jsonObject.getString("author"),
                            jsonObject.getString("url"),
                            jsonObject.getString("urlToImage"),
                        )
                        list.add(news)

                    }
//                findViewById<ProgressBar>(R.id.progressBar).visibility=View.INVISIBLE
                    adapter.updateNews(list)

            },
            {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
        )
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"]="Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {

        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))

    }


}

