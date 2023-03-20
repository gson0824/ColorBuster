package com.gson.colorbuster.ui.main

import android.content.SharedPreferences
import android.graphics.Color.parseColor
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gson.colorbuster.R
import com.gson.colorbuster.ui.model.Phrase
import java.util.prefs.Preferences

class MainFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var viewModel: MainViewModel
    private lateinit var prefs: SharedPreferences

    companion object {
        fun newInstance() = MainFragment()
        const val POSITION = "adapter_position"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        prefs = PreferenceManager.getDefaultSharedPreferences(view.context)

        recycler = view.findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.smoothScrollToPosition(prefs.getInt(POSITION, 0))
        Handler(Looper.getMainLooper()).postDelayed({
            recycler.findViewHolderForAdapterPosition(
                prefs.getInt(
                    POSITION,
                    0
                )
            )
        }, 500L)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.vocabulary.observe(viewLifecycleOwner) {
            recycler.adapter = ColorAdapter(it)
        }
    }

    private inner class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
            private lateinit var phrase: Phrase
            private val wordTextView: TextView = itemView.findViewById(R.id.term_textView)
            private val cardView: CardView = itemView.findViewById(R.id.cardView)

            init {
                itemView.setOnClickListener {
                    onClick(view)
                }
            }

            override fun onClick(view: View) {
                val actionEval = MainFragmentDirections.actionMainFragmentToDetailFragment(phrase.name, phrase.hexString)
                Navigation.findNavController(view).navigate(actionEval)
            }

            fun bind(phrase: Phrase) {
                this.phrase = phrase
                wordTextView.text = phrase.name
                cardView.setCardBackgroundColor(parseColor(phrase.hexString))
            }
        }

    private inner class ColorAdapter(private val list: List<Phrase>) :
        RecyclerView.Adapter<ColorViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
            val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
            return ColorViewHolder(view)
        }

        override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount() = list.size
    }

}
