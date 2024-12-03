import kotlin.math.abs

fun main() {
    fun pair(input: List<String>): Pair<ArrayList<Int>, ArrayList<Int>> {
        val firstList = arrayListOf<Int>()
        val secondList = arrayListOf<Int>()

        for (i in input) {
            val firstNumber = i.substringBefore(" ").toInt()
            val secondNumber = i.substringAfterLast(" ").toInt()

            firstList.add(firstNumber)
            secondList.add(secondNumber)
        }
        return Pair(firstList, secondList)
    }

    fun part1(input: List<String>): Int {
        val (firstList, secondList) = pair(input)
        firstList.sort()
        secondList.sort()

        var totalDifference = 0

        firstList.forEachIndexed { index, firstnumber ->
            totalDifference += abs(firstnumber - secondList[index])
        }

        return totalDifference
    }

    fun part2(input: List<String>): Int {
        val (firstList, secondList) = pair(input)

        var similarityScoreTotal = 0
        for (i in firstList) {
            val countRight = secondList.count { it == i }
            val similarityScore = i * countRight
            similarityScoreTotal += similarityScore
        }
        return similarityScoreTotal
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    part1(testInput).println()
    check(part1(testInput) == 11)

    part1(input).println()


    part2(testInput).println()
    check(part2(testInput) == 31)

    part2(input).println()
}
