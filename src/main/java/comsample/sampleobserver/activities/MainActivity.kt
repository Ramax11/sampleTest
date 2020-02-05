package comsample.sampleobserver.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comsample.sampleobserver.MyApp
import comsample.sampleobserver.R
import comsample.sampleobserver.adapters.MyCartListAdapter
import comsample.sampleobserver.adapters.ProductListAdapter
import comsample.sampleobserver.fragments.ProductFragment
import comsample.sampleobserver.models.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_root, ProductFragment())
        transaction.commit()

    }



}
