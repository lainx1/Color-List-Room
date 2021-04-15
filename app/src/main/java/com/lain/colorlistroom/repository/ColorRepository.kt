package com.lain.colorlistroom.repository

import androidx.annotation.WorkerThread
import com.lain.colorlistroom.dao.ColorDao
import com.lain.colorlistroom.entity.Color

class ColorRepository(private val colorDao: ColorDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findAll(): List<Color> = colorDao.findAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(color: Color) = colorDao.insert(color = color)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(color: Color): Int = colorDao.update(color = color)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(color: Color) = colorDao.delete(color = color)

}