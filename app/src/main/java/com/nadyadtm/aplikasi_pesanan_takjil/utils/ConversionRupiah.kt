package com.nadyadtm.aplikasi_pesanan_takjil.utils

import java.text.NumberFormat
import java.util.*

class ConversionRupiah(var money: Int){
    fun getConversion():String{
        val localeID = Locale("in","ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        val rupiah = formatRupiah.format(money.toDouble())
        return rupiah
    }
}