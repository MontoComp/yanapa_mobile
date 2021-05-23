package com.montcomp.yanata.Charts.PieChart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
import com.montcomp.yanata.Charts.BarChart.BarChartPresenterToViewProtocol
import com.montcomp.yanata.Charts.BarChart.BarChartRouter
import com.montcomp.yanata.Charts.BarChart.BarChartViewToPresenterProtocol
import com.montcomp.yanata.R

class PieChartActivity:DisposableActivity() , PieChartPresenterToViewProtocol {

    override val context: Context = this
    override var presenter: PieChartViewToPresenterProtocol? = null
    var listpeople_res = ArrayList<PeopleResponse>()
    private var noDocsLayout : LinearLayout? = null

    override fun showPieChart(argument: ArrayList<PeopleResponse>) {
        listpeople_res.clear()
        Log.d("PIE",argument.toString())
        var pieChart = findViewById<PieChart>(R.id.piechart)
        noDocsLayout = findViewById(R.id.no_reports_layout_pie)/////

        if (!argument.isNullOrEmpty()){
            listpeople_res=argument

            var listpeople = ArrayList<PieEntry>()

            for (element in listpeople_res){
                listpeople.add(PieEntry(element.total.toFloat(),element.year.toString()))
            }

            var pieDataSet = PieDataSet(listpeople,"Total People")
            pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toMutableList()
            pieDataSet.valueTextColor = Color.BLACK
            pieDataSet.valueTextSize = 16f

            var  pieData =  PieData(pieDataSet)

            pieChart.data = pieData
            pieChart.description.text =  "Pie Chart People"
            pieChart.centerText = "People"
            pieChart.animateY(2000)
        }else{
            pieChart.visibility= View.GONE
            noDocsLayout?.visibility = View.VISIBLE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piechart)
        PieChartRouter.configure(this)

        val toolbarTitle = this.findViewById(R.id.toolbar_title_back)  as TextView?
        toolbarTitle?.setText("PieChart People")
        toolbarTitle?.setTextColor(this.resources.getColor(R.color.white))

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            onBackPressed()
        }

    }

    override fun onResume() {
        super.onResume()

        var rec_district = intent.getStringExtra("District")?:"Pitipo"
        presenter?.validatePieChart(rec_district)
    }


    fun myinitlist():ArrayList<PeopleResponse>{
        var list = ArrayList<PeopleResponse>()
        list.add(PeopleResponse(14001,"dasdasd","sdasdas",21,5649,78545,1000))
        list.add(PeopleResponse(14002,"dasdasd","sdasdas",21,46545,46545,1200))
        list.add(PeopleResponse(14003,"dasdasd","sdasdas",21,5649,78545,900))
        list.add(PeopleResponse(14004,"dasdasd","sdasdas",21,46545,46545,750))
        list.add(PeopleResponse(14005,"dasdasd","sdasdas",21,5649,78545,1100))
        return list
    }

}