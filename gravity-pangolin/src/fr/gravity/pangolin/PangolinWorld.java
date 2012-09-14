package fr.gravity.pangolin;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.BranchBlock;
import fr.gravity.pangolin.entity.block.BranchBlockGraphic.BranchFramePos;
import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.block.ExitBlock.ExitSide;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.exception.InvalidMapException;
import fr.gravity.pangolin.util.GameUtil;

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

	/** The map **/
	private BufferedReader mapFile;

	/** The entities making up the world **/
	private Array<Entity> entities = new Array<Entity>();

	/** Our player controlled hero **/
	private Pangolin pangolin;

	private Background background;

	private Gravity gravity = new Gravity(Side.DOWN);

	/**
	 * Singleton accessor.
	 * 
	 * @param pangolinMap
	 * @return
	 * @throws InvalidMapException
	 */
	public static PangolinWorld getInstance(FileHandle pangolinMap) {
		if (instance == null)
			instance = new PangolinWorld(pangolinMap);
		return instance;
	}

	/**
	 * Singleton accessor. Do not call this method before
	 * {@link #getInstance(FileHandle)}
	 * 
	 * @return
	 */
	public static PangolinWorld getInstance() {
		try {
			if (instance == null)
				throw new NullPointerException(
						"The Pangolin World has not been initiated yet.");
		} catch (InvalidMapException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public PangolinWorld(FileHandle pangolinMap) {
		try {
			mapFile = new BufferedReader(pangolinMap.reader());
			String sizeLine = mapFile.readLine();
			String[] size = sizeLine.split(";");
			if (size.length != 2)
				throw new InvalidMapException(
						"Invalid definition of size (size should be placed on the first line of the map file as sizeX;sizeY)");
			sizeX = Integer.valueOf(size[0]);
			sizeY = Integer.valueOf(size[1]);
			if (sizeX <= 0 || sizeY <= 0)
				throw new InvalidMapException(
						"Invalid definition of size (size should be > 0 for both X and Y)");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Init the world. The game screen must be initiated first.
	 */
	public void init() {
		// Checks if the screen is set first
		if (GameUtil.getScreen() == null)
			throw new NullPointerException(
					"The screen has not been initiated yet.");

		background = new Background();
		Entity exitBlock = null;
		try {
			int y = 0;
			int x = 0;
			for (String line; (line = mapFile.readLine()) != null;) {
				x = 0;
				for (; x < line.length(); x++) {
					String sym = String.valueOf(line.charAt(x));
					if (BLOCK_SYM.equalsIgnoreCase(sym)) {
						BranchFramePos branchFramePos = BranchFramePos.START;
						String previousSym = "";
						String nextSym = "";

						if ((x - 1) >= 0)
							previousSym = String.valueOf(line.charAt(x - 1));
						if ((x + 1) < line.length())
							nextSym = String.valueOf(line.charAt(x + 1));

						if (BLOCK_SYM.equalsIgnoreCase(previousSym)
								&& BLOCK_SYM.equalsIgnoreCase(nextSym))
							branchFramePos = BranchFramePos.MIDDLE;
						else if (BLOCK_SYM.equalsIgnoreCase(previousSym))
							branchFramePos = BranchFramePos.END;

						entities.add(new BranchBlock(x, sizeY - y, branchFramePos));
					} else if (START_SYM.equalsIgnoreCase(sym)) {
						pangolin = new Pangolin(x, sizeY - y);
					} else if (GRAVITY_CHANGER_SYM.equalsIgnoreCase(sym))
						entities.add(new GravityChangerBlock(x, sizeY - y, gravity,
								Side.DOWN, Side.RIGHT));
					else if (FINISH_SYM.equalsIgnoreCase(sym)) {
						entities.add((exitBlock = new ExitBlock(x, sizeY - y,
								ExitSide.EXIT_DOWN)));
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
			throw new InvalidMapException("No start point found in map.");
		else if (exitBlock == null)
			throw new InvalidMapException("No finish point found in map.");
	}

	public Array<Entity> getBlocks() {
		return entities;
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
