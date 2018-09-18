package com.example.bhagya.androidtask

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

class HomeActivity : AppCompatActivity() {

    /*getRunningServices()This method was deprecated in API level 26 but it will still work for backward compatibility*/
    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { startCallerService() }
    }

    private fun startCallerService() {
        if (isMyServiceRunning(CallerService::class.java)) {
            stopService(Intent(baseContext, CallerService::class.java))
        } else {
            val intent = Intent(this, CallerService::class.java)
            startService(intent)
        }
    }

}
