package com.nadyadtm.aplikasi_pesanan_takjil.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadyadtm.aplikasi_pesanan_takjil.model.Food
import com.nadyadtm.aplikasi_pesanan_takjil.R
import com.nadyadtm.aplikasi_pesanan_takjil.adapter.CheckoutAdapter
import com.nadyadtm.aplikasi_pesanan_takjil.utils.ConversionRupiah
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.button_okay.*

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title="Checkout"

        val dataList = intent.getSerializableExtra("data") as ArrayList<Food>
        val total = intent.getIntExtra("total",0)
        rv_item_list.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_item_list.adapter= CheckoutAdapter(dataList)

        tv_total_final.text=ConversionRupiah(total).getConversion()

        b_start_order.setOnClickListener {
            val intent = Intent(this, SuccessOrderActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}
