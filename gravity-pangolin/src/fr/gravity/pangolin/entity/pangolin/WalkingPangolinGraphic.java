package fr.gravity.pangolin.entity.pangolin;

public class WalkingPangolinGraphic extends PangolinGraphic {

	public WalkingPangolinGraphic(Pangolin pangolin, float x, float y) {
		super(pangolin, x, y);
		set(getFrame(stateTime));
	}

	@Override
	public void process() {
		stateTime = 0;
		updateFrame();
		// setRegion(getFrame(stateTime));
	}

}
