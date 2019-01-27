package com.chin.alb.juv.ui.address

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.chin.presentation.address.AddressPresenter
import com.chin.presentation.address.AddressView
import com.chin.alb.juv.R
import com.chin.alb.juv.di.address.AddressModule
import com.chin.alb.juv.ui.BaseActivity
import com.chin.alb.juv.ui.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_address.*
import javax.inject.Inject

class AddressDialogFragment: BaseDialogFragment(), AddressView {

    @Inject
    lateinit var presenter: AddressPresenter

    private lateinit var lastAddress: String
    private lateinit var listener: (address: String) -> Unit

    companion object {
        val TAG: String = AddressDialogFragment::class.java.simpleName

        fun getInstance(lastAddress: String, listener: (address: String) -> Unit): AddressDialogFragment {
            val fragment = AddressDialogFragment()
            fragment.listener = listener
            fragment.lastAddress = lastAddress
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize(this)
        setupView()
    }

    override fun dependencyInjection() {
        (activity as BaseActivity).activityComponent
            .addressComponentBuilder()
            .addressModule(AddressModule())
            .build()
            .inject(this)

    }

    override fun setupAddresses(list: List<String>) {
        autoAddress.setAdapter(
            ArrayAdapter<String>(activity, R.layout.dropdown_item, list)
        )
    }

    override fun getViewLayout(): Int = R.layout.dialog_address

    private fun setupView() {

        btnAccept.setOnClickListener {
            presenter.onAddressSelected(autoAddress.text.toString())
            listener.invoke(autoAddress.text.toString())
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

    }
}