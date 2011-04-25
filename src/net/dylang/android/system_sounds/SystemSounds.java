package net.dylang.android.system_sounds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SystemSounds extends Activity {
	/** Called when the activity is first created. */
	static int systemVolume = 0;
	static int alarmVolume = 0;
	static int voiceVolume = 0;
	static int notificationsVolume = 0;
	static int ringerVolume = 0;
	static int mediaVolume = 0;
	static AudioManager audio;
	static SeekBar systemBar;
	static SeekBar alarmBar;
	static SeekBar voiceBar;
	static SeekBar notificationsBar;
	static SeekBar ringerBar;
	static SeekBar mediaBar;

	public static void setAllStreamVolume(int volume) {
		audio.setStreamVolume(AudioManager.STREAM_SYSTEM, volume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_ALARM, volume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_VOICE_CALL, volume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_RING, volume, AudioManager.FLAG_PLAY_SOUND);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_PLAY_SOUND);
	}

	public static void setIndividualStreamVolume(int stream, int volume) {
		audio.setStreamVolume(stream, volume, AudioManager.FLAG_PLAY_SOUND);
	}

	public static void updateStatusBars() {
		systemBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
		systemBar.setProgress(audio.getStreamVolume(AudioManager.STREAM_SYSTEM));
		alarmBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_ALARM));
		alarmBar.setProgress(audio.getStreamVolume(AudioManager.STREAM_ALARM));
		voiceBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
		voiceBar.setProgress(audio.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
		notificationsBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
		notificationsBar.setProgress(audio.getStreamVolume(AudioManager.STREAM_NOTIFICATION));
		ringerBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_RING));
		ringerBar.setProgress(audio.getStreamVolume(AudioManager.STREAM_RING));
		mediaBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		mediaBar.setProgress(audio.getStreamVolume(AudioManager.STREAM_MUSIC));
	}

	public static void saveVolumes() {
		audio.setStreamVolume(AudioManager.STREAM_SYSTEM, systemVolume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_ALARM, alarmVolume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_VOICE_CALL, voiceVolume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_NOTIFICATION, notificationsVolume, AudioManager.FLAG_PLAY_SOUND);	
		audio.setStreamVolume(AudioManager.STREAM_RING, ringerVolume, AudioManager.FLAG_PLAY_SOUND);
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, mediaVolume, AudioManager.FLAG_PLAY_SOUND);	
	}

	public void init(final SeekBar myBar) {
		myBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar bar, int level, boolean bool) {
				if (myBar.equals(systemBar)) {
					systemVolume = level;
				} else if (myBar.equals(alarmBar)) {
					alarmVolume = level;
				} else if (myBar.equals(voiceBar)) {
					voiceVolume = level;
				} else if (myBar.equals(notificationsBar)) {
					notificationsVolume = level;
				} else if (myBar.equals(ringerBar)) {
					ringerVolume = level;
				} else if (myBar.equals(mediaBar)) {
					mediaVolume = level;
				}
			}
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {}
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {}
		});
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		systemBar = (SeekBar) findViewById(R.id.systemVolume);
		alarmBar = (SeekBar) findViewById(R.id.alarmVolume);
		voiceBar = (SeekBar) findViewById(R.id.voiceVolume);
		notificationsBar = (SeekBar) findViewById(R.id.notificationsVolume);
		ringerBar = (SeekBar) findViewById(R.id.ringerVolume);
		mediaBar = (SeekBar) findViewById(R.id.mediaVolume);
		final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton1);
		final Button button = (Button) findViewById(R.id.profileButton);

		updateStatusBars();

		toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean on) {
				if (on) {
					saveVolumes();
					Toast.makeText(SystemSounds.this, "Your settings have been saved.", Toast.LENGTH_SHORT).show();
					new Handler().postDelayed(new Runnable() { public void run() { toggle.setChecked(false);}}, 3000);
				}
			}
		});

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Intent startResultsActivity = new Intent(SystemSounds.this, ProfilesPage.class);
				SystemSounds.this.startActivity(startResultsActivity);
			}
		});

		init(systemBar);
		init(alarmBar);
		init(voiceBar);
		init(notificationsBar);
		init(ringerBar);
		init(mediaBar);
	}
}


