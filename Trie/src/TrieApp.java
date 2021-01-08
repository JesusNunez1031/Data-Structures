import java.util.Scanner;

public class TrieApp {
    public static void main(String[] args) {
        //Initialize new Trie
        Trie<Object> trie = new Trie<>();
        Scanner input = new Scanner(System.in);

        System.out.println("1: insert() | 2: remove() | 3: get() | 4: contains() | 5: entries() | 6: entriesWithPrefix() | 7: entriesThatMatch() | 8: longestPrefixOf() | 9: size()");
        int choice = input.nextInt();

        while (choice != -1) {
            switch (choice) {
                //add a new entry to the trie
                case 1:
                    System.out.println("Enter the new entry: ");
                    String entry = input.next();
                    System.out.println("Enter value for " + entry);
                    /*
                        Scanner does not allow for generic object types since it has to be defined so change input type
                        to match desired value type, default is string
                     */
                    input.nextLine();
                    Object value = input.nextLine();
                    trie.insert(entry, value);
                    System.out.printf("Entry %s with value %s has been inserted\n", entry, value);
                    break;

                //remove an entry from the trie if it exists
                case 2:
                    if (trie.isEmpty()) {
                        System.out.println("There are no entries present in the Trie");
                    } else {
                        System.out.println("Enter the entry to be removed: ");
                        entry = input.next();
                        if (!trie.contains(entry)) {
                            System.out.printf("The entry %s does not exist in the Trie\n", entry);
                        } else {
                            trie.remove(entry);
                        }
                        System.out.printf("Entry %s has been removed\n", entry);
                    }
                    break;

                //get the value associated with an entry
                case 3:
                    System.out.println("Enter the entry: ");
                    entry = input.next();
                    System.out.printf("Value of %s is: %s\n", entry, trie.get(entry));
                    break;

                //check if the trie contains an entry
                case 4:
                    System.out.println("Enter the entry: ");
                    entry = input.next();
                    boolean contains = trie.contains(entry);
                    if (contains) {
                        System.out.printf("Entry %s exists in the Trie\n", entry);
                    } else {
                        System.out.printf("Entry %s does not exists in the Trie\n", entry);
                    }
                    break;

                //print all entries in the trie
                case 5:
                    System.out.println("All entries present in Trie: " + trie.entries().toString());
                    break;

                //print all the entries with given prefix
                case 6:
                    System.out.println("Enter a prefix to search for: ");
                    String prefix = input.next();
                    System.out.printf("Entries with prefix %s: %s\n", prefix, trie.entriesWithPrefix(prefix).toString());
                    break;

                //print all the entries that match a given pattern
                case 7:
                    System.out.println("Enter pattern to search for (Note: A . can be used as wildcard character): ");
                    String pattern = input.next();
                    System.out.printf("Entries that match %s: %s\n", pattern, trie.entriesThatMatch(pattern).toString());
                    break;

                //prints the entry in the trie with the longest prefix of a given query
                case 8:
                    System.out.println("Enter query: ");
                    String query = input.next();
                    System.out.printf("Longest prefix of %s is %s\n", query, trie.longestPrefixOf(query));
                    break;

                //check size of trie
                case 9:
                    System.out.println("Number of entries: " + trie.size());
                    break;
                default:
                    System.out.println("Error, invalid choice, please enter new selection");
                    break;
            }
            System.out.println("Enter a new selection: ");
            choice = input.nextInt();
        }

    }
}
