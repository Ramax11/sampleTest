package comsample.sampleobserver.managers

import android.app.Application
import comsample.sampleobserver.models.Product

class MyCartListManager {

    enum class CartType {
        ADD, UPDATE, DELETE
    }

    private var cartList: ArrayList<Product> = arrayListOf()

    companion object {
        public var instance: MyCartListManager = MyCartListManager()
            private set
    }

    fun changeCartList(product: Product, cartType: CartType) {
        when (cartType) {
            CartType.ADD -> {
                addToCartList(product)
            }
            CartType.UPDATE -> {
                updateCartList(product)
            }
            CartType.DELETE -> {
                removeProductFromCartList(product)
            }
        }

    }

    fun getCartList() : ArrayList<Product> {
        return cartList
    }

    private fun addToCartList(product: Product) {
        cartList.add(product)
    }

    private fun updateCartList(product: Product) {
        cartList.forEach { _product ->
            if (_product.id == product.id) {
                _product.count = product.count
                return@forEach
            }
        }
    }

    private fun removeProductFromCartList(product: Product){
        val iterator = cartList.iterator()
        while (iterator.hasNext()){
            val item = iterator.next()
            if (item.id == product.id) {
                iterator.remove()
            }
        }
    }

    fun getItemCount(id: Int) : Int{
        cartList.forEach { _product->
            if (_product.id == id){
                return _product.count
            }
        }
        return 0
    }

    interface MyCartItemListener{
        fun onItemChanged()
    }

}