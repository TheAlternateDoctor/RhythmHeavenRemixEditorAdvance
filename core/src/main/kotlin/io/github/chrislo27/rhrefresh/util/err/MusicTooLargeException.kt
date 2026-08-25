package io.github.chrislo27.rhrefresh.util.err

import io.github.chrislo27.rhrefresh.RHREfresh
import io.github.chrislo27.toolboks.i18n.Localization
import io.github.chrislo27.toolboks.util.MemoryUtils


class MusicTooLargeException(bytes: Long, val original: OutOfMemoryError) : MusicLoadingException(bytes) {

    override fun getLocalizedText(): String {
        return Localization["screen.music.tooBig", RHREfresh.OUT_OF_MEMORY_DOC_LINK,
                bytes / (1024 * 1024),
                MemoryUtils.maxMemory / 1024]
    }

}