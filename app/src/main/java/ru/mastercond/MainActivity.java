package ru.mastercond;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.ArrayList;

import ru.mastercond.MenuOpened;
import ru.mastercond.R;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SdelkaID;
import ru.mastercond.TovarID;
import ru.mastercond.AutoID;
import ru.mastercond.KontragentSName;
import ru.mastercond.SiteSName;
import ru.mastercond.fragment_KONTRAGENTI;
import ru.mastercond.fragment_MYFIRMS;
import ru.mastercond.fragment_MYFIRMS;
import ru.mastercond.fragment_NORMDOC;
import ru.mastercond.fragment_TOVARI;
import ru.mastercond.fragment_SAJTI;
import ru.mastercond.fragment_SDELKI;
import ru.mastercond.fragment_SDELKI_po_kontragentu;
import ru.mastercond.fragment_SETTINGS;
import ru.mastercond.fragment_SYNC;
import ru.mastercond.fragment_ZAMETKI;
import ru.mastercond.fragment_AUTOS;
import ru.mastercond.fragment_add_kontragent;
import ru.mastercond.fragment_add_myfirm;
import ru.mastercond.fragment_add_normdoc;
import ru.mastercond.fragment_add_normdoc;
import ru.mastercond.fragment_add_sdelka;
import ru.mastercond.fragment_add_zametka;
import ru.mastercond.fragment_add_zametka_home;
import ru.mastercond.fragment_add_tovar;
import ru.mastercond.fragment_add_site;
import ru.mastercond.fragment_add_sotrudnik;
import ru.mastercond.fragment_add_auto;
import ru.mastercond.fragment_edit_kontragent;
import ru.mastercond.fragment_edit_kontragent_home;
import ru.mastercond.fragment_edit_myfirm;
import ru.mastercond.fragment_edit_myfirm_home;
import ru.mastercond.fragment_edit_normdoc;
import ru.mastercond.fragment_edit_normdoc_home;
import ru.mastercond.fragment_edit_sdelka;
import ru.mastercond.fragment_edit_zametka;
import ru.mastercond.fragment_edit_zametka_home;
import ru.mastercond.fragment_edit_tovar;
import ru.mastercond.fragment_edit_site;
import ru.mastercond.fragment_mainpage;
import ru.mastercond.FTPsql;

