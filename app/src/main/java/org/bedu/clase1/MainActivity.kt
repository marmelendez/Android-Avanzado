package org.bedu.clase1

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.CycleInterpolator
//import android.view.animation.LinearInterpolator
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

        binding.btnSin.setOnClickListener {
            esquivar()
        }
    }

    private fun barrelRoll() {
        val valueAnimator = ValueAnimator.ofFloat(0F,720F) //360 * 2
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            binding.arwing.rotationX = value
        }
        valueAnimator.duration = 2000
        //valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.start()
    }

    private fun esquivar() {
        /*ObjectAnimator.ofFloat(binding.arwing, "translationX", 200F).apply{
            duration = 3000
            interpolator = CycleInterpolator(1f)
            start()
        }*/

        //Inflar un xml = crear las respectivas clases de un animator, mapeo de xml a clases
        //En este caso vamos a crear nuestro objeto de ObjectAnimator
        AnimatorInflater.loadAnimator(this, R.animator.esquivar).apply {
            setTarget(binding.arwing)
            start()
        }
    }
}