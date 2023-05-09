package it.polito.did.gruppo8.util.myLibs

import kotlin.random.Random

class MyRandom {
    companion object{
        fun string(length: Int) : String{
            val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return (1..length)
                .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
                .joinToString("")
        }

        fun int(range: IntRange) : Int{
            return range.random()
        }
    }
}