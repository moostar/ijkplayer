package cn.dpocket.moplusand.yjplayer;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.security.Key;
import java.util.HashMap;

import cn.dpocket.moplusand.yjplayer.config.CodecOption;
import cn.dpocket.moplusand.yjplayer.config.FormatOption;
import cn.dpocket.moplusand.yjplayer.config.PlayerOption;
import cn.dpocket.moplusand.yjplayer.config.SwsOption;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by taotao on 16/6/3.
 */
public abstract class BaseOption {
    public interface PlayerKey {
        String MediaCodec = "mediacodec";
        String OpenslES = "opensles";
        String FrameDrop = "framedrop";
        String StartOnPrepared = "start-on-prepared";
        String MinFrames = "min-frames";
        String Videotoolbox = "videotoolbox";
        String MinVideotoolboxMaxFrameWidth = "min-videotoolbox-max-frame-width";
        String VideoPictqSize = "video-pictq-size";
        String MaxFps = "max-fps";
        String OverlayFormat = "overlay-format";
        String GetAvFrameTimeout = "get-av-frame-timeout";
    }

    public interface FormatKey {
        String HttpDetectRangeSupport = "http-detect-range-support";
        String UserAgent = "user_agent";
        String FFlags = "fflags";
        String Timeout = "timeout";
        String ProbeSize = "probesize";
        String AnalyzeDuration = "analyzeduration";
        String AutoConvert = "auto_convert";
        String Reconnect = "reconnect";
    }

    public interface CodecKey {
        String SkipLoopFilter = "skip_loop_filter";
        String SkipFrame = "skip_frame";
    }

    public interface SwsKey {
        String SwsFlags = "sws_flags";
    }

    public interface OptionId {
        int OptionPlayerId = IjkMediaPlayer.OPT_CATEGORY_PLAYER;
        int OptionFormatId = IjkMediaPlayer.OPT_CATEGORY_FORMAT;
        int OptionCodecId = IjkMediaPlayer.OPT_CATEGORY_CODEC;
        int OptionSwsId = IjkMediaPlayer.OPT_CATEGORY_SWS;
    }


    protected int id;

    protected HashMap<String, Object> mOptionMap;

    protected abstract void init();

    public void clear(){
        if (mOptionMap != null){
            mOptionMap.clear();
        }
    }

    public void removeOption(String key){
        if (!TextUtils.isEmpty(key)){
            mOptionMap.remove(key);
        }
    }

    public void setValue(String key, Object value) {
        if (this.mOptionMap != null) {
            this.mOptionMap.remove(key);
            this.mOptionMap.put(key, value);
        }
    }

    private static CodecOption mCodecOption;

    public static CodecOption getCodecOption() {
        if (mCodecOption == null) {
            mCodecOption = new CodecOption();
        }
        return mCodecOption;
    }

    private static FormatOption mFormatOption;

    public static FormatOption getFormatOption() {
        if (mFormatOption == null) {
            mFormatOption = new FormatOption();
        }
        return mFormatOption;
    }

    private static PlayerOption mPlayerOption;

    public static PlayerOption getPlayerOption() {
        if (mPlayerOption == null) {
            mPlayerOption = new PlayerOption();
        }
        return mPlayerOption;
    }

    private static SwsOption mSwsOption;
    public static SwsOption getSwsOption(){
        if (mSwsOption == null){
            mSwsOption = new SwsOption();
        }
        return mSwsOption;
    }

    public static void release(){
        if (mSwsOption != null){
            mSwsOption.clear();
        }

        if (mPlayerOption != null){
            mPlayerOption.clear();
        }

        if (mCodecOption != null){
            mCodecOption.clear();
        }

        if (mFormatOption != null){
            mFormatOption.clear();
        }
    }

    public static void setMpOptions(YjMediaPlayer player){
        if (player != null){
            setOptions(getCodecOption(), player);
            setOptions(getFormatOption(), player);
            setOptions(getSwsOption(), player);
            setOptions(getPlayerOption(), player);
        }
    }

    private static void setOptions(BaseOption option, YjMediaPlayer player){
        if (option != null && option.mOptionMap != null && option.mOptionMap.size() > 0){
            for (String key : option.mOptionMap.keySet()) {
                Object value = option.mOptionMap.get(key);
                if (value instanceof String){
                    player.setOption(option.id, key, (String)value);
                }else if (value instanceof Long){
                    player.setOption(option.id, key, (Long)value);
                }
            }
        }
    }


    //log level
//    mIjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
//
//    //player CATEGORY
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 1);
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1); //ios 0 qiniu 12
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0); //qiniu 1
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 50);
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "videotoolbox", 0); //from ios
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-videotoolbox-max-frame-width", 960); //from ios
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "video-pictq-size", 3); //from ios
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 30); //from ios
//
//    //format CATEGORY
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0); //qiniu 0
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "youja");
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer");
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "timeout", 2000000); // ios 30 * 1000 * 1000
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 4 * 1024 * 1024);//4096 qiuniu def=102400
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", 8000000);//100000
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "auto_convert", 0); //from ios
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "reconnect", 1); //from ios
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "overlay-format", 842225234L); //from qiniu
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "get-av-frame-timeout", 10000000L); //from qiniu def
//
//    //codec CATEGORY
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48); //qiniu 0
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_frame", 0);//AVDISCARD_DEFAULT
//
//    mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_SWS, "sws_flags", 4);//SWS_BICUBIC
}
