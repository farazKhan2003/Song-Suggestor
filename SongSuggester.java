
import java.io.*;
import java.util.Scanner;

public class SongSuggester {
	
	public static boolean askYesNo(String Question) {
		System.out.println(Question);
		Scanner scan = new Scanner(System.in);
		String input;
		input = scan.nextLine();
		if (input.equals("y")) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public static TreeNode BuildTree(BufferedReader reader) throws IOException {
		String value = reader.readLine();
		TreeNode node = new TreeNode(value.substring(0));
		
		if (value.contains("-")) {
		//if it contains a - then it is a question as defined in the print method
			node.left = BuildTree(reader);
			node.right = BuildTree(reader);
			
		} else {
			return node;
			}
			return node;
		}
	
	public static void replaceNode(TreeNode current, String question){
	    if (current == null)
	        return;
	    // Replace the currents node value with the new value taken by the user
	    current.value = question;
	   
	  }
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		System.out.println("Welcome! Let's help you find a song.");
        TreeNode root;
        //if the file exists then read the files tree, otherwise create the original tree
		try (BufferedReader reader = new BufferedReader(new FileReader("suggestions.txt"))) {
			
			root = BuildTree(reader);
		} catch (IOException e) {
            
			TreeNode first = new TreeNode("Do you like classic music? [y/n]"); 
			TreeNode second = new TreeNode("Are you a fan of Mozart? [y/n]");
			TreeNode third = new
			TreeNode("\"Take Five\" by Dave Brubeck"); 
			TreeNode fourth = new
			TreeNode("Eine Kleine Nachtmusik"); 
			TreeNode fifth = new
			 TreeNode("\"3 Romances\" by Clara Schumann");
			 root = first;
			 root.left = second; 
			 root.right = third; 
			 second.left = fourth; 
			 second.right = fifth;
			 //defining the tree
			 
		}
		
		
		  
		TreeNode current = root;
		boolean playing = true; 
		while (playing == true) {
			 if(current.isLeaf() == false) {
				if (askYesNo(current.value) == true) {
					current = current.getLeft();
					//make setter method in TreeNode that calls .getLeft /.getRight
				} else {
					current = current.getRight();
					}
				 
			 } else {
				 Scanner scan = new Scanner(System.in);
				System.out.println(current.value);
				System.out.println("Is this satisfactory? [y/n]");
				String sat;
				sat = scan.nextLine();
				if (sat.equals("y")) {
					System.out.println("Do you want to play again? [y/n]");
					
					String want2play; 
					want2play = scan.nextLine();
					if (want2play.equals("y")){
						 playing = true;
						 current = root;
						 continue;
					} else {
						playing = false;
					}
				} else {
					//this is where new leaves are added
					System.out.println("What would you prefer instead?");
					String suggestion;
					suggestion = scan.nextLine();
					TreeNode NewPreference = new TreeNode(suggestion);
					current.left = NewPreference;
					//suggestions are always the left child, questions are always the right child
					System.out.println("Tell me a question that distinguishes " + current.value + " from " + suggestion);
					String question;
					question = scan.nextLine();
					if (current.getRight() == null ) {
						TreeNode newNode = new TreeNode(current.value);
						current.right = newNode;
					}
					replaceNode(current, question);

					System.out.println("Do you want to play again? [y/n]");
					String want2play;
					want2play = scan.nextLine();
					if (want2play.equals("y")) {
						 playing = true;
						 current = root;
						 continue;
					} else {
						playing = false;
					}
					
				}
				
			
			 }
		}
            // if they save the tree then write to the file suggestions.txt
		  Scanner scanner = new Scanner(System.in);
		  System.out.println("Do you want to save the current tree? [y/n]");
		  String save; 
		  save = scanner.nextLine();
		  if (save.equals("y")) { // write to file
			  BufferedWriter writer = new BufferedWriter(new FileWriter("suggestions.txt")); 
			  writer.write(root.print());
			  writer.close();	
			  } else { 
			  //if they dont want to save the tree exit the game
				  System.exit(0);
				  }
		  scanner.close();
		  }

}
