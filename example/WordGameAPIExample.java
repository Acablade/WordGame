import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.WordCheckers.IWordChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class WordGameAPIExample extends JavaPlugin {

    IWordChecker wordChecker;

    @Override
    public void onEnable() {
        //You add it to manager like this (returns IWordChecker)
        wordChecker = KelimeOyunu.wordCheckerManager.register(new ExampleWordChecker());
    }


    //Create the word checker class here (you can create independent class aswell, for showing purposes i used inner class)
    private static class ExampleWordChecker implements IWordChecker {
        List<String> wordList;
        public ExampleWordChecker(){
            this.wordList = new ArrayList<>();
            wordList.add("xd");
            wordList.add("lol");
            wordList.add("bruh");
        }
        @Override
        public boolean isWord(String s) {
            return wordList.contains(s);
        }

        @Override
        public String[] getLanguage() {
            return new String[]{"Example"};
        }
    }
}
