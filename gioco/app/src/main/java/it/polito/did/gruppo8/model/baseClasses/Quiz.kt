package it.polito.did.gruppo8.model.baseClasses

class Quiz() {
    var question : String = ""
    var answers : List<String> = listOf()
    var correctAnswer: Int = 0

    constructor(question: String, answers: List<String>, correctAnswer: Int) : this(){
        this.question = question
        this.answers = answers
        this.correctAnswer = correctAnswer
    }

    fun verifyAnswer(answerId : Int) : Boolean {
        return answerId == correctAnswer;
    }
}