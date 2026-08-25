package io.github.chrislo27.rhrefresh.editor.quickswitch

import io.github.chrislo27.rhrefresh.editor.stage.EditorStage
import io.github.chrislo27.rhrefresh.sfxdb.Game
import io.github.chrislo27.rhrefresh.sfxdb.GameGroup

data class SwitchToGame(val button: EditorStage.FilterButton,
                        val groupScroll: Int = button.filter.groupScroll,
                        val currentGroup: GameGroup? = if (button.filter.areGroupsEmpty) null else button.filter.currentGroup,
                        val currentGame: Game? = if (button.filter.areGamesEmpty) null else button.filter.currentGame)
