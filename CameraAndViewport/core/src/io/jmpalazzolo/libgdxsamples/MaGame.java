package io.jmpalazzolo.libgdxsamples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MaGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;

	OrthographicCamera camera;
	Viewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		camera = new OrthographicCamera();				// The camera is from where we see the game
		camera.setToOrtho(false);						// We align the (0,0) to be the bottom left corner
		viewport = new ScreenViewport(camera);			// We inform the viewport on the constructor of the camera that we created
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		viewport.apply();

		batch.setProjectionMatrix(camera.combined);		// We inform the SpriteBatch where is the camera
		
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, false);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
