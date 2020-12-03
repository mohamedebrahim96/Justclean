/**
 * Created by @mohamedebrahim96 on 03,December,2020.
 * ebrahimm131@gmail.com,
 * Dubai, UAE.
 */
package com.justclean.task.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justclean.task.model.PostComment

@Dao
interface PostCommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfo: PostComment)

    @Query("SELECT * FROM PostComment WHERE name = :name_")
    suspend fun getPokemonInfo(name_: String): PostComment?
}
