package cn.dpocket.moplusand.yjplayer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.MediaInfo;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/**
 * Created by taotao on 16/6/2.
 */
public class YjMediaPlayer {
    private IjkMediaPlayer mIjkMediaPlayer;
    private OnErrorListener mOnErrorListener;
    private OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    private OnSeekCompleteListener mOnSeekCompleteListener;
    private OnBufferingUpdateListener mOnBufferingUpdateListener;
    private OnCompletionListener mOnCompletionListener;
    private OnPreparedListener mOnPreparedListener;
    private OnInfoListener mOnInfoListener;
    private OnMediaCodecListener mOnMediaCodecListener;

    private IjkMediaPlayer.OnErrorListener mIjkOnErrorListener;
    private IjkMediaPlayer.OnVideoSizeChangedListener mIjkOnVideoSizeChangedListener;
    private IjkMediaPlayer.OnSeekCompleteListener mIjkOnSeekCompleteListener;
    private IjkMediaPlayer.OnBufferingUpdateListener mIjkOnBufferingUpdateListener;
    private IjkMediaPlayer.OnCompletionListener mIjkOnCompletionListener;
    private IjkMediaPlayer.OnPreparedListener mIjkOnPreparedListener;
    private IjkMediaPlayer.OnInfoListener mIjkOnInfoListener;

    private boolean isSupportMediaCodec = false;

    private class OnMediaCodecSelectListener extends IjkMediaPlayer.DefaultMediaCodecSelector {
        @Override
        public String onMediaCodecSelect(IMediaPlayer mp, String mimeType, int profile, int level) {
            String name = super.onMediaCodecSelect(mp, mimeType, profile, level);
            isSupportMediaCodec = false;
            if (!TextUtils.isEmpty(name)) {
                isSupportMediaCodec = true;
            }

            if (mOnMediaCodecListener != null) {
                mOnMediaCodecListener.onMediaCodecName(name);
            }

            return name;
        }
    }

    public YjMediaPlayer() {

        mIjkMediaPlayer = new IjkMediaPlayer();

        mIjkOnErrorListener = new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError(YjMediaPlayer.this, what, extra);
                }
                return false;
            }
        };
        setOnErrorListener(mIjkOnErrorListener);

        mIjkOnVideoSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
                if (mOnVideoSizeChangedListener != null) {
                    mOnVideoSizeChangedListener.onVideoSizeChanged(YjMediaPlayer.this, width, height);
                }
            }
        };
        setOnVideoSizeChangedListener(mIjkOnVideoSizeChangedListener);

        mIjkOnSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(IMediaPlayer mp) {
                if (mOnSeekCompleteListener != null) {
                    mOnSeekCompleteListener.onSeekComplete(YjMediaPlayer.this);
                }
            }
        };
        setOnSeekCompleteListener(mIjkOnSeekCompleteListener);

        mIjkOnBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                if (mOnBufferingUpdateListener != null) {
                    mOnBufferingUpdateListener.onBufferingUpdate(YjMediaPlayer.this, percent);
                }
            }
        };
        setOnBufferingUpdateListener(mIjkOnBufferingUpdateListener);

        mIjkOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mp) {
                if (mOnCompletionListener != null) {
                    mOnCompletionListener.onCompletion(YjMediaPlayer.this);
                }
            }
        };
        setOnCompletionListener(mIjkOnCompletionListener);

        mIjkOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                if (mOnPreparedListener != null) {
                    mOnPreparedListener.onPrepared(YjMediaPlayer.this);
                }
            }
        };
        setOnPreparedListener(mIjkOnPreparedListener);

        mIjkOnInfoListener = new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                if (mOnInfoListener != null) {
                    mOnInfoListener.onInfo(YjMediaPlayer.this, what, extra);
                }
                return false;
            }
        };
        setOnInfoListener(mIjkOnInfoListener);

        mIjkMediaPlayer.setOnMediaCodecSelectListener(new OnMediaCodecSelectListener());

//        [options setPlayerOptionIntValue:30     forKey:@"max-fps"];
//        [options setPlayerOptionIntValue:0      forKey:@"framedrop"];
//        [options setPlayerOptionIntValue:3      forKey:@"video-pictq-size"];
//        [options setPlayerOptionIntValue:0      forKey:@"videotoolbox"];
//        [options setPlayerOptionIntValue:960    forKey:@"videotoolbox-max-frame-width"];
//
//        [options setFormatOptionIntValue:0                  forKey:@"auto_convert"];
//        [options setFormatOptionIntValue:1                  forKey:@"reconnect"];
//        [options setFormatOptionIntValue:30 * 1000 * 1000   forKey:@"timeout"];
//        [options setFormatOptionValue:@"ijkplayer"          forKey:@"user-agent"];
        //log level

