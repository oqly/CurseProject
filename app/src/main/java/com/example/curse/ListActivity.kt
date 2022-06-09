package com.example.curse

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.Volley


class ListActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView
    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        VolleyLog.DEBUG = true
        val id = intent.extras?.getString("id")

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE
        textView = findViewById(R.id.name_textView)
        textView.text = getString(R.string.loading)

        listView = findViewById(R.id.facultyList)
        requestQueue = Volley.newRequestQueue(this)

        val observer = Observer<Array<String?>> { array ->
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, array
            )
            listView.adapter = adapter
            progressBar.visibility = ProgressBar.INVISIBLE
            textView.text = "Ваш результат:"
        }

        val model: ParserViewModel by viewModels()
        model.listItems.observe(this, observer)
        model.init(id, requestQueue)
    }
}