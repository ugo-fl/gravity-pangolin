package fr.gravity.pangolin.entity.pangolin;

public class WalkingPangolinGraphic extends PangolinGraphic {

	public WalkingPangolinGraphic(Pangolin pangolin, float x, float y) {
		super(pangolin, x, y);
		set(getFrame(stateTime));
	}

	@Override
	public void process() {
		updateFrame();
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchDownOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUpOut() {
		// TODO Auto-generated method stub
		
	}

}
