package com.lain.colorlistroom

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.azeesoft.lib.colorpicker.ColorPickerDialog
import com.lain.colorlistroom.viewmodel.ColorViewModel
import kotlinx.android.synthetic.main.activity_add_color.*
import java.util.*

class AddColorActivity : AppCompatActivity() {

    private var colorViewModel : ColorViewModel?= null
    private var color = Color.BLACK
    private var colorUpdate : com.lain.colorlistroom.entity.Color ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_color)

        DrawableCompat.setTint(colorSelectedIV.background, color)

        if(intent.extras != null){
            colorUpdate = intent.getSerializableExtra("COLOR") as com.lain.colorlistroom.entity.Color
            colorNameET.setText(colorUpdate!!.name)
            DrawableCompat.setTint(colorSelectedIV.background, colorUpdate!!.color)
        }

        colorViewModel = ColorViewModel(colorRepository =  ((application) as Application).repository)

    }

    override fun onStart() {
        super.onStart()

        colorPickerBTN.setOnClickListener {
            /**
             * Open color picker
             * */
            photoshopColorPicker()
        }

        saveColorBTN.setOnClickListener {
            /**
             * Validate inputs
             */
            val color = this.color
            val name = colorNameET.text.toString().toLowerCase(Locale.ROOT)

            if(name.isEmpty()){
                Toast.makeText(this@AddColorActivity, "Es necesario agregar el nombre del color.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(colorUpdate == null)
            /**
             * Insert new color
             */
            colorViewModel!!.insert(color = com.lain.colorlistroom.entity.Color(id = 0, name = name, color = color))
            else {
                /**
                 * Update color
                 */
                colorUpdate!!.color = color
                colorUpdate!!.name = name
                colorViewModel!!.update(color = colorUpdate!!)
            }

            finish()
        }


    }

    private fun photoshopColorPicker(){

        val colorPicker = ColorPickerDialog.createColorPickerDialog(this)
        colorPicker.setOnColorPickedListener { color, _ ->
            this.color = color
            DrawableCompat.setTint(colorSelectedIV.background, color)
        }
        colorPicker.show()

    }

}