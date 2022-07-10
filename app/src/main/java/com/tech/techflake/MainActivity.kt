package com.tech.techflake

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tech.techflake.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var players: ArrayList<String>
    private lateinit var remaining: MutableList<String>
    private lateinit var scores: HashMap<String, HashMap<String, Int>>
    private lateinit var probs: Hashtable<String, ArrayList<Double>>
    private lateinit var playing: ArrayList<String>

//    private val viewmodel by lazy {
//        ViewModelProvider(
//            this,
//            MainViewModelFactory()
//        )[MainViewModel::class.java]
//    }

    companion object {
        // Number of overs
        var totalover = 4
        var overcount = 0

        //per Ball
        var over = 0

        var totalRuns = 40

        // Number of runs
        var runs = 0

        //Number of wickets gone. Used as flag to check if all players are out
        var wickets = 0

//        //Number of balls in an over
//        val balls = 1..6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = binding.appCompatButton


        players = ArrayList()
        players.add("Kirat Boli")
        players.add("NS Nodhi")
        players.add("R Rumrah")
        players.add("Shashi Henra")

        // List of remaining players (Since 1st two are on field)
        remaining = ArrayList()
        remaining = players.subList(2, players.size)

        // Scores for players, with an out flag
        // If the player hasn't been on the field yet, their scores will not be present here
        scores = HashMap()
        scores[players[0]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)
        scores[players[1]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)


//        // Storing the probabilities of 0,1,2,3,4,5,6 and out in a dictionary
//        // for the given players
        probs = Hashtable<String, ArrayList<Double>>()
        probs[players[0]] = arrayListOf(0.05, 0.30, 0.25, 0.10, 0.15, 0.01, 0.09, 0.05)
        probs[players[1]] = arrayListOf(0.10, 0.40, 0.20, 0.05, 0.10, 0.01, 0.04, 0.10)
        probs[players[2]] = arrayListOf(0.20, 0.30, 0.15, 0.05, 0.05, 0.01, 0.04, 0.20)
        probs[players[3]] = arrayListOf(0.30, 0.25, 0.05, 0.00, 0.05, 0.01, 0.04, 0.30)

//        // Current players (1st and 2nd on the field)
        playing = ArrayList()
        playing.add(players[wickets])
        playing.add(players[wickets + 1])


        updateScore(scores, overcount, over)


        updatePlayer(
            0,
            players[0],
            players[1],
            scores[playing[0]]?.get("Score") ?: 0,
            scores[playing[1]]?.get("Score") ?: 0,
            0,
            0,
            0,
        )


//        viewmodel.giveReponseModel.observe(this) {
//            binding.overs.text = "Overs: ${it.overcount}.${it.ball}"
//            binding.playerName1.text = "${it.player1} (${it.scoreByPlayer0})"
//            binding.playerName2.text = "${it.player2} (${it.scoreByPlayer1})"
//            binding.score.text = "Score: ${it.runByBall} -  ${it.wickets}"
//        }
//
//        viewmodel.win.observe(this) {
//            Toast.makeText(
//                this,
//                it,
//                Toast.LENGTH_LONG
//            ).show()
//            binding.appCompatButton.isEnabled = false
//        }
//
//        viewmodel.lost.observe(this){
//
//        }
//
//
//
//        viewmodel.strikeChange.observe(this) {
//            if (binding.star1.visibility == View.VISIBLE) {
//                binding.star2.visibility = View.VISIBLE
//                binding.star1.visibility = View.GONE
//            } else {
//                binding.star2.visibility = View.GONE
//                binding.star1.visibility = View.VISIBLE
//            }
//        }


        button.setOnClickListener {
            //reffer as ball
            binding.textRuns.text = "0"
            if (overcount <= 4 && wickets < 3) {
                over += 1

                ballBowled(over)
//                viewmodel.setballBowled(
//                    over, scores, overcount, wickets, probs, players, playing, remaining,
//                    totalRuns,
//                    totalover,
//                    runs
//                )

                updateScore(scores, overcount, over)


                if (over == 6) {
                    overcount += 1
                    over = 0
                    // At the end of an over, change strike
//                    viewmodel.changestrike(playing)
                    changestrike(playing)
                }

            }
        }
    }

