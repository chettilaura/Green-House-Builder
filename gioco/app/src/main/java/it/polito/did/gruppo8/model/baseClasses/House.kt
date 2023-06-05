package it.polito.did.gruppo8.model.baseClasses

import android.util.Log


class House {
    val stats: Statistics = Statistics()
    var items : ArrayList<Item> = arrayListOf()

    fun addItem(item: Item){
        if(items.contains(item)){
            Log.d("House", "Adding an already existing item to the house")
            return
        }
        // Add item
        items.add(item)

        // Update Modifiers
        stats.update(item.greenModifier, item.comfyModifier, item.lowcostModifier)
    }
}