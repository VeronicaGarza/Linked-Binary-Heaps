/* file: MaxPQ.java
   author: Bob Muller

   CSCI 1102 Computer Science 2

   A specification of a max priority queue.
*/
public interface MaxPQ<T extends Comparable<T>> {
  T dequeue();
  void enqueue(T key);
  boolean isEmpty();
  int size();
  String toString();
}
