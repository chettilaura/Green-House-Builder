package it.polito.did.gruppo8.model.baseClasses

class Player(
    val id : String,
    val nickname: String,
) {
    val wallet : Wallet = Wallet()
    val house : House = House()

    //TODO
}