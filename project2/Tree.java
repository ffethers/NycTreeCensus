/**
 * This class models a tree based on the data points in the census file and
 * includes methods that allow you to access and compare those data fields
 * 
 * @author Finn Fetherstonhaugh
 */

public class Tree implements Comparable<Tree> {

	private int tree_id;
	private String status;
	private String health;
	private String spc_latin;
	private String spc_common;
	private int zipcode;
	private String boroname;
	private double x_sp;
	private double y_sp;

	public Tree(int TreeID, TreeSpecies Species) {
		// Checks if the parameter values are valid and assigns treeID and species
		// (including all species fields) to the parameter values
		if (Species == null) {
			throw new IllegalArgumentException("Invalid input, cannot be null");
		}
		tree_id = TreeID;
		spc_latin = Species.getLatinName();
		spc_common = Species.getCommonName();
		if (tree_id <= 0) {
			throw new IllegalArgumentException("Invalid input, must be a positive number");
		}
	}

	// returns current value for tree id
	public int getTree_id() {
		return tree_id;
	}

	// sets the current value of tree id to the parameter value
	public void setTree_id(int tree_id) {
		this.tree_id = tree_id;
	}

	// returns current value for tree status
	public String getStatus() {
		return status;
	}

	// sets the current value of tree status to the parameter value
	// throws a illegal argument exception if parameter does not equal a valid input
	public void setStatus(String status) {
		if (status.equalsIgnoreCase("Alive") || status.equalsIgnoreCase("Dead") || status.equalsIgnoreCase("Stump")
				|| status.equalsIgnoreCase("") || status.equalsIgnoreCase(null)) {
			this.status = status;
		} else {
			throw new IllegalArgumentException("Invalid input, must be 'Dead', 'Stump");
		}
	}

	// returns current value for tree health
	public String getHealth() {
		return health;
	}

	// sets the current value of tree health to the parameter value
	// throws a illegal argument exception if parameter does not equal a valid input
	public void setHealth(String health) {
		if (health.equalsIgnoreCase("Good") || health.equalsIgnoreCase("Fair") || health.equalsIgnoreCase("Poor")
				|| health.equalsIgnoreCase("") || health.equalsIgnoreCase(null)) {
			this.health = health;
		} else {
			throw new IllegalArgumentException("Invalid input, must be 'Dead', 'Stump");
		}
	}

	// returns current value for tree latin name
	public String getSpc_latin() {
		return spc_latin;
	}

	// sets the current value of tree latin name to the parameter value
	// throws a illegal argument exception if parameter does not equal a valid input
	public void setSpc_latin(String spc_latin) {
		if (!(spc_latin.equals(null))) {
			this.spc_latin = spc_latin;
		} else {
			throw new IllegalArgumentException("Invalid input, cannot be null");
		}
	}

	// returns current value for tree common name
	public String getSpc_common() {
		return spc_common;
	}

	// sets the current value of tree common name to the parameter value
	// throws a illegal argument exception if parameter does not equal a valid input
	public void setSpc_common(String spc_common) {
		if (!(spc_common.equals(null))) {
			this.spc_common = spc_common;
		} else {
			throw new IllegalArgumentException("Invalid input, cannot be null");
		}
	}

	// returns current value for tree zipcode
	public int getZipcode() {
		return zipcode;
	}

	// sets the current value of tree zipcode to the parameter value
	// throws a illegal argument exception if parameter does not equal a valid input
	public void setZipcode(int zipcode) {
		if (zipcode >= 0 && zipcode <= 99999) {
			int size = Integer.toString(zipcode).length();
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < 5 - size; i++) {
				s.append('0');
			}
			s.append(Integer.toString(zipcode));
			this.zipcode = Integer.valueOf(s.toString());
		} else {
			throw new IllegalArgumentException("Invalid input, zipcode must be between 0 and 99999");
		}
	}

	// returns current value for tree borough name
	public String getBoroname() {
		return boroname;
	}

	// sets the current value of tree borough to the parameter value
	// throws a illegal argument exception if parameter does not equal a valid input
	public void setBoroname(String boroname) {
		if (boroname.equalsIgnoreCase("Manhattan") || boroname.equalsIgnoreCase("Bronx")
				|| boroname.equalsIgnoreCase("Queens") || boroname.equalsIgnoreCase("Staten Island")
				|| boroname.equalsIgnoreCase("Brooklyn")) {
			this.boroname = boroname;
		} else {
			throw new IllegalArgumentException("Invalid input, this is not a New York Borough");
		}
	}

	// returns current value for tree X_sp
	public double getX_sp() {
		return x_sp;
	}

	// sets the current value of x_sp to the parameter value
	public void setX_sp(double x_sp) {
		this.x_sp = x_sp;
	}

	// returns current value for Y_sp
	public double getY_sp() {
		return y_sp;
	}

	// sets the current value of y_sp to the parameter value
	public void setY_sp(double y_sp) {
		this.y_sp = y_sp;
	}

	/*
	 * method evaluates if the value of 2 trees is equal based on id and common name
	 * 
	 * parameter of object class used for exception handling and later cast as a
	 * tree for comparison (expected that a tree will be inputed)
	 * 
	 * returns true if the trees have equal id and common name, false if not
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
		if (!(obj instanceof Tree)) {
			return false;
		}
		Tree other = (Tree) obj;
		if (other.getSpc_common().equalsIgnoreCase(this.getSpc_common())
				&& other.getSpc_latin().equalsIgnoreCase(this.getSpc_latin())
				&& other.getTree_id() == this.getTree_id()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * method compares the value of 2 tree objects
	 * 
	 * tree is passed as a parameter
	 * 
	 * returns 0 if the tree id and common name are equal. returns -1 if not equal
	 * and this.commonName is earlier in the alphabet than o.commonName. returns 1
	 * if not equal and this.commonName is later in the alphabet than o.commonName
	 * 
	 * no thrown exceptions
	 */
	@Override
	public int compareTo(Tree o) {
		int asciiDifference = this.getSpc_common().compareToIgnoreCase(o.getSpc_common());

		if (asciiDifference < 0) {
			return -1;
		} else if (asciiDifference > 0) {
			return 1;
		} else {
			if (Integer.compare(this.getTree_id(), o.getTree_id()) < 0) {
				return -1;
			} else if (Integer.compare(this.getTree_id(), o.getTree_id()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	/*
	 * method prints out common name and tree id values of a tree object
	 * 
	 * no parameter
	 * 
	 * no return value
	 * 
	 * no thrown exceptions
	 */
	@Override
	public String toString() {
		return this.getSpc_common() + "," + this.getTree_id();
	}

}
