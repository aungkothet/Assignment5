package io.github.aungkothet.padc.assignment5.network.dataAgents;

import io.github.aungkothet.padc.assignment5.network.HouseApi;
import io.github.aungkothet.padc.assignment5.network.responses.GetHouseListResponse;
import io.github.aungkothet.padc.assignment5.utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class RetrofitDataAgentImpl implements HouseDataAgent {

    private static RetrofitDataAgentImpl objInstance;

    private HouseApi eventsApi;

    public static RetrofitDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgentImpl();
        }
        return objInstance;
    }

    private RetrofitDataAgentImpl() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, SECONDS)
                .writeTimeout(15, SECONDS)
                .readTimeout(15, SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        eventsApi = retrofit.create(HouseApi.class);

    }

    @Override
    public void getHouseList(String accessToken, final GetHouseListFromNetworkDelegates delegates) {

        Call<GetHouseListResponse> eventsResponseCall = eventsApi.getHouseList(accessToken);
        eventsResponseCall.enqueue(new Callback<GetHouseListResponse>() {
            @Override
            public void onResponse(Call<GetHouseListResponse> call, Response<GetHouseListResponse> response) {
                if (response.body().isResponseOk()) {
                    delegates.onSuccess(response.body().getData());
                } else {
                    delegates.onFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<GetHouseListResponse> call, Throwable t) {
                delegates.onFailure(t.getMessage());
            }
        });
    }
}
