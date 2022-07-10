package com.tech.techflake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {

    val strikeChange = MutableLiveData<String>()
    val giveReponseModel = MutableLiveData<GiveReponseModel>()
    val win = MutableLiveData<String>()

    val lost = MutableLiveData<String>()


    var runsViewModel:Int=0
    var wicketViewModel:Int=0

    lateinit var playingViewModel:ArrayList<String>

    fun setballBowled(
        over: Int,
        scores: HashMap<String, HashMap<String, Int>>,
        overcount: Int,
        wickets: Int,
        probs: Hashtable<String, ArrayList<Double>>,
        players: ArrayList<String>,
        playin: ArrayList<String>,
        remaining: MutableList<String>,
        totalRuns: Int,
        totalover: Int,
        runs: Int
    ) {
        playingViewModel= ArrayList()
        playingViewModel=playin
        if (overcount >= 4) {
            lost.postValue("Other Team won by ${totalRuns - runs} runs & $wicketViewModel wickets.")
            return
        }

        // get random values between 0 and 7
        // p are the probability values, used to select random values
        val random = random_runs(probs, playingViewModel)

        // Increasing balls played for the player on strike
        scores[playingViewModel[0]]?.get("Balls")?.let { scores[playingViewModel[0]]?.set("Balls", it.plus(1)) }

        // random being 7 means out

        if (random != 7) {
//           Reducing number of runs remaining
            runsViewModel = runs.plus(random)
//                # Increasing the score of the player
            scores[playingViewModel[0]]?.get("Score")
                ?.let { scores[playingViewModel[0]]?.set("Score", it.plus(random)) }

            val getdata = GiveReponseModel(
                playingViewModel[0], playingViewModel[1], scores[playingViewModel[0]]?.get("Score") ?: 0,
                scores[playingViewModel[1]]?.get("Score") ?: 0, runsViewModel, totalRuns, totalover, over,overcount,wicketViewModel
            )

            giveReponseModel.postValue(getdata)


            //If the no. of runs is odd then change strike
            if (random % 2 != 0) {
                playingViewModel = changestrike(playingViewModel)

            }

//              More than given runs made, other team wins
            if (MainActivity.runs >= 40) {
                win.postValue("Team won by ${3 - wicketViewModel} wickets")
                return
            }

        } else {
            // If random is 7 and the player is out
            wicketViewModel = wickets.plus(1)

            // Set the player status to Out
            scores[playingViewModel[0]]?.set("Out", 1)

            //  If all players are out, team lost
            if (wicketViewModel == 3) {

                lost.postValue("Other Team won ${totalRuns - runsViewModel} runs & $wicketViewModel wickets")

            } else {
                //  Put the next player on strike
                playingViewModel.clear()
                playingViewModel.add(remaining[0])
                playingViewModel.add(players[1])

                scores[remaining[0]] = hashMapOf("Score" to 0, "Balls" to 0, "Out" to 0)

                // Remove onstrike player from remaining players list
                remaining.remove(remaining[0])
            }
        }

    }

    fun changestrike(playing: ArrayList<String>): ArrayList<String> {

        strikeChange.value = ""
        return playing.reversed() as ArrayList<String>
    }


    private fun random_runs(
        probs: Hashtable<String, ArrayList<Double>>,
        playing: ArrayList<String>
    ): Int {
        val d: Int = (0..100).random()
        var cumulativeProbability = 0

        for (i in 0..probs.size) {

            cumulativeProbability += (probs[playing[0]]?.get(i)?.times(100))?.toInt() ?: 0

            if (d <= cumulativeProbability && (probs[playing[0]]?.get(i)
                    ?.times(100))?.toInt() ?: 0 != 0
            ) {
                return i
            }
        }
        return 0
    }


}