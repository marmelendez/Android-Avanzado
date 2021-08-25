package org.bedu.clase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.clase1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // esta parte nos ayuda a no tener que hacer findViewByID
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)


    }
}