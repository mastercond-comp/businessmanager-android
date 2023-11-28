package ru.mastercond;

import android.app.AlertDialog;
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


public class fragment_SETTINGS extends Fragment {


    private SQLiteConnect DB;
    private SQLiteConnect DB1;
    private RSA CRYPTOALGORITM;
    AlertDialog.Builder ad;

    public fragment_SETTINGS() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(
            final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final MainActivity appActivity=(MainActivity)getActivity();
        appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

        DB = new SQLiteConnect(getActivity());


        //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

        final LinearLayout fragRoot = (LinearLayout) rootView.findViewById(R.id.LLContainer);
        fragRoot.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            MainActivity rootActivity = (MainActivity) getActivity();
                                            rootActivity.opencloseMenu(true);
                                        }
                                    }
        );
//endregion

        //region ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ

        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null);
            cursor.moveToNext(); //без этого exception
            String result = cursor.getString(2);

            if (result.equals("PHONE")) {

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density * 350), ViewGroup.LayoutParams.WRAP_CONTENT);
                fragRoot.setLayoutParams(param);

            }

            db.close();
        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }

        //endregion


        final RadioButton ButtonPlanshet = (RadioButton) rootView.findViewById(R.id.radio_planshet);
        final RadioButton ButtonPhone = (RadioButton) rootView.findViewById(R.id.radio_smartphone);

        final TextView TextViewServerAddr = (TextView) rootView.findViewById(R.id.ftpserver_addr);
        final TextView TextViewServerPort = (TextView) rootView.findViewById(R.id.ftpserver_port);
        final TextView TextViewServerUser = (TextView) rootView.findViewById(R.id.ftpserver_user);
        final TextView TextViewServerPass = (TextView) rootView.findViewById(R.id.ftpserver_pass);


        //region ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null);
            cursor.moveToNext(); //без этого exception
            String result = cursor.getString(2);
            //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            if (result.equals("TABLET")) {
                ButtonPlanshet.setChecked(true);
            }
            if (result.equals("PHONE")) {
                ButtonPhone.setChecked(true);
            }
            db.close();

        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }


        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '2'", null);
            cursor.moveToNext(); //без этого exception
            String result = cursor.getString(2);

            TextViewServerAddr.setText(result);

            db.close();
        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }

        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '3'", null);
            cursor.moveToNext(); //без этого exception
            String result = cursor.getString(2);

            TextViewServerPort.setText(result);

            db.close();
        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }

        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '4'", null);
            cursor.moveToNext(); //без этого exception
            String result = cursor.getString(2);

            TextViewServerUser.setText(result);

            db.close();
        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }


        try {

            MainActivity rootActivity = (MainActivity) getActivity();

            //прочитать ключи из файлов
            //RSA private keys are encoded in PKCS#8 format, and public keys are encoded in X.509 format
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pub_recovered = kf.generatePublic(new X509EncodedKeySpec(rootActivity.getKey("PublicKey")));
            PrivateKey prv_recovered = kf.generatePrivate(new PKCS8EncodedKeySpec(rootActivity.getKey("PrivateKey")));

            CRYPTOALGORITM = new RSA();

            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '5'", null);
            cursor.moveToNext(); //без этого exception 
            String result = cursor.getString(2);

            try {
                String ServerPassDB = CRYPTOALGORITM.Decrypt(result, prv_recovered);
                TextViewServerPass.setText(ServerPassDB);
            }

            catch (Exception ex1) {

                appActivity.Toasts(Toast.makeText(getActivity(), ex1.toString(), Toast.LENGTH_LONG),"show");
            }

            db.close();

        }

        catch (Exception ex) {
            appActivity.Toasts(Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG),"show");
        }



        //endregion


        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroupDevice);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.radio_planshet:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db = DB.getReadableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('1','VIEWTYPE','TABLET');");
                        //Toast.makeText(getActivity(),"РЕЖИМ ПЛАНШЕТА",Toast.LENGTH_LONG).show();
                        db.close();
                        break;
                    case R.id.radio_smartphone:
                        DB1 = new SQLiteConnect(getActivity());
                        SQLiteDatabase db1 = DB1.getReadableDatabase();
                        db1.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('1','VIEWTYPE','PHONE');");
                        //Toast.makeText(getActivity(),"РЕЖИМ ТЕЛЕФОНА",Toast.LENGTH_LONG).show();
                        db1.close();
                        break;


                    default:
                        break;
                }
            }
        });


        Button DBexport = (Button) rootView.findViewById(R.id.buttonexportbd);

        DBexport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad = new AlertDialog.Builder(container.getContext());
                ad.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_confirm, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                ad.setView(dialogView);
                final AlertDialog ydialog = ad.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                btn_negative.setText("Не экспортировать");
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                btn_positive.setText("Экспортировать");
                final TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Выполнить экспорт базы данных в папку приложения? Существующий файл businessmanagement.db будет перезаписан.");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ydialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity rootActivity = (MainActivity) getActivity();
                        rootActivity.exportbd();
                        appActivity.Toasts(Toast.makeText(getActivity(), "Экспорт базы данных в папку приложения выполнен", Toast.LENGTH_LONG),"show");
                        ydialog.cancel();


                    }
                });

                ydialog.show();
            }
        });


        Button DBimport = (Button) rootView.findViewById(R.id.buttonimportbd);
        DBimport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            ad = new AlertDialog.Builder(container.getContext());
                ad.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_confirm, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                ad.setView(dialogView);
                final AlertDialog ydialog = ad.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                btn_negative.setText("Не импортировать");
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                btn_positive.setText("Импортировать");
                final TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Выполнить импорт базы данных businessmanagement.db из папки в приложение? База данных в приложении будет перезаписана, а данные заменены.");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ydialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity rootActivity = (MainActivity) getActivity();
                        rootActivity.importbd();
                        appActivity.Toasts(Toast.makeText(getActivity(), "Импорт базы данных в папку приложения выполнен", Toast.LENGTH_LONG),"show");
                        ydialog.cancel();


                    }
                });

                ydialog.show();
            }
        });
            
            
            
            
            
          

        Button DBexportFTP = (Button) rootView.findViewById(R.id.buttonexportbdftp);
        DBexportFTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
                ad = new AlertDialog.Builder(container.getContext());
                ad.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_confirm, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                ad.setView(dialogView);
                final AlertDialog ydialog = ad.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                btn_negative.setText("Не экспортировать");
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                btn_positive.setText("Экспортировать");
                final TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Выполнить экспорт (выгрузку) базы данных businessmanagement.db на удаленный FTP-сервер? В случае, если файл businessmanagement.db существует, он будет перезаписан.");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ydialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        ydialog.cancel();
                
                MainActivity rootActivity = (MainActivity)getActivity();


                String ServerAddr = TextViewServerAddr.getText().toString();
                String ServerUser = TextViewServerUser.getText().toString();
                String ServerPort = TextViewServerPort.getText().toString();
                String ServerPass = TextViewServerPass.getText().toString();

                rootActivity.uploadbdftp(ServerAddr, Integer.parseInt(ServerPort), ServerUser, ServerPass);

                    }
                });

                ydialog.show();
            }
        });
            
            
            
            
            


        Button DBcreateKeys = (Button) rootView.findViewById(R.id.buttongeneratekeys);
        DBcreateKeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

