package io.github.chrislo27.rhrefresh.editor.picker

import io.github.chrislo27.rhrefresh.sfxdb.Series


class PickerSelection {

    var filter: Filter = SeriesFilter.allSeriesFilters[Series.TENGOKU] ?: error("Default filter not found")
        set(value) {
            field = value
            field.update()
        }

}