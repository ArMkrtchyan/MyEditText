package com.example.edittext

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edittext.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.progress.startAnimation(16000)

        CoroutineScope(Dispatchers.Main).launch {
            (15 downTo 0).forEach { if (it>=10) mBinding.time.text = "00 : $it" else mBinding.time.text = "00 : 0$it" ; delay(1000) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}