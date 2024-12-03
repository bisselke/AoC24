fun main() {

    fun getMulResult(mul: String): Long {
        val number1 = mul.substringBefore(",").substringAfter("mul(").toLong()
        val number2 = mul.substringAfter(",").substringBefore(")").toLong()
        return (number1 * number2)
    }

    fun part1(input: List<String>): Long {
        var endResult: Long = 0
        for (i in input) {
            Regex("""mul\(\d{1,3},\d{1,3}\)""").findAll(i).iterator()
                .forEach { endResult += getMulResult(it.value) }
        }
        return endResult
    }

    fun changeDoOrDont(regexResult: String, currentDoo: Boolean): Boolean {
        if (currentDoo && Regex("""(don't\(\))""").containsMatchIn(regexResult)) {
            return false
        } else if (!currentDoo && Regex("""(do\(\))""").containsMatchIn(regexResult)) {
            return true
        }
        return currentDoo
    }

    fun part2(input: List<String>): Long {
        var endResult: Long = 0
        var doo = true
        for (i in input) {
            Regex("""(mul\(\d{1,3},\d{1,3}\))|(do\(\))|(don't\(\))""").findAll(i).iterator()
                .forEach {
                    if (Regex("""(do\(\))|(don't\(\))""").containsMatchIn(it.value)) {
                        doo = changeDoOrDont(it.value, doo)
                    } else if (doo) {
                        endResult += getMulResult(it.value)
                    }
                }
        }
        return endResult
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    part1(testInput).println()
    check(part1(testInput) == 161L)

    part1(input).println()


    val testInput2 = readInput("Day03_test_2")
    part2(testInput2).println()
    check(part2(testInput2) == 48L)

    part2(input).println()
}
