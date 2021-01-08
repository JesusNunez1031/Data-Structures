/**
 * The {@code TrieNode} class represents a node for each string symbol in the table. Each {@code TrieNode} node created
 * holds an array of other {@code TrieNodes}, known as children, in the range of the alphabet R; This implementation uses a
 * 256-way trie. Each node can also hold a value.
 */
public class TrieNode {
    public static final int R = 256;   //the values allowed in the trie are any valid ascii values

    //After the insertion of a new trie entry, we assign a value to the last node in the entry so we know the end of the entry
    public Object value;

    /*
        Each node in the trie has R number of children which are an array of nodes so for every node in the trie, the
        possible children nodes it may have are 256 ascii characters
     */
    public TrieNode[] children = new TrieNode[R];
}
