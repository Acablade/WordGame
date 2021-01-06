package me.acablade.kelimeoyunu.Objects.WordCheckers;

import java.util.ArrayList;
import java.util.List;

public class WordCheckerManager {

    List<IWordChecker> IWordCheckerList;


    public WordCheckerManager(){
        //Init variables
        this.IWordCheckerList = new ArrayList<>();
    }

    /**
     * Register word checks
     * @param IWordChecker Word Checker that is going to be registered
     * @return Word checker so the dev can apply it to a variable
     */
    public IWordChecker register(IWordChecker IWordChecker){
        if(getWordCheckerList().contains(IWordChecker)) return null;
        getWordCheckerList().add(IWordChecker);
        return IWordChecker;
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
     * @param IWordChecker word checker that wanted to be removed
     */
    public void unregister(IWordChecker IWordChecker){
        if(!getWordCheckerList().contains(IWordChecker)) return;
        getWordCheckerList().remove(IWordChecker);
    }

    /**
     * Returns all registered word checkers
     * @return word checker list
     */
    public List<IWordChecker> getWordCheckerList() {
        return IWordCheckerList;
    }
}
