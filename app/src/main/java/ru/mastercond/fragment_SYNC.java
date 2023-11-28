package ru.mastercond;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.content.Intent;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.os.Bundle;
import ru.mastercond.MenuOpened;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.R;


import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.util.DisplayMetrics;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.AdapterView.OnItemClickListener;

import ru.mastercond.SQLiteConnect;
import ru.mastercond.MainActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;

import java.lang.ClassCastException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Provider;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.EncodedKeySpec;
import java.security.KeyFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import ru.mastercond.RSA;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;


public class fragment_SYNC extends Fragment { 

private SQLiteConnect DB;
private Jedis jedis;
public fragment_SYNC() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setRetainInstance(true);
  }



  @Override
  public View onCreateView(
      final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_sync, container, false);
    final MainActivity appActivity=(MainActivity)getActivity();
    appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast
    
    DB = new SQLiteConnect(getActivity());
    
    //=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

   final LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.LLContainer); 
   fragRoot.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.opencloseMenu(true);
             } 
           }   
   );
//=================КОНЕЦ СЕКЦИИ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================



 //=================ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null); 
    cursor.moveToNext(); //без этого exception 
    String result=cursor.getString(2);

    if (result.equals("PHONE")) 
    { 
      
     LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), ViewGroup.LayoutParams.WRAP_CONTENT);
     fragRoot.setLayoutParams(param);
      
    }
    
    db.close();
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    
    
    
    Button RedisSync = (Button)rootView.findViewById(R.id.redissync);
        RedisSync.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            
            Thread thread = new Thread(new Runnable() {
              public void run() {
            
            

            try 
            {     
           
            Jedis jedis = new Jedis("192.168.1.46", 6379);
            jedis.connect();     
            jedis.auth("testpass");     
            jedis.select(0); 
            
            
            
            jedis.hset("SDELKI|Контрагент(сокр)|сделка(наим)", "KontragentFullName", "Общество с ограниченной ответственностью Ромашка");
            jedis.hset("SDELKI|Контрагент(сокр)|сделка(наим)", "KontragentUrAddr", "117155, Москва, ул Красноказарменная, 1");
            
            jedis.save(); //сохранить на диск базу
            
            final String name = jedis.hget("SDELKI|Контрагент(сокр)|сделка(наим)", "KontragentFullName");
            final String nameaddr = jedis.hget("SDELKI|Контрагент(сокр)|сделка(наим)", "KontragentUrAddr");
         
            jedis.close();
            
            //отображение Toast и других UI из потока невозможно, только из UITHREAD
            getActivity().runOnUiThread(new Runnable() {
              public void run() {
              Toast.makeText(getActivity(),"Соединение с сервером установлено "+name+" "+nameaddr,Toast.LENGTH_LONG).show(); 
             } }); 
            }
            
            catch (final Exception exx) {
            
            Log.d("ru.mastercond", exx.toString());
            
            //отображение Toast и других UI из потока невозможно, только из UITHREAD
            
            getActivity().runOnUiThread(new Runnable() {
              public void run() {
              Toast.makeText(getActivity(),exx.toString(),Toast.LENGTH_LONG).show();
            } });
            
            }
            
                }});
     
     thread.start();
     
         }    
            
    }) ;
    
    
    
return rootView;
}
}