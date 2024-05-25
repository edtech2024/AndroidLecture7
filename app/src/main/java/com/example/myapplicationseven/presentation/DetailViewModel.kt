package com.example.myapplicationseven.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationseven.data.ItemRepository
import com.example.myapplicationseven.domain.Item
import kotlinx.coroutines.launch


class DetailViewModel(val repository: ItemRepository, val bundle: Bundle?) : ViewModel() {

    var argAction: String? = null
    var argId: Int? = null
    var argTitle: String = "null"
    var argDescription: String = "null"
    var argPriority: Int? = null
    var argType: String = "null"
    var argType1: Boolean
    var argType2: Boolean
    var argCount: String = "null"
    var argPeriod: String = "null"

    var flag: Boolean

    init{
        flag = true
        argType1 = false
        argType2 = false

        readBundle(bundle)
    }

    fun chooseTypeFirst(){
        argType1 = true
        argType2 = false
        argType = "Type 1"
    }

    fun chooseTypeSecond(){
        argType1 = false
        argType2 = true
        argType = "Type 2"
    }

    private fun readBundle(bundle:Bundle?){
        argAction = bundle?.getString("Action", "")
        argId = bundle?.getInt("Id",0 )
        argTitle = bundle!!.getString("Title", "Naming")
        argDescription = bundle!!.getString("Description", "...")
        argPriority = bundle!!.getString("Priority", "2")?.toInt()
        argType = bundle!!.getString("TYPE", "Type 1")

        if (argType != "Type 1") {
            argType2 = true
            argType1 = false
        }
        if (argType != "Type 2") {
            argType1 = true
            argType2 = false
        }

        argCount = bundle!!.getString("Count", "0")
        argPeriod = bundle!!.getString("Period", "0")

    }

    override fun onCleared() {}

    // Launching a new coroutine to insert the data in a non-blocking way
    private fun callCreateMethod(item: Item){
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    // Launching a new coroutine to update the data in a non-blocking way
    private fun callUpdateMethod(item: Item){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun callClick(){
        if (argAction == "Create"){
            callCreateMethod(makeItem())
        } else {
            callUpdateMethod(makeItem())
        }
    }

    private fun makeItem(): Item {

        if (argAction == "Create") {
            argId = null
        }

        val item = Item(argId, argTitle, argDescription,
            argPriority, argType, argCount?.toInt(), argPeriod?.toInt()
        )

        return item
    }

}