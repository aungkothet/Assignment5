package io.github.aungkothet.padc.assignment5.network.dataAgents;

import java.util.List;

import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;

public interface HouseDataAgent {

    void getHouseList(String accessToken, GetHouseListFromNetworkDelegates delegates);

    interface GetHouseListFromNetworkDelegates {

        void onSuccess(List<HouseVo> events);

        void onFailure(String errorMessage);
    }
}
