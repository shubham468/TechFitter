package com.tech.techflake

import android.view.View
import android.widget.Toast
import java.util.*

class ran {
//
//    val button = binding.appCompatButton
////
//    players = ArrayList()
//    players.add("Kirat Boli")
//    players.add("NS Nodhi")
//    players.add("R Rumrah")
//    players.add("Shashi Henra")
//
//    // List of remaining players (Since 1st two are on field)
//    remaining = ArrayList()
//    remaining = players.subList(2, players.size)
////
//    // Scores for players, with an out flag
//    // If the player hasn't been on the field yet, their scores will not be present here
//    scores = HashMap()
//    scores[players[0]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)
//    scores[players[1]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)
////
////        // Storing the probabilities of 0,1,2,3,4,5,6 and out in a dictionary
////        // for the given players
//    probs = Hashtable<String, ArrayList<Double>>()
//    probs[players[0]] = arrayListOf(0.05, 0.30, 0.25, 0.10, 0.15, 0.01, 0.09, 0.05)
//    probs[players[1]] = arrayListOf(0.10, 0.40, 0.20, 0.05, 0.10, 0.01, 0.04, 0.10)
//    probs[players[2]] = arrayListOf(0.20, 0.30, 0.15, 0.05, 0.05, 0.01, 0.04, 0.20)
//    probs[players[3]] = arrayListOf(0.30, 0.25, 0.05, 0.00, 0.05, 0.01, 0.04, 0.30)
////
////
////        // Current players (1st and 2nd on the field)
//    playing = ArrayList()
//    playing.add(players[wickets])
//    playing.add(players[wickets + 1])
//
//
//    updateScore(scores, overcount, over)
//
//
//    updatePlayer(
//    0,
//    players[0],
//    players[1],
//    0,
//    scores[playing[0]]?.get("Score") ?: 0,
//    scores[playing[1]]?.get("Score") ?: 0,
//    0,
//    0
//    )
//
//
//    button.setOnClickListener {
//        //reffer as ball
//        if (MainActivity.overcount <= 4 && MainActivity.wickets < 3) {
//            MainActivity.over += 1
//            ballBowled(MainActivity.over)
//            updateScore(scores, MainActivity.overcount, MainActivity.over)
//
//            if (MainActivity.over == 6) {
//                MainActivity.overcount += 1
//                MainActivity.over = 0
//                // At the end of an over, change strike
//                playing = changestrike(playing)
//            }
//        }
//    }
//}
//
//private fun ballBowled(ball: Int) {
//
//    if (MainActivity.overcount >= 4) {
//        Toast.makeText(
//            this,
//            "Other Team won by ${MainActivity.totalRuns - MainActivity.runs} runs & ${MainActivity.wickets} wickets.",
//            Toast.LENGTH_LONG
//        ).show()
//
//        return
//    }
//
//    // get random values between 0 and 7
//    // p are the probability values, used to select random values
//    val random = random_runs(probs, playing)
//
//    // Increasing balls played for the player on strike
//    scores[playing[0]]?.get("Balls")?.let { scores[playing[0]]?.set("Balls", it.plus(1)) }
//
//    // random being 7 means out
//
//    if (random != 7) {
////           Reducing number of runs remaining
////           totalRuns = totalRuns.minus(random)
//        MainActivity.runs = MainActivity.runs.plus(random)
////                # Increasing the score of the player
//        scores[playing[0]]?.get("Score")
//            ?.let { scores[playing[0]]?.set("Score", it.plus(random)) }
//
//        updatePlayer(
//            ball,
//            players[0],
//            players[1],
//            random,
//            scores[playing[0]]?.get("Score") ?: 0,
//            scores[playing[1]]?.get("Score") ?: 0,
//            MainActivity.overcount,
//            MainActivity.runs
//        )
//
//
//        //If the no. of runs is odd then change strike
//
//        if (random % 2 != 0) {
//            playing = changestrike(playing)
//
//        }
//
////              More than given runs made, other team wins
//        if (MainActivity.runs >= 40) {
//            Toast.makeText(this, "Team won by ${3 - MainActivity.wickets} wickets", Toast.LENGTH_LONG).show()
//            binding.appCompatButton.isEnabled = false
//            return
//        }
//
//    } else {
//        // If random is 7 and the player is out
//        MainActivity.wickets = MainActivity.wickets.plus(1)
//
//        // Set the player status to Out
//        scores[playing[0]]?.set("Out", 1)
//
//        //  If all players are out, team lost
//        if (MainActivity.wickets == 3) {
//            Toast.makeText(
//                this,
//                "Other Team won ${MainActivity.totalRuns - MainActivity.runs} runs & ${MainActivity.wickets} wickets",
//                Toast.LENGTH_LONG
//            ).show()
//
//        } else {
//            //  Put the next player on strike
//            playing.clear()
//            playing.add(remaining[0])
//            playing.add(players[1])
//
//            scores[remaining[0]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)
//
//            updatePlayer(
//                ball,
//                playing[0],
//                playing[1],
//                random,
//                scores[playing[0]]?.get("Score") ?: 0,
//                scores[playing[1]]?.get("Score") ?: 0,
//                MainActivity.overcount,
//                MainActivity.runs
//            )
//
//            // Remove onstrike player from remaining players list
//            remaining.remove(remaining[0])
//        }
//    }
//
//}
//
//private fun changestrike(playing: ArrayList<String>): ArrayList<String> {
//    if (binding.star1.visibility == View.VISIBLE) {
//        binding.star2.visibility = View.VISIBLE
//        binding.star1.visibility = View.GONE
//    } else {
//        binding.star2.visibility = View.GONE
//        binding.star1.visibility = View.VISIBLE
//    }
//    return playing.reversed() as ArrayList<String>
//}
//
//
//private fun random_runs(
//    probs: Hashtable<String, ArrayList<Double>>,
//    playing: ArrayList<String>
//): Int {
//    val d: Int = (0..100).random()
//    var cumulativeProbability = 0
//
//    for (i in 0..probs.size) {
//        cumulativeProbability += (probs[playing[0]]?.get(i)?.times(100))?.toInt() ?: 0
//
//        if (d <= cumulativeProbability && (probs[playing[0]]?.get(i)
//                ?.times(100))?.toInt() ?: 0 != 0
//        ) {
//            return i
//        }
//    }
//    return 0
//}
//
//private fun updatePlayer(
//    ball: Int,
//    s: String,
//    s1: String,
//    random: Any,
//    runs: Int,
//    runs1: Int,
//    overcount: Int,
//    allRunsMade: Int
//) {
//
//    binding.overs.text = "Overs: ${overcount}.$ball"
//    binding.playerName1.text = "$s ($runs)"
//    binding.playerName2.text = "$s1 ($runs1)"
//    binding.score.text = "Score: $allRunsMade -  ${MainActivity.wickets}"
//
//}
//
//
//private fun updateScore(
//    scores: HashMap<String, HashMap<String, Int>>,
//    overcount: Int,
//    over: Int
//) {
//    for (player: MutableMap.MutableEntry<String, HashMap<String, Int>> in scores) {
//        if (scores[player.key]?.get("Score") == 0) {
//            binding.score.text = "Score: ${MainActivity.runs} - ${MainActivity.wickets}"
//            binding.overs.text = "Overs: $overcount.$over"
//            binding.textRuns.text = MainActivity.runs.toString()
//
//
//        }
//    }
//}
}