ad = new AlertDialog.Builder(container.getContext());
                ad.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_confirm, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                ad.setView(dialogView);
                final AlertDialog ydialog = ad.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                btn_negative.setText("Не генерировать");
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                btn_positive.setText("Генерировать");
                final TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Выполнить генерацию ключей PublicKey и PrivateKey? Если ключи существуют, они будут перезаписаны.");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ydialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        ydialog.cancel();
                
                try {

                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                    keyPairGenerator.initialize(2048);
                    KeyPair keyPair = keyPairGenerator.genKeyPair();
                    String publicKeyFilename = "PublicKey";
                    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
                    MainActivity rootActivity = (MainActivity) getActivity();
                    rootActivity.saveKey(publicKeyBytes, publicKeyFilename);
                    //rootActivity.saveKeySD(publicKeyBytes, publicKeyFilename); //для отладки ключей в рабочей директории

                    String privateKeyFilename = "PrivateKey";
                    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
                    //byte[] encryptedPrivateKeyBytes = passwordEncrypt(password.toCharArray(), privateKeyBytes);
                    rootActivity.saveKey(privateKeyBytes, privateKeyFilename);
                    //rootActivity.saveKeySD(privateKeyBytes, privateKeyFilename); //для отладки ключей в рабочей директории

                } catch (Exception ex) {
                    appActivity.Toasts(Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG),"show");
                }

                    }
                });

                ydialog.show();
            }
        });















        Button ImportKeys = (Button) rootView.findViewById(R.id.buttonimportkeys);
        ImportKeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            ad = new AlertDialog.Builder(container.getContext());
                ad.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_confirm, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                ad.setView(dialogView);
                final AlertDialog ydialog = ad.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                btn_negative.setText("Не импортировать");
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                btn_positive.setText("Импортировать");
                final TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Выполнить импорт (загрузку) PublicKey и PrivateKey в приложение с карты памяти?");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ydialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        ydialog.cancel();
                
                try {
                    MainActivity rootActivity = (MainActivity) getActivity();
                    rootActivity.importkeyfile("PrivateKey");
                    rootActivity.importkeyfile("PublicKey");

                    Toast.makeText(getActivity(), "Импорт ключей в приложение выполнен", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                }

                    }
                });

                ydialog.show();
            }
        });
            
            
            
            
            
            
            
            
            

            

        Button ExportKeys = (Button) rootView.findViewById(R.id.buttonexportkeys);
        ExportKeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

