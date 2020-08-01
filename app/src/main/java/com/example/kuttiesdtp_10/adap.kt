package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.mem.view.*

class adap(val stert : String, val stert2 : String, val stert3 : String) : RecyclerView.Adapter<adap.customview>() {



    override fun getItemCount(): Int {
        val r= stert.split(",",ignoreCase = true)
        return  (r.size - 1)
        //  return 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customview {

        val infa = LayoutInflater.from(parent?.context)
        val cell= infa.inflate(R.layout.mem, parent, false)
        return customview(cell)
    }


    override fun onBindViewHolder(holder: customview, position: Int) {


        var i = stert.split(",",ignoreCase = true)
        var o = i.get(position)
        var i2 = stert2.split(",",ignoreCase = true)
        var o2 = i2.get(position).dropLast(1)
        var i3 = stert3.split(",",ignoreCase = true)
        var o3 = i3.get(position)



        holder?.v?.textView2?.text = o
        holder?.v?.textView3?.text = o2
        holder?.v?.textView4?.text = o3

    }


    //
    // holder?.v?.textView2?.text = "hrloo,hrllle,asfd"




    class customview(val v: View):RecyclerView.ViewHolder(v){
        init {
            v.setOnClickListener{
                val poss = adapterPosition
                // val poss = 555
                val intents = Intent(v.context,details::class.java)

                val save1 = v.context.getSharedPreferences("database", Context.MODE_PRIVATE)
                save1.edit().apply {
                    putInt("posi",poss)
                }.apply()
                v.context.startActivity(intents)

            }
        }


    }

}

