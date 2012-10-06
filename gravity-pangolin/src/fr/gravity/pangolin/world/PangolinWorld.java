package fr.gravity.pangolin.world;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.BranchBlock;
import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;
import fr.gravity.pangolin.entity.graphic.BranchBlockGraphic.BranchFramePosition;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.exception.InvalidMapException;
import fr.gravity.pangolin.screen.AbstractScreen;
import fr.gravity.pangolin.util.GameUtil;

public class PangolinWorld {

	// Options
	private static final int GRAVITY = -10;

	// Singleton instance
	private static PangolinWorld instance;

	// The game world
	private World world;

	private int sizeX = 0;
	private int sizeY = 0;

	private final static String OPTION_PANGOLIN_DIRECTION = "PANGOLINDIR";
	private final static String OPTION_EXIT_DIRECTION = "EXITDIR";
	private final static String OPTION_MAP_SIZE = "MAPSIZE";

	private final static String SYM_BLOCK = "X";
	private final static String SYM_GRAVITY_CHANGER = "O";
	private final static String SYM_START = "S";
	private final static String SYM_EXIT = "E";

	/** The map **/
	private BufferedReader mapFile;

	/** The entities making up the world **/
	private Array<Entity> entities = new Array<Entity>();

	/** Our controlled hero **/
	// private Pangolin pangolin = new Pangolin();
	private Pangolin pangolin;

	/** Finish spot **/
	// private ExitBlock exitBlock = new ExitBlock(this);
	private ExitBlock exitBlock;

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
		readOptions(pangolinMap);
		world = new World(new Vector2(0, GRAVITY), true);
	}

	private void readOptions(FileHandle pangolinMap) {
		try {
			mapFile = new BufferedReader(pangolinMap.reader());
			String line = mapFile.readLine();
			while (true) {
				String[] option = line.split("=");
				if (option.length != 2)
					break;
				String optionName = option[0];
				String optionValue = option[1];
				if (OPTION_MAP_SIZE.equals(optionName))
					readOptionMapSize(optionValue);
				// else if (OPTION_PANGOLIN_DIRECTION.equals(optionName))
				// readOptionPangolinDirection(optionValue);
				// else if (OPTION_EXIT_DIRECTION.equals(optionName))
				// readOptionFinishDirection(optionValue);
				line = mapFile.readLine();
			}
			if (sizeX <= 0 || sizeY <= 0)
				throw new InvalidMapException("Invalid definition of size (size should be > 0 for both X and Y)");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readOptionFinishDirection(String optionValue) {
		exitBlock.setDirection(Direction.values()[Integer.valueOf(optionValue)]);
	}

	private void readOptionPangolinDirection(String optionValue) {
		pangolin.setDirection(Direction.values()[Integer.valueOf(optionValue)]);
	}

	private void readOptionMapSize(String optionValue) {
		String[] size = optionValue.split(";");
		if (size.length != 2)
			throw new InvalidMapException(
					"Invalid definition of size (size should be placed on the first line of the map file as sizeX;sizeY)");
		sizeX = Integer.valueOf(size[0]);
		sizeY = Integer.valueOf(size[1]);

	}

	/**
	 * Initiate the world. The game screen must be initiated first.
	 */
	public void init(Stage stage) {
		// Checks if the screen is set first
		AbstractScreen screen = GameUtil.getScreen();
		if (screen == null)
			throw new NullPointerException("The screen has not been initiated yet.");

		try {
			int y = sizeY - 1;
			int x = 0;
			for (String line; y >= 0 && (line = mapFile.readLine()) != null; y--) {
				x = 0;
				for (; x < sizeX && x < line.length(); x++) {

					String sym = String.valueOf(line.charAt(x));

					if (SYM_BLOCK.equalsIgnoreCase(sym)) {
						BranchBlock branchBlock = new BranchBlock(world, x, y);
						addEntity(stage, branchBlock);
					} else if (SYM_START.equalsIgnoreCase(sym)) {
						pangolin = new Pangolin(world, x, y);
						addEntity(stage, pangolin);
					}
					// else if (SYM_GRAVITY_CHANGER.equalsIgnoreCase(sym)) {
					// GravityChangerBlock gravityChangerBlock = new
					// GravityChangerBlock(x, y, gravity);
					// addEntity(stage, gravityChangerBlock);
					// } else if (SYM_EXIT.equalsIgnoreCase(sym)) {
					// exitBlock.init(x, y);
					// }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// We add the Exit and the Pangolin at the end
		// in order to put their graphics above the rest
		// addEntity(stage, exitBlock);
		// addEntity(stage, pangolin);
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

		if (SYM_BLOCK.equalsIgnoreCase(previousSym) && SYM_BLOCK.equalsIgnoreCase(nextSym))
			branchFramePos = BranchFramePosition.MIDDLE;
		else if (SYM_BLOCK.equalsIgnoreCase(previousSym))
			branchFramePos = BranchFramePosition.END;
		return branchFramePos;
	}

	public void step(float timeStep, int velocityIterations, int positionIterations) {
		world.step(timeStep, velocityIterations, positionIterations);
	}

	// Getters/Setters

	public World getWorld() {
		return world;
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

}
