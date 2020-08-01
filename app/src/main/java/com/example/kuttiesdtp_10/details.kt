package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.detail.*


class details : AppCompatActivity() {
var choose = ""
    var tempname = ""
    var dish = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail)

        if(chk() == false) {
            Toast.makeText(this, "Check your connection", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this,MainActivity::class.java))
        }
        val ddbclient = FirebaseDatabase.getInstance().getReference("dclient")
        val ddbproj = FirebaseDatabase.getInstance().getReference("dproj")
        val ddbloca = FirebaseDatabase.getInstance().getReference("dloca")
        val ddbdescp = FirebaseDatabase.getInstance().getReference("ddescp")
        val dbclient = FirebaseDatabase.getInstance().getReference("client")
        val dbproj = FirebaseDatabase.getInstance().getReference("proj")
        val dbloca = FirebaseDatabase.getInstance().getReference("loca")
        val dbdescp = FirebaseDatabase.getInstance().getReference("descp")

        val dbremarks = FirebaseDatabase.getInstance().getReference("remarks")
        dbremarks.addValueEventListener(darts)
        val save1 =getSharedPreferences("database", Context.MODE_PRIVATE)
         tempname = save1?.getString("tempname","NULL").toString()
        var temp = save1?.getString("client", "")
        val desi = save1?.getString("tempname","NULL")
        val teeth = save1?.getString("tempdesi","NULL")
        var temp2 = save1?.getString("proj", "")
        var temp3 = save1?.getString("loca","")
        var temp4 = save1?.getString("descp","")

        var tems1 = temp?.split(",",ignoreCase = false)?.toMutableList()
        var tems2 = temp2?.split(",",ignoreCase = false)?.toMutableList()
        var tems3 = temp3?.split(",",ignoreCase = false)?.toMutableList()
        var tems4 = temp4?.split(",",ignoreCase = false)?.toMutableList()

        next2.text = "Finish Project"

        val pos =save1.getInt("posi",999)

        var ded1 = save1?.getString("dclient","")
        var ded2 = save1?.getString("dproj","")

        var ded3 = save1?.getString("dloca","")
        var ded4 = save1?.getString("ddescp"," ")
        val inp = Intent(this,MainActivity::class.java)
        var fine= ""
