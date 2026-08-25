package io.github.chrislo27.rhrefresh.editor

import com.badlogic.gdx.Gdx
import io.github.chrislo27.rhrefresh.PreferenceKeys
import io.github.chrislo27.rhrefresh.RHREfresh
import io.github.chrislo27.rhrefresh.entity.Entity
import io.github.chrislo27.rhrefresh.entity.model.IRepitchable
import io.github.chrislo27.rhrefresh.entity.model.cue.CueEntity
import io.github.chrislo27.rhrefresh.midi.MidiHandler
import io.github.chrislo27.rhrefresh.sfxdb.SFXDatabase
import io.github.chrislo27.rhrefresh.sfxdb.datamodel.impl.Cue
import io.github.chrislo27.rhrefresh.track.PlayState
import io.github.chrislo27.rhrefresh.track.PlaybackCompletion
import io.github.chrislo27.rhrefresh.track.Remix


data class BuildingNote(val note: MidiHandler.MidiReceiver.Note, val entity: Entity)

class EditorMidiListener(val editor: Editor) : MidiHandler.MidiNoteListener {
    private val remix: Remix get() = editor.remix
    var numberOfNotes = 0

    companion object {

    }

    override fun noteOn(note: MidiHandler.MidiReceiver.Note) {
        Gdx.app.postRunnable {
            val selection = editor.selection
            if (remix.playState == PlayState.STOPPED) {
                if (editor.clickOccupation == ClickOccupation.None && selection.isNotEmpty()) {
                    editor.changePitchOfSelection(note.semitone, false, true, selection)
                }
            } else if (RHREfresh.midiRecording && remix.playState == PlayState.PLAYING) {
                val defaultCue = SFXDatabase.data.objectMap[Remix.DEFAULT_MIDI_NOTE]!! as Cue
                val noteCue = SFXDatabase.data.objectMap[remix.main.preferences.getString(PreferenceKeys.MIDI_NOTE)] ?: defaultCue
                val ent = noteCue.createEntity(remix, null).apply {
                    updateBounds {
                        bounds.set(remix.beat, (numberOfNotes % remix.trackCount).toFloat(), 0f, 1f)
                    }

                    if (this is IRepitchable) {
                        semitone = note.semitone
                        (this as? CueEntity)?.stopAtEnd = true
                    }
                }
                ent.updateInterpolation(true)

                ent.playbackCompletion = PlaybackCompletion.FINISHED
                remix.addEntity(ent)

                val bn = BuildingNote(note, ent)

                editor.buildingNotes[bn.note] = bn
                numberOfNotes++
            }
        }
    }

    override fun noteOff(note: MidiHandler.MidiReceiver.Note) {
        if (RHREfresh.midiRecording && editor.buildingNotes.containsKey(note)) editor.buildingNotes.remove(note)
    }

    var pedalDown = false

    override fun controlChange(ccNumber: Int, data: Int) {
        if (ccNumber == 64) {
            if (RHREfresh.midiRecording && data < 64 && pedalDown) {
                val state = remix.playState
                if (state == PlayState.STOPPED || state == PlayState.PAUSED) {
                    remix.playState = PlayState.PLAYING
                } else if (state == PlayState.PLAYING) {
                    remix.playState = PlayState.PAUSED
                }
            }
            pedalDown = data >= 64
        }
    }
}
