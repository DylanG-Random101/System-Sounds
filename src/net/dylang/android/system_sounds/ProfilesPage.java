package net.dylang.android.system_sounds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ProfilesPage extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profiles);
		final Button silent = (Button) findViewById(R.id.silentButton);
		silent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent aa = new Intent(ProfilesPage.this, SystemSounds.class);
				SystemSounds.setAllStreamVolume(0);
				SystemSounds.updateStatusBars();
				Toast.makeText(ProfilesPage.this, "Your volumes have been silented.", Toast.LENGTH_SHORT).show();
				ProfilesPage.this.startActivity(aa);
			}
		});
	}
}