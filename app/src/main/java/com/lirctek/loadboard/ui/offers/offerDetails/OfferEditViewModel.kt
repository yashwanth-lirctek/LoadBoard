package com.lirctek.loadboard.ui.offers.offerDetails

import androidx.lifecycle.ViewModel
import com.lirctek.loadboard.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfferEditViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun addCondition(condition: String, proposedPickup: String) {
        //Add Condition and Proposed Pickup to Conditions List
    }

    fun getDescriptions(){}

    fun addDescription(){}

    fun submitRequest(){}

    fun getOfferDetails(){}

    fun getOfferList(){}

}