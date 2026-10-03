#!/usr/bin/env kotlin

import java.util.PriorityQueue

/*
This problem was asked by Snapchat.

Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.

For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
*/

fun getClassroomsCount(schedules: List<Pair<Int, Int>>): Int {
    if (schedules.isEmpty()) {
        return 0
    }

    val sorted = schedules.sortedBy { it.first }
    val roomEndTimes = PriorityQueue<Int>()

    for ((start, end) in sorted) {

        if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= start) {
            roomEndTimes.poll()
        }

        roomEndTimes.offer(end)
    }

    return roomEndTimes.count()
}

getClassroomsCount(listOf(Pair(30, 75), Pair(0, 50), Pair(60, 150)))
