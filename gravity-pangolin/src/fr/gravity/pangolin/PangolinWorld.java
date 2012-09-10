package fr.gravity.pangolin;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.block.Block;
import fr.gravity.pangolin.block.ExitBlock;
import fr.gravity.pangolin.block.GravityChangerBlock;
import fr.gravity.pangolin.block.BranchBlock;
import fr.gravity.pangolin.block.BranchBlock.BranchFramePos;
import fr.gravity.pangolin.entity.Pangolin;
import fr.gravity.pangolin.exception.InvalidMapException;

public class PangolinWorld {

	private static PangolinWorld instance;

	enum GravityAxis {
		X_AXIS, Y_AXIS
	}

	private int sizeX = 0;
	private int sizeY = 0;

	private final static String BLOCK_SYM = "X";
	private final static String GRAVITY_CHANGER_SYM = "O";
	private final static String START_SYM = "S";
	private final static String FINISH_SYM = "F";

	/** The blocks making up the world **/
	private Array<Block> blocks = new Array<Block>();

	// private Array<GravityChanger> gravityChangers = new
	// Array<GravityChanger>();
	/** Our player controlled hero **/
	private Pangolin pangolin;

	private Background background;

	private Gravity gravity = new Gravity(Side.DOWN);

	public static PangolinWorld getInstance() {
		if (instance == null)
			instance = new PangolinWorld();
		return instance;
	}

	public void init(FileHandle pangolinMap) throws InvalidMapException {
		background = new Background();
		Block exitBlock = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(pangolinMap.reader());
			int y = 0;
			int x = 0;
			for (String line; (line = bufferedReader.readLine()) != null;) {
				x = 0;
				for (; x < line.length(); x++) {
					String sym = String.valueOf(line.charAt(x));
					if (BLOCK_SYM.equalsIgnoreCase(sym)) {
						BranchFramePos branchFramePos = BranchFramePos.START;
						String previousSym = String.valueOf(line.charAt(x - 1));
						String nextSym = String.valueOf(line.charAt(x + 1));
						if (BLOCK_SYM.equalsIgnoreCase(previousSym) && BLOCK_SYM.equalsIgnoreCase(nextSym))
							branchFramePos = BranchFramePos.MIDDLE;
						else if (BLOCK_SYM.equalsIgnoreCase(previousSym))
							branchFramePos = BranchFramePos.END;
						blocks.add(new BranchBlock(new Vector2(x, y), branchFramePos));
					} else if (START_SYM.equalsIgnoreCase(sym)) {
						pangolin = new Pangolin(new Vector2(x, y));
					} else if (GRAVITY_CHANGER_SYM.equalsIgnoreCase(sym))
						blocks.add(new GravityChangerBlock(new Vector2(x, y), gravity, Side.DOWN, Side.RIGHT));
					else if (FINISH_SYM.equalsIgnoreCase(sym)) {
						blocks.add((exitBlock = new ExitBlock(new Vector2(x, y), 0)));
					}
				}
				y++;
			}
			sizeX = x;
			sizeY = y;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (pangolin == null)
			throw new InvalidMapException("No start point found in map '" + pangolinMap.name());
		else if (exitBlock == null)
			throw new InvalidMapException("No finish point found in map '" + pangolinMap.name());
		// CollisionHelper singleton initialization
		CollisionHelper.getInstance(pangolin, blocks);
	}

	public Array<Block> getBlocks() {
		return blocks;
	}

	public Pangolin getPangolin() {
		return pangolin;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public Gravity getGravity() {
		return gravity;
	}

	public Background getBackground() {
		return background;
	}

}
