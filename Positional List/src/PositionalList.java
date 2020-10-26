/**
 * Positional lists provide a data structure that is not dependant on indexes to get values added.
 * They allow us to efficiently describe actions such as a person deciding to leave the line before
 * reaching the front, or allowing a friend to "cut" into line right behind them
 * Positional list is implemented using a DoublyLinked List for fast insertion and deletions
 * <p>
 * PL can be viewed as a collection of Positions, each of which stores an element.
 *
 * @param <E>
 */
public interface PositionalList<E> {

    //Returns the number of elements in the list
    int size();

    //Checks whether the list if empty
    boolean isEmpty();

    //Returns the first Position in the list (or null, if empty)
    Position<E> first();

    //Returns the last Position in the list (or null, if empty)
    Position<E> last();

    //Returns the Position immediately before Position p (or null, if p is first)
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    //Returns the Position immediately after Position p (or null, if empty)
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    //Inserts an element e at the front of the list and returns its new Position
    Position<E> addFirst(E e);

    //Inserts an element e at the back of the list and returns its new Position
    Position<E> addLast(E e);

    //Inserts element e immediately before Position p and returns its new Position
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    //Inserts element e immediately after Position p and returns its new Position
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    //Replaces the element stored at Position p and returns the replaced element
    E set(Position<E> p, E e) throws IllegalArgumentException;

    //Removes the element stored at Position p and returns it (invalidating p)
    E remove(Position<E> p) throws IllegalArgumentException;

    //Returns an iterable representation of the lists positions
    Iterable<Position<E>> positions();
}
