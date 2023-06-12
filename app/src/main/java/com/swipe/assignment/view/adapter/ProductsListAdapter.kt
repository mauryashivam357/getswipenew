package com.swipe.assignment.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swipe.assignment.R
import com.swipe.assignment.databinding.ItemProductLayoutBinding
import com.swipe.assignment.model.ProductItem

class ProductsListAdapter(
    private val fragment: Fragment
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    private var list: List<ProductItem> = listOf()

    class ViewHolder(itemView: ItemProductLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        val tvProductName = itemView.tvProductName
        val tvProductType = itemView.tvProductType
        val tvProductPrice = itemView.tvPrice
        val tvGST = itemView.tvGst
        val ivProduct = itemView.imageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemProductLayoutBinding =
            ItemProductLayoutBinding.inflate(
                LayoutInflater.from(fragment.context), parent, false
            )

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvProductName.text = item.product_name
        holder.tvProductType.text = item.product_type
        holder.tvProductPrice.text = "₹ ${item.price.toInt()}"
        holder.tvGST.text = "${item.tax} % GST"
        Glide
            .with(fragment)
            .load(item.image)
            .centerCrop()
            .placeholder(R.drawable.ic_product_placeholder)
            .into(holder.ivProduct)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun postList(list: List<ProductItem>) {
        this.list = list
        notifyDataSetChanged()
    }
}