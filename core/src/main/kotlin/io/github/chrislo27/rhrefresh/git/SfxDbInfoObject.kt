package io.github.chrislo27.rhrefresh.git

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.chrislo27.rhrefresh.RHREfresh


class SfxDbInfoObject {

    @JsonProperty("v")
    var version: Int = -1

    @JsonProperty("editor")
    var requiresVersion: String = RHREfresh.VERSION.toString()

    @JsonProperty("rsde")
    var rsdeVersion: String = ""

}