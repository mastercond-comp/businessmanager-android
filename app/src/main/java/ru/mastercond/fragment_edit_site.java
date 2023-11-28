package ru.mastercond;

import android.app.Fragment;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class fragment_edit_site extends Fragment {

        private SQLiteConnect DB;
        public fragment_edit_site() {}
        private RSA CRYPTOALGORITM;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


   @Override
   public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

   final View rootView = inflater.inflate(R.layout.fragment_edit_sajt, container, false);
   final MainActivity appActivity=(MainActivity)getActivity();
   appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

   DB = new SQLiteConnect(getActivity());
   final String ID = appActivity.getsiteid();

   final EditText SITEname=(EditText)rootView.findViewById(R.id.site_name);
   final EditText SITEwwwaddress=(EditText)rootView.findViewById(R.id.site_www);
   final EditText SITEftpaddress=(EditText)rootView.findViewById(R.id.site_ftpaddres);
   final EditText SITEftpport=(EditText)rootView.findViewById(R.id.site_ftpport);
   final EditText SITEftpuser=(EditText)rootView.findViewById(R.id.site_ftpuser);
   final EditText SITEftppassword=(EditText)rootView.findViewById(R.id.site_ftppassword);

   final Button DBeditSITE=(Button)rootView.findViewById(R.id.buttonaddsite);


//=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

   final LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.LLContainer);
   fragRoot.setOnClickListener(new OnClickListener() {
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

     LayoutParams param = new LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), ViewGroup.LayoutParams.WRAP_CONTENT);
     fragRoot.setLayoutParams(param);

    }
    db.close();
     }

     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }

    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================


       //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
       SQLiteDatabase db = DB.getReadableDatabase();

    Cursor cursor = db.rawQuery("SELECT * FROM SITES WHERE ID = " + ID, null);
       cursor.moveToNext(); //без этого exception

       //0 - это ID 1- NAME

       //SITES
       // (ID Integer Primary Key Autoincrement NOT NULL,
       // NAME Varchar(255),
       // ADDRES Varchar(1000),
       // FTPADDRES Varchar(1000),
       // FTPPORT Varchar(20),
       // FTPUSER Varchar(255),
       // FTPPASS Varchar(255),
       // FTPSECURE Varchar(20));");




       SITEname.setText(cursor.getString(1));
       SITEwwwaddress.setText(cursor.getString(2));
       SITEftpaddress.setText(cursor.getString(3));
       SITEftpport.setText(cursor.getString(4));
       SITEftpuser.setText(cursor.getString(5));

       try {

           //прочитать ключи из файлов
           //RSA private keys are encoded in PKCS#8 format, and public keys are encoded in X.509 format
           KeyFactory kf = KeyFactory.getInstance("RSA");
           PublicKey pub_recovered = kf.generatePublic(new X509EncodedKeySpec(appActivity.getKey("PublicKey")));
           PrivateKey prv_recovered = kf.generatePrivate(new PKCS8EncodedKeySpec(appActivity.getKey("PrivateKey")));

           CRYPTOALGORITM = new RSA();

           String result = cursor.getString(6);

           try {
               String ServerPassDB = CRYPTOALGORITM.Decrypt(result, prv_recovered);
               SITEftppassword.setText(ServerPassDB);
           }

           catch (Exception ex1) {

               appActivity.Toasts(Toast.makeText(getActivity(), ex1.toString(), Toast.LENGTH_LONG),"show");
           }


       }

       catch (Exception ex) {
           appActivity.Toasts(Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG),"show");
       }


       db.close();

       //endregion


       DBeditSITE.setText("Сохранить изменения");

    DBeditSITE.setOnClickListener(new OnClickListener() {
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

                    
                    //DB.AddSite(Name, SiteAddress, FtpAddress, FtpPort, FtpUser, FtpPassCrypto);

                    Toast.makeText(getActivity(), "Данные сайта успешно изменены в базе данных", Toast.LENGTH_LONG).show();

                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                }
              
   } }) ;
    
    return rootView;
}


}
