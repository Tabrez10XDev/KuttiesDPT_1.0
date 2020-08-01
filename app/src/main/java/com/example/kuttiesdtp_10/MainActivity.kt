package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//class MainActivity : AppCompatActivity(){
lateinit var users : String
    lateinit var passs : String
    lateinit var desigs : String
    lateinit var client:String
    lateinit var proj:String
    lateinit var loca : String
    lateinit var descp : String
    lateinit var dclient:String
    lateinit var dproj:String
    lateinit var dloca : String
    lateinit var ddescp : String
    lateinit var reg: String
    lateinit var permanent : String
    var ini = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if(chk()==false){
            Toast.makeText(this,"Check your connection",Toast.LENGTH_SHORT).show()
        }
       // val dbsumma = FirebaseDatabase.getInstance().getReference("Summa")
       // dbsumma.setValue("Summa")
        val dbuser = FirebaseDatabase.getInstance().getReference("loguser")
        val dbpass = FirebaseDatabase.getInstance().getReference("logpass")
        val dbdesig = FirebaseDatabase.getInstance().getReference("logdesig")
        val ddbclient = FirebaseDatabase.getInstance().getReference("dclient")
        val ddbproj = FirebaseDatabase.getInstance().getReference("dproj")
        val ddbloca = FirebaseDatabase.getInstance().getReference("dloca")
        val ddbdescp = FirebaseDatabase.getInstance().getReference("ddescp")
        val dbclient = FirebaseDatabase.getInstance().getReference("client")
        val dbproj = FirebaseDatabase.getInstance().getReference("proj")
        val dbloca = FirebaseDatabase.getInstance().getReference("loca")
        val dbdescp = FirebaseDatabase.getInstance().getReference("descp")
        val dbreg = FirebaseDatabase.getInstance().getReference("reg")
        val dbpermanent = FirebaseDatabase.getInstance().getReference("permanent")
        dbuser.addListenerForSingleValueEvent(getdata)
        dbpass.addListenerForSingleValueEvent(getdata2)
        dbdesig.addListenerForSingleValueEvent(getdata3)

        ddbclient.addListenerForSingleValueEvent(ddata1)
        ddbproj.addListenerForSingleValueEvent(ddata2)
        ddbloca.addListenerForSingleValueEvent(ddata3)
        ddbdescp.addListenerForSingleValueEvent(ddata4)

        dbclient.addListenerForSingleValueEvent(data1)
        dbproj.addListenerForSingleValueEvent(data2)
        dbloca.addListenerForSingleValueEvent(data3)
        dbdescp.addListenerForSingleValueEvent(data4)
        dbreg.addListenerForSingleValueEvent(data5)
        ///////// come here

        dbpermanent.addListenerForSingleValueEvent(datas)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
