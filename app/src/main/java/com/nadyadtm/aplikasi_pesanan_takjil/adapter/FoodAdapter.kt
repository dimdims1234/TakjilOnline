package com.nadyadtm.aplikasi_pesanan_takjil.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nadyadtm.aplikasi_pesanan_takjil.model.Food
import com.nadyadtm.aplikasi_pesanan_takjil.R
import com.nadyadtm.aplikasi_pesanan_takjil.utils.ConversionRupiah
import kotlinx.android.synthetic.main.item_food.view.*
import kotlin.collections.ArrayList

class FoodAdapter (private val list: ArrayList<Food>): RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        contextAdapter = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.b_add.setOnClickListener {
            list[position].addQuantity()
            holder.itemView.tv_number.text=list[position].quantity.toString()
            if(list[position].quantity!=0){
                holder.itemView.b_min.visibility= View.VISIBLE
                holder.itemView.tv_number.visibility=View.VISIBLE
            }
            notifyDataSetChanged()
        }

        holder.itemView.b_min.setOnClickListener {
            list[position].minQuantity()
            holder.itemView.tv_number.text=list[position].quantity.toString()
            if(list[position].quantity<1){
                holder.itemView.b_min.visibility= View.GONE
                holder.itemView.tv_number.visibility=View.GONE
            }
            notifyDataSetChanged()
        }
        holder.onBind(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(food: Food) {

            itemView.tv_food_price.text=ConversionRupiah(food.price).getConversion()
            itemView.tv_food_desc.text=food.desc
            itemView.tv_food_name.text=food.name

            Glide.with(contextAdapter)
                .load(food.photo)
                .into(itemView.iv_foodpic)
        }
    }

}
