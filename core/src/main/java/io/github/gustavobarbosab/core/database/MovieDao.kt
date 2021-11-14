package io.github.gustavobarbosab.core.database

import androidx.room.*
import io.github.gustavobarbosab.core.database.dto.MovieDto

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAll(): List<MovieDto>

    @Query("SELECT * FROM Movie WHERE favorite ORDER BY savedDate")
    fun getFavorites(): List<MovieDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg movieDto: MovieDto)

    @Query("UPDATE Movie SET favorite = 'false' WHERE id = :id")
    fun unlikeMovie(id: Long)
}