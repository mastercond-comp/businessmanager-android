package ru.mastercond;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import android.view.View.OnTouchListener;
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

import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import ru.mastercond.NORMDOC;
import ru.mastercond.NORMDOCListAdapter;
import ru.mastercond.ZAMETKI;
import ru.mastercond.ZAMETKIListAdapter;
import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;
import ru.mastercond.fragment_KONTRAGENTI;
import ru.mastercond.fragment_MYFIRMS;
import ru.mastercond.SetDynamicHeightListView;
import ru.mastercond.TextStr;

import ru.mastercond.SdelkaID;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView.OnItemClickListener;

import ru.mastercond.SQLiteConnect;
import ru.mastercond.MainActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;

import java.lang.ClassCastException;


public class fragment_mainpage extends Fragment {

    private SQLiteConnect DB;
    private ListView ListViewNORMDOC;
    private ArrayList<NORMDOC> list;

    private ListView ListViewSdelki;
    private ArrayList<Sdelki> listkontragenti;

    private ListView ListViewMYFIRMS;
    private ArrayList<Sdelki> listkontragenti1;

    private TextStr SummStr;
    private TextStr SummTovariStr;
    private TextStr SummUslugiStr;

    private TextStr VSummStr;
    private TextStr VSummTovariStr;
    private TextStr VSummUslugiStr;


    public fragment_mainpage() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_mainpage, container, false);

        //region ПЕРЕМЕННЫЕ

        final MainActivity appActivity = (MainActivity) getActivity();
        appActivity.Toasts(new Toast(getActivity()), "hide-all"); //скрыть все Toast

        final ListView ListViewSdelkiVRabote = (ListView) rootView.findViewById(R.id.ListViewSDELKIVRABOTE);

        final ListView ListViewSdelkiPredlozhenie = (ListView) rootView.findViewById(R.id.ListViewSDELKIEND);


        final LinearLayout LLSdelkivrabote = (LinearLayout) rootView.findViewById(R.id.LLSdelkiVRabote);

        final LinearLayout LLSdelanoPredlozhenije = (LinearLayout) rootView.findViewById(R.id.LLSdelanoPredlozhenie);

        final LinearLayout LLSvodnieDannije = (LinearLayout) rootView.findViewById(R.id.LLSvodnieDannie);

        final LinearLayout LLtSotrudniki = (LinearLayout) rootView.findViewById(R.id.LLSotrudniki);

        final LinearLayout LLtMiniSklad = (LinearLayout) rootView.findViewById(R.id.LLMiniSklad);

        final LinearLayout LLtPotencClients = (LinearLayout) rootView.findViewById(R.id.LLPotencClients);

        final LinearLayout LLtFinance = (LinearLayout) rootView.findViewById(R.id.LLFinance);

        final LinearLayout LLtSdelkiSProblemami = (LinearLayout) rootView.findViewById(R.id.LLSdelkiSProblemami);

        final LinearLayout LLtCalendar = (LinearLayout) rootView.findViewById(R.id.LLCalendar);

        final LinearLayout LLtNormDok = (LinearLayout) rootView.findViewById(R.id.LLNormDok);

        final LinearLayout LLtRashodi = (LinearLayout) rootView.findViewById(R.id.LLRashodi);

        final LinearLayout LLtAvto = (LinearLayout) rootView.findViewById(R.id.LLAvto);

        final LinearLayout LLtOsnovnieSredstva = (LinearLayout) rootView.findViewById(R.id.LLOsnovnieSredstva);

        final LinearLayout LLtZametki = (LinearLayout) rootView.findViewById(R.id.LLZametki);

        final LinearLayout LLtKontragenti = (LinearLayout) rootView.findViewById(R.id.LLKontragenti);

        final LinearLayout LLtMyOrgs = (LinearLayout) rootView.findViewById(R.id.LLMyOrgs);

        final LinearLayout LLtSites = (LinearLayout) rootView.findViewById(R.id.LLSites);

        final LinearLayout LLtVoronkaProdazh = (LinearLayout) rootView.findViewById(R.id.LLVoronkaProdazh);

        final LinearLayout LLtMail = (LinearLayout) rootView.findViewById(R.id.LLMail);

        final LinearLayout LLtDolzhniki = (LinearLayout) rootView.findViewById(R.id.LLDolzhniki);

        final TextView SvodnieDannieKolvoSdelok = (TextView) rootView.findViewById(R.id.svodniedannie_oks);
        final TextView SvodnieDannieSumm = (TextView) rootView.findViewById(R.id.svodniedannie_summasdelok);
        final TextView SvodnieDannieSummTovarov = (TextView) rootView.findViewById(R.id.svodniedannie_summtovarov);
        final TextView SvodnieDannieSummUslug = (TextView) rootView.findViewById(R.id.svodniedannie_summuslug);

        final TextView VoronkaVRabote = (TextView) rootView.findViewById(R.id.voronkavrabote);
        final TextView VoronkaSoglasovanie = (TextView) rootView.findViewById(R.id.voronkasoglasovanie);
        final TextView VoronkaOtkazi = (TextView) rootView.findViewById(R.id.voronkaotkazi);
        final TextView VoronkaPredlozhenija = (TextView) rootView.findViewById(R.id.voronkapredlozhenija);

        final TextView VoronkaProc1 = (TextView) rootView.findViewById(R.id.vproc1);
        final TextView VoronkaProc11 = (TextView) rootView.findViewById(R.id.vproc11);
        final TextView VoronkaProc111 = (TextView) rootView.findViewById(R.id.vproc111);
        final TextView VoronkaProc2 = (TextView) rootView.findViewById(R.id.vproc2);
        final TextView VoronkaProc22 = (TextView) rootView.findViewById(R.id.vproc22);
        final TextView VoronkaProc3 = (TextView) rootView.findViewById(R.id.vproc3);
        final TextView VoronkaProc33 = (TextView) rootView.findViewById(R.id.vproc33);
        final TextView VoronkaProc4 = (TextView) rootView.findViewById(R.id.vproc4);
        final TextView VoronkaProc44 = (TextView) rootView.findViewById(R.id.vproc44);

        final Button MYSDELKIlistfull = (Button) rootView.findViewById(R.id.butonsdelkivrabotelist_full);

        final Button MYSDELKIlistfull2 = (Button) rootView.findViewById(R.id.butonsdelkivrabotelist_full_2);

        final Button RaskritieSdelkiVRabote = (Button) rootView.findViewById(R.id.buttonraskritiesdelkivrabote);

        final Button RaskritieSdelanoPredlozhenie = (Button) rootView.findViewById(R.id.buttonraskritiesdelanopredlozhenie);


        final Button RaskritieSvodnieDannie = (Button) rootView.findViewById(R.id.buttonraskritiesvodniedannie);

        final Button RaskritieSotrudniki = (Button) rootView.findViewById(R.id.buttonraskritiesotrudniki);

        final Button RaskritieSklad = (Button) rootView.findViewById(R.id.buttonraskritiesklad);

        final Button RaskritiePotencClients = (Button) rootView.findViewById(R.id.buttonraskritiepotencclients);

        final Button RaskritieVoronkaProdazh = (Button) rootView.findViewById(R.id.buttonraskritievoronkaprodazh);

        final Button RaskritieFinance = (Button) rootView.findViewById(R.id.buttonraskritiefinance);

        final Button RaskritieSdelkiSProblemami = (Button) rootView.findViewById(R.id.buttonraskritiesdelkisproblemami);

        final Button RaskritieKalendar = (Button) rootView.findViewById(R.id.buttonraskritiedelovojkalendar);

        final Button RaskritieDolzhniki = (Button) rootView.findViewById(R.id.buttonraskritiedolzhniki);

        final Button RaskritieNormDok = (Button) rootView.findViewById(R.id.buttonraskritienormdoc);

        final Button RaskritieRasxody = (Button) rootView.findViewById(R.id.buttonraskritierashody);

        final Button RaskritieKorrespondence = (Button) rootView.findViewById(R.id.buttonraskritiekorrespondence);

        final Button RaskritieOsnovnieSredstva = (Button) rootView.findViewById(R.id.buttonraskritieosnovniesredstva);

        final Button RaskritieZametki = (Button) rootView.findViewById(R.id.buttonraskritiezametki);

        final Button RaskritieKontragenti = (Button) rootView.findViewById(R.id.buttonraskritiekontragenti);

        final Button RaskritieMyOrgs = (Button) rootView.findViewById(R.id.buttonraskritiemyorgs);

        final Button RaskritieSajti = (Button) rootView.findViewById(R.id.buttonraskritiesajti);

        final Button RaskritieAvto = (Button) rootView.findViewById(R.id.buttonraskritieavto);

        //endregion

        //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

        LinearLayout fragRoot = (LinearLayout) rootView.findViewById(R.id.FragmentRoot);
        fragRoot.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            MainActivity rootActivity = (MainActivity) getActivity();
                                            rootActivity.opencloseMenu(true);
                                        }
                                    }
        );
//endregion

        //region СКРЫТЬ ВСЕ TOAST
        Toast t0 = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        appActivity.Toasts(t0, "hide-all");
