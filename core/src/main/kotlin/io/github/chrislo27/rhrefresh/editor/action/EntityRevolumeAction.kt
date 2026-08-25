package io.github.chrislo27.rhrefresh.editor.action

import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.entity.Entity
import io.github.chrislo27.rhrefresh.entity.model.IVolumetric
import io.github.chrislo27.rhrefresh.undoredo.ReversibleAction
import io.github.chrislo27.rhrefresh.track.Remix


class EntityRevolumeAction(val editor: Editor, val entities: List<Entity>, val oldVolumes: List<Int>)
    : ReversibleAction<Remix> {

    var newVolumes: List<Int> = getVolumes()
        private set

    private fun getVolumes() = entities.map { (it as? IVolumetric)?.volumePercent ?: IVolumetric.DEFAULT_VOLUME }

    fun reloadNewVolumes() {
        newVolumes = getVolumes()
    }

    override fun redo(context: Remix) {
        entities.forEachIndexed { index, entity ->
            (entity as? IVolumetric)?.volumePercent = newVolumes[index]
        }
    }

    override fun undo(context: Remix) {
        entities.forEachIndexed { index, entity ->
            (entity as? IVolumetric)?.volumePercent = oldVolumes[index]
        }
    }
}