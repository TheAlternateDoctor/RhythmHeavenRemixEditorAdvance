package io.github.chrislo27.rhrefresh.entity.model


interface IEditableText {

    var text: String
    val canInputNewlines: Boolean
        get() = false

}
