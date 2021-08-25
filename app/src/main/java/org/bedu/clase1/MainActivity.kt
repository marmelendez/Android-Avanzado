package org.bedu.clase1

import android.animation.ValueAnimator
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
        binding.btnBarrel.setOnClickListener {
            barrelRoll()
        }
    }

    private fun barrelRoll() {
        val valueAnimator = ValueAnimator.ofFloat(0F,720F)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            binding.arwing.rotationX = value
        }
        valueAnimator.duration = 1000
        valueAnimator.start()
    }
}