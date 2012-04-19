/* ListSorts.java */

package list;

public class ListSorts {

  private final static int SORTSIZE = 100000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    LinkedQueue result = new LinkedQueue();
    try{
	Object item = q.dequeue();
	while(true){
		LinkedQueue element = new LinkedQueue();
		element.enqueue(item);
		result.enqueue(element);
		item = q.dequeue();
	}
    }
    catch(QueueEmptyException e){
	// Method depends on this catch statement to make sure all elements are
	// included in the new queue, since using loops that use !q.isValid()
	// don't include the last element 
    }
    return result;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    LinkedQueue result = new LinkedQueue();
    try{    
	// Inserts items into the new queue, checking which one to sort to ensure they
	// are in order
	while(!q1.isEmpty() && !q2.isEmpty()){
		if(((Comparable)q1.front()).compareTo((Comparable)q2.front()) <= 0){
			result.enqueue(q1.dequeue());
		}
		else{
			result.enqueue(q2.dequeue());
 		}
    	}

	// If one of the queues runs out, empty all of the remaining items into the
	// resulting list
	while(!q1.isEmpty()){
		result.enqueue(q1.dequeue());
	}
	while(!q2.isEmpty()){
		result.enqueue(q2.dequeue());
	}
    }
    catch(QueueEmptyException e){
	System.err.println(e);
    }
    return result;
}


  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    try{
	Comparable item = (Comparable) qIn.dequeue();
	while(true){
		if(item.compareTo(pivot) == 0){
			qEquals.enqueue(item);
		}
		else if(item.compareTo(pivot) < 0){
			qSmall.enqueue(item);
		}
		else{
			qLarge.enqueue(item);
		}
		item = (Comparable) qIn.dequeue();
	}
    }
    catch(QueueEmptyException e){
	// Method depends on this catch statement to make sure all elements are
	// included in the new queue, since using loops that use !q.isValid()
	// don't include the last element 
    }


  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
	try{
		LinkedQueue subqueues = makeQueueOfQueues(q);
		while(subqueues.size() != 1){
			subqueues.enqueue( mergeSortedQueues((LinkedQueue) subqueues.dequeue(), (LinkedQueue) subqueues.dequeue()));

		}
		q.append((LinkedQueue) subqueues.dequeue());
	}
	catch(QueueEmptyException e){
		System.err.println(e);
	}
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    if(q.isEmpty()){
	return;
    }
    Comparable pivot = (Comparable) q.nth((int) (q.size() * Math.random()));
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    partition(q, pivot, qSmall, qEquals, qLarge);
    quickSort(qSmall);
    quickSort(qLarge);
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);

 }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }
/*
  public static LinkedQueue makeRandomTest(int size, boolean random) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      if(random){
      	q.enqueue(new IntTest((int) (size * Math.random()), (char) (94 * Math.random() + 33)));
      }
      else{
      	q.enqueue(new IntTest(1, (char) (94 * Math.random() + 33)));

      }
    }
    return q;
  }
*/

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/

/*
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    // Custom tests
    

    System.out.println("CUSTOM MERGE TESTS");

    q = makeRandomTest(10,true);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString()+"\n");

    q = makeRandomTest(10, true);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString()+"\n");

    q = makeRandomTest(10, false);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString()+"\n");

    q = makeRandomTest(10, false);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString()+"\n");

    System.out.println("CUSTOM QUICK TESTS");



    q = makeRandomTest(10, true);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString() + "\n");

    q = makeRandomTest(10, false);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString() + "\n");


    q = makeRandomTest(10, false);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString() + "\n");

    q = makeRandomTest(10, false);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString() + "\n");



    q = new LinkedQueue();
    q.enqueue(3);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    q = new LinkedQueue();
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());
    


    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
  }
*/
}
