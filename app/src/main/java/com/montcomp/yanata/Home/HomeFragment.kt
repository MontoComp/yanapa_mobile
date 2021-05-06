package com.montcomp.yanata.Home


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.montcomp.yanata.R
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment() : Fragment(){

    var ctx: Context ? = null
    private var text: TextView? = null


    /*override val contxt: Context
        get() = ctx!!;*/

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.findViewById<ConstraintLayout>(R.id.cly_home_home).setOnClickListener {

        }
        view.findViewById<ConstraintLayout>(R.id.cly_home_inquiries).setOnClickListener {

        }
        view.findViewById<ConstraintLayout>(R.id.cly_home_projects).setOnClickListener {

        }
        view.findViewById<ConstraintLayout>(R.id.cly_home_forum).setOnClickListener {

        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as DisposableActivity).displayWaiting()

    }


}