import ru.mastercond.SimpleFileDialog;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class MainActivity extends Activity {

    SQLiteConnect DB;

    FragmentTransaction fTrans;


    fragment_add_kontragent fragaddkontragent;
    fragment_add_myfirm fragaddmyfirm;
    fragment_add_normdoc fraddnormdoc;
    fragment_add_zametka fraddzametka;
    fragment_add_zametka_home fraddzametkahome;
    fragment_add_sdelka fragaddsdelka;
    fragment_add_tovar fragaddtovar;
    fragment_add_site fragaddsite;
    fragment_add_sotrudnik fragaddsotrudnik;
    fragment_add_auto fragaddauto;

    fragment_edit_kontragent frageditkontragent;
    fragment_edit_myfirm frageditmyfirm;
    fragment_edit_myfirm_home frageditmyfirmhome;
    fragment_edit_normdoc frageditnormdoc;
    fragment_edit_normdoc_home frageditnormdochome;
    fragment_edit_zametka frageditzametka;
    fragment_edit_zametka_home frageditzametkahome;
    fragment_edit_kontragent_home frageditkontragenthome;
    fragment_edit_sdelka fragsdelka;
    fragment_edit_sdelka frsd;
    fragment_edit_tovar fragedittovar;
    fragment_edit_site frageditsite;


    fragment_ZAMETKI fragZAMETKI;
    fragment_NORMDOC fragNORMDOC;
    fragment_SDELKI frag1;
    fragment_SDELKI fragsdelki;
    fragment_SDELKI_po_kontragentu fragsdelkipokontragentu;
    fragment_SYNC fragsync;
    fragment_mainpage fragmain;
    fragment_mainpage fragmain1;
    fragment_SETTINGS fragsettings;
    fragment_KONTRAGENTI fragKONTRAGENTI;
    fragment_MYFIRMS fragMYFIRMS;
    fragment_SAJTI fragsajti;
    fragment_TOVARI fragtovari;
    fragment_AUTOS fragautos;
    fragment_AUTORASHODY fragautorashody;


    SdelkaID sdelkaid;
    TovarID tovarid;
    KontragentSName kontragentsname;
    SiteSName site;
    AutoID auto;

    FTPsql ftpsql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region ПЕРЕМЕННЫЕ

        FTPsql ftpsql = new FTPsql();

        DB = new SQLiteConnect(this);
        Toasts(new Toast(getApplicationContext()),"hide-all");

        final ScrollView fragArea = (ScrollView) findViewById(R.id.FragmentArea);
        final ImageButton menuButton = (ImageButton) findViewById(R.id.MenuButton);
        final ScrollView menuLayout = (ScrollView) findViewById(R.id.MenuLayout);
        final MenuOpened iMO = new MenuOpened();
        iMO.setMenuOpened(false);
        menuLayout.setVisibility(View.GONE);


        final Button FragmentButtonWorkTable = (Button) findViewById(R.id.MenuButton0);
        final Button FragmentButtonSdelki = (Button) findViewById(R.id.MenuButton1);
        final Button FragmentButtonKontragenti = (Button) findViewById(R.id.MenuButton2);
        final Button FragmentButtonMyOrgs = (Button) findViewById(R.id.MenuButton3);
        final Button FragmentButtonTovariUslugi = (Button) findViewById(R.id.MenuButton4);
        final Button FragmentButtonSotrudniki = (Button) findViewById(R.id.MenuButton5);
        final Button FragmentButtonMiniSklad = (Button) findViewById(R.id.MenuButton6);
        final Button FragmentButtonFinance = (Button) findViewById(R.id.MenuButton7);
        final Button FragmentButtonNORMDOC = (Button) findViewById(R.id.MenuButton8);
        final Button FragmentButtonSajti = (Button) findViewById(R.id.MenuButton9);
        final Button FragmentButtonSync = (Button) findViewById(R.id.MenuButton10);
        final Button FragmentButtonSettings = (Button) findViewById(R.id.MenuButton11);
        final Button FragmentButtonZametki = (Button) findViewById(R.id.MenuButton12);
        final Button FragmentButtonAUTOS = (Button) findViewById(R.id.MenuButton13);


        final Button FragmentButtonWorkTableUP = (Button) findViewById(R.id.UPMenuButton0);
        final Button FragmentButtonAddSdelka = (Button) findViewById(R.id.UPMenuButton1);
        final Button FragmentButtonAddKontragent = (Button) findViewById(R.id.UPMenuButton2);
        final Button FragmentButtonAddMyOrg = (Button) findViewById(R.id.UPMenuButton3);
        final Button FragmentButtonAddTovarUsluga = (Button) findViewById(R.id.UPMenuButton4);
        final Button FragmentButtonAddDelo = (Button) findViewById(R.id.UPMenuButton5);
        final Button FragmentButtonAddZametka = (Button) findViewById(R.id.UPMenuButton6);
        final Button FragmentButtonAddSotrudnik = (Button) findViewById(R.id.UPMenuButton7);


        //endregion

        //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isMenuOpened = iMO.getMenuOpened();

                if (isMenuOpened == false) {
                    opencloseMenu(false);
                    iMO.setMenuOpened(true);
                }

                if (isMenuOpened == true) {
                    opencloseMenu(true);
                    iMO.setMenuOpened(false);
                }

            }
        });


        //endregion

        //region ПЕРЕЙТИ НА ФРАГМЕНТ ГЛАВНАЯ СТРАНИЦА

        fragmain = new fragment_mainpage();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.FragmentArea, fragmain);
        fTrans.commit();

        //endregion

        //region КНОПКИ ВЕРХНЕГО И БОКОВОГО МЕНЮ ГЛАВНОЙ СТРАНИЦЫ

        FragmentButtonWorkTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmain1 = new fragment_mainpage();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragmain1);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonMyOrgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_MYFIRMS fragMYFIRMS = new fragment_MYFIRMS();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragMYFIRMS);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });

        FragmentButtonKontragenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_KONTRAGENTI fragKONTRAGENTI = new fragment_KONTRAGENTI();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragKONTRAGENTI);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_SETTINGS fragsettings = new fragment_SETTINGS();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsettings);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonSdelki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragsdelki = new fragment_SDELKI();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsdelki);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonAddMyOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragaddmyfirm = new fragment_add_myfirm();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddmyfirm);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonAddSdelka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragaddsdelka = new fragment_add_sdelka();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddsdelka);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonWorkTableUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragmain);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonAddKontragent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_add_kontragent fragaddkontragent = new fragment_add_kontragent();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddkontragent);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonAddZametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_add_zametka_home fragaddzametkahome = new fragment_add_zametka_home();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddzametkahome);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });

        FragmentButtonAddSotrudnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_add_sotrudnik fragaddsotrudnik = new fragment_add_sotrudnik();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddsotrudnik);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });



        FragmentButtonMyOrgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_MYFIRMS fragMYFIRMS = new fragment_MYFIRMS();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragMYFIRMS);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonNORMDOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_NORMDOC fragNORMDOC = new fragment_NORMDOC();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragNORMDOC);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonZametki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_ZAMETKI fragZAMETKI = new fragment_ZAMETKI();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragZAMETKI);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonSajti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_SAJTI fragsajti = new fragment_SAJTI();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsajti);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_SYNC fragsync = new fragment_SYNC();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsync);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonTovariUslugi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_TOVARI fragtovari = new fragment_TOVARI();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragtovari);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });




        FragmentButtonAddTovarUsluga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_add_tovar fragaddtovar = new fragment_add_tovar();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddtovar);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });


        FragmentButtonAUTOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_AUTOS fragautos = new fragment_AUTOS();

                fTrans = getFragmentManager().beginTransaction();
                fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragautos);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });

        //endregion

    }


    //region ФУНКЦИЯ ОТКРЫТИЯ_ЗАКРЫТИЯ БОКОВОГО МЕНЮ

    public void opencloseMenu(Boolean isMenuOpened) {

        MenuOpened iM1 = new MenuOpened();

        final ScrollView menuLayout = (ScrollView) findViewById(R.id.MenuLayout);


        if (isMenuOpened == false) {
            menuLayout.setVisibility(View.VISIBLE);
            iM1.setMenuOpened(true);
        }

        if (isMenuOpened == true) {
            menuLayout.setVisibility(View.GONE);
            iM1.setMenuOpened(false);
        }

    }

    //endregion

    //region ФУНКЦИЯ ДОБАВЛЕНИЯ И ПОСЛЕДУЮЩЕГО СКРЫТИЯ TOAST

    public void Toasts(Toast toast, String command) {

        ArrayList<Toast> msjsToast = new ArrayList<Toast>();

        msjsToast.add(toast);

        if (command.equals("show")) {
            toast.show();
        }

        if (command.equals("hide-all")) {
            for(Toast toasts:msjsToast) {
                if(toasts!=null) {
                    toasts.cancel();
                }
            }
            msjsToast.clear();
        }

    }

    //endregion


    //region ФУНКЦИЯ КОПИРОВАТЬ ФАЙЛ copyFile(String inputPath, String inputFile, String outputFile, String outputPath)

    private void copyFile(String inputPath, String inputFile, String outputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + outputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        } catch (FileNotFoundException fnfe1) {
            Toasts(Toast.makeText(getApplicationContext(), fnfe1.toString(), Toast.LENGTH_LONG),"show");
        } catch (Exception e) {
            Toasts(Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG),"show");
        }

    }

    //endregion


    //region ФУНКЦИЯ ПЕРЕЙТИ НА ГЛАВНУЮ СТРАНИЦУ gohome()

    public void gohome() {
        fragment_mainpage frhome = new fragment_mainpage();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frhome);
        fTrans.commit();

    }

