package cn.dpocket.moplusand.yjplayer.config;

import java.util.HashMap;

import cn.dpocket.moplusand.yjplayer.BaseOption;

/**
 * Created by taotao on 16/6/3.
 */
public class FormatOption extends BaseOption {

    public FormatOption() {
        this.id = OptionId.OptionFormatId;
        init();
    }

    public void init() {
        this.mOptionMap = new HashMap<String, Object>();
        setUserAgent("youja");
    }


    public void setUserAgent(String value) {
        setValue(BaseOption.FormatKey.UserAgent, value);
    }

    public void setFFlags(String value) {
        setValue(FormatKey.FFlags, value);
    }

    public void setHttpDetectRangeSupport(long value) {
        setValue(FormatKey.HttpDetectRangeSupport, value);
    }

    public void setTimeout(long value) {
        setValue(FormatKey.Timeout, value);
    }

    public void setProbeSize(long value) {
        setValue(FormatKey.ProbeSize, value);
    }

    public void setAnalyzeDuration(long value) {
        setValue(FormatKey.AnalyzeDuration, value);
    }

    public void setAutoConvert(long value) {
        setValue(FormatKey.AutoConvert, value);
    }

    public void setReconnect(long value) {
        setValue(FormatKey.Reconnect, value);
    }
}