//
//        //player CATEGORY
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 1);
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1); //ios 0 qiniu 12
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0); //qiniu 1
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 50);
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "videotoolbox", 0); //from ios
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-videotoolbox-max-frame-width", 960); //from ios
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "video-pictq-size", 3); //from ios
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 30); //from ios
//
//        //format CATEGORY
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0); //qiniu 0
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "youja");
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer");
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "timeout", 2000000); // ios 30 * 1000 * 1000
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 4 * 1024 * 1024);//4096 qiuniu def=102400
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", 8000000);//100000
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "auto_convert", 0); //from ios
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "reconnect", 1); //from ios
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "overlay-format", 842225234L); //from qiniu
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "get-av-frame-timeout", 10000000L); //from qiniu def
//
//        //codec CATEGORY
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48); //qiniu 0
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_frame", 0);//AVDISCARD_DEFAULT
//
//        mIjkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_SWS, "sws_flags", 4);//SWS_BICUBIC

//        public static final String KEY_BUFFER_TIME = "rtmp_buffer";
//        public static final String KEY_FFLAGS = "fflags";
//        public static final String VALUE_FFLAGS_NOBUFFER = "nobuffer";
//        public static final String KEY_GET_AV_FRAME_TIMEOUT = "get-av-frame-timeout";
//        public static final String KEY_LIVE_STREAMING = "live-streaming";
//        public static final String KEY_MEDIACODEC = "mediacodec";

