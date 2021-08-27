package org.bedu.interceptorandgson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import okhttp3.*
import org.bedu.interceptorandgson.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val url = "https://swapi.dev/api/people/1/"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJedi.setOnClickListener{
            llamarALaFuerza()
        }

        binding.btnSith.setOnClickListener{
            llamarALaFuerza(true)
        }
    }

    private fun llamarALaFuerza(isSith : Boolean = false) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val clientBuilder = client.newBuilder()

        clientBuilder.build().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                try {
                    val jedi = Gson().fromJson(body, Jedi::class.java)
                    /*val json = JSONObject(body)
                    val name = json.getString("name")
                    val height = json.getString("height")
                    val mass = json.getString("mass")*/

                    runOnUiThread{
                        binding.tvName.text = jedi.name
                        binding.tvHeight.text = jedi.height.toString()
                        binding.tvWeight.text = jedi.weight.toString()
                    }
                }catch (e: JSONException){

                }
            }

        })



    }
}