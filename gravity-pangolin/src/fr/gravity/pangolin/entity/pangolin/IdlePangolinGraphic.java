package fr.gravity.pangolin.entity.pangolin;


public class IdlePangolinGraphic extends PangolinGraphic {

	public IdlePangolinGraphic(Pangolin pangolin, float x, float y) {
		super(pangolin, x, y);
		set(getFrame(stateTime));
	}

	@Override
	public void process() {
		stateTime = 0;
		updateFrame();
//		setRegion(getFrame(stateTime));
	}

}
