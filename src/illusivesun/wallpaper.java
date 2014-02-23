package illusivesun;

import java.util.ArrayList;

import android.os.Handler;
import android.service.wallpaper.WallpaperService;

public class wallpaper extends WallpaperService {

	@Override
	public Engine onCreateEngine() {
		// TODO Auto-generated method stub
		return new wallEngine();
	}
	
	private class wallEngine extends Engine {
		private final Handler handler = new Handler();
		private final Runnable drawRun = new Runnable() {
			@Override
			public void run() {
				draw();
			}
		};
		
		private List<MyPoint> circles;
		private Paint paint = new Paint();
		private int width;
		int height;
		private boolean visible = true;
		private int maxNum;
		private boolean touchOn;
		
		public wallEngine() {
			circles = new ArrayList<MyPoint>();
		}
		
	}

}
