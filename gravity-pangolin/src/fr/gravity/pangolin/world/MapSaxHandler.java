package fr.gravity.pangolin.world;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;
import fr.gravity.pangolin.entity.block.HarmfulBlock;
import fr.gravity.pangolin.entity.block.SpadesBlock;
import fr.gravity.pangolin.entity.block.WallBlock;
import fr.gravity.pangolin.entity.mob.StarFish;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;

public class MapSaxHandler extends DefaultHandler {

	// Element ids
	private static final String MAP = "map";
	private static final String GRAVITY_CHANGER = "gravityChanger";
	private static final String EXIT = "exit";
	private static final String PANGOLIN = "pangolin";
	private static final String WALL_BLOCK = "wallBlock";
	private static final String HARMFUL_BLOCK = "harmfulBlock";
	private static final String STAR_FISH = "starFish";
	private static final String SPADES_BLOCK = "spadesBlock";
	
	// Attributes ids
	private static final String DIRECTION = "direction";
	private static final String Y = "y";
	private static final String X = "x";

	private String temp;

	private GravityPangolinWorld gravityPangolinWorld;

	/*
	 * When the parser encounters plain text (not XML elements), it calls(this
	 * method, which accumulates them in a string buffer
	 */
	public void characters(char[] buffer, int start, int length) {
		temp = new String(buffer, start, length);
	}

	/*
	 * Every time the parser encounters the beginning of a new element, it calls
	 * this method, which resets the string buffer
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		temp = "";

		float x = Float.valueOf(attributes.getValue(X));
		float y = Float.valueOf(attributes.getValue(Y));
		if (qName.equalsIgnoreCase(MAP)) {
			gravityPangolinWorld = new GravityPangolinWorld((int) x, (int) y);
		} else {
			y = gravityPangolinWorld.getSizeY() - y - 1;

			if (qName.equalsIgnoreCase(WALL_BLOCK)) {
				gravityPangolinWorld.addEntity(new WallBlock(gravityPangolinWorld, x, y));
			} else if (qName.equalsIgnoreCase(PANGOLIN)) {
				gravityPangolinWorld.addEntity(new Pangolin(gravityPangolinWorld, x, y));
			} else if (qName.equalsIgnoreCase(EXIT)) {
				Direction direction = Direction.valueOf(attributes.getValue(DIRECTION).toUpperCase());
				gravityPangolinWorld.addEntity(new ExitBlock(gravityPangolinWorld, x, y, direction));
			} else if (qName.equalsIgnoreCase(GRAVITY_CHANGER)) {
				gravityPangolinWorld.addEntity(new GravityChangerBlock(gravityPangolinWorld, x, y));
			} else if (qName.equalsIgnoreCase(HARMFUL_BLOCK)) {
				Direction direction = Direction.valueOf(attributes.getValue(DIRECTION).toUpperCase());
				gravityPangolinWorld.addEntity(new HarmfulBlock(gravityPangolinWorld, x, y, direction));
			} else if (qName.equalsIgnoreCase(STAR_FISH)) {
				gravityPangolinWorld.addEntity(new StarFish(gravityPangolinWorld, x, y));
			} else if (qName.equalsIgnoreCase(SPADES_BLOCK)) {
				Direction direction = Direction.valueOf(attributes.getValue(DIRECTION).toUpperCase());
				gravityPangolinWorld.addEntity(new SpadesBlock(gravityPangolinWorld, x, y, direction));
			}
		}
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
	}

	public GravityPangolinWorld getGravityPangolinWorld() {
		return gravityPangolinWorld;
	}

}
