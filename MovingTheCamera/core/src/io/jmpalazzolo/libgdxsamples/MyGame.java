package io.jmpalazzolo.libgdxsamples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	OrthographicCamera camera;
	Viewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		viewport = new ScreenViewport(camera);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		viewport.apply();

		captureInput();
		camera.update();	// The camera modified its position attributes, then it needs to call update

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	private void captureInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			camera.position.y++;
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			camera.position.y--;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			camera.position.x++;
		} else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			camera.position.x--;
		}
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
