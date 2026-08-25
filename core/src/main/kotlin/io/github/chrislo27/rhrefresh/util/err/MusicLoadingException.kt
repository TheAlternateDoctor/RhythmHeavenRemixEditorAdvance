package io.github.chrislo27.rhrefresh.util.err


abstract class MusicLoadingException(val bytes: Long) : RuntimeException() {

    abstract fun getLocalizedText(): String

}