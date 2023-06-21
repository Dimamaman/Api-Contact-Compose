package uz.gita.dima.apicontactcompose.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fname: String,
    val lname: String,
    val phone: String,
    val isSavedToServer:Int = 0
) {
    fun toData() = ContactData(
        id, fname, lname, phone
    )
}