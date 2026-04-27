package io.github.chrislo27.toolboks.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import io.github.chrislo27.rhre3.stage.ColourPicker
import io.github.chrislo27.toolboks.ToolboksScreen
import io.github.chrislo27.toolboks.registry.AssetRegistry
import io.github.chrislo27.toolboks.ui.Label
import io.github.chrislo27.toolboks.util.gdxutils.getInputX

open class MovingArrow<S : ToolboksScreen<*, *>>
    : UIElement<S>, Palettable, Backgrounded {

    final override var palette: UIPalette
    override var background: Boolean = false

    constructor(palette: UIPalette, parent: UIElement<S>, stage: Stage<S>) : super(parent, stage) {
        this.palette = palette
    }

    var percentage = 0f

    var onPercentageChange: (value: Float) -> Unit = {}

    override fun render(screen: S, batch: SpriteBatch, shapeRenderer: ShapeRenderer) {
        if (wasClickedOn && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            val old = percentage
            percentage = ((stage.camera.getInputX() - location.realX) / location.realWidth).coerceIn(0f, 1f)
            if (percentage != old) {
                onPercentageChange(percentage)
            }
        }

        val tex = AssetRegistry.get<Texture>("ui_colour_picker_arrow")
        val height = location.realHeight / 2f
        batch.setColor(1f, 1f, 1f, 1f)
        batch.draw(tex, location.realX + location.realWidth * percentage - height / 2, location.realY,
            height, height)
    }

    override fun canBeClickedOn(): Boolean {
        return true
    }
}