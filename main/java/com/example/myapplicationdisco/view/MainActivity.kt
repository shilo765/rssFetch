package com.example.myapplicationdisco.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationdisco.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var dateTimeTextView: TextView
    private lateinit var lastFeedTextView: TextView
    private lateinit var secondPageButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setupName()
        startUpdatingTime()
        setupLastFeedTextView()
        setupSecondPageButton()
    }

    private fun initializeViews() {
        nameTextView = findViewById(R.id.nameTextView)
        dateTimeTextView = findViewById(R.id.dateTimeTextView)
        lastFeedTextView = findViewById(R.id.lastFeedTextView)
        secondPageButton = findViewById(R.id.secondPageButton)
    }

    private fun setupName() {
        nameTextView.text = "Shilo Ben Natan"
    }

    private fun startUpdatingTime() {
        updateTime()
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    updateTime()
                }
            }
        }, 0, 60 * 1000)
    }

    private fun updateTime() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val currentDateAndTime: String = sdf.format(Date())
        dateTimeTextView.text = currentDateAndTime
    }

    private fun setupLastFeedTextView() {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val lastOpenArticle = sharedPreferences.getString("lastOpenArticle", "").toString()
        lastFeedTextView.text = lastOpenArticle
        lastFeedTextView.setOnClickListener {
            val url = sharedPreferences.getString("lastOpenUrl", "").toString()
            openUrlInBrowser(url)
        }
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun setupSecondPageButton() {
        secondPageButton.setOnClickListener {
            val intent = Intent(this, CnnGrids::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }
    }

    companion object {
        const val SECOND_ACTIVITY_REQUEST_CODE = 100
    }
}
