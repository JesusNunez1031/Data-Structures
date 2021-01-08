import java.util.*;
/*
    Implementation of Trie data structure using nodes with array of children nodes.

    A search tree known as a trie, is a data structure built from the characters of the string entries added. These characters
    guide the search of entries in the trie. The nodes in the trie contain links that are either null or references to
    other nodes. Each node is pointed to by just one other node, which is called its parent (with the exception of the
    root node, which has no nodes pointing to it). Each node has R links, where R is the size of the alphabet being used
    in the trie. Each node also has a corresponding value, which may be null or the value associated with one of the string
    entry's. The value paired to the entry is stored in the node corresponding to its last character.
    Example of Trie:
                                              [] -> root
     link to all keys that start with a -> [/] | \
                                           a   c   t
 entry   |   value                         |   |   [\] -> link from one node to its child
  app    |     1                           p   o     h
  apple  |     2                           |   |     |
  core   |     3                       (1) p   r     e [4] -> the value assigned to entry "the"
  the    |     4                          /    |
                                         l (3) e
                                        /
                                   (2) e
    Applications:
        1. Autocomplete
            - google search at its core includes a massive trie to help users get suggested results to the entries they
              type into a searchbar

        2. Spell Checker
            - misspelled words are flagged and users are suggested other similar words which prefix's matches the flagged
              word.

        3. IP routing
            - longest prefix matching algorithm uses tries in internet protocol (IP) routing to select an entry from a
              forwarding table

        4. Predictive text
            - similar to autocomplete, when users type into a messaging application, suggestions are offered based off the
              prefix of the current typed word

    Method Time Complexities:
                                   Method         |   Time Complexities
                                 ————————————————————————————————————――
                                  size()          |     O(1)
                                  isEmpty()       |     O(1)
                                  get()           |     O(m)
                                  contains()      |     O(m)
                                  insert()        |     O(m)
                                  remove()        |     O(m)
                                  startsWith()    |     O(p)
                                  entries()       |     O(L * N * R)
                           entriesWithPrefix()    |     O(L * N * R)
                           entriesThatMatch()     |     O(L * N * R)
                            longestPrefixOf()     |     O(p)

      - Memory requirements for Trie are O(R * E * N) where R is the alphabet size, E is the average length of entries
        and N is the number of entries.
      - N = number of entries in trie
      - R = Alphabet size (alphabet greatly impacts space used and search, insertion, and removal)
      - m = length of the entry to be inserted, searched for, or removed
      - p = length of the prefix
      - L = number of links between nodes in the trie
 */

/**
 * The {@code Trie} class represents a symbol table of entry - value pairs, with string entries and generic values.
 * {@code Trie} structure supports typical <em>size</em>, <em>is-empty</em>, <em>get</em>, <em>contains</em>,
 * <em>remove</em>, and <em>insert</em> methods.
 * It also supports character-based methods for checking if there exits a string in the symbol table that
 * <em>start with</em>, a given prefix, finding <em>entries with prefix</em> given a prefix, finding all strings in the
 * symbol table that <em>match</em> a given pattern, and finding all <em>entries</em> in the symbol table. Adding a new
 * string entry that already exists in the table replaces the value of the old entry with that of the new entry. Values
 * associated with an entry cannot be {@code null} - setting the value associated with an entry to {@code null} is
 * equivalent to removing the entry.
 *
 * @param <E> Value type
 */
public class Trie<E> {  //since we want to allow values to be of any Object type, we set trie as <E> type
    private TrieNode root;  //root node in the trie
    private int n;          //number of entries in the trie

    /**
     * Initializes an empty trie
     */
    public Trie() {
        root = new TrieNode();
        n = 0;
    }

    /**
     * Returns the number of entries in the trie
     *
     * @return the number of entry - value pairs in the trie
     */
    public int size() {
        return n;
    }

    /**
     * Checks of the trie contains entries
     *
     * @return {@code true} if the trie contains no {@code entries}, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param entry entry
     * @return the value associated with the given {@code entry} if the {@code entry} exists in the trie and {@code null}
     * If the {@code entry} does not exist in the trie
     * @throws IllegalArgumentException if {@code entry} is {@code null}
     */
    public E get(String entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry requested is null");
        }
        TrieNode node = getEntry(entry);

