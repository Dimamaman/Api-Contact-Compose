package uz.gita.dima.contactappwithclean.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllContacts(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String
): Parcelable
