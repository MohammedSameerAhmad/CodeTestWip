package com.wipro.wiprocodetest.views

import android.os.Bundle
import com.wipro.wiprocodetest.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.screen_container, InfoListFragment())
                .commit()
        }
    }
}
