public interface Position<E> {
    /**
     * The concept of a position formalizes the intuitive notion of the "location" of an element relative to others in the list
     * We can use node references as natural manifestations of positions
     * Returns the element stored at this position
     *
     * @return the stored element
     * @throws IllegalStateException if position is no longer valid
     */
    E getElement() throws IllegalStateException;
}
