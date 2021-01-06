package me.acablade.kelimeoyunu.Objects.WordCheckers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class EnglishWordChecker implements IWordChecker {
    @Override
    public boolean isWord(String word) throws IOException {

        URL obj = new URL("https://www.wordsapi.com/mashape/words/"+word+"?when=2021-01-05T14:13:47.097Z&encrypted=8cfdb18be722929bea9407beeb58bfbaaeb42a0930f690b8");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == 304) { // success
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String[] getLanguage() {
        return new String[]{"ENGLISH","ENG","EN"};
    }
}
