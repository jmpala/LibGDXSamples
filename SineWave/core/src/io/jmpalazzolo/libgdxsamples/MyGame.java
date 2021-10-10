package io.jmpalazzolo.libgdxsamples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer render;

	OrthographicCamera camera;
	Viewport viewport;

	float x = 0;
	Vector2 coordinate;

	@Override
	public void create () {
		batch = new SpriteBatch();
		render = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		viewport = new ScreenViewport(camera);

		coordinate = new Vector2(0, Gdx.graphics.getHeight() / 2);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		viewport.apply();
		camera.update();
		render.setProjectionMatrix(camera.combined);

		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(Color.RED);

		Vector2 coordinate = SineWave(x);
		x += 1f;
		render.circle(coordinate.x, coordinate.y, 5);
		render.end();
	}

	private Vector2 SineWave(float x) {
		float a = 50f;
		float k = 0.05f;
		return new Vector2(x, (float) ((a * (Math.sin(k * x))) + Gdx.graphics.getHeight() / 2) );
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose () {
		render.dispose();
	}
}
