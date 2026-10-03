#!/usr/bin/env kotlin

import java.util.PriorityQueue

/*
This problem was asked by Snapchat.

Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.

For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
*/

fun getClassroomsCount(schedules: List<List<Int>>): Int {
    if (schedules.isEmpty()) {
        return 0
    }

    val sorted = schedules.sortedBy { it[0] }
    val roomEndTimes = PriorityQueue<Int>()

    for (schedule in sorted) {
        val start = schedule[0]
        val end = schedule[1]

        if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= start) {
            roomEndTimes.poll();
        }

        roomEndTimes.offer(end);
    }

    return roomEndTimes.count();
}

getClassroomsCount(listOf(listOf(30, 75), listOf(0, 50), listOf(60, 150)))
