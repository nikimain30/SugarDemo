package com.sugar.test.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sugar.test.R
import com.sugar.test.data.model.Product


class ProductAdapter(private val context: Context, private var filterList: ArrayList<Product>?, private var clickListner: OnClickListener)
    : RecyclerView.Adapter<ProductAdapter.FilterViewHolder>() {

    interface OnClickListener{
        fun onClickListner(filter: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.FilterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
        return FilterViewHolder(v,clickListner)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ProductAdapter.FilterViewHolder, position: Int) {
        holder.bindItems(filterList!![position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return filterList!!.size
    }

    //the class is hodling the list view
    class FilterViewHolder(itemView: View, private  val clickListner: OnClickListener) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(filterList: Product) {
            val cardView = itemView.findViewById(R.id.card_view) as CardView
            val textViewName = itemView.findViewById(R.id.tvTitle) as TextView
            val imageFilter  = itemView.findViewById(R.id.imgTitle) as ImageView
            textViewName.text = filterList.title
//            Glide.with(imageFilter.context)
//                .load(filterList.images!!.get(0))
//                .placeholder(R.drawable.banner1)
//                .error(R.drawable.banner2)
//                .into(imageFilter)

            Picasso
                .with(imageFilter.context) // give it the context
                .load(filterList.images!!.toString())
                .placeholder(R.drawable.banner1)// load the image
                .into(imageFilter)

            cardView.setOnClickListener {
                clickListner.onClickListner(filterList)
            }

        }
    }

    fun setProducts(products: ArrayList<Product>) {
        this.filterList = products
        notifyDataSetChanged()
    }
}