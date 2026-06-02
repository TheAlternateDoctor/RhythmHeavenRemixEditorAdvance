package io.github.chrislo27.rhre3.editor.stage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import io.github.chrislo27.rhre3.PreferenceKeys
import io.github.chrislo27.rhre3.editor.Editor
import io.github.chrislo27.rhre3.screen.EditorScreen
import io.github.chrislo27.toolboks.i18n.Localization
import io.github.chrislo27.toolboks.registry.AssetRegistry
import io.github.chrislo27.toolboks.ui.Button
import io.github.chrislo27.toolboks.ui.ImageLabel
import io.github.chrislo27.toolboks.ui.Stage
import io.github.chrislo27.toolboks.ui.UIElement
import io.github.chrislo27.toolboks.ui.UIPalette
import kotlin.math.round

class VolumeButton(val editor: Editor, palette: UIPalette,
                   parent: UIElement<EditorScreen>,
                   stage: Stage<EditorScreen>)
    : Button<EditorScreen>(palette, parent, stage) {


    init {
        addLabel(ImageLabel(palette, this, this.stage).apply {
            this.renderType = ImageLabel.ImageRendering.ASPECT_RATIO
            this.image = TextureRegion(AssetRegistry.get<Texture>("ui_icon_sfx_volume"))
        })
    }

    private val preferences = Gdx.app.getPreferences("RHRE3")
    private var currentVolume = preferences.getFloat(PreferenceKeys.SETTINGS_AUDIO_VOLUME)

    override var tooltipText: String?
        set(_) {}
        get() {
            val volumeInPercentage = "%.0f".format(round(currentVolume*100))
            return "Volume (${volumeInPercentage}%)"
        }
}