package fr.gravity.pangolin.entity.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.gravity.pangolin.util.GameUtil;

public class ButtonsSlider extends Group {
	
	private static final int FOCUSED_BUTTON_WIDTH = 100;
	private static final int FOCUSED_BUTTON_HEIGHT = 100;
	private static final int BUTTON_WIDTH = FOCUSED_BUTTON_WIDTH * 80 / 100;
	private static final int BUTTON_HEIGHT = FOCUSED_BUTTON_HEIGHT * 80 / 100;
	
	
	private static final int SELECTED_X = (GameUtil.getScreen().getWidth() / 2) - (FOCUSED_BUTTON_WIDTH / 2);
	private static final int SELECTED_Y = (GameUtil.getScreen().getHeight() / 2) - (FOCUSED_BUTTON_HEIGHT / 2);
	private static final int PADDING = 10;
	
	private List<TextButton> buttons = new ArrayList<TextButton>();
	private int focusedButtonIndex = 0;
	
	public ButtonsSlider(List<TextButton> buttons, Stage stage) {
		this.buttons = buttons;
		this.stage = stage;
		
		this.width = GameUtil.getScreen().getWidth();
		this.height = GameUtil.getScreen().getHeight();
		
		placeButtons();
	}

	private void placeButtons() {
		for (int i = 0; i < buttons.size(); i++) {
			TextButton textButton = buttons.get(i);
			textButton.x = SELECTED_X + (i * (PADDING + FOCUSED_BUTTON_WIDTH));
			textButton.y = SELECTED_Y;
			textButton.width = (i == focusedButtonIndex || i == buttons.size() + 1 ? FOCUSED_BUTTON_WIDTH : BUTTON_WIDTH);
			textButton.height = (i == focusedButtonIndex || i == buttons.size() + 1 ? FOCUSED_BUTTON_HEIGHT : BUTTON_HEIGHT);
			
			addActor(textButton);
		}
	}
	
	private float touchDownX;
	private float touchDownY;
	
//	@Override
//	public boolean touchDown (float x, float y, int pointer) {
//		touchDownX = x;
//		touchDownY = y;
//		System.out.println("TOUCHED DOWN (pointer=" + pointer + ")");
//		return false;
//	}
//
//	@Override
//	public void touchUp (float x, float y, int pointer) {
//		System.out.println("TOUCHED UP");
//	}
//
//	@Override
//	public void touchDragged (float x, float y, int pointer) {
//		if ((touchDownX - x) < FOCUSED_BUTTON_WIDTH)
//			focusedButtonIndex++;
//		System.out.println("TOUCHED DRAGGED");
//	}

}
