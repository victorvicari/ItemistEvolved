package com.jakmos.itemistevolved.domain.useCase

import com.jakmos.itemistevolved.data.db.ChecklistDao
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity
import com.jakmos.itemistevolved.domain.model.project.DateTimeInterface
import com.jakmos.itemistevolved.domain.model.project.None

class InsertChecklistUseCase(
    private val dateTime: DateTimeInterface,
    private val dao: ChecklistDao,
    private val getChecklistsUseCase: GetChecklistsUseCase
) : UseCase<Checklist, List<Checklist>>() {

    override suspend fun doWork(param: Checklist): List<Checklist> {
        dao.insert(createEntity(param))

        return getChecklistsUseCase.doWork(None)
    }

    private fun createEntity(param: Checklist): ChecklistEntity =
        ChecklistEntity(
            param.name,
            param.image,
            param.createdAt ?: dateTime.date,
            dateTime.date,
            param.lines,
            param.id
        )
}
