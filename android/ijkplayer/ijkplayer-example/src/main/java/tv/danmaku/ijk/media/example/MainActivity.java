package tv.danmaku.ijk.media.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import cn.dpocket.moplusand.yjplayer.BaseOption;
import cn.dpocket.moplusand.yjplayer.YjMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Apple on 16/6/1.
 */
public class MainActivity extends Activity {

    private YjMediaPlayer ijkMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        SurfaceView view = (SurfaceView)findViewById(R.id.sufaceview);
//        YjMediaPlayer.openDebug(true);
        ijkMediaPlayer = createMediaPlay();
        BaseOption.getPlayerOption().setMediaCodec(1);
        BaseOption.setMpOptions(ijkMediaPlayer);
        try {
            ijkMediaPlayer.setDataSource("rtmp://pili-live-rtmp.qn.live.youja.cn/youja/57369d92eb6f925c31000b70");
//            ijkMediaPlayer.setDataSource("http://pili-live-hdl.qn.live.youja.cn/youja/786e207ecf1cfa9ace5a0cfd77521751.flv");
        }catch (Exception e){

        }
//        ijkMediaPlayer.setDisplay(view.getHolder());
        final SurfaceHolder holder = view.getHolder();
        holder.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                ijkMediaPlayer.setDisplay(holder);
                ijkMediaPlayer.setSurface(holder.getSurface());
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        ijkMediaPlayer.setDisplay(holder);
        ijkMediaPlayer.prepareAsync();

        ijkMediaPlayer.setOnBufferingUpdateListener(new YjMediaPlayer.OnBufferingUpdateListener() {

            @Override
            public void onBufferingUpdate(YjMediaPlayer mp, int percent) {

            }
        });
        ijkMediaPlayer.setOnErrorListener(new YjMediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(YjMediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        ijkMediaPlayer.setOnPreparedListener(new YjMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(YjMediaPlayer mp) {
                holder.setFixedSize(mp.getVideoWidth(), mp.getVideoHeight());
                mp.start();
            }
        });

        ijkMediaPlayer.setOnMediaCodecListener(new YjMediaPlayer.OnMediaCodecListener() {
            @Override
            public void onMediaCodecName(String name) {
                Log.e("IjkMediaPlayer", "onMediaCodecName name=" + name);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaPlayer.release();
    }

    /////////////////////////////

    YjMediaPlayer createMediaPlay(){
        YjMediaPlayer ijkMediaPlayer = new YjMediaPlayer();
//        ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
//
////        if (mSettings.getUsingMediaCodec()) {
//            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
//            if (mSettings.getUsingMediaCodecAutoRotate()) {
//                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
//            } else {
//                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 0);
//            }
//        } else {
//            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
//        }

//        if (mSettings.getUsingOpenSLES()) {
//            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 1);
//        } else {
//            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
//        }

//        String pixelFormat = mSettings.getPixelFormat();
//        if (TextUtils.isEmpty(pixelFormat)) {
//            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
//        } else {
//            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", pixelFormat);
//        }
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
//
//        //新增加设置
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "youja");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 50);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "timeout", 2000000);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_frame", 0);//AVDISCARD_DEFAULT
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 4*1024*1024);//4096
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", 8000000);//100000
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_SWS, "sws_flags", 4);//SWS_BICUBIC


        return ijkMediaPlayer;
    }


}
