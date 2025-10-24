package br.edu.ifsp.scl.ads.prdm.sc090578.contactlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.Constant.EXTRA_CONTACT
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.contact_details)

        with(acb) {
            saveBt.setOnClickListener {
                Contact(
                    hashCode(),
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