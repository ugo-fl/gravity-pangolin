package fr.gravity.pangolin.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.badlogic.gdx.files.FileHandle;

import fr.gravity.pangolin.world.GravityPangolinWorld;
import fr.gravity.pangolin.world.MapSaxHandler;

public class Pack {

	private String name;
	private FileHandle[] maps;

	public Pack(String name, FileHandle packDirectory) {
		this.name = name;
		maps = packDirectory.list(".xml");
	}

	public String getName() {
		return name;
	}

	public GravityPangolinWorld getWorld(int index) {
		if (index >= maps.length)
			throw new NullPointerException("map does not exist (yet!)");
		
		MapSaxHandler handler = new MapSaxHandler();
		try {
			SAXParserFactory spfac = SAXParserFactory.newInstance();
			SAXParser sp;
			
			sp = spfac.newSAXParser();
			sp.parse(maps[index].file(), handler);
			
			return handler.getGravityPangolinWorld();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int size() {
		return maps.length;
	}
}
