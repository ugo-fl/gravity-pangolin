package fr.gravity.pangolin.entity.pangolin;



public class WalkingPangolinSprite extends PangolinGraphic {

	public WalkingPangolinSprite(Pangolin pangolin) {
		super(pangolin);
	}

	@Override
	public void process() {
		stateTime = 0;
		setRegion(getFrame(stateTime));
	}
	
}
