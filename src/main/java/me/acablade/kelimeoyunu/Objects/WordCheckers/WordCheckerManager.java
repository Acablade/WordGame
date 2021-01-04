package me.acablade.kelimeoyunu.Objects.WordCheckers;

import java.util.ArrayList;
import java.util.List;

public class WordCheckerManager {

    List<WordChecker> wordCheckerList;


    public WordCheckerManager(){
        //Init variables
        this.wordCheckerList = new ArrayList<>();
    }

    /**
     * Register word checks
     * @param wordChecker Word Checker that is going to be registered
     * @return Word checker so the dev can apply it to a variable
     */
    public WordChecker register(WordChecker wordChecker){
        if(getWordCheckerList().contains(wordChecker)) return null;
        getWordCheckerList().add(wordChecker);
        return wordChecker;
    }

    /**
     * Unregisters the word checker at specified index
     * @param index index to be unregistered
     */
    public void unregister(int index){
        if(getWordCheckerList().size() <= index) return;
        getWordCheckerList().remove(index);
    }

    /**
     * Unregisters the specified word checker
     * @param wordChecker word checker that wanted to be removed
     */
    public void unregister(WordChecker wordChecker){
        if(!getWordCheckerList().contains(wordChecker)) return;
        getWordCheckerList().remove(wordChecker);
    }

    /**
     * Returns all registered word checkers
     * @return word checker list
     */
    public List<WordChecker> getWordCheckerList() {
        return wordCheckerList;
    }
}
