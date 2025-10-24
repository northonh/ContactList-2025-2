package br.edu.ifsp.scl.ads.prdm.sc090578.contactlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    var id: Int? = -1,
    var name: String = "",
    var address: String = "",
    var phone: String = "",
    var email: String = ""
): Parcelable