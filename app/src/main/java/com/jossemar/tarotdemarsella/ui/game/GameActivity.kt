package com.jossemar.tarotdemarsella.ui.game

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.MenuItem
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jossemar.tarotdemarsella.R

class GameActivity : AppCompatActivity(), MoveClickListener {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var instOne: TextView
    private lateinit var instTwo: TextView
    private lateinit var instThree: TextView
    private lateinit var instFour: TextView
    private lateinit var instFive: TextView
    private lateinit var cards: LinearLayout
    private lateinit var result: AppCompatButton
    private var statusOne: Boolean = false
    private var statusTwo: Boolean = false
    private var statusThree: Boolean = false
    private var statusFour: Boolean = false
    private var statusFive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        setContentView(R.layout.activity_game)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView: RecyclerView = findViewById(R.id.game_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.addItemDecoration(ItemDecorator(-100))
        gameViewModel.listCards.observe(this, Observer {
            recyclerView.adapter = RecyclerAdapter(it,this)
        })

        instOne = findViewById(R.id.inst_one)
        instTwo = findViewById(R.id.inst_two)
        instThree = findViewById(R.id.inst_three)
        instFour = findViewById(R.id.inst_four)
        instFive = findViewById(R.id.inst_five)
        cards = findViewById(R.id.game_cards)
        result = findViewById(R.id.game_results)
        findViewById<View>(R.id.locker_one).setOnDragListener { view, dragEvent ->
            if (!statusOne) OnDrag(view,dragEvent, 1) else false
        }
        findViewById<View>(R.id.locker_two).setOnDragListener { view, dragEvent ->
            if (statusOne && !statusTwo) OnDrag(view,dragEvent, 2) else false
        }
        findViewById<View>(R.id.locker_three).setOnDragListener { view, dragEvent ->
            if (statusTwo && !statusThree) OnDrag(view,dragEvent, 3) else false
        }
        findViewById<View>(R.id.locker_four).setOnDragListener { view, dragEvent ->
            if (statusThree && !statusFour) OnDrag(view,dragEvent, 4) else false
        }
        findViewById<View>(R.id.locker_five).setOnDragListener { view, dragEvent ->
            if (statusFour && !statusFive) OnDrag(view,dragEvent, 5) else false
        }
    }

    fun OnDrag(v: View, event: DragEvent, status: Int): Boolean {
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                return event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    v.background.colorFilter = BlendModeColorFilter(Color.GRAY, BlendMode.SRC_ATOP)
                } else {
                    v.background.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
                }
                v.invalidate()
                return true
            }
            DragEvent.ACTION_DRAG_LOCATION ->
                return true
            DragEvent.ACTION_DRAG_EXITED -> {
                v.background.clearColorFilter()
                v.invalidate()
                return true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text.toString()
                Toast.makeText(this, "Dragged data is $dragData ", Toast.LENGTH_SHORT).show()
                v.background.clearColorFilter()
                v.invalidate()
                val vw = event.localState as View
                val owner = vw.parent as ViewGroup
                owner.removeView(vw)
                val container = v as LinearLayout
                container.addView(vw)
                vw.visibility = View.VISIBLE
                vw.setBackgroundResource(applicationContext.resources.getIdentifier(dragData,"drawable",packageName))
                when (status) {
                    1 -> statusOne = true
                    2 -> statusTwo = true
                    3 -> statusThree = true
                    4 -> statusFour = true
                    5 -> {
                        statusFive = true
                        cards.visibility = View.GONE
                        result.visibility = View.VISIBLE
                    }
                }
                instOne.visibility = View.GONE
                instTwo.visibility = View.GONE
                instThree.visibility = View.GONE
                instFour.visibility = View.GONE
                instFive.visibility = View.GONE
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.background.clearColorFilter()
                v.invalidate()
                if (event.result) Toast.makeText(
                    this,
                    "The drop was handled.",
                    Toast.LENGTH_SHORT
                ).show() else Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT)
                    .show()
                instOne.visibility = View.GONE
                instTwo.visibility = View.GONE
                instThree.visibility = View.GONE
                instFour.visibility = View.GONE
                instFive.visibility = View.GONE
                return true
            }
            else -> Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
        }
        return false
    }

    override fun OnMoveClick(view: View): Boolean {
        val item = ClipData.Item(view.tag as? CharSequence)
        val dragData = ClipData(view.tag as? CharSequence,arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),item)
        val dragshadow = DragShadowBuilder(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(dragData,dragshadow,view,0)
        } else {
            view.startDrag(dragData,dragshadow,view,0)
        }
        if (!statusOne)
            instOne.visibility = View.VISIBLE
        else if (!statusTwo)
            instTwo.visibility = View.VISIBLE
        else if (!statusThree)
            instThree.visibility = View.VISIBLE
        else if (!statusFour)
            instFour.visibility = View.VISIBLE
        else if (!statusFive)
            instFive.visibility = View.VISIBLE
        return true
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