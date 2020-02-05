package comsample.sampleobserver.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comsample.sampleobserver.R
import comsample.sampleobserver.adapters.MyCartListAdapter
import comsample.sampleobserver.managers.MyCartListManager
import comsample.sampleobserver.models.Product
import kotlinx.android.synthetic.main.activity_my_cart.view.*

class MyCartActivity(private val itemChangeListener: MyCartListManager.MyCartItemListener) : Fragment() {

    private var productList: ArrayList<Product> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.activity_my_cart, container, false)

        productList = MyCartListManager.instance.getCartList()
        context?.let {  _context->
            val layoutManager = LinearLayoutManager(_context, RecyclerView.VERTICAL, false)
            val adapter = MyCartListAdapter(_context, productList, itemChangeListener)
            view.recycler_view_cart_items.layoutManager = layoutManager
            view.recycler_view_cart_items.adapter = adapter
        }

        return view
    }




}
