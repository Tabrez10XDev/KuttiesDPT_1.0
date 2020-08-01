package com.example.kuttiesdtp_10

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_frag2.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Frag2.newInstance] factory method to
 * create an instance of this fragment.
 */

class Frag2 : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbuser = FirebaseDatabase.getInstance().getReference("loguser")
        val dbpass = FirebaseDatabase.getInstance().getReference("logpass")
        val dbdesig = FirebaseDatabase.getInstance().getReference("logdesig")
        val save1 = this.context?.getSharedPreferences("database", Context.MODE_PRIVATE)

       // var temp = save1?.getString("log_user", "")

        var users = save1?.getString("users","")
        var passs = save1?.getString("passs","")
        var desigs = save1?.getString("desigs","")


        var temps = users?.split(",",ignoreCase = false)?.toMutableList()
      //  var temp2 = save1?.getString("log_pass", "")
        var temps2 = passs?.split(",",ignoreCase = false)?.toMutableList()
     //   var temp3 = save1?.getString("log_desig","")
        var temps3 = desigs?.split(",",ignoreCase = false)?.toMutableList()


        var one ="--Add Member--" +"," + users
        var ones = one?.split(",",ignoreCase = false).toMutableList()
        val desi = save1?.getString("tempdesi","NULL")
        var myadapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_spinner_item,ones)
        spinner.adapter = myadapter
        val inen = Intent(this.context, MainActivity::class.java)

        val nen = this.context


        

                


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var point = save1?.getString("reg","999")?.toInt()!! + 1
                val ca = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val ac = ca.activeNetworkInfo
                val cb = ac != null && ac.isConnectedOrConnecting
                if(desi == "Admin" || desi =="NULL") {
                    if (position == 0) {
                        addaal.setOnClickListener() {

                            if(cb == true){
                            if (addnam.text.toString().trim().length > 0 && addpas.text.toString()
                                    .trim().length > 0
                            ) {

                                if (adddes.text.toString().trim().length > 0) {

                                    var edit1 =
                                        users + addnam.text.toString().replace(",", "").trim() + ","
                                    var edit2 =
                                        passs + addpas.text.toString().replace(",", "").trim() + ","
                                    var edit3 = desigs + adddes.text.toString().replace(",", "")
                                        .trim() + ","


                                    dbuser.setValue(edit1.trim())
                                    dbpass.setValue(edit2.trim())
                                    dbdesig.setValue(edit3.trim())


                                    startActivity(inen)
                                    Toast.makeText(nen, "Success", Toast.LENGTH_SHORT).show()


                                } else {
                                    Toast.makeText(
                                        nen,
                                        "Fill in the designation",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()

                                }
                            } else {
                                Toast.makeText(nen, "Fill in", Toast.LENGTH_SHORT).show()
                            }

                        }
                            else{
                                Toast.makeText(activity,"Check your connection",Toast.LENGTH_SHORT).show()

                            }
                        }


                    } else {
                        addnam.isEnabled = false
                        addpas.isEnabled = false
                        adddes.isEnabled = false

                        addaal.text = "Delete"
                        addaal.setOnClickListener() {
                            val it = position - 1
                            if (desi != temps3?.get(it)) {
                                var cheese1 = temps?.get(it) + ","
                                var chuck = temps?.toString()
                                temps2?.removeAt(it)
                                var chuck1 = temps2?.toString()
                                temps3?.removeAt(it)
                                var chuck2 = temps3?.toString()
                                var rep1 =
                                    chuck?.replace("[", "")?.replace("]", "")?.replace(cheese1, "")
                                        ?.replace(" ", "")

                                var rep2 =
                                    chuck1?.replace("[", "")?.replace("]", "")?.replace(" ", "")

                                var rep3 =
                                    chuck2?.replace("[", "")?.replace("]", "")?.replace(" ", "")
                                dbuser.setValue(rep1)
                                dbpass.setValue(rep2)
                                dbdesig.setValue(rep3)

                                Toast.makeText(nen, "Deleted", Toast.LENGTH_LONG).show()
                                startActivity(inen)


                            }
                            else{
                                Toast.makeText(nen,"Invalid Authority", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(nen,"Authorized entry only", Toast.LENGTH_SHORT).show()
                    addaal.isEnabled = false
                }
            }
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Frag2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Frag2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}
