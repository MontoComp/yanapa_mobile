package com.montcomp.yanata.Forum


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.*
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.montcomp.yanata.R
import com.montcomp.yanata.AddComment.BottomSheetFragment
import com.montcomp.yanata.Base.ComonDomain.ForumResponse
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.Forum.Adapter.ForumFragmentAdapter
import com.montcomp.yanata.Projects.Adapter.ProjectsFragmentAdapter
import com.montcomp.yanata.Projects.MyViewProjects
import com.montcomp.yanata.Projects.ProjectsFragmentPresenterToViewProtocol
import com.montcomp.yanata.Projects.ProjectsFragmentViewToPresenterProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ForumFragment() : Fragment(), ForumFragmentPresenterToViewProtocol {



    var ctx: Context ? = null

    override val contxt: Context
        get() = ctx!!;

    override var presenter: ForumFragmentViewToPresenterProtocol? = null

    private var noDocsLayout : LinearLayout? = null

    var recycleView: RecyclerView? = null

    var mylist = ArrayList<ForumResponse>()

    companion object {

        fun newInstance(): ForumFragment {
            return ForumFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ForumFragmentRouter.configure(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_forum, container, false)

        view.findViewById<RelativeLayout>(R.id.rl_fragment_forum).addView(MyViewForum(view.context),0)

        recycleView = view.findViewById(R.id.recycler_fg_forum)

        noDocsLayout = view.findViewById(R.id.no_reports_layout_forum)/////

        view.findViewById<FloatingActionButton>(R.id.fab_forum).setOnClickListener {
            var bottomsheetfragment = BottomSheetFragment()
            val fragmentmanager = (this@ForumFragment).fragmentManager
            bottomsheetfragment.show(fragmentmanager!!,"BottomSheet")
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
        presenter?.validateForum()
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

    override fun showForum(argument: ArrayList<ForumResponse>) {
        mylist.clear()

        if (argument!=null){
            CoroutineScope(Dispatchers.IO).launch {
                mylist.addAll(argument)

                withContext(Dispatchers.Main){
                    if (ctx != null) {
                        //val contx = ctx ?: return@withContext
                        recycleView?.adapter = ForumFragmentAdapter(this@ForumFragment,mylist)

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


}

class MyViewForum(context: Context?) : View(context) {

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

