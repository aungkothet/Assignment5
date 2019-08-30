package io.github.aungkothet.padc.assignment5.data.models;

import java.util.List;

import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;
import io.github.aungkothet.padc.assignment5.network.dataAgents.HouseDataAgent;

public class HouseModelImpl extends BaseModel implements HouseModel{

    private static HouseModelImpl objInstance;

    private HouseModelImpl() {

    }

    public static HouseModelImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new HouseModelImpl();
        }
        return objInstance;
    }

    @Override
    public void getHouseList(String accessToken, final GetHouseListFromDataLayerDelegate dataLayerDelegate) {

        //data call make from dataAgent and parse inner class for NetworkLayerDelegate
        mDataAgent.getHouseList(accessToken, new HouseDataAgent.GetHouseListFromNetworkDelegates() {
            @Override
            public void onSuccess(List<HouseVo> events) {
                dataLayerDelegate.onSuccess(events);
            }

            @Override
            public void onFailure(String errorMessage) {
                dataLayerDelegate.onFailure(errorMessage);
            }
        });
    }
}
