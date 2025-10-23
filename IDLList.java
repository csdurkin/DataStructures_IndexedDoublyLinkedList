//Connor Durkin
//CS-570-WA - Fall 2020
//Original Submission: October 16, 2020
//Resubmission: November 23, 2020
//Homework 3

//FIXED MISTAKES:

	//-5, remove(E) fails on a general case
		//Problem: An extra nested loop caused the for loop to advance improperly through indices
		//Solution: Extra loop removed.

	//-2, Remove() fails on an empty list with error: java.lang.IllegalArgumentException: The head is null and cannot be removed.
		//Problem: When an empty list was tested, the Exception stated that this was because of head with null value, not an empty list.
		//Solution: Test for empty list and throw Exception if necessary. 

	//-2, RemoveLast() fails on an empty list with java.lang.IllegalArgumentException: The tail is null and cannot be removed.	
		//Problem: When an empty list was tested, the Exception stated that this was because of tail with null value, not an empty list.

	//-1, No bound check for removeAt() with java.lang.IndexOutOfBoundsException: Index 100 out of bounds for length 2
		//The bound check improperly used < when comparing index to the size and improperly set the index of the final item as size
		//The comparison now uses <= when comparing size & index and sets the index of the final item as size - 1

	//-20, Late submission
		//I spoke with Professor Peyrovian, and he said that I can earn these points back by resubmitting the assignment 
		//Note, the resubmission deadlines was originally marked Sunday, Nov. 23, but Nov. 23 is Monday.
		//Professor Peyrovian let me know that Nov. 23, not the day of the week, would be the deadline. 

package homework3b;

//IMPORT JAVA UTIL

	import java.util.ArrayList;
	
//CLASS DECLARATION 
	
	public class IDLList<E> {
		
//PARAMETER DECLARATIONS	

	private Node<E> head;
	private Node<E> tail;
	private int size = 0;
	private ArrayList<Node<E>> indices;	

//INNER CLASS: Node<E>
	
	private class Node<E> {
		
		// PARAMETER DECLARATIONS	
			E data;
			Node<E> prev;
			Node<E> next;
			
		//CONSTRUCTOR, ONLY DATAITEM PROVIDED
	
		Node(E elem) {
			data = elem; 
			this.prev = null;
			this.next = null;
		}
	
		//CONSTRUCTOR, DATAITEM/PREV/NEXT PROVIDED
	
		Node(E elem, Node<E> prev, Node<E> next) {
		    data = elem;
		    this.prev = prev;
		    this.next = next;
		  }
		
	}

//CONSTRUCTOR - Creates an empty double linked list 

	public IDLList() {
		head = null; 
		tail = null; 
		size = 0;
		indices = new ArrayList<Node<E>>();
	}	

//ADD OPERATIONS
	
	//add(int index, E elem)
	//Adds "elem" at position index (counting from wherever head is).
	
	public boolean add(int index, E elem) {
		
		//Exception Handle: Check Bounds
			
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException(Integer.toString(index));
			}
		
		//Check if index is the head or tail 
			
			if (index == 0) {
				
				add(elem);
				
				return true;
				
			} else if (index == size) {
				
				append(elem);
				
				return true;
				
			} else {
			
				//Define nodes "CurrentNode" and "PreviousNode" based on "Index" 

					Node<E> CurrentNode = indices.get(index); 
					Node<E> PreviousNode = indices.get(index-1); 

				//Create node "InsertNode" using "elem," "CurrentNode," and "PreviousNode"

					Node<E> InsertNode = new Node<E>(elem, PreviousNode, CurrentNode); 

				//Adjust "prev" and "next" for "CurrentNode" and "PreviousNode" 

					CurrentNode.prev = InsertNode; 
					PreviousNode.next = InsertNode; 

					
				//Add "InsertNode" to "indices" 

				indices.add(index, InsertNode); 	
					
				//Increase size 

					size++; 

				//Return True

					return true; 
					
			}
			
	}
	
	//add(E elem)
	//Adds elem at the head, becomes the first element of the list
	
	public boolean add(E elem) {
		
		Node<E> InsertNode = new Node<E>(elem);
		
		if (elem == null) {
			
			throw new IllegalArgumentException("The provided element cannot be a null value.");
			
		} else if (head == null  || indices.isEmpty()) {
			
			head = InsertNode;
			tail = InsertNode;
			
		} else {
			
			InsertNode.next = head;
			InsertNode.prev = null;
			head.prev = InsertNode;
			head = InsertNode; 	
		}
		 
		indices.add(0, InsertNode);
		size ++;
		return true; 
		
	}
	
	//append(E elem)	
	//Adds elem as the new last element of the list (i.e. at the tail)
	
	public boolean append(E elem) {
		
		Node<E> InsertNode = new Node<E>(elem);
		
		if (elem == null) {
			
			throw new IllegalArgumentException("The provided element cannot be a null value");
			
		} else if (tail == null || indices.isEmpty()) {

			head = tail = InsertNode;
			
		} else {
			
			InsertNode.prev = tail; 
			InsertNode.next = null; 
			tail.next = InsertNode; 
			tail = InsertNode;
			
		}
		
		indices.add(size, InsertNode);
		size ++; 
		return true;
		
	}
			
