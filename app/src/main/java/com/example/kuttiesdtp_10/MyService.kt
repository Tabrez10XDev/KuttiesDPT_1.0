package com.example.kuttiesdtp_10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log.d
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.concurrent.thread

val dbrem = FirebaseDatabase.getInstance().getReference("remarks")

class MyService : Service() {
    lateinit var manager : NotificationManager
    lateinit var channel: NotificationChannel
    val channelid = "com.example.anhi1"
    val descp = "My Notification"
    override fun onCreate() {

        super.onCreate()
    }
    override fun onBind(intent: Intent): IBinder? {
return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val runn = Runnable {
         //   val inte = intent?.getStringExtra("pls")
          //  if(inte != "pls") {
                dbrem.addValueEventListener(dat)
         //   }
        }

        val thr = Thread(runn)
        thr.start()
        return START_STICKY
    }
    override fun onDestroy() {
        d("tabbb","poccccc")
        super.onDestroy()
    }

    var dat = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {

            var save1 = getSharedPreferences("database", Context.MODE_PRIVATE)
            var client = save1.getString("proj","")
            var dclient = save1.getString("dproj","")
            var proj = save1.getString("client","")
            var dproj = save1.getString("dclient","")
            var projs = proj?.split(",")
            var dprojs = dproj?.split(",")
            var temp = save1.getString("tempname","null")
            var ruba = save1.getString("rub", "null")
            var rubas = ruba?.length
            var rupee = p0.getValue().toString()
            var otu = false
var rubat = ruba!!.replace("[","").split("]")

            var rupeet = rupee.replace("[","").split("]")
            val shift = save1.getInt("shift",0)

            var tot = rupeet.minus(rubat)
            d("poo","$tot")
            var too = 0

            if(rupee == "null"){
                rupee = ""
            }

            var rupees = rupee.length
            //

            if(ruba == "null"){
                save1.edit().apply{
                    putString("rub",rupee)
                }.apply()
            }

            else if(rubas != rupees){
                var title= ""
                while(too < tot.size) {
                    var tush = tot.get(too).findLast { true }.toString()
                    var tots = tot.get(too).toString().split(",")
                    var vei = tots.size - 1
                    var win = tots.get(vei)
                    if (win.contains(":")){
                    var won = win.indexOf(":")
                        var wish = win
                        win = win.drop(won)
                        if(shift == 0) {
                            if (win.contains(temp.toString(), ignoreCase = true)) {

                                if (client != "") {
                                    var clients = client?.split(",")
                                    var cliente = clients?.size
                                    var off = 0
                                    while (off < cliente!!) {
                                        if (clients!!.get(off).findLast { true }
                                                .toString() == tush) {
                                            title = projs?.get(off).toString()
                                            val wo = wish.indexOf(tush)
                                            if (wish.length - 4 <= wo) {
                                                wish = wish.replace(tush.toString(), "")


                                            }
                                        }
                                        off++
                                    }
                                } else if (dclient != "") {
                                    var dclients = dclient?.split(",")
                                    var dcliente = dclients?.size
                                    var off = 0
                                    while (off < dcliente!!) {
                                        if (dclients!!.get(off).findLast { true }
                                                .toString() == tush) {
                                            title = dprojs?.get(off).toString()
                                        }
                                        off++
                                    }
                                }
                                manager =
                                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                                val builder = NotificationCompat.Builder(this@MyService, channelid)

                                val nten = Intent(this@MyService, firsts::class.java)
                                val ten = PendingIntent.getActivity(
                                    this@MyService,
                                    1,
                                    nten,
                                    PendingIntent.FLAG_ONE_SHOT
                                )
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                    channel =
                                        NotificationChannel(
                                            channelid,
                                            descp,
                                            NotificationManager.IMPORTANCE_HIGH
                                        )
                                    channel.enableLights(true)
                                    channel.lightColor = Color.RED
                                    channel.enableVibration(true)
                                    manager.createNotificationChannel(channel)


                                    builder
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle(title)
                                        .setContentText(wish)
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        // Set the intent that will fire when the user taps the notification
                                        .setAutoCancel(true)
                                        .setContentIntent(ten)

                                } else {
                                    builder
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle(title)
                                        .setContentText(wish)
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        // Set the intent that will fire when the user taps the notification
                                        .setAutoCancel(true)
                                        .setContentIntent(ten)

                                }
                                var m = (1..1999).random()
                                d("tab", "podsosdsd ")
                                d("tab", "$wish")
                                manager.notify(m, builder.build())
                                save1.edit().apply {
                                    putString("rub", rupee)
                                }.apply()
//
                            }
                        }
                        else{
                            if (client != "") {
                                var clients = client?.split(",")
                                var cliente = clients?.size
                                var off = 0
                                while (off < cliente!!) {
                                    if (clients!!.get(off).findLast { true }
                                            .toString() == tush) {
                                        title = projs?.get(off).toString()
                                        val wo = wish.indexOf(tush)
                                        if (wish.length - 4 <= wo) {
                                            wish = wish.replace(tush.toString(), "")


                                        }
                                    }
                                    off++
                                }
                            } else if (dclient != "") {
                                var dclients = dclient?.split(",")
                                var dcliente = dclients?.size
                                var off = 0
                                while (off < dcliente!!) {
                                    if (dclients!!.get(off).findLast { true }
                                            .toString() == tush) {
                                        title = dprojs?.get(off).toString()
                                    }
                                    off++
                                }
                            }
                            manager =
                                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            val builder = NotificationCompat.Builder(this@MyService, channelid)

                            val nten = Intent(this@MyService, firsts::class.java)
                            val ten = PendingIntent.getActivity(
                                this@MyService,
                                1,
                                nten,
                                PendingIntent.FLAG_ONE_SHOT
                            )
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                channel =
                                    NotificationChannel(
                                        channelid,
                                        descp,
                                        NotificationManager.IMPORTANCE_HIGH
                                    )
                                channel.enableLights(true)
                                channel.lightColor = Color.RED
                                channel.enableVibration(true)
                                manager.createNotificationChannel(channel)


                                builder
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle(title)
                                    .setContentText(wish)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    // Set the intent that will fire when the user taps the notification
                                    .setAutoCancel(true)
                                    .setContentIntent(ten)

                            } else {
                                builder
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle(title)
                                    .setContentText(wish)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    // Set the intent that will fire when the user taps the notification
                                    .setAutoCancel(true)
                                    .setContentIntent(ten)

                            }
                            var m = (1..1999).random()
                            d("tab", "podsosdsd ")
                            d("tab", "$wish")
                            manager.notify(m, builder.build())
                            save1.edit().apply {
                                putString("rub", rupee)
                            }.apply()
//
                        }
///////////////
                }
                    too++

                }
                }

                ///////////

        }
    }
}
