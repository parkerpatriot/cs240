package cs240.byu.edu.evilhangman_android.StudentPackage;

//import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by jparker on 5/7/16.
 */
public class EvilHangmanGame implements StudentEvilHangmanGameController {

    // current state of the game
    private GAME_STATUS gameStatus;
    private TreeSet<Character> guessedLetters;
    private HashSet<String> currentWordList;
    private TreeSet<Partition> sortedPartitions;
    private TreeMap<String, Partition> keySet;

    // keeps track of guesses
    private int numGuesses;
    private int wordLength;
    private String currentWord;

    public EvilHangmanGame() {
        currentWordList = new HashSet<>();
        sortedPartitions = new TreeSet<>();
        guessedLetters = new TreeSet<>();
        keySet = new TreeMap<>();
        gameStatus = GAME_STATUS.NORMAL;

    }

    public void startGame(InputStreamReader dictionary, int wordLength) {
        this.wordLength = wordLength;
        char[] chars = new char[wordLength];
        Arrays.fill(chars, '-');
        currentWord = new String(chars);


        BufferedReader bufferedReader = new BufferedReader(dictionary);
        String _word, word;
        try {
            while ((_word = bufferedReader.readLine()) != null) {
                word = _word.toLowerCase();
                // 
                if (word.length() == wordLength) {
                    currentWordList.add(word);
                }
            }
        } catch (IOException e) {
//            Log.d("Exception", "Dictionary load failed!");
        }

    }

    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        guess = Character.toLowerCase(guess);
        if(!Character.isAlphabetic(guess)) {
            return currentWordList;
        }

        if (guessedLetters.contains(guess)) {
            throw new GuessAlreadyMadeException();
        } else {
            guessedLetters.add(guess);
            keySet.clear();
            numGuesses--;

            for(String word: currentWordList) {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i=0; i<word.length(); i++) {
                    if(word.charAt(i) == guess){
                        stringBuilder.append(guess);
                    } else {
                        stringBuilder.append('-');
                    }
                }

                String keyWord = stringBuilder.toString();
                if(keySet.containsKey(keyWord)){
                    Partition key = keySet.get(keyWord);
                    key.wordBucket.add(word);
                } else {
                    Partition newKey = new Partition(keyWord);
                    newKey.wordBucket.add(word);
                    keySet.put(keyWord, newKey);
                }

            }
            Partition best = getBestPartition();
            updateCurrentWordWith(best.key);
            currentWordList = best.wordBucket;
            updateGameStatus();

            return currentWordList;
        }
    }

    private void updateGameStatus() {
        if(currentWordList.size() == 1){
            if (currentWordList.iterator().next().equals(currentWord)){
                gameStatus = GAME_STATUS.PLAYER_WON;
            }
        } else if (numGuesses == 0) {
            gameStatus = GAME_STATUS.PLAYER_LOST;
        }
    }

    private void updateCurrentWordWith(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<wordLength; i++){
            char newLetter = key.charAt(i);
            if(newLetter != '-'){
                stringBuilder.append(newLetter);
            } else {
                stringBuilder.append(currentWord.charAt(i));
            }
        }
        currentWord = stringBuilder.toString();
    }

    private Partition getBestPartition() {
        sortedPartitions.clear();
        for(Partition part: keySet.values()) {
            sortedPartitions.add(part);
        }
        return sortedPartitions.last();
    }

    public GAME_STATUS getGameStatus() {
        return gameStatus;
    }

    public int getNumberOfGuessesLeft() {
        return numGuesses;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public Set<Character> getUsedLetters() {
        return guessedLetters;
    }

    public void setNumberOfGuesses(int numberOfGuessesToStart) {
        numGuesses = numberOfGuessesToStart;
    }

    private class Partition implements Comparable<Partition> {
        public HashSet<String> wordBucket;
        public String key;
        public int charFreq;
        public int rightMeasure;

        public Partition(String key) {
            this.wordBucket = new HashSet<>();
            this.key = key;
            setRightMeasure();
            setCharFreq();
        }

        public int length() {
            return wordBucket.size();
        }
        private void setCharFreq() {
            for(int i=0; i<wordLength; i++) {
                if(key.charAt(i) != '-') {
                    charFreq++;
                }
            }
        }

        private void setRightMeasure() {
            for(int i = 0; i< wordLength; i++) {
                char letter = key.charAt(i);
                if(letter != '-'){
                    rightMeasure += 2^i;
                }
            }
        }

        @Override
        public int compareTo(Partition part) {
            int compare = Integer.compare(this.length(), part.length());
            if (compare == 0) {
                compare = Integer.compare(part.charFreq, charFreq); // less is more :) so invert
            }
            if (compare == 0) {
                compare = Integer.compare(rightMeasure, part.rightMeasure);
            }
            return compare;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Partition wordKey = (Partition) o;

            return key != null ? key.equals(wordKey.key) : wordKey.key == null;

        }
    }

}

