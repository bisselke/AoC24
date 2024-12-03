import kotlin.math.absoluteValue

fun main() {
    fun isSafe(
        intList: List<Int>
    ): Boolean {
        var safe = true
        var additions = true
        var subtractions = true

        for (i in 0..<intList.lastIndex) {
            val firstNumber = intList[i]
            val secondNumber = intList[i + 1]
            safe = safe && ((firstNumber - secondNumber).absoluteValue <= 3)
            when {
                firstNumber < secondNumber -> subtractions = false
                secondNumber < firstNumber -> additions = false
                else -> { //equals
                    additions = false
                    subtractions = false
                }
            }
        }

        return safe && (additions || subtractions)
    }

    fun part1(input: List<String>): Int {
        var safeTotal = 0
        for (i in input) {
            val intList = i.split(" ").map { it.toInt() }
            if (isSafe(intList)) safeTotal++
        }
        return safeTotal
    }

    fun part2(input: List<String>): Int {
        var safeTotal = 0

        for (i in input) {
            val intList = i.split(" ").map { it.toInt() }
//            if (isSafe(intList)) safeTotal++
//            else {
            for (j in 0..intList.lastIndex) {
                val retryList = intList.toMutableList().apply { removeAt(j) }
                if (isSafe(retryList)) {
                    safeTotal++
                    break
                }
            }
        }
//        }
        return safeTotal
    }

    // Test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    part1(testInput).println()
    check(part1(testInput) == 2)

    part1(input).println()

    part2(testInput).println()
    check(part2(testInput) == 4)

    part2(input).println()
}
