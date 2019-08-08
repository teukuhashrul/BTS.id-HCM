package com.example.hcmbtsid.ui.menu

/**
 * a : recycle view tutorial
 *
 */
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hcmbtsid.R
import com.example.hcmbtsid.repository.DataRepository
import com.example.hcmbtsid.data.model.Profile_Model
import com.example.hcmbtsid.adapter.Profile_RecyclerAdapter
import com.example.hcmbtsid.data.model.Karyawan
import com.example.hcmbtsid.data.model.User_Retrofit
import com.example.hcmbtsid.ui.editProfile.EditProfile_Activity
import kotlinx.android.synthetic.main.fragment_profile_.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Profile_Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Profile_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Profile_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    // Linear Layout Manager (a.1)
    private lateinit var linearLayoutManager: LinearLayoutManager


    // constant value for picking dialog alert for update photo feature
    private val GALLERY = 1
    private val CAMERA = 2

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestKaryawanData()

        // add listener to the button
        // create master data list of profile


        // button edit profile on click listener
        btn_edit.setOnClickListener {
            startActivity(Intent(activity, EditProfile_Activity::class.java))
        }

        // button update photo profile on click listener
        btn_updatePhoto.setOnClickListener {
            showPictureDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
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
         * @return A new instance of fragment Profile_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /**
     *  Method called when on click for update photo profile is clicked
     *  alert dialog with
     */
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(context!!)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    /**
     * method called when people pick Select Photo From Calleri
     * then start picking from gallery
     */
    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALLERY)
    }

    /**
     * Method called when people pick Capture Photo From the Camera
     */
    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        ) {
            startActivityForResult(intent, CAMERA)
        }
    }

    /**
     * When user picked the photo whether from gallery or the camera
     * Called to handle the picture picked from 2 method before
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    // get data from the picked photo
                    val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentURI)
                    // save to the local directory
                    // val path = saveBitmap(bitmap)
                    // change profile picture with the newest pic picked from the result
                    profilePic_iv.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else if (requestCode == CAMERA) {
            // get data from the camera
            val thumbnail = data!!.extras!!.get("data") as Bitmap

            // change profile picture with the newest pic picked from the result
            profilePic_iv.setImageBitmap(thumbnail)
            // save to the local directory

        }
    }

//    fun saveImage()


    fun requestKaryawanData() {
        val new_Intent = activity?.intent
        Toast.makeText(context , " ${new_Intent?.getIntExtra("Karyawan" , 0)}" , Toast.LENGTH_LONG).show()
        val userServices = DataRepository.create()
        userServices.getKaryawanById(2).enqueue(object : Callback<Karyawan> {
            override fun onResponse(call: Call<Karyawan>, response: Response<Karyawan>) {
                setDataKaryawan(response.body() as Karyawan)
            }

            override fun onFailure(call: Call<Karyawan>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    fun setDataKaryawan(karyawan_New: Karyawan) {
        val listProfile = listOf(
            Profile_Model("Name", "${karyawan_New.nama}", R.drawable.ic_profile),
            Profile_Model("Email", "${karyawan_New.email}", R.drawable.ic_email_black),
            Profile_Model("Employee ID", "${karyawan_New.nip}", R.drawable.ic_employeeid_black),
            Profile_Model("Phone Number", "${karyawan_New.telp_kerja}", R.drawable.ic_phone_black),
            Profile_Model("Home Address", "${karyawan_New.alamat}", R.drawable.ic_address_black),
            Profile_Model("Birth Date", "Jakarta, October 15th 1998", R.drawable.ic_logout_black),
            Profile_Model("Position", "Senior Mobile Developer", R.drawable.ic_position_black)
        )

        // create profile adapter
        val profilesAdapter = Profile_RecyclerAdapter(listProfile)

        //
        recyclerView_Profile.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = profilesAdapter
        }
    }
}
