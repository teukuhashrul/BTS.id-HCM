package com.example.hcmbtsid.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.hcmbtsid.R
import com.example.hcmbtsid.data.model.MyProjects
import com.example.hcmbtsid.adapter.Projects_RecyclerAdapter
import com.example.hcmbtsid.adapter.Projects_RecyclerAdapter.OnItemClickListener
import com.example.hcmbtsid.ui.projectDetail.ProjectDetail_Activity
import kotlinx.android.synthetic.main.fragment_projects_.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Projects_Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Projects_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Projects_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_projects_, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //-------------------------- Recyler View
        //create dummy data
        val projectList = listOf(
            MyProjects("Mutia Farma", "On Progress" , "PT Mutia Farma", "On Site", "February 2019", "May 2019") ,
            MyProjects("Grab Jek", "Completed" , "PT Grab X Gojek ", "On Site", "February 2019", "May 2019") ,
            MyProjects("ITM Gas Emas", "Completed" , "PT Gas Emas", "Remote", "February 2019", "December 2019")
        )

        // create adapter
        val projects_adapter =
            Projects_RecyclerAdapter(projectList, object : OnItemClickListener {
                override fun onItemClick(myProjects: MyProjects) {
//                Toast.makeText(context, "clicked" + myProjects.projectsName , Toast.LENGTH_SHORT).show()
                    startActivity(Intent(activity, ProjectDetail_Activity::class.java))

                }
            })

        // attach Adapter
        recylerView_Projects.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = projects_adapter
        }
      // --------------------------



    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Projects_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Projects_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
