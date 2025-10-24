package br.edu.ifsp.scl.ads.prdm.sc090578.contactlist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.Constant.EXTRA_CONTACT
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Data source
    private val contactList: MutableList<Contact> = mutableListOf()

    // Adapter
    private val contactAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            contactList.map {
                "${it.name} - ${it.phone}"
            }
        )
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.list)

        fillContactList()

        amb.contactLv.adapter = contactAdapter

        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val contact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)  {
                    result.data?.getParcelableExtra(EXTRA_CONTACT, Contact::class.java)
                }
                else {
                    result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
                }
                contact?.let{
                    contactList.add(it)
                    contactAdapter.add("${contact.name} - ${contact.phone}")
                    contactAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_contact_mi -> {
                carl.launch(Intent(this, ContactActivity::class.java))
                true
            }
            else -> { false }
        }
    }

    private fun fillContactList() {
        for (i in 1..10) {
            contactList.add(
                Contact(
                    i,
                    "Name $i",
                    "Address $i",
                    "Phone $i",
                    "Email $i"
                )
            )
        }
    }
}