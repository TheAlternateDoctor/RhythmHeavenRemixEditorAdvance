package io.github.chrislo27.rhrefresh.editor.picker

import io.github.chrislo27.rhrefresh.sfxdb.Game


class CustomFilter : SimpleFilter({ it.games.any(Game::isCustom) }, Game::isCustom)
