package com.huutho.utilsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.huutho.utilslib.printE

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printE { "This is log error" }
    }
}