//GET OPERATIONS			
		
	//get(int index)	
	//Returns the object at position index from the head;
	
	public E get(int index) {
		
		//Exception Handle: Check Bounds
		
		if (index < 0 || index > size) {
			
			throw new IndexOutOfBoundsException(Integer.toString(index));
			
		} else {
			
			return indices.get(index).data;
			
		}
		
	}
	
	//getHead()
	//Returns the object at the head
	
		public E getHead() {
			
			if (head == null) {
				
				throw new IllegalArgumentException ("The head is null and cannot be returned");
				
			} else {
				
				return head.data;
				
			}	
			
		}
		
	//getTail 
	//Returns the object at the tail 
		
		public E getLast() {
			
			if (tail == null) {
				
				throw new IllegalArgumentException ("The tail is null and cannot be returned.");
			
			} else {
				
				return tail.data; 
			
			}
			
		}
		
//SIZE OPERARTIONS 
		
		//size()
		//returns the list size 
		
		public int size() {
			
			return size;
			
		}

//REMOVE OPERATIONS 		
		
	//remove ()
	//Removes and returns the element at the head 
		
		public E remove() {
			
			
			if (indices.size()==0){ 
				throw new IllegalArgumentException("The list is empty, and therefore, no items can be removed.");
			} else if (head == null) {
				throw new IllegalArgumentException("The head is null and cannot be removed.");
			} else if (indices.size() == 1){
				
				head = null;
				tail = null;
				
			} else {
				
				head.next.prev = null;
				head = head.next;
				
			}
			
			size--;
			return indices.remove(0).data;
			
		}
		
	//removeLast()
	//Removes and returns the element at the tail. 
		
		
		public E removeLast() { 
			
		if (indices.size()==0){ 
				throw new IllegalArgumentException("The list is empty, and therefore, no items can be removed.");
		} else if (tail == null) {
			throw new IllegalArgumentException ("The tail is null and cannot be removed.");
		} else if (indices.size()==1) {
			head = null;
			tail = null;
		} else {
			tail.prev.next = null;
			tail = tail.prev;
		}
		
		size --;
		return indices.remove(size).data;
		
		}
	
	//RemoveAt(int index)
	//Removes and returns the element at the index
	
		public E removeAt(int index) {
			
			//Create temporary node 
			
			Node<E> temp = indices.get(index);
			
			//Exception handling 
			
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException(Integer.toString(index));
			} else if (index == 0) {
				remove();
			} else if (index == size - 1) {
				removeLast();
			} else {
				temp.prev.next = temp.next; 
				temp.next.prev = temp.prev;
				indices.remove(index);
				size--;
			}
			
			return temp.data;
			
		}
		
	//Remove(E elem)
	//Removes first occurrence of element and returns true; returns false if element not present.

		public boolean remove(E elem) {
			
			for (int i = 0; i < indices.size(); i++) {
				if (indices.get(i).data == elem) {
					removeAt(i);
					return true;
				}
			} return false;
		}
		
		
	//toString()	
		
		public String toString() {
			
			String temp = "";
			
			for (int i = 0; i < indices.size(); i++) {
				temp = temp + indices.get(i).data.toString();
				if (i != indices.size()-1) {
					temp = temp + ", ";
				}
			}
			
			return temp;
			
		}
		
}
