package com.example.composerest.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "user")
data class User (
    val name: String,
    val lasName: String,
    val city: String,
    val thumbnail: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inset(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC ")
    fun getAll() : LiveData<List<User>>

    @Delete
    fun delete(user: User)


}