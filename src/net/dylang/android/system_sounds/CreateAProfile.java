package net.dylang.android.system_sounds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class CreateAProfile extends Activity {

	static SeekBar systemBar;
	static SeekBar alarmBar;
	static SeekBar voiceBar;
	static SeekBar notificationsBar;
	static SeekBar ringerBar;
	static SeekBar mediaBar;
	static Button create;
	static AudioManager audio;
	static EditText profileName;
	static int systemVolume;
	static int alarmVolume;
	static int voiceVolume;
	static int notificationsVolume;
	static int ringerVolume;
	static int mediaVolume;

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
		setContentView(R.layout.createaprofile);

		systemBar = (SeekBar) findViewById(R.id.systemVolume1);
		alarmBar = (SeekBar) findViewById(R.id.alarmVolume1);
		voiceBar = (SeekBar) findViewById(R.id.voiceVolume1);
		notificationsBar = (SeekBar) findViewById(R.id.notificationsVolume1);
		ringerBar = (SeekBar) findViewById(R.id.ringerVolume1);
		mediaBar = (SeekBar) findViewById(R.id.mediaVolume1);
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		profileName = (EditText) findViewById(R.id.profileName);
		create = (Button) findViewById(R.id.submitButton);

		systemBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
		alarmBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_ALARM));
		voiceBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
		notificationsBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
		ringerBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_RING));
		mediaBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

		init(systemBar);
		init(alarmBar);
		init(voiceBar);
		init(notificationsBar);
		init(ringerBar);
		init(mediaBar);

		create.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Boolean good = true;
				final String newProfileName = (!profileName.getText().toString().equals("") && !profileName.getText().toString().equals(" ")) ? profileName.getText().toString() : "error";
	
				if (newProfileName.equalsIgnoreCase("error")) {
					Toast.makeText(CreateAProfile.this, "Please set a profile name!", Toast.LENGTH_LONG).show();
					good = false;
				}
				if (SystemSounds.madeProperties) {
					if (SystemSounds.prop.getProperty("profile-names").toLowerCase().contains(newProfileName.toLowerCase())) {
						Toast.makeText(CreateAProfile.this, "Please set a different profile name!", Toast.LENGTH_LONG).show();
						good = false;
					}
					if (good) {
						SystemSounds.prop.setProperty("profile-names", (SystemSounds.prop.getProperty("profile-names").equals("")) ? SystemSounds.prop.getProperty("profile-names") + "," + newProfileName.replaceAll(" ", "-") : SystemSounds.prop.getProperty("profile-names") + "," + newProfileName.replaceAll(" ", "-"));
						SystemSounds.prop.setProperty(newProfileName.replaceAll(" ", "-"), systemVolume + "," + alarmVolume + "," + voiceVolume + "," + notificationsVolume + "," + ringerVolume + "," + mediaVolume);
						final Intent profilePage = new Intent(CreateAProfile.this, ProfilesPage.class);
						CreateAProfile.this.startActivity(profilePage);
					}
				}
			}
		});
	}
}
