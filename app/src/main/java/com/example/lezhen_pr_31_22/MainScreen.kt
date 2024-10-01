package com.example.lezhen_pr_31_22

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import com.bumptech.glide.Glide

class MainScreen : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var Rating : EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        Rating = findViewById(R.id.rating)
        btn = findViewById(R.id.button)
    }
    fun getResult(view: View) {
        val queue = Volley.newRequestQueue(this)
        var film = findViewById<TextView>(R.id.film)
        var release = findViewById<TextView>(R.id.release)
        var runtime = findViewById<TextView>(R.id.runtime)
        var Image = findViewById<ImageView>(R.id.image)
        var url = "https://www.omdbapi.com/?i=tt3896198&apikey=26a8dd9b"
        val stringRequest = StringRequest(Request.Method.GET, url, { response->
            var obj = JSONObject(response)
            film.setText("Film: " + obj.getString("Title"))
            release.setText("Release Date: " + obj.getString("Released"))
            runtime.setText("Runtime: " + obj.getString("Runtime"))
            Rating.visibility = View.VISIBLE
            val imageUrl = "https://img.omdbapi.com/?i=tt3896198&apikey=26a8dd9b"
            Glide.with(this).load(imageUrl).into(Image)
                                                                   },
            {
                Log.d("MyLog", "Volley error: $it")
                val rootView : View = findViewById(R.id.rootview)
                val snackBar = Snackbar.make(rootView, "Error", Snackbar.LENGTH_SHORT)
                val snackBarView = snackBar.view
                val textView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                textView.setTextColor(Color.rgb(77, 137,255))
                snackBar.show()
            }
        )
        queue.add(stringRequest)
    }
}