package proyect.travelassistant.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    public static String getJsonResponse(String urlServicio, String strReq) throws Exception {
        InputStream is = obtenerInputStream(urlServicio, strReq);
        if (is != null) {
            return convertirInputStreamAString(is);
        }
        return null;
    }

    public static String getJsonResponse(String urlServicio) throws Exception {
        InputStream is = obtenerInputStreamNoParams(urlServicio);
        if (is != null) {
            return convertirInputStreamAString(is);
        }else{
            return null;
        }
    }

    public static InputStream obtenerInputStream(String urlServicio, String strReq) throws Exception {
        URL url = new URL(urlServicio);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.addRequestProperty("Accept", "application/json");
        httpURLConnection.addRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setUseCaches(false);
        //httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
        wr.write(strReq);
        wr.flush();
        httpURLConnection.connect();
        return httpURLConnection.getInputStream();
    }

    public static InputStream obtenerInputStreamNoParams(String urlServicio) throws Exception {
        URL url = new URL(urlServicio);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //httpURLConnection.setRequestProperty("Accept", "application/json");
        //httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        //httpURLConnection.setRequestProperty("Accept-Charset", "ISO-8859-1");
        //httpURLConnection.setConnectTimeout(60000);
        //httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setUseCaches(false);
        //httpURLConnection.setDoOutput(true);
        //httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        return httpURLConnection.getInputStream();
    }

    public static String convertirInputStreamAString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String result ="";
        try{
            while((line = br.readLine())!= null){
                result += line;
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            is.close();
        }

        return result;

    }

}
