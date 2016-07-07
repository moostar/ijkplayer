package cn.dpocket.moplusand.yjplayer.config;

import java.util.HashMap;

import cn.dpocket.moplusand.yjplayer.BaseOption;

/**
 * Created by taotao on 16/6/3.
 */
public class CodecOption extends BaseOption {

    public CodecOption() {
        this.id = OptionId.OptionCodecId;
        init();
    }

    @Override
    protected void init() {
        this.mOptionMap = new HashMap<String, Object>();
    }

    public void setSkipLoopFilter(long value) {
        setValue(CodecKey.SkipLoopFilter, value);
    }

    public void setSkipFrame(long value) {
        setValue(CodecKey.SkipFrame, value);
    }
}
