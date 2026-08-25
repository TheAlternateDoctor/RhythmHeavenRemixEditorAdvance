package io.github.chrislo27.rhrefresh.sfxdb.datamodel

import io.github.chrislo27.rhrefresh.modding.ModdingGame
import io.github.chrislo27.rhrefresh.modding.ModdingMetadata


interface ModdingModel {

    val moddingMetadata: Map<ModdingGame, ModdingMetadata>

}