//endregion

        //region ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ

        DB = new SQLiteConnect(getActivity());

        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null);
            cursor.moveToNext(); //без этого exception
            String result = cursor.getString(2);

            if (result.equals("PHONE")) {
                LinearLayout Line1 = (LinearLayout) rootView.findViewById(R.id.LLLine1);
                LinearLayout Line2 = (LinearLayout) rootView.findViewById(R.id.LLLine2);
                LinearLayout Line3 = (LinearLayout) rootView.findViewById(R.id.LLLine3);
                LinearLayout Line4 = (LinearLayout) rootView.findViewById(R.id.LLLine4);
                LinearLayout Line5 = (LinearLayout) rootView.findViewById(R.id.LLLine5);

                Line1.setOrientation(LinearLayout.VERTICAL);
                Line2.setOrientation(LinearLayout.VERTICAL);
                Line3.setOrientation(LinearLayout.VERTICAL);
                Line4.setOrientation(LinearLayout.VERTICAL);
                Line5.setOrientation(LinearLayout.VERTICAL);

                ListViewSdelkiPredlozhenie.setVisibility(View.GONE);

                LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) Line1.getLayoutParams();
                LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) Line2.getLayoutParams();
                LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) Line3.getLayoutParams();
                LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) Line4.getLayoutParams();
                LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) Line5.getLayoutParams();

                params1.width = (int) (355 * (getResources().getDisplayMetrics().density));
                params2.width = (int) (355 * (getResources().getDisplayMetrics().density));
                params3.width = (int) (355 * (getResources().getDisplayMetrics().density));
                params4.width = (int) (355 * (getResources().getDisplayMetrics().density));
                params5.width = (int) (355 * (getResources().getDisplayMetrics().density));

                Line1.setLayoutParams(params1);
                Line2.setLayoutParams(params2);
                Line3.setLayoutParams(params3);
                Line4.setLayoutParams(params4);
                Line5.setLayoutParams(params5);

                MYSDELKIlistfull.setVisibility(View.GONE);
                MYSDELKIlistfull2.setVisibility(View.GONE);

                LinearLayout.LayoutParams params6 = (LinearLayout.LayoutParams) LLSdelkivrabote.getLayoutParams();
                params6.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLSdelkivrabote.setLayoutParams(params6);

                LinearLayout.LayoutParams params7 = (LinearLayout.LayoutParams) LLSdelanoPredlozhenije.getLayoutParams();
                params7.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLSdelanoPredlozhenije.setLayoutParams(params7);

                LinearLayout.LayoutParams params8 = (LinearLayout.LayoutParams) LLSvodnieDannije.getLayoutParams();
                params8.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLSvodnieDannije.setLayoutParams(params8);


                LinearLayout.LayoutParams params9 = (LinearLayout.LayoutParams) LLtSotrudniki.getLayoutParams();
                params9.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtSotrudniki.setLayoutParams(params9);

                LinearLayout.LayoutParams params10 = (LinearLayout.LayoutParams) LLtMiniSklad.getLayoutParams();
                params10.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtMiniSklad.setLayoutParams(params10);

                LinearLayout.LayoutParams params11 = (LinearLayout.LayoutParams) LLtPotencClients.getLayoutParams();
                params11.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtPotencClients.setLayoutParams(params11);

                LinearLayout.LayoutParams params12 = (LinearLayout.LayoutParams) LLtFinance.getLayoutParams();
                params12.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtFinance.setLayoutParams(params12);

                LinearLayout.LayoutParams params13 = (LinearLayout.LayoutParams) LLtSdelkiSProblemami.getLayoutParams();
                params13.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtSdelkiSProblemami.setLayoutParams(params13);

                LinearLayout.LayoutParams params14 = (LinearLayout.LayoutParams) LLtCalendar.getLayoutParams();
                params14.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtCalendar.setLayoutParams(params14);

                LinearLayout.LayoutParams params15 = (LinearLayout.LayoutParams) LLtNormDok.getLayoutParams();
                params15.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtNormDok.setLayoutParams(params15);

                LinearLayout.LayoutParams params16 = (LinearLayout.LayoutParams) LLtRashodi.getLayoutParams();
                params16.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtRashodi.setLayoutParams(params16);

                LinearLayout.LayoutParams params17 = (LinearLayout.LayoutParams) LLtAvto.getLayoutParams();
                params17.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtAvto.setLayoutParams(params17);

                LinearLayout.LayoutParams params18 = (LinearLayout.LayoutParams) LLtOsnovnieSredstva.getLayoutParams();
                params18.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtOsnovnieSredstva.setLayoutParams(params18);

                LinearLayout.LayoutParams params19 = (LinearLayout.LayoutParams) LLtZametki.getLayoutParams();
                params19.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtZametki.setLayoutParams(params19);

                LinearLayout.LayoutParams params20 = (LinearLayout.LayoutParams) LLtKontragenti.getLayoutParams();
                params20.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtKontragenti.setLayoutParams(params20);

                LinearLayout.LayoutParams params21 = (LinearLayout.LayoutParams) LLtMyOrgs.getLayoutParams();
                params21.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtMyOrgs.setLayoutParams(params21);

                LinearLayout.LayoutParams params22 = (LinearLayout.LayoutParams) LLtSites.getLayoutParams();
                params22.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtSites.setLayoutParams(params22);

                LinearLayout.LayoutParams params23 = (LinearLayout.LayoutParams) LLtVoronkaProdazh.getLayoutParams();
                params23.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtVoronkaProdazh.setLayoutParams(params23);

                LinearLayout.LayoutParams params24 = (LinearLayout.LayoutParams) LLtDolzhniki.getLayoutParams();
                params24.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtDolzhniki.setLayoutParams(params24);

                LinearLayout.LayoutParams params25 = (LinearLayout.LayoutParams) LLtMail.getLayoutParams();
                params25.height = (int) (70 * (getResources().getDisplayMetrics().density));
                LLtMail.setLayoutParams(params25);

            } else {

                RaskritieSdelkiVRabote.setVisibility(View.GONE);
                RaskritieSdelanoPredlozhenie.setVisibility(View.GONE);

                RaskritieSvodnieDannie.setVisibility(View.GONE);
                RaskritieSotrudniki.setVisibility(View.GONE);
                RaskritieSklad.setVisibility(View.GONE);
                RaskritiePotencClients.setVisibility(View.GONE);
                RaskritieVoronkaProdazh.setVisibility(View.GONE);
                RaskritieFinance.setVisibility(View.GONE);
                RaskritieSdelkiSProblemami.setVisibility(View.GONE);
                RaskritieKalendar.setVisibility(View.GONE);
                RaskritieDolzhniki.setVisibility(View.GONE);
                RaskritieNormDok.setVisibility(View.GONE);
                RaskritieRasxody.setVisibility(View.GONE);
                RaskritieKorrespondence.setVisibility(View.GONE);
                RaskritieOsnovnieSredstva.setVisibility(View.GONE);
                RaskritieZametki.setVisibility(View.GONE);
                RaskritieKontragenti.setVisibility(View.GONE);
                RaskritieMyOrgs.setVisibility(View.GONE);
                RaskritieSajti.setVisibility(View.GONE);
                RaskritieAvto.setVisibility(View.GONE);

            }
            cursor.close();
            db.close();
        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

        //endregion

        // region РАСКРЫТИЕ КАРТОЧЕК

        RaskritieSdelkiVRabote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSdelkiVRabote.getText().equals("-")) {

                    ListViewSdelkiVRabote.setVisibility(View.GONE);
                    RaskritieSdelkiVRabote.setText("+");
                    MYSDELKIlistfull.setVisibility(View.GONE);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSdelkivrabote.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLSdelkivrabote.setLayoutParams(params);

                } else {
                    ListViewSdelkiVRabote.setVisibility(View.VISIBLE);
                    RaskritieSdelkiVRabote.setText("-");
                    MYSDELKIlistfull.setVisibility(View.VISIBLE);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSdelkivrabote.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ; // In dp
                    LLSdelkivrabote.setLayoutParams(params);
                }

            }
        });


        RaskritieSdelanoPredlozhenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSdelanoPredlozhenie.getText().equals("-")) {

                    ListViewSdelkiPredlozhenie.setVisibility(View.GONE);
                    RaskritieSdelanoPredlozhenie.setText("+");
                    MYSDELKIlistfull2.setVisibility(View.GONE);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSdelanoPredlozhenije.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLSdelanoPredlozhenije.setLayoutParams(params);

                } else {
                    ListViewSdelkiPredlozhenie.setVisibility(View.VISIBLE);
                    RaskritieSdelanoPredlozhenie.setText("-");
                    MYSDELKIlistfull2.setVisibility(View.VISIBLE);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSdelanoPredlozhenije.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLSdelanoPredlozhenije.setLayoutParams(params);
                }

            }
        });


        RaskritieSvodnieDannie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSvodnieDannie.getText().equals("+")) {

                    RaskritieSvodnieDannie.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSvodnieDannije.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLSvodnieDannije.setLayoutParams(params);

                } else {
                    RaskritieSvodnieDannie.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSvodnieDannije.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLSvodnieDannije.setLayoutParams(params);
                }

            }
        });


        RaskritieSotrudniki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSotrudniki.getText().equals("+")) {

                    RaskritieSotrudniki.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtSotrudniki.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtSotrudniki.setLayoutParams(params);

                } else {
                    RaskritieSotrudniki.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtSotrudniki.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtSotrudniki.setLayoutParams(params);
                }

            }
        });


        RaskritieSklad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSklad.getText().equals("+")) {

                    RaskritieSklad.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtMiniSklad.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtMiniSklad.setLayoutParams(params);

                } else {
                    RaskritieSklad.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtMiniSklad.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtMiniSklad.setLayoutParams(params);
                }

            }
        });


        RaskritiePotencClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritiePotencClients.getText().equals("+")) {

                    RaskritiePotencClients.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtPotencClients.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtPotencClients.setLayoutParams(params);

                } else {
                    RaskritiePotencClients.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtPotencClients.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtPotencClients.setLayoutParams(params);
                }

            }
        });


        RaskritieVoronkaProdazh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieVoronkaProdazh.getText().equals("+")) {

                    RaskritieVoronkaProdazh.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtVoronkaProdazh.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtVoronkaProdazh.setLayoutParams(params);

                } else {
                    RaskritieVoronkaProdazh.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtVoronkaProdazh.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtVoronkaProdazh.setLayoutParams(params);
                }

            }
        });


        RaskritieSvodnieDannie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSvodnieDannie.getText().equals("+")) {

                    RaskritieSvodnieDannie.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSvodnieDannije.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLSvodnieDannije.setLayoutParams(params);

                } else {
                    RaskritieSvodnieDannie.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLSvodnieDannije.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLSvodnieDannije.setLayoutParams(params);
                }

            }
        });


        RaskritieFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieFinance.getText().equals("+")) {

                    RaskritieFinance.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtFinance.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtFinance.setLayoutParams(params);

                } else {
                    RaskritieFinance.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtFinance.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtFinance.setLayoutParams(params);
                }

            }
        });


        RaskritieSdelkiSProblemami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSdelkiSProblemami.getText().equals("+")) {

                    RaskritieSdelkiSProblemami.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtSdelkiSProblemami.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtSdelkiSProblemami.setLayoutParams(params);

                } else {
                    RaskritieSdelkiSProblemami.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtSdelkiSProblemami.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtSdelkiSProblemami.setLayoutParams(params);
                }

            }
        });


        RaskritieKalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieKalendar.getText().equals("+")) {

                    RaskritieKalendar.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtCalendar.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtCalendar.setLayoutParams(params);

                } else {
                    RaskritieKalendar.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtCalendar.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtCalendar.setLayoutParams(params);
                }

            }
        });


        RaskritieDolzhniki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieDolzhniki.getText().equals("+")) {

                    RaskritieDolzhniki.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtDolzhniki.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtDolzhniki.setLayoutParams(params);

                } else {
                    RaskritieDolzhniki.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtDolzhniki.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtDolzhniki.setLayoutParams(params);
                }

            }
        });


        RaskritieNormDok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieNormDok.getText().equals("+")) {

                    RaskritieNormDok.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtNormDok.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtNormDok.setLayoutParams(params);

                } else {
                    RaskritieNormDok.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtNormDok.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtNormDok.setLayoutParams(params);
                }

            }
        });


        RaskritieRasxody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieRasxody.getText().equals("+")) {

                    RaskritieRasxody.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtRashodi.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtRashodi.setLayoutParams(params);

                } else {
                    RaskritieRasxody.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtRashodi.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtRashodi.setLayoutParams(params);
                }

            }
        });


        RaskritieAvto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieAvto.getText().equals("+")) {

                    RaskritieAvto.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtAvto.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtAvto.setLayoutParams(params);

                } else {
                    RaskritieAvto.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtAvto.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtAvto.setLayoutParams(params);
                }

            }
        });


        RaskritieKorrespondence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieKorrespondence.getText().equals("+")) {

                    RaskritieKorrespondence.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtMail.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtMail.setLayoutParams(params);

                } else {
                    RaskritieKorrespondence.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtMail.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtMail.setLayoutParams(params);
                }

            }
        });


        RaskritieOsnovnieSredstva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieOsnovnieSredstva.getText().equals("+")) {

                    RaskritieOsnovnieSredstva.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtOsnovnieSredstva.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtOsnovnieSredstva.setLayoutParams(params);

                } else {
                    RaskritieOsnovnieSredstva.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtOsnovnieSredstva.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtOsnovnieSredstva.setLayoutParams(params);
                }

            }
        });


        RaskritieZametki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieZametki.getText().equals("+")) {

                    RaskritieZametki.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtZametki.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtZametki.setLayoutParams(params);

                } else {
                    RaskritieZametki.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtZametki.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtZametki.setLayoutParams(params);
                }

            }
        });


        RaskritieKontragenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieKontragenti.getText().equals("+")) {

                    RaskritieKontragenti.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtKontragenti.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtKontragenti.setLayoutParams(params);

                } else {
                    RaskritieKontragenti.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtKontragenti.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtKontragenti.setLayoutParams(params);
                }

            }
        });


        RaskritieMyOrgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieMyOrgs.getText().equals("+")) {

                    RaskritieMyOrgs.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtMyOrgs.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtMyOrgs.setLayoutParams(params);

                } else {
                    RaskritieMyOrgs.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtMyOrgs.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtMyOrgs.setLayoutParams(params);
                }

            }
        });


        RaskritieSajti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RaskritieSajti.getText().equals("+")) {

                    RaskritieSajti.setText("-");


                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtSites.getLayoutParams();
                    params.height = (int) (400 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtSites.setLayoutParams(params);

                } else {
                    RaskritieSajti.setText("+");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) LLtSites.getLayoutParams();
                    params.height = (int) (70 * (getResources().getDisplayMetrics().density));
                    ;
                    LLtSites.setLayoutParams(params);
                }

            }
        });

        //endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (НОРМАТИВНЫЕ ДОКУМЕНТЫ)

        try {


            final ArrayList<NORMDOC> list = new ArrayList<NORMDOC>();

            SQLiteDatabase db = DB.getReadableDatabase();


            Cursor cursor = db.rawQuery("SELECT [NAME],[OPISANIE], [FILENAME], [ID] FROM NORMDOC ORDER BY ID DESC LIMIT 20", null); // запрос из базы реквизитов

            while (cursor.moveToNext()) {
                list.add(
                        new NORMDOC(
                                cursor.getString(0),
                                "Описание: " + cursor.getString(1),
                                "Файл: " + cursor.getString(2),
                                cursor.getString(3)));

            }


            final ListView ListViewNORMDOC = (ListView) rootView.findViewById(R.id.ListViewNORMDOC);

            ListViewNORMDOC.setAdapter(new NORMDOCListAdapter(getActivity(), list));

            ListViewNORMDOC.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editnormdochome((list.get(position)).getidnumber());

                            SdelkaID sdelkaid = new SdelkaID();
                            sdelkaid.setSdelkaID((list.get(position)).getidnumber());
                        }
                    });

            cursor.close();
            db.close();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }
//endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (ЗАМЕТКИ)


        try {


            final ArrayList<ZAMETKI> list = new ArrayList<ZAMETKI>();

            SQLiteDatabase db = DB.getReadableDatabase();


            Cursor cursor = db.rawQuery("SELECT [NAME],[OPISANIE], [SDELKAIDD], [DATA], [IZBR], [ID] FROM ZAMETKI ORDER BY ID DESC LIMIT 20", null); // запрос из базы реквизитов

            while (cursor.moveToNext()) {

                SdelkaID sdelkaname = new SdelkaID();
                sdelkaname.setSdelkaID("");

                try {
                    SQLiteDatabase db1 = DB.getReadableDatabase();
                    Cursor cursor1 = db1.rawQuery("SELECT SDELKA_NAME FROM SDELKI WHERE ID = " + cursor.getString(2), null);
                    cursor1.moveToNext(); //без этого exception
                    sdelkaname.setSdelkaID(cursor1.getString(0));
                    db1.close();
                } catch (CursorIndexOutOfBoundsException CursorException) {
                } catch (SQLException mSQLException) {
                }

                list.add(
                        new ZAMETKI(
                                cursor.getString(0),
                                cursor.getString(1),
                                "Сделка: " + sdelkaname.getSdelkaID(),
                                "Дата: " + cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5)));

            }

            ListView ListViewZAMETKI = (ListView) rootView.findViewById(R.id.ListViewZAMETKI);

            ListViewZAMETKI.setAdapter(new ZAMETKIListAdapter(getActivity(), list));


            ListViewZAMETKI.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                            SdelkaID sdelkaid = new SdelkaID();
                            sdelkaid.setSdelkaID((list.get(position)).getidnumber());


                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editzametkahome((list.get(position)).getidnumber());


                        }
                    });

            cursor.close();
            db.close();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }
//endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (КОНТРАГЕНТЫ)


        try {


            final ArrayList<Sdelki> listsdelki = new ArrayList<Sdelki>(); //один список для контрагентов, моих организаций и сделок

            SQLiteDatabase db = DB.getReadableDatabase();


            Cursor cursor = db.rawQuery("SELECT [SOKRNAME],[BANKNAME], [INN], [ID] FROM KONTRAGENTI ORDER BY ID DESC LIMIT 20", null); // запрос из базы реквизитов

            while (cursor.moveToNext()) {
                listsdelki.add(
                        new Sdelki(
                                cursor.getString(0),
                                "БАНК: " + cursor.getString(1),
                                "ИНН: " + cursor.getString(2),
                                cursor.getString(3)));

            }


            ListView ListViewKONTRAGENTI = (ListView) rootView.findViewById(R.id.ListViewKONTRAGENTI);

            ListViewKONTRAGENTI.setAdapter(new SdelkiListAdapter(getActivity(), listsdelki));

            ListViewKONTRAGENTI.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();

                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editkontragenthome((listsdelki.get(position)).getidnumber());

                            SdelkaID sdelkaid = new SdelkaID();
                            sdelkaid.setSdelkaID((listsdelki.get(position)).getidnumber());
                        }
                    });

            cursor.close();
            db.close();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

//endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (МОИ ОРГАНИЗАЦИИ)


        try {


            final ArrayList<Sdelki> listsdelki1 = new ArrayList<Sdelki>(); //один список для контрагентов, моих организаций и сделок

            SQLiteDatabase db = DB.getReadableDatabase();


            Cursor cursor = db.rawQuery("SELECT [SOKRNAME],[BANKNAME], [INN], [ID] FROM MYFIRMREKVIZITI ORDER BY ID DESC LIMIT 20", null); // запрос из базы реквизитов

            while (cursor.moveToNext()) {
                listsdelki1.add(
                        new Sdelki(
                                cursor.getString(0),
                                "БАНК: " + cursor.getString(1),
                                "ИНН: " + cursor.getString(2),
                                cursor.getString(3)));

            }


            ListView ListViewMYFIRMS = (ListView) rootView.findViewById(R.id.ListViewMYFIRMS);

            ListViewMYFIRMS.setAdapter(new SdelkiListAdapter(getActivity(), listsdelki1));

            ListViewMYFIRMS.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editmyfirmhome((listsdelki1.get(position)).getidnumber());

                            SdelkaID sdelkaid = new SdelkaID();
                            sdelkaid.setSdelkaID((listsdelki1.get(position)).getidnumber());
                        }
                    });

            cursor.close();
            db.close();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

        //endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (СДЕЛКИ В РАБОТЕ)

        //СТАТУСЫ СДЕЛОК:
        //(1) Направлено коммерческое предложение
        //(2) Коммерческое предложение не устроило/отклонено/без ответа
        //(3) Договор на согласовании
        //(4) Договор подписан - этап исполнения сделки
        //(5) Отказ от заключения договора с нашей стороны
        //(6) Отказ от заключения договора со стороны Контрагента
        //(7) В процессе исполнения Договора - этап поставки товаров
        //(8) В процессе исполнения Договора - этап оказания услуг
        //(9) Договор подписан, ожидание предоплаты
        //(10) Договор подписан, ожидание предоплаты - оплаты товаров
        //(11) Договор подписан, ожидание предоплаты - оплаты услуг
        //(12) Ожидание частичной/полной оплаты - оплаты товаров
        //(13) Ожидание частичной/полной оплаты - оплаты услуг
        //(14) Ожидание полной оплаты по сделке
        //(15) Подписание закрывающих документов - подписание актов
        //(16) Подписание закрывающих документов - подписание накладных
        //(17) Подписание закрывающих документов (актов и/или накладных)
        //(18) Этап взыскания оплаты - направление Претензии
        //(19) Этап взыскания оплаты - Суд
        //(20) Этап взыскания оплаты - Исполнительное производство
        //(21) Сделка завершена УСПЕШНО
        //(22) Сделка завершена с проблемами
        //(23) Сделка не завершена
        //(24) Сделка не завершена - досрочное расторжение договора
        //(25) Сделка не завершена - по нашей вине
        //(26) Сделка не завершена - по вине Заказчика


        try {


            final ArrayList<Sdelki> list = new ArrayList<Sdelki>();

            SQLiteDatabase db = DB.getReadableDatabase();


            Cursor cursor = db.rawQuery("SELECT [SDELKA_NAME],[K_SOKRNAME], [MY_SOKRNAME], [ID] FROM SDELKI WHERE STATUS = '3' OR STATUS='4' OR STATUS = '7' OR STATUS = '8' OR STATUS = '9' OR STATUS = '10' OR STATUS = '11' OR STATUS = '12' OR STATUS = '13' OR STATUS = '14' OR STATUS = '15' OR STATUS = '16' OR STATUS = '17' ORDER BY ID DESC", null); // запрос из базы реквизитов


            while (cursor.moveToNext()) {
                list.add(
                        new Sdelki(
                                cursor.getString(0),
                                "Контрагент: " + cursor.getString(1),
                                "Моя организация: " + cursor.getString(2),
                                cursor.getString(3)));

            }


            final SdelkiListAdapter Z1SdelkiListAdapter = new SdelkiListAdapter(getActivity(), list);
            ListViewSdelkiVRabote.setAdapter(Z1SdelkiListAdapter);

            ListViewSdelkiVRabote.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();

                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editsdelka((list.get(position)).getidnumber());

                            SdelkaID sdelkaid = new SdelkaID();
                            sdelkaid.setSdelkaID((list.get(position)).getidnumber());
                        }
                    });

            cursor.close();
            db.close();

            ListViewSdelkiVRabote.setOnItemLongClickListener(new OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {

                    final String selectedIDsdelka = list.get(position).getidnumber();

                    AlertDialog.Builder editstatussdelkabuilder = new AlertDialog.Builder(container.getContext());

                    View dialogView = inflater.inflate(R.layout.alertdialog_edit_statussdelka, null); //важно - inflater определен в начале кода фрагмента

                    editstatussdelkabuilder.setCancelable(true);

                    // Привязка xml-разметки окна диалогов
                    editstatussdelkabuilder.setView(dialogView);

                    //region ЗАПОЛНЕНИЯ ДИАЛОГА ДАННЫМИ ИЗ БД ДЛЯ РАДИОГРУППЫ СТАТУСА СДЕЛКИ

                    final RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.radiogroupSTATUS);
                    final RadioButton Step1 = (RadioButton) dialogView.findViewById(R.id.radio_step1);
                    final RadioButton Step2 = (RadioButton) dialogView.findViewById(R.id.radio_step2);
                    final RadioButton Step3 = (RadioButton) dialogView.findViewById(R.id.radio_step3);
                    final RadioButton Step4 = (RadioButton) dialogView.findViewById(R.id.radio_step4);
                    final RadioButton Step5 = (RadioButton) dialogView.findViewById(R.id.radio_step5);
                    final RadioButton Step6 = (RadioButton) dialogView.findViewById(R.id.radio_step6);
                    final RadioButton Step7 = (RadioButton) dialogView.findViewById(R.id.radio_step7);
                    final RadioButton Step8 = (RadioButton) dialogView.findViewById(R.id.radio_step8);
                    final RadioButton Step9 = (RadioButton) dialogView.findViewById(R.id.radio_step9);
                    final RadioButton Step10 = (RadioButton) dialogView.findViewById(R.id.radio_step10);
                    final RadioButton Step11 = (RadioButton) dialogView.findViewById(R.id.radio_step11);
                    final RadioButton Step12 = (RadioButton) dialogView.findViewById(R.id.radio_step12);
                    final RadioButton Step13 = (RadioButton) dialogView.findViewById(R.id.radio_step13);
                    final RadioButton Step14 = (RadioButton) dialogView.findViewById(R.id.radio_step14);
                    final RadioButton Step15 = (RadioButton) dialogView.findViewById(R.id.radio_step15);
                    final RadioButton Step16 = (RadioButton) dialogView.findViewById(R.id.radio_step16);
                    final RadioButton Step17 = (RadioButton) dialogView.findViewById(R.id.radio_step17);
                    final RadioButton Step18 = (RadioButton) dialogView.findViewById(R.id.radio_step18);
                    final RadioButton Step19 = (RadioButton) dialogView.findViewById(R.id.radio_step19);
                    final RadioButton Step20 = (RadioButton) dialogView.findViewById(R.id.radio_step20);
                    final RadioButton Step21 = (RadioButton) dialogView.findViewById(R.id.radio_step21);
                    final RadioButton Step22 = (RadioButton) dialogView.findViewById(R.id.radio_step22);
                    final RadioButton Step23 = (RadioButton) dialogView.findViewById(R.id.radio_step23);
                    final RadioButton Step24 = (RadioButton) dialogView.findViewById(R.id.radio_step24);
                    final RadioButton Step25 = (RadioButton) dialogView.findViewById(R.id.radio_step25);
                    final RadioButton Step26 = (RadioButton) dialogView.findViewById(R.id.radio_step26);


                    try {
                        SQLiteDatabase db = DB.getReadableDatabase();
                        Cursor cursor = db.rawQuery("SELECT [STATUS] FROM SDELKI WHERE ID = '" + selectedIDsdelka + "';", null);
                        cursor.moveToNext(); //без этого exception
                        String result1 = cursor.getString(0);
                        cursor.close();
                        db.close();
                        radioGroup.clearCheck();

                        if (result1.equals("1")) {
                            Step1.setChecked(true);
                        }
                        if (result1.equals("2")) {
                            Step2.setChecked(true);
                        }
                        if (result1.equals("3")) {
                            Step3.setChecked(true);
                        }
                        if (result1.equals("4")) {
                            Step4.setChecked(true);
                        }
                        if (result1.equals("5")) {
                            Step5.setChecked(true);
                        }
                        if (result1.equals("6")) {
                            Step6.setChecked(true);
                        }
                        if (result1.equals("7")) {
                            Step7.setChecked(true);
                        }
                        if (result1.equals("8")) {
                            Step8.setChecked(true);
                        }
                        if (result1.equals("9")) {
                            Step9.setChecked(true);
                        }
                        if (result1.equals("10")) {
                            Step10.setChecked(true);
                        }
                        if (result1.equals("11")) {
                            Step11.setChecked(true);
                        }
                        if (result1.equals("12")) {
                            Step12.setChecked(true);
                        }
                        if (result1.equals("13")) {
                            Step13.setChecked(true);
                        }
                        if (result1.equals("14")) {
                            Step14.setChecked(true);
                        }
                        if (result1.equals("15")) {
                            Step15.setChecked(true);
                        }
                        if (result1.equals("16")) {
                            Step16.setChecked(true);
                        }
                        if (result1.equals("17")) {
                            Step17.setChecked(true);
                        }
                        if (result1.equals("18")) {
                            Step18.setChecked(true);
                        }
                        if (result1.equals("19")) {
                            Step19.setChecked(true);
                        }
                        if (result1.equals("20")) {
                            Step20.setChecked(true);
                        }
                        if (result1.equals("21")) {
                            Step21.setChecked(true);

                        }
                        if (result1.equals("22")) {
                            Step22.setChecked(true);
                        }
                        if (result1.equals("23")) {
                            Step23.setChecked(true);
                        }
                        if (result1.equals("24")) {
                            Step24.setChecked(true);
                        }
                        if (result1.equals("25")) {
                            Step25.setChecked(true);
                        }
                        if (result1.equals("26")) {
                            Step26.setChecked(true);
                        }


                    } catch (Exception CursorException) {
                        appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
                    }


                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {

                                case R.id.radio_step1:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db1 = DB.getReadableDatabase();
                                    db1.execSQL("UPDATE SDELKI SET STATUS = '1' WHERE ID ='" + selectedIDsdelka + "'; ");
                                    db1.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step2:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db2 = DB.getReadableDatabase();
                                    db2.execSQL("UPDATE SDELKI SET STATUS = '2' WHERE ID ='" + selectedIDsdelka + "';");
                                    db2.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step3:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db3 = DB.getReadableDatabase();
                                    db3.execSQL("UPDATE SDELKI SET STATUS = '3' WHERE ID ='" + selectedIDsdelka + "';");
                                    db3.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step4:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db4 = DB.getReadableDatabase();
                                    db4.execSQL("UPDATE SDELKI SET STATUS = '4' WHERE ID='" + selectedIDsdelka + "';");
                                    db4.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step5:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db5 = DB.getReadableDatabase();
                                    db5.execSQL("UPDATE SDELKI SET STATUS = '5' WHERE ID='" + selectedIDsdelka + "';");
                                    db5.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step6:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db6 = DB.getReadableDatabase();
                                    db6.execSQL("UPDATE SDELKI SET STATUS = '6' WHERE ID='" + selectedIDsdelka + "';");
                                    db6.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step7:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db7 = DB.getReadableDatabase();
                                    db7.execSQL("UPDATE SDELKI SET STATUS = '7' WHERE ID='" + selectedIDsdelka + "';");
                                    db7.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step8:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db8 = DB.getReadableDatabase();
                                    db8.execSQL("UPDATE SDELKI SET STATUS = '8' WHERE ID='" + selectedIDsdelka + "';");
                                    db8.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step9:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db9 = DB.getReadableDatabase();
                                    db9.execSQL("UPDATE SDELKI SET STATUS = '9' WHERE ID='" + selectedIDsdelka + "';");
                                    db9.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step10:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db10 = DB.getReadableDatabase();
                                    db10.execSQL("UPDATE SDELKI SET STATUS = '10' WHERE ID='" + selectedIDsdelka + "';");
                                    db10.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step11:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db11 = DB.getReadableDatabase();
                                    db11.execSQL("UPDATE SDELKI SET STATUS = '11' WHERE ID='" + selectedIDsdelka + "';");
                                    db11.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step12:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db12 = DB.getReadableDatabase();
                                    db12.execSQL("UPDATE SDELKI SET STATUS = '12' WHERE ID='" + selectedIDsdelka + "';");
                                    db12.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step13:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db13 = DB.getReadableDatabase();
                                    db13.execSQL("UPDATE SDELKI SET STATUS = '13' WHERE ID='" + selectedIDsdelka + "';");
                                    db13.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step14:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db14 = DB.getReadableDatabase();
                                    db14.execSQL("UPDATE SDELKI SET STATUS = '14' WHERE ID='" + selectedIDsdelka + "';");
                                    db14.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step15:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db15 = DB.getReadableDatabase();
                                    db15.execSQL("UPDATE SDELKI SET STATUS = '15' WHERE ID='" + selectedIDsdelka + "';");
                                    db15.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step16:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db16 = DB.getReadableDatabase();
                                    db16.execSQL("UPDATE SDELKI SET STATUS = '16' WHERE ID='" + selectedIDsdelka + "';");
                                    db16.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step17:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db17 = DB.getReadableDatabase();
                                    db17.execSQL("UPDATE SDELKI SET STATUS = '17' WHERE ID='" + selectedIDsdelka + "';");
                                    db17.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step18:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db18 = DB.getReadableDatabase();
                                    db18.execSQL("UPDATE SDELKI SET STATUS = '18' WHERE ID='" + selectedIDsdelka + "';");
                                    db18.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step19:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db19 = DB.getReadableDatabase();
                                    db19.execSQL("UPDATE SDELKI SET STATUS = '19' WHERE ID='" + selectedIDsdelka + "';");
                                    db19.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step20:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db20 = DB.getReadableDatabase();
                                    db20.execSQL("UPDATE SDELKI SET STATUS = '20' WHERE ID='" + selectedIDsdelka + "';");
                                    db20.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step21:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db21 = DB.getReadableDatabase();
                                    db21.execSQL("UPDATE SDELKI SET STATUS = '21' WHERE ID='" + selectedIDsdelka + "';");
                                    db21.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step22:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db22 = DB.getReadableDatabase();
                                    db22.execSQL("UPDATE SDELKI SET STATUS = '22' WHERE ID='" + selectedIDsdelka + "';");
                                    db22.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step23:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db23 = DB.getReadableDatabase();
                                    db23.execSQL("UPDATE SDELKI SET STATUS = '23' WHERE ID='" + selectedIDsdelka + "';");
                                    db23.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step24:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db24 = DB.getReadableDatabase();
                                    db24.execSQL("UPDATE SDELKI SET STATUS = '24' WHERE ID='" + selectedIDsdelka + "';");
                                    db24.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step25:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db25 = DB.getReadableDatabase();
                                    db25.execSQL("UPDATE SDELKI SET STATUS = '25' WHERE ID='" + selectedIDsdelka + "';");
                                    db25.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step26:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db26 = DB.getReadableDatabase();
                                    db26.execSQL("UPDATE SDELKI SET STATUS = '26' WHERE ID='" + selectedIDsdelka + "';");
                                    db26.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                default:
                                    break;
                            }
                        }
                    });


                    // Создание диалога
                    final AlertDialog editsdelkadialog = editstatussdelkabuilder.create();

                    editsdelkadialog.show();

                    return true;
                }

            });

            Z1SdelkiListAdapter.notifyDataSetChanged();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

        //endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (СДЕЛКИ СДЕЛАНО ПРЕДЛОЖЕНИЕ)

        /*
        СТАТУСЫ СДЕЛОК:
        (1) Направлено коммерческое предложение
        (2) Коммерческое предложение не устроило/отклонено/без ответа
        (3) Договор на согласовании
        (4) Договор подписан - этап исполнения сделки
        (5) Отказ от заключения договора с нашей стороны
        (6) Отказ от заключения договора со стороны Контрагента
        (7) В процессе исполнения Договора - этап поставки товаров
        (8) В процессе исполнения Договора - этап оказания услуг
        (9) Договор подписан, ожидание предоплаты
        (10) Договор подписан, ожидание предоплаты - оплаты товаров
        (11) Договор подписан, ожидание предоплаты - оплаты услуг
        (12) Ожидание частичной/полной оплаты - оплаты товаров
        (13) Ожидание частичной/полной оплаты - оплаты услуг
        (14) Ожидание полной оплаты по сделке
        (15) Подписание закрывающих документов - подписание актов
        (16) Подписание закрывающих документов - подписание накладных
        (17) Подписание закрывающих документов (актов и/или накладных)
        (18) Этап взыскания оплаты - направление Претензии
        (19) Этап взыскания оплаты - Суд
        (20) Этап взыскания оплаты - Исполнительное производство
        (21) Сделка завершена УСПЕШНО
        (22) Сделка завершена с проблемами
        (23) Сделка не завершена
        (24) Сделка не завершена - досрочное расторжение договора
        (25) Сделка не завершена - по нашей вине
        (26) Сделка не завершена - по вине Заказчика
        */


        try {

            final ArrayList<Sdelki> list = new ArrayList<Sdelki>();

            SQLiteDatabase db = DB.getReadableDatabase();


            Cursor cursor = db.rawQuery("SELECT [SDELKA_NAME],[K_SOKRNAME], [MY_SOKRNAME], [ID] FROM SDELKI WHERE STATUS = '1' ORDER BY ID DESC", null); // запрос из базы реквизитов


            while (cursor.moveToNext()) {
                list.add(
                        new Sdelki(
                                cursor.getString(0),
                                "Контрагент: " + cursor.getString(1),
                                "Моя организация: " + cursor.getString(2),
                                cursor.getString(3)));

            }


            final SdelkiListAdapter Z1SdelkiListAdapter = new SdelkiListAdapter(getActivity(), list);
            ListViewSdelkiPredlozhenie.setAdapter(Z1SdelkiListAdapter);

            ListViewSdelkiPredlozhenie.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editsdelka((list.get(position)).getidnumber());

                            SdelkaID sdelkaid = new SdelkaID();
                            sdelkaid.setSdelkaID((list.get(position)).getidnumber());
                        }
                    });
            cursor.close();
            db.close();

            ListViewSdelkiPredlozhenie.setOnItemLongClickListener(new OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {

                    final String selectedIDsdelka = list.get(position).getidnumber();

                    AlertDialog.Builder editstatussdelkabuilder = new AlertDialog.Builder(container.getContext());

                    View dialogView = inflater.inflate(R.layout.alertdialog_edit_statussdelka, null); //важно - inflater определен в начале кода фрагмента

                    editstatussdelkabuilder.setCancelable(true);

                    // Привязка xml-разметки окна диалогов
                    editstatussdelkabuilder.setView(dialogView);


                    final RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.radiogroupSTATUS);
                    final RadioButton Step1 = (RadioButton) dialogView.findViewById(R.id.radio_step1);
                    final RadioButton Step2 = (RadioButton) dialogView.findViewById(R.id.radio_step2);
                    final RadioButton Step3 = (RadioButton) dialogView.findViewById(R.id.radio_step3);
                    final RadioButton Step4 = (RadioButton) dialogView.findViewById(R.id.radio_step4);
                    final RadioButton Step5 = (RadioButton) dialogView.findViewById(R.id.radio_step5);
                    final RadioButton Step6 = (RadioButton) dialogView.findViewById(R.id.radio_step6);
                    final RadioButton Step7 = (RadioButton) dialogView.findViewById(R.id.radio_step7);
                    final RadioButton Step8 = (RadioButton) dialogView.findViewById(R.id.radio_step8);
                    final RadioButton Step9 = (RadioButton) dialogView.findViewById(R.id.radio_step9);
                    final RadioButton Step10 = (RadioButton) dialogView.findViewById(R.id.radio_step10);
                    final RadioButton Step11 = (RadioButton) dialogView.findViewById(R.id.radio_step11);
                    final RadioButton Step12 = (RadioButton) dialogView.findViewById(R.id.radio_step12);
                    final RadioButton Step13 = (RadioButton) dialogView.findViewById(R.id.radio_step13);
                    final RadioButton Step14 = (RadioButton) dialogView.findViewById(R.id.radio_step14);
                    final RadioButton Step15 = (RadioButton) dialogView.findViewById(R.id.radio_step15);
                    final RadioButton Step16 = (RadioButton) dialogView.findViewById(R.id.radio_step16);
                    final RadioButton Step17 = (RadioButton) dialogView.findViewById(R.id.radio_step17);
                    final RadioButton Step18 = (RadioButton) dialogView.findViewById(R.id.radio_step18);
                    final RadioButton Step19 = (RadioButton) dialogView.findViewById(R.id.radio_step19);
                    final RadioButton Step20 = (RadioButton) dialogView.findViewById(R.id.radio_step20);
                    final RadioButton Step21 = (RadioButton) dialogView.findViewById(R.id.radio_step21);
                    final RadioButton Step22 = (RadioButton) dialogView.findViewById(R.id.radio_step22);
                    final RadioButton Step23 = (RadioButton) dialogView.findViewById(R.id.radio_step23);
                    final RadioButton Step24 = (RadioButton) dialogView.findViewById(R.id.radio_step24);
                    final RadioButton Step25 = (RadioButton) dialogView.findViewById(R.id.radio_step25);
                    final RadioButton Step26 = (RadioButton) dialogView.findViewById(R.id.radio_step26);


                    try {
                        SQLiteDatabase db = DB.getReadableDatabase();
                        Cursor cursor = db.rawQuery("SELECT [STATUS] FROM SDELKI WHERE ID = '" + selectedIDsdelka + "';", null);
                        cursor.moveToNext(); //без этого exception
                        String result1 = cursor.getString(0);
                        cursor.close();
                        db.close();
                        radioGroup.clearCheck();

                        if (result1.equals("1")) {
                            Step1.setChecked(true);
                        }
                        if (result1.equals("2")) {
                            Step2.setChecked(true);
                        }
                        if (result1.equals("3")) {
                            Step3.setChecked(true);
                        }
                        if (result1.equals("4")) {
                            Step4.setChecked(true);
                        }
                        if (result1.equals("5")) {
                            Step5.setChecked(true);
                        }
                        if (result1.equals("6")) {
                            Step6.setChecked(true);
                        }
                        if (result1.equals("7")) {
                            Step7.setChecked(true);
                        }
                        if (result1.equals("8")) {
                            Step8.setChecked(true);
                        }
                        if (result1.equals("9")) {
                            Step9.setChecked(true);
                        }
                        if (result1.equals("10")) {
                            Step10.setChecked(true);
                        }
                        if (result1.equals("11")) {
                            Step11.setChecked(true);
                        }
                        if (result1.equals("12")) {
                            Step12.setChecked(true);
                        }
                        if (result1.equals("13")) {
                            Step13.setChecked(true);
                        }
                        if (result1.equals("14")) {
                            Step14.setChecked(true);
                        }
                        if (result1.equals("15")) {
                            Step15.setChecked(true);
                        }
                        if (result1.equals("16")) {
                            Step16.setChecked(true);
                        }
                        if (result1.equals("17")) {
                            Step17.setChecked(true);
                        }
                        if (result1.equals("18")) {
                            Step18.setChecked(true);
                        }
                        if (result1.equals("19")) {
                            Step19.setChecked(true);
                        }
                        if (result1.equals("20")) {
                            Step20.setChecked(true);
                        }
                        if (result1.equals("21")) {
                            Step21.setChecked(true);

                        }
                        if (result1.equals("22")) {
                            Step22.setChecked(true);
                        }
                        if (result1.equals("23")) {
                            Step23.setChecked(true);
                        }
                        if (result1.equals("24")) {
                            Step24.setChecked(true);
                        }
                        if (result1.equals("25")) {
                            Step25.setChecked(true);
                        }
                        if (result1.equals("26")) {
                            Step26.setChecked(true);
                        }


                    } catch (Exception CursorException) {
                        appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
                    }


                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {

                                case R.id.radio_step1:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db1 = DB.getReadableDatabase();
                                    db1.execSQL("UPDATE SDELKI SET STATUS = '1' WHERE ID ='" + selectedIDsdelka + "'; ");
                                    db1.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step2:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db2 = DB.getReadableDatabase();
                                    db2.execSQL("UPDATE SDELKI SET STATUS = '2' WHERE ID ='" + selectedIDsdelka + "';");
                                    db2.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step3:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db3 = DB.getReadableDatabase();
                                    db3.execSQL("UPDATE SDELKI SET STATUS = '3' WHERE ID ='" + selectedIDsdelka + "';");
                                    db3.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step4:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db4 = DB.getReadableDatabase();
                                    db4.execSQL("UPDATE SDELKI SET STATUS = '4' WHERE ID='" + selectedIDsdelka + "';");
                                    db4.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step5:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db5 = DB.getReadableDatabase();
                                    db5.execSQL("UPDATE SDELKI SET STATUS = '5' WHERE ID='" + selectedIDsdelka + "';");
                                    db5.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step6:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db6 = DB.getReadableDatabase();
                                    db6.execSQL("UPDATE SDELKI SET STATUS = '6' WHERE ID='" + selectedIDsdelka + "';");
                                    db6.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step7:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db7 = DB.getReadableDatabase();
                                    db7.execSQL("UPDATE SDELKI SET STATUS = '7' WHERE ID='" + selectedIDsdelka + "';");
                                    db7.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step8:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db8 = DB.getReadableDatabase();
                                    db8.execSQL("UPDATE SDELKI SET STATUS = '8' WHERE ID='" + selectedIDsdelka + "';");
                                    db8.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step9:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db9 = DB.getReadableDatabase();
                                    db9.execSQL("UPDATE SDELKI SET STATUS = '9' WHERE ID='" + selectedIDsdelka + "';");
                                    db9.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step10:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db10 = DB.getReadableDatabase();
                                    db10.execSQL("UPDATE SDELKI SET STATUS = '10' WHERE ID='" + selectedIDsdelka + "';");
                                    db10.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step11:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db11 = DB.getReadableDatabase();
                                    db11.execSQL("UPDATE SDELKI SET STATUS = '11' WHERE ID='" + selectedIDsdelka + "';");
                                    db11.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step12:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db12 = DB.getReadableDatabase();
                                    db12.execSQL("UPDATE SDELKI SET STATUS = '12' WHERE ID='" + selectedIDsdelka + "';");
                                    db12.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step13:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db13 = DB.getReadableDatabase();
                                    db13.execSQL("UPDATE SDELKI SET STATUS = '13' WHERE ID='" + selectedIDsdelka + "';");
                                    db13.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step14:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db14 = DB.getReadableDatabase();
                                    db14.execSQL("UPDATE SDELKI SET STATUS = '14' WHERE ID='" + selectedIDsdelka + "';");
                                    db14.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step15:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db15 = DB.getReadableDatabase();
                                    db15.execSQL("UPDATE SDELKI SET STATUS = '15' WHERE ID='" + selectedIDsdelka + "';");
                                    db15.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step16:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db16 = DB.getReadableDatabase();
                                    db16.execSQL("UPDATE SDELKI SET STATUS = '16' WHERE ID='" + selectedIDsdelka + "';");
                                    db16.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step17:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db17 = DB.getReadableDatabase();
                                    db17.execSQL("UPDATE SDELKI SET STATUS = '17' WHERE ID='" + selectedIDsdelka + "';");
                                    db17.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step18:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db18 = DB.getReadableDatabase();
                                    db18.execSQL("UPDATE SDELKI SET STATUS = '18' WHERE ID='" + selectedIDsdelka + "';");
                                    db18.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step19:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db19 = DB.getReadableDatabase();
                                    db19.execSQL("UPDATE SDELKI SET STATUS = '19' WHERE ID='" + selectedIDsdelka + "';");
                                    db19.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step20:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db20 = DB.getReadableDatabase();
                                    db20.execSQL("UPDATE SDELKI SET STATUS = '20' WHERE ID='" + selectedIDsdelka + "';");
                                    db20.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step21:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db21 = DB.getReadableDatabase();
                                    db21.execSQL("UPDATE SDELKI SET STATUS = '21' WHERE ID='" + selectedIDsdelka + "';");
                                    db21.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step22:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db22 = DB.getReadableDatabase();
                                    db22.execSQL("UPDATE SDELKI SET STATUS = '22' WHERE ID='" + selectedIDsdelka + "';");
                                    db22.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step23:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db23 = DB.getReadableDatabase();
                                    db23.execSQL("UPDATE SDELKI SET STATUS = '23' WHERE ID='" + selectedIDsdelka + "';");
                                    db23.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step24:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db24 = DB.getReadableDatabase();
                                    db24.execSQL("UPDATE SDELKI SET STATUS = '24' WHERE ID='" + selectedIDsdelka + "';");
                                    db24.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step25:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db25 = DB.getReadableDatabase();
                                    db25.execSQL("UPDATE SDELKI SET STATUS = '25' WHERE ID='" + selectedIDsdelka + "';");
                                    db25.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step26:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db26 = DB.getReadableDatabase();
                                    db26.execSQL("UPDATE SDELKI SET STATUS = '26' WHERE ID='" + selectedIDsdelka + "';");
                                    db26.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                default:
                                    break;
                            }
                        }
                    });



                    // Создание диалога
                    final AlertDialog editsdelkadialog = editstatussdelkabuilder.create();

                    editsdelkadialog.show();

                    return true;
                }

            });

            Z1SdelkiListAdapter.notifyDataSetChanged();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

        //endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (СДЕЛКИ С ПРОБЛЕМАМИ)

        //      СТАТУСЫ СДЕЛОК:
        //(1) Направлено коммерческое предложение
        //(2) Коммерческое предложение не устроило/отклонено/без ответа
        //(3) Договор на согласовании
        //(4) Договор подписан - этап исполнения сделки
        //(5) Отказ от заключения договора с нашей стороны
        //(6) Отказ от заключения договора со стороны Контрагента
        //(7) В процессе исполнения Договора - этап поставки товаров
        //(8) В процессе исполнения Договора - этап оказания услуг
        //(9) Договор подписан, ожидание предоплаты
        //(10) Договор подписан, ожидание предоплаты - оплаты товаров
        //(11) Договор подписан, ожидание предоплаты - оплаты услуг
        //(12) Ожидание частичной/полной оплаты - оплаты товаров
        //(13) Ожидание частичной/полной оплаты - оплаты услуг
        //(14) Ожидание полной оплаты по сделке
        //(15) Подписание закрывающих документов - подписание актов
        //(16) Подписание закрывающих документов - подписание накладных
        //(17) Подписание закрывающих документов (актов и/или накладных)
        //(18) Этап взыскания оплаты - направление Претензии
        //(19) Этап взыскания оплаты - Суд
        //(20) Этап взыскания оплаты - Исполнительное производство
        //(21) Сделка завершена УСПЕШНО
        //(22) Сделка завершена с проблемами
        //(23) Сделка не завершена
        //(24) Сделка не завершена - досрочное расторжение договора
        //(25) Сделка не завершена - по нашей вине
        //(26) Сделка не завершена - по вине Заказчика

        try {


            final ArrayList<Sdelki> list = new ArrayList<Sdelki>();

            SQLiteDatabase db = DB.getReadableDatabase();


            Cursor cursor = db.rawQuery("SELECT [SDELKA_NAME],[K_SOKRNAME], [MY_SOKRNAME], [ID] FROM SDELKI WHERE STATUS = '18' OR STATUS = '19' OR STATUS = '20' OR STATUS = '22' OR STATUS = '23' OR STATUS = '24' OR STATUS = '25' OR STATUS = '26' ORDER BY ID DESC", null); // запрос из базы реквизитов


            while (cursor.moveToNext()) {
                list.add(
                        new Sdelki(
                                cursor.getString(0),
                                "Контрагент: " + cursor.getString(1),
                                "Моя организация: " + cursor.getString(2),
                                cursor.getString(3)));
                // Toast.makeText(getActivity(),"Контрагент: "+cursor.getString(0) + " БАНК:
                // "+cursor.getString(1)+" ИНН: "+cursor.getString(2)+" ID
                // "+cursor.getString(3),Toast.LENGTH_LONG).show();
            }
            // MyFullName.setText(cursor.getString(0));


            ListView ListViewSdelkiSProblem = (ListView) rootView.findViewById(R.id.ListViewSDELKIPROBLEM);

            final SdelkiListAdapter Z1SdelkiListAdapter = new SdelkiListAdapter(getActivity(), list);
            ListViewSdelkiSProblem.setAdapter(Z1SdelkiListAdapter);

            ListViewSdelkiSProblem.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();

                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editsdelka((list.get(position)).getidnumber());

                            SdelkaID sdelkaid = new SdelkaID();
                            sdelkaid.setSdelkaID((list.get(position)).getidnumber());
                        }
                    });
            cursor.close();
            db.close();

            ListViewSdelkiSProblem.setOnItemLongClickListener(new OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {

                    final String selectedIDsdelka = list.get(position).getidnumber();

                    AlertDialog.Builder editstatussdelkabuilder = new AlertDialog.Builder(container.getContext());

                    View dialogView = inflater.inflate(R.layout.alertdialog_edit_statussdelka, null); //важно - inflater определен в начале кода фрагмента

                    editstatussdelkabuilder.setCancelable(true);

                    // Привязка xml-разметки окна диалогов
                    editstatussdelkabuilder.setView(dialogView);

                    //region ЗАПОЛНЕНИЯ ДИАЛОГА ДАННЫМИ ИЗ БД ДЛЯ РАДИОГРУППЫ СТАТУСА СДЕЛКИ

                    final RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.radiogroupSTATUS);
                    final RadioButton Step1 = (RadioButton) dialogView.findViewById(R.id.radio_step1);
                    final RadioButton Step2 = (RadioButton) dialogView.findViewById(R.id.radio_step2);
                    final RadioButton Step3 = (RadioButton) dialogView.findViewById(R.id.radio_step3);
                    final RadioButton Step4 = (RadioButton) dialogView.findViewById(R.id.radio_step4);
                    final RadioButton Step5 = (RadioButton) dialogView.findViewById(R.id.radio_step5);
                    final RadioButton Step6 = (RadioButton) dialogView.findViewById(R.id.radio_step6);
                    final RadioButton Step7 = (RadioButton) dialogView.findViewById(R.id.radio_step7);
                    final RadioButton Step8 = (RadioButton) dialogView.findViewById(R.id.radio_step8);
                    final RadioButton Step9 = (RadioButton) dialogView.findViewById(R.id.radio_step9);
                    final RadioButton Step10 = (RadioButton) dialogView.findViewById(R.id.radio_step10);
                    final RadioButton Step11 = (RadioButton) dialogView.findViewById(R.id.radio_step11);
                    final RadioButton Step12 = (RadioButton) dialogView.findViewById(R.id.radio_step12);
                    final RadioButton Step13 = (RadioButton) dialogView.findViewById(R.id.radio_step13);
                    final RadioButton Step14 = (RadioButton) dialogView.findViewById(R.id.radio_step14);
                    final RadioButton Step15 = (RadioButton) dialogView.findViewById(R.id.radio_step15);
                    final RadioButton Step16 = (RadioButton) dialogView.findViewById(R.id.radio_step16);
                    final RadioButton Step17 = (RadioButton) dialogView.findViewById(R.id.radio_step17);
                    final RadioButton Step18 = (RadioButton) dialogView.findViewById(R.id.radio_step18);
                    final RadioButton Step19 = (RadioButton) dialogView.findViewById(R.id.radio_step19);
                    final RadioButton Step20 = (RadioButton) dialogView.findViewById(R.id.radio_step20);
                    final RadioButton Step21 = (RadioButton) dialogView.findViewById(R.id.radio_step21);
                    final RadioButton Step22 = (RadioButton) dialogView.findViewById(R.id.radio_step22);
                    final RadioButton Step23 = (RadioButton) dialogView.findViewById(R.id.radio_step23);
                    final RadioButton Step24 = (RadioButton) dialogView.findViewById(R.id.radio_step24);
                    final RadioButton Step25 = (RadioButton) dialogView.findViewById(R.id.radio_step25);
                    final RadioButton Step26 = (RadioButton) dialogView.findViewById(R.id.radio_step26);


                    try {
                        SQLiteDatabase db = DB.getReadableDatabase();
                        Cursor cursor = db.rawQuery("SELECT [STATUS] FROM SDELKI WHERE ID = '" + selectedIDsdelka + "';", null);
                        cursor.moveToNext(); //без этого exception
                        String result1 = cursor.getString(0);
                        cursor.close();
                        db.close();
                        radioGroup.clearCheck();

                        if (result1.equals("1")) {
                            Step1.setChecked(true);
                        }
                        if (result1.equals("2")) {
                            Step2.setChecked(true);
                        }
                        if (result1.equals("3")) {
                            Step3.setChecked(true);
                        }
                        if (result1.equals("4")) {
                            Step4.setChecked(true);
                        }
                        if (result1.equals("5")) {
                            Step5.setChecked(true);
                        }
                        if (result1.equals("6")) {
                            Step6.setChecked(true);
                        }
                        if (result1.equals("7")) {
                            Step7.setChecked(true);
                        }
                        if (result1.equals("8")) {
                            Step8.setChecked(true);
                        }
                        if (result1.equals("9")) {
                            Step9.setChecked(true);
                        }
                        if (result1.equals("10")) {
                            Step10.setChecked(true);
                        }
                        if (result1.equals("11")) {
                            Step11.setChecked(true);
                        }
                        if (result1.equals("12")) {
                            Step12.setChecked(true);
                        }
                        if (result1.equals("13")) {
                            Step13.setChecked(true);
                        }
                        if (result1.equals("14")) {
                            Step14.setChecked(true);
                        }
                        if (result1.equals("15")) {
                            Step15.setChecked(true);
                        }
                        if (result1.equals("16")) {
                            Step16.setChecked(true);
                        }
                        if (result1.equals("17")) {
                            Step17.setChecked(true);
                        }
                        if (result1.equals("18")) {
                            Step18.setChecked(true);
                        }
                        if (result1.equals("19")) {
                            Step19.setChecked(true);
                        }
                        if (result1.equals("20")) {
                            Step20.setChecked(true);
                        }
                        if (result1.equals("21")) {
                            Step21.setChecked(true);

                        }
                        if (result1.equals("22")) {
                            Step22.setChecked(true);
                        }
                        if (result1.equals("23")) {
                            Step23.setChecked(true);
                        }
                        if (result1.equals("24")) {
                            Step24.setChecked(true);
                        }
                        if (result1.equals("25")) {
                            Step25.setChecked(true);
                        }
                        if (result1.equals("26")) {
                            Step26.setChecked(true);
                        }


                    } catch (Exception CursorException) {
                        appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
                    }


                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {

                                case R.id.radio_step1:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db1 = DB.getReadableDatabase();
                                    db1.execSQL("UPDATE SDELKI SET STATUS = '1' WHERE ID ='" + selectedIDsdelka + "'; ");
                                    db1.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step2:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db2 = DB.getReadableDatabase();
                                    db2.execSQL("UPDATE SDELKI SET STATUS = '2' WHERE ID ='" + selectedIDsdelka + "';");
                                    db2.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step3:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db3 = DB.getReadableDatabase();
                                    db3.execSQL("UPDATE SDELKI SET STATUS = '3' WHERE ID ='" + selectedIDsdelka + "';");
                                    db3.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step4:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db4 = DB.getReadableDatabase();
                                    db4.execSQL("UPDATE SDELKI SET STATUS = '4' WHERE ID='" + selectedIDsdelka + "';");
                                    db4.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step5:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db5 = DB.getReadableDatabase();
                                    db5.execSQL("UPDATE SDELKI SET STATUS = '5' WHERE ID='" + selectedIDsdelka + "';");
                                    db5.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step6:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db6 = DB.getReadableDatabase();
                                    db6.execSQL("UPDATE SDELKI SET STATUS = '6' WHERE ID='" + selectedIDsdelka + "';");
                                    db6.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;


                                case R.id.radio_step7:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db7 = DB.getReadableDatabase();
                                    db7.execSQL("UPDATE SDELKI SET STATUS = '7' WHERE ID='" + selectedIDsdelka + "';");
                                    db7.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step8:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db8 = DB.getReadableDatabase();
                                    db8.execSQL("UPDATE SDELKI SET STATUS = '8' WHERE ID='" + selectedIDsdelka + "';");
                                    db8.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step9:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db9 = DB.getReadableDatabase();
                                    db9.execSQL("UPDATE SDELKI SET STATUS = '9' WHERE ID='" + selectedIDsdelka + "';");
                                    db9.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step10:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db10 = DB.getReadableDatabase();
                                    db10.execSQL("UPDATE SDELKI SET STATUS = '10' WHERE ID='" + selectedIDsdelka + "';");
                                    db10.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step11:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db11 = DB.getReadableDatabase();
                                    db11.execSQL("UPDATE SDELKI SET STATUS = '11' WHERE ID='" + selectedIDsdelka + "';");
                                    db11.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step12:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db12 = DB.getReadableDatabase();
                                    db12.execSQL("UPDATE SDELKI SET STATUS = '12' WHERE ID='" + selectedIDsdelka + "';");
                                    db12.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step13:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db13 = DB.getReadableDatabase();
                                    db13.execSQL("UPDATE SDELKI SET STATUS = '13' WHERE ID='" + selectedIDsdelka + "';");
                                    db13.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step14:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db14 = DB.getReadableDatabase();
                                    db14.execSQL("UPDATE SDELKI SET STATUS = '14' WHERE ID='" + selectedIDsdelka + "';");
                                    db14.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step15:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db15 = DB.getReadableDatabase();
                                    db15.execSQL("UPDATE SDELKI SET STATUS = '15' WHERE ID='" + selectedIDsdelka + "';");
                                    db15.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step16:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db16 = DB.getReadableDatabase();
                                    db16.execSQL("UPDATE SDELKI SET STATUS = '16' WHERE ID='" + selectedIDsdelka + "';");
                                    db16.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step17:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db17 = DB.getReadableDatabase();
                                    db17.execSQL("UPDATE SDELKI SET STATUS = '17' WHERE ID='" + selectedIDsdelka + "';");
                                    db17.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step18:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db18 = DB.getReadableDatabase();
                                    db18.execSQL("UPDATE SDELKI SET STATUS = '18' WHERE ID='" + selectedIDsdelka + "';");
                                    db18.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step19:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db19 = DB.getReadableDatabase();
                                    db19.execSQL("UPDATE SDELKI SET STATUS = '19' WHERE ID='" + selectedIDsdelka + "';");
                                    db19.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step20:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db20 = DB.getReadableDatabase();
                                    db20.execSQL("UPDATE SDELKI SET STATUS = '20' WHERE ID='" + selectedIDsdelka + "';");
                                    db20.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step21:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db21 = DB.getReadableDatabase();
                                    db21.execSQL("UPDATE SDELKI SET STATUS = '21' WHERE ID='" + selectedIDsdelka + "';");
                                    db21.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step22:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db22 = DB.getReadableDatabase();
                                    db22.execSQL("UPDATE SDELKI SET STATUS = '22' WHERE ID='" + selectedIDsdelka + "';");
                                    db22.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step23:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db23 = DB.getReadableDatabase();
                                    db23.execSQL("UPDATE SDELKI SET STATUS = '23' WHERE ID='" + selectedIDsdelka + "';");
                                    db23.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step24:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db24 = DB.getReadableDatabase();
                                    db24.execSQL("UPDATE SDELKI SET STATUS = '24' WHERE ID='" + selectedIDsdelka + "';");
                                    db24.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step25:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db25 = DB.getReadableDatabase();
                                    db25.execSQL("UPDATE SDELKI SET STATUS = '25' WHERE ID='" + selectedIDsdelka + "';");
                                    db25.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                case R.id.radio_step26:
                                    DB = new SQLiteConnect(getActivity());
                                    SQLiteDatabase db26 = DB.getReadableDatabase();
                                    db26.execSQL("UPDATE SDELKI SET STATUS = '26' WHERE ID='" + selectedIDsdelka + "';");
                                    db26.close();
                                    Z1SdelkiListAdapter.notifyDataSetChanged();
                                    break;

                                default:
                                    break;
                            }
                        }
                    });



                    // Создание диалога
                    final AlertDialog editsdelkadialog = editstatussdelkabuilder.create();

                    editsdelkadialog.show();

                    return true;
                }

            });

            Z1SdelkiListAdapter.notifyDataSetChanged();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

