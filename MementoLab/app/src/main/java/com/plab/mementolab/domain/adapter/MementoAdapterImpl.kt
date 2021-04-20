package com.plab.mementolab.domain.adapter

import com.plab.mementolab.domain.adapter.MementoAdapter.Companion.SortType
import com.plab.mementolab.domain.mapper.shuffled
import com.plab.mementolab.domain.mapper.sortByOrdinal
import com.plab.mementolab.domain.model.Memento
import com.plab.mementolab.domain.repository.MementoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Timmerman_Lyderic on 14/03/2021.
 */

class MementoAdapterImpl(
    private val repository: MementoRepository
) : MementoAdapter {

    private val mementoFlow = repository.getMementoFlow()

    override fun getMementoFlow(
        sortedBy: SortType,
        showNonPlayable: Boolean
    ): Flow<List<Memento>> {
        return when (sortedBy) {
            SortType.ORDINAL -> mementoFlow.sortByOrdinal()
            SortType.RANDOM -> mementoFlow.shuffled()
        }.filterPlayable(showNonPlayable)
    }

    override suspend fun getMementos(sortedBy: SortType, showNonPlayable: Boolean) =
        when (sortedBy) {
            SortType.RANDOM -> repository.getMementos().shuffled()
            SortType.ORDINAL -> repository.getMementos().sortByOrdinal()
        }.filterPlayable(showNonPlayable)

    override suspend fun addMemento(memory: String, image: String) {
        repository.getMementos()
            .find { it.memory == memory }
            .let { memento ->
                repository.insertImage(
                    mementoId = memento?.id ?: repository.insertMemory(memory),
                    imageName = image,
                    isPlayable = isImagePlayable(memory)
                )
            }
    }

    override suspend fun togglePlayableForId(imageId: Long) {
        repository.getImages()
            .find { it.id == imageId }
            ?.let { image ->
                repository.update(
                    mementoId = image.id,
                    isPlayable = image.isPlayable.not()
                )
            }
    }

    override suspend fun delete(imageId: Long) = repository.deleteMemento(imageId = imageId)

    private fun Flow<List<Memento>>.filterPlayable(showNonPlayable: Boolean) =
        map { list -> list.filterPlayable(showNonPlayable) }

    private fun List<Memento>.filterPlayable(showNonPlayable: Boolean) =
        filter { showNonPlayable || it.image.isPlayable }

    private fun isImagePlayable(memory: String) =
        try {
            memory.toInt() < 100
        } catch (e: NumberFormatException) {
            false
        }
}