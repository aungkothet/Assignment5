package io.github.aungkothet.padc.assignment5.data.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;
import io.github.aungkothet.padc.assignment5.network.dataAgents.HouseDataAgent;

public class HouseModelImpl extends BaseModel implements HouseModel {

    private static HouseModelImpl objInstance;

    private Map<Integer, HouseVo> houseDataRepositories;

    private HouseModelImpl() {
        houseDataRepositories = new HashMap<>();
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
            public void onSuccess(List<HouseVo> houseVoList) {
                for (HouseVo houseVo :
                        houseVoList) {
                    houseDataRepositories.put(houseVo.getId(), houseVo);
                }

                dataLayerDelegate.onSuccess(houseVoList);
            }

            @Override
            public void onFailure(String errorMessage) {
                dataLayerDelegate.onFailure(errorMessage);
            }
        });
    }

    @Override
    public HouseVo getHouseById(int houseID) {
        HouseVo houseVo = houseDataRepositories.get(houseID);
        return houseVo;
    }

    @Override
    public List<HouseVo> filterHouse(String query) {
        List<HouseVo> houseList = new ArrayList<>(houseDataRepositories.values());
        List<HouseVo> resultList = new ArrayList<>();
        for (HouseVo house :
                houseList ) {
            if(house.getName().contains(query))
                resultList.add(house);
        }
        return resultList;
    }


}
