package com.jossemar.tarotdemarsella.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.jossemar.tarotdemarsella.R
import com.jossemar.tarotdemarsella.ui.game.GameActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val cardThree: CardView = root.findViewById(R.id.home_card_three)
        cardThree.setOnClickListener {
            val intent = Intent(context, GameActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}