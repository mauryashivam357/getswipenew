package com.swipe.assignment.view.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.swipe.assignment.R
import com.swipe.assignment.databinding.FragmentAddProductBinding
import com.swipe.assignment.model.ProductResponse
import com.swipe.assignment.utils.Constants
import com.swipe.assignment.utils.GlideLoader
import com.swipe.assignment.viewmodel.RetrofitViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException


class AddProductFragment : Fragment(), View.OnClickListener {

    private lateinit var mBinding: FragmentAddProductBinding
    private val retrofitViewModel: RetrofitViewModel by viewModel()
    private var mSelectedFileUriType: Int = 1
    private var mSelectedFileUri: Uri = ("").toUri()
    private val uriList = ArrayList<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBackButton()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentAddProductBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(mBinding.toolbar.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            (activity as AppCompatActivity).title = "Add Product"
        }
        retrofitViewModel.addProduct.observeForever { it ->

            if (it.success) {
                Toast.makeText(
                    requireContext(),
                    "Your Product Added Successfully",
                    Toast.LENGTH_LONG
                ).show()
                mBinding.progressBar.visibility = View.GONE
                mBinding.btnSubmit.isEnabled = true
            } else {
                mBinding.progressBar.visibility = View.GONE
                mBinding.btnSubmit.isEnabled = true
            }
        }
        mBinding.btnSubmit.setOnClickListener {
            mBinding.progressBar.visibility = View.VISIBLE
            mBinding.btnSubmit.isEnabled = false
            addProduct()
        }
        mBinding.imageView1.setOnClickListener(this)
        mBinding.imageView2.setOnClickListener(this)
        mBinding.imageView3.setOnClickListener(this)
        mBinding.imageView4.setOnClickListener(this)
        mBinding.imageView5.setOnClickListener(this)
        mBinding.imageView6.setOnClickListener(this)
        mBinding.imageView7.setOnClickListener(this)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().supportFragmentManager.popBackStack(
            "Later Transaction",
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        return super.onOptionsItemSelected(item)
    }

    private fun showBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun addProduct() {
        val name = mBinding.etProductName.text.toString().trim { it <= ' ' }
        val price = mBinding.etSellingPrice.text.toString().trim { it <= ' ' }
        val taxRate = mBinding.etTaxRate.text.toString().trim { it <= ' ' }
        val type = if (mBinding.radioButtonProduct.isChecked) "Product"
        else if (mBinding.radioButtonService.isChecked) "Service"
        else ""

        when {

            TextUtils.isEmpty(name) -> {
                mBinding.progressBar.visibility = View.GONE
                mBinding.btnSubmit.isEnabled = true
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.err_msg_enter_product_name),
                    Toast.LENGTH_SHORT
                ).show()
            }

            TextUtils.isEmpty(price) -> {
                mBinding.progressBar.visibility = View.GONE
                mBinding.btnSubmit.isEnabled = true
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.err_msg_enter_product_price),
                    Toast.LENGTH_SHORT
                ).show()
            }
            TextUtils.isEmpty(type) -> {
                mBinding.progressBar.visibility = View.GONE
                mBinding.btnSubmit.isEnabled = true
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.err_msg_select_type),
                    Toast.LENGTH_SHORT
                ).show()
            }
            TextUtils.isEmpty(taxRate) -> {
                mBinding.progressBar.visibility = View.GONE
                mBinding.btnSubmit.isEnabled = true
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.err_msg_enter_tex),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val productResponse = ProductResponse(
                    name, type, price, taxRate,
                    uriList.toList()
                )
                retrofitViewModel.addProduct(productResponse)

            }
        }

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Constants.showImageChooser(resultLauncher)
        } else {
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.read_storage_permission_dened), Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

                if (data != null) {
                    try {
                        when (mSelectedFileUriType) {
                            1 -> {
                                mSelectedFileUri = data.data!!
                                uriList.add(File(data.data!!.path!!))
                                GlideLoader(requireContext()).loadUserPicture(
                                    data.data!!,
                                    mBinding.imageView1
                                )
                            }
                            2 -> {
                                uriList.add(File(data.data!!.path!!))
                                GlideLoader(requireContext()).loadUserPicture(
                                    data.data!!,
                                    mBinding.imageView2
                                )
                            }
                            3 -> {
                                uriList.add(File(data.data!!.path!!))
                                GlideLoader(requireContext()).loadUserPicture(
                                    data.data!!,
                                    mBinding.imageView3
                                )
                            }
                            4 -> {
                                uriList.add(File(data.data!!.path!!))
                                GlideLoader(requireContext()).loadUserPicture(
                                    data.data!!,
                                    mBinding.imageView4
                                )
                            }
                            5 -> {
                                uriList.add(File(data.data!!.path!!))
                                GlideLoader(requireContext()).loadUserPicture(
                                    data.data!!,
                                    mBinding.imageView5
                                )
                            }
                            6 -> {
                                uriList.add(File(data.data!!.path!!))
                                GlideLoader(requireContext()).loadUserPicture(
                                    data.data!!,
                                    mBinding.imageView6
                                )
                            }
                            7 -> {
                                uriList.add(File(data.data!!.path!!))
                                GlideLoader(requireContext()).loadUserPicture(
                                    data.data!!,
                                    mBinding.imageView7
                                )
                            }
                        }


                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            requireContext(),
                            resources.getString(R.string.image_selection_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.imageView1 -> {
                    mSelectedFileUriType = 1
                    askPermission()
                }
                R.id.imageView2 -> {
                    mSelectedFileUriType = 2
                    askPermission()
                }

                R.id.imageView3 -> {
                    mSelectedFileUriType = 3
                    askPermission()
                }

                R.id.imageView4 -> {
                    mSelectedFileUriType = 4
                    askPermission()
                }

                R.id.imageView5 -> {
                    mSelectedFileUriType = 5
                    askPermission()
                }
                R.id.imageView6 -> {
                    mSelectedFileUriType = 6
                    askPermission()
                }
                R.id.imageView7 -> {
                    mSelectedFileUriType = 7
                    askPermission()
                }


            }
        }
    }

    private fun askPermission() {
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

}