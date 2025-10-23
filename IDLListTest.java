//Connor Durkin
//CS-570-WA - Fall 2020
//Original Submission: October 16, 2020
//Resubmission: November 23, 2020
//Homework 3


//Some tests are commented out, as they purposely trigger exception handling.


package homework3b;

public class IDLListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IDLList<Integer> testrun  = new IDLList<Integer>();
		
		try {
		
		//Test: ResubmissionA
		//Remove() from empty list (Commented out, as corrected exception is caught).
			
			//System.out.println(testrun.remove());
			
		//Test: ResubmissionB
		//RemoveLast() from empty list (Commented out, as corrected exception is caught).
				
			//System.out.println(testrun.removeLast());
			
			
		//Test: 1
		//Add head to the the list(x2); Print Results 
			
			System.out.println("Test 1");
			System.out.println("Run successful? " + testrun.add(1));
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Run successful? " + testrun.add(2));
			System.out.println("String of list: " + testrun.toString());
			System.out.println();
		
		//Test: 2	
		//Append list and print result 
			
			System.out.println("Test 2");
			System.out.println("Run successful? " + testrun.append(3));
			System.out.println("String of list: " + testrun.toString());
			System.out.println();

		//Test: 3	
		//Add element to index 1 and print result 
			
			System.out.println("Test 3");
			System.out.println("Run successful? " + testrun.add(1, 4));
			System.out.println("String of list: " + testrun.toString());
			System.out.println();
			
		//Test: ResubmissionC
		//Check size 
		//Remove index 2, print the return data
		//Then print resultant list
		//Then check size 
		
			System.out.println("Test ResubmissionC");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Element removed from index 2: " + testrun.removeAt(2));
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Size of list: " + testrun.size());
			System.out.println();	
			
		//Test: ResubmissionD
		//Check size 
		//Try removing element below lower bound
		//Then print resultant list
		//Then check size 
		//(Commented out, as it tests that exception handling)
			
			/*System.out.println("Test ResubmissionD");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Element removed from index -1: " + testrun.removeAt(-1));
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Size of list: " + testrun.size());
			System.out.println();*/
			
		//Test: ResubmissionE
		//Check size 
		//Try removing element above upper bound
		//Then print resultant list
		//Then check size 
		//(Commented out, as it tests that exception handling)
			
			/*System.out.println("Test ResubmissionE");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Element removed from index 10: " + testrun.removeAt(10));
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Size of list: " + testrun.size());
			System.out.println();*/

		//Test: 4
		//Add element to index 0 and print result 
			
			System.out.println("Test 4");
			System.out.println("Run successful? " + testrun.add(0, 5));
			System.out.println("String of list: " + testrun.toString());
			System.out.println();
		
		//Test: 5
		//Get and print the size
		//Then add element at the an index equal to the size
			
			System.out.println("Test 5");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Run successful? " + testrun.add(4, 6));
			System.out.println("String of list: " + testrun.toString());
			System.out.println();
			
		//Test: 6
		//Get and print element at index 2, head and tail
			System.out.println("Test 6");
			System.out.println("Element at Index 2: " + testrun.get(2));
			System.out.println("Element at head: " + testrun.getHead());
			System.out.println("Element at tail: " + testrun.getLast());
			System.out.println();
			
		//Test: 7
		//Check size 
		//Remove the head, print the return data
		//Then print resultant list
		//Then check size 
		
			System.out.println("Test 7");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Element removed from head: " + testrun.remove());
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Size of list: " + testrun.size());
			System.out.println();
				
			
		//Test: 8
		//Check size 
		//Remove the tail, print the return data
		//Then print resultant list
		//Then check size 
		
			System.out.println("Test 8");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Element removed from tail: " + testrun.removeLast());
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Size of list: " + testrun.size());
			System.out.println();
			
		//Test: 9
		//Check size 
		//Remove index 2, print the return data
		//Then print resultant list
		//Then check size 
		
			System.out.println("Test 9");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Element removed from index 2: " + testrun.removeAt(2));
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Size of list: " + testrun.size());
			System.out.println();
		
		//Test: 10
		//Check size 
		//Remove element 2, print the return data
		//Then print resultant list
		//Then check size 
		
			System.out.println("Test 10");
			System.out.println("Size of list: " + testrun.size());
			System.out.println("Run successful? " + testrun.remove(2));
			System.out.println("String of list: " + testrun.toString());
			System.out.println("Size of list: " + testrun.size());
			System.out.println();	
			
		//Test: ResubmissionF
		//Check size 
		//Try to remove element not in list
		//Then print resultant list
		//Then check size 
			
				System.out.println("Test ResubmissionF");
				System.out.println("Size of list: " + testrun.size());
				System.out.println("Run successful? " + testrun.remove(6));
				System.out.println("String of list: " + testrun.toString());
				System.out.println("Size of list: " + testrun.size());
				System.out.println();	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}