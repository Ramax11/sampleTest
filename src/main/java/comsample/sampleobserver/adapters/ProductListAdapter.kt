package comsample.sampleobserver.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import comsample.sampleobserver.R
import comsample.sampleobserver.adapters.ProductListAdapter.ProductViewHolder
import comsample.sampleobserver.managers.MyCartListManager
import comsample.sampleobserver.models.Product
import kotlinx.android.synthetic.main.row_product_list.view.*

class ProductListAdapter(private val context: Context, private val productList: ArrayList<Product>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.row_product_list, parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = productList[position]
        product.count = MyCartListManager.instance.getItemCount(product.id)

        holder.itemView.item_name.text = product.name
        holder.itemView.item_description.text = product.description
        holder.itemView.tv_count.text = product.count.toString()

        if (product.count == 0){
            holder.itemView.btn_add.visibility = View.VISIBLE
            holder.itemView.img_btn_increase.visibility = View.GONE
            holder.itemView.img_btn_decrease.visibility = View.GONE
            holder.itemView.tv_count.visibility = View.GONE
        } else {
            holder.itemView.btn_add.visibility = View.GONE
            holder.itemView.img_btn_increase.visibility = View.VISIBLE
            holder.itemView.img_btn_decrease.visibility = View.VISIBLE
            holder.itemView.tv_count.visibility = View.VISIBLE
        }

        holder.itemView.btn_add.setOnClickListener {
            holder.itemView.btn_add.visibility = View.GONE
            holder.itemView.img_btn_increase.visibility = View.VISIBLE
            holder.itemView.img_btn_decrease.visibility = View.VISIBLE
            holder.itemView.tv_count.visibility = View.VISIBLE

            holder.itemView.tv_count.text = holder.itemView.tv_count.text.toString().toInt().plus(1).toString()
            product.count = holder.itemView.tv_count.text.toString().toInt()
            MyCartListManager.instance.changeCartList(product, MyCartListManager.CartType.ADD)
        }

        holder.itemView.img_btn_increase.setOnClickListener {
            if (holder.itemView.tv_count.text.toString().toInt() >= 0) {
                holder.itemView.tv_count.text = holder.itemView.tv_count.text.toString().toInt().plus(1).toString()
                product.count = holder.itemView.tv_count.text.toString().toInt()
                MyCartListManager.instance.changeCartList(product, MyCartListManager.CartType.UPDATE)
            }
        }

        holder.itemView.img_btn_decrease.setOnClickListener {
            if (holder.itemView.tv_count.text.toString().toInt() > 0) {
                holder.itemView.tv_count.text = holder.itemView.tv_count.text.toString().toInt().minus(1).toString()
                product.count = holder.itemView.tv_count.text.toString().toInt()
                if (holder.itemView.tv_count.text.toString().toInt() == 0){
                    MyCartListManager.instance.changeCartList(product, MyCartListManager.CartType.DELETE)
                    holder.itemView.btn_add.visibility = View.VISIBLE
                    holder.itemView.img_btn_increase.visibility = View.GONE
                    holder.itemView.img_btn_decrease.visibility = View.GONE
                    holder.itemView.tv_count.visibility = View.GONE
                } else {
                    MyCartListManager.instance.changeCartList(product, MyCartListManager.CartType.UPDATE)
                }
            }
        }

    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}