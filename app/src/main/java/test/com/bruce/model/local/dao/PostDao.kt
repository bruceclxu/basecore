package test.com.bruce.model.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

/**
 * @author bruce
 *	@desc PostDao
 *
 */
@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetAll(postinfo: List<PostEntity>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserttPost(postinfo: PostEntity)

    @Query("SELECT * FROM postentity")
    fun getPostInfo(): Single<PostEntity>

}