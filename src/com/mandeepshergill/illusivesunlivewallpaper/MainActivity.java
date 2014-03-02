package com.mandeepshergill.illusivesunlivewallpaper;

import android.os.Bundle;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void onClick(View view) {
		Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
		intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, IllusiveWallpaperService.class));
		startActivity(intent);
	}

}
