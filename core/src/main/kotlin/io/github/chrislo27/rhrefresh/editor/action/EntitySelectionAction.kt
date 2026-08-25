package io.github.chrislo27.rhrefresh.editor.action

import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.entity.Entity
import io.github.chrislo27.rhrefresh.undoredo.ReversibleAction
import io.github.chrislo27.rhrefresh.track.Remix


class EntitySelectionAction(val editor: Editor, val old: List<Entity>, val new: List<Entity>)
    : ReversibleAction<Remix> {

    override fun redo(context: Remix) {
        editor.selection = new.toList()
    }

    override fun undo(context: Remix) {
        editor.selection = old.toList()
    }
}