package ru.anikey.mymindcards.activities

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.anikey.mymindcards.R

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
