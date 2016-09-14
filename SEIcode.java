

import java.util.*;
class DeDup {
public int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};
public static void main(String[] args) {
  //Since the integer array is non static we need to create and object instance to use it in a statuc method.   
  DeDup deDup = new DeDup();  
  int[] result1 = removeDuplicatesWithLinkedHashSet(deDup.randomIntegers);
  System.out.println("RESULT 1:");
  logArrayContents(result1);
  int[] result2 = removeDuplicatesWithArrayStream(deDup.randomIntegers);
  System.out.println("RESULT 2:");
  logArrayContents(result2);
  int[] result3 = removeDuplicatesWithHashSet(deDup.randomIntegers);    
  System.out.println("RESULT 3:");
  logArrayContents(result3);
 }

 //This approach will allow us to preserve the input array order and the usage of iterator 
 //within it will allow us to delete certain elements within the loop without throwing any exception.
 //Positives: 
 //Preserve the input array order
 //Set by default takes care of removal of duplicate entries, so it is a better approach compared
 //to using ArrayList. In ArrayList we need to iterate through each value and seperate step needs to be 
 //considered for duplicate entry removal
 //Negative: Lines of Code is more compared to the second approach below which is available for Java 8.So if we have
 //Java compiler 8 version then we should go for option number 2
    
 private static int[] removeDuplicatesWithLinkedHashSet(int[] inputArray) {
  //This preserves ordering  
  Set < Integer > integerSet = new LinkedHashSet < Integer > ();
  for (int i = 0; i < inputArray.length; i++) {
   integerSet.add(inputArray[i]);
  }
  //If you need object array, use return integerSet.toArray();
  //For integer array, we can use integerSet.toArray(new Integer[0]);
  //below changes are for returning back int[] array  
  int[] result = new int[integerSet.size()];
  //Iterator allows you to safely remove elements preventing ConcurrentModificationException
  //it.remove();  
  Iterator < Integer > it = integerSet.iterator();
  int i = 0;
  while (it.hasNext()) {
   result[i++] = it.next();
  }
  return result;
 }
    
    
 //If we are using Java8, we can use the below approach to reduce the LinesOfCode and 
 //to create a simple approach for filtering.
 //Positives:
 //Fewer Lines of code compared to other approaches.
 //Negatives: Backward compatibility is an issue as this functionlaity is available only in Java 8.
 //So if we have Java compiler versions less than 8 then we should use either option 1 or 3
 private static int[] removeDuplicatesWithArrayStream(int[] inputArray) {
  //This is only compatible with Java8  
  return Arrays.stream(inputArray).distinct().toArray();
 }
    
 //This is an extended approach of using Set. Here filtering of duplicates and creation of new input array
 //is completed in one single for loop.
 //Positives:
 //Hash Set removes duplicates enrty
 //Filtering of duplicates and and populating new input array is taken care in 1 single for loop.
 //Negatives:
 //However, with this since we dont use an iterator to loop, this approach doesnt suite if we need to delete som elements in between
 //the processing.
 //You have an extra step to copy the array elements to exclude the
 //positions which were taken by duplicate elements 
 //We should use option 1 if delete operations happen to the array during our processing.
 private static int[] removeDuplicatesWithHashSet(int[] inputArray) {
  final Set < Integer > set = new HashSet < > ();
  final int[] tmp = new int[inputArray.length];
  int index = 0;
  for (final int i: inputArray)
   //set.add returns boolean which we can use to see whether the element is already added or not. 
   if (set.add(i))
    tmp[index++] = i;
  //finally, copy the array so that the removed duplicate elements are not taken into account.
  return Arrays.copyOfRange(tmp, 0, index);
 }
   
//This method takes care of logging the values present in the final filtered output integer array    
   private static void logArrayContents(int[] filteredIntArray){
       for(int cnt=0; cnt<filteredIntArray.length;cnt++){
           System.out.println(filteredIntArray[cnt]);
       }
       
   } 
}
