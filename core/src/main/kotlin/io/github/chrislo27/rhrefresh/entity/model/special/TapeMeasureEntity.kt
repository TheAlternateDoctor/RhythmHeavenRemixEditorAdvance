package io.github.chrislo27.rhrefresh.entity.model.special

import com.badlogic.gdx.graphics.Color
import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.entity.model.IStretchable
import io.github.chrislo27.rhrefresh.entity.model.ModelEntity
import io.github.chrislo27.rhrefresh.modding.ModdingUtils
import io.github.chrislo27.rhrefresh.sfxdb.datamodel.impl.special.TapeMeasure
import io.github.chrislo27.rhrefresh.theme.Theme
import io.github.chrislo27.rhrefresh.track.Remix


class TapeMeasureEntity(remix: Remix, datamodel: TapeMeasure)
    : ModelEntity<TapeMeasure>(remix, datamodel), IStretchable {

    override val isStretchable: Boolean = true
    override val renderText: String
        get() = "${Editor.THREE_DECIMAL_PLACES_FORMATTER.format(bounds.width)} ♩${if (ModdingUtils.moddingToolsEnabled) "\n" + ModdingUtils.currentGame.beatsToTickflowString(bounds.width) else ""}"

    init {
        bounds.height = 1f
    }

    override fun onStart() {
    }

    override fun whilePlaying() {
    }

    override fun onEnd() {
    }

    override fun getRenderColor(editor: Editor, theme: Theme): Color {
        return theme.entities.keepTheBeat
    }

    override fun copy(remix: Remix): TapeMeasureEntity {
        return TapeMeasureEntity(remix, datamodel).also {
            it.updateBounds {
                it.bounds.set(this@TapeMeasureEntity.bounds)
            }
        }
    }

}
