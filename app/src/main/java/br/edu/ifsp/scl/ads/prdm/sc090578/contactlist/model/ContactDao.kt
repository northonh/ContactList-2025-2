package br.edu.ifsp.scl.ads.prdm.sc090578.contactlist.model

interface ContactDao {
    fun createContact(contact: Contact): Long
    fun retrieveContact(id: Int): Contact
    fun retrieveContacts(): MutableList<Contact>
    fun updateContact(contact: Contact): Int
    fun deleteContact(id: Int): Int
}