//endregion



    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ СДЕЛКУ addsdelka()

    public void addsdelka() {

        fragment_add_sdelka fragaddsdelka = new fragment_add_sdelka();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddsdelka);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

//endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННУЮ СДЕЛКУ editsdelka(String S)

    public void editsdelka(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_sdelka frsd = new fragment_edit_sdelka();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frsd);
        fTrans.addToBackStack(null);
        fTrans.commit();
        //Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

//endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА СТРАНИЦУ СО СПИСКОМ СДЕЛОК sdelkiclose()

    public void sdelkiclose() {
        fragment_SDELKI fragsdelki = new fragment_SDELKI();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsdelki);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

//endregion



    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ КОНТРАГЕНТА addkontragent()

    public void addkontragent() {
        fragment_add_kontragent fragaddkontragent = new fragment_add_kontragent();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddkontragent);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

//endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННОГО КОНТРАГЕНТА editkontragent(String S)

    public void editkontragent(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_kontragent frageditkontragent = new fragment_edit_kontragent();


        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditkontragent);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

//endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННОГО КОНТРАГЕНТА С ГЛАВНОЙ СТАНИЦЫ editkontragenthome(String S)

    public void editkontragenthome(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_kontragent_home frageditkontragenthome = new fragment_edit_kontragent_home();


        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditkontragenthome);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

//endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ СПИСОК КОНТРАГЕНТОВ closekontragent()

    public void closekontragent() {
        fragment_KONTRAGENTI fragKONTRAGENTI = new fragment_KONTRAGENTI();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragKONTRAGENTI);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ГЛАВНУЮ closekontragenthome()

    public void closekontragenthome() {
        fragment_mainpage fragmain = new fragment_mainpage();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragmain);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion



    //region ФУНКЦИЯ РЕДАКТИРОВАТЬ ВЫБРАННУЮ СДЕЛКУ myfirmdetail(String S)

    public void myfirmdetail(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_sdelka frsd = new fragment_edit_sdelka();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frsd);
        fTrans.addToBackStack(null);
        fTrans.commit();
        Toasts(Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG),"show");

    }

    //endregion



    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ МОЮ ОРГАНИЗАЦИЮ addmyfirm()

    public void addmyfirm() {
        fragment_add_myfirm fragaddmyfirm = new fragment_add_myfirm();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddmyfirm);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }
    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННУЮ МОЮ ОРГАНИЗАЦИЮ editmyfirm(String S)

    public void editmyfirm(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_myfirm frageditmyfirm = new fragment_edit_myfirm();


        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditmyfirm);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

    //endregion

    //region ФУНКЦИЯ РЕДАКТИРОВАТЬ ВЫБРАННУЮ МОЮ ОРГАНИЗАЦИЮ С ГЛАВНОЙ СТРАНИЦЫ editmyfirmhome(String S)

    public void editmyfirmhome(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_myfirm_home frageditmyfirmhome = new fragment_edit_myfirm_home();


        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditmyfirmhome);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ СО СПИСКОМ МОИХ ОРГАНИЗАЦИЙ closemyfirm()

    public void closemyfirm() {
        fragment_MYFIRMS fragMYFIRMS = new fragment_MYFIRMS();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragMYFIRMS);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ГЛАВНУЮ СТРАНИЦУ closemyfirmhome()

    public void closemyfirmhome() {
        fragment_mainpage fragmain = new fragment_mainpage();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragmain);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion



    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ НОРМАТИВНЫЙ ДОКУМЕНТ addnormdoc()

    public void addnormdoc() {
        fragment_add_normdoc fraddnormdoc = new fragment_add_normdoc();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fraddnormdoc);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННЫЙ НОРМАТИВНЫЙ ДОКУМЕНТ editnormdoc(String S)

    public void editnormdoc(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_normdoc freditnormdoc = new fragment_edit_normdoc();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, freditnormdoc);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННЫЙ НОРМАТИВНЫЙ ДОКУМЕНТ С ГЛАВНОЙ СТРАНИЦЫ editnormdochome(String S)

    public void editnormdochome(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_normdoc_home freditnormdoc = new fragment_edit_normdoc_home();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, freditnormdoc);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ГЛАВНУЮ СТРАНИЦУ closenormdoc()

    public void closenormdoc() {
        fragment_NORMDOC frnormdoc = new fragment_NORMDOC();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frnormdoc);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion



    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ ЗАМЕТКУ addzametka()

    public void addzametka() {
        fragment_add_zametka fraddzametka = new fragment_add_zametka();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fraddzametka);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННУЮ ЗАМЕТКУ editzametka(String S)

    public void editzametka(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_zametka frageditzametka = new fragment_edit_zametka();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditzametka);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННУЮ ЗАМЕТКУ С ГЛАВНОЙ СТРАНИЦЫ editzametkahome(String S)

    public void editzametkahome(String S) {
        sdelkaid = new SdelkaID();
        sdelkaid.setSdelkaID(S);

        fragment_edit_zametka_home frageditzametkahome = new fragment_edit_zametka_home();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditzametkahome);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ СПИСОК ЗАМЕТОК closezametka()

    public void closezametka() {
        fragment_ZAMETKI frzametki = new fragment_ZAMETKI();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frzametki);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ГЛАВНУЮ СТРАНИЦУ closezametkahome()

    public void closezametkahome() {
        fragment_mainpage fragmain = new fragment_mainpage();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragmain);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion



    //region ФУНКЦИЯ ПЕРЕЙТИ НА СТРАНИЦУ СО СПИСКОМ ТОВАРОВ tovarclose()

    public void tovarclose() {
        fragment_TOVARI fragtovari = new fragment_TOVARI();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragtovari);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ ТОВАР addtovar()

    public void addtovar() {

        fragment_add_tovar fragaddtovar = new fragment_add_tovar();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddtovar);
        fTrans.addToBackStack(null);
        fTrans.commit();
    }

//endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ ВЫБРАННЫЙ ТОВАР edittovar(String S)

    public void edittovar(String S) {
        tovarid = new TovarID();
        tovarid.setTovarID(S);

        fragment_edit_tovar fragedittovar = new fragment_edit_tovar();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragedittovar);
        fTrans.addToBackStack(null);
        fTrans.commit();
        //Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

//endregion



    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ СО СДЕЛКАМИ ПО ВЫБРАННОМУ КОНТРАГЕНТУ SdelkiPoImeniKontragenta(String S)

    public void SdelkiPoImeniKontragenta(String S) {
        kontragentsname = new KontragentSName();
        kontragentsname.setKontragentSName(S);

        fragment_SDELKI_po_kontragentu fragsdelkipokontragentu = new fragment_SDELKI_po_kontragentu();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsdelkipokontragentu);
        fTrans.addToBackStack(null);
        fTrans.commit();
        // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();

    }

    //endregion


    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ САЙТ addsite()

    public void addsite() {
        fragment_add_site fragaddsite = new fragment_add_site();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddsite);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ РЕДАКТИРОВАТЬ САЙТ editsite()

    public void editsite(String S) {
        site = new SiteSName();
        site.setSiteSName(S);

        fragment_edit_site frageditsite = new fragment_edit_site();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditsite);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ АВТО addauto()

    public void addauto() {
        fragment_add_auto fragaddauto = new fragment_add_auto();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddauto);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ ДОБАВИТЬ АВТО editauto()

    public void editauto(String S) {
        fragment_edit_auto frageditauto = new fragment_edit_auto();
        auto = new AutoID();
        auto.setAutoID(S);

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditauto);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion

    //region ФУНКЦИЯ ПЕРЕЙТИ НА ФРАГМЕНТ СПИСОК АВТО autoslist()

    public void autoslist() {
        fragment_AUTOS fragautos = new fragment_AUTOS();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragautos);
        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    //endregion



    //region ФУНКЦИЯ ЭКСПОРТ ФАЙЛА БД НА SDCARD ИЗ ПРИЛОЖЕНИЯ

    public void exportbd() {
        DB = new SQLiteConnect(this);

        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================
        Context CN = getApplicationContext();
        String dbfilePath = CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement1.db
        String dbfileOUTPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/"; //получить путь до /storage/sdcard0 + НУЖНО android.permission.WRITE_EXTERNAL_STORAGE в MANIFEST!!!

        copyFile(dbfilePath, "businessmanagement.db", "businessmanagement.db", dbfileOUTPath); //скопировать файл базы данных для отладки

        //  Toast. makeText(CN, dbfilePath, Toast.LENGTH_LONG). show();

        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================

    }

    //endregion

    //region ФУНКЦИЯ ИМПОРТ ФАЙЛА БД С SDCARD В ПРИЛОЖЕНИЕ

    public void importbd() {

        DB = new SQLiteConnect(this);

        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================
        Context CN = getApplicationContext();

        String dbfilePath = CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement.db
        String dbfileINPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/";
        copyFile(dbfileINPath, "businessmanagement.db", "businessmanagement.db", dbfilePath); //импортировать файл базы данных


        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================

    }

    //endregion

    //region ФУНКЦИЯ ВЫГРУЗИТЬ ФАЙЛ БД НА FTP

    public void uploadbdftp(final String Server, final int Port, final String User, final String Pass) {
        DB = new SQLiteConnect(this);
        final FTPsql ftpsql = new FTPsql();

        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================
        Context CN = getApplicationContext();
        String dbfilePath = CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement1.db
        final String dbfileOUTPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/"; //получить путь до /storage/sdcard0 + НУЖНО android.permission.WRITE_EXTERNAL_STORAGE в MANIFEST!!!

        copyFile(dbfilePath, "businessmanagement.db", "businessmanagement.db", dbfileOUTPath); //скопировать файл базы данных для отладки

        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    ftpsql.UploadDatabase(Server, Port, User, Pass, dbfileOUTPath + "businessmanagement.db", "businessmanagement.db");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasts(Toast.makeText(getApplicationContext(), "База данных успешно выгружена на сервер " + Server, Toast.LENGTH_SHORT),"show");
                        }
                    });

                } catch (final Exception exx1) {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasts(Toast.makeText(getApplicationContext(), exx1.toString(), Toast.LENGTH_LONG),"show");
                        }
                    });
                }


            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        //  Toast. makeText(CN, dbfilePath, Toast.LENGTH_LONG). show();


        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================

    }

    //endregion

    //region ФУНКЦИЯ СКАЧАТЬ ФАЙЛ БД С FTP И ЗАМЕНИТЬ В ПРИЛОЖЕНИИ

    public void downloadbdftp(final String Server, final int Port, final String User, final String Pass) {

        DB = new SQLiteConnect(this);

        Runnable runnable = new Runnable() {
            public void run() {


                final FTPsql ftpsql = new FTPsql();

                //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================

                Context CN = getApplicationContext();

                final String dbfilePath = CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement.db
                final String dbfileINPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/";

                ftpsql.DownloadDatabase(Server, Port, User, Pass, dbfileINPath + "businessmanagement.db", "businessmanagement.db");
                copyFile(dbfileINPath, "businessmanagement.db", "businessmanagement.db", dbfilePath); //импортировать файл базы данных


                //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    //endregion

    //region ФУНКЦИЯ ВЫГРУЗИТЬ ФАЙЛ БД НА FTPS

    public void uploadbdftps(final String Server, final int Port, final String User, final String Pass) {
        DB = new SQLiteConnect(this);
        final FTPsql ftpsql = new FTPsql();

        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================
        Context CN = getApplicationContext();
        String dbfilePath = CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement1.db
        final String dbfileOUTPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/"; //получить путь до /storage/sdcard0 + НУЖНО android.permission.WRITE_EXTERNAL_STORAGE в MANIFEST!!!

        copyFile(dbfilePath, "businessmanagement.db", "businessmanagement.db", dbfileOUTPath); //скопировать файл базы данных для отладки

        Runnable runnable = new Runnable() {
            public void run() {
                ftpsql.UploadDatabaseSecure(Server, Port, User, Pass, dbfileOUTPath + "businessmanagement.db", "businessmanagement.db");

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        //  Toast. makeText(CN, dbfilePath, Toast.LENGTH_LONG). show();

        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================


    }

    //endregion

    //region ФУНКЦИЯ СКАЧАТЬ ФАЙЛ БД С FTPS И ЗАМЕНИТЬ В ПРИЛОЖЕНИИ

    public void downloadbdftps(final String Server, final int Port, final String User, final String Pass) {
        DB = new SQLiteConnect(this);

        Runnable runnable = new Runnable() {
            public void run() {

                final FTPsql ftpsql = new FTPsql();

                //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================
                Context CN = getApplicationContext();

                final String dbfilePath = CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement.db
                final String dbfileINPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/";
                ftpsql.DownloadDatabaseSecure(Server, Port, User, Pass, dbfileINPath + "businessmanagement.db", "businessmanagement.db");
                copyFile(dbfileINPath, "businessmanagement.db", "businessmanagement.db", dbfilePath); //импортировать файл базы данных


                //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    //endregion


    //region ФУНКЦИЯ ВЫГРУЗИТЬ PHP ФАЙЛ НА FTP

    public void uploadPHPftp(final String Server, final int Port, final String User, final String Pass) {
        DB = new SQLiteConnect(this);
        final FTPsql ftpsql = new FTPsql();

        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================
        Context CN = getApplicationContext();
        String dbfilePath = CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement1.db
        final String dbfileOUTPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/"; //получить путь до /storage/sdcard0 + НУЖНО android.permission.WRITE_EXTERNAL_STORAGE в MANIFEST!!!

        copyFile(dbfilePath, "businessmanagement.db", "businessmanagement.db", dbfileOUTPath); //скопировать файл базы данных для отладки

        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    ftpsql.UploadDatabase(Server, Port, User, Pass, dbfileOUTPath + "businessmanagement.db", "businessmanagement.db");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasts(Toast.makeText(getApplicationContext(), "База данных успешно выгружена на сервер " + Server, Toast.LENGTH_SHORT),"show");
                        }
                    });

                } catch (final Exception exx1) {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toasts(Toast.makeText(getApplicationContext(), exx1.toString(), Toast.LENGTH_LONG),"show");
                        }
                    });
                }


            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        //  Toast. makeText(CN, dbfilePath, Toast.LENGTH_LONG). show();


        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================

    }

    //endregion



    //region ФУНКЦИЯ СОХРАНИТЬ СГЕНЕРИРОВАННЫЙ html-файл В ПАПКЕ ПРИЛОЖЕНИЯ


    public void savehtmlFile(String FolderName, String SubFolderName, String FileName, String str) {

        try {

            File folderPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/" + FolderName + "/" + SubFolderName + "/");
            if (!folderPath.exists()) {
                folderPath.mkdirs();
            }

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/" + FolderName + "/" + SubFolderName + "/";
            File file = new File(filePath + FileName);
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);

            stream.write(str.getBytes());
            stream.close();

            Toasts(Toast.makeText(getApplicationContext(), "Документ " + FileName + " успешно сохранен в папке " + FolderName, Toast.LENGTH_LONG),"show");
        } catch (Exception e) {
            Log.d("ru.mastercond", e.toString());
            Toasts(Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG),"show");
        }

    }

    //endregion

    //region ФУНКЦИЯ СОХРАНИТЬ СГЕНЕРИРОВАННЫЙ PHP-файл В ПАПКЕ ПРИЛОЖЕНИЯ САЙТА


    public void savePHPFile(String FolderName, String SubFolderName, String FileName, String str) {

        try {

            File folderPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/WEB-САЙТЫ/" + FolderName + "/products/" + SubFolderName + "/");
            if (!folderPath.exists()) {
                folderPath.mkdirs();
            }

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/WEB-САЙТЫ/" + FolderName + "/products/" + SubFolderName + "/";
            File file = new File(filePath + FileName);
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);

            stream.write(str.getBytes());
            stream.close();

            Toasts(Toast.makeText(getApplicationContext(), " PHP-файл " + FileName + " успешно сохранен в папке " + FolderName, Toast.LENGTH_LONG),"show");
        } catch (Exception e) {
            Log.d("ru.mastercond", e.toString());
            Toasts(Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG),"show");
        }

    }

    //endregion

    //region ФУНКЦИЯ СОХРАНИТЬ ЛОГ В ПАПКЕ ПРИЛОЖЕНИЯ


    public void saveLogFile(String FileName, String str) {

        try {

            File folderPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/ЛОГИ ОШИБОК/");
            if (!folderPath.exists()) {
                folderPath.mkdirs();
            }

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/ЛОГИ ОШИБОК/";
            File file = new File(filePath + FileName);
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);

            stream.write(str.getBytes());
            stream.close();


        } catch (Exception e) {
            Log.d("ru.mastercond", e.toString());
            Toasts(Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG),"show");
        }

    }

    //endregion


    //region ФУНКЦИЯ СОХРАНИТЬ ФАЙЛ КЛЮЧА ВО ВНУТРЕННЕЙ ПАПКЕ ПРИЛОЖЕНИЯ (ЭКСПОРТ ФАЙЛА КЛЮЧА ИЗ ПЕРЕМЕННОЙ)

    public void saveKey(byte[] KeyBytes, String FileName) {

        try {


            FileOutputStream stream = new FileOutputStream(getApplicationContext().getFilesDir() + "/" + FileName);

            stream.write(KeyBytes);
            stream.close();

            Toasts(Toast.makeText(getApplicationContext(), "Ключ " + FileName + " успешно сохранен", Toast.LENGTH_SHORT),"show");
        } catch (Exception e) {
            Log.d("ru.mastercond", e.toString());
            Toasts(Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG),"show");
        }

    }

    //endregion

    //region ФУНКЦИЯ ПОЛУЧИТЬ КЛЮЧ ИЗ ФАЙЛА ВО ВНУТРЕННЕЙ ПАПКЕ ПРИЛОЖЕНИЯ (ИМПОРТ ФАЙЛА КЛЮЧА В ПЕРЕМЕННУЮ)

    public byte[] getKey(String FileName) {

        File file = new File(getApplicationContext().getFilesDir() + "/" + FileName);
        byte[] KeyBytes = new byte[(int) file.length()];

        try {

            //File file = new File(getApplicationContext().getFilesDir()+"/"+FileName);
            FileInputStream stream = new FileInputStream(getApplicationContext().getFilesDir() + "/" + FileName);

            stream.read(KeyBytes);
            stream.close();

            // Toast.makeText(getApplicationContext(), "Ключ "+FileName+" успешно прочитан",  Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Log.d("ru.mastercond", e.toString());
            Toasts(Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG),"show");
        }


        return KeyBytes;
    }

    //endregion

    //region ФУНКЦИЯ СОХРАНИТЬ ФАЙЛ КЛЮЧА В ПАПКУ ПРИЛОЖЕНИЯ НА SDCARD (ЭКСПОРТ ФАЙЛА КЛЮЧА ИЗ ПЕРЕМЕННОЙ)

    public void saveKeySD(byte[] KeyBytes, String FileName) {

        try {

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/";

            FileOutputStream stream = new FileOutputStream(filePath + "/" + FileName);

            stream.write(KeyBytes);
            stream.close();

            Toasts(Toast.makeText(getApplicationContext(), "Ключ " + FileName + " успешно сохранен", Toast.LENGTH_SHORT),"show");
        } catch (Exception e) {
            Log.d("ru.mastercond", e.toString());
            Toasts(Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG),"show");
        }

    }

    //endregion

    //region ФУНКЦИЯ ПОЛУЧИТЬ (ИМПОРТИРОВАТЬ) КЛЮЧ ИЗ ФАЙЛА В ПАПКЕ ПРИЛОЖЕНИЯ НА SDCARD (КОПИРОВАНИЕ СУЩЕСТВУЮЩЕГО ФАЙЛА КЛЮЧА С SDCARD ВО ВНУТРЕННЮЮ ПАПКУ ПРИЛОЖЕНИЯ)

    public void importkeyfile(String keyfile) {


        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА КЛЮЧА В ПРИЛОЖЕНИЕ=================


        String keyfileINPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/";
        String keyfileOUTPath = getApplicationContext().getFilesDir().toString() + "/";
        copyFile(keyfileINPath, keyfile, keyfile, keyfileOUTPath); //импортировать файл ключа


        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА КЛЮЧА В ПРИЛОЖЕНИЕ=================

    }

    //endregion

    //region ФУНКЦИЯ ЭКСПОРТ КЛЮЧА ИЗ ВНУТРЕННЕЙ ПАПКИ ПРИЛОЖЕНИЯ НА SDCARD

    public void exportkeyfile(String keyfile) {


        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА КЛЮЧА ИЗ ПРИЛОЖЕНИЯ НА ДИСК=================

        String keyfileINPath = getApplicationContext().getFilesDir().toString() + "/";
        String keyfileOUTPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Управление.бизнесом/";
        copyFile(keyfileINPath, keyfile, keyfile, keyfileOUTPath); //экспортировать файл ключа


        //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА КЛЮЧА ИЗ ПРИЛОЖЕНИЯ НА ДИСК=================

    }

    //endregion



    //region ФУНКЦИЯ ПОЛУЧИТЬ ID сделки getsdelkaid()

    public String getsdelkaid() {
        return sdelkaid.getSdelkaID();
    }

    //endregion

    //region ФУНКЦИЯ ПОЛУЧИТЬ ID товара gettovarid()

    public String gettovarid() {
        return tovarid.getTovarID();
    }

    //endregion

    //region ФУНКЦИЯ ПОЛУЧИТЬ СОКР ИМЯ КОНТРАГЕНТА getkontragentsokrname()

    public String getkontragentsokrname() {
        return kontragentsname.getKontragentSName();
    }

    //endregion

    //region ФУНКЦИЯ ПОЛУЧИТЬ ID сайта getsiteid()

    public String getsiteid() { return site.getSiteSName(); }

    //endregion

    //region ФУНКЦИЯ ПОЛУЧИТЬ ID авто getautoid()

    public String getautoid() { return auto.getAutoID(); }

    //endregion


}
