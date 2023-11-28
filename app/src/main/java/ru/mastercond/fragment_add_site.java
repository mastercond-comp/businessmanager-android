package ru.mastercond;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ClassCastException;
import java.util.ArrayList;
import java.util.List;
import ru.mastercond.MainActivity;
import ru.mastercond.MenuOpened;
import ru.mastercond.R;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SdelkaID;
import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;
import ru.mastercond.SetDynamicHeightListView;

import java.lang.ClassCastException;

import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
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
import java.security.KeyException;
import java.security.KeyManagementException;
import java.security.AccessControlException;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;

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



public class fragment_add_site extends Fragment {
  
        private SQLiteConnect DB;
        public fragment_add_site() {}
        private RSA CRYPTOALGORITM;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }    
        
          
   @Override
   public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
   
   final View rootView = inflater.inflate(R.layout.fragment_add_sajt, container, false);
   final MainActivity appActivity=(MainActivity)getActivity();
   appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

   DB = new SQLiteConnect(getActivity());
   
   final EditText SITEname=(EditText)rootView.findViewById(R.id.site_name);
   final EditText SITEwwwaddress=(EditText)rootView.findViewById(R.id.site_www);
   final EditText SITEftpaddress=(EditText)rootView.findViewById(R.id.site_ftpaddres);
   final EditText SITEftpport=(EditText)rootView.findViewById(R.id.site_ftpport);
   final EditText SITEftpuser=(EditText)rootView.findViewById(R.id.site_ftpuser);
   final EditText SITEftppassword=(EditText)rootView.findViewById(R.id.site_ftppassword);
   
   final Button DBaddSITE=(Button)rootView.findViewById(R.id.buttonaddsite);
   
    
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
    
    
    
    DBaddSITE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
              try {
                    MainActivity rootActivity = (MainActivity) getActivity();

                    //прочитать ключи из файлов
                    //RSA private keys are encoded in PKCS#8 format, and public keys are encoded in X.509 format
                    KeyFactory kf = KeyFactory.getInstance("RSA");
                    PublicKey pub_recovered = kf.generatePublic(new X509EncodedKeySpec(rootActivity.getKey("PublicKey")));
                    PrivateKey prv_recovered = kf.generatePrivate(new PKCS8EncodedKeySpec(rootActivity.getKey("PrivateKey")));

                    CRYPTOALGORITM = new RSA();



                    String Name = SITEname.getText().toString();
                    String SiteAddress = SITEwwwaddress.getText().toString();
                    String FtpAddress = SITEftpaddress.getText().toString();
                    String FtpPort = SITEftpport.getText().toString();
                    String FtpUser = SITEftpuser.getText().toString();
                    String FtpPass = SITEftppassword.getText().toString();
                    
                    String FtpPassCrypto = CRYPTOALGORITM.Encrypt(FtpPass, pub_recovered);

                    
                    DB.AddSite(Name, SiteAddress, FtpAddress, FtpPort, FtpUser, FtpPassCrypto);

                    Toast.makeText(getActivity(), "Сайт успешно добавлен в базу данных", Toast.LENGTH_LONG).show();

                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                }
              
   } }) ;
    
    return rootView;
}


}
