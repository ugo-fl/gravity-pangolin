package fr.gravity.pangolin.entity.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CustomButton extends TextButton {

	public CustomButton(Skin skin) {
		super(skin);
	}
	
	@Override
	public boolean touchDown (float x, float y, int pointer) {
		parent.touchDown(x, y, pointer);
		System.out.println("TOUCHED DOWN CHILD");
		return false;
	}

	@Override
	public void touchUp (float x, float y, int pointer) {
		parent.touchUp(x, y, pointer);
		System.out.println("TOUCHED UP CHILD");
	}

	@Override
	public void touchDragged (float x, float y, int pointer) {
		parent.touchDragged(x, y, pointer);
		System.out.println("TOUCHED DRAGGED CHILD");
	}

}
