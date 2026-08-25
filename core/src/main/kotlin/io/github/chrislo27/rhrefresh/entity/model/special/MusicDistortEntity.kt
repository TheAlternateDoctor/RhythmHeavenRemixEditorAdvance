package io.github.chrislo27.rhrefresh.entity.model.special

import com.badlogic.gdx.graphics.Color
import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.entity.model.IStretchable
import io.github.chrislo27.rhrefresh.entity.model.ModelEntity
import io.github.chrislo27.rhrefresh.sfxdb.datamodel.impl.special.MusicDistortModel
import io.github.chrislo27.rhrefresh.theme.Theme
import io.github.chrislo27.rhrefresh.track.Remix


class MusicDistortEntity(remix: Remix, datamodel: MusicDistortModel)
    : ModelEntity<MusicDistortModel>(remix, datamodel), IStretchable {

    override val isStretchable: Boolean = true

    override fun getRenderColor(editor: Editor, theme: Theme): Color {
        return theme.entities.cue
    }

    override fun onStart() {
    }

    override fun whilePlaying() {
    }

    override fun onEnd() {
    }

    override fun copy(remix: Remix): MusicDistortEntity {
        return MusicDistortEntity(remix, datamodel).also {
            it.updateBounds {
                it.bounds.set(this.bounds)
            }
        }
    }


}