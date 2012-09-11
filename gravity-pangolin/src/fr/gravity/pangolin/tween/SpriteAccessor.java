package fr.gravity.pangolin.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteAccessor implements TweenAccessor<Sprite> {
	public static final int SKEW_X2X3 = 1;
	public static final int SKEW_Y2Y3 = 2;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case SKEW_X2X3:
			float[] vs = target.getVertices();
			returnValues[0] = vs[SpriteBatch.X2] - target.getX();
			returnValues[1] = vs[SpriteBatch.X3] - target.getX() - target.getWidth();
			return 2;
		case SKEW_Y2Y3:
			float[] vs2 = target.getVertices();
			returnValues[0] = vs2[SpriteBatch.Y2] - target.getY();
			returnValues[1] = vs2[SpriteBatch.Y3] - target.getY() - target.getHeight();
			return 2;
		}

		assert false;
		return -1;
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case SKEW_X2X3:
			float x2 = target.getX();
			float x3 = x2 + target.getWidth();
			float[] vs = target.getVertices();
			vs[SpriteBatch.X2] = x2 + newValues[0];
			vs[SpriteBatch.X3] = x3 + newValues[1];
			break;
		case SKEW_Y2Y3:
			float y2 = target.getY();
			float y3 = y2 + target.getHeight();
			float[] vs2 = target.getVertices();
			vs2[SpriteBatch.Y2] = y2 + newValues[0];
			vs2[SpriteBatch.Y3] = y3 + newValues[1];
			break;
		}
	}
}