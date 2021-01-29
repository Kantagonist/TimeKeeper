package com.example.timekeeper.internal

/**
 * A singleton, which holds the variables of the program.
 */
object TimeKeeperModel{
    var TimePoints : List<TimePoint>
    val CSVPath : String = "/Users/henrikhatje/AndroidStudioProjects/TimeKeeper/app/src/main/java/com/example/timekeeper/internal/timePoints.csv"

    init {
        TimePoints = mutableListOf()
    }
}
