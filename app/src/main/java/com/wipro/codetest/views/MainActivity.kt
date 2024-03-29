package com.wipro.codetest.views

import android.os.Bundle
import com.wipro.codetest.R

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