        //entry does not exist in the trie
        if (node == null) {
            return null;
        }
        return (E) node.value;
    }

    //helper method for get(), returns the last node of the entry
    private TrieNode getEntry(String entry) {
        TrieNode iter = root; //node used to iterate through trie

        //search entries in the trie associated with given entry
        for (int i = 0; i < entry.length(); i++) {
            char c = entry.charAt(i);

            //if a character in the entry does not exist, return null since the entry is not in the trie
            if (iter.children[c] == null) {
                return null;
            }
            //move to the next node c
            iter = iter.children[c];
        }
        return iter;
    }

    /**
     * Checks if the trie contains the given entry
     *
     * @param entry entry
     * @return {@code true} if {@code entry} exists in the trie, {@code false} otherwise
     */
    public boolean contains(String entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry requested is null");
        }
        //if get returns an non-null value, the entry exists in the trie
        return get(entry) != null;
    }

    /**
     * Inserts a new entry with a specified value into the trie, the old value of the entry is overwritten if the entry
     * already exists in the trie. If the value passed is {@code null}, the entry is erased.
     *
     * @param entry new entry to be added
     * @param value value to be associated with entry
     * @throws IllegalArgumentException if {@code entry} is {@code null}
     */
    public void insert(String entry, E value) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry given is null");
        }
        if (value == null) {
            remove(entry);
        } else {
            TrieNode iter = root;   //node used to iterate through trie

            /*
                search through the trie, if we encounter a character that does not exist in the trie, we make a new node
                for it
             */
            for (int i = 0; i < entry.length(); i++) {
                char c = entry.charAt(i);

                if (iter.children[c] == null) {
                    iter.children[c] = new TrieNode();
                }

                //move to the next node or newly created node
                iter = iter.children[c];
            }

            /*
                once we've added all the characters in the entry to the trie, iter will be pointing to the last
                character node so we set its value to mark the end of the entry
             */
            iter.value = value;

            //increase the number of entries in the trie
            n++;
        }
    }

    /**
     * Removes {@code entry} from the trie if present
     *
     * @param entry entry to be removed
     * @throws IllegalArgumentException if {@code entry} is {@code null}
     * @throws InputMismatchException   if {@code entry} does not exist in the trie
     */
    public void remove(String entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry given is null");
        }
        //check if the entry to be deleted is actually in the trie
        if (!contains(entry)) {
            throw new InputMismatchException("Entry to be deleted does not exist");
        }

        TrieNode iter = getLastNode(entry);   //getLastNode returns the reference to the last node of the entry

        /*
            Starting from the end, we set the value of the entry to null to delete it and continue going backwards until
            a node with a value is encountered, when this occurs, that means the branch is from another entry so we stop
            deleting nodes
         */
        for (int i = entry.length() - 1; i >= 0; i--) {
            char c = entry.charAt(i);

            //remove the value of the last node of the entry
            if (i == entry.length() - 1) {
                iter.value = null;
            }

            //the next time we hit a value of an entry, we break out since this is another entry in the trie
            if (iter.value != null) {
                break;
            }

            //erase the node value
            iter.children[c] = null;
        }

        //reduce the number of entries in the trie
        n--;
    }

    /**
     * @param entry entry
     * @return reference to the last node of {@code entry}
     */
    private TrieNode getLastNode(String entry) {
        TrieNode iter = root;

        for (int i = 0; i < entry.length(); i++) {
            char c = entry.charAt(i);

            iter = iter.children[c];
        }
        return iter;
    }


    /**
     * @param prefix the prefix
     * @return True if there is any entry in the trie that starts with {@code prefix}
     */
    public boolean startsWith(String prefix) {
        TrieNode iter = root;   //node used to iterate through trie

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            //if a character in the prefix is not in the trie, return false
            if (iter.children[c] == null) {
                return false;
            }

            //move to the next child node
            iter = iter.children[c];
        }
        //if we traversed through the trie entire trie, that means there is an entry branch for the prefix so return true
        return true;
    }


    /**
     * @return All entries in the trie
     */
    public Iterable<String> entries() {
        //pass in the empty prefix therefore we start the prefix string from the first character int the alphabet R
        return entriesWithPrefix("");
    }

    /**
     * @param prefix the prefix
     * @return all entries in trie that start with {@code prefix}, as an iterable
     */
    public Iterable<String> entriesWithPrefix(String prefix) {
        List<String> entries = new LinkedList<>();
        TrieNode node = getEntry(prefix);
        collectEntries(node, new StringBuilder(prefix), entries);

        return entries;
    }

    /**
     * <p>
     * Method searches trie starting from the last node/character, in prefix. R characters are searched and
     * appended to the prefix string until a node with a value is encountered. All entries found with same prefix are
     * added to Iterable.
     * </p>
     *
     * @param node    start of search for other {@code entries} with same {@code prefix}
     * @param prefix  StringBuilder object to store string generated through search of trie
     * @param entries Iterable to store all {@code entries} with {@code prefix}
     */
    /*
        Using backtracking, we start from the last node of prefix, we append characters to the prefix starting from the
        first character in the alphabet R. When we encounter a node with a value, we add the current prefix string to the
        list of entries and then backtrack once an entry is found to the next node prefix + c++
     */
    private void collectEntries(TrieNode node, StringBuilder prefix, List<String> entries) {
        //node will be null if node.children[c], or the cth character is not in the trie
        if (node == null) {
            //if the node is not in the trie, backtrack, remove the current node c, and append c + 1 to the prefix string
            return;
        }
        //if the current node has a value that means its the end of an entry so we add it to the list of entries
        if (node.value != null) {
            entries.add(prefix.toString());
        }
        for (char c = 0; c < TrieNode.R; c++) {
            prefix.append(c);
            collectEntries(node.children[c], prefix, entries);  //call method on the next character c
            prefix.setLength(prefix.length() - 1);  //remove the last character in the prefix string
        }
    }

    /**
     * Returns all the entries in the trie that match {@code pattern} where a period . is treated as a wildcard character
     * meaning the character can be any character in the alphabet R.
     *
     * @param pattern the pattern
     * @return all the entries in the trie that match {@code pattern} as an iterable, where . is treated as a wildcard
     * character.
     */
    public Iterable<String> entriesThatMatch(String pattern) {
        List<String> entries = new LinkedList<>();
        collectEntries(root, new StringBuilder(), pattern, entries);
        return entries;
    }

    //Similar to the other collectEntries() method, we use backtracking to find all the entries that match the given pattern
    private void collectEntries(TrieNode node, StringBuilder str, String pattern, List<String> entries) {
        //if the character does not exist in the trie, return
        if (node == null) {
            return;
        }
        //if the current string generated is the game length as the pattern and its last node has a value, an entry has been found
        if (str.length() == pattern.length() && node.value != null) {
            entries.add(str.toString());
        }
        /*
            if the current string generated is the same length as the pattern but its last node has no value, the entry
            does not exist so we return
         */
        if (str.length() == pattern.length()) {
            return;
        }
        //next character in the given pattern
        char c = pattern.charAt(str.length());

        /*
            if the character in the patten is a '.', we can use any character in the alphabet R to generate a match, so
            we search normally through the entire alphabet
         */
        if (c == '.') {
            for (char ch = 0; ch < TrieNode.R; ch++) {
                str.append(ch);
                collectEntries(node.children[ch], str, pattern, entries);
                str.setLength(str.length() - 1);
            }
            //if character is not a '.', then we just append the character to the current string "str" and move to its node
        } else {
            str.append(c);
            collectEntries(node.children[c], str, pattern, entries);
            str.setLength(str.length() - 1);
        }
    }

    /**
     * Searches the trie for the string with the longest prefix of {@code query} and returns it, {@code null} if there
     * is no such prefix
     *
     * @param query the query string
     * @return the string in the trie that is the longest prefix of {@code query}, {@code null} if the string is not in
     * the trie.
     * @throws IllegalArgumentException If {@code query} is {@code null}
     */
    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        }
        //variable to hold the end of the query substring's prefix
        int j = -1;
        TrieNode iter = root;   //iterator used to traverse trie

        /*
            search through all the characters in the query, if at any point we encounter a character in the query that
            does not exist in the trie, we break out and return the substring "query" from 0 to j, if j = -1, there is
            no string in the trie with the prefix "query"
            Ex:     if trie entries = "hello", "heli", "helium" and query = "helicopter"
                    the returned string will be "heli"
         */
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);

            //if a character in query isn't in the trie, break out since its a null link
            if (iter == null) {
                break;
            }

            //if the current node has a value, set the end of query substring j to i since this marks an entry in the trie
            if (iter.value != null) {
                j = i;
            }
            //move to the next node c
            iter = iter.children[c];
        }

        if (j == -1) {
            return null;
        } else {
            return query.substring(0, j);
        }
    }
}
