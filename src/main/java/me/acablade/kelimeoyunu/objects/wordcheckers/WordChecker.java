package me.acablade.kelimeoyunu.objects.wordcheckers;

import java.io.IOException;

public abstract class WordChecker {

    public abstract boolean isWord(String word) throws IOException;

    public abstract String[] getLanguage();

}
