package io.github.chrislo27.rhre3.screen.info

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align
import io.github.chrislo27.rhre3.PreferenceKeys
import io.github.chrislo27.rhre3.RHRE3Application
import io.github.chrislo27.rhre3.analytics.AnalyticsHandler
import io.github.chrislo27.rhre3.editor.Editor
import io.github.chrislo27.rhre3.extras.*
import io.github.chrislo27.rhre3.util.FadeIn
import io.github.chrislo27.rhre3.util.FadeOut
import io.github.chrislo27.rhre3.util.WipeFrom
import io.github.chrislo27.rhre3.util.WipeTo
import io.github.chrislo27.toolboks.registry.AssetRegistry
import io.github.chrislo27.toolboks.registry.ScreenRegistry
import io.github.chrislo27.toolboks.transition.TransitionScreen
import io.github.chrislo27.toolboks.ui.*
import io.github.chrislo27.toolboks.util.gdxutils.isShiftDown


class ExtrasStage(parent: UIElement<InfoScreen>?, camera: OrthographicCamera, val infoScreen: InfoScreen)
    : Stage<InfoScreen>(parent, camera) {

    private val main: RHRE3Application get() = infoScreen.main
    
    init {
        val palette = infoScreen.stage.palette
        val padding = 0.025f
        val buttonWidth = 0.45f
        val buttonHeight = 0.1f
        val squareWidth = 38f / 456f
        val fontScale = 0.75f

        this.elements += Button(palette, this, this).apply {
            addLabel(TextLabel(palette, this, this.stage).apply {
                this.fontScaleMultiplier = fontScale
                this.isLocalizationKey = false
                this.textWrapping = false
                this.textAlign = Align.center
                this.text = "Bouncy Road Mania"
                this.location.set(screenX = squareWidth * 1.25f, screenWidth = 1f - squareWidth * 2.5f)
            })
            addLabel(ImageLabel(palette, this, this.stage).apply {
                this.location.set(screenX = 0f, screenWidth = squareWidth, pixelX = 1f, pixelWidth = -2f, pixelY = 1f, pixelHeight = -2f)
                this.image = TextureRegion(AssetRegistry.get<Texture>("ui_icon_bouncy_road_mania"))
            })
            this.location.set(screenX = padding,
                              screenY = padding * 7 + buttonHeight * 6,
                              screenWidth = buttonWidth,
                              screenHeight = buttonHeight)
            this.leftClickAction = { _, _ ->
                AnalyticsHandler.track("View Bouncy Road Mania", mapOf())
                Gdx.net.openURI("""https://github.com/chrislo27/BouncyRoadMania""")
            }
            this.tooltipTextIsLocalizationKey = true
            this.tooltipText = "extras.bouncyRoadMania.tooltip"
        }

        this.elements += Button(palette, this, this).apply {
            addLabel(TextLabel(palette, this, this.stage).apply {
                this.fontScaleMultiplier = fontScale
                this.isLocalizationKey = false
                this.textWrapping = false
                this.textAlign = Align.center
                this.text = "Polyrhythm Mania"
                this.location.set(screenX = squareWidth * 1.25f, screenWidth = 1f - squareWidth * 2.5f)
            })
            addLabel(ImageLabel(palette, this, this.stage).apply {
                this.location.set(screenX = 0f, screenWidth = squareWidth, pixelX = 1f, pixelWidth = -2f, pixelY = 1f, pixelHeight = -2f)
                this.image = TextureRegion(AssetRegistry.get<Texture>("ui_icon_polyrhythm_mania"))
            })
            this.location.set(screenX = 1f - (padding + buttonWidth),
                              screenY = padding * 7 + buttonHeight * 6,
                              screenWidth = buttonWidth,
                              screenHeight = buttonHeight)
            this.leftClickAction = { _, _ ->
                AnalyticsHandler.track("View Polyrhythm Mania", mapOf())
                Gdx.net.openURI("""https://github.com/chrislo27/PolyrhythmMania""")
            }
            this.tooltipTextIsLocalizationKey = true
            this.tooltipText = "extras.polyrhythmMania.tooltip"
        }
    }
}