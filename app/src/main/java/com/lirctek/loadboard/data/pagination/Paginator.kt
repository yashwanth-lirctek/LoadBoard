package com.lirctek.loadboard.data.pagination

interface Paginator<Key, OfferDataList> {
    suspend fun loadNextItems(status: String)
    fun reset()
}