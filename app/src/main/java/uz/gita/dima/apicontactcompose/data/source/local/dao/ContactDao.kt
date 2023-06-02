package uz.gita.dima.apicontactcompose.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.data.source.local.entity.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(contactEntity: ContactEntity)

    @Delete
    fun delete(contactEntity: ContactEntity)

    @Update
    fun update(contactEntity: ContactEntity)

    @Query("Select * from contacts")
    fun retrieveAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): ContactEntity
}