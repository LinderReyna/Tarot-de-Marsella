package com.jossemar.tarotdemarsella.ui.detail

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jossemar.tarotdemarsella.R
import com.jossemar.tarotdemarsella.model.Arcane
import com.jossemar.tarotdemarsella.ui.game.GameViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val selected : ArrayList<String> = intent.getStringArrayListExtra("SELECTED_CARDS")
        val recyclerView: RecyclerView = findViewById(R.id.detail_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        gameViewModel.listCards.observe(this, Observer { it ->
            val listSelect: ArrayList<Arcane> = ArrayList()
            it.forEach { arcane ->
                selected.forEach { item ->
                    if (arcane.image == item){
                        listSelect.add(arcane)
                    }
                }
            }
            recyclerView.adapter = RecyclerAdapter(listSelect, applicationContext, displayMetrics.heightPixels)
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}