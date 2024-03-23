package br.com.teteukt.pitchfind.service

import kotlin.math.max
import kotlin.math.min

class ScoreService {
    var score = 1000
        private set

    fun decrease(amount: Int) {
        score -= max(0, amount)
    }

    fun increase(amount: Int) {
        score += min(0, amount)
    }
}