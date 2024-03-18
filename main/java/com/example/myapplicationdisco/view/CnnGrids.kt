package com.example.myapplicationdisco.view

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationdisco.R
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*

class CnnGrids : AppCompatActivity() {

    private lateinit var gridAdapter: GridAdapter
    private lateinit var gridAdapterSportAndEn: GridAdapter
    private lateinit var gridView: GridView
    private lateinit var gridViewSportAndEn: GridView
    private lateinit var imageView: ImageView
    private lateinit var imageViewSportAndEn: ImageView
    private var itemList = ArrayList<String>()
    private var itemListSportAndEn = ArrayList<String>()
    private var lastUpdateTravel: String = ""
    private var lastUpdateSport: String = ""
    private var lastUpdateEn: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cnn_grids)
        initializeViews()

        setupGridAdapters()

        loadImages()

        setupReturnButton()

        startFetchingRSS()
    }

    private fun initializeViews() {
        gridView = findViewById(R.id.gridView)
        gridViewSportAndEn = findViewById(R.id.gridView2)
        imageView = findViewById(R.id.imageView)
        imageViewSportAndEn = findViewById(R.id.imageViewSportAndEn)
    }

    private fun setupGridAdapters() {
        gridAdapter = GridAdapter(this, itemList)
        gridAdapterSportAndEn = GridAdapter(this, itemListSportAndEn)
        gridView.adapter = gridAdapter
        gridViewSportAndEn.adapter = gridAdapterSportAndEn
    }

    private fun loadImages() {
        val imageUrl = "https://wifivox.com/wp-content/uploads/2013/07/cnn-travel.jpg"
        val imageUrlSportAndEn =
            "https://outfront.kw.com/wp-content/uploads/2021/11/OF_Scoial_Preview-1.jpg"
        Picasso.get().load(imageUrl).into(imageView)
        Picasso.get().load(imageUrlSportAndEn).into(imageViewSportAndEn)
    }

    private fun setupReturnButton() {
        val buttonGoToMain: Button = findViewById(R.id.buttonGoToMain)
        buttonGoToMain.setOnClickListener {
            val returnBtn = Intent(
                applicationContext,
                MainActivity::class.java
            )
            startActivity(returnBtn)
        }
    }

    private fun startFetchingRSS() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                FetchRSSTravel().execute("http://rss.cnn.com/rss/cnn_travel.rss")
                FetchRSS_SportAndEn().execute("http://rss.cnn.com/rss/edition_sport.rss")
                FetchRSS_SportAndEn().execute("http://rss.cnn.com/rss/edition_entertainment.rss")
            }
        }, 0, 5000)
    }

    private inner class FetchRSSTravel : AsyncTask<String, Void, Void>() {
        override fun doInBackground(vararg strings: String): Void? {
            val url = strings[0]
            try {
                val document = Jsoup.connect(url).get()
                val items = document.select("item")
                val lastPost = document.select("lastBuildDate")
                if (lastPost.equals(lastUpdateTravel))
                    return null
                itemList.clear()
                for (item in items) {
                    val title = item.select("title").first()?.text()
                    val link = item.select("link").first()?.text()

                    if (title != null && link != null) {
                        itemList.add(title)
                        itemList.add(link)

                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                runOnUiThread { Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show() }
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            gridAdapter.notifyDataSetChanged()
        }
    }

    private inner class FetchRSS_SportAndEn : AsyncTask<String, Void, Void>() {
        override fun doInBackground(vararg strings: String): Void? {
            val url = strings[0]
            try {
                val document = Jsoup.connect(url).get()
                val items = document.select("item")
                val lastPost = document.select("lastBuildDate")
                if (lastPost.equals(lastUpdateSport) || lastPost.equals(lastUpdateEn))
                    return null
                if (url.contains("sport"))
                    itemListSportAndEn.clear()
                for (item in items) {
                    val title = item.select("title").first()?.text()
                    val link = item.select("link").first()?.text()

                    if (title != null && link != null) {
                        itemListSportAndEn.add(title)
                        itemListSportAndEn.add(link)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                runOnUiThread { Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show() }
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            gridAdapterSportAndEn.notifyDataSetChanged()
        }
    }
}
