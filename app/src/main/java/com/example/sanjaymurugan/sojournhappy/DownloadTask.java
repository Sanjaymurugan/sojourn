package com.example.sanjaymurugan.sojournhappy;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by RAJKUMAR on 15-03-2018.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ParserTask parserTask=new ParserTask();
        parserTask.execute(s);
    }

    @Override
    protected String doInBackground(String... url) {
        String data="";
        try{
            data=downloadurl(url[0]);
        }
        catch (Exception e){
            System.err.println("--->"+e);
        }
        return data;
    }
    private String downloadurl(String strurl) throws IOException {
        String data="";
        InputStream istream=null;
        HttpURLConnection urlConnection=null;
        try{
            URL url=new URL(strurl);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.connect();
            istream=urlConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(istream));
            StringBuffer sb=new StringBuffer();
            String line="";
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            data=sb.toString();
            br.close();
        }
        catch (Exception e){
            System.err.println("--->"+e);
        }
        finally {
            istream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}

