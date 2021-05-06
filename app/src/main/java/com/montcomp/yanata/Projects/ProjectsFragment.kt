package com.montcomp.yanata.Projects


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.montcomp.yanata.AddProject.AddProjectActivity
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.Projects.Adapter.ProjectsFragmentAdapter
import com.montcomp.yanata.R
import com.montcomp.yanata.Utils.DinamicScroll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProjectsFragment() : Fragment(), ProjectsFragmentPresenterToViewProtocol{

    var ctx: Context ? = null

    override val contxt: Context
        get() = ctx!!;

    override var presenter: ProjectsFragmentViewToPresenterProtocol? = null

    private var noDocsLayout : LinearLayout? = null

    var recycleView: RecyclerView? = null

    var mylist = ArrayList<ProjectsResponse>()

    companion object {

        fun newInstance(): ProjectsFragment {
            return ProjectsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DinamicScroll().setHidingToolbar(recycleView as View, findViewById(R.id.my_toolbar))
        ProjectsFragmentRouter.configure(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_projects, container, false)

        view.findViewById<RelativeLayout>(R.id.rl_fragment_projects).addView(MyViewProjects(view.context),0)

        recycleView = view.findViewById(R.id.recycler_fg_projects)

        noDocsLayout = view.findViewById(R.id.no_reports_layout_projects)/////

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(view.context,AddProjectActivity::class.java)
            startActivity(intent)
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as DisposableActivity).displayWaiting()
        if (ctx != null) {

            recycleView?.layoutManager = LinearLayoutManager(ctx)
        }


    }

    override fun onResume() {
        presenter?.validateProjects()
        super.onResume()
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

    override fun showProjects(argument: ArrayList<ProjectsResponse>) {
        mylist.clear()

        if (argument!=null){
            CoroutineScope(Dispatchers.IO).launch {
                mylist.addAll(argument)

                withContext(Dispatchers.Main){
                    if (ctx != null) {
                        //val contx = ctx ?: return@withContext
                        recycleView?.adapter = ProjectsFragmentAdapter(this@ProjectsFragment,mylist)

                        if (!mylist.isNullOrEmpty()){
                            noDocsLayout?.visibility= View.GONE
                        }else{
                            noDocsLayout?.visibility= View.VISIBLE
                        }
                    }
                }
            }
        }


    }

    override fun showPostProject(argument: Any) {
        Toast.makeText(ctx,"OK",Toast.LENGTH_SHORT).show()
    }


}

class MyViewProjects(context: Context?) : View(context) {

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
        paint.setColor(context.resources.getColor(R.color.dom_yanapa))
        paint.setStyle(Paint.Style.FILL)
        paint.setStrokeWidth(15F)

        val width = canvas?.width
        val heigth = canvas?.height

        val path = Path()
        path.lineTo(0F, (heigth!!*0.12).toFloat())
        path.quadTo((width!!*0.50).toFloat(),(heigth!!*0.18).toFloat(), width!!.toFloat(), (heigth!!*0.12).toFloat())
        path.lineTo(width!!.toFloat(), 0F)
        path.lineTo(0F, 0F)

        canvas.drawPath(path, paint)

    }
}

