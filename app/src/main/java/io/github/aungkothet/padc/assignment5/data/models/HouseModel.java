package io.github.aungkothet.padc.assignment5.data.models;

import java.util.List;

import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;

public interface HouseModel {

    void getHouseList(String accessToken, GetHouseListFromDataLayerDelegate dataLayerDelegate);

    interface GetHouseListFromDataLayerDelegate{

        void onSuccess(List<HouseVo> houseVoList);
        void onFailure(String errMessage);
    }
}
