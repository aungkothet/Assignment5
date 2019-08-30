package io.github.aungkothet.padc.assignment5.data.models;

import io.github.aungkothet.padc.assignment5.network.dataAgents.HouseDataAgent;
import io.github.aungkothet.padc.assignment5.network.dataAgents.HttpUrlConnectionDataAgentsImpl;

public abstract class BaseModel {
    protected HouseDataAgent mDataAgent;

    BaseModel() {
        this.mDataAgent = HttpUrlConnectionDataAgentsImpl.getObjInstance();
    }
}
