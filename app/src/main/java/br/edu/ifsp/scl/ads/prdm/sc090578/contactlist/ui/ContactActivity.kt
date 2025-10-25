package br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.R
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model.Constant.EXTRA_CONTACT
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.databinding.ActivityContactBinding
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model.Constant.EXTRA_VIEW_CONTACT
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model.Contact

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.contact_details)

        val receivedContact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)  {
            intent.getParcelableExtra(EXTRA_CONTACT, Contact::class.java)
        }
        else {
            intent.getParcelableExtra<Contact>(EXTRA_CONTACT)
        }
        receivedContact?.let{
            with(acb) {
                nameEt.setText(it.name)
                addressEt.setText(it.address)
                phoneEt.setText(it.phone)
                emailEt.setText(it.email)

                val viewContact = intent.getBooleanExtra(EXTRA_VIEW_CONTACT, false)
                if (viewContact) {
                    nameEt.isEnabled = false
                    addressEt.isEnabled = false
                    phoneEt.isEnabled = false
                    emailEt.isEnabled = false
                    saveBt.visibility = View.GONE
                }
            }
        }

        with(acb) {
            saveBt.setOnClickListener {
                Contact(
                    receivedContact?.id?:hashCode(),
                    nameEt.text.toString(),
                    addressEt.text.toString(),
                    phoneEt.text.toString(),
                    emailEt.text.toString()
                ).let{ contact ->
                    Intent().putExtra(EXTRA_CONTACT, contact).apply {
                        setResult(RESULT_OK, this)
                        finish()
                    }
                }
            }
        }
    }
}