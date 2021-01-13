package me.acablade.kelimeoyunu.objects.wordcheckers;

import java.io.IOException;

public interface IWordChecker {

    boolean isWord(String word) throws IOException;

    String[] getLanguage();

}
