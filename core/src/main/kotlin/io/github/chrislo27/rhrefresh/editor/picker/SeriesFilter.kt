package io.github.chrislo27.rhrefresh.editor.picker

import io.github.chrislo27.rhrefresh.sfxdb.Series


class SeriesFilter(val series: Series) : SimpleFilter({ it.series == series }) {

    companion object {
        val allSeriesFilters: Map<Series, SeriesFilter> = Series.VALUES.associateWith { SeriesFilter(it) }
    }

}