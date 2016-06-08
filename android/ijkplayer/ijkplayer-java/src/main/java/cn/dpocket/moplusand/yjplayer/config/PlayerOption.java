package cn.dpocket.moplusand.yjplayer.config;

import java.util.HashMap;

import cn.dpocket.moplusand.yjplayer.BaseOption;

/**
 * Created by taotao on 16/6/3.
 */
public class PlayerOption extends BaseOption {
    public PlayerOption() {
        this.id = OptionId.OptionPlayerId;
        init();
    }

    @Override
    protected void init() {
        this.mOptionMap = new HashMap<String, Object>();
    }

    public void setMediaCodec(long value) {
        setValue(PlayerKey.MediaCodec, value);
    }

    public void setOpenslES(long value) {
        setValue(PlayerKey.OpenslES, value);
    }

    public void setFrameDrop(long value) {
        setValue(PlayerKey.FrameDrop, value);
    }

    public void setStartOnPrepared(long value) {
        setValue(PlayerKey.StartOnPrepared, value);
    }

    public void setMinFrames(long value) {
        setValue(BaseOption.PlayerKey.MinFrames, value);
    }

    public void setVideotoolbox(long value) {
        setValue(PlayerKey.Videotoolbox, value);
    }

    public void setMinVideotoolboxMaxFrameWidth(long value) {
        setValue(PlayerKey.MinVideotoolboxMaxFrameWidth, value);
    }

    public void setVideoPictqSize(long value) {
        setValue(PlayerKey.VideoPictqSize, value);
    }

    public void setMaxFps(long value) {
        setValue(PlayerKey.MaxFps, value);
    }

    public void setOverlayFormat(long value) {
        setValue(PlayerKey.OverlayFormat, value);
    }

    public void setGetAvFrameTimeout(long value) {
        setValue(PlayerKey.GetAvFrameTimeout, value);
    }
}
