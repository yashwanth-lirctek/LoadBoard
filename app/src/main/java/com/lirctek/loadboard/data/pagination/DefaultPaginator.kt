package com.lirctek.loadboard.data.pagination

import android.util.Log
import com.google.gson.Gson


class DefaultPaginator<Key, DataList>(
    private val initialKey: Key,
    private val resetValues: (Key) -> Unit,
    private inline val onDataUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<DataList>>,
    private inline val getNextKey: suspend (List<DataList>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (dataList: List<DataList>, newKey: Key) -> Unit
) : Paginator<Key, DataList> {

    private var currentKey: Key = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) return

        isMakingRequest = true
        onDataUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val dataList = result.getOrElse {
            onError(it)
            onDataUpdated(false)
            return
        }
        currentKey = getNextKey(dataList)
        onSuccess(dataList, currentKey)
        onDataUpdated(false)

    }

    override fun reset() {
        currentKey = initialKey
        resetValues(currentKey)
    }
}