//    private fun ballBowled(ball: Int) {
//
//        if (overcount >= 4) {
//            Toast.makeText(
//                this,
//                "Other Team won by ${totalRuns - runs} runs & $wickets wickets.",
//                Toast.LENGTH_LONG
//            ).show()
//
//            return
//        }
//
//        // get random values between 0 and 7
//        // p are the probability values, used to select random values
//        val random = random_runs(probs, playing)
//
//        // Increasing balls played for the player on strike
//        scores[playing[0]]?.get("Balls")?.let { scores[playing[0]]?.set("Balls", it.plus(1)) }
//
//        // random being 7 means out
//
//        if (random != 7) {
////           Reducing number of runs remaining
////           totalRuns = totalRuns.minus(random)
//            runs = runs.plus(random)
////                # Increasing the score of the player
//            scores[playing[0]]?.get("Score")
//                ?.let { scores[playing[0]]?.set("Score", it.plus(random)) }
//
//            updatePlayer(
//                ball,
//                players[0],
//                players[1],
//                random,
//                scores[playing[0]]?.get("Score") ?: 0,
//                scores[playing[1]]?.get("Score") ?: 0,
//                overcount,
//                runs
//            )
//
//
//            //If the no. of runs is odd then change strike
//
//            if (random % 2 != 0) {
//                playing = changestrike(playing)
//
//            }
//
////              More than given runs made, other team wins
//            if (runs >= 40) {
//                Toast.makeText(this, "Team won by ${3 - wickets} wickets", Toast.LENGTH_LONG).show()
//                binding.appCompatButton.isEnabled = false
//                return
//            }
//
//        } else {
//            // If random is 7 and the player is out
//            wickets = wickets.plus(1)
//
//            // Set the player status to Out
//            scores[playing[0]]?.set("Out", 1)
//
//            //  If all players are out, team lost
//            if (wickets == 3) {
//                Toast.makeText(
//                    this,
//                    "Other Team won ${totalRuns - runs} runs & ${wickets} wickets",
//                    Toast.LENGTH_LONG
//                ).show()
//
//            } else {
//                //  Put the next player on strike
//                playing.clear()
//                playing.add(remaining[0])
//                playing.add(players[1])
//
//                scores[remaining[0]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)
//
//                updatePlayer(
//                    ball,
//                    playing[0],
//                    playing[1],
//                    random,
//                    scores[playing[0]]?.get("Score") ?: 0,
//                    scores[playing[1]]?.get("Score") ?: 0,
//                    overcount,
//                    runs
//                )
//
//                // Remove onstrike player from remaining players list
//                remaining.remove(remaining[0])
//            }
//        }
//
//    }

//    private fun changestrike(playing: ArrayList<String>): ArrayList<String> {
//        if (binding.star1.visibility == View.VISIBLE) {
//            binding.star2.visibility = View.VISIBLE
//            binding.star1.visibility = View.GONE
//        } else {
//            binding.star2.visibility = View.GONE
//            binding.star1.visibility = View.VISIBLE
//        }
//        return playing.reversed() as ArrayList<String>
//    }


