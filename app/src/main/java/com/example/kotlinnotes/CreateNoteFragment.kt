package com.example.kotlinnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kotlinnotes.database.NotesDatabase
import com.example.kotlinnotes.entities.Notes
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : BaseFragment() {

    var selectedColor = "#171C26"
    var currentDate:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())

        tvDateTime.text = currentDate

        imgDone.setOnClickListener{

            //saveNote
            saveNote()
        }

        imgBack.setOnClickListener{
            replaceFragment(HomeFragment.newInstance(), false)
        }

    }

    private fun saveNote(){

        if(etNoteTitle.text.isNullOrEmpty()){
            Toast.makeText(context, "Note Title is Required",Toast.LENGTH_SHORT).show()
        }
        if(etNoteSubTitle.text.isNullOrEmpty()){
            Toast.makeText(context, "Note Sub Title is Required",Toast.LENGTH_SHORT).show()
        }
        if(etNoteDescription.text.isNullOrEmpty()){
            Toast.makeText(context, "Note Description must contains text",Toast.LENGTH_SHORT).show()
        }

        launch {
            val notes = Notes()
            notes.title = etNoteTitle.text.toString()
            notes.subTitle = etNoteSubTitle.text.toString()
            notes.noteText = etNoteDescription.text.toString()
            notes.dateTime = currentDate

            context?.let{
                NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                etNoteTitle.setText("")
                etNoteSubTitle.setText("")
                etNoteDescription.setText("")
            }
        }

    }

    fun replaceFragment(fragment:Fragment, istransition:Boolean){
        val fragmentTransition = activity!!.supportFragmentManager.beginTransaction()

        if(istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }

        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }

}