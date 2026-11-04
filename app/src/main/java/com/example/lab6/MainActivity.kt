package com.example.lab6

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    data class Data(
        val photo: Int,
        val name: String
    )

    inner class MyAdapter(
        private val data: Array<Data>,
        private val layoutResId: Int
    ) : BaseAdapter() {

        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Any = data[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: layoutInflater.inflate(layoutResId, parent, false)

            val nameTextView = view.findViewById<TextView>(R.id.name)
            val imageView = view.findViewById<ImageView>(R.id.imageView)

            val item = data[position]
            nameTextView.text = item.name
            imageView.setImageResource(item.photo)

            return view
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士")
        val transPhotoIdArray = intArrayOf(
            R.drawable.trans1, R.drawable.trans2,
            R.drawable.trans3, R.drawable.trans4
        )

        val cubeeNameArray = arrayOf(
            "哭哭", "發抖", "再見", "生氣", "昏倒",
            "竊笑", "很棒", "你好", "驚嚇", "大笑"
        )
        val cubeePhotoIdArray = intArrayOf(
            R.drawable.cubee1, R.drawable.cubee2,
            R.drawable.cubee3, R.drawable.cubee4,
            R.drawable.cubee5, R.drawable.cubee6,
            R.drawable.cubee7, R.drawable.cubee8,
            R.drawable.cubee9, R.drawable.cubee10
        )

        val messageArray = arrayOf(
            "訊息１", "訊息２", "訊息３",
            "訊息４", "訊息５", "訊息６"
        )

        val transData = Array(transNameArray.size) { i ->
            Data(transPhotoIdArray[i], transNameArray[i])
        }

        val cubeeData = Array(cubeeNameArray.size) { i ->
            Data(cubeePhotoIdArray[i], cubeeNameArray[i])
        }

        val transAdapter = MyAdapter(transData, R.layout.trans_list)
        val cubeeAdapter = MyAdapter(cubeeData, R.layout.cubee_list)
        val messageAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messageArray)

        findViewById<Spinner>(R.id.spinner).adapter = transAdapter

        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = cubeeAdapter
        gridView.numColumns = 3

        findViewById<ListView>(R.id.listView).adapter = messageAdapter
    }
}