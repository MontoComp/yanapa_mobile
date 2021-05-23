package com.montcomp.yanata.Charts.RadarChart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
import com.montcomp.yanata.Charts.PieChart.PieChartPresenterToViewProtocol
import com.montcomp.yanata.Charts.PieChart.PieChartViewToPresenterProtocol
import com.montcomp.yanata.R

class RadarChartActivity: DisposableActivity(), RadarChartPresenterToViewProtocol {

    override val context: Context = this
    override var presenter: RadarChartViewToPresenterProtocol? = null
    var listpeople_res = ArrayList<PeopleResponse>()
    private var noDocsLayout : LinearLayout? = null

    override fun showRadarChart(argument: ArrayList<PeopleResponse>) {
        listpeople_res.clear()
        Log.d("RADAR",argument.toString())

        var radarChart = findViewById<RadarChart>(R.id.radarchart)
        noDocsLayout = findViewById(R.id.no_reports_layout_radar)/////

        if (!argument.isNullOrEmpty()){
            listpeople_res=argument

            var listpeople = ArrayList<RadarEntry>()

            for (element in listpeople_res){
                listpeople.add(RadarEntry(element.total.toFloat()))
            }

            var radarDataSet = RadarDataSet(listpeople,"Total People")
            radarDataSet.color = Color.RED
            radarDataSet.lineWidth = 2f
            radarDataSet.valueTextColor = Color.RED
            radarDataSet.valueTextSize = 14f

            var  radarData =  RadarData()

            radarData.addDataSet(radarDataSet)

            var labels = ArrayList<String>()
            for (element in listpeople_res){
                labels.add(element.year.toString())
            }

            var xAxis = radarChart.xAxis
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)

            radarChart.data = radarData
            radarChart.description.text =  "Pie Chart People"
            radarChart.animateY(2000)
        }else{
            radarChart.visibility= View.GONE
            noDocsLayout?.visibility = View.VISIBLE
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radarchart)
        RadarChartRouter.configure(this)

        val toolbarTitle = this.findViewById(R.id.toolbar_title_back)  as TextView?
        toolbarTitle?.setText("RadarChart People")
        toolbarTitle?.setTextColor(this.resources.getColor(R.color.white))


        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            onBackPressed()
        }


    }

    override fun onResume() {
        super.onResume()

        var rec_district = intent.getStringExtra("District")?:"Pitipo"
        presenter?.validateRadarChart(rec_district)
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