package com.lirctek.loadboard.data.pagination


class DefaultPaginator<Key, OfferDataList>(
    private val initialKey: Key,
    private val resetValues: (Key) -> Unit,
    private inline val onDataUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key, status: String) -> Result<List<OfferDataList>>,
    private inline val getNextKey: suspend (List<OfferDataList>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (offerDataList: List<OfferDataList>, newKey: Key) -> Unit
) : Paginator<Key, OfferDataList> {

    private var currentKey: Key = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems(status: String) {
        if (isMakingRequest) return

        isMakingRequest = true
        onDataUpdated(true)
        val result = onRequest(currentKey, status)
        isMakingRequest = false
        val offerDataList = result.getOrElse {
            onError(it)
            onDataUpdated(false)
            return
        }
        currentKey = getNextKey(offerDataList)
        onSuccess(offerDataList, currentKey)
        onDataUpdated(false)

    }

    override fun reset() {
        currentKey = initialKey
        resetValues(currentKey)
    }
}