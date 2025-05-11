package com.kevin.welltrack23.repository

import android.content.Context
import com.kevin.welltrack23.data.ProductDatabase


import com.kevin.welltrack23.model.Product

class ProductRepository(context: Context) {
    private val productDao = ProductDatabase.getDatabase(context).productDao()

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    fun getAllProducts() = productDao.getAllProducts()

    suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)
}
