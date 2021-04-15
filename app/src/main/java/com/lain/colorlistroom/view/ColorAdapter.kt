package com.lain.colorlistroom.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.lain.colorlistroom.R
import com.lain.colorlistroom.entity.Color


class ColorAdapter (
        private val colors: MutableList<Color>,
        private val onDelete: (Color) -> Unit,
        private val onClick : (Color) -> Unit
): RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    //View holder
    class ColorViewHolder(
            private val colorItem: View,
            private val onDelete: (Color) -> Unit,
            private val onClick: (Color) -> Unit) : RecyclerView.ViewHolder(colorItem){

        private val idTv = colorItem.findViewById<TextView>(R.id.idTV)
        private val colorNameTV = colorItem.findViewById<TextView>(R.id.colorNameTV)
        private val colorPreviewIV = colorItem.findViewById<ImageView>(R.id.colorPreviewIV)
        private var currentColor: Color ?= null

        init {

            itemView.setOnLongClickListener {

                currentColor?.let {
                    val popupMenu = PopupMenu(colorItem.context, colorItem)
                    popupMenu.inflate(R.menu.color_item_menu)
                    popupMenu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.deleteColor -> {
                                onDelete(currentColor!!)
                                return@setOnMenuItemClickListener  true
                            }
                            else -> {
                                return@setOnMenuItemClickListener  false
                            }
                        }
                    }
                    popupMenu.show()
                }

                return@setOnLongClickListener true
            }

            itemView.setOnClickListener {
                currentColor?.let {
                    onClick(it)
                }
            }
        }

        /**
         * Bind color
         */
        fun bind(color: Color){
            currentColor = color

            idTv.text = color.id.toString()
            colorNameTV.text = color.name
            DrawableCompat.setTint(colorPreviewIV.background, color.color)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ColorViewHolder = ColorViewHolder(
            colorItem = LayoutInflater.from(viewGroup.context).inflate(R.layout.color_item, viewGroup, false),
            onDelete = onDelete,
            onClick = onClick
    )


    override fun onBindViewHolder(holder: ColorViewHolder, position: Int)  = holder.bind(color = colors[position])

    override fun getItemCount(): Int = colors.size

    fun addAll(colors: List<Color>){
        this.colors.addAll(colors)
        notifyDataSetChanged()
    }

    fun clear() {
        colors.clear()
        notifyDataSetChanged()
    }
}