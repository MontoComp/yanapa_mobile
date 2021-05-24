package com.montcomp.yanata.Charts.BarChart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
import com.montcomp.yanata.Login.LoginPresenterToViewProtocol
import com.montcomp.yanata.Login.LoginViewToPresenterProtocol
import com.montcomp.yanata.R

class BarChartActivity: DisposableActivity(),BarChartPresenterToViewProtocol {

    override val context: Context = this
    override var presenter: BarChartViewToPresenterProtocol? = null
    var listpeople_res = ArrayList<PeopleResponse>()
    private var noDocsLayout : LinearLayout? = null

    override fun showBarChart(argument: ArrayList<PeopleResponse>) {
        listpeople_res.clear()
        Log.d("BAR",argument.toString())
        var barChart = findViewById<BarChart>(R.id.barchart)
        noDocsLayout = findViewById(R.id.no_reports_layout_bar)/////

        var rec_type = intent.getStringExtra("Type")?:"Total"

        if (!argument.isNullOrEmpty()){
            listpeople_res=argument

            var listpeopletotal = ArrayList<BarEntry>()
            for (element in 0 until listpeople_res.size){
                if (rec_type.equals("Male")){
                    listpeopletotal.add(BarEntry(listpeople_res[element].year.toFloat(),listpeople_res[element].male.toFloat()))
                }
                if (rec_type.equals("Female")){
                    listpeopletotal.add(BarEntry(listpeople_res[element].year.toFloat(),listpeople_res[element].female.toFloat()))
                }
                if (rec_type.equals("Total")){
                    listpeopletotal.add(BarEntry(listpeople_res[element].year.toFloat(),listpeople_res[element].total.toFloat()))
                }
            }

            /*var listpeoplemale = ArrayList<BarEntry>()
            for (element in 0 until listpeople_res.size){
                listpeoplemale.add(BarEntry(listpeople_res[element].year.toFloat(),listpeople_res[element].male.toFloat()))
            }

            var listpeoplefemale = ArrayList<BarEntry>()
            for (element in 0 until listpeople_res.size){
                listpeoplefemale.add(BarEntry(listpeople_res[element].year.toFloat(),listpeople_res[element].female.toFloat()))
            }*/

            var barDataSettotal = BarDataSet(listpeopletotal,"Total")
            barDataSettotal.colors = ColorTemplate.MATERIAL_COLORS.toMutableList()
            //barDataSettotal.color = resources.getColor(R.color.green)
            barDataSettotal.valueTextColor = Color.BLACK
            barDataSettotal.valueTextSize = 16f

            /*var barDataSetmale = BarDataSet(listpeoplemale,"Male")
            //barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toMutableList()
            barDataSetmale.color = resources.getColor(R.color.blue)
            barDataSetmale.valueTextColor = Color.BLACK
            barDataSetmale.valueTextSize = 16f

            var barDataSetfemale = BarDataSet(listpeoplefemale,"Female")
            //barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toMutableList()
            barDataSetfemale.color = resources.getColor(R.color.red)
            barDataSetfemale.valueTextColor = Color.BLACK
            barDataSetfemale.valueTextSize = 16f*/


            var  barData =  BarData()
            barData.addDataSet(barDataSettotal)
            /*barData.addDataSet(barDataSetmale)
            barData.addDataSet(barDataSetfemale)*/


            barChart.setFitBars(true)
            barChart.data = barData

            barData.barWidth = 0.7f
            //barChart.groupBars(0f,0.01f,0.05f)
            barChart.description.text =  "Bar Chart People"
            barChart.animateY(2000)
        }else{
            barChart.visibility= View.GONE
            noDocsLayout?.visibility = View.VISIBLE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barchart)
        BarChartRouter.configure(this)

        val toolbarTitle = this.findViewById(R.id.toolbar_title_back)  as TextView?
        toolbarTitle?.setText("BarChart People")
        toolbarTitle?.setTextColor(this.resources.getColor(R.color.white))

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            onBackPressed()
        }

    }

    override fun onResume() {
        super.onResume()

        var rec_district = intent.getStringExtra("District")?:"Pitipo"
        presenter?.validateBarChart(rec_district)
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