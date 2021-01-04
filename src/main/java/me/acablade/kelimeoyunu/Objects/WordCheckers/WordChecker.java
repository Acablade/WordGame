package me.acablade.kelimeoyunu.Objects.WordCheckers;

import java.io.IOException;

public abstract class WordChecker {

    public abstract boolean isWord(String word) throws IOException;

    public abstract String[] getLanguage();

}
