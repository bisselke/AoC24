fun main() {

    fun String.isValid(inputLine: String, rules: List<String>): Boolean {
        for (rule in rules) {
            if (!rule.contains(this))
                continue
            if (rule.startsWith(this) &&
                inputLine.substringBefore(this).contains(rule.substringAfter("|"))
            )
                return false
            if (rule.endsWith(this) &&
                inputLine.substringAfter(this).contains(rule.substringBefore("|"))
            )
                return false
        }
        return true
    }


    fun part1(input: List<String>, rules: List<String>): Long {
        var sumMiddleNumberOfValidUpdates = 0L
        for (inputLine in input) {
            val numberList = inputLine.split(",")
            if (numberList.all { number -> number.isValid(inputLine, rules) }) {
                sumMiddleNumberOfValidUpdates += numberList[(numberList.size - 1) / 2].toLong()
            }
        }
        return sumMiddleNumberOfValidUpdates
    }

    fun String.ifInvalidCorrectOrder(inputLine: String, rules: List<String>): String {
        var mutableInputLine = inputLine
        for (rule in rules) {
            if (!rule.contains(this))
                continue
            if (rule.startsWith(this) &&
                mutableInputLine.substringBefore(this).contains(rule.substringAfter("|"))
            ) {
                mutableInputLine = mutableInputLine.replace(rule.substringAfter("|") + ",", "")
                mutableInputLine = mutableInputLine.replace(this, this + "," + rule.substringAfter("|"))
            }
            if (rule.endsWith(this) &&
                mutableInputLine.substringAfter(this).contains(rule.substringBefore("|"))
            ) {
                mutableInputLine = mutableInputLine.replace("," + rule.substringBefore("|"), "")
                mutableInputLine = mutableInputLine.replace(this, this + "," + rule.substringBefore("|"))
            }
        }
        return mutableInputLine
    }

    fun isInputLineValid(inputLine: String, rules: List<String>): Boolean {
        val numberList = inputLine.split(",")
        if (numberList.all { number -> number.isValid(inputLine, rules) })
            return true
        return false
    }

    fun convertToCorrectlyOrderedString(inputLine: String, rules: List<String>): String {
        var mutableInputLine = inputLine
        while (!isInputLineValid(mutableInputLine, rules)) {
            val numberList = mutableInputLine.split(",")
            for (number in numberList) {
                val updatedLine = number.ifInvalidCorrectOrder(mutableInputLine, rules)
                if (mutableInputLine != updatedLine) {
                    mutableInputLine = updatedLine
                }
            }
        }
        return mutableInputLine
    }

    fun part2(input: List<String>, rules: List<String>): Long {
        var sumMiddleNumberOfCorrectedUpdates = 0L
        for (inputLine in input) {
            val numberList = inputLine.split(",")
            if (!numberList.all { number -> number.isValid(inputLine, rules) }) {
                val correctlyOrderedString = convertToCorrectlyOrderedString(inputLine, rules)

                val correctlyOrderedList = correctlyOrderedString.split(",")
                sumMiddleNumberOfCorrectedUpdates += correctlyOrderedList[(correctlyOrderedList.size - 1) / 2].toLong()
            }
        }
        return sumMiddleNumberOfCorrectedUpdates
    }

    val testInput = readInput("Day05_test")
    val testRules = readInput("Day05_test_rules")

    val input = readInput("Day05")
    val rules = readInput("Day05_rules")

    part1(testInput, testRules).println()
    check(part1(testInput, testRules) == 143L)

    part1(input, rules).println()

    part2(testInput, testRules).println()
    check(part2(testInput, testRules) == 123L)

    part2(input, rules).println()
    check(part2(input, rules) == 5479L)
}

