package cn.dpocket.moplusand.yjplayer.config;

import java.util.HashMap;

import cn.dpocket.moplusand.yjplayer.BaseOption;

/**
 * Created by taotao on 16/6/3.
 */
public class SwsOption extends BaseOption {
    public SwsOption() {
        this.id = OptionId.OptionSwsId;
        init();
    }

    @Override
    protected void init() {
        this.mOptionMap = new HashMap<String, Object>();
    }

    public void setSwsFlags(long value){
        setValue(SwsKey.SwsFlags, value);
    }
}
