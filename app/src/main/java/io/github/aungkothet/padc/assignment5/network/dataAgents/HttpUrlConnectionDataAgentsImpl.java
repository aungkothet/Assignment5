package io.github.aungkothet.padc.assignment5.network.dataAgents;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import io.github.aungkothet.padc.assignment5.network.responses.GetHouseListResponse;
import io.github.aungkothet.padc.assignment5.utils.Constants;


public class HttpUrlConnectionDataAgentsImpl implements HouseDataAgent {

    private static HttpUrlConnectionDataAgentsImpl objInstance;

    private HttpUrlConnectionDataAgentsImpl() {

    }

    public static HttpUrlConnectionDataAgentsImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new HttpUrlConnectionDataAgentsImpl();
        }
        return objInstance;
    }

    @Override
    public void getHouseList(String accessToken, GetHouseListFromNetworkDelegates delegates) {
        new GetEventsTask(accessToken, delegates).execute();
    }

    private static class GetEventsTask extends AsyncTask<Void, Void, GetHouseListResponse> {
        String accessToken;
        GetHouseListFromNetworkDelegates delegates;

        public GetEventsTask(String accessToken, GetHouseListFromNetworkDelegates delegates) {
            this.accessToken = accessToken;
            this.delegates = delegates;

        }

        @Override
        protected GetHouseListResponse doInBackground(Void... voids) {
            URL url;
            StringBuilder stringBuilder;
            BufferedReader reader = null;

            try {

                url = new URL(Constants.URL_GET_ALL_HOUSE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setConnectTimeout(15 * 1000);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                List<NameValuePair> param = new ArrayList<>();
                param.add(new BasicNameValuePair(Constants.PARAM_ACCESS_TOKEN, accessToken));

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(getQuery(param));
                writer.flush();
                writer.close();
                outputStream.close();
                connection.connect();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                String responseString = stringBuilder.toString();

                GetHouseListResponse response = new Gson().fromJson(responseString, GetHouseListResponse.class);
                return response;

            } catch (Exception e) {

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        private String getQuery(List<NameValuePair> param) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (NameValuePair pairs : param) {
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(pairs.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pairs.getValue(), "UTF-8"));
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(GetHouseListResponse response) {
            super.onPostExecute(response);
            System.out.println(response);
            if (response != null) {
                delegates.onSuccess(response.getData());
            } else {
                delegates.onFailure(response.getMessage());
            }
        }
    }
}
