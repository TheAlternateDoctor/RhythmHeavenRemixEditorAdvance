package io.github.chrislo27.rhrefresh.track.timesignature

import io.github.chrislo27.rhrefresh.undoredo.ReversibleAction
import io.github.chrislo27.rhrefresh.track.Remix


class TimeSigValueChange(val original: TimeSignature, var current: TimeSignature)
    : ReversibleAction<Remix> {

    private val container = original.container

    override fun redo(context: Remix) {
        container.remove(original)
        container.add(current)
    }

    override fun undo(context: Remix) {
        container.remove(current)
        container.add(original)
    }
}