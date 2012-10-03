package fr.gravity.pangolin.entity.graphic;

import fr.gravity.pangolin.entity.pangolin.Pangolin;


public class IdlePangolinGraphic extends PangolinGraphic {

	public IdlePangolinGraphic(Pangolin pangolin, float x, float y) {
		super(pangolin, x, y);
		setRegion(getFrame(stateTime));
	}

	@Override
	public void process() {
		stateTime = 0;
		updateFrame();
//		setRegion(getFrame(stateTime));
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
