package comsample.sampleobserver.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import comsample.sampleobserver.R
import comsample.sampleobserver.activities.MyCartActivity
import comsample.sampleobserver.adapters.ProductListAdapter
import comsample.sampleobserver.managers.MyCartListManager
import comsample.sampleobserver.models.Product
import kotlinx.android.synthetic.main.fragment_product.view.*

class ProductFragment : Fragment() {

    private lateinit var adapter: ProductListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        val productList = getProductList()
        context?.let { _context->
            val layoutManager = LinearLayoutManager(_context, RecyclerView.VERTICAL, false)
            adapter = ProductListAdapter(_context, productList)
            view.recycler_view_products.layoutManager = layoutManager
            view.recycler_view_products.adapter = adapter
        }
        view.btn_next.setOnClickListener {
            val transaction =  activity?.supportFragmentManager?.beginTransaction()
            transaction?.add(R.id.container_root, MyCartActivity(itemChangeListener))
            transaction?.addToBackStack(MyCartActivity::class.java.name)
            transaction?.commitAllowingStateLoss()
        }

        return view
    }

    private val itemChangeListener = object : MyCartListManager.MyCartItemListener{
        override fun onItemChanged() {
            adapter.notifyDataSetChanged()
        }
    }

    private fun getProductList() : ArrayList<Product> {
        val productList: ArrayList<Product> = arrayListOf()
        productList.add(Product(id = 1, name = "Chicken briyani", description = "It contains 6 pieces of chicken", count = 0))
        productList.add(Product(id = 2, name = "Duck briyani", description = "It contains 1/2 kg of duck", count = 0))
        productList.add(Product(id = 3, name = "Mutton briyani", description = "It contains 6 pieces of chicken", count = 0))
        productList.add(Product(id = 4, name = "Beef briyani", description = "It contains 10 pieces of beef", count = 0))
        productList.add(Product(id = 5, name = "Veg briyani", description = "It contains 6 pieces of chicken", count = 0))
        productList.add(Product(id = 6, name = "Chicken Sukka", description = "Winner winner chicken dinner", count = 0))
        productList.add(Product(id = 7, name = "Mutton sukka", description = "It contains 4 pieces of Mutton", count = 0))
        productList.add(Product(id = 8, name = "Chicken salad", description = "Winner winner chicken dinner", count = 0))
        productList.add(Product(id = 9, name = "Veg fried rice", description = "Veg rolls and mushroom pieces", count = 0))
        productList.add(Product(id = 10, name = "Chicken mixed rice", description = "Mixed with veg", count = 0))
        productList.add(Product(id = 11, name = "Beef mushroom", description = "Winner winner chicken dinner", count = 0))
        return productList
    }

}
