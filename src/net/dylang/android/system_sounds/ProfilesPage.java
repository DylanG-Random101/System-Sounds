package net.dylang.android.system_sounds;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ProfilesPage extends Activity { 
	static int i = 0;
	static String[] profileNames;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profiles);
		final Button silent = (Button) findViewById(R.id.silentButton);
		final Button loud 	= (Button) findViewById(R.id.loudButton);
		final Button create	= (Button) findViewById(R.id.createButton);
		final Intent systemSounds = new Intent(ProfilesPage.this, SystemSounds.class);

		if (SystemSounds.madeProperties) {
			profileNames = SystemSounds.prop.getProperty("profile-names").split(",");
			for (ProfilesPage.i = 0; ProfilesPage.i <= (profileNames.length - 1); ProfilesPage.i++) {
				LinearLayout layout = (LinearLayout) findViewById(R.id.layout2);	
				if (!profileNames[i].equals("") && !profileNames[i].equals(" ")) {
					Button button = new Button(this);
					button.setText(profileNames[i].replaceAll("-", " "));
					button.setLayoutParams(new LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,
							ViewGroup.LayoutParams.WRAP_CONTENT)); 
					button.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							String[] volumes = SystemSounds.prop.getProperty(ProfilesPage.profileNames[ProfilesPage.i-1]).split(",");
							SystemSounds.setIndividualStreamVolume(AudioManager.STREAM_SYSTEM, Integer.parseInt(volumes[0]));
							SystemSounds.setIndividualStreamVolume(AudioManager.STREAM_ALARM, Integer.parseInt(volumes[1]));
							SystemSounds.setIndividualStreamVolume(AudioManager.STREAM_VOICE_CALL, Integer.parseInt(volumes[2]));
							SystemSounds.setIndividualStreamVolume(AudioManager.STREAM_NOTIFICATION, Integer.parseInt(volumes[3]));
							SystemSounds.setIndividualStreamVolume(AudioManager.STREAM_RING, Integer.parseInt(volumes[4]));
							SystemSounds.setIndividualStreamVolume(AudioManager.STREAM_MUSIC, Integer.parseInt(volumes[5]));
							SystemSounds.updateStatusBars();
							Toast.makeText(ProfilesPage.this, "Your volumes have been set to '" + ProfilesPage.profileNames[ProfilesPage.i-1].replaceAll("-", " ") + "'.", Toast.LENGTH_SHORT).show();
							ProfilesPage.this.startActivity(systemSounds);
						}
					});
					button.setGravity(Gravity.CENTER_HORIZONTAL);
					layout.addView(button);
				}
			}
		}

		loud.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SystemSounds.setAllStreamVolume(15);
				SystemSounds.updateStatusBars();
				Toast.makeText(ProfilesPage.this, "Your volumes have been maximized.", Toast.LENGTH_SHORT).show();
				ProfilesPage.this.startActivity(systemSounds);
			}
		});

		silent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SystemSounds.setAllStreamVolume(0);
				SystemSounds.updateStatusBars();
				Toast.makeText(ProfilesPage.this, "Your volumes have been silented.", Toast.LENGTH_SHORT).show();
				ProfilesPage.this.startActivity(systemSounds);
			}
		});
		create.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent createaprofile = new Intent(ProfilesPage.this, CreateAProfile.class);
				ProfilesPage.this.startActivity(createaprofile);
			}
		});

	}
}