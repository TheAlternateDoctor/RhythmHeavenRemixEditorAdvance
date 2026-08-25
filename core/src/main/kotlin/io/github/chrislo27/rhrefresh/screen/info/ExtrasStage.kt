package io.github.chrislo27.rhrefresh.screen.info

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align
import io.github.chrislo27.rhrefresh.RHREfreshApplication
import io.github.chrislo27.rhrefresh.analytics.AnalyticsHandler
import io.github.chrislo27.toolboks.registry.AssetRegistry
import io.github.chrislo27.toolboks.ui.*


class ExtrasStage(parent: UIElement<InfoScreen>?, camera: OrthographicCamera, val infoScreen: InfoScreen)
    : Stage<InfoScreen>(parent, camera) {

    private val main: RHREfreshApplication get() = infoScreen.main
    
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