package com.jakmos.itemistevolved.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.domain.model.project.None
import com.jakmos.itemistevolved.domain.model.project.State
import com.jakmos.itemistevolved.domain.model.Item
import com.jakmos.itemistevolved.domain.useCase.InsertChecklistUseCase
import timber.log.Timber

class AddViewModel(
    private val insertChecklistUseCase: InsertChecklistUseCase
) : ViewModel() {

    val state = MutableLiveData<State<None>>().apply {
        this.value = State.Loading()
    }

    init {
        //TODO temporary add
        val items = listOf(
            Item("item1", false),
            Item("item2", false),
            Item("item3", false)
        )

        val checklist1 = Checklist(1, "Name1", "url1", lines = items)
        val checklist2 = Checklist(2, "Name2", "url2", lines = items)
        val checklist3 = Checklist(3, "Name3", "url3", lines = items)

        addChecklist(checklist1)
        addChecklist(checklist2)
        addChecklist(checklist3)
    }

    private fun addChecklist(checklist: Checklist) {
        insertChecklistUseCase.execute(viewModelScope, checklist) {
            it.either(::handleFailure, ::handleSuccessLoad)
        }
    }

    private fun handleSuccessLoad(ignore: Unit) {
        Timber.tag("KUBA").v("handleSuccessLoad $ignore")
    }

    private fun handleFailure(error: Exception) {
        state.value = State.Error(error)
    }
}
