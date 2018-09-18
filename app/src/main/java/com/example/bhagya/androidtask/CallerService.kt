package com.example.bhagya.androidtask


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationCompat.PRIORITY_MIN
import android.util.Log
import com.example.bhagya.androidtask.Constants.Companion.ONE_MINUTE_MILLIS


class CallerService : Service() {

    private var mStartMode = Service.START_STICKY
    private var mBinder: IBinder? = null
    private var mAllowRebind: Boolean = false

    override fun onCreate() {
        startForeground()
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        try {
            handlerForMinute()
        } finally {
            Log.d("Error", "handle the error")
        }
        return mStartMode
    }

    private fun handlerForMinute() {
        Handler().postDelayed({
            navigateToCallerActivity()
        }, ONE_MINUTE_MILLIS.toLong())
    }

    private fun navigateToCallerActivity() {
        val i = Intent(this, CallerActivity::class.java)
        startActivity(i)
    }


    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        return mAllowRebind
    }

    override fun onRebind(intent: Intent) {

    }

    override fun onDestroy() {

    }

    /*To start the app foreground and back ground*/
    private fun startForeground() {
        var channelId = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            channelId = createNotificationChannel()
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
        val notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build()
        startForeground(101, notification)
    }

    /*to getting channel id*/
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = getString(R.string.my_service)
        val channelName = getString(R.string.my_back_service)
        val chan = NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}
