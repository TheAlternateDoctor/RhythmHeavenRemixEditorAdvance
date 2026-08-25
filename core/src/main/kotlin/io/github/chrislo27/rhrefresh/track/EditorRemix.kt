package io.github.chrislo27.rhrefresh.track

import io.github.chrislo27.rhrefresh.RHREfreshApplication
import io.github.chrislo27.rhrefresh.editor.Editor


class EditorRemix(main: RHREfreshApplication, val editor: Editor) : Remix(main) {

    override var doUpdatePlayalong: Boolean
        get() = editor.stage.playalongStage.visible
        set(_) {}
    override var cuesMuted: Boolean = super.cuesMuted
        get() = field && editor.stage.tapalongStage.visible
}