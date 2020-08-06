package com.jossemar.tarotdemarsella.ui.game

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.content.res.Configuration
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
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
import com.jossemar.tarotdemarsella.ui.detail.DetailActivity

class GameActivity : AppCompatActivity(), MoveClickListener {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var instOne: TextView
    private lateinit var instTwo: TextView
    private lateinit var instThree: TextView
    private lateinit var instFour: TextView
    private lateinit var instFive: TextView
    private lateinit var cards: LinearLayout
    private lateinit var result: AppCompatButton
    private var selected: ArrayList<String> = ArrayList()
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

        val orientation = this.resources.configuration.orientation
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        instOne = findViewById(R.id.inst_one)
        instTwo = findViewById(R.id.inst_two)
        instThree = findViewById(R.id.inst_three)
        instFour = findViewById(R.id.inst_four)
        instFive = findViewById(R.id.inst_five)
        cards = findViewById(R.id.game_cards)
        result = findViewById(R.id.game_results)
        val lockerOne =  findViewById<View>(R.id.locker_one)
        val lockerTwo = findViewById<View>(R.id.locker_two)
        val lockerThree = findViewById<View>(R.id.locker_three)
        val lockerFour = findViewById<View>(R.id.locker_four)
        val lockerFive = findViewById<View>(R.id.locker_five)
        var height = displayMetrics.heightPixels / 5
        var width = displayMetrics.heightPixels / 10
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            height = displayMetrics.heightPixels / 4
            width = displayMetrics.heightPixels / 8
        }
        lockerOne.layoutParams.height = height
        lockerOne.layoutParams.width = width
        lockerOne.requestLayout()
        lockerTwo.layoutParams.height = height
        lockerTwo.layoutParams.width = width
        lockerTwo.requestLayout()
        lockerThree.layoutParams.height = height
        lockerThree.layoutParams.width = width
        lockerThree.requestLayout()
        lockerFour.layoutParams.height = height
        lockerFour.layoutParams.width = width
        lockerFour.requestLayout()
        lockerFive.layoutParams.height = height
        lockerFive.layoutParams.width = width
        lockerFive.requestLayout()
        lockerOne.setOnDragListener { view, dragEvent ->
            if (!statusOne) OnDrag(view,dragEvent, 1) else false
        }
        lockerTwo.setOnDragListener { view, dragEvent ->
            if (statusOne && !statusTwo) OnDrag(view,dragEvent, 2) else false
        }
        lockerThree.setOnDragListener { view, dragEvent ->
            if (statusTwo && !statusThree) OnDrag(view,dragEvent, 3) else false
        }
        lockerFour.setOnDragListener { view, dragEvent ->
            if (statusThree && !statusFour) OnDrag(view,dragEvent, 4) else false
        }
        lockerFive.setOnDragListener { view, dragEvent ->
            if (statusFour && !statusFive) OnDrag(view,dragEvent, 5) else false
        }
        result.setOnClickListener {
            val intent = Intent(baseContext, DetailActivity::class.java)
            intent.putStringArrayListExtra("SELECTED_CARDS", selected)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.game_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.addItemDecoration(ItemDecorator(-110))
        gameViewModel.listCards.observe(this, Observer {
            recyclerView.adapter = RecyclerAdapter(it,this, displayMetrics.heightPixels)
        })
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
                v.background.clearColorFilter()
                v.invalidate()
                val vw = event.localState as View
                val owner = vw.parent as ViewGroup
                owner.removeView(vw)
                val container = v as LinearLayout
                container.addView(vw)
                vw.visibility = View.VISIBLE
                selected.add(dragData)
                vw.setBackgroundResource(applicationContext.resources.getIdentifier(dragData,"drawable",packageName))
                vw.setOnLongClickListener {
                    true
                }
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
                instOne.visibility = View.GONE
                instTwo.visibility = View.GONE
                instThree.visibility = View.GONE
                instFour.visibility = View.GONE
                instFive.visibility = View.GONE
                return true
            }
            else -> Log.e("DragDrop Tarot", "Unknown action type received by OnDragListener.")
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