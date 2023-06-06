package it.polito.did.gruppo8.model.baseClasses

class Wallet {
    var coins : Int = 0
        private set

    fun addCoins(amount: Int){
        coins += amount
    }

    fun removeCoins(amount: Int){
        coins -= amount
        if (coins<0) coins = 0
    }
}