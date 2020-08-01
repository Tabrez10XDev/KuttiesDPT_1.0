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
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_frag1.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Frag1.newInstance] factory method to
 * create an instance of this fragment.
 */

class Frag1 : Fragment() {

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
        val i =inflater.inflate(R.layout.fragment_frag1, container, false)

        return i
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbclient = FirebaseDatabase.getInstance().getReference("client")
        val dbproj = FirebaseDatabase.getInstance().getReference("proj")
        val dbloca = FirebaseDatabase.getInstance().getReference("loca")
        val dbdescp = FirebaseDatabase.getInstance().getReference("descp")
        val dbreg = FirebaseDatabase.getInstance().getReference("reg")
        val save1 = this.context?.getSharedPreferences("database", Context.MODE_PRIVATE)
        val desi = save1?.getString("tempdesi", "NULL")

        var point = save1?.getString("reg","999")?.toInt()!! + 1
            val ca = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ac = ca.activeNetworkInfo
            val cb = ac != null && ac.isConnectedOrConnecting


        if (desi == "Admin" || desi == "NULL")
        {

            next.setOnClickListener() {
                if (cb == true){
                    if (editText3.text.toString().trim().length > 0 && editText2.text.toString()
                            .trim().length > 0
                    ) {
                        if (editText6.text.toString().trim().length > 0 && editText.text.toString()
                                .trim().length > 0
                        ) {


                            val save1 =
                                this.context?.getSharedPreferences("database", Context.MODE_PRIVATE)
                            var temp = save1?.getString("client", "")
                            var temp2 = save1?.getString("proj", "")
                            var temp3 = save1?.getString("loca", "")
                            var temp4 = save1?.getString("descp", "")


                            var edit1 = temp + editText3.text.toString().replace(",", " ") + ","
                            var edit2 = temp2 + editText2.text.toString().replace(",", " ")
                                .replace("[", "(").replace("]", ")") + point + ","
                            var edit3 = temp3 + editText6.text.toString().replace(",", "|") + ","
                            var edit4 = temp4 + editText.text.toString().replace(",", " ") + ","


                            dbclient.setValue(edit1)
                            dbproj.setValue(edit2)
                            dbloca.setValue(edit3)
                            dbdescp.setValue(edit4)
                            dbreg.setValue(point.toString())

                            startActivity(Intent(activity, MainActivity::class.java))

                        } else {
                            Toast.makeText(activity, "Fill in", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(activity, "Fill in", Toast.LENGTH_SHORT).show()


                    }
            }
                else{
                    Toast.makeText(activity,"Check your connection",Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(activity,"Authorized entry only", Toast.LENGTH_SHORT).show()
            next.isEnabled = false
        }
//        next3.setOnClickListener {
//            startActivity(Intent(activity, acc::class.java))
//        }


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Frag1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Frag1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}

