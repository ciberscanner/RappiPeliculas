
package com.kiwabolab.rappipeliculas.modelo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token implements Serializable
{

    @SerializedName("status_code")
    @Expose
    public Integer statusCode;
    @SerializedName("status_message")
    @Expose
    public String statusMessage;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("request_token")
    @Expose
    public String requestToken;
    private final static long serialVersionUID = -749664990626157929L;

}
