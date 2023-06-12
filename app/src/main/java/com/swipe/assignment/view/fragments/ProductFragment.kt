package com.swipe.assignment.view.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.swipe.assignment.R
import com.swipe.assignment.databinding.FragmentProductBinding
import com.swipe.assignment.view.adapter.ProductsListAdapter
import com.swipe.assignment.viewmodel.RetrofitViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductFragment : Fragment() {

    private val retrofitViewModel: RetrofitViewModel by viewModel()
    private var mBinding: FragmentProductBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // if we want to use the option menu in fragment we need to add it.
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProductBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(mBinding!!.toolbar.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            (activity as AppCompatActivity).setTitle("Product And Services")
        }

        mBinding!!.fbAddProduct.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            val fragment = AddProductFragment()
            fm.beginTransaction()
                .replace(R.id.main_contenier, fragment)
                .addToBackStack("Later Transaction").commit()
        }

        mBinding!!.rvMyProductItems.layoutManager = LinearLayoutManager(requireContext())
        val productsListAdapter = ProductsListAdapter(this)
        mBinding!!.rvMyProductItems.adapter = productsListAdapter

        retrofitViewModel.getProductFromAPI()

        retrofitViewModel.allProductList.observeForever {
            if (it.isNotEmpty()) {
                mBinding!!.progressCircular.visibility = View.GONE
                productsListAdapter.postList(it)
            } else {
                mBinding!!.progressCircular.visibility = View.VISIBLE
            }
        }

    }

    // option for add product
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_product_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_product -> {
                setHasOptionsMenu(false)
//                val fm: FragmentManager = requireActivity().supportFragmentManager
//                val fragment = AddProductFragment()
//                fm.beginTransaction()
//                    .replace(R.id.main_contenier, fragment)
//                    .addToBackStack("Later Transaction").commit()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}