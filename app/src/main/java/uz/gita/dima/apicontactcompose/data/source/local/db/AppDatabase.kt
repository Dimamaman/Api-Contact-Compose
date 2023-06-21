package uz.gita.dima.apicontactcompose.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.dima.apicontactcompose.data.source.local.dao.ContactDao
import uz.gita.dima.apicontactcompose.data.source.local.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao

}