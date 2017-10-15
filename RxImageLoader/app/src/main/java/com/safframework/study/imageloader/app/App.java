/**
 * 
 */
package com.safframework.study.imageloader.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.ComponentCallbacks2;

import com.safframework.study.imageloader.imageloader.RxImageLoader;
import com.safframework.study.imageloader.imageloader.Utils;

/**
 *
 * 
 * @author Tony Shen
 * 
 */
public class App extends Application {

	public RxImageLoader imageLoader;
	private static App instance;

	/**
	 * @see Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	public void init() {
		instance = this;

		imageLoader = new RxImageLoader();
		imageLoader.init(instance);
	}

	public static App getInstance() {
		return instance;
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		imageLoader.clearMemCache();
	}
	
	@Override
	@TargetApi(14)
	public void onTrimMemory(int level) {
		if (Utils.isICSOrHigher()) {
			super.onTrimMemory(level);
			
			if (level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {
				imageLoader.clearMemCache();
			}
		}
	}
}