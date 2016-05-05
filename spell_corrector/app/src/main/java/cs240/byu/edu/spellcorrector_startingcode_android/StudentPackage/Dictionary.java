package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

//import android.util.Log;

import java.util.Arrays;
import java.util.TreeSet;


/**
 * Created by jparker on 5/2/16.
 */
public class Dictionary implements ITrie{
    private int numWords;
    private int numNodes;
    private Node root;
    private TreeSet<Node> dictSet;

    public Dictionary(){
        numWords = 0;
        numNodes = 1;
        root = new Node();
        dictSet = new TreeSet<Node>();
    }

    private boolean wordIsGood(String word) {
        return word.matches("[a-z]+");
    }

    public void add(String word) {
        if (wordIsGood(word)) {
            root.insert(word, 0);
        }
        else {
//            Log.d("Debug", String.format("Bad word: %s", word));
        }
    }

    public ITrie.INode find(String word) {
        if (wordIsGood(word)) {
            ITrie.INode temp = root.find(word, 0);
            return temp;

        } else {
            throw new IllegalArgumentException(String.format("Illegal word: %s", word));
        }
    }

    public int getWordCount() {
        return numWords;
    }

    public int getNodeCount() {
        return numNodes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Node node: dictSet) {
            stringBuilder.append(node.word + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionary that = (Dictionary) o;

        if (numWords != that.numWords) return false;
        if (numNodes != that.numNodes) return false;
        return root != null ? root.equals(that.root) : that.root == null;

    }

    @Override
    public int hashCode() {
        int result = numWords;
        result = 31 * result + numNodes;
        result = 31 * result + (root != null ? root.hashCode() : 0);
        result = 31 * result + (dictSet != null ? dictSet.hashCode() : 0);
        return result;
    }


    ////// NODE CLASS ///////


    private class Node implements ITrie.INode, Comparable<Node> {

        private Node[] letters;
        public int freq;
        public String word;

        public Node() {
            letters = new Node[26];
            freq = 0;
        }

        public int getValue() {
            return freq;
        }

        public boolean insert(String word, int char_index) {
            // First, we check to see if we've reached the end of the word.
            // If so we increment the frequency of the word and return.
            if (char_index == word.length()) {
                freq++;
                numWords++;
                this.word = word;
                dictSet.add(this);
                return true;
            }
            // If not...
            else {
                // Get the index of the letter we're looking for
                int letter_index = word.charAt(char_index) - 'a';
                // First check to see if we've been here before
                if (this.letters[letter_index] == null) {
                    // If not we create a new node and call insert on it with the next letter (inc)
                    Node newNode = new Node();
                    numNodes++;
                    this.letters[letter_index] = newNode;
                    return newNode.insert(word, ++char_index);
                    // If it exists we need to insert on that node with the next letter (inc)
                } else {

                    return letters[letter_index].insert(word, ++char_index);

                }
            }
        }

        public ITrie.INode find(String word, int char_index) {
            // First, we check to see if we've reached the end of the word.
            // If so it means we have a valid word and we return a ref to this node.
            if (char_index == word.length()) {
                if(freq > 0) {
                    return this;
                } else {
                    return null;
                }
            }
            // If not...
            else {
                // Get the index of the letter we're looking for
                int letter_index = word.charAt(char_index) - 'a';
                Node nextNode = this.letters[letter_index];
                // First check to see if a word with the next letter exists
                if (nextNode == null) {
                    // If the next letter (node) doesn't exist it means this is an invalid word
                    return null;
                } else {
                    // If it exists we need to insert on that node with the next letter (inc)
                    return nextNode.find(word, ++char_index);
                }
            }
        }

        public int compareTo(Node node) {
            if (this.freq == node.freq) {
                return node.word.compareTo(this.word);
            } else if (this.freq > node.freq) {
                return 1;
            }
            else {
                return -1;
            }
        }

//        public boolean equals(Node node) {
//            return (this.word.equals(node.word)) && (this.freq == node.freq);
//        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (freq != node.freq) return false;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            if (!Arrays.equals(letters, node.letters)) return false;
            return word != null ? word.equals(node.word) : node.word == null;

        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(letters);
            result = 31 * result + freq;
            result = 31 * result + (word != null ? word.hashCode() : 0);
            return result;
        }

        public String toString() {
            return word;
        }

    }

}