//endregion

        //region СВОДНЫЕ ДАННЫЕ

        //      СТАТУСЫ СДЕЛОК:
        //(1) Направлено коммерческое предложение
        //(2) Коммерческое предложение не устроило/отклонено/без ответа
        //(3) Договор на согласовании
        //(4) Договор подписан - этап исполнения сделки
        //(5) Отказ от заключения договора с нашей стороны
        //(6) Отказ от заключения договора со стороны Контрагента
        //(7) В процессе исполнения Договора - этап поставки товаров
        //(8) В процессе исполнения Договора - этап оказания услуг
        //(9) Договор подписан, ожидание предоплаты
        //(10) Договор подписан, ожидание предоплаты - оплаты товаров
        //(11) Договор подписан, ожидание предоплаты - оплаты услуг
        //(12) Ожидание частичной/полной оплаты - оплаты товаров
        //(13) Ожидание частичной/полной оплаты - оплаты услуг
        //(14) Ожидание полной оплаты по сделке
        //(15) Подписание закрывающих документов - подписание актов
        //(16) Подписание закрывающих документов - подписание накладных
        //(17) Подписание закрывающих документов (актов и/или накладных)
        //(18) Этап взыскания оплаты - направление Претензии
        //(19) Этап взыскания оплаты - Суд
        //(20) Этап взыскания оплаты - Исполнительное производство
        //(21) Сделка завершена УСПЕШНО
        //(22) Сделка завершена с проблемами
        //(23) Сделка не завершена
        //(24) Сделка не завершена - досрочное расторжение договора
        //(25) Сделка не завершена - по нашей вине
        //(26) Сделка не завершена - по вине Заказчика

        try {


            SQLiteDatabase db_svd = DB.getReadableDatabase();
            Cursor cursor_svd = db_svd.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '4' OR STATUS = '7' OR STATUS = '8' OR STATUS = '9' OR STATUS = '10' OR STATUS = '11' OR STATUS = '15' OR STATUS = '16' OR STATUS = '17' OR STATUS='21';", null);

            Cursor cursor1_svd = db_svd.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI;", null);

            int NTU = 0;
            float Summ = 0;
            float SummTovari = 0;
            float SummUslugi = 0;
            SummStr = new TextStr();
            SummUslugiStr = new TextStr();
            SummTovariStr = new TextStr();
            SummStr.setTextStr("0");
            SummUslugiStr.setTextStr("0");
            SummTovariStr.setTextStr("0");


            while (cursor_svd.moveToNext()) {
                try {
                    Summ = Summ + Float.parseFloat(cursor_svd.getString(0));
                    SummTovari = SummTovari + Float.parseFloat(cursor_svd.getString(1));
                    SummUslugi = SummUslugi + Float.parseFloat(cursor_svd.getString(2));

                    SummStr.setTextStr(Float.toString(Summ));
                    SummTovariStr.setTextStr(Float.toString(SummTovari));
                    SummUslugiStr.setTextStr(Float.toString(SummUslugi));

                } catch (NumberFormatException ex) {
                }

                while (cursor1_svd.moveToNext()) {
                    NTU = NTU + 1;

                }


            }

            SvodnieDannieKolvoSdelok.setText(Integer.toString(NTU) + " сделок");

            cursor_svd.close();
            cursor1_svd.close();
            db_svd.close();

        } catch (Exception CursorException) {
            Log.d("ru.mastercond",CursorException.toString());
        }
        try {
            int SummInt = Math.round((int) Float.parseFloat(SummStr.getTextStr()));
            SvodnieDannieSumm.setText(Integer.toString(SummInt) + " ₽");

            int SummTovariInt = Math.round((int) Float.parseFloat(SummTovariStr.getTextStr()));
            SvodnieDannieSummTovarov.setText(Integer.toString(SummTovariInt) + " ₽");

            int SummUslugiInt = Math.round((int) Float.parseFloat(SummUslugiStr.getTextStr()));
            SvodnieDannieSummUslug.setText(Integer.toString(SummUslugiInt) + " ₽");


        } catch (NumberFormatException ex) {
        } catch (ArithmeticException e) {
        } catch (ArrayStoreException ex) {
        } catch (ClassCastException ex) {
        } catch (NullPointerException ex) {
        }


        //endregion

        //region ВОРОНКА ПРОДАЖ

        //      СТАТУСЫ СДЕЛОК:
        //(1) Направлено коммерческое предложение
        //(2) Коммерческое предложение не устроило/отклонено/без ответа
        //(3) Договор на согласовании
        //(4) Договор подписан - этап исполнения сделки
        //(5) Отказ от заключения договора с нашей стороны
        //(6) Отказ от заключения договора со стороны Контрагента
        //(7) В процессе исполнения Договора - этап поставки товаров
        //(8) В процессе исполнения Договора - этап оказания услуг
        //(9) Договор подписан, ожидание предоплаты
        //(10) Договор подписан, ожидание предоплаты - оплаты товаров
        //(11) Договор подписан, ожидание предоплаты - оплаты услуг
        //(12) Ожидание частичной/полной оплаты - оплаты товаров
        //(13) Ожидание частичной/полной оплаты - оплаты услуг
        //(14) Ожидание полной оплаты по сделке
        //(15) Подписание закрывающих документов - подписание актов
        //(16) Подписание закрывающих документов - подписание накладных
        //(17) Подписание закрывающих документов (актов и/или накладных)
        //(18) Этап взыскания оплаты - направление Претензии
        //(19) Этап взыскания оплаты - Суд
        //(20) Этап взыскания оплаты - Исполнительное производство
        //(21) Сделка завершена УСПЕШНО
        //(22) Сделка завершена с проблемами
        //(23) Сделка не завершена
        //(24) Сделка не завершена - досрочное расторжение договора
        //(25) Сделка не завершена - по нашей вине
        //(26) Сделка не завершена - по вине Заказчика

        try {


            SQLiteDatabase db_voronka = DB.getReadableDatabase();

            Cursor cursor = db_voronka.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '1';", null); //направлено предложений

            Cursor cursor1 = db_voronka.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '2' OR STATUS = '5' OR STATUS = '6';", null); //отказы

            Cursor cursor2 = db_voronka.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '3';", null); //на согласовании

            Cursor cursor3 = db_voronka.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '4' OR STATUS = '7' OR STATUS = '8' OR STATUS = '9' OR STATUS = '10' OR STATUS = '11' OR STATUS = '12' OR STATUS = '13' OR STATUS = '14' OR STATUS = '15' OR STATUS = '16' OR STATUS = '17';", null); //в работе

            int NTU = 0;
            int NTU1 = 0;
            int NTU2 = 0;
            int NTU3 = 0;


            float Summ = 0;
            float Summ1 = 0;
            float Summ2 = 0;
            float Summ3 = 0;


            while (cursor.moveToNext()) {
                try {
                    NTU++;
                    Summ = Summ + Float.parseFloat(cursor.getString(0));
                } catch (NumberFormatException ex) {
                }
            }

            int Summint = Math.round(Summ);
            VoronkaPredlozhenija.setText(Integer.toString(NTU) + " на " + Integer.toString(Summint) + " ₽");

            while (cursor1.moveToNext()) {
                try {
                    NTU1++;
                    Summ1 = Summ1 + Float.parseFloat(cursor1.getString(0));
                } catch (NumberFormatException ex) {
                }
            }

            int Summ1int = Math.round(Summ1);
            VoronkaOtkazi.setText(Integer.toString(NTU1) + " на " + Integer.toString(Summ1int) + " ₽");

            while (cursor2.moveToNext()) {
                try {
                    NTU2++;
                    Summ2 = Summ2 + Float.parseFloat(cursor2.getString(0));
                } catch (NumberFormatException ex) {
                    Log.d ("ru.mastercond", "Ошибка вычисления суммы сделок на согласовании: "+ex.toString());
                }
            }

            int Summ2int = Math.round(Summ2);
            VoronkaSoglasovanie.setText(Integer.toString(NTU2) + " на " + Integer.toString(Summ2int) + " ₽");

            while (cursor3.moveToNext()) {
                try {
                    NTU3++;
                    Summ3 = Summ3 + Float.parseFloat(cursor3.getString(0));
                } catch (NumberFormatException ex) {
                }
            }

            int Summ3int = Math.round(Summ3);
            VoronkaVRabote.setText(Integer.toString(NTU3) + " на " + Integer.toString(Summ3int) + " ₽");

            try {
                int NTUSumm = NTU + NTU1 + NTU2 + NTU3;
                float s1 = 136 * NTU / NTUSumm;
                float s2 = 136 * NTU1 / NTUSumm;
                float s3 = 136 * NTU2 / NTUSumm;
                float s4 = 136 * NTU3 / NTUSumm;

                int proc = (int) Math.round(s1); //подсчитываем количество для заполнения ряда диаграммы
                int proc1 = (int) Math.round(s2); //подсчитываем количество для заполнения ряда диаграммы
                int proc2 = (int) Math.round(s3); //подсчитываем количество для заполнения ряда диаграммы
                int proc3 = (int) Math.round(s4); //подсчитываем количество для заполнения ряда диаграммы

                LinearLayout.LayoutParams Vparams1 = (LinearLayout.LayoutParams) VoronkaProc1.getLayoutParams();
                Vparams1.width = (int) (proc * (getResources().getDisplayMetrics().density));
                VoronkaProc1.setLayoutParams(Vparams1);

                LinearLayout.LayoutParams Vparams11 = (LinearLayout.LayoutParams) VoronkaProc11.getLayoutParams();
                Vparams11.width = (int) (proc * (getResources().getDisplayMetrics().density));
                VoronkaProc11.setLayoutParams(Vparams11);

                LinearLayout.LayoutParams Vparams111 = (LinearLayout.LayoutParams) VoronkaProc111.getLayoutParams();
                Vparams111.width = (int) (proc * (getResources().getDisplayMetrics().density));
                VoronkaProc111.setLayoutParams(Vparams111);


                LinearLayout.LayoutParams Vparams2 = (LinearLayout.LayoutParams) VoronkaProc2.getLayoutParams();
                Vparams2.width = (int) (proc1 * (getResources().getDisplayMetrics().density));
                VoronkaProc2.setLayoutParams(Vparams2);

                LinearLayout.LayoutParams Vparams22 = (LinearLayout.LayoutParams) VoronkaProc22.getLayoutParams();
                Vparams22.width = (int) (proc1 * (getResources().getDisplayMetrics().density));
                VoronkaProc22.setLayoutParams(Vparams22);


                LinearLayout.LayoutParams Vparams3 = (LinearLayout.LayoutParams) VoronkaProc3.getLayoutParams();
                Vparams3.width = (int) (proc2 * (getResources().getDisplayMetrics().density));
                VoronkaProc3.setLayoutParams(Vparams3);

                LinearLayout.LayoutParams Vparams33 = (LinearLayout.LayoutParams) VoronkaProc33.getLayoutParams();
                Vparams33.width = (int) (proc2 * (getResources().getDisplayMetrics().density));
                VoronkaProc33.setLayoutParams(Vparams33);


                LinearLayout.LayoutParams Vparams4 = (LinearLayout.LayoutParams) VoronkaProc4.getLayoutParams();
                Vparams4.width = (int) (proc3 * (getResources().getDisplayMetrics().density));
                VoronkaProc4.setLayoutParams(Vparams4);

                LinearLayout.LayoutParams Vparams44 = (LinearLayout.LayoutParams) VoronkaProc44.getLayoutParams();
                Vparams44.width = (int) (proc3 * (getResources().getDisplayMetrics().density));
                VoronkaProc44.setLayoutParams(Vparams44);
            } catch (Exception ex) {
            }

            cursor.close();
            cursor1.close();
            cursor2.close();
            cursor3.close();

            db_voronka.close();


        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

        try {
            int SummInt = Math.round((int) Float.parseFloat(VSummStr.getTextStr()));
            //SvodnieDannieSumm.setText(Integer.toString(SummInt)+" ₽");

            int SummTovariInt = Math.round((int) Float.parseFloat(VSummTovariStr.getTextStr()));
            //SvodnieDannieSummTovarov.setText(Integer.toString(SummTovariInt)+" ₽");

            int SummUslugiInt = Math.round((int) Float.parseFloat(VSummUslugiStr.getTextStr()));
            //SvodnieDannieSummUslug.setText(Integer.toString(SummUslugiInt)+" ₽");


        } catch (NumberFormatException ex) {
        } catch (ArithmeticException e) {
        } catch (ArrayStoreException ex) {
        } catch (ClassCastException ex) {
        } catch (NullPointerException ex) {
        }


        //endregion

        //region КНОПКИ ОТОБРАЖЕНИЯ ПОЛНОГО СПИСКА В КАРТОЧКАХ

        Button NORMDOClistfull = (Button) rootView.findViewById(R.id.butonnormdoclist_full);
        NORMDOClistfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.closenormdoc();


            }
        });

        Button ZAMETKAlistfull = (Button) rootView.findViewById(R.id.butonzametkilist_full);
        ZAMETKAlistfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.closezametka();


            }
        });

        Button KONTRAGENTIlistfull = (Button) rootView.findViewById(R.id.buttonkontragentilist_full);
        KONTRAGENTIlistfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.closekontragent();


            }
        });


        Button MYFIRMSlistfull = (Button) rootView.findViewById(R.id.buttonmyfirmslist_full);
        MYFIRMSlistfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.closemyfirm();


            }
        });


        MYSDELKIlistfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.sdelkiclose();


            }
        });


        MYSDELKIlistfull2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.sdelkiclose();


            }
        });

        Button MYSDELKIlistfull3 = (Button) rootView.findViewById(R.id.buttonsdelkiproblem_full);
        MYSDELKIlistfull3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.sdelkiclose();


            }
        });

        //endregion

        //region КНОПКИ В ДИАГРАММАХ

        Button DiagramPoKolvu=(Button)rootView.findViewById(R.id.buttondiagrammpokolvu);
        Button DiagramPoKachestvu=(Button)rootView.findViewById(R.id.buttondiagrammpokachestvu);

        DiagramPoKolvu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    SQLiteDatabase db_voronka1 = DB.getReadableDatabase();

                    Cursor cursor = db_voronka1.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '1';", null); //направлено предложений

                    Cursor cursor1 = db_voronka1.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '2' OR STATUS = '5' OR STATUS = '6';", null); //отказы

                    Cursor cursor2 = db_voronka1.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '3';", null); //на согласовании

                    Cursor cursor3 = db_voronka1.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '4' OR STATUS = '7' OR STATUS = '8' OR STATUS = '9' OR STATUS = '10' OR STATUS = '11' OR STATUS = '12' OR STATUS = '13' OR STATUS = '14' OR STATUS = '15' OR STATUS = '16' OR STATUS = '17';", null); //в работе

                    int NTU = 0;
                    int NTU1 = 0;
                    int NTU2 = 0;
                    int NTU3 = 0;


                    float Summ = 0;
                    float Summ1 = 0;
                    float Summ2 = 0;
                    float Summ3 = 0;


                    while (cursor.moveToNext()) {
                        try {
                            NTU++;
                            Summ = Summ + Float.parseFloat(cursor.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summint = Math.round(Summ);
                    VoronkaPredlozhenija.setText(Integer.toString(NTU) + " на " + Integer.toString(Summint) + " ₽");

                    while (cursor1.moveToNext()) {
                        try {
                            NTU1++;
                            Summ1 = Summ1 + Float.parseFloat(cursor1.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summ1int = Math.round(Summ1);
                    VoronkaOtkazi.setText(Integer.toString(NTU1) + " на " + Integer.toString(Summ1int) + " ₽");

                    while (cursor2.moveToNext()) {
                        try {
                            NTU2++;
                            Summ2 = Summ2 + Float.parseFloat(cursor2.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summ2int = Math.round(Summ2);
                    VoronkaSoglasovanie.setText(Integer.toString(NTU2) + " на " + Integer.toString(Summ2int) + " ₽");

                    while (cursor3.moveToNext()) {
                        try {
                            NTU3++;
                            Summ3 = Summ3 + Float.parseFloat(cursor3.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summ3int = Math.round(Summ3);
                    VoronkaVRabote.setText(Integer.toString(NTU3) + " на " + Integer.toString(Summ3int) + " ₽");

                    try {
                        int NTUSumm = NTU + NTU1 + NTU2 + NTU3;
                        float s1 = 136 * NTU / NTUSumm;
                        float s2 = 136 * NTU1 / NTUSumm;
                        float s3 = 136 * NTU2 / NTUSumm;
                        float s4 = 136 * NTU3 / NTUSumm;

                        int proc = (int) Math.round(s1); //подсчитываем количество для заполнения ряда диаграммы
                        int proc1 = (int) Math.round(s2); //подсчитываем количество для заполнения ряда диаграммы
                        int proc2 = (int) Math.round(s3); //подсчитываем количество для заполнения ряда диаграммы
                        int proc3 = (int) Math.round(s4); //подсчитываем количество для заполнения ряда диаграммы

                        LinearLayout.LayoutParams Vparams1 = (LinearLayout.LayoutParams) VoronkaProc1.getLayoutParams();
                        Vparams1.width = (int) (proc * (getResources().getDisplayMetrics().density));
                        VoronkaProc1.setLayoutParams(Vparams1);

                        LinearLayout.LayoutParams Vparams11 = (LinearLayout.LayoutParams) VoronkaProc11.getLayoutParams();
                        Vparams11.width = (int) (proc * (getResources().getDisplayMetrics().density));
                        VoronkaProc11.setLayoutParams(Vparams11);

                        LinearLayout.LayoutParams Vparams111 = (LinearLayout.LayoutParams) VoronkaProc111.getLayoutParams();
                        Vparams111.width = (int) (proc * (getResources().getDisplayMetrics().density));
                        VoronkaProc111.setLayoutParams(Vparams111);


                        LinearLayout.LayoutParams Vparams2 = (LinearLayout.LayoutParams) VoronkaProc2.getLayoutParams();
                        Vparams2.width = (int) (proc1 * (getResources().getDisplayMetrics().density));
                        VoronkaProc2.setLayoutParams(Vparams2);

                        LinearLayout.LayoutParams Vparams22 = (LinearLayout.LayoutParams) VoronkaProc22.getLayoutParams();
                        Vparams22.width = (int) (proc1 * (getResources().getDisplayMetrics().density));
                        VoronkaProc22.setLayoutParams(Vparams22);


                        LinearLayout.LayoutParams Vparams3 = (LinearLayout.LayoutParams) VoronkaProc3.getLayoutParams();
                        Vparams3.width = (int) (proc2 * (getResources().getDisplayMetrics().density));
                        VoronkaProc3.setLayoutParams(Vparams3);

                        LinearLayout.LayoutParams Vparams33 = (LinearLayout.LayoutParams) VoronkaProc33.getLayoutParams();
                        Vparams33.width = (int) (proc2 * (getResources().getDisplayMetrics().density));
                        VoronkaProc33.setLayoutParams(Vparams33);


                        LinearLayout.LayoutParams Vparams4 = (LinearLayout.LayoutParams) VoronkaProc4.getLayoutParams();
                        Vparams4.width = (int) (proc3 * (getResources().getDisplayMetrics().density));
                        VoronkaProc4.setLayoutParams(Vparams4);

                        LinearLayout.LayoutParams Vparams44 = (LinearLayout.LayoutParams) VoronkaProc44.getLayoutParams();
                        Vparams44.width = (int) (proc3 * (getResources().getDisplayMetrics().density));
                        VoronkaProc44.setLayoutParams(Vparams44);
                    } catch (Exception ex) {
                    }

                    cursor.close();
                    cursor1.close();
                    cursor2.close();
                    cursor3.close();

                    db_voronka1.close();


                } catch (Exception CursorException) {
                    appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
                }

                try {
                    int SummInt = Math.round((int) Float.parseFloat(VSummStr.getTextStr()));
                    //SvodnieDannieSumm.setText(Integer.toString(SummInt)+" ₽");

                    int SummTovariInt = Math.round((int) Float.parseFloat(VSummTovariStr.getTextStr()));
                    //SvodnieDannieSummTovarov.setText(Integer.toString(SummTovariInt)+" ₽");

                    int SummUslugiInt = Math.round((int) Float.parseFloat(VSummUslugiStr.getTextStr()));
                    //SvodnieDannieSummUslug.setText(Integer.toString(SummUslugiInt)+" ₽");


                } catch (NumberFormatException ex) {
                } catch (ArithmeticException e) {
                } catch (ArrayStoreException ex) {
                } catch (ClassCastException ex) {
                } catch (NullPointerException ex) {
                }
            }
        });

        DiagramPoKachestvu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    SQLiteDatabase db_voronka2 = DB.getReadableDatabase();

                    Cursor cursor = db_voronka2.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '1';", null); //направлено предложений

                    Cursor cursor1 = db_voronka2.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '2' OR STATUS = '5' OR STATUS = '6';", null); //отказы

                    Cursor cursor2 = db_voronka2.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '3';", null); //на согласовании

                    Cursor cursor3 = db_voronka2.rawQuery("SELECT [STOIMOSTSDELKI],[STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI] FROM SDELKI WHERE STATUS = '4' OR STATUS = '7' OR STATUS = '8' OR STATUS = '9' OR STATUS = '10' OR STATUS = '11' OR STATUS = '12' OR STATUS = '13' OR STATUS = '14' OR STATUS = '15' OR STATUS = '16' OR STATUS = '17';", null); //в работе

                    int NTU = 0;
                    int NTU1 = 0;
                    int NTU2 = 0;
                    int NTU3 = 0;


                    float Summ = 0;
                    float Summ1 = 0;
                    float Summ2 = 0;
                    float Summ3 = 0;


                    while (cursor.moveToNext()) {
                        try {
                            NTU++;
                            Summ = Summ + Float.parseFloat(cursor.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summint = Math.round(Summ);
                    VoronkaPredlozhenija.setText(Integer.toString(NTU) + " на " + Integer.toString(Summint) + " ₽");

                    while (cursor1.moveToNext()) {
                        try {
                            NTU1++;
                            Summ1 = Summ1 + Float.parseFloat(cursor1.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summ1int = Math.round(Summ1);
                    VoronkaOtkazi.setText(Integer.toString(NTU1) + " на " + Integer.toString(Summ1int) + " ₽");

                    while (cursor2.moveToNext()) {
                        try {
                            NTU2++;
                            Summ2 = Summ2 + Float.parseFloat(cursor2.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summ2int = Math.round(Summ2);
                    VoronkaSoglasovanie.setText(Integer.toString(NTU2) + " на " + Integer.toString(Summ2int) + " ₽");

                    while (cursor3.moveToNext()) {
                        try {
                            NTU3++;
                            Summ3 = Summ3 + Float.parseFloat(cursor3.getString(0));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    int Summ3int = Math.round(Summ3);
                    VoronkaVRabote.setText(Integer.toString(NTU3) + " на " + Integer.toString(Summ3int) + " ₽");

                    try {
                        int SummAllint = Summint + Summ1int + Summ2int + Summ3int;
                        float s1 = 136 * Summint / SummAllint;
                        float s2 = 136 * Summ1int / SummAllint;
                        float s3 = 136 * Summ2int / SummAllint;
                        float s4 = 136 * Summ3int / SummAllint;

                        int proc = (int) Math.round(s1); //подсчитываем количество для заполнения ряда диаграммы
                        int proc1 = (int) Math.round(s2); //подсчитываем количество для заполнения ряда диаграммы
                        int proc2 = (int) Math.round(s3); //подсчитываем количество для заполнения ряда диаграммы
                        int proc3 = (int) Math.round(s4); //подсчитываем количество для заполнения ряда диаграммы

                        LinearLayout.LayoutParams Vparams1 = (LinearLayout.LayoutParams) VoronkaProc1.getLayoutParams();
                        Vparams1.width = (int) (proc * (getResources().getDisplayMetrics().density));
                        VoronkaProc1.setLayoutParams(Vparams1);

                        LinearLayout.LayoutParams Vparams11 = (LinearLayout.LayoutParams) VoronkaProc11.getLayoutParams();
                        Vparams11.width = (int) (proc * (getResources().getDisplayMetrics().density));
                        VoronkaProc11.setLayoutParams(Vparams11);

                        LinearLayout.LayoutParams Vparams111 = (LinearLayout.LayoutParams) VoronkaProc111.getLayoutParams();
                        Vparams111.width = (int) (proc * (getResources().getDisplayMetrics().density));
                        VoronkaProc111.setLayoutParams(Vparams111);


                        LinearLayout.LayoutParams Vparams2 = (LinearLayout.LayoutParams) VoronkaProc2.getLayoutParams();
                        Vparams2.width = (int) (proc1 * (getResources().getDisplayMetrics().density));
                        VoronkaProc2.setLayoutParams(Vparams2);

                        LinearLayout.LayoutParams Vparams22 = (LinearLayout.LayoutParams) VoronkaProc22.getLayoutParams();
                        Vparams22.width = (int) (proc1 * (getResources().getDisplayMetrics().density));
                        VoronkaProc22.setLayoutParams(Vparams22);


                        LinearLayout.LayoutParams Vparams3 = (LinearLayout.LayoutParams) VoronkaProc3.getLayoutParams();
                        Vparams3.width = (int) (proc2 * (getResources().getDisplayMetrics().density));
                        VoronkaProc3.setLayoutParams(Vparams3);

                        LinearLayout.LayoutParams Vparams33 = (LinearLayout.LayoutParams) VoronkaProc33.getLayoutParams();
                        Vparams33.width = (int) (proc2 * (getResources().getDisplayMetrics().density));
                        VoronkaProc33.setLayoutParams(Vparams33);


                        LinearLayout.LayoutParams Vparams4 = (LinearLayout.LayoutParams) VoronkaProc4.getLayoutParams();
                        Vparams4.width = (int) (proc3 * (getResources().getDisplayMetrics().density));
                        VoronkaProc4.setLayoutParams(Vparams4);

                        LinearLayout.LayoutParams Vparams44 = (LinearLayout.LayoutParams) VoronkaProc44.getLayoutParams();
                        Vparams44.width = (int) (proc3 * (getResources().getDisplayMetrics().density));
                        VoronkaProc44.setLayoutParams(Vparams44);
                    } catch (Exception ex) {
                    }

                    cursor.close();
                    cursor1.close();
                    cursor2.close();
                    cursor3.close();


                    db_voronka2.close();


                } catch (Exception CursorException) {
                    appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
                }

                try {
                    int SummInt = Math.round((int) Float.parseFloat(VSummStr.getTextStr()));
                    //SvodnieDannieSumm.setText(Integer.toString(SummInt)+" ₽");

                    int SummTovariInt = Math.round((int) Float.parseFloat(VSummTovariStr.getTextStr()));
                    //SvodnieDannieSummTovarov.setText(Integer.toString(SummTovariInt)+" ₽");

                    int SummUslugiInt = Math.round((int) Float.parseFloat(VSummUslugiStr.getTextStr()));
                    //SvodnieDannieSummUslug.setText(Integer.toString(SummUslugiInt)+" ₽");


                } catch (NumberFormatException ex) {
                } catch (ArithmeticException e) {
                } catch (ArrayStoreException ex) {
                } catch (ClassCastException ex) {
                } catch (NullPointerException ex) {
                }
            }
        });

//endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (САЙТЫ)

        try {

            final ArrayList<ZAMETKI> list = new ArrayList<ZAMETKI>();

            SQLiteDatabase db = DB.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT [NAME],[ADDRES], [ID] FROM SITES ORDER BY ID DESC LIMIT 20", null);



            while (cursor.moveToNext()) {
                list.add(
                        new ZAMETKI(
                                cursor.getString(0),
                                "",
                                "",
                                cursor.getString(1),
                                "",
                                cursor.getString(2)));


            }


            ListView ListViewSAJTI = (ListView) rootView.findViewById(R.id.ListViewSajti);

            ListViewSAJTI.setAdapter(new ZAMETKIListAdapter(getActivity(), list));

            ListViewSAJTI.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                            //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
                            SiteSName siteid = new SiteSName();
                            siteid.setSiteSName(
                                    (list.get(position)).getidnumber());

                            appActivity.editsite((list.get(position)).getidnumber());


                        }
                    });

            cursor.close();
            db.close();

        } catch (Exception CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
        }

        //endregion

        return rootView;
    }







    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d("ru.mastercond", "Fragment.onAttach()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("ru.mastercond", "Fragment.onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d("ru.mastercond", "Fragment.onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(getActivity(), "Fragment.onResume()",Toast.LENGTH_LONG).show();
        Log.d("ru.mastercond", "Fragment.onResume()");

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("ru.mastercond", "Fragment.onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d("ru.mastercond", "Fragment.onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("ru.mastercond", "Fragment.onDestroyView()");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("ru.mastercond", "Fragment.onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d("ru.mastercond", "Fragment.onDetach()");
    }


}
