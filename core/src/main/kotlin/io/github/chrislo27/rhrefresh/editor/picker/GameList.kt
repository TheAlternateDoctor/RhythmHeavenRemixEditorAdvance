package io.github.chrislo27.rhrefresh.editor.picker

import io.github.chrislo27.rhrefresh.editor.Editor
import io.github.chrislo27.rhrefresh.sfxdb.Game


class GameList : ScrollList<Game>() {

    override val maxScroll: Int
        get() = (list.size - Editor.ICON_COUNT_Y).coerceAtLeast(0)

}