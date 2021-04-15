package com.lain.colorlistroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lain.colorlistroom.entity.Color
import com.lain.colorlistroom.view.ColorAdapter
import com.lain.colorlistroom.viewmodel.ColorViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var colorViewModel : ColorViewModel ?= null

    private val colorAdapter = ColorAdapter(
            colors = mutableListOf(),
            onDelete = { color -> onDelete(color = color) },
            onClick = { color -> onClickColor(color = color) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        colorViewModel = ColorViewModel(colorRepository =  ((application) as Application).repository)

        with(colorsRV){
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            layoutManager =LinearLayoutManager(this@MainActivity,  LinearLayoutManager.VERTICAL, false )
            adapter = colorAdapter
        }

        colorViewModel!!.allColors.observe(this){
            colorAdapter.clear()
            colorAdapter.addAll(colors = it)
        }

        colorViewModel!!.onDelete.observe(this){
            if(it) colorViewModel!!.findAll()
        }
    }

    private fun onClickColor(color: Color){
        val intent = Intent(this, AddColorActivity::class.java)
        intent.putExtra("COLOR", color)
        startActivity(intent)
    }

    private fun onDelete(color: Color){
        colorViewModel!!.delete(color = color)
    }


    override fun onStart() {
        super.onStart()

        colorViewModel!!.findAll()

        addColorFAB.setOnClickListener {
            /**
             * Laun add color activity
             */
            startActivity(Intent(this, AddColorActivity::class.java))
        }

    }

}