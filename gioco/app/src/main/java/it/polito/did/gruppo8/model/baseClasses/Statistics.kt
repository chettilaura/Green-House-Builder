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
        const val MAX_GREEN = 500
        const val MAX_COMFY = 500
        const val MAX_LOWCOST = 500
    }

    var green : Int = 0
    var comfy : Int = 0
    var lowcost : Int = 0

    fun update(greenModifier: StatModifier,
               comfyModifier: StatModifier,
               lowcostModifier: StatModifier
    ){
        green += greenModifier.value
        green.coerceIn(0..MAX_GREEN)

        comfy += comfyModifier.value
        comfy.coerceIn(0..MAX_COMFY)

        lowcost += lowcostModifier.value
        lowcost.coerceIn(0..MAX_LOWCOST)
    }

    fun weightedAverage(): Float{
        return (green*MAX_GREEN +
                comfy*MAX_COMFY +
                lowcost*MAX_LOWCOST).toFloat() /
                (MAX_GREEN+MAX_LOWCOST+MAX_COMFY)
    }
}