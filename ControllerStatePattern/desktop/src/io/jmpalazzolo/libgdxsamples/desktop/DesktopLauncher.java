package io.jmpalazzolo.libgdxsamples.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.jmpalazzolo.libgdxsamples.Constants;
import io.jmpalazzolo.libgdxsamples.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Constants.SCREEN_HEIGHT;
		config.width = Constants.SCREEN_WIDTH;
		config.title = "Controller State Pattern";
		new LwjglApplication(new MyGame(), config);
	}
}
