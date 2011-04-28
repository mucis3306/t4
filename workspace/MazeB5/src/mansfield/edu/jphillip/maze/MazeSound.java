package mansfield.edu.jphillip.maze;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * MVC Model: sets up and plays short sound clips.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips Edited by team 4
 */
public class MazeSound {
	private AudioInputStream popSound, blipSound,coinSound;
	private AudioInputStream poorFinishSound, goodFinishSound;
	private Clip popSoundClip, blipSoundClip, coinSoundClip;
	private Clip poorFinishSoundClip, goodFinishSoundClip;

	public MazeSound() {
		try {
			coinSound = AudioSystem.getAudioInputStream(MazeSound.class
					.getResourceAsStream("/sound/coin-drop-4.wav"));
			coinSoundClip = AudioSystem.getClip();
			coinSoundClip.open(coinSound);
			popSound = AudioSystem.getAudioInputStream(MazeSound.class
					.getResourceAsStream("/sound/pop.wav"));
			popSoundClip = AudioSystem.getClip();
			popSoundClip.open(popSound);

			blipSound = AudioSystem.getAudioInputStream(MazeSound.class
					.getResourceAsStream("/sound/blip.wav"));
			blipSoundClip = AudioSystem.getClip();
			blipSoundClip.open(blipSound);

			poorFinishSound = AudioSystem
					.getAudioInputStream(MazeSound.class
							.getResourceAsStream("/sound/crowdohh.wav"));
			poorFinishSoundClip = AudioSystem.getClip();
			poorFinishSoundClip.open(poorFinishSound);

			goodFinishSound = AudioSystem
					.getAudioInputStream(MazeSound.class
						.getResourceAsStream("/sound/hitcrowdcheer.wav"));
			goodFinishSoundClip = AudioSystem.getClip();
			goodFinishSoundClip.open(goodFinishSound);
		} catch (Exception e) {
			System.out.println("Error with sound file");
		}
	}

	public void pop() {
		if ( popSoundClip != null ) {
			popSoundClip.setFramePosition(0);
			popSoundClip.start();
		}
	}

	public void blip() {
		if ( blipSoundClip != null ) {
			blipSoundClip.setFramePosition(0);
			blipSoundClip.start();
		}
	}

	public void boo() {
		if ( poorFinishSoundClip != null ) {
			poorFinishSoundClip.setFramePosition(0);
			poorFinishSoundClip.start();
		}
	}

	public void cheer() {
		if ( goodFinishSoundClip != null ) {
			// goodFinishSoundClip.setFramePosition(0);
			goodFinishSoundClip.start();
		}
	}
	public void coin(){
		if(coinSoundClip != null){
			coinSoundClip.start();
		}
	}
}
