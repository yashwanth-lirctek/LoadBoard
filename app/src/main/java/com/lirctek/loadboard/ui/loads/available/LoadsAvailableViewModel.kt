package com.lirctek.loadboard.ui.loads

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lirctek.loadboard.data.pagination.DefaultPaginator
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.repository.Repository
import com.lirctek.loadboard.ui.loads.delivered.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadsAvailableViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(ScreenState())
    var isRefreshing : MutableState<Boolean> = mutableStateOf(false)

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        resetValues = { position ->
            if (position == 0) {
                state = state.copy(
                    isLoading = false,
                    loadsList = emptyList(),
                    error = null,
                    endReached = false,
                    page = 0
                )
            }
        },
        onDataUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getOffers(nextPage, 1, "Active")
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            isRefreshing.value = false
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { offerData, newKey ->
            isRefreshing.value = false
            val newList = state.loadsList + offerData
            val error = if (state.loadsList.isEmpty() && offerData.isEmpty()) "No Data" else null
            state = state.copy(
                loadsList = newList,
                page = newKey,
                endReached = offerData.isEmpty(),
                isLoading = offerData.isNotEmpty(),
                error = error
            )
        }
    )

}

data class ScreenState(
    var isLoading: Boolean = false,
    var loadsList: List<OfferDataList> = emptyList(),
    var error: String? = null,
    var endReached: Boolean = false,
    var page: Int = 0
)