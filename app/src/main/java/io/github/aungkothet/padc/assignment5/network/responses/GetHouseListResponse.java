package io.github.aungkothet.padc.assignment5.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;

import static io.github.aungkothet.padc.assignment5.utils.Constants.CODE_RESPONSE_OK;

public class GetHouseListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<HouseVo> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HouseVo> getData() {
        return data;
    }

    public void setData(List<HouseVo> data) {
        this.data = data;
    }

    public boolean isResponseOk(){
        return code == CODE_RESPONSE_OK && data != null;
    }
}
