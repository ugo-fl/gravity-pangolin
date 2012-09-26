package fr.gravity.pangolin;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.BranchBlock;
import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.block.ExitBlock.ExitSide;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;
import fr.gravity.pangolin.entity.graphic.BranchBlockGraphic.BranchFramePosition;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.exception.InvalidMapException;
import fr.gravity.pangolin.screen.AbstractScreen2;
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

	private Gravity gravity = new Gravity(Direction.DOWN);

	/**
	 * Singleton accessor.
	 * 
	 * @param pangolinMap
	 * @return
	 * @throws InvalidMapException
	 */
	public static PangolinWorld getInstance(FileHandle pangolinMap) {
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
		if (instance == null)
			throw new NullPointerException("The Pangolin World has not been initiated yet.");
		return instance;
	}

	public PangolinWorld(FileHandle pangolinMap) {
		try {
			mapFile = new BufferedReader(pangolinMap.reader());
			String sizeLine = mapFile.readLine();
			String[] size = sizeLine.split(";");
			if (size.length != 2)
				throw new InvalidMapException("Invalid definition of size (size should be placed on the first line of the map file as sizeX;sizeY)");
			sizeX = Integer.valueOf(size[0]);
			sizeY = Integer.valueOf(size[1]);
			if (sizeX <= 0 || sizeY <= 0)
				throw new InvalidMapException("Invalid definition of size (size should be > 0 for both X and Y)");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initiate the world. The game screen must be initiated first.
	 */
	public void init(Stage stage) {
		// Checks if the screen is set first
		AbstractScreen2 screen = GameUtil.getScreen();
		if (screen == null)
			throw new NullPointerException("The screen has not been initiated yet.");

		background = new Background();
		Entity exitBlock = null;
		try {
			int y = sizeY - 1;
			int x = 0;
			for (String line; y >= 0 && (line = mapFile.readLine()) != null; y--) {
				x = 0;
				for (; x < sizeX && x < line.length(); x++) {

					String sym = String.valueOf(line.charAt(x));

					if (BLOCK_SYM.equalsIgnoreCase(sym)) {
						BranchBlock branchBlock = new BranchBlock(x, y, getBranchFramePosition(x, y, line));
						addEntity(stage, branchBlock);
					} else if (START_SYM.equalsIgnoreCase(sym)) {
						pangolin = new Pangolin(x, y);
						stage.addActor(pangolin);
					} else if (GRAVITY_CHANGER_SYM.equalsIgnoreCase(sym)) {
						GravityChangerBlock gravityChangerBlock = new GravityChangerBlock(x, y, gravity);
						addEntity(stage, gravityChangerBlock);
					} else if (FINISH_SYM.equalsIgnoreCase(sym)) {
						exitBlock = new ExitBlock(x, y, ExitSide.EXIT_DOWN);
						addEntity(stage, exitBlock);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (pangolin == null)
			throw new InvalidMapException("No start point found in map.");
		else if (exitBlock == null)
			throw new InvalidMapException("No finish point found in map.");
	}

	private void addEntity(Stage stage, Entity entity) {
		entities.add(entity);
		stage.addActor(entity);
	}

	private BranchFramePosition getBranchFramePosition(int x, int y, String line) {
		BranchFramePosition branchFramePos = BranchFramePosition.START;
		String previousSym = "";
		String nextSym = "";

		if ((x - 1) >= 0)
			previousSym = String.valueOf(line.charAt(x - 1));
		if ((x + 1) < line.length())
			nextSym = String.valueOf(line.charAt(x + 1));

		if (BLOCK_SYM.equalsIgnoreCase(previousSym) && BLOCK_SYM.equalsIgnoreCase(nextSym))
			branchFramePos = BranchFramePosition.MIDDLE;
		else if (BLOCK_SYM.equalsIgnoreCase(previousSym))
			branchFramePos = BranchFramePosition.END;
		return branchFramePos;
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
