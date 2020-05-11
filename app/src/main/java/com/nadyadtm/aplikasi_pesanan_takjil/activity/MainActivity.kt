package com.nadyadtm.aplikasi_pesanan_takjil.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.nadyadtm.aplikasi_pesanan_takjil.R
import com.nadyadtm.aplikasi_pesanan_takjil.fragment.TotalPriceFragment
import com.nadyadtm.aplikasi_pesanan_takjil.adapter.FoodAdapter
import com.nadyadtm.aplikasi_pesanan_takjil.model.Food
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Food>()
    lateinit var foodAdapter: FoodAdapter

    var observer : RecyclerView.AdapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            setFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDatabase = FirebaseDatabase.getInstance().getReference("Food")

        rv_food_list.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        getData()
    }

    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()
                for (items in p0.children){
                    var food = items.getValue(Food::class.java)
                    dataList.add(food!!)
                }
                foodAdapter = FoodAdapter(dataList)
                rv_food_list.adapter=foodAdapter
                foodAdapter.registerAdapterDataObserver(observer)
                pb_load.visibility= View.GONE
                rv_food_list.visibility=View.VISIBLE
            }
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext,p0.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun calculateTotal():Int{
        var total = 0
        for (item in dataList){
            total=total+item.getSubtotal()
        }
        return total
    }

    fun orderedList():ArrayList<Food>{
        val foodOrderedList = ArrayList<Food>()
        for (item in dataList){
            if (item.quantity>0){
                foodOrderedList.add(item)
            }
        }
        return foodOrderedList
    }

    fun setFragment(){
        if (calculateTotal()>0){
            this.supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frame_total,
                    TotalPriceFragment(
                        calculateTotal(),
                        orderedList()
                    ),"FRAGMENT_INI")
                .commit()
        }
        else{
            val fragment = this.supportFragmentManager.findFragmentByTag("FRAGMENT_INI")
            if (fragment!=null){
                this.supportFragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit()
            }
        }
    }
}
