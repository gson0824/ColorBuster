package com.gson.colorbuster.ui.main

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.Person.fromBundle
import androidx.media.AudioAttributesCompat.fromBundle
import com.gson.colorbuster.R
import androidx.navigation.findNavController
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {


    private lateinit var color: TextView
    private lateinit var hex: TextView
    private lateinit var backButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        backButton = view.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_detailFragment_to_mainFragment)
        }
        return view
    }
}