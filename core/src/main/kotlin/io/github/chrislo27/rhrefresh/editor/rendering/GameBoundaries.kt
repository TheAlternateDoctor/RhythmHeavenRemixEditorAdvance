package io.github.chrislo27.rhrefresh.editor.rendering

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.chrislo27.rhrefresh.editor.Editor

fun Editor.renderGameBoundaryBg(batch: SpriteBatch, beatRange: IntRange) {
    val squareHeight = remix.trackCount.toFloat()
    val squareWidth = squareHeight / (Editor.ENTITY_WIDTH / Editor.ENTITY_HEIGHT)

    remix.gameSections.values.forEach { section ->
        if (section.startBeat > beatRange.last || section.endBeat < beatRange.first)
            return@forEach
        val tex = section.game.icon

        val sectionWidth = section.endBeat - section.startBeat
        val sections = (sectionWidth / squareWidth)
        val wholes = sections.toInt()
        val remainder = sectionWidth % squareWidth

        // track background icons
        batch.setColor(1f, 1f, 1f, 0.25f)
        for (i in 0 until wholes) {
            batch.draw(tex, section.startBeat + squareWidth * i, 0f,
                       squareWidth, squareHeight)
        }
        batch.draw(tex, section.startBeat + squareWidth * wholes, 0f,
                   remainder, squareHeight,
                   0, 0, (tex.width * (sections - wholes)).toInt(), tex.height,
                   false, false)
    }

    batch.setColor(1f, 1f, 1f, 1f)
}
