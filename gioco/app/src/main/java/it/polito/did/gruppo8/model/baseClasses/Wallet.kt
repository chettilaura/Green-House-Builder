package it.polito.did.gruppo8.model.baseClasses

class Wallet {
    var _coins : Int = 0

    fun totalCoins(): Int{
        return _coins
    }

    fun addCoins(amount: Int){
        _coins += amount
    }

    fun removeCoins(amount: Int){
        _coins -= amount
    }
}