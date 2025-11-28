package br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.controller

import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model.Contact
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model.ContactDao
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model.ContactSqlite
import br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.ui.MainActivity

class MainController(mainActivity: MainActivity) {
    private val contactDao: ContactDao = ContactSqlite(mainActivity)

    fun insertContact(contact: Contact) = contactDao.createContact(contact)
    fun getContact(id: Int) = contactDao.retrieveContact(id)
    fun getContacts() = contactDao.retrieveContacts()
    fun modifyContact(contact: Contact) = contactDao.updateContact(contact)
    fun removeContact(id: Int) = contactDao.deleteContact(id)
}