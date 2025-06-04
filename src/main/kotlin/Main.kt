package votefy

import votefy.model.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val client = VotefyClient("http://localhost:3000")
//    var q = client.admin.getAllDefinitions()
//    q?.forEach {
//        println("Vote: ${it.title} , ${it.options}")
//    }
//    println("== יצירת הצבעה ==")
//    val created = client.admin.createVote(
//        title = "מה השפה האהובה עליך?",
//        type = "multiple_choice",
//        options = listOf("Kotlin", "Python", "JavaScript")
//    )
//    println("נוצר? $created")

    // נניח שיש לנו שאלה עם מזהה ידוע מראש (כמו מה־DB שלך)
    val questionId = "74423a09-f0db-4630-b18a-d36ff3645cd4" // החלף במזהה אמיתי אם יש

//    println("== שליחת הצבעה ==")
//    val voteSuccess = client.voter.vote(
//        VoteSubmission(
//            userId = "testUser",
//            targetId = questionId,
//            value = "Python",)
//    )
//    println("ההצבעה הצליחה? $voteSuccess")

//    println("== תוצאה עדכנית ==")
//    val result = client.voter.getResults(questionId)
//    println("תוצאה: ${result}")


//    println("== מי הצביע ==")
//    val voters = client.admin.showWhoVoted(questionId)
//    println("משתתפים: ${voters.joinToString(", ")}")

}



