package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int mCatSound, mDogSound, mEagleSound,mCowSound, mWolfSound,mChickenSound;
    private int mStream;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ImageButton cowImageButton=findViewById(R.id.imageButtonCow);
         cowImageButton.setOnClickListener(onClickListener);
         ImageButton dogImageButton=findViewById(R.id.imageButtonDog);
         dogImageButton.setOnClickListener(onClickListener);
         ImageButton catImageButton=findViewById(R.id.imageButtonCat);
         catImageButton.setOnClickListener(onClickListener);
         ImageButton eagleImageButton=findViewById(R.id.imageButtonEagle);
        eagleImageButton.setOnClickListener(onClickListener);
         ImageButton chickenImageButton=findViewById(R.id.imageButtonChicken);
         chickenImageButton.setOnClickListener(onClickListener);
         ImageButton wolfImageButton=findViewById(R.id.imageButtonChicken);
        wolfImageButton.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch(view.getId()){ case R.id.imageButtonCow:playSound(mCowSound);
            case R.id.imageButtonEagle:playSound(mEagleSound);
                case R.id.imageButtonDog:playSound(mDogSound);
                case R.id.imageButtonWolf:playSound(mWolfSound);
                case R.id.imageButtonCat:playSound(mCatSound);
                case R.id.imageButtonChicken:playSound(mChickenSound);
            break;}
        }
    };
@TargetApi(Build.VERSION_CODES.Q)
private void createNewSoundPool()
{
    AudioAttributes attributes=new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
    mSoundPool=new SoundPool.Builder().setAudioAttributes(attributes).build();
}
    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool=new SoundPool(3,AudioManager.STREAM_MUSIC,0);
    }
    private int playSound(int sound)
    {
        if(sound>0)
        {
            mStream=mSoundPool.play(sound,1,1,1,0,1);

        }
        return mStream;
    }
    private int loadSound(String Filename)
    {
        AssetFileDescriptor afd;
        try{
            afd=mAssetManager.openFd(Filename);
        }
        catch (IOException e)
        {
            e.printStackTrace();;
            Toast.makeText(getApplicationContext(),"cannot download file"+Filename,Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd,1);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.Q){
            createOldSoundPool();
        }
        else {createNewSoundPool();
    }
    mAssetManager =getAssets();
        mCowSound=loadSound("cow.mp3");
        mDogSound=loadSound("dog.mp3");
        mEagleSound=loadSound("eagle.mp3");
        mCatSound=loadSound("cat.mp3");
        mWolfSound=loadSound("wolf.mp3");
        mChickenSound=loadSound("chicken.mp3");

}
@Override
    protected void onPause()
{
    super.onPause();
    mSoundPool.release();
    mSoundPool=null;
}
}
