package com.ecse428.billmaker.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpRequest {

    CloseableHttpClient httpclient;

    /**
     * Get the list of objects by the given url
     * @param post_url
     * @return a JSON object with desired GET result
     * @throws IOException
     * @throws JSONException
     */
    public int GET(String post_url) throws JSONException, IOException {
        httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(post_url);
        CloseableHttpResponse response = httpclient.execute(httpget);
        return response.getStatusLine().getStatusCode();
    }

    /**
     * Post an object
     */
    public int POST(String post_url) throws IOException {
        httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(post_url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CloseableHttpResponse response = httpclient.execute(httppost);
        System.out.println("Got: " + response.getStatusLine().getStatusCode());
        return response.getStatusLine().getStatusCode();
    }


    class MyResponseHandler implements ResponseHandler<String> {

        public String handleResponse(final HttpResponse response) throws IOException{
            //Get the status of the response
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                if(entity == null) {
                    return "";
                } else {
                    return EntityUtils.toString(entity);
                }
            } else {
                return ""+status;
            }
        }
    }

}
