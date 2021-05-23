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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Charts.BarChart.BarChartActivity
import com.montcomp.yanata.Charts.PieChart.PieChartActivity
import com.montcomp.yanata.Charts.RadarChart.RadarChartActivity
import com.montcomp.yanata.People.Adapter.PeopleFragmentAdapter
import com.montcomp.yanata.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList


class PeopleFragment() : Fragment(), PeopleFragmentPresenterToViewProtocol{

    var getPeople:ArrayList<PeopleResponse> = ArrayList()
    var displayList:ArrayList<PeopleResponse> = ArrayList()

    var recycleView: RecyclerView? = null
    var search_people: SearchView? = null
    private var noDocsLayout : LinearLayout? = null

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
        recycleView = view.findViewById(R.id.recycler_fg_people)
        search_people = view.findViewById(R.id.search_people)
        noDocsLayout = view.findViewById(R.id.no_reports_layout_people)/////

        view.findViewById<ImageView>(R.id.iv_setting_filter).setOnClickListener {

            var alertFilterDialog = Dialog(view.context)
            alertFilterDialog.setContentView(R.layout.alert_custom_filter_people)///
            var window = alertFilterDialog.window
            var param : WindowManager.LayoutParams = window.attributes
            param.gravity=  Gravity.CENTER
            window.attributes=param

            window.setBackgroundDrawable(ContextCompat.getDrawable(view.context,R.drawable.border_shape_input))

            var et_district = alertFilterDialog.findViewById<EditText>(R.id.et_district_people)
            var rb_bar = alertFilterDialog.findViewById<RadioButton>(R.id.rb_bar)
            var rb_pie= alertFilterDialog.findViewById<RadioButton>(R.id.rb_pie)
            var rb_radar = alertFilterDialog.findViewById<RadioButton>(R.id.rb_radar)

            rb_bar.isChecked=true


            alertFilterDialog.findViewById<Button>(R.id.btn_send_filter).setOnClickListener {

                if (!et_district.text.toString().equals("")){
                    if (rb_bar.isChecked){
                        val intent = Intent(view.context, BarChartActivity::class.java)
                        intent.putExtra("District",et_district.text.toString())
                        startActivity(intent)
                    }
                    if (rb_pie.isChecked){
                        val intent = Intent(view.context, PieChartActivity::class.java)
                        intent.putExtra("District",et_district.text.toString())
                        startActivity(intent)
                    }
                    if (rb_radar.isChecked){
                        val intent = Intent(view.context, RadarChartActivity::class.java)
                        intent.putExtra("District",et_district.text.toString())
                        startActivity(intent)
                    }

                    //Toast.makeText(view.context,"${rb_bar.isChecked}-${rb_pie.isChecked}-${rb_radar.isChecked}",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(view.context,"Es necesario que escriba un distrito",Toast.LENGTH_SHORT).show()
                }

            }

            var close_checkpermissionDialog=alertFilterDialog.findViewById<ImageView>(R.id.close_dialog_filter_people)
            close_checkpermissionDialog.setOnClickListener {
                alertFilterDialog.dismiss()
            }

            alertFilterDialog.show()

        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ctx != null) {

            recycleView?.layoutManager = LinearLayoutManager(ctx)
        }

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
        getPeople.clear()

        CoroutineScope(Dispatchers.IO).launch {
            var listF = ArrayList<PeopleResponse>()

            for (element1 in argument){
                    for (element2 in listF){
                        if (element1.area == element2.area){
                            listF.add(PeopleResponse(element2.area,element2.district,element2.stage,element2.year,element1.male+element2.male,element1.female+element2.female,element1.total+element2.total))
                        }else{
                            listF.add(element1)
                        }

                    }
            }

            withContext(Dispatchers.Main){
                Log.d("LISTFFFF",listF.toString())
            }
        }


        getPeople=argument
        displayList.addAll(getPeople)

        if (argument!=null){

            search_people?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0!!.isNotEmpty()){
                        displayList.clear()
                        val search = p0.toLowerCase(Locale.getDefault())
                        getPeople.forEach{
                            if (it.district.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        recycleView?.adapter?.notifyDataSetChanged()
                    }else{
                        displayList.clear()
                        displayList.addAll(getPeople)
                        recycleView?.adapter?.notifyDataSetChanged()
                    }
                    return true
                }
            })


            if (ctx != null) {

                recycleView?.adapter = PeopleFragmentAdapter(this@PeopleFragment,displayList)

                if (!getPeople.isNullOrEmpty()){
                    noDocsLayout?.visibility= View.GONE
                }else{
                    noDocsLayout?.visibility= View.VISIBLE
                }

            }
        }
    }
}
