package io.github.chrislo27.rhrefresh.editor.action

import com.badlogic.gdx.math.Rectangle
import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.entity.Entity
import io.github.chrislo27.rhrefresh.undoredo.ReversibleAction
import io.github.chrislo27.rhrefresh.track.Remix


class EntityRemoveAction(val editor: Editor, val entities: List<Entity>, val oldPos: List<Rectangle>)
    : ReversibleAction<Remix> {

    override fun undo(context: Remix) {
        context.addEntities(entities, false)
        entities.forEachIndexed { index, entity ->
            entity.updateBounds {
                entity.bounds.set(oldPos[index])
            }
        }
    }

    override fun redo(context: Remix) {
        context.removeEntities(entities, false)
    }

}
