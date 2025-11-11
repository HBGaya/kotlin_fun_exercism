class BowlingGame {

    private val rolls = mutableListOf<Int>()

    fun roll(pins: Int) {

        // Determine frame progress before this roll
        val state = analyzeRolls(rolls)

        // If game already has a complete 10th frame, disallow more rolls
        if (state.framesCompleted >= 10 && state.tenthComplete) {
            throw IllegalStateException("Game already finished; cannot roll more")
        }

        // Validate frame pin totals for non-10th frames when applicable
        // Locate index within current frame (simulate to find index into current frame)
        val index = rolls.size
        val framesSoFar = state.framesCompleted

        if (framesSoFar < 9) {
            // We're in frames 1..9
            // Find if we're on the second roll of the current frame and validate sum <= 10
            val inFrameFirst = findFirstOfCurrentFrameIndex(rolls)
            if (inFrameFirst != null && index == inFrameFirst + 1) {
                // We are rolling the second ball of a non-10th frame
                val first = rolls[inFrameFirst]
                if (first != 10 && first + pins > 10) {
                    throw IllegalStateException("Pin count for frame exceeds pins standing")
                }
            }
        } else {
            // We're in 10th frame territory (framesSoFar >= 9)
            // Apply 10th frame pin rules:
            // - If first roll exists and is <10, then first + second must be <=10
            // - Bonus rolls after strike are independent (each 0..10)

            // If we're about to roll the second ball of 10th and first < 10 -> ensure first + pins <= 10
            val tenthStart = indexOfTenthFrameStart(rolls)
            if (tenthStart >= 0 && index == tenthStart + 1) {
                val f = rolls[tenthStart]
                if (f < 10 && f + pins > 10) {
                    throw IllegalStateException("Pin count in 10th frame exceeds pins standing")
                }
            }

            // If we're about to roll the third ball (a bonus roll), validate that a bonus is actually allowed
            if (tenthStart >= 0 && index >= tenthStart + 2) {
                // Need to check if bonus roll is permitted
                val firstRoll = rolls.getOrNull(tenthStart) ?: 0
                val secondRoll = rolls.getOrNull(tenthStart + 1) ?: 0
                val rollsInTenthSoFar = rolls.size - tenthStart
                // If first was strike -> two bonus rolls allowed
                // If spare (first + second == 10) -> one bonus roll allowed
                // Otherwise no third roll allowed
                if (firstRoll == 10) {
                    // two bonus rolls allowed -> adding more than 3 (including new) should be prevented by earlier guard
                    // no extra validation needed here for individual pin counts (each bonus can be 0..10)
                } else {
                    // firstRoll < 10
                    if (firstRoll + secondRoll == 10) {
                        // spare -> exactly one bonus allowed; ensure we don't exceed it
                        if (rollsInTenthSoFar >= 2 && rolls.size - tenthStart >= 2 && index >= tenthStart + 2) {
                            // allowed to roll one bonus (this will be the third). But disallow fourth.
                        }
                        // pin count of bonus roll may be 0..10 (it's a bonus); no sum-check with previous.
                    } else {
                        // No spare/strike in the first two balls -> no bonus allowed, so reaching here means disallowed
                        throw IllegalStateException("No bonus roll allowed in 10th frame")
                    }
                }
            }
        }

        // All checks passed, record the roll
        rolls.add(pins)
    }

    fun score(): Int {
        val state = analyzeRolls(rolls)

        // Must have exactly 10 frames and the 10th frame must be complete
        if (state.framesCompleted < 10 || !state.tenthComplete) {
            throw IllegalStateException("Cannot score incomplete game")
        }

        // Also ensure no extra rolls beyond allowed (defensive - analyzeRolls ensures tenthComplete means allowed set)
        // Now compute the score
        var totalScore = 0
        var rollIndex = 0

        for (frame in 1..10) {
            val first = rolls.getOrElse(rollIndex) { 0 }
            if (first == 10) {
                // strike
                totalScore += 10 + rolls.getOrElse(rollIndex + 1) { 0 } + rolls.getOrElse(rollIndex + 2) { 0 }
                rollIndex += 1
            } else {
                val second = rolls.getOrElse(rollIndex + 1) { 0 }
                if (first + second == 10) {
                    // spare
                    totalScore += 10 + rolls.getOrElse(rollIndex + 2) { 0 }
                } else {
                    // open
                    totalScore += first + second
                }
                rollIndex += 2
            }
        }

        return totalScore
    }

    // ---------- Helper utilities ----------

    // Analyze rolls and return framesCompleted (how many full frames 1..9 are completed + tenths presence)
    private data class Analysis(val framesCompleted: Int, val tenthComplete: Boolean)

    private fun analyzeRolls(rolls: List<Int>): Analysis {
        var i = 0
        var frames = 0

        // Count frames for frames 1..9
        while (frames < 9 && i < rolls.size) {
            val r = rolls[i]
            if (r == 10) {
                // strike frame uses 1 roll
                frames++
                i += 1
            } else {
                // need two rolls; if second doesn't exist yet, frame is not complete
                if (i + 1 < rolls.size) {
                    frames++
                    i += 2
                } else {
                    // incomplete frame
                    return Analysis(frames, tenthComplete = false)
                }
            }
        }

        // If we've counted less than 9 frames then tenth hasn't started - not complete
        if (frames < 9) return Analysis(frames, tenthComplete = false)

        // Now i is the start index of the 10th frame (may be equal to rolls.size if not started)
        val rollsInTenth = rolls.size - i
        if (rollsInTenth == 0) return Analysis(9, tenthComplete = false)

        val first = rolls.getOrNull(i) ?: 0
        if (first == 10) {
            // strike in first roll -> need 3 rolls in tenth (strike + two bonus)
            return Analysis(10, tenthComplete = (rollsInTenth >= 3))
        } else {
            // need at least two rolls in tenth to decide open/spare
            if (rollsInTenth < 2) return Analysis(9, tenthComplete = false)
            val second = rolls.getOrNull(i + 1) ?: 0
            return if (first + second == 10) {
                // spare -> need exactly 3 rolls (spare + one bonus)
                Analysis(10, tenthComplete = (rollsInTenth >= 3))
            } else {
                // open frame -> exactly 2 rolls
                Analysis(10, tenthComplete = (rollsInTenth >= 2))
            }
        }
    }

    // Find index where 10th frame starts. Returns 0..size or -1 if not enough frames to reach tenth.
    private fun indexOfTenthFrameStart(rolls: List<Int>): Int {
        var i = 0
        var frames = 0
        while (frames < 9 && i < rolls.size) {
            val r = rolls[i]
            if (r == 10) {
                frames++; i += 1
            } else {
                if (i + 1 < rolls.size) {
                    frames++; i += 2
                } else {
                    return -1
                }
            }
        }
        return if (frames >= 9) i else -1
    }

    // Find index of first roll of the current (unfinished) non-10th frame; return null if not in a non-10th incomplete frame
    private fun findFirstOfCurrentFrameIndex(rolls: List<Int>): Int? {
        var i = 0
        var frames = 0
        while (frames < 9 && i < rolls.size) {
            val r = rolls[i]
            if (r == 10) {
                frames++; i += 1
            } else {
                if (i + 1 < rolls.size) {
                    frames++; i += 2
                } else {
                    // second roll missing -> current frame first is at i
                    return i
                }
            }
        }
        // if frames < 9 and we are at the start of a new frame then return null (no first roll yet)
        return null
    }
}

fun main() {
    val game = BowlingGame()

    val rolls = listOf(
        10,        // strike
        5, 5,      // spare
        9, 0,      // open frame
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 // remaining frames (8 frames * 2 rolls)
    )

    rolls.forEach { game.roll(it) }

    println("Final Score: ${game.score()}")
}
