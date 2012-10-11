package fr.gravity.pangolin.entity.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.gravity.pangolin.screen.IScreen;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.util.NumbersUtil;

public class ButtonsSlider extends Group implements InputProcessor {

	private static final float FOCUS_RATE = 0.3F;

	private static final int FOCUSED_BUTTON_WIDTH = 100;
	private static final int FOCUSED_BUTTON_HEIGHT = 100;
	private static final int BUTTON_WIDTH = FOCUSED_BUTTON_WIDTH * 70 / 100;
	private static final int BUTTON_HEIGHT = FOCUSED_BUTTON_HEIGHT * 70 / 100;

	private static final int PADDING = 5;

	private final float middleScreen;
	private final float selectedX;
	private final float selectedY;
	
	private float startX;

	private List<TextButton> buttons = new ArrayList<TextButton>();

	public ButtonsSlider(List<TextButton> buttons, Stage stage, IScreen screen) {
		this.buttons = buttons;
		this.stage = stage;

		this.width = screen.getWidth();
		this.height = screen.getHeight();
		 
		this.middleScreen = screen.getWidth() / 2;
		this.selectedX = middleScreen - (FOCUSED_BUTTON_WIDTH / 2);
		this.selectedY = (screen.getHeight() / 2) - (FOCUSED_BUTTON_HEIGHT / 2);
		this.startX = selectedX;
		
		placeButtons(startX);
		addButtons();

		Gdx.input.setInputProcessor(this);
	}

	private void addButtons() {
		for (TextButton textButton : buttons) {
			addActor(textButton);
		}
	}

	private void placeButtons(float startX) {
		for (int i = 0; i < buttons.size(); i++) {
			TextButton textButton = buttons.get(i);

			textButton.x = startX + (i * (PADDING + FOCUSED_BUTTON_WIDTH));
			textButton.y = selectedY;

			textButton.width = NumbersUtil.limit(FOCUSED_BUTTON_WIDTH - Math.abs(middleScreen - (textButton.x + textButton.width / 2)) * FOCUS_RATE,
					FOCUSED_BUTTON_WIDTH, BUTTON_WIDTH);
			textButton.height = NumbersUtil.limit(FOCUSED_BUTTON_HEIGHT - Math.abs(middleScreen - (textButton.x + textButton.width / 2)) * FOCUS_RATE,
					FOCUSED_BUTTON_HEIGHT, BUTTON_HEIGHT);

			textButton.x += (FOCUSED_BUTTON_WIDTH - textButton.width) / 2;
		}
	}

	private boolean magnetized = true;
	private float initialStartX;
	private float shift;

	private static final float DEFAULT_MAGNETIZE_RATIO = 0.08F;
	private float magnetizeRatio;

	/**
	 * Initiate the shift to be performed for magnetizing
	 */
	private void initMagnetizing() {
		shift = Float.MAX_VALUE;
		for (int i = 0; i < buttons.size(); i++) {
			TextButton textButton = buttons.get(i);
			float tmpShift = middleScreen - (textButton.x + textButton.width / 2);
			if (tmpShift == 0)
				return;
			if (Math.abs(tmpShift) < Math.abs(shift)) {
				shift = tmpShift;
			}
		}
		magnetized = false;
		initialStartX = startX;
		magnetizeRatio = DEFAULT_MAGNETIZE_RATIO;
	}

	/**
	 * Magnetizes the focused button on the middle of the screen
	 * 
	 * @return true when the magnetizing movement is done
	 */
	private boolean magnetize() {
		startX += shift * magnetizeRatio;
		magnetizeRatio -= Math.abs(startX - initialStartX) * 0.0001F;
		placeButtons(startX);
		if (magnetizeRatio < 0 || (initialStartX <= (initialStartX + shift) && startX >= (initialStartX + shift)))
			return doneMagnetize();
		else if (initialStartX >= (initialStartX + shift) && startX <= (initialStartX + shift))
			return doneMagnetize();
		return false;
	}

	private boolean doneMagnetize() {
		startX = (initialStartX + shift);
		placeButtons(startX);
		return true;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (!magnetized)
			magnetized = magnetize();

		// DEBUG
//		 ShapeRenderer debugRenderer = new ShapeRenderer();
//		 debugRenderer.begin(ShapeType.Line);
//		 debugRenderer.setColor(new Color(0, 1, 0, 1));
//		 AbstractScreen screen = GameUtil.getScreen();
//		 for (TextButton button : buttons) {
//		 debugRenderer.setProjectionMatrix(stage.getCamera().combined);
//		 debugRenderer.line(button.x + (button.width / 2), 0, button.x +
//		 (button.width / 2), screen.getHeight());
//		 }
//		
//		 debugRenderer.setColor(new Color(1, 0.5F, 0.3F, 0.01F));
//		 debugRenderer.line(MIDDLE_SCR, 0, MIDDLE_SCR, screen.getHeight());
//		 debugRenderer.end();
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
			child.touchUp(x - child.x, y - child.y, pointer);
		System.out.println("UP " + x);
		startX -= (touchDownX - x);
		// Trigger magnetizing
		initMagnetizing();
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
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