package io.github.aungkothet.padc.assignment5.data.models;

import io.github.aungkothet.padc.assignment5.network.dataAgents.HouseDataAgent;
import io.github.aungkothet.padc.assignment5.network.dataAgents.RetrofitDataAgentImpl;

public abstract class BaseModel {
    protected HouseDataAgent mDataAgent;

    BaseModel() {

//        this.mDataAgent = HttpUrlConnectionDataAgentsImpl.getObjInstance();
//        this.mDataAgent = OkHttpDataAgentImpl.getObjInstance();
        this.mDataAgent = RetrofitDataAgentImpl.getObjInstance();
    }
}
