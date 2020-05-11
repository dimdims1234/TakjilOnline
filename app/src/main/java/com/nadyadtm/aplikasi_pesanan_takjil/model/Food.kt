package com.nadyadtm.aplikasi_pesanan_takjil.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Food(
    var name: String?="",
    var desc: String?="",
    var photo: String?="",
    var price: Int=0,
    var quantity: Int=0
): Serializable {
    fun addQuantity(){
        this.quantity=this.quantity+1
    }
    fun minQuantity(){
        if (this.quantity>0){
            this.quantity=this.quantity-1
        }
    }

    fun getSubtotal():Int{
        return this.quantity*price
    }
}