//


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        val tem = save1.getString("tempname","NULL")
        val dest = save1.getString("tempdesi","NULL")
        var swi = navView.getHeaderView(0)
        var swe = swi.findViewById<TextView>(R.id.swipename)
        var swr = swi.findViewById<TextView>(R.id.swipedesig)
        if(tem != "NULL"){
            swe.setText(tem)
            swr.setText(dest)


        }
        fab.setOnClickListener(){
            val it = Intent(this,addpro::class.java)
            startActivity(it)
        }


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_home -> {

                val inx =  Intent(this, MainActivity::class.java)


                val save1 = getSharedPreferences("database",Context.MODE_PRIVATE)
                save1.edit().apply{
                    putInt("vap",1)
                }.apply()
                Toast.makeText(this, "Ongoing", Toast.LENGTH_SHORT).show()
                startActivity(inx)

            }
            R.id.nav_gallery -> {

                val inx =  Intent(this, MainActivity::class.java)

                val save1 = getSharedPreferences("database",Context.MODE_PRIVATE)
                save1.edit().apply{
                    putInt("vap",2)
                }.apply()
                Toast.makeText(this, "Finished Projects", Toast.LENGTH_SHORT).show()
                startActivity(inx)

            }

            R.id.nav_change->{
                if(users == "null"){
                    users = ""
                    passs = ""
                    desigs = ""
                }
                val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)

                save1.edit().apply {
                    putString("users",users)
                    putString("passs",passs)
                    putString("desigs",desigs)
                }.apply()
                startActivity(Intent(this, trial::class.java))
            }
            R.id.nav_out -> {
                val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)

                save1?.edit()?.apply(){
                    putString("tempname","NULL")
                    putString("tempdesi","NULL")
                    putInt("shift",0)
                }?.apply()
                startActivity(Intent(this, firsts::class.java))
            }
            R.id.nav_it -> {
                val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)
                val desi = save1?.getString("tempdesi","NULL")
                if(desi == "Admin" || desi == "NULL"){
                startActivity(Intent(this, advance::class.java))
            }
                else{
                    Toast.makeText(this,"Unauthorized Access",Toast.LENGTH_SHORT).show()
                }
            }



        }
        return true
    }


    override fun onBackPressed() {
        // super.onBackPressed()
    }



        var getdata = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                users = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }
            }
        }

        var getdata2 = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                passs = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }

            }
        }
        var getdata3 = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                desigs = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }
            }
        }

    var ddata1 = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            dclient = p0.getValue().toString()
            ini++
            if(ini == 13){
                power()
            }
        }
    }

    var ddata2 = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            dproj = p0.getValue().toString()
            ini++
            if(ini == 13){
                power()
            }
        }
    }

    var ddata3 = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            dloca = p0.getValue().toString()
            ini++
            if(ini == 13){
                power()
            }
        }
    }

    var ddata4 = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            ddescp = p0.getValue().toString()
            ini++
            if(ini == 13){
                power()
            }
        }
    }
        var data1 = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                client = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }
            }
        }
        var data2 = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                proj = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }
            }
        }
        var data3 = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                loca = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }
            }
        }
        var data4 = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                descp = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }
            }
        }
        var data5 = object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                reg = p0.getValue().toString()
                ini++
                if(ini == 13){
                    power()
                }

            }
        }
    var datas = object : ValueEventListener {

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            permanent = p0.getValue().toString()
            ini++
            if(ini == 13){
                power()
            }

        }
    }
    private fun power(){
        val save1 = getSharedPreferences("database",Context.MODE_PRIVATE)
        val vap = save1.getInt("vap",1)
                var tems = ""
                var tems2 = ""
                var tems3 = ""
                if(client == "null"){
                    client = ""
                    proj=""
                    loca=""
                    descp=""
                }
                if(dclient == "null"){
                    dclient = ""
                    dproj=""
                    dloca=""
                    ddescp=""
                }
        if(vap ==1) {
            save1.edit().apply(){
                putInt("cod",1)
            }.apply()
                tems = client
               tems2 = proj
                tems3 = loca
        }
        else if(vap ==2){
            save1.edit().apply(){
                putInt("cod",2)
            }.apply()
               tems = dclient
               tems2 = dproj
              tems3 = dloca
        }
        d("tab","chillax")
        if(reg == "null" || reg.trim() ==""){
            d("tab","chillax")

            reg= "0"
            d("tab","$reg")

        }
        recycle.layoutManager= LinearLayoutManager(this@MainActivity)
        recycle.adapter= adap(tems,tems2,tems3)
        if(users == "null"){
            users = ""
            passs = ""
            desigs = ""
        }

        if(permanent=="null")
        {
            permanent = ""
        }
        save1.edit().apply {
            putString("users",users)
            putString("passs",passs)
            putString("desigs",desigs)
            putString("client",client)
            putString("proj",proj)
            putString("loca",loca)
            putString("descp",descp)
            putString("reg",reg)
            putString("dclient",dclient)
            putString("dproj",dproj)
            putString("dloca",dloca)
            putString("ddescp",ddescp)
            putString("permanent",permanent)
        }.apply()
        val inte = Intent(this,MyService::class.java)
        startService(inte)
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







