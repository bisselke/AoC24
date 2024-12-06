fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
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
