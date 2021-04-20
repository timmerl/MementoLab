package com.plab.mementolab.data.database.repository

import com.plab.mementolab.data.database.dao.MementoDao
import com.plab.mementolab.data.database.entity.ImageEntity
import com.plab.mementolab.data.database.entity.MemoryEntity
import com.plab.mementolab.domain.mapper.mapToModel
import com.plab.mementolab.domain.mapper.toModel
import com.plab.mementolab.domain.model.Image
import com.plab.mementolab.domain.model.Memento
import com.plab.mementolab.domain.repository.MementoRepository

/**
 * Created by Timmerman_Lyderic on 28/02/2021.
 */

class MementoRepositoryImpl(private val dao: MementoDao) : MementoRepository {

    override fun getMementoFlow() =
        dao.getMementoFlow().toModel()

    override suspend fun getMementos(): List<Memento> =
        dao.getMementos().mapToModel()

    override suspend fun insertMemory(memory: String) =
        dao.insert(MemoryEntity(name = memory))

    override suspend fun getImages(): List<Image> {
        return dao.getImages().toModel()
    }

    override suspend fun insertImage(mementoId: Long, imageName: String, isPlayable: Boolean) {
        dao.insert(
            ImageEntity(
                name = imageName,
                mementoId = mementoId,
                isPlayable = isPlayable
            )
        )
    }

    override suspend fun update(mementoId: Long, isPlayable: Boolean) {
        dao.update(mementoId, isPlayable)
    }

    override suspend fun deleteMemento(imageId: Long) {
        val mementoId = dao.getImage(imageId).mementoId
        val mementoHasNoImage = dao.getMemento(mementoId).images.isEmpty()
        dao.deleteImage(imageId)
        if (mementoHasNoImage)
            dao.deleteMemory(mementoId)
    }

}
