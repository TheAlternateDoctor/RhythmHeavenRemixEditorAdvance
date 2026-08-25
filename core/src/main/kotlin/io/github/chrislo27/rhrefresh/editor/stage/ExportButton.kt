package io.github.chrislo27.rhrefresh.editor.stage

import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.screen.EditorScreen
import io.github.chrislo27.rhrefresh.screen.ExportRemixScreen
import io.github.chrislo27.toolboks.i18n.Localization
import io.github.chrislo27.toolboks.ui.Button
import io.github.chrislo27.toolboks.ui.Stage
import io.github.chrislo27.toolboks.ui.UIElement
import io.github.chrislo27.toolboks.ui.UIPalette


class ExportButton(val editor: Editor,
                   palette: UIPalette, parent: UIElement<EditorScreen>,
                   stage: Stage<EditorScreen>)
    : Button<EditorScreen>(palette, parent, stage) {

    override var tooltipText: String?
        set(_) {}
        get() {
            return Localization["editor.exportButton"]
        }

    override fun onLeftClick(xPercent: Float, yPercent: Float) {
        super.onLeftClick(xPercent, yPercent)
        editor.main.screen = ExportRemixScreen(editor.main)
    }
}