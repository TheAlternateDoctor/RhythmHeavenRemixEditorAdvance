package io.github.chrislo27.rhrefresh.editor.stage

import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.screen.EditorScreen
import io.github.chrislo27.rhrefresh.screen.info.InfoScreen
import io.github.chrislo27.toolboks.i18n.Localization
import io.github.chrislo27.toolboks.registry.ScreenRegistry
import io.github.chrislo27.toolboks.ui.*


class InfoButton(val editor: Editor, palette: UIPalette, parent: UIElement<EditorScreen>,
                 stage: Stage<EditorScreen>)
    : Button<EditorScreen>(palette, parent, stage) {

    private val infoScreen: InfoScreen by lazy { ScreenRegistry.getNonNullAsType<InfoScreen>("info") }

    override var tooltipText: String?
        set(_) {}
        get() {
            return Localization["editor.info.tooltip"]
        }

    override fun onLeftClick(xPercent: Float, yPercent: Float) {
        super.onLeftClick(xPercent, yPercent)
        editor.main.screen = infoScreen
    }
}