package com.montcomp.yanata.People


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.R
import java.util.*
import kotlin.collections.ArrayList


class PeopleFragment() : Fragment(), PeopleFragmentPresenterToViewProtocol{

    var ctx: Context ? = null

    override val contxt: Context
        get() = ctx!!;

    override var presenter: PeopleFragmentViewToPresenterProtocol? = null

    companion object {

        fun newInstance(): PeopleFragment {
            return PeopleFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PeopleFragmentRouter.configure(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_people, container, false)
        //var rb1=view.findViewById<RadioButton>(R.id.rb_request_condauth)
        //var rb2= view.findViewById<RadioButton>(R.id.rb_request_permit)

        //rb1.isChecked=true

        view.findViewById<LinearLayout>(R.id.ly_view_datail_approved).setOnClickListener {
            /*val intent = Intent(view.context,DetailRequestActivity::class.java)
            startActivity(intent)*/
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as DisposableActivity).displayWaiting()

        presenter?.validatePeople()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx=context
    }

    override fun onDetach() {
        super.onDetach()
        ctx = null
    }

    override fun displayWaiting() {
        val progressBarHolder = view?.findViewById(R.id.common_waiting_container) as? RelativeLayout
        val inAnimation = AlphaAnimation(0f, 1f)
        progressBarHolder?.animation = inAnimation
        progressBarHolder?.visibility = View.VISIBLE

    }

    override fun hideWaiting() {
        val progressBarHolder =  view?.findViewById<View>(R.id.common_waiting_container) as? RelativeLayout
        val outAnimation = AlphaAnimation(1f, 0f)
        progressBarHolder?.animation = outAnimation
        progressBarHolder?.visibility = View.GONE

    }

    override fun showPeople(argument: ArrayList<PeopleResponse>) {
        Log.d("PEOPLE",argument.toString())
    }
}
