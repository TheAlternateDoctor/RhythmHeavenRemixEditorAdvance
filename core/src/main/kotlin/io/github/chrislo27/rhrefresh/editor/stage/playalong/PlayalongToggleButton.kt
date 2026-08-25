package io.github.chrislo27.rhrefresh.editor.stage.playalong

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import io.github.chrislo27.rhrefresh.RHREfreshApplication
import io.github.chrislo27.rhrefresh.editor.Tool
import io.github.chrislo27.rhrefresh.editor.stage.EditorStage
import io.github.chrislo27.rhrefresh.screen.EditorScreen
import io.github.chrislo27.rhrefresh.screen.PlayalongSettingsScreen
import io.github.chrislo27.toolboks.i18n.Localization
import io.github.chrislo27.toolboks.registry.AssetRegistry
import io.github.chrislo27.toolboks.ui.*
import io.github.chrislo27.toolboks.util.MathHelper


class PlayalongToggleButton(val editorStage: EditorStage, palette: UIPalette, parent: UIElement<EditorScreen>, stage: Stage<EditorScreen>)
    : Button<EditorScreen>(palette, parent, stage) {

    private val main: RHREfreshApplication get() = editorStage.main
    private val label = ImageLabel(palette, this, this.stage).apply {
        this.renderType = ImageLabel.ImageRendering.ASPECT_RATIO
        this.image = TextureRegion(AssetRegistry.get<Texture>("ui_icon_playalong_button"))
    }

    init {
        this.addLabel(label)
    }

    override var tooltipText: String?
        set(_) {}
        get() = Localization["editor.playalong"]

    override fun render(screen: EditorScreen, batch: SpriteBatch, shapeRenderer: ShapeRenderer) {
        if (editorStage.playalongStage.visible) {
            label.tint.fromHsv(MathHelper.getSawtoothWave(1.5f) * 360f, 0.3f, 0.75f)
        } else {
            label.tint.set(1f, 1f, 1f, 1f)
        }

        super.render(screen, batch, shapeRenderer)
    }

    override fun onLeftClick(xPercent: Float, yPercent: Float) {
        super.onLeftClick(xPercent, yPercent)
        val stage = editorStage
        val visible = !stage.playalongStage.visible
        val editor = stage.editor
        stage.elements.filterIsInstance<Stage<*>>().forEach {
            it.visible = !visible
        }
        stage.playalongStage.visible = visible
        stage.subtitleStage.visible = true // Exception made for subtitles
        stage.tapalongStage.visible = false
        stage.presentationModeStage.visible = false
        stage.paneLikeStages.forEach { it.visible = false }
        stage.buttonBarStage.visible = true
        stage.messageBarStage.visible = true
        stage.centreAreaStage.visible = true
        if (visible) {
            editor.currentTool = Tool.SELECTION
        }
        stage.updateSelected()
        editor.updateMessageLabel()
    }

    override fun onRightClick(xPercent: Float, yPercent: Float) {
        super.onRightClick(xPercent, yPercent)
        main.screen = PlayalongSettingsScreen(main, main.screen)
    }

}