package com.example.administrator.cameras;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
private  String imgPath="/sdcard/";
    private ImageView imageView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)this.findViewById(R.id.imageView1);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmss");
        Date curDate= new Date(System.currentTimeMillis());
        String str=formatter.format(curDate);
        imgPath=imgPath +"/"+str+".jpeg";
        file=new File(imgPath);
        if(!file.exists()){
            File vDirPath=file.getParentFile();
            boolean result=vDirPath.mkdirs();
            if(!result){
                Log.v("create directory","创建文件夹不成功");
            }else{
                Log.v("create directory","创建文件夹成功");


            }
        }
        Uri uri = Uri.fromFile(file);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,10);



    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(file.exists()){
            imageView.setImageURI(Uri.fromFile(file));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
