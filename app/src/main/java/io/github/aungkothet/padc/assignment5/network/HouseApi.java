package io.github.aungkothet.padc.assignment5.network;

import io.github.aungkothet.padc.assignment5.network.responses.GetHouseListResponse;
import io.github.aungkothet.padc.assignment5.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HouseApi {

    @POST(Constants.URL_GET_ALL_HOUSE)
    @FormUrlEncoded
    Call<GetHouseListResponse> getHouseList(@Field(Constants.PARAM_ACCESS_TOKEN) String accessToken);
}
