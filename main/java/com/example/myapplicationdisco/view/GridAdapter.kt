package com.example.myapplicationdisco.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplicationdisco.R

class GridAdapter(private val mContext: Context, private val mItemList: ArrayList<String>) : ArrayAdapter<String>(mContext, 0, mItemList) {
    private val mBundle = Bundle()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false)
        }

        val item = getItem(position)
        item?.let {
            if (!it.startsWith("http")) {
                val textView = view!!.findViewById<TextView>(R.id.gridItemTextView)
                textView.text = it
            } else {
                val bt1 = view!!.findViewById<TextView>(R.id.button_open_link)
                bt1.text = "Open Article"
                bt1.visibility = View.VISIBLE
                bt1.setOnClickListener {
                    openLink(position)
                }
            }
        }

        return view!!
    }

    private fun openLink(position: Int) {
        val item = getItem(position)
        item?.let {
            val sharedPreferences: SharedPreferences = mContext.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("lastOpenArticle", getItem(position - 1))
            editor.putString("lastOpenUrl", it)
            editor.apply()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            intent.putExtras(mBundle)
            mContext.startActivity(intent)
        }
    }
}
