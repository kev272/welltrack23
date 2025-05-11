package com.kevin.welltrack23.repository


import com.kevin.welltrack23.data.ContentDao
import com.kevin.welltrack23.model.Content

class ContentRepository(private val contentDao: ContentDao) {
    val allContent = contentDao.getAllContent()

    suspend fun insert(content: Content) = contentDao.insertContent(content)
    suspend fun update(content: Content) = contentDao.updateContent(content)
    suspend fun delete(content: Content) = contentDao.deleteContent(content)
    suspend fun getById(id: Int) = contentDao.getContentById(id)
}