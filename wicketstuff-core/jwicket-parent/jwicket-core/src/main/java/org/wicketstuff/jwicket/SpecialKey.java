package org.wicketstuff.jwicket;


public enum SpecialKey {

	SHIFT("shift", 16),
	STRG("ctrl", 17),
	ALT("alt", 18),
	ILLEGAL("illegal", -1);

	private final String name;
	private final int code;

	private SpecialKey(final String name, final int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public int getCode() {
		return this.code;
	}


	public static SpecialKey getSpecialKey(final String name) {
		for (SpecialKey key : SpecialKey.values())
			if (key.getName().equalsIgnoreCase(name))
				return key;
		return ILLEGAL;
	}

	public String toString() {
		return this.name;
	}
}
