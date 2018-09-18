package com.example.bhagya.androidtask

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

class CallerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caller)
    }

    fun onClickEnd(view: View) {
        stopService(Intent(baseContext, CallerService::class.java))
        onBackPressed()
        Toast.makeText(this, getString(R.string.call_end), Toast.LENGTH_SHORT).show()
    }
}
