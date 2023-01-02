package com.lirctek.loadboard.data.pagination

interface Paginator<Key, DataList> {
    suspend fun loadNextItems()
    fun reset()
}