package io.github.chrislo27.rhrefresh.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Align
import io.github.chrislo27.rhrefresh.PreferenceKeys
import io.github.chrislo27.rhrefresh.RHREfresh
import io.github.chrislo27.rhrefresh.RHREfreshApplication
import io.github.chrislo27.rhrefresh.RemixRecovery
import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.sfxdb.GameMetadata
import io.github.chrislo27.rhrefresh.stage.GenericStage
import io.github.chrislo27.rhrefresh.stage.LoadingIcon
import io.github.chrislo27.rhrefresh.track.Remix
import io.github.chrislo27.rhrefresh.util.*
import io.github.chrislo27.toolboks.ToolboksScreen
import io.github.chrislo27.toolboks.i18n.Localization
import io.github.chrislo27.toolboks.registry.AssetRegistry
import io.github.chrislo27.toolboks.registry.ScreenRegistry
import io.github.chrislo27.toolboks.ui.ImageLabel
import io.github.chrislo27.toolboks.ui.TextLabel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SaveRemixScreen(main: RHREfreshApplication)
    : ToolboksScreen<RHREfreshApplication, SaveRemixScreen>(main) {

    private val editorScreen: EditorScreen by lazy { ScreenRegistry.getNonNullAsType<EditorScreen>("editor") }
    private val editor: Editor
        get() = editorScreen.editor
    override val stage: GenericStage<SaveRemixScreen> = GenericStage(main.uiPalette, null, main.defaultCamera)

    @Volatile
    private var isChooserOpen = false
        set(value) {
            field = value
            stage.backButton.enabled = !isChooserOpen
        }
    @Volatile
    private var isSaving: Boolean = false
    private val mainLabel: TextLabel<SaveRemixScreen>

    init {
        stage.titleIcon.image = TextureRegion(AssetRegistry.get<Texture>("ui_icon_save"))
        stage.titleLabel.text = "screen.save.title"
        stage.backButton.visible = true
        stage.onBackButtonClick = {
            if (!isChooserOpen) {
                main.screen = ScreenRegistry.getNonNull("editor")
            }
        }

        stage.centreStage.elements += object : LoadingIcon<SaveRemixScreen>(main.uiPalette, stage.centreStage) {
            override var visible: Boolean = true
                get() = field && isSaving
        }.apply {
            this.renderType = ImageLabel.ImageRendering.ASPECT_RATIO
            this.location.set(screenHeight = 0.125f, screenY = 0.125f / 2f)
        }

        val palette = main.uiPalette
        stage.centreStage.elements += object : TextLabel<SaveRemixScreen>(palette, stage.centreStage,
                                                                          stage.centreStage) {
            override fun frameUpdate(screen: SaveRemixScreen) {
                super.frameUpdate(screen)
                this.visible = isChooserOpen
            }
        }.apply {
            this.location.set(screenHeight = 0.25f)
            this.textAlign = Align.center
            this.isLocalizationKey = true
            this.text = "closeChooser"
            this.visible = false
        }
        mainLabel = TextLabel(palette, stage.centreStage, stage.centreStage).apply {
            this.location.set(screenHeight = 0.75f, screenY = 0.25f)
            this.textAlign = Align.center
            this.isLocalizationKey = false
            this.text = ""
        }
        stage.centreStage.elements += mainLabel

        stage.updatePositions()
        updateLabels(null)
    }

    override fun renderUpdate() {
        super.renderUpdate()

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            stage.onBackButtonClick()
        }
    }

    @Synchronized
    private fun openPicker() {
        if (!isChooserOpen) {
            GlobalScope.launch {
                isChooserOpen = true
                val lastSaveFile = editor.lastSaveFile
                val filter = TinyFDWrapper.FileExtFilter(Localization["screen.save.fileFilter"] + "(.${RHREfresh.REMIX_FILE_EXTENSION})", "*.${RHREfresh.REMIX_FILE_EXTENSION}")
                TinyFDWrapper.saveFile(Localization["screen.save.fileChooserTitle"], lastSaveFile?.file() ?: lastSaveFile?.parent()?.file() ?: attemptRememberDirectory(main, PreferenceKeys.FILE_CHOOSER_SAVE) ?: getDefaultDirectory(), filter) { file ->
                    isChooserOpen = false
                    if (file != null) {
                        val newInitialDirectory = if (!file.isDirectory) file.parentFile else file
                        persistDirectory(main, PreferenceKeys.FILE_CHOOSER_SAVE, newInitialDirectory)
                        GlobalScope.launch {
                            try {
                                val correctFile = if (file.extension == "rhre3")
                                    file.parentFile.resolve("${file.nameWithoutExtension}.${RHREfresh.REMIX_FILE_EXTENSION}")
                                else if (file.extension != RHREfresh.REMIX_FILE_EXTENSION)
                                    file.parentFile.resolve("${file.name}.${RHREfresh.REMIX_FILE_EXTENSION}")
                                else
                                    file

                                val remix = editor.remix
                                isSaving = true
                                Remix.saveTo(remix, correctFile, false)
                                val newfh = FileHandle(correctFile)
                                editor.setFileHandles(newfh)
                                RemixRecovery.cacheChecksumOfFile(newfh)

                                mainLabel.text = Localization["screen.save.success"]
                                Gdx.app.postRunnable(GameMetadata::persist)
                            } catch (t: Throwable) {
                                t.printStackTrace()
                                updateLabels(t)
                            }
                            isSaving = false
                        }
                    } else {
                        stage.onBackButtonClick()
                    }
                }
            }
        }
    }

    private fun updateLabels(throwable: Throwable? = null) {
        val label = mainLabel
        if (throwable == null) {
            label.text = ""
        } else {
            label.text = Localization["screen.save.failed", throwable::class.java.canonicalName]
        }
    }

    override fun show() {
        super.show()
        openPicker()
        updateLabels()
        isSaving = false
    }

    override fun dispose() {
    }

    override fun tickUpdate() {
    }
}