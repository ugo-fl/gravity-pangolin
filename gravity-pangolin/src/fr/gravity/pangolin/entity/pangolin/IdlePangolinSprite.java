package fr.gravity.pangolin.entity.pangolin;


public class IdlePangolinSprite extends PangolinGraphic {

	public IdlePangolinSprite(Pangolin pangolin) {
		super(pangolin);
	}

	@Override
	public void process() {
		stateTime = 0;
		setRegion(getFrame(stateTime));
	}

}
