package fr.gravity.pangolin.game;

import com.badlogic.gdx.files.FileHandle;

public class Pack {
	
	private String name;
	private FileHandle[] maps;
	
	public Pack(String name, FileHandle packDirectory) {
		this.name = name;
		maps = packDirectory.list();
	}
	
	public String getName() {
		return name;
	}

	public FileHandle[] getMaps() {
		return maps;
	}
	
	public FileHandle getMap(int index) {
		if (index >= maps.length)
			throw new NullPointerException("map does not exist (yet!)");
		return maps[index];
	}
}
