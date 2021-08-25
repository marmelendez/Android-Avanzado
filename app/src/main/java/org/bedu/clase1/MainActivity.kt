package org.bedu.clase1

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
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

        binding.btnAlpha.setOnClickListener {
            blink()
        }

        binding.btnTiny.setOnClickListener {
            shrink()
        }

        binding.btnStart.setOnClickListener {
            start()
        }

        binding.btnPivot.setOnClickListener {
            pivot()
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

    private fun blink() {
        AnimatorInflater.loadAnimator(this, R.animator.blinking).apply {
            setTarget(binding.arwing)

            addListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                    Toast.makeText(applicationContext, "Iniciando blinking", Toast.LENGTH_SHORT).show()
                }

                override fun onAnimationEnd(p0: Animator?) {
                    Toast.makeText(applicationContext, "Terminando blinking", Toast.LENGTH_SHORT).show()
                }

                override fun onAnimationCancel(p0: Animator?) {
                    Toast.makeText(applicationContext, "Cancelando blinking", Toast.LENGTH_SHORT).show()
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    Toast.makeText(applicationContext, "Repitiendo blinking", Toast.LENGTH_SHORT).show()
                }
            })
            start()
        }
    }

    private fun shrink() {
        AnimatorInflater.loadAnimator(this, R.animator.shrink).apply {
            setTarget(binding.arwing)
            start()
        }
    }

    private fun start() {
        binding.arwing.animate().apply {
            translationX(0F)
            translationY(0F)
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }

    private fun pivot() {
        val initPivotX = PropertyValuesHolder.ofFloat("pivotX", 0f)
        val initPivotY = PropertyValuesHolder.ofFloat("pivotY", 0f)
        val transparent = PropertyValuesHolder.ofFloat("alpha", 0.6f)
        val animation1 = ObjectAnimator.ofPropertyValuesHolder(binding.arwing, initPivotX, initPivotY, transparent)
        animation1.duration = 500

        val pivotCenterX = binding.arwing.width.toFloat() / 2f
        val pivotCenterY = binding.arwing.height.toFloat() / 2f
        val centerPivotX = PropertyValuesHolder.ofFloat("pivotX", pivotCenterX)
        val centerPivotY = PropertyValuesHolder.ofFloat("pivotY", pivotCenterY)
        val opacy = PropertyValuesHolder.ofFloat("alpha", 1f)
        val animation2 = ObjectAnimator.ofPropertyValuesHolder(binding.arwing, centerPivotX, centerPivotY, opacy).apply {
                duration = 500
                startDelay = 4000
            }


        AnimatorSet().apply {
            playSequentially(animation1, animation2)
            start()
        }

    }
}