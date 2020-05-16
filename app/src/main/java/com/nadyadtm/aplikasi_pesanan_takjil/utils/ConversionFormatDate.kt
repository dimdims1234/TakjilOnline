package com.nadyadtm.aplikasi_pesanan_takjil.utils

import java.text.SimpleDateFormat
import java.util.*

class ConversionFormatDate (var year : Int, var month : Int, var day : Int){
    fun getDate():String{
        var calendar = Calendar.getInstance()
        calendar.set(year,month,day)
        var formatter = SimpleDateFormat("MMMM dd, YYYY")
        return formatter.format(calendar.time)
    }
}