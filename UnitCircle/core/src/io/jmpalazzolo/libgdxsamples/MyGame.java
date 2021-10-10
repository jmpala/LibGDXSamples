package io.jmpalazzolo.libgdxsamples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGame extends ApplicationAdapter {

	ShapeRenderer renderer;

	OrthographicCamera camera;
	Viewport viewport;

	Vector2 coordinate;
	float angle;

	@Override
	public void create () {
		renderer = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		viewport = new ScreenViewport(camera);

		coordinate = new Vector2(0, 0);
		angle = 0f;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		camera.update();
		viewport.apply();

		coordinate = unitCircle(angle, 200);
		angle += 0.1f;

		renderer.setProjectionMatrix(camera.combined);
		renderer.setColor(Color.WHITE);
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.circle(coordinate.x, coordinate.y, 5);
		renderer.end();
	}

	public Vector2 unitCircle(float theta, float rad) {
		float x = (float) ((rad * Math.cos(theta)) + Gdx.graphics.getWidth() / 2);
		float y = (float) ((rad * Math.sin(theta)) + Gdx.graphics.getHeight() / 2);
		return new Vector2(x, y);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose () {
		renderer.dispose();
	}
}
