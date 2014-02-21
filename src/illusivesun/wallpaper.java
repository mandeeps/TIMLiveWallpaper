package illusivesun;

import android.service.wallpaper.WallpaperService;

public class wallpaper extends WallpaperService {

	@Override
	public Engine onCreateEngine() {
		// TODO Auto-generated method stub
		return new wallEngine();
	}
	
	private class wallEngine extends Engine {
		
	}

}
