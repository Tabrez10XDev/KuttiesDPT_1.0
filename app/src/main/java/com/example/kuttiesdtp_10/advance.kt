package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.advances.*
import java.io.File
import java.util.*

class advance : AppCompatActivity() {
    lateinit var rem : String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    setContentView(R.layout.advances)
        lateinit var frm : String
        lateinit var to : String
        val save1 = getSharedPreferences("database",Context.MODE_PRIVATE)
        val dbrem = FirebaseDatabase.getInstance().getReference("remarks")
        dbrem.addListenerForSingleValueEvent(data)

        var shift = save1?.getInt("shift",0)
        if(shift ==0){
        switch1.isChecked = false
    }
        else{
            switch1.isChecked = true
        }
        switch1.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                save1.edit().apply{
                    putInt("shift",1)
                }.apply()
                Toast.makeText(this,"New Notification settings",Toast.LENGTH_SHORT).show()
            }
            if(!isChecked){
                save1.edit().apply{
                    putInt("shift",0)
                }.apply()
                Toast.makeText(this,"New Notification settings",Toast.LENGTH_SHORT).show()
            }

        }
        req.setOnClickListener(){
            //yet to be done
            var myfish = "/Statements"
            frm = from.text.toString().format(Date())
            to = too.text.toString().format(Date())
            rem = rem.replace("[","")
            val save1 = getSharedPreferences("database",Context.MODE_PRIVATE)
            var proj = save1.getString("proj","")
            var projs = proj?.split(",")
            var client = save1.getString("client","")
            var clients = client?.split(",")
            var descp = save1.getString("descp","")
            var descps = descp?.split(",")
            var dclient = save1.getString("dclient","")
            var dclients = dclient?.split(",")
            var ddescp = save1.getString("ddescp","")
            var ddescps = ddescp?.split(",")
            var dproj = save1.getString("dproj","")
            var dprojs = dproj?.split(",")

            val shif = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            var fileName =shif.toString() + myfish + ".txt"
            var myfile = File(fileName)
            fun sun(){
                if(myfile.exists()){

                        myfish = myfish + "(1)"
                    fileName =shif.toString() + myfish + ".txt"
                    myfile = File(fileName)
                    if(myfile.exists()){
                        myfish = myfish.replace("1","2")
                        fileName =shif.toString() + myfish + ".txt"
                        myfile = File(fileName)
                        if (myfile.exists()){
                            myfish = myfish.replace("2","3")

                            fileName =shif.toString() + myfish + ".txt"
                            myfile = File(fileName)
                        }
                    }
                 }
                 fileName =shif.toString() + myfish + ".txt"
                myfile = File(fileName)
            }
            sun()

            myfile.createNewFile()
            var rems = rem.split("]")

            var i =0
            var wish = ""

            while(i<rems.size){
                var po = false
var last = rems.get(i).findLast { true }.toString()
                var j =0

                var l = 0
                while (j<projs!!.size){
                    d("tab","$last   rubbbus, ${projs.get(j)}")
                    if(projs.get(j).findLast { true }.toString() == last){
                        d("lj","ljey")
                        wish = wish.plus("Client Name:").plus(clients?.get(j)) + "\n"
                        wish =   wish.plus("Project Name:").plus(projs.get(j).dropLast(1)) + "\n"
                        wish = wish + "Description:" + descps?.get(j) + "\n"
                        po= true

                    }

                    j++
                }
                if(po==false){
                    while(l<dprojs!!.size){
                        if(dprojs?.get(l)?.findLast { true }.toString() == last){
                            println("pushuuu")

                            wish = wish.plus("Client Name:").plus(dclients?.get(l)) + "\n"
                            wish =   wish.plus("Project Name:").plus(dprojs?.get(l)?.dropLast(1)) + "\n"
                            wish = wish + "Description:" + ddescps?.get(l) + "\n"

                        }
                        l++
                    }
                }

                var rim = rems.get(i).split(",")
                var k = 0

                while (k<rim.size){
                    var fin = rim.get(k).indexOf("-")
                    var far = rim.get(k).drop(fin+1)
if(far.trim().length > 0) {
    far = far.dropLast(far.length - 8).format(Date())
    var one = after(far, frm)
    var two = after(far, to)
    if (one == far) {
        d("tabb", "dushhhh")

        if (two == to) {
            wish = wish.plus(rim.get(k).dropLast(1)) + "\n"
            d("tabb", "dupeee")

        }
    }
}
                    k++
                }

                i++
            }

            myfile.printWriter().use { out ->
                out.println(wish)

            }
            var shut = after(frm,to)
            if(shut == to){
            Toast.makeText(this,"File Created",Toast.LENGTH_SHORT).show()
        }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
    }

    var data = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            rem = p0.getValue().toString()


        }
    }

    private fun after(one : String, two : String): String {
        var ones = one.split("/")
        var twos = two.split("/")
        if(ones[2].toInt() > twos[2].toInt()){
            return one
        }
        else if(ones[2].toInt() == twos[2].toInt()){
            if(ones[1].toInt() > twos[1].toInt()) {
                return one
            }
            else if(ones[1].toInt() == twos[1].toInt()){
                if(ones[0].toInt() > twos[0].toInt() ){
                    return one
                }
                else if(ones[0].toInt() == twos[0].toInt()){
                    return "null"
                }
                else return two
            }
            else  return two
        }
        else return two
    }

}