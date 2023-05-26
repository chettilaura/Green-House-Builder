package it.polito.did.gruppo8.model.baseClasses

enum class StatModifier(val value: Int){
    BestImpact(100),
    GoodImpact(50),
    NeutralImpact(0),
    BadImpact(-50),
    WorstImpact(-100)
}

class Statistics {
    companion object{
        const val MAX_GREEN = 1000
        const val MAX_COMFY = 1000
        const val MAX_LOWCOST = 1000
    }

    var green : Int = 0
    var comfy : Int = 0
    var lowcost : Int = 0

    fun update(greenModifier: StatModifier,
               comfyModifier: StatModifier,
               lowcostModifier: StatModifier
    ){
        green += greenModifier.value
        comfy += comfyModifier.value
        lowcost += lowcostModifier.value
    }

    fun weightedAverage(): Float{
        return (green*MAX_GREEN +
                comfy*MAX_COMFY +
                lowcost*MAX_LOWCOST).toFloat() /
                (MAX_GREEN+MAX_LOWCOST+MAX_COMFY)
    }
}