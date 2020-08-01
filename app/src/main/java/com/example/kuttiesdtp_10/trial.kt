package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.trials.*


class trial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.trials)
        val dbpass = FirebaseDatabase.getInstance().getReference("logpass")

        val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)
        val temp = save1.getString("tempname","NULL")
        if(temp == "NULL") {


            change_new.isEnabled = false
            change_new2.isEnabled = false
            change_old.hint = "Enter username to reset"
            change.text = " Reset"
            val check = change_old.text.toString()
            change.setOnClickListener() {

                Log.d("tab", "makka")
                var in_user = save1.getString("users", "")
                var in_pass = save1.getString("passs", "")
                val inn_user = in_user?.split(",", ignoreCase = false)
                var inn_pass = in_pass?.split(",", ignoreCase = false)?.toMutableList()

                val ints = inn_user?.size
                var i = 0
                while (i < ints!!) {

                    if (change_old.text.toString() == inn_user.get(i)) {

                        break
                    }
                    i += 1
                }
                val check = change_old.text.toString()
                if (check == inn_user?.get(i)) {

                    inn_pass!![i] = "password"

                    in_pass = inn_pass.toString().replace("[", "").replace("]", "")

                    dbpass.setValue(in_pass)
                    Toast.makeText(this,"Password changed to 'password'", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))



                }
            }
        }
        else {


            change.setOnClickListener() {

                val save1 = getSharedPreferences("database", Context.MODE_PRIVATE)
                val temp = save1.getString("tempname", "NULL")
                var in_user = save1.getString("users", "")
                var in_pass = save1.getString("passs", "")
                val inn_user = in_user?.split(",", ignoreCase = false)
                var inn_pass = in_pass?.split(",", ignoreCase = false)?.toMutableList()

                val ints = inn_user?.size
                var i = 0
                while (i < ints!!) {

                    if (temp == inn_user.get(i)) {

                        break
                    }
                    i += 1
                }
                val pass = change_old.text.toString()
                if (pass == inn_pass?.get(i)) {
                    if (change_new.text.toString() == change_new2.text.toString()) {

                        inn_pass[i] = change_new.text.toString()

                        in_pass = inn_pass.toString().replace("[", "").replace("]", "")

                        dbpass.setValue(in_pass)

                        startActivity(Intent(this, MainActivity::class.java))
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Check your info", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Enter correct password", Toast.LENGTH_SHORT).show()

                }

            }

        }
    }
}