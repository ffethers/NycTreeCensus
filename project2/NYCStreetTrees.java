/**
 * This class contains the main method of the program which is responsible for
 * reading the file, collecting the data, and interacting with the user
 * 
 * @author Finn Fetherstonhaugh
 */
public class NYCStreetTrees {
	/**
	 * The main method parses through the tree census file and stores the data
	 * inside of a TreeList and a TreeSpeciesList
	 * 
	 * It then prompts the user to input the name of a tree species and scans the
	 * captured data, returning the number of trees of this type in different areas
	 * of new york city
	 */
	public static void main(String[] Args) throws FileNotFoundException {
		// New TreeList and new TreeSpeciesList for storing data as Tree objects and
		// TreeSpecies objects
		TreeList tl = new TreeList();
		TreeSpeciesList tsl = new TreeSpeciesList();

		// file reading and exception handling
		if (Args.length < 1) {
			throw new ArrayIndexOutOfBoundsException("Usage Error: must provide a command line to run the program");
		}

		File f = new File(Args[0]);

		//CITATION: Code from the colorlist example colorconverter class on ed
		if (!f.exists()) {
			System.err.println("Error: the file " + f.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		if (!f.canRead()) {
			System.err.println("Error: the file " + f.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}
		Scanner scan = new Scanner(f);
		CSV csv = new CSV(scan);

		// iterate through file to gather data and fill lists
		for (int i = 0; i < csv.getNumOfRows(); i++) {
			// each tree's data stored in an arraylist
			ArrayList<String> al = csv.getNextRow();

			// checks for tree ID and common name to decide if tree is valid
			boolean validTree = (al.get(9) != null && al.get(0) != null);
			if (validTree == true) {

				Tree currentTree = null;
				boolean hasTreeSpecies = false;

				/*
				 * iterates through the TreeSpeciesList and checks if the current tree's species
				 * is already in the TreeSpeciesList (via common and latin name strings)
				 * 
				 * if true: sets the currentTree's species to the current iteration of the
				 * TreeSpeciesList
				 * 
				 * if false: creates a new TreeSpecies object and adds it to the TreeSpecies
				 * Class and sets the currenTree's species to the newly created TreeSpecies
				 * object
				 */
				for (TreeSpecies ts : tsl) {
					if (ts.getCommonName().equalsIgnoreCase(al.get(9))
							&& ts.getLatinName().equalsIgnoreCase(al.get(8))) {
						hasTreeSpecies = true;
						currentTree = new Tree(Integer.parseInt(al.get(0), 10), ts);
						break;
					}
				}

				if (hasTreeSpecies == false) {
					TreeSpecies newSpecies = new TreeSpecies(al.get(9), al.get(8));
					try {
						currentTree = new Tree(Integer.parseInt(al.get(0), 10), newSpecies);
						tsl.add(newSpecies);
					} catch (Exception NumberFormatException) {
						validTree = false;
					}
				}

				if (validTree == true) {
					currentTree.setStatus(al.get(6));
					currentTree.setHealth(al.get(7));
					currentTree.setZipcode(Integer.parseInt(al.get(25)));
					currentTree.setBoroname(al.get(29));
					currentTree.setX_sp(Double.parseDouble(al.get(39)));
					currentTree.setY_sp(Double.parseDouble(al.get(40)));
					tl.add(currentTree);
				}
				// if tree does not have a valid id and common name, line is skipped and loop
				// continues
			} else {
				continue;
			}
		}

		// prompt user
		System.out.println("Enter the tree species to learn more about it ('quit' to stop):");
		scan = new Scanner(System.in);
		String s = scan.nextLine();

		while (!(s.equalsIgnoreCase("quit"))) {
			// search the TreeSpeciesList for the user's input and collect it in 2 TSL's
			TreeSpeciesList commonSpecies = tsl.getByCommonName(s);
			TreeSpeciesList latinSpecies = tsl.getByLatinName(s);
			// new array list of strings to add the collected species too
			ArrayList<String> matchingStrings = new ArrayList<String>();

			// check if the lists are empty and add the common and latins names of the
			// collected TSs to the String ArrayList
			if (commonSpecies != null) {
				matchingStrings = commonSpecies.toStringArrayList();
			}
			if (latinSpecies != null) {
				for (TreeSpecies latins : latinSpecies) {
					matchingStrings.add(latins.getLatinName());
				}
			}
			if (latinSpecies == null && commonSpecies == null) {
				System.out.println("\nCould not find this tree species");
				System.out.println("\nEnter the tree species to learn more about it ('quit' to stop):");
				scan = new Scanner(System.in);
				s = scan.nextLine();
				continue;
			}

			// sort and print out the String ArrayList of species names
			Collections.sort(matchingStrings);
			System.out.println("All Matching Species:");
			for (String s1 : matchingStrings) {
				System.out.printf("%8s%s\n", " ", s1);
			}

			System.out.println();
			System.out.println("Popularity in the city:");
			String[] BORO_NAMES = { "NYC", "Manhattan", "Bronx", "Brooklyn", "Queens", "Staten Island" };

			// print out the borough name, number of found trees in each borough, and total
			// number of trees in each borough
			for (int i = 0; i < 6; i++) {
				if (i == 0) {
					System.out.printf("%8s%-14s%s%19s%8.2f%s\n", "", BORO_NAMES[i], ":",
							Integer.toString(tl.getCountByGenericName(s)) + "("
									+ Integer.toString(tl.getTotalNumberOfTrees()) + ")",
							tl.getCountByGenericName(s) > 0
									? (double) tl.getCountByGenericName(s) / (double) tl.getTotalNumberOfTrees() * 100
									: 0,
							"%");
				} else {
					System.out
							.printf("%8s%-14s%s%19s%8.2f%s\n", "", BORO_NAMES[i], ":",
									Integer.toString(tl.getCountByGenericNameBorough(s, BORO_NAMES[i])) + "("
											+ Integer.toString(tl.getCountByBorough(BORO_NAMES[i])) + ")",
									tl.getCountByGenericNameBorough(s, BORO_NAMES[i]) > 0
											? (double) tl.getCountByGenericNameBorough(s, BORO_NAMES[i])
													/ (double) tl.getCountByBorough(BORO_NAMES[i]) * 100
											: 0,
									"%");
				}

			}

			// prompt user again
			System.out.println("\nEnter the tree species to learn more about it ('quit' to stop):");
			scan = new Scanner(System.in);
			s = scan.nextLine();

		}

	}

}
