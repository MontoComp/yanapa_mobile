package com.montcomp.yanata.AddComment

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.montcomp.yanata.AddComment.Adapter.ProjectsAdapter
import com.montcomp.yanata.Base.ComonDomain.ForumRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.MainActivity
import com.montcomp.yanata.Projects.MyViewProjects
import com.montcomp.yanata.Projects.ProjectsFragment
import com.montcomp.yanata.R

class BottomSheetFragment():BottomSheetDialogFragment(),BottomSheetFragmentPresenterToViewProtocol {

    private var spinner_projects: Spinner?=null
    private var arrayAdapter_projects: ProjectsAdapter?=null
    private var itemlist_projects = ArrayList<ProjectsResponse>()
    var myprojetcs:ProjectsResponse?=null

    var ctx: Context? = null

    override val contxt: Context
        get() = ctx!!;

    override var presenter: BottomSheetFragmentViewToPresenterProtocol? = null

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
        setStyle(STYLE_NORMAL, R.style. AppBottomSheetDialogTheme);

        BottomSheetFragmentRouter.configure(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_bottom_sheet,container,false)

        view.findViewById<RelativeLayout>(R.id.fragment_bottom_comment).addView(MyViewBottomComment(view.context),0)
        //dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.drawable.border_shape_nav_bottom))

        spinner_projects=view.findViewById(R.id.sp_projects)

        //var title_project = view.findViewById<EditText>(R.id.et_project_forum)
        var name = view.findViewById<EditText>(R.id.et_name_forum)
        var description = view.findViewById<EditText>(R.id.et_comment_forum)

        val sdfHour = java.text.SimpleDateFormat("HH:mm")
        val sdfDate = java.text.SimpleDateFormat("dd/MM/yyyy")

        val myhour = sdfHour.format(java.util.Date().time)
        val mydate = sdfDate.format(java.util.Date().time)

        view.findViewById<Button>(R.id.btn_send_comment).setOnClickListener {
            if (myprojetcs?.id!="0"){
                if (!name.text.toString().isNullOrEmpty()){
                    if (!description.text.toString().isNullOrEmpty()){
                        var forumrequest = ForumRequest(myprojetcs!!.title,name.text.toString(),description.text.toString(),myhour,mydate)
                        presenter?.validatePostComment(forumrequest)
                    }else{
                        Toast.makeText(view.context,"Escribe un comentario sobre el proyecto",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(view.context,"Es necesario que escribas tu nombre",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(view.context,"Debes seleccionar un proyecto",Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(view.parent as View).setBackgroundColor(Color.TRANSPARENT)
        if (ctx != null) {

            recycleView?.layoutManager = LinearLayoutManager(ctx)
        }


    }

    override fun onResume() {
        presenter?.validateProjectsNames()
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

    override fun showProjectsNames(argument: ArrayList<ProjectsResponse>) {
        //Toast.makeText(contxt,"${argument.toString()}",Toast.LENGTH_SHORT).show()
        itemlist_projects.clear()

        if (argument.isNullOrEmpty()){
            spinner_projects?.isEnabled=false
        }

        itemlist_projects.addAll(0,arrayListOf(ProjectsResponse("0","Seleccionar","no",123456,"no","no",123456)))
        itemlist_projects.addAll(argument)

        arrayAdapter_projects = ProjectsAdapter(contxt,itemlist_projects)
        spinner_projects?.adapter = arrayAdapter_projects
        spinner_projects?.prompt = "Seleccionar"

        spinner_projects?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                myprojetcs=itemlist_projects[p2]
                //myprovince = p0?.getItemAtPosition(p2) as String
                if (p2==0){


                }else{


                }
            }
        }
    }

    override fun showPostComment(argument: Any) {
        Toast.makeText(ctx,"OK",Toast.LENGTH_SHORT).show()
        /*var bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.dis*/
        val intent = Intent(view?.context,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("id_main",4)
        startActivity(intent)

    }
}

class MyViewBottomComment(context: Context?) : View(context) {

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
        paint.setColor(context.resources.getColor(R.color.dom_yanapa))
        paint.setStyle(Paint.Style.FILL)
        paint.setStrokeWidth(15F)

        val width = canvas?.width
        val heigth = canvas?.height

        val path = Path()
        path.lineTo(0F, (heigth!!*0.10).toFloat())
        path.quadTo((width!!*0.50).toFloat(),(heigth!!*0.18).toFloat(), width!!.toFloat(), (heigth!!*0.10).toFloat())
        path.lineTo(width!!.toFloat(), 0F)
        path.lineTo(0F, 0F)

        canvas.drawPath(path, paint)

    }
}