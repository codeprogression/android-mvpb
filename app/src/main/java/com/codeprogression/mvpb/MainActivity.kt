package com.codeprogression.mvpb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private fun buildComponent() {
        if (component == null) {
            component = DaggerMainComponent.builder().build()
            component!!.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildComponent()
        setContentView(R.layout.main_view)
    }

    override fun onDestroy() {
        super.onDestroy()
        component = null
    }

    companion object {

        fun component(): MainComponent? {
            return component
        }

        private var component: MainComponent? = null
    }
}
