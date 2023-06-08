package it.polito.did.gruppo8.model.baseClasses


class Quiz() {
    var question : String = ""
    var answers : List<String> = listOf()
    var correct: Int = 0

    constructor(question: String, answers: List<String>, correctAnswer: Int) : this(){
        this.question = question
        this.answers = answers
        this.correct = correctAnswer
    }

    enum class Result(){
        NotGiven,
        Wrong,
        Correct
    }
    fun verifyAnswer(answerId : Int) : Result {
        if(answerId==-1)
            return Result.NotGiven
        return if(answerId==correct) Result.Correct else Result.Wrong
    }
}