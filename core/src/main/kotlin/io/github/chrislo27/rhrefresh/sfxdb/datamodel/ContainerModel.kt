package io.github.chrislo27.rhrefresh.sfxdb.datamodel

import io.github.chrislo27.rhrefresh.sfxdb.datamodel.impl.CuePointer


interface ContainerModel {

    val cues: List<CuePointer>

}