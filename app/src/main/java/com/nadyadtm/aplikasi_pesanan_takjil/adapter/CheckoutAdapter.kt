package com.nadyadtm.aplikasi_pesanan_takjil.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nadyadtm.aplikasi_pesanan_takjil.model.Food
import com.nadyadtm.aplikasi_pesanan_takjil.R
import com.nadyadtm.aplikasi_pesanan_takjil.utils.ConversionRupiah
import kotlinx.android.synthetic.main.item_order_list.view.*
import kotlin.collections.ArrayList

class CheckoutAdapter (private val list: ArrayList<Food>): RecyclerView.Adapter<CheckoutAdapter.ViewHolder>(){

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        contextAdapter = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order_list,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(food: Food) {

            itemView.tv_price.text=ConversionRupiah(food.price).getConversion()
            itemView.tv_name.text=food.name
            itemView.tv_quantity.text=food.quantity.toString()

        }
    }

}
