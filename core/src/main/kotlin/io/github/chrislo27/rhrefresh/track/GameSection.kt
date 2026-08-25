package io.github.chrislo27.rhrefresh.track

import io.github.chrislo27.rhrefresh.sfxdb.Game
import io.github.chrislo27.rhrefresh.sfxdb.GameGroup


class GameSection(val startBeat: Float, val endBeat: Float, val game: Game) {

    val gameGroup: GameGroup
        get() = game.gameGroup

}