ad = new AlertDialog.Builder(container.getContext());
                ad.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_confirm, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                ad.setView(dialogView);
                final AlertDialog ydialog = ad.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                btn_negative.setText("Не экспортировать");
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                btn_positive.setText("Экспортировать");
                final TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Выполнить экспорт (выгрузку) файлов ключей PublicKey и PrivateKey из приложения на карту памяти? В случае, если файлы существуют, они будут перезаписаны.");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ydialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        ydialog.cancel();
                
                try {
                    MainActivity rootActivity = (MainActivity) getActivity();
                    rootActivity.exportkeyfile("PrivateKey");
                    rootActivity.exportkeyfile("PublicKey");

                    Toast.makeText(getActivity(), "Экспорт ключей в папку приложения на диске выполнен", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                }

                    }
                });

                ydialog.show();
            }
        });









                

        Button DBimportFTP = (Button) rootView.findViewById(R.id.buttonimportbdftp);
        DBimportFTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            
            ad = new AlertDialog.Builder(container.getContext());
                ad.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_confirm, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                ad.setView(dialogView);
                final AlertDialog ydialog = ad.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                btn_negative.setText("Не импортировать");
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                btn_positive.setText("Импортировать");
                final TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Выполнить импорт (загрузку) базы данных businessmanagement.db с удаленного FTP-сервера в приложение? База данных приложения будет перезаписана.");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ydialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        ydialog.cancel();
                
                String ServerAddr = TextViewServerAddr.getText().toString();
                String ServerUser = TextViewServerUser.getText().toString();
                String ServerPort = TextViewServerPort.getText().toString();
                String ServerPass = TextViewServerPass.getText().toString();
                try {
                    MainActivity rootActivity = (MainActivity) getActivity();
                    rootActivity.downloadbdftp(ServerAddr, Integer.parseInt(ServerPort), ServerUser, ServerPass);

                    Toast.makeText(getActivity(), "Импорт базы данных в приложение с удаленного FTP выполнен", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                }
                    }
                });

                ydialog.show();
            }
        });
            
            
            
            
            

                


        Button DBsaveFTP = (Button) rootView.findViewById(R.id.buttonsaveftpsettings);
        DBsaveFTP.setOnClickListener(new View.OnClickListener() {
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


                    //CRYPTOALGORITM.getSecurityProviders(); - получить список криптопровайдеров и алгоритмов в лог
                    //ПРОВЕРКА РАБОТОСПОСОБНОСТИ КРИПТОГРАФИИ
                    //try {
                    //String s1 = "Labor Room;!";
                    //String s2 = CRYPTOALGORITM.Encrypt(s1, pub_recovered);
                    //String s3 = CRYPTOALGORITM.Decrypt(s2, prv_recovered);
                    //Log.d("ru.mastercond", s1);
                    //Log.d("ru.mastercond", s2);
                    //Log.d("ru.mastercond", s3);
                    //}

                    //catch (Exception ex) {
                    //Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                    // }
                    //КОНЕЦ ПРОВЕРКА РАБОТОСПОСОБНОСТИ КРИПТОГРАФИИ


                    String ServerAddr = TextViewServerAddr.getText().toString();
                    String ServerUser = TextViewServerUser.getText().toString();
                    String ServerPort = TextViewServerPort.getText().toString();
                    String ServerPass = TextViewServerPass.getText().toString();
                    String ServerPassDB = CRYPTOALGORITM.Encrypt(ServerPass, pub_recovered);


                    DB = new SQLiteConnect(getActivity());
                    SQLiteDatabase db = DB.getReadableDatabase();
                    db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('2','FTPSERVER','" + ServerAddr + "');");
                    db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('3','FTPPORT','" + ServerPort + "');");
                    db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('4','FTPUSER','" + ServerUser + "');");
                    db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('5','FTPPASS','" + ServerPassDB + "');");

                    db.close();


                    Toast.makeText(getActivity(), "Данные FTP успешно записаны в базу", Toast.LENGTH_LONG).show();

                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


        return rootView;
    }

}
