package org.bedu.httpconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import org.bedu.httpconnection.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://swapi.dev/api/planets/"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            llamadaAsincrona()
        }

        binding.btnSincrono.setOnClickListener {
            Thread {
                llamadaSincrona()
            }.start()
        }
    }

    private fun llamadaAsincrona() {
        val okHttpClient = OkHttpClient()
        val planetNumber = Random.nextInt(1,60)
        val url = "$baseUrl$planetNumber/"
        val request = Request.Builder().url(url).build()

        okHttpClient.newCall(request).enqueue( object: Callback { //enque lo hace asincrona
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Response", e.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                try {
                    val json = JSONObject(body)
                    val phrase = "El planeta elegido se llama: "
                    val planet = json.getString("name")

                    runOnUiThread{
                        binding.textView.text ="$phrase $planet"
                    }

                } catch(e: JSONException) {

                }
            }
        })
    }

    //Nuestro thread se bloquea hasta recuperar la información
    fun llamadaSincrona(){

        val okHttpClient = OkHttpClient()
        val planetNumber = Random.nextInt(1,60)
        val url = "$baseUrl$planetNumber/"
        val request = Request.Builder().url(url).build()

        try {
            val response = okHttpClient.newCall(request).execute()
            val body = response.body?.string()

            val json = JSONObject(body)

            val phrase = getString(R.string.choosen_planet)
            val planet = json.getString("name")

            runOnUiThread{
                binding.textView.text ="$phrase $planet"
            }

        } catch (e: Error){
            Log.e("Error",e.toString())
        }
    }

}