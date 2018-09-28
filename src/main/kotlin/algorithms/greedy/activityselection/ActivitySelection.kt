package algorithms.greedy.activityselection

import misc.activity.Activity
import misc.activity.isCompatibleWith

fun scheduleActivities(activities: List<Activity>): Set<Activity> {

    val sortedActivities = activities.sortedBy { (_, interval) -> interval.endTime }

    var lastSelected = sortedActivities.first()    // stores the activity last selected
    val selectedActivities: MutableSet<Activity> = hashSetOf(lastSelected)

    sortedActivities.drop(1).forEach { activity ->
        if (activity.isCompatibleWith(lastSelected)) {
            selectedActivities += activity
            lastSelected = activity
        }
    }

    return selectedActivities
}