//        var dads = save1?.getString("remark","")
//        var dadds = dads?.split("]",ignoreCase = true)
        var opo = 0
        val cod = save1.getInt("cod",1)

        var o = 0
        if(pos != 999) {
            val vit = Intent(this,MainActivity::class.java)

            val eb = this
            if(cod ==1) {
                vit.putExtra("vap",1)

                val chuck1 = tems1?.get(pos)?.replace("[","")?.replace("]","")
                val chuck3 = tems3?.get(pos)?.replace("[","")?.replace("]","")
                val chuck4 = tems4?.get(pos)?.replace("[","")?.replace("]","")
                val chuck2  = tems2?.get(pos)?.replace("[","")?.replace("]","")
                choose = chuck2?.findLast { true }.toString()
                var suma = chuck2?.findLast { true }?.toString()
                save1.edit().apply(){
                    putString("numbo",suma)
                }.apply()

                det_1.setText(chuck1)
                det_2.setText(chuck2?.dropLast(1))
                det_3.setText(chuck3)
                det_4.setText(chuck4)
//
                val perm = save1?.getString("permanent","")
                val permish = perm?.split("^")
                var qw = 0
                var rrr = ""
                if(perm != "") {
                    while (qw < permish!!.size) {

                        val pist = permish.get(qw).findLast { true }.toString()
                        if(pist == suma){

                            val fist = permish.get(qw).indexOf("[")
                            val ip = permish.get(qw).length - fist
                            rrr = permish.get(qw)



                        }
                        qw++
                    }

                }
                if(teeth != "Admin" && teeth != "NULL"){
                    if(rrr.contains(desi.toString())){

                    }
                    else{
                        startActivity(vit)
                        Toast.makeText(this,"You are not part of the project", Toast.LENGTH_SHORT).show()
                    }
                }



                next2.setOnClickListener() {
                    if (chk() == true){
                        if (teeth == "Admin" || teeth == "NULL") {
                            if (o < 3) {
                                o += 1
                                Toast.makeText(
                                    this,
                                    "Click once more to Wrap up",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (o >= 3) {
                                val one = ded1 + chuck1 + ","
                                val two = ded2 + chuck2 + ","
                                val three = ded3 + chuck3 + ","
                                val four = ded4 + chuck4 + ","

                                tems1?.removeAt(pos)
                                tems2?.removeAt(pos)
                                tems3?.removeAt(pos)
                                tems4?.removeAt(pos)
                                ddbclient.setValue(one)
                                ddbproj.setValue(two)
                                ddbloca.setValue(three)
                                ddbdescp.setValue(four)

                                Toast.makeText(
                                    eb,
                                    "Finished Project successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                dbclient.setValue(
                                    tems1.toString().replace("[", "").replace("]", "").trim()
                                )
                                dbproj.setValue(
                                    tems2.toString().replace("[", "").replace("]", "").trim()
                                )
                                dbloca.setValue(
                                    tems3.toString().replace("[", "").replace("]", "").trim()
                                )
                                dbdescp.setValue(
                                    tems4.toString().replace("[", "").replace("]", "").trim()
                                )


                                //  inp.putExtra("numm",suma)
                                startActivity(inp)

                            }
                        } else {
                            Toast.makeText(this, "Authorized personnel only", Toast.LENGTH_SHORT)
                                .show()
                        }
                }
                    else{
                        Toast.makeText(this,"Check your connection",Toast.LENGTH_SHORT).show()
                    }
                }
                rem.setOnClickListener {
                    startActivity(Intent(this, pop::class.java))
                }
                team.setOnClickListener {
                    if(chk()==true){
                    startActivity(Intent(this, acc::class.java))
                }
                    else{
                        Toast.makeText(this,"Check your connection",Toast.LENGTH_SHORT).show()

                    }
                }
            }
            else if(cod ==2) {
                vit.putExtra("vap",2)
                //tobecontinuedtuesd
                var dtwo = ded2?.split(",", ignoreCase = false)?.toMutableList()
                var dtw = dtwo?.get(pos)
                choose = dtw?.findLast { true }.toString()
//                while (opo < dadds!!.size) {
//                    var das = dadds.get(opo).replace("[", "").replace("]", "")
//                    //  var dus = das
//                    if (das.findLast { true }.toString() == dtw?.findLast { true }.toString()) {
//
//                        var mok = das.dropLast(1)
//                        fine = mok.replace(",", "\n")
//                    }
//                    opo++
//                }
                next2.text = "Delete"
                var done = ded1?.split(",", ignoreCase = false)?.toMutableList()
                var dthree = ded3?.split(",", ignoreCase = false)?.toMutableList()
                var dfour = ded4?.split(",", ignoreCase = false)?.toMutableList()
                var don = done?.get(pos)
                var dth = dthree?.get(pos)
                var dfo = dfour?.get(pos)
                det_1.setText(don)
                det_2.setText(dtw?.dropLast(1))
                det_3.setText(dth)
                det_4.setText(dfo)
              //  chat.text = fine
                var suma = dtw?.findLast { true }?.toString()
                save1.edit().apply() {
                    putString("numbo", suma)
                }.apply()
                val perm = save1?.getString("permanent", "")
                val permish = perm?.split("^")
                var qw = 0
                var rrr = ""
                if (perm != "") {
                    while (qw < permish!!.size) {

                        val pist = permish.get(qw).findLast { true }.toString()
                        if (pist == suma) {

                            val fist = permish.get(qw).indexOf("[")
                            val ip = permish.get(qw).length - fist
                            rrr = permish.get(qw)


                        }
                        qw++
                    }

                }
                if (teeth != "Admin" && teeth != "NULL") {
                    if (rrr.contains(desi.toString())) {

                    } else {
                        startActivity(vit)
                        Toast.makeText(this, "You are not part of the project", Toast.LENGTH_SHORT).show()
                    }
                }
//////////here

                team.setOnClickListener {
                    if(chk()==true){
                    startActivity(Intent(this, acc::class.java))
                }
                    else{
                        Toast.makeText(this,"Check your connection",Toast.LENGTH_SHORT).show()

                    }
                }
                next2.setOnClickListener() {

                    if(chk() == true){
                    if (teeth == "Admin" || teeth == "NULL") {
                        if (o < 3) {
                            o += 1
                            Toast.makeText(this, "Click once more to Delete", Toast.LENGTH_SHORT)
                                .show()
                        } else if (o >= 3) {
                            var dishs = dish.split("]")
                            var q = 0
                            while(q<dishs.size){
                                if(dishs.get(q).findLast { true }.toString() == choose){
                                    var dum = dish.replace(dishs.get(q)+"]","")
                                    Toast.makeText(this,dum,Toast.LENGTH_SHORT).show()

                                    dbremarks.setValue(dum)
                                }
                                q++
                            }

                            done?.removeAt(pos)
                            dtwo?.removeAt(pos)
                            dthree?.removeAt(pos)
                            dfour?.removeAt(pos)
                            don = done.toString().replace("[", "").replace("]", "").trim()
                            dtw = dtwo.toString().replace("[", "").replace("]", "").trim()
                            dth = dthree.toString().replace("[", "").replace("]", "").trim()
                            dfo = dfour.toString().replace("[", "").replace("]", "").trim()


                            ddbclient.setValue(don)
                            ddbproj.setValue(dtw)
                            ddbloca.setValue(dth)
                            ddbdescp.setValue(dfo)

                            startActivity(inp)
                        }
                    } else {
                        Toast.makeText(eb, "Authorized personnel only", Toast.LENGTH_SHORT).show()
                    }
                }
                    else{
                        Toast.makeText(this,"Check your connection",Toast.LENGTH_SHORT).show()

                    }
                }


            }
        }
        else{
            Toast.makeText(this,"Exceeding Capacity", Toast.LENGTH_SHORT).show()
        }



    }
    var darts = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                var dads = p0.getValue().toString()
                dish = dads
                if(dads=="null"){
                    dads=""
                }
             //   Toast.makeText(this@details,"Updated",Toast.LENGTH_SHORT).show()
                var opo = 0
                var fine = ""
              //  var dads = save1?.getString("remark","")
                var dadds = dads?.split("]",ignoreCase = true)
                while(opo< dadds!!.size){
                    var das = dadds.get(opo).replace("[","").replace("]","")

                    if(das.findLast { true }.toString() == choose ){

                        var mok=  das.dropLast(1)
                        fine = mok.replace(",","\n")
                    }
                    opo++
                }
                chat.setText(fine)

                var ulta = SpannableStringBuilder()
                var och = chat.text.toString().split("\n")
                var oppu= 0
                if(chat.text.toString().trim().length > 0 && chat.text.toString() != "[]") {
                    while(oppu < och.size){

                        if(och.get(oppu).contains("Assigning")){



                            var simp1 = SpannableString(och.get(oppu) + "\n")
                            val nus = och.get(oppu).indexOf("Assigning")
                            val num = och.get(oppu).indexOf(":")
                            val it = och.get(oppu).drop(num)
                            if(it.contains(tempname.toString(),ignoreCase = true)){
                                simp1.setSpan(
                                    BackgroundColorSpan(Color.LTGRAY),num+1,och.get(oppu).length ,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

                            }

                            simp1.setSpan(ForegroundColorSpan(Color.BLUE),nus,nus+9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            ulta.append(simp1)
                        }
                        else if (och.get(oppu).contains("Remarks")){

                            var simp2 = SpannableString(och.get(oppu) + "\n")
                            val nus2 = och.get(oppu).indexOf("Remarks")
                            val num = och.get(oppu).indexOf(":")
                            val it = och.get(oppu).drop(num)
                            if(it.contains(tempname.toString(),ignoreCase = true)){
                                val num = och.get(oppu).indexOf(":")
                                simp2.setSpan(
                                    BackgroundColorSpan(Color.LTGRAY),num+1,och.get(oppu).length ,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

                            }
                            simp2.setSpan(ForegroundColorSpan(Color.RED),nus2,nus2+10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            ulta.append(simp2)
                        }
                        else if(och.get(oppu).contains("Poking")){
                            var simp3 = SpannableString(och.get(oppu) + "\n")
                            val nus3 = och.get(oppu).indexOf("Poking")
                            val num = och.get(oppu).indexOf(":")
                            val it = och.get(oppu).drop(num)
                            if(it.contains(tempname.toString(),ignoreCase = true)){
                                val num = och.get(oppu).indexOf(":")
                                simp3.setSpan(
                                    BackgroundColorSpan(Color.LTGRAY),num+1,och.get(oppu).length ,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

                            }
                            simp3.setSpan(ForegroundColorSpan(Color.CYAN),nus3,nus3+6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            ulta.append(simp3)
                        }
                        else if(och.get(oppu).contains("Reporting")) {
                            var simp4 = SpannableString(och.get(oppu) + "\n")
                            val nus4 = och.get(oppu).indexOf("Reporting")
                            val num = och.get(oppu).indexOf(":")
                            val it = och.get(oppu).drop(num)
                            if(it.contains(tempname.toString(),ignoreCase = true)){
                                val num = och.get(oppu).indexOf(":")
                                simp4.setSpan(
                                    BackgroundColorSpan(Color.LTGRAY),num+1,och.get(oppu).length ,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

                            }
                            simp4.setSpan(ForegroundColorSpan(Color.GREEN), nus4, nus4+7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            ulta.append(simp4)
                        }
                        else if(och.get(oppu).contains("Requesting")) {
                            var simp5 = SpannableString(och.get(oppu) + "\n")
                            val nus5 = och.get(oppu).indexOf("Requesting")
                            val num = och.get(oppu).indexOf(":")
                            val it = och.get(oppu).drop(num)
                            if(it.contains(tempname.toString(),ignoreCase = true)){
                                val num = och.get(oppu).indexOf(":")
                                simp5.setSpan(
                                    BackgroundColorSpan(Color.LTGRAY),num+1,och.get(oppu).length ,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

                            }
                            simp5.setSpan(ForegroundColorSpan(Color.MAGENTA), nus5, nus5+10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            ulta.append(simp5)
                        }
                        else{
                            var simp5 = SpannableString(och.get(oppu))
                            val num = och.get(oppu).indexOf(":")
                            d("tab","${och.get(oppu)}")
                            val it = och.get(oppu).drop(num)
                            if(it.contains(tempname.toString(),ignoreCase = true)){
                                simp5.setSpan(
                                    BackgroundColorSpan(Color.LTGRAY),num+1,och.get(oppu).length ,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

                            }
                            simp5.setSpan(ForegroundColorSpan(Color.BLACK),num+1,och.get(oppu).length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            ulta.append(simp5)
                        }
                        oppu++
                    }
                    chat.text = ulta
                }
            }
        }

    override fun onBackPressed() {
        // super.onBackPressed()
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


