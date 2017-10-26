
package com.example.dell.edbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variants {

    @SerializedName("obfuscated")
    @Expose
    private Obfuscated obfuscated;
    @SerializedName("nsfw")
    @Expose
    private Nsfw nsfw;

    public Obfuscated getObfuscated() {
        return obfuscated;
    }

    public void setObfuscated(Obfuscated obfuscated) {
        this.obfuscated = obfuscated;
    }

    public Nsfw getNsfw() {
        return nsfw;
    }

    public void setNsfw(Nsfw nsfw) {
        this.nsfw = nsfw;
    }

}
