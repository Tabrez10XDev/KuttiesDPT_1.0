package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.access.*
import kotlin.text.clear


class acc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.access)
        val dbpermanent = FirebaseDatabase.getInstance().getReference("permanent")

        teamer.isEnabled = false
        staffer.isEnabled = false
        var once = false
        val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)
        val desi = save1?.getString("tempdesi","NULL")
        val tempname = save1?.getString("tempname","NULL")

        var fulltemp = save1.getString("fulltemp","")
        if(fulltemp != ""){
            mana.text = fulltemp?.replace(",","\n")
        }
        val numbo = save1?.getString("numbo","")
        val perm = save1?.getString("permanent","")
        val permish = perm?.split("^")
        var qw = 0
        var rrr = ""
        var wish = ""
        var with = ""
        var ift = ""
        if(perm != "") {
            while (qw < permish!!.size) {

                val pist = permish.get(qw).findLast { true }.toString()
                if(pist == numbo){
                    wish = permish.get(qw)
                    val vot = permish.get(qw).indexOf("[")
                    with = permish.get(qw).dropLast(permish.get(qw).length - vot)
                    clear.isEnabled = false
                    clear2.isEnabled = true
                    clear2.text = "Replace"
                    spins3.isEnabled = true

                    addition.isEnabled = false
                    saver.isEnabled = false

                    once = true
                    val fist = permish.get(qw).indexOf("[")
                    val ip = permish.get(qw).length - fist
                    rrr = permish.get(qw)
                    mana.text = permish.get(qw).dropLast(ip).replace(",","\n")


                    spins1.isEnabled = false
                    spins3.isEnabled = false

                }
                qw++
            }

            val eti = rrr.indexOf("]")
        }


        var temp = save1?.getString("users","")
        var tempteam = save1?.getString("tempteam","")
        var teamu = tempteam?.split("]")
        var testu = ""
        var i =0
        if(tempteam != ""){
            while(i< teamu!!.size - 1){
                var sum1 = teamu.get(i).replace("[","").replace("]","")
                var sum2 = sum1.split(",")
                testu += sum2.get(0) + ","

                i++
            }

        }
        var one ="--Select--" +"," + temp
        var ones = one?.split(",",ignoreCase = false)
        ones= ones.dropLast(1)
        if(once == false) {
            var two ="--Add team--" +"," +testu
            var twos = two?.split(",", ignoreCase = false)
            var myadapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_item,twos)
            spins2.adapter = myadapter2
        }
        else{
            val fist = rrr.indexOf("[")


            var two = ""
            var fuse = rrr.drop(fist).replace("[","")
            var fusion = fuse.split("]").dropLast(1)

            var kis = 0

            while(kis < fusion.size){
                val puss = fusion.get(kis).split(",")
                val pussy = puss.get(0) + ","
                two +=pussy
                kis++
            }
            var twos = two?.split(",", ignoreCase = false)
            twos = twos.dropLast(1)
            var myadapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_item,twos)
            spins2.adapter = myadapter2
        }
        var myadapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, ones)

        spins1.adapter = myadapter1

        // var threes =  "--,--,--".split(",").toMutableList()
        var myadapter3 = ArrayAdapter(this,android.R.layout.simple_spinner_item,ones)
        spins3.adapter = myadapter3
        spins1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position !=0){
                    var spish = mana.text.toString()
                    var spoon = spins1.selectedItem.toString()
                    mana.text = spish + "\n" + spoon

                }
            }

        }
        clear.setOnClickListener {
            mana.text = ""
        }
        spins2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(once == false){
                    if(position==0){
                        teamer.isEnabled = true
                        staffer.isEnabled = true

                    }
                }
                else {


                    var sum = spins2.selectedItem.toString()
                    if(sum != ""){
                        teamer.isEnabled = true
                        staffer.isEnabled = true
                        teamer.setText(sum)
                        var mudivu = ""
                        val st = rrr.indexOf("[")
                        var sss = rrr.drop(st).replace("[","").split("]")
                        var ot = 0
                        while(ot <sss.size){
                            var stut = sss.get(ot).split(",")
                            if(stut.get(0)==sum){
                                var os = 1
                                while (os < stut.size){
                                    mudivu += stut.get(os) + ","
                                    os++
                                }
                                mudivu.dropLast(1)
                                val mun = mudivu.indexOf(",")
                                mudivu = mudivu.replace(",","\n")
                                var mudivus = SpannableString(mudivu)
                                mudivus.setSpan(StyleSpan(Typeface.BOLD_ITALIC),0,mun, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                                staffer.text = mudivus
                            }
                            ot++
                        }


                    }
                    else{
                        teamer.setText("")
                        staffer.text = ""
                    }

                }
            }

        }
        spins3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0){
                    var spish = staffer.text.toString()
                    var spoon = spins3.selectedItem.toString()
                    staffer.text = spish + "\n" + spoon            }}

        }
        clear2.setOnClickListener {

            if(clear2.text == "Replace" || clear2.text== "REPLACE"){
                if(desi == "Admin" || desi == "NULL"){
                saver.isEnabled = true
                spins3.isEnabled = true
                staffer.isEnabled = true
                ift = staffer.text.toString().replace("\n", ",")
                staffer.text = ""

            }
                else if(with.contains(tempname.toString())){
                    saver.isEnabled = true
                    spins3.isEnabled = true
                    staffer.isEnabled = true
                    ift = staffer.text.toString().replace("\n", ",")
                    staffer.text = ""

                }
            }
            else{
                staffer.text = ""

            }


        }
        addition.setOnClickListener {
            if(mana.text.toString() != "") {
                save1.edit().apply() {

                    putString("fulltemp",mana.text.toString().replace("\n",","))
                }.apply()
            }
            if(tempteam==""){
                var end =  "[" + teamer.text.toString().replace(","," ").replace("[","(").replace("]",")")   + staffer.text.toString().replace("\n",",")                + "]"

                save1?.edit()?.apply {
                    putString("tempteam",end)
                }?.apply()
                startActivity(Intent(this,acc::class.java))
            }
            else{
                var end = tempteam + "[" + teamer.text.toString().replace(","," ").replace("[","(").replace("]",")") + staffer.text.toString().replace("\n",",") + "]"
                save1?.edit()?.apply {
                    putString("tempteam",end)
                }?.apply()
                startActivity(Intent(this,acc::class.java))
            }
        }
        saver.setOnClickListener {
      if(clear2.text == "clear" || clear2.text == "CLEAR"){
            var silk = save1.getString("tempteam","")

            if(silk != "" && mana.text.toString() != "") {
                var teamish = perm + mana.text.toString().replace("\n", ",") + save1?.getString(
                    "tempteam",
                    ""
                ) + numbo + "^"
                if (teamish != "") {
                    save1.edit().apply {
                        putString("tempteam", "")
                        putString("fulltemp", "")
                    }.apply()

                    dbpermanent.setValue(teamish)
                } else {
                    Toast.makeText(this, "Empty value", Toast.LENGTH_SHORT).show()
                }

                startActivity(Intent(this, MainActivity::class.java))
            }
            else{
                Toast.makeText(this,"Empty fields",Toast.LENGTH_SHORT).show()
            }
        }
            else {
//////////////////////////////////////// continue  here
          if (ift.findLast { true }.toString() == ",") {
              ift = ift.dropLast(1)
          }
          var wosh = wish.replace("," + ift, staffer.text.toString().replace("\n", ","))

          d("tab", "ift")
          d("tab", "$ift")
          d("tab", "staffer")
          d("tab", "${staffer.text.toString()}")
          d("tab", "$wosh")

          var shit = perm.replace(wish, wosh)
          dbpermanent.setValue(shit)
          startActivity(Intent(this, MainActivity::class.java))

      }
        }
        }

}