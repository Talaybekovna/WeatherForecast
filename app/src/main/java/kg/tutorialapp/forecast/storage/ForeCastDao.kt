package kg.tutorialapp.forecast.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kg.tutorialapp.forecast.models.ForeCast

@Dao
interface ForeCastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(foreCast: ForeCast)

    @Update
    fun update(foreCast: ForeCast): Completable

    @Delete
    fun delete(foreCast: ForeCast): Completable

    @Query("select * from ForeCast")
    fun getAll(): LiveData<ForeCast>

    @Query("select * from ForeCast where id = :id")
    fun getById(id: Long): Single<ForeCast>

    @Query("delete from ForeCast")
    fun deleteAll(): Completable
}