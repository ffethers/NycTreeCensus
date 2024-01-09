import java.util.ArrayList;

/**
 * this class is an extensions ArryaList that stores a number of tree species
 * 
 * @author Finn Fetherstonhaugh
 *
 */
public class TreeSpeciesList extends ArrayList<TreeSpecies> {

	// empty contructor
	public TreeSpeciesList() {

	}

	/*
	 * searches through the TSL it is called on and returns a new TSL that contains
	 * the TS that match the parameter value
	 * 
	 * parameters is a string that represetns a commonName of a tree species
	 * 
	 * returns a treeList of all species that match the parameter
	 * 
	 * no excpetions thrown
	 */
	public TreeSpeciesList getByCommonName(String keyword) {
		if (keyword == null) {
			throw new IllegalArgumentException("Invalid input, arguement cannot be null");
		}
		TreeSpeciesList tslCommon = new TreeSpeciesList();
		for (TreeSpecies i : this) {
			if (i.getCommonName().toLowerCase().contains(keyword.toLowerCase())) {
				tslCommon.add(i);
			}
		}
		if (tslCommon.size() == 0) {
			return null;
		}
		return tslCommon;
	}

	/*
	 * searches through the TSL it is called on and returns a new TSL that contains
	 * the TS that match the parameter value
	 * 
	 * parameters is a string that represetns a latinName of a tree species
	 * 
	 * returns a treeList of all species that match the parameter
	 * 
	 * no excpetions thrown
	 */
	public TreeSpeciesList getByLatinName(String keyword) {
		if (keyword == null) {
			throw new IllegalArgumentException("Invalid input, arguement cannot be null");
		}
		TreeSpeciesList tslLatin = new TreeSpeciesList();
		for (TreeSpecies i : this) {
			if (i.getLatinName().toLowerCase().contains(keyword.toLowerCase())) {
				tslLatin.add(i);
			}
		}
		if (tslLatin.size() == 0) {
			return null;
		}
		return tslLatin;
	}

	/*
	 * turns the TSL into an String ArrayList of the commonName's of each species
	 * 
	 * no parameters
	 * 
	 * returns a array list of strings that represents the given TSL using common
	 * names
	 * 
	 * no excpetions thrown
	 */
	public ArrayList<String> toStringArrayList() {
		ArrayList<String> ArrayListStrings = new ArrayList<String>();
		for (TreeSpecies i : this) {
			ArrayListStrings.add(i.getCommonName());
		}
		return ArrayListStrings;
	}

}
