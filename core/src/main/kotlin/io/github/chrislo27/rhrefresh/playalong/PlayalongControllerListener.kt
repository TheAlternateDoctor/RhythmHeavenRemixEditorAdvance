package io.github.chrislo27.rhrefresh.playalong

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.ControllerListener
import io.github.chrislo27.rhrefresh.track.PlayState
import io.github.chrislo27.toolboks.Toolboks
import java.util.*


class PlayalongControllerListener(val playalongGetter: () -> Playalong) : ControllerListener {

    private fun getMapping(controller: Controller): ControllerMapping? = Playalong.activeControllerMappings[controller]

    private val playalong: Playalong get() = playalongGetter()

    override fun connected(controller: Controller) {
        Toolboks.LOGGER.info("[PlayalongControllerListener] Controller ${controller.name} connected")
    }

    override fun disconnected(controller: Controller) {
        Toolboks.LOGGER.info("[PlayalongControllerListener] Controller ${controller.name} disconnected")
    }

    private fun ControllerMapping.containsAnyButton(buttonCode: Int): Boolean {
        val buttonA = buttonA
        val buttonB = buttonB
        val buttonLeft = buttonLeft
        val buttonRight = buttonRight
        val buttonUp = buttonUp
        val buttonDown = buttonDown
        return (buttonA is ControllerInput.Button && buttonA.code == buttonCode) ||
                (buttonB is ControllerInput.Button && buttonB.code == buttonCode) ||
                (buttonUp is ControllerInput.Button && buttonUp.code == buttonCode) ||
                (buttonDown is ControllerInput.Button && buttonDown.code == buttonCode) ||
                (buttonLeft is ControllerInput.Button && buttonLeft.code == buttonCode) ||
                (buttonRight is ControllerInput.Button && buttonRight.code == buttonCode)
    }

    override fun buttonDown(controller: Controller, buttonCode: Int): Boolean {
        val mapping = getMapping(controller) ?: return false
        val gdxMapping = controller.mapping
        val buttonA = mapping.buttonA
        val buttonB = mapping.buttonB
        val buttonLeft = mapping.buttonLeft
        val buttonRight = mapping.buttonRight
        val buttonUp = mapping.buttonUp
        val buttonDown = mapping.buttonDown
        val buttonStart = mapping.buttonStart
        val buttonSelect = mapping.buttonSelect
        var any = false
        if (buttonA is ControllerInput.Button && buttonA.code == buttonCode) {
            playalong.handleInput(true, EnumSet.of(PlayalongInput.BUTTON_A, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonB is ControllerInput.Button && buttonB.code == buttonCode) {
            playalong.handleInput(true, EnumSet.of(PlayalongInput.BUTTON_B), buttonCode shl 16, false)
            any = true
        }
        if (buttonLeft is ControllerInput.Button && buttonLeft.code == buttonCode) {
            playalong.handleInput(true, EnumSet.of(PlayalongInput.BUTTON_DPAD_LEFT, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonRight is ControllerInput.Button && buttonRight.code == buttonCode) {
            playalong.handleInput(true, EnumSet.of(PlayalongInput.BUTTON_DPAD_RIGHT, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonUp is ControllerInput.Button && buttonUp.code == buttonCode) {
            playalong.handleInput(true, EnumSet.of(PlayalongInput.BUTTON_DPAD_UP, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonDown is ControllerInput.Button && buttonDown.code == buttonCode) {
            playalong.handleInput(true, EnumSet.of(PlayalongInput.BUTTON_DPAD_DOWN, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonStart is ControllerInput.Button && buttonStart.code == buttonCode) {
            // Pause/Play
            playalong.remix.playState = if (playalong.remix.playState != PlayState.PLAYING) PlayState.PLAYING else PlayState.PAUSED
            return true
        }
        if (buttonSelect is ControllerInput.Button && buttonSelect.code == buttonCode) {
            // Stop
            playalong.remix.playState = PlayState.STOPPED
            return true
        }
        return any && playalong.remix.playState == PlayState.PLAYING
    }

    override fun buttonUp(controller: Controller, buttonCode: Int): Boolean {
        val mapping = getMapping(controller) ?: return false
        val buttonA = mapping.buttonA
        val buttonB = mapping.buttonB
        val buttonLeft = mapping.buttonLeft
        val buttonRight = mapping.buttonRight
        val buttonUp = mapping.buttonUp
        val buttonDown = mapping.buttonDown
        var any = false
        if (buttonA is ControllerInput.Button && buttonA.code == buttonCode) {
            playalong.handleInput(false, EnumSet.of(PlayalongInput.BUTTON_A, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonB is ControllerInput.Button && buttonB.code == buttonCode) {
            playalong.handleInput(false, EnumSet.of(PlayalongInput.BUTTON_B), buttonCode shl 16, false)
            any = true
        }
        if (buttonLeft is ControllerInput.Button && buttonLeft.code == buttonCode) {
            playalong.handleInput(false, EnumSet.of(PlayalongInput.BUTTON_DPAD_LEFT, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonRight is ControllerInput.Button && buttonRight.code == buttonCode) {
            playalong.handleInput(false, EnumSet.of(PlayalongInput.BUTTON_DPAD_RIGHT, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonUp is ControllerInput.Button && buttonUp.code == buttonCode) {
            playalong.handleInput(false, EnumSet.of(PlayalongInput.BUTTON_DPAD_UP, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        if (buttonDown is ControllerInput.Button && buttonDown.code == buttonCode) {
            playalong.handleInput(false, EnumSet.of(PlayalongInput.BUTTON_DPAD_DOWN, PlayalongInput.BUTTON_DPAD, PlayalongInput.BUTTON_A_OR_DPAD), buttonCode shl 16, false)
            any = true
        }
        return any && playalong.remix.playState == PlayState.PLAYING
    }

    // Below not implemented
    override fun axisMoved(controller: Controller, axisCode: Int, value: Float): Boolean = false


}