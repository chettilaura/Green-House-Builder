package it.polito.did.gruppo8.model.baseClasses

class Player(
    val nickname: String,
    val id : Int,
) {
    val wallet : Wallet = Wallet()
    val house : House = House()

    //TODO
}