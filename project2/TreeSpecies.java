public class TreeSpecies {

	/**
	 * class represents a tree species by creating an object that stores the common
	 * and latin name of said species
	 * 
	 * @author Finn Fetherstonhaugh
	 */
	private String commonName;
	private String latinName;

	// constructor that throws Illegal Argument Excpetion if parameter is null, and
	// otherwise sets the latin and common names to the passed values
	public TreeSpecies(String CommonName, String LatinName) {
		if (CommonName == null || LatinName == null) {
			throw new IllegalArgumentException("Invalid input, cannot be null");
		}
		setCommonName(CommonName);
		setLatinName(LatinName);
	}

	// returns the current value of common name
	public String getCommonName() {
		return commonName;
	}

	// sets the value of common name to the passed string
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	// returns the current value of latin name
	public String getLatinName() {
		return latinName;
	}

	// sets the value of latin name to the passed string
	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	/*
	 * method evaluates if the value of 2 treesSpecies are equal based on latin name
	 * and common name
	 * 
	 * parameter of object class used for exception handling and later cast as a
	 * tree Species for comparison (expected that a tree will be inputed)
	 * 
	 * returns true if the trees have equal latin and common names, false if not
	 * 
	 * no exceptions thrown
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TreeSpecies)) {
			return false;
		}
		TreeSpecies other = (TreeSpecies) obj;
		if (other.getCommonName().equalsIgnoreCase(this.getCommonName())
				&& other.getLatinName().equalsIgnoreCase(this.getLatinName())) {
			return true;
		} else {
			return false;
		}
	}
}
