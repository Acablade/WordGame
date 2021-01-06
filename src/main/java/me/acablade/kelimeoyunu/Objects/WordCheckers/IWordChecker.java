package me.acablade.kelimeoyunu.Objects.WordCheckers;

import java.io.IOException;

public interface IWordChecker {

    boolean isWord(String word) throws IOException;

    String[] getLanguage();

}
