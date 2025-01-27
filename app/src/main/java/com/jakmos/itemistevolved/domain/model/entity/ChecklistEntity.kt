package com.jakmos.itemistevolved.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jakmos.itemistevolved.data.converter.DateConverter
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.Item
import java.util.*

@Entity(tableName = "checklist")
data class ChecklistEntity(
    val name: String,
    val image: String,

    @TypeConverters(DateConverter::class)
    val createdAt: Date = Date(),

    @TypeConverters(DateConverter::class)
    val updatedAt: Date = Date(),

    val lines: List<Item>,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) {
    companion object {
        fun entityToModel(entity: ChecklistEntity) =
            Checklist(entity.id, entity.name, entity.image, entity.lines, entity.createdAt)
    }
}