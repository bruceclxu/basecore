package test.com.bruce.model.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author bruce
 *	@desc 创建Post的Entity
 *
 */
@Entity
data class PostEntity(
    val message: String,
    @PrimaryKey
    val nu: String,
    val ischeck: String,
    val condition: String,
    val com: String,
    val status: String,
    val state: String
)