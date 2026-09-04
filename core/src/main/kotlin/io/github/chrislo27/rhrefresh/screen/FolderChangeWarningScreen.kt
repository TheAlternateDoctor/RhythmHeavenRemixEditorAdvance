package io.github.chrislo27.rhrefresh.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align
import io.github.chrislo27.rhrefresh.PreferenceKeys
import io.github.chrislo27.rhrefresh.RHREfresh
import io.github.chrislo27.rhrefresh.RHREfreshApplication
import io.github.chrislo27.rhrefresh.RemixRecovery
import io.github.chrislo27.rhrefresh.stage.FalseCheckbox
import io.github.chrislo27.rhrefresh.stage.GenericStage
import io.github.chrislo27.toolboks.ToolboksScreen
import io.github.chrislo27.toolboks.i18n.Localization
import io.github.chrislo27.toolboks.registry.AssetRegistry
import io.github.chrislo27.toolboks.registry.ScreenRegistry
import io.github.chrislo27.toolboks.ui.Button
import io.github.chrislo27.toolboks.ui.ImageLabel
import io.github.chrislo27.toolboks.ui.TextLabel
import java.awt.Desktop
import java.io.File
import kotlin.collections.plusAssign


class FolderChangeWarningScreen(main: RHREfreshApplication) : ToolboksScreen<RHREfreshApplication, FolderChangeWarningScreen>(main) {

    override val stage: GenericStage<FolderChangeWarningScreen> = GenericStage(main.uiPalette, null, main.defaultCamera)

    private val folderButton: Button<FolderChangeWarningScreen>

    init {
        stage.titleIcon.apply {
            this.image = TextureRegion(AssetRegistry.get<Texture>("ui_icon_warn"))
            this.renderType = ImageLabel.ImageRendering.ASPECT_RATIO
        }
        stage.titleLabel.apply {
            this.isLocalizationKey = true
            this.text = "screen.folderChangeWarning.title"
        }
        stage.backButton.apply {
            this.enabled = false
            this.visible = false
        }

        val palette = main.uiPalette

        stage.centreStage.elements += TextLabel(palette, stage.centreStage, stage.centreStage).apply {
            this.isLocalizationKey = false
            this.textWrapping = false
            this.text = when(RHREfresh.CURRENT_OS) {
                RHREfresh.OS.WINDOWS -> Localization["screen.folderChangeWarning.content", "%USERPROFILE%/"+RHREfresh.RHREFRESH_FOLDER]
                RHREfresh.OS.LINUX -> Localization["screen.folderChangeWarning.content", "~/"+RHREfresh.RHREFRESH_FOLDER]
                RHREfresh.OS.MACOS -> TODO()
                RHREfresh.OS.UNKNOWN -> Localization["screen.folderChangeWarning.content", RHREfresh.RHREFRESH_FOLDER]
            }

            this.textColor = Color.LIGHT_GRAY

        }
        stage.bottomStage.elements += Button(palette.copy(highlightedBackColor = Color(0f, 1f, 0f, 0.5f), clickedBackColor = Color(0.5f, 1f, 0.5f, 0.5f)), stage.bottomStage, stage.bottomStage).apply {
            this.location.set(screenX = 0.2f, screenWidth = 0.6f)
            addLabel(TextLabel(palette, this, this.stage).apply {
                this.text = "screen.folderChangeWarning.button"
                this.isLocalizationKey = true
            })
            this.leftClickAction = { _, _ ->
                main.preferences.putBoolean(PreferenceKeys.PASSED_FOLDER_CHANGE_WARNING, true)
                val screen = ScreenRegistry[if (RHREfresh.skipGitScreen) "sfxdbLoad" else "databaseUpdate"]
                main.screen = screen
            }
        }
        folderButton = Button(palette, stage.bottomStage, stage.bottomStage).apply {
            this.addLabel(ImageLabel(palette, this, this.stage).apply {
                this.image = TextureRegion(AssetRegistry.get<Texture>("ui_icon_folder"))
            })
            this.tooltipText = "screen.folderChangeWarning.openFoldersButton"
            this.tooltipTextIsLocalizationKey = true
            this.leftClickAction = { _, _ ->
                val newCustomSounds = RHREfresh.RHREFRESH_FOLDER.child("customSounds/").file()
                val oldCustomSounds = Gdx.files.external(".rhre3/customSounds").file()
                if (newCustomSounds != null) {
                    Desktop.getDesktop().open(newCustomSounds)
                }
                Desktop.getDesktop().open(oldCustomSounds)
            }

            this.location.set(this@FolderChangeWarningScreen.stage.backButton.location)
            this.location.set(screenX = 1f - this.location.screenWidth)
        }
        if(RHREfresh.CURRENT_OS != RHREfresh.OS.MACOS) {
            stage.bottomStage.elements += folderButton
        }

        stage.updatePositions()
    }

    override fun renderUpdate() {
        super.renderUpdate()
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            stage.onBackButtonClick()
        }
    }

    override fun tickUpdate() {
    }

    override fun dispose() {
    }
}
