package it.polito.did.gruppo8.model.baseClasses

import android.util.Log


class House {
    val stats: Statistics = Statistics()
    var items : MutableMap<Int, Item> = mutableMapOf()

    fun addItem(item: Item){
        if(items.contains(item.id)){
            Log.d("House", "Adding an already existing item to the house")
            return
        }
        // Add item
        items[item.id] = item

        // Update Modifiers
        stats.update(item.greenModifier, item.comfyModifier, item.lowcostModifier)
    }
}