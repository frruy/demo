package com.example.exampleest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleest.R
import com.example.exampleest.data.model.City
import kotlinx.android.synthetic.main.item_city.view.*

class CitiesAdapter(private val cities: ArrayList<City>) :
    RecyclerView.Adapter<CitiesAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: City) {
            itemView.tvCityTitle.text = city.title
            itemView.tvCityLattLong.text = city.lattLong
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_city, parent,
                false
            )
        )

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(cities[position])

    fun addData(list: List<City>) {
        cities.addAll(list)
    }
}