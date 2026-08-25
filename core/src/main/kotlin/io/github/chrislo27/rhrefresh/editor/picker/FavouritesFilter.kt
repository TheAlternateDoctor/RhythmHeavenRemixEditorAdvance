package io.github.chrislo27.rhrefresh.editor.picker

import io.github.chrislo27.rhrefresh.sfxdb.Game


class FavouritesFilter : SimpleFilter(
        { it.isFavourited || it.games.any(Game::isFavourited) },
        gameFilter = { it.isFavourited || it.gameGroup.isFavourited }
                                     )