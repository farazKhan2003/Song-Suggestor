

import java.io.*;
class TreeNode {
	String value;
	TreeNode left;
	TreeNode right;
	
	public TreeNode (String value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public TreeNode (String value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public TreeNode getLeft() {
		return left;
	}
	
	public TreeNode getRight() {
		return right;
	}
	
	public boolean isLeaf() {
	//if both left and right are null then it is one of the final nodes in the tree
		if (this.getLeft() == null && this.getRight() == null) {
			return true;
		}
		return false;
	}
	public String print() {
	// add a new line character after every character and add a - if it is a question to differentiate the questions and suggestions
	// using recursion
		String lines = "";
		lines = lines + (this.value);
		if (left != null) {
			lines = "-" + lines + "\n" + this.left.print();
		} 
		if (this.right != null) {
			lines = lines + "\n" + this.right.print();
		}
		return lines;
	}

}
