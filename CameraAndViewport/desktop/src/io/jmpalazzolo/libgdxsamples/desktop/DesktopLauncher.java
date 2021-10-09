package io.jmpalazzolo.libgdxsamples.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.jmpalazzolo.libgdxsamples.Constants;
import io.jmpalazzolo.libgdxsamples.MaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Constants.HEIGHT;
		config.width = Constants.WIDTH;
		new LwjglApplication(new MaGame(), config);
	}
}
