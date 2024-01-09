import java.util.Iterator;

/**
 * this is an iterable list class that stores trees and their data points
 * collected from the tree census file
 * 
 * @author Finn Fetherstonhaugh
 *
 */

public class TreeList {

	/**
	 * this class is responsible for keeping track of memory addresses of trees
	 * 
	 * @author Finn Fetherstonhaugh
	 *
	 */
	private class Node {
		public Tree data;
		public Node next;

		public Node(Tree data) {
			this.data = data;
			this.next = null;
		}
	}

	/**
	 * this class is responsible for iterating over a list of trees using their node
	 * values
	 * 
	 * @author Finn Fetherstonhaugh
	 *
	 */
	private class Itr implements Iterator<Tree> { // CITATION: code from LinkedList 3 presentation slides (slide 24)
		private Node current = head;

		public boolean hasNext() {
			return current != null;
		}

		public Tree next() {
			Tree tmp = current.data;
			current = current.next;
			return tmp;
		}
	}

	// each treeList is assigned a head node, tail node, and size to keep track of
	// the first and last trees as well as the number of trees in the list
	private Node head;
	private Node tail;
	private int size;

	/*
	 * this constructor sets head and tail nodes to null when the list is first
	 * created
	 */
	public TreeList() {
		head = null;
		tail = null;
	}

	/*
	 * this method adds a tree to the tree list
	 * 
	 * a tree is passed as a parameter
	 * 
	 * no return value
	 * 
	 * illegal arguement excpetion thrown if parameter value is null
	 */
	public void add(Tree tree) {

		if (tree == null) {
			throw new IllegalArgumentException("Tree object cannot be null");
		}
		Node n = new Node(tree);
		if (head == null && tail == null) {
			head = n;
			tail = n;
		} else {
			tail.next = n;
			tail = tail.next;
		}
		size++;

	}

	/*
	 * this class makes an iterator for the tree list to use
	 * 
	 * no parameter
	 * 
	 * new iterator object returned
	 * 
	 * no excpetions thrown
	 */
	public Iterator<Tree> iterator() {
		return new Itr();
	}

	// returns the size variable representing the number of trees in the list
	public int getTotalNumberOfTrees() {
		return size;
	}

	/*
	 * parses through the treelist and finds trees that have a matching species
	 * common name with the parameter
	 * 
	 * parameter is a string that represetns a species
	 * 
	 * returns an int the represents the number of found matches
	 * 
	 * no excpetions thrown
	 */
	public int getCountByCommonName(String speciesName) {
		// CITATION: got this iterator implementation code from slide 22 of LinkedLists
		// 3 lecture slides
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		int counter = 0;
		while (itr.hasNext()) {
			Tree t = itr.next();
			if (t.getSpc_common().equalsIgnoreCase(speciesName)) {
				counter += 1;
			}
		}
		return counter;

	}

	/*
	 * parses through the treelist and finds trees that have a matching species
	 * common OR latin name species with the parameter
	 * 
	 * parameter is a string that represetns a species
	 * 
	 * returns an int the represents the number of found matches
	 * 
	 * no excpetions thrown
	 */
	public int getCountByGenericName(String speciesName) {
		// CITATION: got this iterator implementation code from slide 22 of LinkedLists
		// 3 lecture slides
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		int counter = 0;
		while (itr.hasNext()) {
			Tree t = itr.next();
			if (t.getSpc_common().toLowerCase().contains(speciesName.toLowerCase())
					|| t.getSpc_latin().toLowerCase().contains(speciesName.toLowerCase())) {
				counter += 1;
			}
		}
		return counter;
	}

	/*
	 * parses through the treelist and finds trees that have a matching species
	 * latin name with the parameter
	 * 
	 * parameter is a string that represetns a species
	 * 
	 * returns an int the represents the number of found matches
	 * 
	 * no excpetions thrown
	 */
	public int getCountByLatinName(String speciesName) {// method that returns the number of Tree objects in the list
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		int counter = 0;
		while (itr.hasNext()) {
			Tree t = itr.next();
			if (t.getSpc_latin().equalsIgnoreCase(speciesName)) {
				counter += 1;
			}
		}
		return counter;
	}

	/*
	 * parses through the treelist and finds trees that have a matching borgough
	 * with the parameter
	 * 
	 * parameter is a string that represetns a borough
	 * 
	 * returns an int the represents the number of found matches
	 * 
	 * no excpetions thrown
	 */
	public int getCountByBorough(String boroName) { // method that returns the number of Tree objects in the list that
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		int counter = 0;
		while (itr.hasNext()) {
			Tree t = itr.next();
			if (t.getBoroname().equalsIgnoreCase(boroName)) {
				counter += 1;
			}
		}
		return counter;
	}

	/*
	 * parses through the treelist and finds trees that have a matching borgough and
	 * common name with the parameters
	 * 
	 * parameters is a string that represetns a commonname and a string that
	 * represents and borough name
	 * 
	 * returns an int the represents the number of found matches
	 * 
	 * no excpetions thrown
	 */
	public int getCountByCommonNameBorough(String speciesName, String boroName) { // Tree objects in the list whose
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		int counter = 0;
		while (itr.hasNext()) {
			Tree t = itr.next();
			if (t.getSpc_common().equalsIgnoreCase(speciesName) && t.getBoroname().equalsIgnoreCase(boroName)) {
				counter += 1;
			}
		}
		return counter;
	}

	/*
	 * parses through the treelist and finds trees that have a matching borgough and
	 * common name OR latin name with the parameters
	 * 
	 * parameters is a string that represetns a commonname OR latin name and a
	 * string that represents and borough name
	 * 
	 * returns an int the represents the number of found matches
	 * 
	 * no excpetions thrown
	 */
	public int getCountByGenericNameBorough(String speciesName, String boroName) { // Tree objects in the list whose
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		int counter = 0;
		while (itr.hasNext()) {
			Tree t = itr.next();
			if ((t.getSpc_common().toLowerCase().contains(speciesName.toLowerCase())
					|| t.getSpc_latin().toLowerCase().contains(speciesName.toLowerCase()))
					&& t.getBoroname().toLowerCase().contains(boroName.toLowerCase())) {
				counter += 1;
			}
		}
		return counter;
	}

	/*
	 * parses through the treelist and finds trees that have a matching borgough and
	 * latin name with the parameters
	 * 
	 * parameters is a string that represetns a latin and a string that represents
	 * and borough name
	 * 
	 * returns an int the represents the number of found matches
	 * 
	 * no excpetions thrown
	 */
	public int getCountByLatinNameBorough(String speciesName, String boroName) {
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		int counter = 0;
		while (itr.hasNext()) {
			Tree t = itr.next();
			if (t.getSpc_latin().equalsIgnoreCase(speciesName) && t.getBoroname().equalsIgnoreCase(boroName)) {
				counter += 1;
			}
		}
		return counter;
	}

	/*
	 * prints out the all the data field of a tree as a string
	 * 
	 * no parameters
	 * 
	 * no return value
	 * 
	 * no excpetions thrown
	 */
	@Override
	public String toString() {
		Iterator<Tree> itr = this.iterator();
		itr = this.iterator();
		String s = "";
		while (itr.hasNext()) {
			Tree t = itr.next();
			s += String.valueOf(t.getTree_id()) + "," + t.getStatus() + "," + t.getHealth() + "," + t.getSpc_latin()
					+ "," + t.getSpc_common() + "," + t.getZipcode() + "," + t.getBoroname() + ","
					+ Double.toString(t.getX_sp()) + "," + Double.toString(t.getY_sp()) + "\n";
		}
		return s;
	}
}
