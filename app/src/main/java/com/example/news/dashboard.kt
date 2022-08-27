package com.example.news

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class dashboard : NewsItemClicked, MainActivity() {

    private lateinit var politics: CardView
    private lateinit var business: CardView
    private lateinit var science: CardView
    private lateinit var health: CardView
    private lateinit var entertainment: CardView
    private lateinit var sports: CardView
    lateinit var madapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        politics = findViewById(R.id.politics)
        business = findViewById(R.id.business)
        science = findViewById(R.id.science)
        health = findViewById(R.id.health)
        sports = findViewById(R.id.sports)
        entertainment = findViewById(R.id.entertainment)

        madapter = NewsAdapter(this)
        val progressBarDash = findViewById<ProgressBar>(R.id.progressBar)
        progressBarDash.visibility = View.VISIBLE

        politics.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", "politics")
            startActivity(intent)

        }

        business.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", "business")
            startActivity(intent)

        }

        science.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", "science")
            startActivity(intent)

        }

        entertainment.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", "entertainment")
            startActivity(intent)

        }

        health.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", "health")
            startActivity(intent)

        }

        sports.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", "sports")
            startActivity(intent)

        }

        findViewById<RecyclerView>(R.id.recyclerView1).apply {
            layoutManager = LinearLayoutManager(this@dashboard)
            adapter = madapter
            fetchData("politics", madapter, progressBarDash)

        }

    }

    override fun onItemClicked(item: News) {

        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}