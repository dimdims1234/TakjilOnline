package com.nadyadtm.aplikasi_pesanan_takjil.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nadyadtm.aplikasi_pesanan_takjil.R
import com.nadyadtm.aplikasi_pesanan_takjil.activity.CheckoutActivity
import com.nadyadtm.aplikasi_pesanan_takjil.model.Food
import com.nadyadtm.aplikasi_pesanan_takjil.utils.ConversionRupiah
import kotlinx.android.synthetic.main.fragment_total_price.view.*

/**
 * A simple [Fragment] subclass.
 */
class TotalPriceFragment(var total: Int,var foodOrdered:ArrayList<Food>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_total_price, container, false)
        view.let {
            it.b_checkout.setOnClickListener {
                val intent = Intent(this.context,
                    CheckoutActivity::class.java)
                intent.putExtra("data",foodOrdered)
                intent.putExtra("total",total)
                startActivity(intent)
            }
            it.tv_total_price.text=ConversionRupiah(total).getConversion()
        }
        return view
    }


}
