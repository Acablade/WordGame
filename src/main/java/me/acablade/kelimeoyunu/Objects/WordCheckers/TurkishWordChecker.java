package me.acablade.kelimeoyunu.Objects.WordCheckers;

import me.acablade.kelimeoyunu.KelimeOyunu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class TurkishWordChecker extends WordChecker{
    @Override
    public boolean isWord(String word) throws IOException {
        //DIDNT YOINK THIS FROM ANYWHERE PROUD OF MYSELF
        URL obj = new URL("https://sozluk.gov.tr/gts?ara="+word);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            if(response.toString().contains("error")){
                return false;
            }
            return true;
        } else {
            //Cant connect, finish the game
            KelimeOyunu.getGame().finish();
            return false;
        }
    }

    @Override
    public String[] getLanguage() {
        return new String[]{"TURKISH", "TR", "TURKEY"};
    }
}
