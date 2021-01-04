package me.acablade.kelimeoyunu.Objects.WordCheckers;

public class EnglishWordChecker extends WordChecker{
    @Override
    public boolean isWord(String word) {
        return false;
    }

    @Override
    public String[] getLanguage() {
        return new String[]{"ENGLISH","ENG","EN"};
    }
}
