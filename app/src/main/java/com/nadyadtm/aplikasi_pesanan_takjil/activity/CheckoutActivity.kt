package com.nadyadtm.aplikasi_pesanan_takjil.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadyadtm.aplikasi_pesanan_takjil.model.Food
import com.nadyadtm.aplikasi_pesanan_takjil.R
import com.nadyadtm.aplikasi_pesanan_takjil.adapter.CheckoutAdapter
import com.nadyadtm.aplikasi_pesanan_takjil.utils.ConversionFormatDate
import com.nadyadtm.aplikasi_pesanan_takjil.utils.ConversionRupiah
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.button_okay.*
import java.sql.Time
import java.time.Clock
import java.util.*
import kotlin.collections.ArrayList

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

        b_change_date.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    tv_date.text= ConversionFormatDate(year,monthOfYear,dayOfMonth).getDate()
                }, year, month, day
            )
            datePickerDialog.show()
        }

        b_change_time.setOnClickListener {
            val cl = Calendar.getInstance()
            val hours = cl.get(Calendar.HOUR)
            val minute = cl.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { view, hoursofday, minutes ->
                    tv_time.text = hoursofday.toString() + ":" + minute.toString()
                },hours,minute,false
            )
            timePickerDialog.show()
        }
    }
}
