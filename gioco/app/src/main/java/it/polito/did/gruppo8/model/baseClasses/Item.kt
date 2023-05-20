package it.polito.did.gruppo8.model.baseClasses

class Item() {
    var id : Int = -1
    var name : String = ""
    var price : Int = -1

    var iconURL : String = ""
    var houseSpriteURL : String = ""

    var greenModifier : StatModifier = StatModifier.NeutralImpact
    var comfyModifier : StatModifier = StatModifier.NeutralImpact
    var lowcostModifier : StatModifier = StatModifier.NeutralImpact
}