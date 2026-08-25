package io.github.chrislo27.rhrefresh.modding

import io.github.chrislo27.rhrefresh.RHREfreshApplication


object ModdingUtils {

    val moddingToolsEnabled: Boolean get() = RHREfreshApplication.instance.settings.advancedOptions
    var currentGame: ModdingGame = ModdingGame.DEFAULT_GAME

}
