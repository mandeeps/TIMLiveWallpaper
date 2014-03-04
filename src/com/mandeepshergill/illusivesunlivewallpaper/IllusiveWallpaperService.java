package com.mandeepshergill.illusivesunlivewallpaper;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

// http://www.vogella.com/tutorials/AndroidLiveWallpaper/article.html
public class IllusiveWallpaperService extends WallpaperService {

	@Override
	public Engine onCreateEngine() {
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
		private Bitmap backdrop;
		
		public wallEngine() {
			backdrop = BitmapFactory.decodeResource(getResources(), R.drawable.illusive);
			
			maxNum = 20;
			touchOn = true;
			circles = new ArrayList<MyPoint>();
			paint.setAntiAlias(true);
			paint.setColor(Color.RED);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(10f);
			handler.post(drawRun);
		}
		
		@Override
		public void onVisibilityChanged(boolean visibile) {
			this.visible = visibile;
			if (visibile) {
				handler.post(drawRun);
			} else {
				handler.removeCallbacks(drawRun);
			}
		}
	
		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			this.visible = false;
			handler.removeCallbacks(drawRun);
		}
		
		@Override 
		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			this.width = width;
			this.height = height;
			super.onSurfaceChanged(holder, format, width, height);
		}
		
		@Override
		public void onTouchEvent(MotionEvent event) {
			if (touchOn) {
				float x = event.getX();
				float y = event.getY();
				SurfaceHolder holder = getSurfaceHolder();
				Canvas canvas = null;
				try {
					canvas = holder.lockCanvas();
					if (canvas != null) {
						canvas.drawColor(Color.GREEN);
						circles.clear();
						circles.add(new MyPoint(String.valueOf(circles.size() + 1), x, y));
						drawCircles(canvas, circles);
					}
				} finally {
					if (canvas != null)
						holder.unlockCanvasAndPost(canvas);
					}
					super.onTouchEvent(event);
				}
			}
		
		private void draw() {
			SurfaceHolder holder = getSurfaceHolder();
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					canvas.drawBitmap(backdrop, 0, 0, null);
					if (circles.size() >= maxNum) {
						circles.clear();
					}
					int x = (int) (width * Math.random());
					int y = (int) (height * Math.random());
					//circles.add(new MyPoint(String.valueOf(circles.size()+1), x, y));
					//drawCircles(canvas, circles);
				}
			} finally {
				if (canvas != null) 
					holder.unlockCanvasAndPost(canvas);
			}
			handler.removeCallbacks(drawRun);
			if (visible) {
				handler.postDelayed(drawRun, 5000);
			}
		}
		
		private void drawCircles(Canvas canvas, List<MyPoint> circles) {
			canvas.drawBitmap(backdrop, 0, 0, null);
			//for (MyPoint point:circles) {
			//	canvas.drawCircle(point.x, point.y, 20.0f, paint);
			//}
		}
	
	}

}
