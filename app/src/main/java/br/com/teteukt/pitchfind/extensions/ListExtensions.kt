package br.com.teteukt.pitchfind.extensions

fun <T> List<T>.getRandom(): T {
    val size = this.size
    val randomIndex = (Math.random() * (size - 1)).toInt()
    return this[randomIndex]
}