package fr.gravity.pangolin.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import fr.gravity.pangolin.screen.IScreen;

public class FadeOutTransition extends Transition {

	Color color = new Color();
	
	public FadeOutTransition(int duration) {
		super(duration);
	}

	@Override
	public void render(IScreen current, IScreen next) {
		current.render(Gdx.graphics.getDeltaTime());
		color.set(0f, 0f, 0f, 1F - ((1F / countDownHelper.getDuration()) * countDownHelper.getTimeRemaining()));
		System.out.println("FADE OUT ALPHA = " + color.a);
		
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		
		shapeRenderer.begin(ShapeType.FilledRectangle);
		shapeRenderer.setColor(color);
		shapeRenderer.filledRect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shapeRenderer.end();
		Gdx.gl20.glDisable(GL20.GL_BLEND);
	}

}
