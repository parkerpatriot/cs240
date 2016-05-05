package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by jparker on 5/2/16.
 */
public class MySpellCorrector implements ISpellCorrector {

    private Dictionary dictionary;
    private HashSet<String> wordsNotFoundInProgress;
    private HashSet<String> wordsNotFound;
    private TreeSet<ITrie.INode> wordsSuggested;

    private boolean save = true;

    public MySpellCorrector(){
        dictionary = new Dictionary();
        wordsSuggested = new TreeSet<ITrie.INode>();
        wordsNotFound = new HashSet<String>();
        wordsNotFoundInProgress = new HashSet<String>();
    }

    public void useDictionary(InputStreamReader dictionaryFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(dictionaryFile);
        String _word, word = null;
        while((_word = bufferedReader.readLine()) != null){
                word = _word.toLowerCase();
                dictionary.add(word);
            }
        }

    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        String suggest = find(inputWord.toLowerCase());
        if (suggest != null) {
            return suggest;
        } else {
            throw new NoSimilarWordFoundException();
        }
    }

    private String find(String inputWord) {
        wordsNotFoundInProgress.clear();
        wordsNotFound.clear();
        wordsSuggested.clear();
        ITrie.INode wordFound = dictionary.find(inputWord);
        if(wordFound != null) {
            return wordFound.toString();
        }
        // search for close matches
        else{
            wordsNotFound.add(inputWord);
            for(int i=0; i<2; i++) {
                save = (i<1);
                findEditDistance();
                if (wordsSuggested.size() > 0) {
                    return wordsSuggested.last().toString();
                }
            }
            return null;
        }
    }


    private void findEditDistance(){
        for(String inputWord: wordsNotFound) {
            findSimilarAlternation(inputWord);
            findSimilarDeletion(inputWord);
            findSimilarInsertion(inputWord);
            findSimilarTransposition(inputWord);
        }
        swapNotFoundLists();
    }

    private void swapNotFoundLists() {
        HashSet<String> tempSet = wordsNotFound;
        wordsNotFound = wordsNotFoundInProgress;
        wordsNotFoundInProgress = tempSet;
        wordsNotFoundInProgress.clear();
    }

    private void findSimilarDeletion(String word) {
        StringBuilder dword;
        ITrie.INode tempNode;
        for(int i=0; i<word.length(); i++) {
            dword = new StringBuilder(word);
            dword.deleteCharAt(i);
            tempNode = dictionary.find(dword.toString());
            addToResults(tempNode, dword.toString());
        }
    }

    /**
     * This function looks for similar words by transposing (switching) two adjacent letters.
     * For example, the word "snap" yields: "nsap", "sanp", "snpa" (n-1) options
     * @param word The misspelled word (length n)
     */
    private void findSimilarTransposition(String word) {
        StringBuilder tword;
        ITrie.INode tempNode;
        for(int i=0; i<word.length()-1; i++) {
            tword = new StringBuilder(word);
            char tempChar = tword.charAt(i);
            tword.deleteCharAt(i);
            tword.insert(i+1, tempChar);
            tempNode = dictionary.find(tword.toString());
            addToResults(tempNode, tword.toString());
        }
    }

    private void findSimilarAlternation(String word) {
        StringBuilder aword;
        ITrie.INode tempNode;
        for(int i=0; i<word.length(); i++) {
            aword = new StringBuilder(word);
            for(char c='a'; c<='z'; c++) {
                aword.setCharAt(i,c);
                tempNode = dictionary.find(aword.toString());
                addToResults(tempNode, aword.toString());
            }
        }
    }

    private String findSimilarInsertion(String word) {
        StringBuilder iword;
        StringBuilder notFound = new StringBuilder();
        ITrie.INode tempNode;
        for(int i=0; i<=word.length(); i++) {
            for(char c='a'; c<='z'; c++) {
                iword = new StringBuilder(word);
                iword.insert(i,c);
                tempNode = dictionary.find(iword.toString());
                if(tempNode != null) {
                    wordsSuggested.add(tempNode);
                } else {
                    if (save) {notFound.append(iword.toString() + "\n");}
                }            }
        }
        return notFound.toString();
    }

    private void addToResults(ITrie.INode node, String word) {
        if(node != null) {
            wordsSuggested.add(node);
        } else {
            if (save) {wordsNotFoundInProgress.add(word);}
        }
    }


}
