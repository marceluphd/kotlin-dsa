package algorithms.greedy.activityselection

import types.activity.Activity

/**
 * The [Activity Selection Problem](https://en.wikipedia.org/wiki/Activity_selection_problem).
 */
fun scheduleActivities(activities: List<Activity>): Set<Activity> {
    val sortedActivities = activities.sortedBy { (_, interval) -> interval.endTime }

    var lastSelected = sortedActivities.first()
    val selectedActivities: MutableSet<Activity> = hashSetOf(lastSelected)

    sortedActivities.drop(1).forEach { activity ->
        if (activity.isCompatibleWith(lastSelected)) {
            selectedActivities += activity
            lastSelected = activity
        }
    }

    return selectedActivities
}