//    private fun random_runs(
//        probs: Hashtable<String, ArrayList<Double>>,
//        playing: ArrayList<String>
//    ): Int {
//        val d: Int = (0..100).random()
//        var cumulativeProbability = 0
//
//        for (i in 0..probs.size) {
//            cumulativeProbability += (probs[playing[0]]?.get(i)?.times(100))?.toInt() ?: 0
//
//            if (d <= cumulativeProbability && (probs[playing[0]]?.get(i)
//                    ?.times(100))?.toInt() ?: 0 != 0
//            ) {
//                return i
//            }
//        }
//        return 0
//    }

    private fun ballBowled(ball: Int) {
        binding.matchResult.visibility = View.GONE
        if (overcount >= 4) {
            binding.matchResult.visibility = View.VISIBLE
            binding.matchResult.text = "Bengaluru lost the match with $wickets wickets"
            Toast.makeText(
                this,
                "Other Team won by ${totalRuns - runs} runs & ${wickets} wickets.",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        // get random values between 0 and 7
        // p are the probability values, used to select random values
        val random = random_runs(probs, playing)

        // Increasing balls played for the player on strike
        scores[playing[0]]?.get("Balls")?.let { scores[playing[0]]?.set("Balls", it.plus(1)) }

        // random being 7 means out

        if (random != 7) {
//           Reducing number of runs remaining
//           totalRuns = totalRuns.minus(random)
            runs = runs.plus(random)
//           # Increasing the score of the player
            scores[playing[0]]?.get("Score")
                ?.let { scores[playing[0]]?.set("Score", it.plus(random)) }

            updatePlayer(
                ball,
                players[0],
                players[1],
                scores[playing[0]]?.get("Score") ?: 0,
                scores[playing[1]]?.get("Score") ?: 0,
                overcount,
                runs,
                random
            )


            //If the no. of runs is odd then change strike

            if (random % 2 != 0) {
                playing = changestrike(playing)

            }

//              More than given runs made, other team wins
            if (runs >= 40) {
                binding.matchResult.visibility = View.VISIBLE
                binding.matchResult.text =
                    "Bengaluru won the match with ${3 - wickets} wicket and ${6 - ball} balls"
                Toast.makeText(
                    this,
                    "Team won by ${3 - wickets} wickets",
                    Toast.LENGTH_LONG
                ).show()
                binding.appCompatButton.isEnabled = false
                return
            }

        } else {
            // If random is 7 and the player is out
            wickets = wickets.plus(1)

            // Set the player status to Out
            scores[playing[0]]?.set("Out", 1)

            //  If all players are out, team lost
            if (wickets == 3) {
                binding.matchResult.visibility = View.VISIBLE
                binding.matchResult.text = "Bengaluru lost the match with ${wickets} wickets"
                Toast.makeText(
                    this,
                    "Other Team won ${totalRuns - runs} runs & ${wickets} wickets",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                //  Put the next player on strike
                playing.clear()
                playing.add(remaining[0])
                playing.add(players[1])

                scores[remaining[0]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)

                updatePlayer(
                    ball,
                    playing[0],
                    playing[1],
                    scores[playing[0]]?.get("Score") ?: 0,
                    scores[playing[1]]?.get("Score") ?: 0,
                    overcount,
                    runs,
                    random
                )

                // Remove onstrike player from remaining players list
                remaining.remove(remaining[0])
            }
        }

    }


    private fun changestrike(playing: ArrayList<String>): ArrayList<String> {
        if (binding.star1.visibility == View.VISIBLE) {
            binding.star2.visibility = View.VISIBLE
            binding.star1.visibility = View.GONE
        } else {
            binding.star2.visibility = View.GONE
            binding.star1.visibility = View.VISIBLE
        }
        return playing.reversed() as ArrayList<String>
    }


    private fun random_runs(
        probs: Hashtable<String, ArrayList<Double>>,
        playing: ArrayList<String>
    ): Int {
        val d: Int = (0..100).random()
        var cumulativeProbability = 0

        for (i in 0..7) {

            cumulativeProbability += (probs[playing[0]]?.get(i)?.times(100))?.toInt() ?: 0
            if (d <= cumulativeProbability && (probs[playing[0]]?.get(i)
                    ?.times(100))?.toInt() ?: 0 != 0
            ) {
                Log.e("prob",
                    "$i == $cumulativeProbability  ==  " + probs[playing[0]]?.get(i)?.times(100)
                        .toString()
                )
                return i
            }
        }
        return 0
    }

    private fun updatePlayer(
        ball: Int,
        player0: String,
        player1: String,
        runsp0: Int,
        runsp1: Int,
        overcount: Int,
        allRunsMade: Int,
        random: Int,
    ) {

        binding.overs.text = "Overs: ${overcount}.$ball"
        binding.playerName1.text = "$player0 ($runsp0)"
        binding.playerName2.text = "$player1 ($runsp1)"
        binding.score.text = "Score: $allRunsMade -  $wickets"

        if (random.toString() == "7")
            binding.textRuns.text = "Out"
        else {
            binding.textRuns.text = random.toString()
        }

    }


    private fun updateScore(
        scores: HashMap<String, HashMap<String, Int>>,
        overcount: Int,
        over: Int
    ) {
        for (player: MutableMap.MutableEntry<String, HashMap<String, Int>> in scores) {
            if (scores[player.key]?.get("Score") == 0) {
                binding.score.text = "Score: $runs - $wickets"
                binding.overs.text = "Overs: $overcount.$over"
            }
        }
    }
}

private operator fun <E> ArrayList<E>.get(get: String): E {
    return this[get]

}

private operator fun Double.plus(get: Char): Double {
    return this.plus(get)
}

private operator fun Random.iterator(): Iterator<Int> {
    return this.iterator()
}
