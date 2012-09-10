package fr.gravity.pangolin;

import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.block.Block;
import fr.gravity.pangolin.entity.Pangolin;
import fr.gravity.pangolin.util.Numbers;

public class CollisionHelper {

	private Pangolin pangolin;
	private Array<Block> blocks;

	private static CollisionHelper instance;

	private CollisionHelper(Pangolin pangolin, Array<Block> blocks) {
		this.pangolin = pangolin;
		this.blocks = blocks;
	}

	public static CollisionHelper getInstance(Pangolin pangolin, Array<Block> blocks) {
		if (instance == null)
			instance = new CollisionHelper(pangolin, blocks);
		return instance;
	}

	public static CollisionHelper getInstance() {
		return instance;
	}

	public boolean collidesLeft(Block block) {
		if (block == null)
			return false;
		if (Numbers.between(pangolin.getPosition().x, block.getPosition().x, block.getPosition().x
				+ block.getBounds().getWidth()))
			return true;
		return false;
	}

	public boolean collidesRight(Block block) {
		if (block == null)
			return false;
		if (Numbers.between(pangolin.getPosition().x + pangolin.getBounds().getWidth(), block.getPosition().x,
				block.getPosition().x + block.getBounds().getWidth()))
			return true;
		return false;
	}

	public boolean collidesUp(Block block) {
		if (block == null)
			return false;
		if (Numbers.between(pangolin.getPosition().y, block.getPosition().y, block.getPosition().y
				+ block.getBounds().getHeight()))
			return true;
		return false;
	}

	public boolean collidesDown(Block block) {
		if (block == null)
			return false;
		if (Numbers.between(pangolin.getPosition().y + pangolin.getBounds().getHeight(), block.getPosition().y,
				block.getPosition().y + block.getBounds().getHeight()))
			return true;
		return false;
	}

	/**
	 * Returns the Block the Pangolin collides with if and only if the block is
	 * Solid
	 * 
	 * @param block
	 * @param pangolin
	 * @return
	 */
	public Block collides(Pangolin pangolin) {
		float pangolinPosX = pangolin.getPosition().x;
		float pangolinPosY = pangolin.getPosition().y;
		float pangolinWidth = pangolin.getBounds().getWidth();
		float pangolinHeight = pangolin.getBounds().getHeight();

		for (Block block : blocks) {
			float blockPosX = block.getPosition().x;
			float blockPosY = block.getPosition().y;
			float blockWidth = block.getBounds().getWidth();
			float blockHeight = block.getBounds().getHeight();

			if (Numbers.betweenStrict(pangolinPosX, blockPosX - pangolinWidth, blockPosX + blockWidth))
				if (Numbers.betweenStrict(pangolinPosY, blockPosY - pangolinHeight, blockPosY + blockHeight)) {
					if (block.collides())
						return block;
				}
		}
		return null;
	}
}
