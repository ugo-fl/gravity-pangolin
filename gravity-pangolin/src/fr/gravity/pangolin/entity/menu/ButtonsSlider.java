package fr.gravity.pangolin.entity.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.gravity.pangolin.screen.AbstractScreen;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.util.Numbers;

public class ButtonsSlider extends Group implements InputProcessor {

	private static final float SLIDING_RATE = 0.02F;

	private static final int FOCUSED_BUTTON_WIDTH = 100;
	private static final int FOCUSED_BUTTON_HEIGHT = 100;
	private static final int BUTTON_WIDTH = FOCUSED_BUTTON_WIDTH * 80 / 100;
	private static final int BUTTON_HEIGHT = FOCUSED_BUTTON_HEIGHT * 80 / 100;

	private static final int SELECTED_X = (GameUtil.getScreen().getWidth() / 2) - (FOCUSED_BUTTON_WIDTH / 2);
	private static final int SELECTED_Y = (GameUtil.getScreen().getHeight() / 2) - (FOCUSED_BUTTON_HEIGHT / 2);
	private static final int PADDING = 5;

	private float startX = SELECTED_X;

	private List<TextButton> buttons = new ArrayList<TextButton>();
	private int focusedButtonIndex = 0;

	// FOR DEBUG
	Label startXLabel = new Label("startX=" + startX, GameUtil.getScreen().getSkin());
	Label placeButtonsXLabel = new Label("placeButtonsX=", GameUtil.getScreen().getSkin());

	public ButtonsSlider(List<TextButton> buttons, Stage stage) {
		this.buttons = buttons;
		this.stage = stage;

		this.width = GameUtil.getScreen().getWidth();
		this.height = GameUtil.getScreen().getHeight();

		placeButtons(startX);
		addButtons();

		// FOR DEBUG
		startXLabel.x = 10;
		startXLabel.y = 300;
		placeButtonsXLabel.x = 120;
		placeButtonsXLabel.y = 300;
		stage.addActor(startXLabel);
		stage.addActor(placeButtonsXLabel);

		Gdx.input.setInputProcessor(this);
	}

	private void updateDebug() {
		startXLabel.setText("startX= " + startX);
	}

	private void placeButtons(float startX) {
		// FOR DEBUG
		placeButtonsXLabel.setText("placeButtonsX=" + startX);
		
		float middleScreen = GameUtil.getScreen().getWidth() / 2;
		
		for (int i = 0; i < buttons.size(); i++) {
			TextButton textButton = buttons.get(i);
			textButton.x = startX + (i * (PADDING + FOCUSED_BUTTON_WIDTH));
			textButton.y = SELECTED_Y;
			textButton.width = Numbers.limit(FOCUSED_BUTTON_WIDTH - Math.abs(middleScreen - (textButton.x + textButton.width / 2)) * 0.2F, FOCUSED_BUTTON_WIDTH, BUTTON_WIDTH);
			textButton.height = Numbers.limit(FOCUSED_BUTTON_HEIGHT - Math.abs(middleScreen - (textButton.x + textButton.width / 2)) * 0.2F, FOCUSED_BUTTON_HEIGHT, BUTTON_HEIGHT);
		}
	}

	private void addButtons() {
		for (TextButton textButton : buttons) {
			addActor(textButton);
		}
	}

	private float touchDownX;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		super.touchDown(x, y, pointer);
		System.out.println("DOWN " + x);
		touchDownX = x;
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		super.touchUp(x, y, pointer);
		for (Actor child : children)
			child.touchUp(x, y, pointer);
		System.out.println("UP " + x);
		startX -= (touchDownX - x);
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		updateDebug();
		super.touchDragged(x, y, pointer);
		System.out.println("DRAGGED " + x);
		placeButtons(startX - (touchDownX - x));
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

}
