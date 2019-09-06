package io.github.aungkothet.padc.assignment5.network.dataAgents;

import android.os.AsyncTask;

import com.google.gson.Gson;

import io.github.aungkothet.padc.assignment5.network.responses.GetHouseListResponse;
import io.github.aungkothet.padc.assignment5.utils.Constants;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static io.github.aungkothet.padc.assignment5.utils.Constants.EM_NULL_RESPONSE;
import static java.util.concurrent.TimeUnit.SECONDS;

public class OkHttpDataAgentImpl implements HouseDataAgent {
    private static OkHttpDataAgentImpl objInstance;

    private OkHttpClient mHttpClient;

    private OkHttpDataAgentImpl() {

        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, SECONDS)
                .writeTimeout(15, SECONDS)
                .readTimeout(15, SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new OkHttpDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void getHouseList(String accessToken, GetHouseListFromNetworkDelegates delegates) {
        new GetEventsTask(mHttpClient, accessToken, delegates).execute();
    }

    private static class GetEventsTask extends AsyncTask<Void, Void, GetHouseListResponse> {

        OkHttpClient httpClient;
        String accessToken;
        GetHouseListFromNetworkDelegates delegates;

        public GetEventsTask(OkHttpClient okHttpClient, String accessToken, GetHouseListFromNetworkDelegates delegates) {
            this.httpClient = okHttpClient;
            this.accessToken = accessToken;
            this.delegates = delegates;

        }

        @Override
        protected GetHouseListResponse doInBackground(Void... voids) {

            RequestBody requestBody = new FormBody.Builder()
                    .add(Constants.PARAM_ACCESS_TOKEN, accessToken)
                    .build();

            Request request = new Request.Builder()
                    .url(Constants.URL_GET_ALL_HOUSE)
                    .post(requestBody)
                    .build();

            try {
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    GetHouseListResponse getEventsResponse = new Gson().fromJson(responseString, GetHouseListResponse.class);
                    return getEventsResponse;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(GetHouseListResponse getEventsResponse) {
            super.onPostExecute(getEventsResponse);
            if (getEventsResponse != null) {
                if (getEventsResponse.isResponseOk()) {
                    delegates.onSuccess(getEventsResponse.getData());
                } else {
                    delegates.onFailure(getEventsResponse.getMessage());
                }

            } else {
                delegates.onFailure(EM_NULL_RESPONSE);
            }
        }
    }
}
