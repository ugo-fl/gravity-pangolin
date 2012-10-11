package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Screen;

public interface IScreen extends Screen {
	public float getPpuX();
	public float getPpuY();
	
	public float getWidth();
	public float getHeight();
}
