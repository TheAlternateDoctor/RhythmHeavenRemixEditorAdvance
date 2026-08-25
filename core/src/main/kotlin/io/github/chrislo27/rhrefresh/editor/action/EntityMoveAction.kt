package io.github.chrislo27.rhrefresh.editor.action

import com.badlogic.gdx.math.Rectangle
import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.entity.Entity
import io.github.chrislo27.rhrefresh.undoredo.ReversibleAction
import io.github.chrislo27.rhrefresh.track.Remix


class EntityMoveAction(val editor: Editor, val entities: List<Entity>, val oldPos: List<Rectangle>)
    : ReversibleAction<Remix> {

    private val newPos = entities.map { Rectangle(it.bounds) }

    override fun redo(context: Remix) {
        entities.forEachIndexed { i, it ->
            it.updateBounds {
                it.bounds.set(newPos[i])
            }
        }
        context.recomputeCachedData()
    }

    override fun undo(context: Remix) {
        entities.forEachIndexed { i, it ->
            it.updateBounds {
                it.bounds.set(oldPos[i])
            }
        }
        context.recomputeCachedData()
    }

}
