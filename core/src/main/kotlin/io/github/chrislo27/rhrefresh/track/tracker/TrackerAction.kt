package io.github.chrislo27.rhrefresh.track.tracker

import io.github.chrislo27.rhrefresh.undoredo.ReversibleAction
import io.github.chrislo27.rhrefresh.track.Remix


class TrackerAction(val tracker: Tracker<*>, val remove: Boolean) : ReversibleAction<Remix> {

    private val container: TrackerContainer<*> = tracker.container

    override fun redo(context: Remix) {
        if (remove) {
            container.remove(tracker)
        } else {
            container.add(tracker)
        }
    }

    override fun undo(context: Remix) {
        if (!remove) {
            container.remove(tracker)
        } else {
            container.add(tracker)
        }
    }
}
