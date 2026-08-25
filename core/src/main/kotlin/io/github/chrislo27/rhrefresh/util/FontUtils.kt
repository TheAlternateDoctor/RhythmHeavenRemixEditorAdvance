package io.github.chrislo27.rhrefresh.util

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import io.github.chrislo27.rhrefresh.RHREfreshApplication


fun BitmapFont.scaleFont(camera: OrthographicCamera) {
    this.setUseIntegerPositions(false)
    this.data.setScale(camera.viewportWidth / RHREfreshApplication.instance.defaultCamera.viewportWidth,
                       camera.viewportHeight / RHREfreshApplication.instance.defaultCamera.viewportHeight)
}

fun BitmapFont.unscaleFont() {
    this.setUseIntegerPositions(true)
    this.data.setScale(1f)
}