package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
lateinit var remarks:String

class pop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.pops)
        val dbremark = FirebaseDatabase.getInstance().getReference("remarks")
        dbremark.addValueEventListener(darts)
        val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)
        val num = save1.getString("numbo","")
        Log.d("tab", num)
        val summ = this

        var tempname = save1.getString("tempname","NULL")
        var temples = ""
        val sdk = SimpleDateFormat("dd/MM/yy hh.mm a")
        val dte = sdk.format(Date())
        if(tempname != "NULL"){

            temples =  tempname +"-" + dte +":"
        }
       //// var user = save1?.getString("users","")//
     ////   var users = user?.split(",",ignoreCase = true)//

        val txt = findViewById<EditText>(R.id.editText4)
        val spin1 = findViewById<Spinner>(R.id.spinner2)
        val spin2 = findViewById<Spinner>(R.id.spinner3)
        val k = findViewById<Button>(R.id.add)
        var twos = "--Select--".split(",",ignoreCase = false).toMutableList()

        val ones = listOf<String>("--Select--","Assign","Remarks on","Poke","Report","Request")
        var myadapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,ones)
        spin1.adapter = myadapter

        var str = save1?.getString("permanent","")
        var strs = str?.split("^")
        var io = 0
        while (io< strs?.size!!){
            if(num == strs.get(io).findLast { true }.toString()){
                var fish = strs.get(io)?.indexOf("[")
                var fishes = strs.get(io).length
                var stir = strs.get(io).dropLast(fishes-fish)

                var stiff = stir.split(",")
                var ip =0
                while (ip<stiff.size){
                    if(stiff.get(ip).trim().length > 0){
                    twos.add(stiff.get(ip))}

                ip++
                }

                var sty = strs.get(io).drop(fish)
                var stys = sty.split("]")
                var iq = 0
                while (iq<stys.size){
                    var ste = stys.get(iq).split(",")
                    var iw = 1
                    while (iw < ste.size){
                        if(ste.get(iw).trim().length > 0){
                        twos.add(ste.get(iw))
                        }
                        iw++
                    }
                    iq++
                }

            }

            io++
        }
        var myadapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_item,twos)
        spin2.adapter = myadapter2
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width : Int = dm.widthPixels
        val height = dm.heightPixels*.8
        window.setLayout(width, height.roundToInt())

        spin1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position==1){
                    txt.setText("Assigning xyz the task ")
                }
                if(position==2){
                    txt.setText("Remarks on xyz regarding ")
                }
                if(position==3){
                    txt.setText("Poking xyz regarding ")
                }
                if(position==4){
                    txt.setText("Reporting to xyz :")
                }
                if(position==5){
                    txt.setText("Requesting ")
                }
            }

        }
        //
        spin2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var spoon = spin2.selectedItem.toString()

                var spl = txt.text.toString().replace("xyz",spoon)
                txt.setText(spl)

            }

        }

        k.setOnClickListener {
            val ca = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ac = ca.activeNetworkInfo
            val cb = ac != null && ac.isConnectedOrConnecting
            if(cb == true){
            if (txt.text.toString().trim().length > 0) {
                var dads = remarks
                var dadds = dads?.split("]", ignoreCase = true)
                // var mom = dads

                var o = 0
                var bool = false
                while (o < dadds!!.size) {
                    var das = dadds.get(o)
                    var dus = das
                    if (das.findLast { true }.toString() == num.toString()) {

                        var mok = dus.dropLast(1)
                        var final = mok + "," + temples + txt.text.toString().replace(",", "")
                            .replace("]", ")").replace("[", "(") + num
                        bool = true
                        var mom = dads?.replace(das, final)

                        dbremark.setValue(mom).addOnSuccessListener {
                            Toast.makeText(summ, "Added to existing", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, details::class.java))
                        }.addOnCanceledListener {
                            Toast.makeText(
                                summ,
                                "Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }
                    o++
                }
                if (bool == false) {
                    dads = dads + "[" + temples + txt.text.toString().replace(",", "")
                        .replace("]", ")").replace("[", "(") + num + "]"
                    dbremark.setValue(dads).addOnSuccessListener {
                        Toast.makeText(summ, "Added ", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, details::class.java))
                    }.addOnCanceledListener {
                        Toast.makeText(summ, "Error", Toast.LENGTH_SHORT).show()
                    }


                }
                //////////// var dads = dad1 + txt.text.toString() + ","
                //    save1?.edit()?.apply(){
                //        putString("remark",dads)
                //   }?.apply()
                //  Toast.makeText(summ,"Added",Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Invalid action", Toast.LENGTH_SHORT).show()
            }
        }
            else{
                Toast.makeText(this,"Check your connection",Toast.LENGTH_SHORT).show()
            }
        }
    }
    var darts = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            remarks = p0.getValue().toString()
            if(remarks == "null"){
                remarks = ""
            }
            Toast.makeText(this@pop,"Updated",Toast.LENGTH_SHORT).show()


        }
    }
}