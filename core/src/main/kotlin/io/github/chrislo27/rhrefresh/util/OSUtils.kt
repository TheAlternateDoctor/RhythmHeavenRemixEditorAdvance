package io.github.chrislo27.rhrefresh.util


object OSUtils {

    val IS_WINDOWS: Boolean by lazy { System.getProperty("os.name")?.startsWith("Windows") ?: false }

}