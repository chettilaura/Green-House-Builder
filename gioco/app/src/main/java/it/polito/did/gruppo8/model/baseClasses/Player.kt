package it.polito.did.gruppo8.model.baseClasses

import android.provider.ContactsContract.CommonDataKinds.Nickname

class Player() {
    var id : String = ""
    var nickname : String = ""
    constructor(id : String, nickname : String) : this(){
        this.id = id
        this.nickname = nickname
    }

    val wallet : Wallet = Wallet()
    val house : House = House()

    //TODO
}