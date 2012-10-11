package fr.gravity.pangolin.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import fr.gravity.pangolin.transition.Transition;

public class TransitionScreen implements Screen {
	Game game;

	IScreen current;
	IScreen next;

	int currentTransitionEffect;
	ArrayList<Transition> transitionEffects;

	public TransitionScreen(Game game, IScreen current, IScreen next, ArrayList<Transition> transitionEffects) {
		this.current = current;
		this.next = next;
		this.transitionEffects = transitionEffects;
		this.currentTransitionEffect = 0;
		this.game = game;

//		if (current != null)
//			current.show();
//		if (next != null)
//			next.show();

		transitionEffects.get(currentTransitionEffect).start();
	}

	@Override
	public void render(float delta) {
		transitionEffects.get(currentTransitionEffect).render(current, next);

		if (transitionEffects.get(currentTransitionEffect).isFinished()) {
			currentTransitionEffect++;
			if (currentTransitionEffect >= transitionEffects.size()) {
				game.setScreen(next);
				return;
			} else
				transitionEffects.get(currentTransitionEffect).start();

		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}