package io.github.chrislo27.rhrefresh.editor.action

import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.entity.Entity
import io.github.chrislo27.rhrefresh.undoredo.ReversibleAction
import io.github.chrislo27.rhrefresh.track.Remix


class EntityPlaceAction(val editor: Editor, val entities: List<Entity>) : ReversibleAction<Remix> {

    override fun redo(context: Remix) {
        context.addEntities(entities, false)
    }

    override fun undo(context: Remix) {
        context.removeEntities(entities, false)
    }

}