//        this.a.setOption(4, "overlay-format", 842225234L);
//        this.a.setOption(4, "framedrop", 12L);
//        this.a.setOption(4, "start-on-prepared", 1L);
//        this.a.setOption(1, "http-detect-range-support", 0L);
//        this.a.setOption(2, "skip_loop_filter", 0L);
//        boolean var2 = false;
//        if(var1.containsKey("live-streaming") && var1.getInteger("live-streaming") != 0) {
//            var2 = true;
//            this.a.setOption(1, "rtmp_live", 1L);
//            this.a.setOption(1, "rtmp_buffer", var1.containsKey("rtmp_buffer")?(long)var1.getInteger("rtmp_buffer"):1000L);
//        }
//
//        String var3 = "analyzeduration";
//        this.a.setOption(1, var3, var1.containsKey(var3)?(long)var1.getInteger(var3):0L);
//        String var4 = "probesize";
//        this.a.setOption(1, var4, var1.containsKey(var4)?(long)var1.getInteger(var4):102400L);
//        this.a.setOption(4, "live-streaming", (long)(var2?1:0));
//        this.a.setOption(4, "get-av-frame-timeout", var1.containsKey("get-av-frame-timeout")?(long)(var1.getInteger("get-av-frame-timeout") * 1000):10000000L);
//        this.a.setOption(4 , "mediacodec", var1.containsKey("mediacodec")?(long)var1.getInteger("mediacodec"):0L);
    }

    public static void openDebug(boolean isOpen) {

        if (isOpen) {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
            IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
        }
    }

    protected void setOption(int category, String name, long value) {
        if (mIjkMediaPlayer != null) {
            mIjkMediaPlayer.setOption(category, name, value);
        }
    }

    protected void setOption(int category, String name, String value) {
        if (mIjkMediaPlayer != null) {
            mIjkMediaPlayer.setOption(category, name, value);
        }
    }

    public int getVideoDecoder() {
        if (mIjkMediaPlayer != null) {
            return mIjkMediaPlayer.getVideoDecoder();
        }
        return 0;
    }

    public void setOnMediaCodecListener(OnMediaCodecListener listener) {
        this.mOnMediaCodecListener = listener;
    }

    public boolean isSupportMediaCodec() {
        return isSupportMediaCodec;
    }

    public void setOnErrorListener(OnErrorListener mOnErrorListener) {
        this.mOnErrorListener = mOnErrorListener;
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener mOnVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = mOnVideoSizeChangedListener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener mOnSeekCompleteListener) {
        this.mOnSeekCompleteListener = mOnSeekCompleteListener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener mOnBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = mOnBufferingUpdateListener;
    }

    public void setOnCompletionListener(OnCompletionListener mOnCompletionListener) {
        this.mOnCompletionListener = mOnCompletionListener;
    }

    public void setOnPreparedListener(OnPreparedListener mOnPreparedListener) {
        this.mOnPreparedListener = mOnPreparedListener;
    }

    public void setOnInfoListener(OnInfoListener mOnInfoListener) {
        this.mOnInfoListener = mOnInfoListener;
    }

    public void setDisplay(SurfaceHolder sh) {
        mIjkMediaPlayer.setDisplay(sh);
    }

    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mIjkMediaPlayer.setDataSource(context, uri);
    }

    public void setDataSource(Context context, Uri uri, Map<String, String> headers) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mIjkMediaPlayer.setDataSource(context, uri, headers);
    }

    public void setDataSource(FileDescriptor fd) throws IOException, IllegalArgumentException, IllegalStateException {
        mIjkMediaPlayer.setDataSource(fd);
    }

    public void setDataSource(String path) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mIjkMediaPlayer.setDataSource(path);
    }

    public void setDataSource(IMediaDataSource mediaDataSource) {
        setDataSource(mediaDataSource);
    }

    public String getDataSource() {
        return mIjkMediaPlayer.getDataSource();
    }

    public void setAudioStreamType(int streamtype) {
        mIjkMediaPlayer.setAudioStreamType(streamtype);
    }

    public void setKeepInBackground(boolean keepInBackground) {
        mIjkMediaPlayer.setKeepInBackground(keepInBackground);
    }

    public int getVideoSarNum() {
        return mIjkMediaPlayer.getVideoSarNum();
    }

    public int getVideoSarDen() {
        return mIjkMediaPlayer.getVideoSarDen();
    }

    public void setWakeMode(Context context, int mode) {
        mIjkMediaPlayer.setWakeMode(context, mode);
    }

    public void setLooping(boolean looping) {
        mIjkMediaPlayer.setLooping(looping);
    }

    public boolean isLooping() {
        return mIjkMediaPlayer.isLooping();
    }

    public ITrackInfo[] getTrackInfo() {
        return mIjkMediaPlayer.getTrackInfo();
    }

    public void setSurface(Surface surface) {
        mIjkMediaPlayer.setSurface(surface);
    }

    public void prepareAsync() throws IllegalStateException {
        mIjkMediaPlayer.prepareAsync();
    }

    public void start() throws IllegalStateException {
        mIjkMediaPlayer.start();
    }

    public void stop() throws IllegalStateException {
        mIjkMediaPlayer.stop();
    }

    public void pause() throws IllegalStateException {
        mIjkMediaPlayer.pause();
    }

    public void setScreenOnWhilePlaying(boolean screenOn) {
        mIjkMediaPlayer.setScreenOnWhilePlaying(screenOn);
    }

    public int getVideoWidth() {
        return mIjkMediaPlayer.getVideoWidth();
    }

    public int getVideoHeight() {
        return mIjkMediaPlayer.getVideoHeight();
    }

    public boolean isPlaying() {
        return mIjkMediaPlayer.isPlaying();
    }

    public void seekTo(long msec) throws IllegalStateException {
        mIjkMediaPlayer.seekTo(msec);
    }

    public long getCurrentPosition() {
        return mIjkMediaPlayer.getCurrentPosition();
    }

    public long getDuration() {
        return mIjkMediaPlayer.getDuration();
    }

    public void release() {
        mIjkMediaPlayer.release();
    }

    public void reset() {
        mIjkMediaPlayer.reset();
    }

    public void setVolume(float leftVolume, float rightVolume) {
        mIjkMediaPlayer.setVolume(leftVolume, rightVolume);
    }

    public int getAudioSessionId() {
        return mIjkMediaPlayer.getAudioSessionId();
    }

    public MediaInfo getMediaInfo() {
        return mIjkMediaPlayer.getMediaInfo();
    }

    public void setLogEnabled(boolean enable) {
        mIjkMediaPlayer.setLogEnabled(enable);
    }

    public boolean isPlayable() {
        return mIjkMediaPlayer.isPlayable();
    }

    private void setOnPreparedListener(IMediaPlayer.OnPreparedListener listener) {
        mIjkMediaPlayer.setOnPreparedListener(listener);
    }

    private void setOnCompletionListener(IMediaPlayer.OnCompletionListener listener) {
        mIjkMediaPlayer.setOnCompletionListener(listener);
    }

    private void setOnBufferingUpdateListener(IMediaPlayer.OnBufferingUpdateListener listener) {
        mIjkMediaPlayer.setOnBufferingUpdateListener(listener);
    }

    private void setOnSeekCompleteListener(IMediaPlayer.OnSeekCompleteListener listener) {
        mIjkMediaPlayer.setOnSeekCompleteListener(listener);
    }

    private void setOnVideoSizeChangedListener(IMediaPlayer.OnVideoSizeChangedListener listener) {
        mIjkMediaPlayer.setOnVideoSizeChangedListener(listener);
    }

    private void setOnErrorListener(IMediaPlayer.OnErrorListener listener) {
        mIjkMediaPlayer.setOnErrorListener(listener);
    }

    private void setOnInfoListener(IMediaPlayer.OnInfoListener listener) {
        mIjkMediaPlayer.setOnInfoListener(listener);
    }

    public interface OnErrorListener {
        boolean onError(YjMediaPlayer mp, int what, int extra);
    }

    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(YjMediaPlayer mp, int width, int height);
    }

    public interface OnSeekCompleteListener {
        void onSeekComplete(YjMediaPlayer mp);
    }

    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(YjMediaPlayer mp, int percent);
    }

    public interface OnCompletionListener {
        void onCompletion(YjMediaPlayer mp);
    }

    public interface OnPreparedListener {
        void onPrepared(YjMediaPlayer mp);
    }

    public interface OnInfoListener {
        boolean onInfo(YjMediaPlayer mp, int what, int extra);
    }

    public interface OnMediaCodecListener {
        void onMediaCodecName(String name);
    }
}
