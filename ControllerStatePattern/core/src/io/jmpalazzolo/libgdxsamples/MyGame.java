package io.jmpalazzolo.libgdxsamples;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.jmpalazzolo.libgdxsamples.systems.EntityFactorySystem;
import io.jmpalazzolo.libgdxsamples.systems.MovementSystem;
import io.jmpalazzolo.libgdxsamples.systems.input.InputSystem;
import io.jmpalazzolo.libgdxsamples.systems.render.DebugRenderSystem;
import io.jmpalazzolo.libgdxsamples.systems.render.PlayerRenderSystem;
import io.jmpalazzolo.libgdxsamples.utils.GdxUtils;
import org.graalvm.compiler.lir.aarch64.AArch64Move;

public class MyGame extends ApplicationAdapter {



	private ShapeRenderer renderer;

	private OrthographicCamera camera;
	private Viewport viewport;

	private World world;

	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		viewport = new ExtendViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);
		renderer = new ShapeRenderer();

		WorldConfiguration config = new WorldConfigurationBuilder()
				.dependsOn()
				.with(
						new EntityFactorySystem(),
						new InputSystem(),
						new MovementSystem(),
						new DebugRenderSystem(this),
						new PlayerRenderSystem(this)
				)
				.build();

		world = new World(config);

	}

	@Override
	public void render () {
		GdxUtils.clearScreen();
		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void dispose () {
		world.dispose();
		renderer.dispose();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public ShapeRenderer getRenderer() {
		return renderer;
	}
}
