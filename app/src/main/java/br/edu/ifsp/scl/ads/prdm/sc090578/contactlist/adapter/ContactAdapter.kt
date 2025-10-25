package br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.R
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.databinding.TileContactBinding
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model.Contact

class ContactAdapter(
    context: Context,
    private val contactList: MutableList<Contact>
): ArrayAdapter<Contact>(
    context,
    R.layout.tile_contact,
    contactList
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Recuperar o contato que será usado para preencher os dados da célula
        val contact = contactList[position]

        // Verificar se existe uma célula reciclada ou se é necessário inflar uma nova célula
        var contactTileView = convertView
        if (contactTileView == null) {
            // Inflar nova célula
            TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            ).apply {
                contactTileView = root
                val tileContactViewHolder = TileContactViewHolder(nameTv, emailTv)
                contactTileView.tag = tileContactViewHolder
            }
        }

        // Preencher a célula com os dados do contato
        val tlViewHolder = contactTileView?.tag as TileContactViewHolder
        tlViewHolder.nameTv.text = contact.name
        tlViewHolder.emailTv.text = contact.email

        // Retornar a view preenchida
        return contactTileView
    }

    private data class TileContactViewHolder(val nameTv: TextView, val emailTv: TextView)
}