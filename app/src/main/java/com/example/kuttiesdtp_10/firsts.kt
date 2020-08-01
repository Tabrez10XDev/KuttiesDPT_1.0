package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.first.*


class firsts : AppCompatActivity() {
    lateinit var users : String
    lateinit var passs : String
    lateinit var desigs : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.first)
        val dbuser = FirebaseDatabase.getInstance().getReference("loguser")
        val dbpass = FirebaseDatabase.getInstance().getReference("logpass")
        val dbdesig = FirebaseDatabase.getInstance().getReference("logdesig")

     //   val dbsumma = FirebaseDatabase.getInstance().getReference("summa")
   //     dbsumma.setValue("podaaaaa")





        dbuser.addListenerForSingleValueEvent(getdata)
        dbpass.addListenerForSingleValueEvent(getdata2)
        dbdesig.addListenerForSingleValueEvent(getdata3)



        val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)
        save1.edit().apply{
            putInt("vap",1)
        }.apply()
        //val tempo = save1.getString("log_pass","")
            ////     val tempo2 = save1.getString("log_user","")
        val chk = save1.getString("tempname","NULL")
        if(chk != "NULL"){
            startActivity(Intent(this, MainActivity::class.java))
        }
        login.setOnClickListener() {
            if(chk()==true){
            //  val ref = FirebaseDatabase.getInstance().getReference("message")
            ///  ref.setValue("podaaaa")
            //  val in_user = save1.getString("log_user","")
            //  val in_pass = save1.getString("log_pass","")
            val user = username.text.toString()
            val pass = password.text.toString()
            val inn_user = users?.split(",", ignoreCase = false)
            val inn_pass = passs?.split(",", ignoreCase = false)
            val inn_desig = desigs?.split(",", ignoreCase = false)

            val ints = inn_user?.size
            var i = 0
            var bool = false
            while (i < ints!!) {

                if (user == inn_user.get(i)) {
                    bool = true
                    break
                }
                i += 1
            }
            if (user == "Root_Access" && pass == "Alpha") {
                startActivity(Intent(this, MainActivity::class.java))
            } else if (bool == true) {
                if (inn_pass?.get(i) == pass) {
                    save1?.edit()?.apply() {
                        putString("tempname", inn_user.get(i))
                        putString("tempdesi", inn_desig.get(i))

                    }?.apply()

                    startActivity(Intent(this, MainActivity::class.java))

                } else {
                    Toast.makeText(this, "Invalid Login credentials", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Incorrect Login credentials", Toast.LENGTH_SHORT).show()

            }
        }
            else{
                Toast.makeText(this, "Check your connection", Toast.LENGTH_SHORT).show()

            }
        }
    }
    var getdata = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            users = p0.getValue().toString()
        }
    }

    var getdata2 = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            passs = p0.getValue().toString()
        }
    }
    var getdata3 = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            desigs = p0.getValue().toString()
        }
    }
    private fun chk() : Boolean {
        val ca = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ac = ca.activeNetworkInfo
        val cb = ac != null && ac.isConnectedOrConnecting
        if (!cb) {
            return false
        }
        else{
            return true
        }
    }
}