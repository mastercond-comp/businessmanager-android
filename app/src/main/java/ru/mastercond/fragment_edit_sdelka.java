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
import android.database.StaleDataException;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import ru.mastercond.Documents_MakeDogovor;
import ru.mastercond.Documents_MakeSchet;
import ru.mastercond.Documents_MakeKP;
import ru.mastercond.Documents_MakeAkt;
import ru.mastercond.Documents_MakeNakladnaja;
import ru.mastercond.MainActivity;
import ru.mastercond.MenuOpened;
import ru.mastercond.R;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SdelkaID;
import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;
import ru.mastercond.SetDynamicHeightListView;
import ru.mastercond.SummaToString;
import ru.mastercond.TOVARIUSLUGI;
import ru.mastercond.TOVARIUSLUGIListAdapter;
import ru.mastercond.TextStr;
import ru.mastercond.ZAMETKI;
import ru.mastercond.ZAMETKIdialogListAdapter;


public class fragment_edit_sdelka extends Fragment {

    private SQLiteConnect DB;
    private SetDynamicHeightListView SetDListView;
    private Documents_MakeSchet Schet;
    private Documents_MakeDogovor Dogovor;
    private Documents_MakeKP KP;
    private Documents_MakeAkt Akt;
    private Documents_MakeNakladnaja Nakladnaja;
    private TextStr S6;
    private TextStr S4;
    private TextStr Sst;
    private TextStr TUaddStatus;
    private TextStr TUeditStatus;
    private TextStr SummTovari;
    private SummaToString mo;

    public fragment_edit_sdelka() {
    }

    private ArrayList<Sdelki> kontragentlist;
    private ArrayList<Sdelki> myorglist;
    private ArrayList<Sdelki> uslovijalist;

    private ArrayList<ZAMETKI> listzametki;

    private ArrayList<TOVARIUSLUGI> tovariuslugilist;

    private ArrayList idArray1;
    private ArrayList idArray2;
    private ArrayList idArray3;

    AlertDialog.Builder dialogkontragent;
    AlertDialog.Builder dialogmyorg;
    AlertDialog.Builder sdelkauslovija;
    AlertDialog.Builder addel;
    AlertDialog.Builder addzametkabuilder;
    AlertDialog.Builder editzametkabuilder;
    AlertDialog.Builder selectkontragentbuilder;
    AlertDialog.Builder selectmyorgbuilder;
    AlertDialog.Builder addtovaruslugabuilder;
    AlertDialog.Builder edittovaruslugabuilder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_edit_sdelka, container, false);

        //region ПЕРЕМЕННЫЕ
        DB = new SQLiteConnect(getActivity());
        SetDListView = new SetDynamicHeightListView();
        S6 = new TextStr();
        S4 = new TextStr();
        Sst = new TextStr();
        TUaddStatus = new TextStr();
        TUeditStatus = new TextStr();
        Schet = new Documents_MakeSchet();
        Dogovor = new Documents_MakeDogovor();
        Akt = new Documents_MakeAkt();
        Nakladnaja = new Documents_MakeNakladnaja();
        KP = new Documents_MakeKP();

        final MainActivity rootActivity = (MainActivity) getActivity();
        rootActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

        final String ID = rootActivity.getsdelkaid();


        final ArrayList<ZAMETKI> listzametki = new ArrayList<ZAMETKI>();
        final ListView ListViewZAMETKI = (ListView) rootView.findViewById(R.id.ListViewZAMETKI);
        final ZAMETKIdialogListAdapter Zadapter = new ZAMETKIdialogListAdapter(getActivity(), listzametki);
        final ArrayList idArrayZametki = new ArrayList();

        final ArrayList<TOVARIUSLUGI> tovariuslugilist = new ArrayList<TOVARIUSLUGI>();
        final ListView ListViewTOVARIUSLUGI = (ListView) rootView.findViewById(R.id.ListViewTOVARIUSLUGI);
        final TOVARIUSLUGIListAdapter TUadapter = new TOVARIUSLUGIListAdapter(getActivity(), tovariuslugilist);
        final ArrayList idArrayTovariUslugi = new ArrayList();

        final TextView StoimostTovarov=(TextView)rootView.findViewById(R.id.stoimost_tovarov);
        final TextView StoimostUslug=(TextView)rootView.findViewById(R.id.stoimost_uslug);
        final TextView StoimostItogo=(TextView)rootView.findViewById(R.id.stoimost_itogo);

        final EditText ETSdelkaName = (EditText) rootView.findViewById(R.id.sdelka_name);

        final EditText KontragentFullName = (EditText) rootView.findViewById(R.id.kontragent_myfullname);
        final EditText KontragentSokrName = (EditText) rootView.findViewById(R.id.kontragent_mysokrname);
        final EditText KontragentINN = (EditText) rootView.findViewById(R.id.kontragent_INN);
        final EditText KontragentKPP = (EditText) rootView.findViewById(R.id.kontragent_KPP);
        final EditText KontragentOGRN = (EditText) rootView.findViewById(R.id.kontragent_OGRN);
        final EditText KontragentBankName = (EditText) rootView.findViewById(R.id.kontragent_bankname);
        final EditText KontragentBankBIK = (EditText) rootView.findViewById(R.id.kontragent_bankbik);
        final EditText KontragentBankKS = (EditText) rootView.findViewById(R.id.kontragent_bankks);
        final EditText KontragentBankRS = (EditText) rootView.findViewById(R.id.kontragent_RS);
        final EditText KontragentRukDolzhn = (EditText) rootView.findViewById(R.id.kontragent_rukdolzhnost);
        final EditText KontragentVlice = (EditText) rootView.findViewById(R.id.kontragent_vlice);
        final EditText KontragentFIOruk = (EditText) rootView.findViewById(R.id.kontragent_FIOruksokr);
        final EditText KontragentUrAddr = (EditText) rootView.findViewById(R.id.kontragent_URaddr);
        final EditText KontragentFaktAddr = (EditText) rootView.findViewById(R.id.kontragent_FACTaddr);
        final EditText KontragentPostAddr = (EditText) rootView.findViewById(R.id.kontragent_POSTaddr);
        final EditText KontragentPhone = (EditText) rootView.findViewById(R.id.kontragent_Phone);
        final EditText KontragentMobile = (EditText) rootView.findViewById(R.id.kontragent_MobPhone);
        final EditText KontragentEmail = (EditText) rootView.findViewById(R.id.kontragent_Email);
        final EditText KontragentSite = (EditText) rootView.findViewById(R.id.kontragent_www);
        final EditText KontragentOtvetstvennij = (EditText) rootView.findViewById(R.id.kontragent_otvetstvennij);

        final EditText MyFullName = (EditText) rootView.findViewById(R.id.my_fullname);
        final EditText MySokrName = (EditText) rootView.findViewById(R.id.my_sokrname);
        final EditText MyINN = (EditText) rootView.findViewById(R.id.my_INN);
        final EditText MyKPP = (EditText) rootView.findViewById(R.id.my_KPP);
        final EditText MyOGRN = (EditText) rootView.findViewById(R.id.my_OGRN);
        final EditText MyBankName = (EditText) rootView.findViewById(R.id.my_bankname);
        final EditText MyBankBIK = (EditText) rootView.findViewById(R.id.my_bankbik);
        final EditText MyBankKS = (EditText) rootView.findViewById(R.id.my_bankks);
        final EditText MyBankRS = (EditText) rootView.findViewById(R.id.my_RS);
        final EditText MyRukDolzhn = (EditText) rootView.findViewById(R.id.my_rukdolzhnost);
        final EditText MyVlice = (EditText) rootView.findViewById(R.id.my_vlice);
        final EditText MyFIOruk = (EditText) rootView.findViewById(R.id.my_FIOruksokr);
        final EditText MyUrAddr = (EditText) rootView.findViewById(R.id.my_URaddr);
        final EditText MyFaktAddr = (EditText) rootView.findViewById(R.id.my_FACTaddr);
        final EditText MyPostAddr = (EditText) rootView.findViewById(R.id.my_POSTaddr);
        final EditText MyPhone = (EditText) rootView.findViewById(R.id.my_Phone);
        final EditText MyMobile = (EditText) rootView.findViewById(R.id.my_MobPhone);
        final EditText MyEmail = (EditText) rootView.findViewById(R.id.my_Email);
        final EditText MySite = (EditText) rootView.findViewById(R.id.my_www);
        final EditText MyOtvetstvennij = (EditText) rootView.findViewById(R.id.my_otvetstvennij);
        final EditText MyLogo = (EditText) rootView.findViewById(R.id.my_logo);
        final EditText MyPechat = (EditText) rootView.findViewById(R.id.my_pechat);
        final EditText MySlogan = (EditText) rootView.findViewById(R.id.my_slogan);

        final EditText ETDataDogovora = (EditText) rootView.findViewById(R.id.datadogovora);
        final EditText ETNomerDogovora = (EditText) rootView.findViewById(R.id.nomerdogovora);
        final EditText ETGorodDogovora = (EditText) rootView.findViewById(R.id.goroddogovora);
        final EditText ETUslugiPo = (EditText) rootView.findViewById(R.id.uslugipo);
        final AutoCompleteTextView ETSrokiPostavkiTovarov = (AutoCompleteTextView) rootView.findViewById(R.id.srokipostavkitovarov);
        final AutoCompleteTextView ETSrokiOkazanijaUslug = (AutoCompleteTextView) rootView.findViewById(R.id.srokiokazanijauslug);
        final AutoCompleteTextView ETUslovijaOplatiTovarov = (AutoCompleteTextView) rootView.findViewById(R.id.uslovijaoplatitovarov);
        final AutoCompleteTextView ETUslovijaOplatiUslug = (AutoCompleteTextView) rootView.findViewById(R.id.uslovijaoplatiuslug);
        final AutoCompleteTextView ETUslovijaPriemkiTovarov = (AutoCompleteTextView) rootView.findViewById(R.id.uslovijaprijemkitovarov);
        final AutoCompleteTextView ETUslovijaPriemkiUslug = (AutoCompleteTextView) rootView.findViewById(R.id.uslovijaprijemkiuslug);
        final AutoCompleteTextView ETUslovijaGarantii = (AutoCompleteTextView) rootView.findViewById(R.id.uslovijagarantii);
        final AutoCompleteTextView ETOsobijeUslovija = (AutoCompleteTextView) rootView.findViewById(R.id.osobieuslovija);
        final AutoCompleteTextView ETSud = (AutoCompleteTextView) rootView.findViewById(R.id.arbitrsud);

        final EditText ETSchetNomer = (EditText) rootView.findViewById(R.id.schet_nomer);
        final EditText ETSchetData = (EditText) rootView.findViewById(R.id.schet_data);
        final EditText ETKPNomer = (EditText) rootView.findViewById(R.id.kp_nomer);
        final EditText ETKPData = (EditText) rootView.findViewById(R.id.kp_data);
        final EditText ETAktNomer = (EditText) rootView.findViewById(R.id.akt_nomer);
        final EditText ETAktData = (EditText) rootView.findViewById(R.id.akt_data);
        final EditText ETNakladnajaNomer = (EditText) rootView.findViewById(R.id.nakl_nomer);
        final EditText ETNakladnajaData = (EditText) rootView.findViewById(R.id.nakl_data);

        final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroupSTATUS);
        final RadioButton Step1 = (RadioButton) rootView.findViewById(R.id.radio_step1);
        final RadioButton Step2 = (RadioButton) rootView.findViewById(R.id.radio_step2);
        final RadioButton Step3 = (RadioButton) rootView.findViewById(R.id.radio_step3);
        final RadioButton Step4 = (RadioButton) rootView.findViewById(R.id.radio_step4);
        final RadioButton Step5 = (RadioButton) rootView.findViewById(R.id.radio_step5);
        final RadioButton Step6 = (RadioButton) rootView.findViewById(R.id.radio_step6);
        final RadioButton Step7 = (RadioButton) rootView.findViewById(R.id.radio_step7);
        final RadioButton Step8 = (RadioButton) rootView.findViewById(R.id.radio_step8);
        final RadioButton Step9 = (RadioButton) rootView.findViewById(R.id.radio_step9);
        final RadioButton Step10 = (RadioButton) rootView.findViewById(R.id.radio_step10);
        final RadioButton Step11 = (RadioButton) rootView.findViewById(R.id.radio_step11);
        final RadioButton Step12 = (RadioButton) rootView.findViewById(R.id.radio_step12);
        final RadioButton Step13 = (RadioButton) rootView.findViewById(R.id.radio_step13);
        final RadioButton Step14 = (RadioButton) rootView.findViewById(R.id.radio_step14);
        final RadioButton Step15 = (RadioButton) rootView.findViewById(R.id.radio_step15);
        final RadioButton Step16 = (RadioButton) rootView.findViewById(R.id.radio_step16);
        final RadioButton Step17 = (RadioButton) rootView.findViewById(R.id.radio_step17);
        final RadioButton Step18 = (RadioButton) rootView.findViewById(R.id.radio_step18);
        final RadioButton Step19 = (RadioButton) rootView.findViewById(R.id.radio_step19);
        final RadioButton Step20 = (RadioButton) rootView.findViewById(R.id.radio_step20);
        final RadioButton Step21 = (RadioButton) rootView.findViewById(R.id.radio_step21);
        final RadioButton Step22 = (RadioButton) rootView.findViewById(R.id.radio_step22);
        final RadioButton Step23 = (RadioButton) rootView.findViewById(R.id.radio_step23);
        final RadioButton Step24 = (RadioButton) rootView.findViewById(R.id.radio_step24);
        final RadioButton Step25 = (RadioButton) rootView.findViewById(R.id.radio_step25);
        final RadioButton Step26 = (RadioButton) rootView.findViewById(R.id.radio_step26);


        String[] SrokiPostavkiTovarovArray = getResources().getStringArray(R.array.srokipostavkitovarov);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, SrokiPostavkiTovarovArray);
        ETSrokiPostavkiTovarov.setAdapter(adapter1);

        String[] SrokiOkazanijaUslugArray = getResources().getStringArray(R.array.srokiokazanijauslug);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, SrokiOkazanijaUslugArray);
        ETSrokiOkazanijaUslug.setAdapter(adapter2);

        String[] UslovijaOplatiTovarovArray = getResources().getStringArray(R.array.uslovijaoplatitovarov);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, UslovijaOplatiTovarovArray);
        ETUslovijaOplatiTovarov.setAdapter(adapter3);

        String[] UslovijaOplatiUslugArray = getResources().getStringArray(R.array.uslovijaoplatiuslug);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, UslovijaOplatiUslugArray);
        ETUslovijaOplatiUslug.setAdapter(adapter4);

        String[] UslovijaPriemkiTovarovArray = getResources().getStringArray(R.array.uslovijapriemkitovarov);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, UslovijaPriemkiTovarovArray);
        ETUslovijaPriemkiTovarov.setAdapter(adapter5);

        String[] UslovijaPriemkiUslugArray = getResources().getStringArray(R.array.uslovijapriemkiuslug);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, UslovijaPriemkiUslugArray);
        ETUslovijaPriemkiUslug.setAdapter(adapter6);

        String[] UslovijaGarantiiArray = getResources().getStringArray(R.array.uslovijagarantii);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, UslovijaGarantiiArray);
        ETUslovijaGarantii.setAdapter(adapter7);

        String[] OsobijeUslovijaArray = getResources().getStringArray(R.array.osobijeuslovija);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, OsobijeUslovijaArray);
        ETOsobijeUslovija.setAdapter(adapter8);

        String[] SudArray = getResources().getStringArray(R.array.arbitrsud);
        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, SudArray);
        ETSud.setAdapter(adapter9);

        //endregion

        //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

        final LinearLayout fragRoot = (LinearLayout) rootView.findViewById(R.id.LLContainer);
        fragRoot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MainActivity rootActivity = (MainActivity)getActivity();
                        rootActivity.opencloseMenu(true);
                    }
                });
        //endregion

        //region ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ

        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null);
            cursor.moveToNext(); // без этого exception
            String result = cursor.getString(2);

            if (result.equals("PHONE")) {

                LinearLayout.LayoutParams param =
                        new LinearLayout.LayoutParams(
                                Math.round(getActivity().getResources().getDisplayMetrics().density * 350),
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                fragRoot.setLayoutParams(param);
            }

            db.close();

        } catch (CursorIndexOutOfBoundsException CursorException) {
            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }

        //endregion

        //region ЗАКРЫТЬ ВСЕ РАСКРЫВАЮЩИЕСЯ ЭЛЕМЕНТЫ

        final LinearLayout LinearLayoutKontragent = (LinearLayout) rootView.findViewById(R.id.sectionkontragent);
        final LinearLayout LinearLayoutMyOrg = (LinearLayout) rootView.findViewById(R.id.sectionmyorg);
        final LinearLayout LinearLayoutDocuments = (LinearLayout) rootView.findViewById(R.id.sectiondocuments);
        final LinearLayout LinearLayoutUslovijaSdelki = (LinearLayout) rootView.findViewById(R.id.sectionuslovijasdelki);
        final LinearLayout LinearLayoutTovariUslugi = (LinearLayout) rootView.findViewById(R.id.sectiontovariuslugi);
        final LinearLayout LinearLayoutZametki = (LinearLayout) rootView.findViewById(R.id.sectionzametki);
        final LinearLayout LinearLayoutDogovor = (LinearLayout) rootView.findViewById(R.id.LLdogovoributtons);
        final LinearLayout LinearLayoutAkt = (LinearLayout) rootView.findViewById(R.id.DocsAkti);
        final LinearLayout LinearLayoutNakl = (LinearLayout) rootView.findViewById(R.id.DocsNakladnie);

        LinearLayoutKontragent.setVisibility(View.GONE);
        LinearLayoutMyOrg.setVisibility(View.GONE);
        LinearLayoutDocuments.setVisibility(View.GONE);
        LinearLayoutUslovijaSdelki.setVisibility(View.GONE);
        LinearLayoutTovariUslugi.setVisibility(View.GONE);
        LinearLayoutZametki.setVisibility(View.GONE);
        LinearLayoutDogovor.setVisibility(View.GONE);
        LinearLayoutAkt.setVisibility(View.GONE);
        LinearLayoutNakl.setVisibility(View.GONE);

        radioGroup.setVisibility(View.GONE);

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В КОНТРАГЕНТАХ

        final Button ButtonShowKontragent = (Button) rootView.findViewById(R.id.buttonshowkontragent);
        ButtonShowKontragent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutKontragent.getVisibility() == View.VISIBLE) {

                            LinearLayoutKontragent.setVisibility(View.GONE);
                            ButtonShowKontragent.setText(">");

                        } else {
                            LinearLayoutKontragent.setVisibility(View.VISIBLE);
                            ButtonShowKontragent.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В МОИХ ОРГАНИЗАЦИЯХ

        final Button ButtonShowMyOrg = (Button) rootView.findViewById(R.id.buttonshowmyorg);
        ButtonShowMyOrg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutMyOrg.getVisibility() == View.VISIBLE) {

                            LinearLayoutMyOrg.setVisibility(View.GONE);
                            ButtonShowMyOrg.setText(">");
                        } else {
                            LinearLayoutMyOrg.setVisibility(View.VISIBLE);
                            ButtonShowMyOrg.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В УСЛОВИЯХ СДЕЛКИ

        final Button ButtonShowUslovijaSdelki = (Button) rootView.findViewById(R.id.buttonshowuslovijasdelki);
        ButtonShowUslovijaSdelki.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutUslovijaSdelki.getVisibility() == View.VISIBLE) {

                            LinearLayoutUslovijaSdelki.setVisibility(View.GONE);
                            ButtonShowUslovijaSdelki.setText(">");
                        } else {
                            LinearLayoutUslovijaSdelki.setVisibility(View.VISIBLE);
                            ButtonShowUslovijaSdelki.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ

        final Button ButtonShowDocuments = (Button) rootView.findViewById(R.id.buttonshowdocuments);
        ButtonShowDocuments.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

                            LinearLayoutDocuments.setVisibility(View.GONE);
                            ButtonShowDocuments.setText(">");
                        } else {
                            LinearLayoutDocuments.setVisibility(View.VISIBLE);
                            ButtonShowDocuments.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> КОММЕРЧЕСКИЕ ПРЕДЛОЖЕНИЯ

        final Button ButtonShowDocumentsKP = (Button) rootView.findViewById(R.id.buttonshowkommpredl);
        final Button ButtonSaveKP = (Button) rootView.findViewById(R.id.buttonsavekp);
        final Button ButtonSaveKPPechat = (Button) rootView.findViewById(R.id.buttonsavekppechat);
        final LinearLayout DocsKPs = (LinearLayout) rootView.findViewById(R.id.DocsKPs);

        DocsKPs.setVisibility(View.GONE);

        ButtonShowDocumentsKP.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (DocsKPs.getVisibility() == View.VISIBLE) {

                            DocsKPs.setVisibility(View.GONE);
                            ButtonShowDocuments.setText(">");
                        } else {
                            DocsKPs.setVisibility(View.VISIBLE);
                            ButtonShowDocuments.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> ТЕХЗАКЛЮЧЕНИЯ

        final Button ButtonShowDocumentsTZ = (Button) rootView.findViewById(R.id.buttonshowtehzakl);
        ButtonShowDocumentsTZ.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

                            LinearLayoutDocuments.setVisibility(View.GONE);
                            ButtonShowDocuments.setText(">");
                        } else {
                            LinearLayoutDocuments.setVisibility(View.VISIBLE);
                            ButtonShowDocuments.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> СЧЕТА

        final Button ButtonShowDocumentsScheta = (Button) rootView.findViewById(R.id.buttonshowscheta);
        final Button ButtonSaveSchet = (Button) rootView.findViewById(R.id.buttonsaveschet);
        final Button ButtonSaveSchetPechat = (Button) rootView.findViewById(R.id.buttonsaveschetpechat);
        final LinearLayout DocsSchet = (LinearLayout) rootView.findViewById(R.id.DocsScheta);

        DocsSchet.setVisibility(View.GONE);

        ButtonShowDocumentsScheta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (DocsSchet.getVisibility() == View.VISIBLE) {


                            DocsSchet.setVisibility(View.GONE);
                            ButtonShowDocumentsScheta.setText(">");
                        } else {

                            DocsSchet.setVisibility(View.VISIBLE);

                            ButtonShowDocumentsScheta.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> ДОГОВОР

        final Button ButtonShowDocumentsDogovor = (Button) rootView.findViewById(R.id.buttonshowdogovor);
        final Button ButtonSaveDogovor = (Button) rootView.findViewById(R.id.buttonsavedogovor);
        final Button ButtonSaveDogovorPechat = (Button) rootView.findViewById(R.id.buttonsavedogovorpechat);

        ButtonShowDocumentsDogovor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutDogovor.getVisibility() == View.VISIBLE) {

                            LinearLayoutDogovor.setVisibility(View.GONE);
                            ButtonShowDocumentsDogovor.setText(">");
                        } else {
                            LinearLayoutDogovor.setVisibility(View.VISIBLE);
                            ButtonShowDocumentsDogovor.setText("^");
                        }
                    }
                });

        //endregion КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> ДОГОВОР

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> АКТЫ

        final Button ButtonShowDocumentsAkt = (Button) rootView.findViewById(R.id.buttonshowakt);
        final Button ButtonSaveAkt = (Button) rootView.findViewById(R.id.buttonsaveakt);
        final Button ButtonSaveAktPechat = (Button) rootView.findViewById(R.id.buttonsaveaktpechat);

        ButtonShowDocumentsAkt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutAkt.getVisibility() == View.VISIBLE) {

                            LinearLayoutAkt.setVisibility(View.GONE);
                            ButtonShowDocumentsAkt.setText(">");
                        } else {
                            LinearLayoutAkt.setVisibility(View.VISIBLE);
                            ButtonShowDocumentsAkt.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> НАКЛАДНЫЕ

        final Button ButtonShowDocumentsNakl = (Button) rootView.findViewById(R.id.buttonshownakl);
        final Button ButtonSaveNakl = (Button) rootView.findViewById(R.id.buttonsavenakl);
        final Button ButtonSaveNaklPechat = (Button) rootView.findViewById(R.id.buttonsavenaklpechat);


        ButtonShowDocumentsNakl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutNakl.getVisibility() == View.VISIBLE) {

                            LinearLayoutNakl.setVisibility(View.GONE);
                            ButtonShowDocumentsNakl.setText(">");
                        } else {
                            LinearLayoutNakl.setVisibility(View.VISIBLE);
                            ButtonShowDocumentsNakl.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ --> ПРОЧИЕ ДОКУМЕНТЫ

        final Button ButtonShowDocumentsProch = (Button) rootView.findViewById(R.id.buttonshowdrdoc);
        ButtonShowDocumentsProch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

                            LinearLayoutDocuments.setVisibility(View.GONE);
                            ButtonShowDocuments.setText(">");
                        } else {
                            LinearLayoutDocuments.setVisibility(View.VISIBLE);
                            ButtonShowDocuments.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ТОВАРЫ И УСЛУГИ

        final Button ButtonShowTOVUSL = (Button) rootView.findViewById(R.id.buttonshowtovariuslugi);
        ButtonShowTOVUSL.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutTovariUslugi.getVisibility() == View.VISIBLE) {

                            LinearLayoutTovariUslugi.setVisibility(View.GONE);
                            ButtonShowTOVUSL.setText(">");
                        } else {
                            LinearLayoutTovariUslugi.setVisibility(View.VISIBLE);
                            ButtonShowTOVUSL.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ЗАМЕТКИ

        final Button ButtonShowZAMETKI = (Button) rootView.findViewById(R.id.buttonshowzametki);
        ButtonShowZAMETKI.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (LinearLayoutZametki.getVisibility() == View.VISIBLE) {

                            LinearLayoutZametki.setVisibility(View.GONE);
                            ButtonShowZAMETKI.setText(">");
                        } else {
                            LinearLayoutZametki.setVisibility(View.VISIBLE);
                            ButtonShowZAMETKI.setText("^");
                        }
                    }
                });

        //endregion

        //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ СТАТУС СДЕЛКИ

        final Button ButtonShowSTATUSSDELKI = (Button) rootView.findViewById(R.id.buttonshowstatussdelki);
        ButtonShowSTATUSSDELKI .setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (radioGroup.getVisibility() == View.VISIBLE) {

                            radioGroup.setVisibility(View.GONE);
                            ButtonShowSTATUSSDELKI.setText(">");
                        } else {
                            radioGroup.setVisibility(View.VISIBLE);
                            ButtonShowSTATUSSDELKI.setText("^");
                        }
                    }
                });

        //endregion


        //region КНОПКА СОХРАНИТЬ КП БЕЗ ПЕЧАТИ

        ButtonSaveKP.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();

                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();

                        String KPNomer = ETKPNomer.getText().toString();
                        String KPData = ETKPData.getText().toString();

                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = KP.getOsnova1();
                        String s2 = KP.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);
                        String s3 = KP.getNachaloSmetiSection3(KPNomer, KPData, UslugiPo, GorodDogovora, SMyFullName, SMyVlice, SKontragentSokrName);

                        try {

                            //public String getTovarUslugа4 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + KP.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                } catch (ArithmeticException e) {
                                } catch (ArrayStoreException ex) {
                                } catch (ClassCastException ex) {
                                } catch (NullPointerException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        }
                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());

                        String ItogoTable = KP.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");
                        String UslovijaKP = "<strong>Сроки поставки товаров:</strong> " + SrokiPostavkiTovarov + "<br><strong>Сроки оказания услуг:</strong> " + SrokiOkazanijaUslug + "<br><strong>Порядок оплаты товаров:</strong> " + UslovijaOplatiTovarov + "<br><strong>Порядок оплаты услуг:</strong> " + UslovijaOplatiUslug;

                        String s5 = KP.getSmetaEndTable5(SummTovari.getTextStr(), mo.num2str(), UslovijaKP);

                        String s6 = KP.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);

                        //ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
                        String s7 = KP.getSmetaEnd7(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, KPData);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Предложение КП-" + KPNomer + " от " + KPData + ".html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);


                    }
                });
        //endregion

        //region КНОПКА СОХРАНИТЬ КП С ПЕЧАТЬЮ

        ButtonSaveKPPechat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();

                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();

                        String KPNomer = ETKPNomer.getText().toString();
                        String KPData = ETKPData.getText().toString();

                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = KP.getOsnova1();
                        String s2 = KP.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);
                        String s3 = KP.getNachaloSmetiSection3(KPNomer, KPData, UslugiPo, GorodDogovora, SMyFullName, SMyVlice, SKontragentSokrName);

                        try {

                            //public String getTovarUslugа4 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + KP.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                } catch (ArithmeticException e) {
                                } catch (ArrayStoreException ex) {
                                } catch (ClassCastException ex) {
                                } catch (NullPointerException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        }
                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());

                        String ItogoTable = KP.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");
                        String UslovijaKP = "<strong>Сроки поставки товаров:</strong> " + SrokiPostavkiTovarov + "<br><strong>Сроки оказания услуг:</strong> " + SrokiOkazanijaUslug + "<br><strong>Порядок оплаты товаров:</strong> " + UslovijaOplatiTovarov + "<br><strong>Порядок оплаты услуг:</strong> " + UslovijaOplatiUslug;

                        String s5 = KP.getSmetaEndTable5(SummTovari.getTextStr(), mo.num2str(), UslovijaKP);

                        String s6 = KP.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);

                        //ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
                        String s7 = KP.getSmetaEndPechat8(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, KPData, SMyPechat);


                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Предложение КП-" + KPNomer + " от " + KPData + "(с печатью).html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);

                    }
                });

        //endregion

        //region КНОПКА СОХРАНИТЬ СЧЕТ БЕЗ ПЕЧАТИ

        ButtonSaveSchet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();

                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();

                        String SchetNomer = ETSchetNomer.getText().toString();
                        String SchetData = ETSchetData.getText().toString();

                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Schet.getOsnova1();
                        String s2 = Schet.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);
                        String s3 = Schet.getTablePP3(SMyFullName, SMyINN, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS);
                        String s4 = Schet.getProdPokupSection4(SchetNomer, SchetData, NomerDogovora, DataDogovora, SMyFullName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SKontragentFullName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite);
                        String s5 = Schet.getNachaloTabliciTovarovUslug5();

                        try {

                            //public String getTovarUslugа6 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                            String s6 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S6.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s6 = s6 + Schet.getTovarUslugа6(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S6.setTextStr(s6);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                } catch (ArithmeticException e) {
                                } catch (ArrayStoreException ex) {
                                } catch (ClassCastException ex) {
                                } catch (NullPointerException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        }
                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());


                        String s7 = Schet.getSchetEnd7(SummTovari.getTextStr(), mo.num2str(), SMyRukDolzhn, SMyFIOruk);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Счет С-" + SchetNomer + " от " + SchetData + ".html", s1 + s2 + s3 + s4 + s5 + S6.getTextStr() + s7);

                    }
                });

        //endregion

        //region КНОПКА СОХРАНИТЬ СЧЕТ С ПЕЧАТЬЮ

        ButtonSaveSchetPechat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();

                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();

                        String SchetNomer = ETSchetNomer.getText().toString();
                        String SchetData = ETSchetData.getText().toString();

                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Schet.getOsnova1();
                        String s2 = Schet.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);
                        String s3 = Schet.getTablePP3(SMyFullName, SMyINN, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS);
                        String s4 = Schet.getProdPokupSection4(SchetNomer, SchetData, NomerDogovora, DataDogovora, SMyFullName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SKontragentFullName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite);
                        String s5 = Schet.getNachaloTabliciTovarovUslug5();

                        try {

                            //public String getTovarUslugа6 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                            String s6 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S6.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s6 = s6 + Schet.getTovarUslugа6(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S6.setTextStr(s6);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                } catch (ArithmeticException e) {
                                } catch (ArrayStoreException ex) {
                                } catch (ClassCastException ex) {
                                } catch (NullPointerException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        }
                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());


                        String s7 = Schet.getSchetEndPechat8(SummTovari.getTextStr(), mo.num2str(), SMyRukDolzhn, SMyFIOruk, SMyPechat);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Счет С-" + SchetNomer + " от " + SchetData + " (с печатью).html", s1 + s2 + s3 + s4 + s5 + S6.getTextStr() + s7);

                    }
                });

        //endregion

        //region КНОПКА СОХРАНИТЬ ДОГОВОР БЕЗ ПЕЧАТИ

        ButtonSaveDogovor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();


                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();


                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Dogovor.getOsnova1();
                        // String s2 = Dogovor.getShapka2 (String Imgaddr, String Slogan, SMySite, SMyPhone);
                        String s2 = Dogovor.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);

                        try {

                            //public String getTovarUslugа6 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + Dogovor.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        }
                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        } catch (ArithmeticException e) {
                        } catch (ArrayStoreException ex) {
                        } catch (ClassCastException ex) {
                        } catch (NullPointerException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());


                        String s3 = Dogovor.getNachaloDogovoraSection3(NomerDogovora, DataDogovora, UslugiPo, GorodDogovora, SMyFullName, SMyVlice, SKontragentFullName, SKontragentVlice, SrokiPostavkiTovarov, SrokiOkazanijaUslug, UslovijaOplatiTovarov, UslovijaOplatiUslug, SummTovari.getTextStr(), mo.num2str());
                        String ItogoTable = Dogovor.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");

//Documents_MakeDogovor.getSectionDogovora5 (String Garantija, String UslugiPo, String OsobieUslovijaDogovora, String UslovijaPriemkiTovarov, String UslovijaPriemkiUslug, String Sud)

                        String s5 = Dogovor.getSectionDogovora5(UslovijaGarantii, UslugiPo, OsobijeUslovija, UslovijaPriemkiTovarov, UslovijaPriemkiUslug, Sud);
                        String s6 = Dogovor.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);

                        //ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
                        String s7 = Dogovor.getDogovorEnd7(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, DataDogovora);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Договор " + NomerDogovora + " от " + DataDogovora + ".html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);

                    }
                });

        //endregion

        //region КНОПКА СОХРАНИТЬ ДОГОВОР С ПЕЧАТЬЮ

        ButtonSaveDogovorPechat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();

                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();


                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Dogovor.getOsnova1();
                        // String s2 = Dogovor.getShapka2 (String Imgaddr, String Slogan, SMySite, SMyPhone);
                        String s2 = Dogovor.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);

                        try {

                            //public String getTovarUslugа6 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + Dogovor.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        }
                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        } catch (ArithmeticException e) {
                        } catch (ArrayStoreException ex) {
                        } catch (ClassCastException ex) {
                        } catch (NullPointerException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());

                        String s3 = Dogovor.getNachaloDogovoraSection3(NomerDogovora, DataDogovora, UslugiPo, GorodDogovora, SMyFullName, SMyVlice, SKontragentFullName, SKontragentVlice, SrokiPostavkiTovarov, SrokiOkazanijaUslug, UslovijaOplatiTovarov, UslovijaOplatiUslug, SummTovari.getTextStr(), mo.num2str());
                        String ItogoTable = Dogovor.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");

                        String s5 = Dogovor.getSectionDogovora5(UslovijaGarantii, UslugiPo, OsobijeUslovija, UslovijaPriemkiTovarov, UslovijaPriemkiUslug, Sud);
                        String s6 = Dogovor.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);

                        //ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
                        String s7 = Dogovor.getDogovorEndPechat8(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, DataDogovora, SMyPechat);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Договор " + NomerDogovora + " от " + DataDogovora + " (с печатью).html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);


                    }
                });

        //endregion

        //region КНОПКА СОХРАНИТЬ АКТ БЕЗ ПЕЧАТИ

        ButtonSaveAkt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();


                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String NomerAkta = ETAktNomer.getText().toString();
                        String DataAkta = ETAktData.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();


                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Akt.getOsnova1();
                        String s2 = Akt.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);

                        try {

                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Usluga' OR DOCUMENT='Услуга');", null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + Akt.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        } catch (SQLException ex) {
                        } catch (StaleDataException ex) {
                        }

                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        } catch (ArithmeticException e) {
                        } catch (ArrayStoreException ex) {
                        } catch (ClassCastException ex) {
                        } catch (NullPointerException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());

                        String s3 = Akt.getNachaloAktaSection3(NomerAkta, DataAkta, NomerDogovora, DataDogovora, UslugiPo, GorodDogovora, SMyFullName, SMyVlice, SKontragentFullName, SKontragentVlice, SummTovari.getTextStr(), mo.num2str());

                        String ItogoTable = Akt.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");

                        String s5 = Akt.getAktEndTable5(SummTovari.getTextStr(), mo.num2str());

                        String s6 = Akt.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);


                        String s7 = Akt.getAktEnd7(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, DataAkta);


                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Акт А-" + NomerAkta + " от " + DataAkta + " к Договору " + NomerDogovora + " от " + DataDogovora + ".html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);

                    }
                });

//endregion

        //region КНОПКА СОХРАНИТЬ АКТ С ПЕЧАТЬЮ

        ButtonSaveAktPechat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();


                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String NomerAkta = ETAktNomer.getText().toString();
                        String DataAkta = ETAktData.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();


                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Akt.getOsnova1();
                        String s2 = Akt.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);

                        try {

                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Usluga' OR DOCUMENT='Услуга');", null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;

                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + Akt.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        } catch (SQLException ex) {
                        } catch (StaleDataException ex) {
                        }

                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        } catch (ArithmeticException e) {
                        } catch (ArrayStoreException ex) {
                        } catch (ClassCastException ex) {
                        } catch (NullPointerException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());

                        String s3 = Akt.getNachaloAktaSection3(NomerAkta, DataAkta, NomerDogovora, DataDogovora, UslugiPo, GorodDogovora, SMyFullName, SMyVlice, SKontragentFullName, SKontragentVlice, SummTovari.getTextStr(), mo.num2str());

                        String ItogoTable = Akt.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");

                        String s5 = Akt.getAktEndTable5(SummTovari.getTextStr(), mo.num2str());

                        String s6 = Akt.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);

                        String s7 = Akt.getAktEndPechat8(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, DataAkta, SMyPechat);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Акт А-" + NomerAkta + " от " + DataAkta + " к Договору " + NomerDogovora + " от " + DataDogovora + " (с печатью).html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);

                    }
                });

//endregion

        //region КНОПКА СОХРАНИТЬ НАКЛАДНУЮ БЕЗ ПЕЧАТИ

        ButtonSaveNakl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();


                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String NomerNakladnoj = ETNakladnajaNomer.getText().toString();
                        String DataNakladnoj = ETNakladnajaData.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();


                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Nakladnaja.getOsnova1();
                        String s2 = Nakladnaja.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);

                        try {

                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Tovar' OR DOCUMENT='Товар');", null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");
                            S6.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;
                                S6.setTextStr(Integer.toString(NTU));
                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + Nakladnaja.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        } catch (SQLException ex) {
                        } catch (StaleDataException ex) {
                        }

                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        } catch (ArithmeticException e) {
                        } catch (ArrayStoreException ex) {
                        } catch (ClassCastException ex) {
                        } catch (NullPointerException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());

                        String NomerovPoNakladnoj = S6.getTextStr();
                        String s3 = Nakladnaja.getNachaloNakladnojSection3(NomerNakladnoj, DataNakladnoj, NomerDogovora, DataDogovora, GorodDogovora);
                        String ItogoTable = Nakladnaja.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");
                        //Documents_MakeNakladnaja.getNakladnajaEndTable5 (String Stoimost, String StoimostPropis, String NomerovPoNakladnoj)
                        String s5 = Nakladnaja.getNakladnajaEndTable5(SummTovari.getTextStr(), mo.num2str(), NomerovPoNakladnoj);

                        String s6 = Nakladnaja.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);

                        //ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
                        String s7 = Nakladnaja.getNakladnajaEnd7(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, DataNakladnoj);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Накладная Н-" + NomerNakladnoj + " от " + DataNakladnoj + " к Договору " + NomerDogovora + " от " + DataDogovora + ".html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);

                    }
                });

//endregion

        //region КНОПКА СОХРАНИТЬ НАКЛАДНУЮ С ПЕЧАТЬЮ

        ButtonSaveNaklPechat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String SdelkaName = ETSdelkaName.getText().toString();

                        String SKontragentFullName = KontragentFullName.getText().toString();
                        String SKontragentSokrName = KontragentSokrName.getText().toString();
                        String SKontragentINN = KontragentINN.getText().toString();
                        String SKontragentKPP = KontragentKPP.getText().toString();
                        String SKontragentOGRN = KontragentOGRN.getText().toString();
                        String SKontragentBankName = KontragentBankName.getText().toString();
                        String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                        String SKontragentBankKS = KontragentBankKS.getText().toString();
                        String SKontragentBankRS = KontragentBankRS.getText().toString();
                        String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                        String SKontragentVlice = KontragentVlice.getText().toString();
                        String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                        String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                        String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                        String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                        String SKontragentPhone = KontragentPhone.getText().toString();
                        String SKontragentMobile = KontragentMobile.getText().toString();
                        String SKontragentEmail = KontragentEmail.getText().toString();
                        String SKontragentSite = KontragentSite.getText().toString();
                        String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                        String SMyFullName = MyFullName.getText().toString();
                        String SMySokrName = MySokrName.getText().toString();
                        String SMyINN = MyINN.getText().toString();
                        String SMyKPP = MyKPP.getText().toString();
                        String SMyOGRN = MyOGRN.getText().toString();
                        String SMyBankName = MyBankName.getText().toString();
                        String SMyBankBIK = MyBankBIK.getText().toString();
                        String SMyBankKS = MyBankKS.getText().toString();
                        String SMyBankRS = MyBankRS.getText().toString();
                        String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                        String SMyVlice = MyVlice.getText().toString();
                        String SMyFIOruk = MyFIOruk.getText().toString();
                        String SMyUrAddr = MyUrAddr.getText().toString();
                        String SMyFaktAddr = MyFaktAddr.getText().toString();
                        String SMyPostAddr = MyPostAddr.getText().toString();
                        String SMyPhone = MyPhone.getText().toString();
                        String SMyMobile = MyMobile.getText().toString();
                        String SMyEmail = MyEmail.getText().toString();
                        String SMySite = MySite.getText().toString();
                        String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                        String SMyLogo = MyLogo.getText().toString();
                        String SMyPechat = MyPechat.getText().toString();
                        String SMySlogan = MySlogan.getText().toString();


                        String DataDogovora = ETDataDogovora.getText().toString();
                        String NomerDogovora = ETNomerDogovora.getText().toString();
                        String GorodDogovora = ETGorodDogovora.getText().toString();
                        String NomerNakladnoj = ETNakladnajaNomer.getText().toString();
                        String DataNakladnoj = ETNakladnajaData.getText().toString();
                        String UslugiPo = ETUslugiPo.getText().toString();
                        String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                        String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                        String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                        String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                        String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                        String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                        String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                        String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                        String Sud = ETSud.getText().toString();


                        String SKontragentBankRekv = "Р/с " + SKontragentBankRS + " в " + SKontragentBankName + " БИК " + SKontragentBankBIK + " к/с " + SKontragentBankKS;


                        String s1 = Nakladnaja.getOsnova1();
                        String s2 = Nakladnaja.getShapka2(SMyLogo, SMySlogan, SMySite, SMyPhone);

                        try {

                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Tovar' OR DOCUMENT='Товар');", null);
                            String s4 = "";
                            int NTU = 0;
                            float Summ = 0;
                            SummTovari = new TextStr();
                            SummTovari.setTextStr("0");
                            S4.setTextStr("");
                            S6.setTextStr("");

                            while (cursor.moveToNext()) {
                                NTU = NTU + 1;
                                S6.setTextStr(Integer.toString(NTU));
                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                s4 = s4 + Nakladnaja.getTovarUslugа4(Integer.toString(NTU), cursor.getString(2), cursor.getString(6), cursor.getString(5), cursor.getString(3), cursor.getString(7));
                                S4.setTextStr(s4);
                                try {
                                    Summ = Summ + Float.parseFloat(cursor.getString(7));
                                    SummTovari.setTextStr(Float.toString(Summ));
                                } catch (NumberFormatException ex) {
                                }
                            }

                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                        } catch (SQLException ex) {
                        } catch (StaleDataException ex) {
                        }

                        try {
                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                            SummTovari.setTextStr(Integer.toString(SummInt));
                        } catch (NumberFormatException ex) {
                        } catch (ArithmeticException e) {
                        } catch (ArrayStoreException ex) {
                        } catch (ClassCastException ex) {
                        } catch (NullPointerException ex) {
                        }

                        SummaToString mo = new SummaToString(SummTovari.getTextStr());

                        String NomerovPoNakladnoj = S6.getTextStr();
                        String s3 = Nakladnaja.getNachaloNakladnojSection3(NomerNakladnoj, DataNakladnoj, NomerDogovora, DataDogovora, GorodDogovora);
                        String ItogoTable = Nakladnaja.getTovarUslugа4("", "<strong>ИТОГО</strong>", "", "", "", "<strong>" + SummTovari.getTextStr() + "</strong>");
                        //Documents_MakeNakladnaja.getNakladnajaEndTable5 (String Stoimost, String StoimostPropis, String NomerovPoNakladnoj)
                        String s5 = Nakladnaja.getNakladnajaEndTable5(SummTovari.getTextStr(), mo.num2str(), NomerovPoNakladnoj);

                        String s6 = Nakladnaja.getRekvizitiStoron6(SMySokrName, SMyUrAddr, SMyFaktAddr, SMyOGRN, SMyINN, SMyKPP, SMyBankRS, SMyBankName, SMyBankBIK, SMyBankKS, SMyPhone, SMyMobile, SMyEmail, SMySite, SMyOtvetstvennij, SKontragentSokrName, SKontragentUrAddr, SKontragentFaktAddr, SKontragentINN, SKontragentKPP, SKontragentOGRN, SKontragentBankRekv, SKontragentPhone, SKontragentMobile, SKontragentEmail, SKontragentSite, SKontragentOtvetstvennij);


                        String s7 = Nakladnaja.getNakladnajaEndPechat8(SMyRukDolzhn, SMyFIOruk, SKontragentRukDolzhn, SKontragentFIOruk, DataNakladnoj, SMyPechat);

                        String KFolderName = SKontragentSokrName.replaceAll("\"", ""); //убираем кавычки из сокр наименования контрагента для создания папки

                        rootActivity.savehtmlFile(KFolderName, SdelkaName, "Накладная Н-" + NomerNakladnoj + " от " + DataNakladnoj + " к Договору " + NomerDogovora + " от " + DataDogovora + " (с печатью).html", s1 + s2 + s3 + S4.getTextStr() + ItogoTable + s5 + s6 + s7);

                    }
                });

//endregion


        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SDELKI WHERE ID = " + ID, null);
            cursor.moveToNext(); //без этого exception

            ETSdelkaName.setText(cursor.getString(1));

            KontragentFullName.setText(cursor.getString(2));
            KontragentSokrName.setText(cursor.getString(3));
            KontragentINN.setText(cursor.getString(4));
            KontragentKPP.setText(cursor.getString(5));
            KontragentOGRN.setText(cursor.getString(6));
            KontragentBankName.setText(cursor.getString(7));
            KontragentBankBIK.setText(cursor.getString(8));
            KontragentBankKS.setText(cursor.getString(9));
            KontragentBankRS.setText(cursor.getString(10));
            KontragentRukDolzhn.setText(cursor.getString(11));
            KontragentVlice.setText(cursor.getString(12));
            KontragentFIOruk.setText(cursor.getString(13));
            KontragentUrAddr.setText(cursor.getString(14));
            KontragentFaktAddr.setText(cursor.getString(15));
            KontragentPostAddr.setText(cursor.getString(16));
            KontragentPhone.setText(cursor.getString(17));
            KontragentMobile.setText(cursor.getString(18));
            KontragentEmail.setText(cursor.getString(19));
            KontragentSite.setText(cursor.getString(20));
            KontragentOtvetstvennij.setText(cursor.getString(21));

            MyFullName.setText(cursor.getString(22));
            MySokrName.setText(cursor.getString(23));
            MyINN.setText(cursor.getString(24));
            MyKPP.setText(cursor.getString(25));
            MyOGRN.setText(cursor.getString(26));
            MyBankName.setText(cursor.getString(27));
            MyBankBIK.setText(cursor.getString(28));
            MyBankKS.setText(cursor.getString(29));
            MyBankRS.setText(cursor.getString(30));
            MyRukDolzhn.setText(cursor.getString(31));
            MyVlice.setText(cursor.getString(32));
            MyFIOruk.setText(cursor.getString(33));
            MyUrAddr.setText(cursor.getString(34));
            MyFaktAddr.setText(cursor.getString(35));
            MyPostAddr.setText(cursor.getString(36));
            MyPhone.setText(cursor.getString(37));
            MyMobile.setText(cursor.getString(38));
            MyEmail.setText(cursor.getString(39));
            MySite.setText(cursor.getString(40));
            MyOtvetstvennij.setText(cursor.getString(41));
            MySlogan.setText(cursor.getString(42));
            MyLogo.setText(cursor.getString(43));
            MyPechat.setText(cursor.getString(44));

            ETNomerDogovora.setText(cursor.getString(45));
            ETDataDogovora.setText(cursor.getString(46));
            ETGorodDogovora.setText(cursor.getString(47));
            ETUslugiPo.setText(cursor.getString(48));
            ETSrokiPostavkiTovarov.setText(cursor.getString(49));
            ETSrokiOkazanijaUslug.setText(cursor.getString(50));
            ETUslovijaOplatiTovarov.setText(cursor.getString(51));
            ETUslovijaOplatiUslug.setText(cursor.getString(52));
            ETUslovijaPriemkiTovarov.setText(cursor.getString(53));
            ETUslovijaPriemkiUslug.setText(cursor.getString(54));
            ETUslovijaGarantii.setText(cursor.getString(55));
            ETOsobijeUslovija.setText(cursor.getString(56));
            ETSud.setText(cursor.getString(57));
            String result1 = cursor.getString(58);

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
            }if (result1.equals("22")) {
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


            ETKPNomer.setText(cursor.getString(59));
            ETKPData.setText(cursor.getString(60));
            ETSchetNomer.setText(cursor.getString(61));
            ETSchetData.setText(cursor.getString(62));
            ETAktNomer.setText(cursor.getString(63));
            ETAktData.setText(cursor.getString(64));
            ETNakladnajaNomer.setText(cursor.getString(65));
            ETNakladnajaData.setText(cursor.getString(66));

            db.close();

            try {

                SQLiteDatabase db1 = DB.getReadableDatabase();
                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Tovar' OR DOCUMENT='Товар');", null);
                String s4 = "";
                float Summ = 0;
                SummTovari = new TextStr();
                SummTovari.setTextStr("0");

                while (cursor1.moveToNext()) {
                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                    try {
                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                        SummTovari.setTextStr(Float.toString(Summ));
                    } catch (NumberFormatException ex) {
                    }
                }

            } catch (CursorIndexOutOfBoundsException CursorException) {
                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            } catch (SQLException ex) {
            } catch (StaleDataException ex) {
            }

            try {
                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                SummTovari.setTextStr(Integer.toString(SummInt));
                StoimostTovarov.setText("Стоимость товаров: "+Integer.toString(SummInt));
            } catch (NumberFormatException ex) {
            } catch (ArithmeticException e) {
            } catch (ArrayStoreException ex) {
            } catch (ClassCastException ex) {
            } catch (NullPointerException ex) {
            }

            try {

                SQLiteDatabase db1 = DB.getReadableDatabase();
                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Usluga' OR DOCUMENT='Услуга');", null);
                String s4 = "";
                float Summ = 0;
                SummTovari = new TextStr();
                SummTovari.setTextStr("0");

                while (cursor1.moveToNext()) {
                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                    try {
                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                        SummTovari.setTextStr(Float.toString(Summ));
                    } catch (NumberFormatException ex) {
                    }
                }

            } catch (CursorIndexOutOfBoundsException CursorException) {
                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            } catch (SQLException ex) {
            } catch (StaleDataException ex) {
            }

            try {
                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                SummTovari.setTextStr(Integer.toString(SummInt));
                StoimostUslug.setText("Стоимость услуг: "+Integer.toString(SummInt));
            } catch (NumberFormatException ex) {
            } catch (ArithmeticException e) {
            } catch (ArrayStoreException ex) {
            } catch (ClassCastException ex) {
            } catch (NullPointerException ex) {
            }

            try {

                SQLiteDatabase db1 = DB.getReadableDatabase();
                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                String s4 = "";
                float Summ = 0;
                SummTovari = new TextStr();
                SummTovari.setTextStr("0");

                while (cursor1.moveToNext()) {
                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                    try {
                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                        SummTovari.setTextStr(Float.toString(Summ));
                    } catch (NumberFormatException ex) {
                    }
                }

            } catch (CursorIndexOutOfBoundsException CursorException) {
                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            } catch (SQLException ex) {
            } catch (StaleDataException ex) {
            }

            try {
                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                SummTovari.setTextStr(Integer.toString(SummInt));
                StoimostItogo.setText("ИТОГО: "+Integer.toString(SummInt));
            } catch (NumberFormatException ex) {
            } catch (ArithmeticException e) {
            } catch (ArrayStoreException ex) {
            } catch (ClassCastException ex) {
            } catch (NullPointerException ex) {
            }

        } catch (CursorIndexOutOfBoundsException CursorException) {
            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }

        //endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (РАЗДЕЛ ЗАМЕТКИ)
        try {

            idArrayZametki.clear();
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null);

            while (cursor.moveToNext()) {
                listzametki.add(new ZAMETKI(cursor.getString(1), "", "", "Дата: " + cursor.getString(3), "", ""));
                idArrayZametki.add(cursor.getString(0));
            }

            //final ListView ListViewZAMETKI = (ListView)rootView.findViewById(R.id.ListViewZAMETKI);
            //final ZAMETKIdialogListAdapter Zadapter = new ZAMETKIdialogListAdapter(getActivity(), listzametki);
            ListViewZAMETKI.setAdapter(Zadapter);

            Zadapter.notifyDataSetChanged();
            SetDListView.SetDynamicHeight(ListViewZAMETKI);

            ListViewZAMETKI.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                            final String selectedIDzametka = idArrayZametki.get(position).toString();

                            AlertDialog.Builder editzametkabuilder = new AlertDialog.Builder(container.getContext());

                            View dialogView = inflater.inflate(R.layout.alertdialog_edit_zametka, null); //важно - inflater определен в начале кода фрагмента

                            editzametkabuilder.setCancelable(false);

                            // Привязка xml-разметки окна диалогов
                            editzametkabuilder.setView(dialogView);


                            Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                            Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                            Button btn_del = (Button) dialogView.findViewById(R.id.dialog_del_btn);
                            final Button btn_del_go = (Button) dialogView.findViewById(R.id.dialog_del_btn_go);
                            final EditText DialogEditZametkaName = (EditText) dialogView.findViewById(R.id.dialogadd_zametka_name);
                            final EditText DialogEditZametkaOpisanie = (EditText) dialogView.findViewById(R.id.dialogadd_zametka_opisanie);
                            final EditText DialogEditZametkaData = (EditText) dialogView.findViewById(R.id.dialogadd_zametka_data);

                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE ID = " + selectedIDzametka, null);
                            cursor.moveToNext(); //без этого exception

                            DialogEditZametkaName.setText(cursor.getString(1));
                            DialogEditZametkaOpisanie.setText(cursor.getString(2));
                            DialogEditZametkaData.setText(cursor.getString(3));

                            // Создание диалога
                            final AlertDialog editzametkadialog = editzametkabuilder.create();


                            btn_positive.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Dismiss the alert dialog


                                    try {

                                        String Zname = DialogEditZametkaName.getText().toString();
                                        String Zopisanie = DialogEditZametkaOpisanie.getText().toString();
                                        String Zdata = DialogEditZametkaData.getText().toString();

                                        DB.ChangeZAMETKA(selectedIDzametka, Zname, Zopisanie, ID, Zdata);


                                        listzametki.clear();
                                        idArrayZametki.clear();

                                        Toast.makeText(getActivity(), "Заметка успешно изменена", Toast.LENGTH_LONG).show();

                                        SQLiteDatabase db = DB.getReadableDatabase();
                                        Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null);

                                        while (cursor.moveToNext()) {
                                            listzametki.add(new ZAMETKI(cursor.getString(1), "", "", "Дата: " + cursor.getString(3), "", ""));
                                            idArrayZametki.add(cursor.getString(0));
                                        }

                                        Zadapter.notifyDataSetChanged();
                                        SetDListView.SetDynamicHeight(ListViewZAMETKI);


                                    } catch (SQLException mSQLException) {
                                        Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
                                    }

                                    editzametkadialog.cancel();

                                }
                            });


                            btn_negative.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Dismiss/cancel the alert dialog
                                    //dialog.cancel();
                                    editzametkadialog.dismiss();
                                    //Toast.makeText(getContext(),"No button clicked", Toast.LENGTH_SHORT).show();
                                }
                            });


                            btn_del.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Dismiss/cancel the alert dialog
                                    //dialog.cancel();

                                    btn_del_go.setVisibility(View.VISIBLE);

                                    btn_del_go.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            DB.DelZAMETKA(selectedIDzametka);

                                            listzametki.clear();
                                            idArrayZametki.clear();

                                            Toast.makeText(getActivity(), "Заметка успешно удалена", Toast.LENGTH_LONG).show();

                                            SQLiteDatabase db = DB.getReadableDatabase();
                                            Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null);


                                            while (cursor.moveToNext()) {
                                                listzametki.add(new ZAMETKI(cursor.getString(1), "", "", "Дата: " + cursor.getString(3), "", ""));
                                                idArrayZametki.add(cursor.getString(0));
                                            }

                                            Zadapter.notifyDataSetChanged();
                                            SetDListView.SetDynamicHeight(ListViewZAMETKI);
                                            btn_del_go.setVisibility(View.GONE);
                                            editzametkadialog.dismiss();
                                        }
                                    });


                                }
                            });

                            editzametkadialog.show();
                        }
                    });


            db.close();


        } catch (CursorIndexOutOfBoundsException CursorException) {
            Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
        }


        //endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (РАЗДЕЛ ТОВАРЫ И УСЛУГИ)


        try {

            idArrayTovariUslugi.clear();
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);

            while (cursor.moveToNext()) {
                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)
                tovariuslugilist.add(new TOVARIUSLUGI(cursor.getString(2), cursor.getString(8), cursor.getString(3), "", cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(0)));
                idArrayTovariUslugi.add(cursor.getString(0));
            }

            TUadapter.notifyDataSetChanged();
            ListViewTOVARIUSLUGI.setAdapter(TUadapter);
            SetDListView.SetDynamicHeight(ListViewTOVARIUSLUGI);


            ListViewTOVARIUSLUGI.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                            final String selectedIDtovarusluga = idArrayTovariUslugi.get(position).toString();

                            AlertDialog.Builder edittovaruslugabuilder = new AlertDialog.Builder(container.getContext());

                            View dialogView = inflater.inflate(R.layout.alertdialog_edit_tovarusluga, null); //важно - inflater определен в начале кода фрагмента

                            edittovaruslugabuilder.setCancelable(false);

                            // Привязка xml-разметки окна диалогов
                            edittovaruslugabuilder.setView(dialogView);


                            Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                            Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                            Button btn_del = (Button) dialogView.findViewById(R.id.dialog_del_btn);
                            final Button btn_del_go = (Button) dialogView.findViewById(R.id.dialog_del_btn_go);

                            final EditText DialogEditTovaUslugaName = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_name);
                            final EditText DialogEditTovarUslugaEdIzm = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_edizm);
                            final EditText DialogEditTovaUslugaKolvo = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_kolvo);
                            final EditText DialogEditTovaUslugaCenaEd = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_cenaed);
                            final TextView TovarUslugaStoimost = (TextView) dialogView.findViewById(R.id.dialog_addtovarusluga_stoimost);
                            final Switch TovarUslugaSwitch = (Switch) dialogView.findViewById(R.id.tovarusluga_switch);
                            //TovarUslugaSwitch. setChecked(true);


                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE ID = " + selectedIDtovarusluga, null);
                            cursor.moveToNext(); //без этого exception

                            DialogEditTovaUslugaName.setText(cursor.getString(2));
                            DialogEditTovarUslugaEdIzm.setText(cursor.getString(6));
                            DialogEditTovaUslugaKolvo.setText(cursor.getString(5));
                            DialogEditTovaUslugaCenaEd.setText(cursor.getString(3));
                            TovarUslugaStoimost.setText("Стоимость: " + cursor.getString(7));
                            String TUStatus = cursor.getString(8);

                            if (TUStatus.equals("Tovar") || TUStatus.equals("Товар")) {
                                TovarUslugaSwitch.setChecked(true);
                            }

                            // Создание диалога
                            final AlertDialog edittovaruslugadialog = edittovaruslugabuilder.create();


                            btn_positive.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Dismiss the alert dialog


                                    try {

                                        String Zname = DialogEditTovaUslugaName.getText().toString();
                                        String ZEdIzm = DialogEditTovarUslugaEdIzm.getText().toString();
                                        String ZKolvo = DialogEditTovaUslugaKolvo.getText().toString();
                                        String ZCenaEd = DialogEditTovaUslugaCenaEd.getText().toString();

                                        Sst.setTextStr("");
                                        TUeditStatus.setTextStr("");

                                        if (TovarUslugaSwitch.isChecked() == true) {
                                            TUeditStatus.setTextStr("Товар");
                                        } else {
                                            TUeditStatus.setTextStr("Услуга");
                                        }

                                        try {
                                            Sst.setTextStr("");
                                            float f1 = Float.parseFloat(DialogEditTovaUslugaKolvo.getText().toString());
                                            float f2 = Float.parseFloat(DialogEditTovaUslugaCenaEd.getText().toString());
                                            float f3 = f1 * f2;
                                            TovarUslugaStoimost.setText("Стоимость: " + String.valueOf(f3));

                                            Sst.setTextStr(String.valueOf(f3));
                                        } catch (NumberFormatException ex) {
                                        }


                                        //ChangeTOVARUSLUGA(String ID, String IDD, String Name, String Cena, String Kolvo, String EdIzm, String Stoimost)

                                        DB.ChangeTOVARUSLUGA(selectedIDtovarusluga, ID, Zname, ZCenaEd, ZKolvo, ZEdIzm, Sst.getTextStr(), TUeditStatus.getTextStr());


                                        tovariuslugilist.clear();
                                        idArrayTovariUslugi.clear();

                                        Toast.makeText(getActivity(), "Изменение товара/услуги произведено успешно", Toast.LENGTH_LONG).show();

                                        SQLiteDatabase db = DB.getReadableDatabase();
                                        Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);

                                        while (cursor.moveToNext()) {
                                            tovariuslugilist.add(new TOVARIUSLUGI(cursor.getString(2), cursor.getString(8), cursor.getString(3), "", cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(0)));
                                            idArrayTovariUslugi.add(cursor.getString(0));
                                        }

                                        TUadapter.notifyDataSetChanged();
                                        SetDListView.SetDynamicHeight(ListViewTOVARIUSLUGI);


                                        try {

                                            SQLiteDatabase db1 = DB.getReadableDatabase();
                                            Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Tovar' OR DOCUMENT='Товар');", null);
                                            String s4 = "";
                                            float Summ = 0;
                                            SummTovari = new TextStr();
                                            SummTovari.setTextStr("0");

                                            while (cursor1.moveToNext()) {
                                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                                try {
                                                    Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                                    SummTovari.setTextStr(Float.toString(Summ));
                                                } catch (NumberFormatException ex) {
                                                }
                                            }

                                        } catch (CursorIndexOutOfBoundsException CursorException) {
                                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                                        } catch (SQLException ex) {
                                        } catch (StaleDataException ex) {
                                        }

                                        try {
                                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                            SummTovari.setTextStr(Integer.toString(SummInt));
                                            StoimostTovarov.setText("Стоимость товаров: "+Integer.toString(SummInt));
                                        } catch (NumberFormatException ex) {
                                        } catch (ArithmeticException e) {
                                        } catch (ArrayStoreException ex) {
                                        } catch (ClassCastException ex) {
                                        } catch (NullPointerException ex) {
                                        }

                                        try {

                                            SQLiteDatabase db1 = DB.getReadableDatabase();
                                            Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Usluga' OR DOCUMENT='Услуга');", null);
                                            String s4 = "";
                                            float Summ = 0;
                                            SummTovari = new TextStr();
                                            SummTovari.setTextStr("0");

                                            while (cursor1.moveToNext()) {
                                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                                try {
                                                    Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                                    SummTovari.setTextStr(Float.toString(Summ));
                                                } catch (NumberFormatException ex) {
                                                }
                                            }

                                        } catch (CursorIndexOutOfBoundsException CursorException) {
                                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                                        } catch (SQLException ex) {
                                        } catch (StaleDataException ex) {
                                        }

                                        try {
                                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                            SummTovari.setTextStr(Integer.toString(SummInt));
                                            StoimostUslug.setText("Стоимость услуг: "+Integer.toString(SummInt));
                                        } catch (NumberFormatException ex) {
                                        } catch (ArithmeticException e) {
                                        } catch (ArrayStoreException ex) {
                                        } catch (ClassCastException ex) {
                                        } catch (NullPointerException ex) {
                                        }

                                        try {

                                            SQLiteDatabase db1 = DB.getReadableDatabase();
                                            Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                                            String s4 = "";
                                            float Summ = 0;
                                            SummTovari = new TextStr();
                                            SummTovari.setTextStr("0");

                                            while (cursor1.moveToNext()) {
                                                //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                                try {
                                                    Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                                    SummTovari.setTextStr(Float.toString(Summ));
                                                } catch (NumberFormatException ex) {
                                                }
                                            }

                                        } catch (CursorIndexOutOfBoundsException CursorException) {
                                            // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                                        } catch (SQLException ex) {
                                        } catch (StaleDataException ex) {
                                        }

                                        try {
                                            int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                            SummTovari.setTextStr(Integer.toString(SummInt));
                                            StoimostItogo.setText("ИТОГО: "+Integer.toString(SummInt));
                                        } catch (NumberFormatException ex) {
                                        } catch (ArithmeticException e) {
                                        } catch (ArrayStoreException ex) {
                                        } catch (ClassCastException ex) {
                                        } catch (NullPointerException ex) {
                                        }


                                    btn_del_go.setVisibility(View.GONE);
                                        edittovaruslugadialog.dismiss();


                                    } catch (SQLException mSQLException) {
                                        Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
                                    }

                                    edittovaruslugadialog.cancel();

                                }
                            });


                            btn_negative.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Dismiss/cancel the alert dialog
                                    //dialog.cancel();
                                    edittovaruslugadialog.dismiss();
                                    //Toast.makeText(getContext(),"No button clicked", Toast.LENGTH_SHORT).show();
                                }
                            });


                            btn_del.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Dismiss/cancel the alert dialog
                                    //dialog.cancel();

                                    btn_del_go.setVisibility(View.VISIBLE);

                                    btn_del_go.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            DB.DelTOVARUSLUGA(selectedIDtovarusluga);

                                            tovariuslugilist.clear();
                                            idArrayTovariUslugi.clear();

                                            Toast.makeText(getActivity(), "Товар/услуга успешно удалена", Toast.LENGTH_LONG).show();

                                            SQLiteDatabase db = DB.getReadableDatabase();
                                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);

                                            while (cursor.moveToNext()) {
                                                tovariuslugilist.add(new TOVARIUSLUGI(cursor.getString(2), cursor.getString(8), cursor.getString(3), "", cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(0)));
                                                idArrayTovariUslugi.add(cursor.getString(0));
                                            }

                                            TUadapter.notifyDataSetChanged();
                                            SetDListView.SetDynamicHeight(ListViewTOVARIUSLUGI);

                                            try {

                                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Tovar' OR DOCUMENT='Товар');", null);
                                                String s4 = "";
                                                float Summ = 0;
                                                SummTovari = new TextStr();
                                                SummTovari.setTextStr("0");

                                                while (cursor1.moveToNext()) {
                                                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                                    try {
                                                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                                        SummTovari.setTextStr(Float.toString(Summ));
                                                    } catch (NumberFormatException ex) {
                                                    }
                                                }

                                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                                            } catch (SQLException ex) {
                                            } catch (StaleDataException ex) {
                                            }

                                            try {
                                                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                                SummTovari.setTextStr(Integer.toString(SummInt));
                                                StoimostTovarov.setText("Стоимость товаров: "+Integer.toString(SummInt));
                                            } catch (NumberFormatException ex) {
                                            } catch (ArithmeticException e) {
                                            } catch (ArrayStoreException ex) {
                                            } catch (ClassCastException ex) {
                                            } catch (NullPointerException ex) {
                                            }

                                            try {

                                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Usluga' OR DOCUMENT='Услуга');", null);
                                                String s4 = "";
                                                float Summ = 0;
                                                SummTovari = new TextStr();
                                                SummTovari.setTextStr("0");

                                                while (cursor1.moveToNext()) {
                                                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                                    try {
                                                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                                        SummTovari.setTextStr(Float.toString(Summ));
                                                    } catch (NumberFormatException ex) {
                                                    }
                                                }

                                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                                            } catch (SQLException ex) {
                                            } catch (StaleDataException ex) {
                                            }

                                            try {
                                                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                                SummTovari.setTextStr(Integer.toString(SummInt));
                                                StoimostUslug.setText("Стоимость услуг: "+Integer.toString(SummInt));
                                            } catch (NumberFormatException ex) {
                                            } catch (ArithmeticException e) {
                                            } catch (ArrayStoreException ex) {
                                            } catch (ClassCastException ex) {
                                            } catch (NullPointerException ex) {
                                            }

                                            try {

                                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                                                String s4 = "";
                                                float Summ = 0;
                                                SummTovari = new TextStr();
                                                SummTovari.setTextStr("0");

                                                while (cursor1.moveToNext()) {
                                                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                                    try {
                                                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                                        SummTovari.setTextStr(Float.toString(Summ));
                                                    } catch (NumberFormatException ex) {
                                                    }
                                                }

                                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                                            } catch (SQLException ex) {
                                            } catch (StaleDataException ex) {
                                            }

                                            try {
                                                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                                SummTovari.setTextStr(Integer.toString(SummInt));
                                                StoimostItogo.setText("ИТОГО: "+Integer.toString(SummInt));
                                            } catch (NumberFormatException ex) {
                                            } catch (ArithmeticException e) {
                                            } catch (ArrayStoreException ex) {
                                            } catch (ClassCastException ex) {
                                            } catch (NullPointerException ex) {
                                            }

                                            btn_del_go.setVisibility(View.GONE);
                                            edittovaruslugadialog.dismiss();

                                        }
                                    });

                                }
                            });

                            edittovaruslugadialog.show();
                        }
                    });


            db.close();


        } catch (CursorIndexOutOfBoundsException CursorException) {
            Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
        }


        //endregion


        //region КНОПКА СОХРАНИТЬ СДЕЛКУ В БД

        Button DBsaveSdelka = (Button) rootView.findViewById(R.id.buttonaddsdelka);
        DBsaveSdelka.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            String SdelkaName = ETSdelkaName.getText().toString();

                            String SKontragentFullName = KontragentFullName.getText().toString();
                            String SKontragentSokrName = KontragentSokrName.getText().toString();
                            String SKontragentINN = KontragentINN.getText().toString();
                            String SKontragentKPP = KontragentKPP.getText().toString();
                            String SKontragentOGRN = KontragentOGRN.getText().toString();
                            String SKontragentBankName = KontragentBankName.getText().toString();
                            String SKontragentBankBIK = KontragentBankBIK.getText().toString();
                            String SKontragentBankKS = KontragentBankKS.getText().toString();
                            String SKontragentBankRS = KontragentBankRS.getText().toString();
                            String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
                            String SKontragentVlice = KontragentVlice.getText().toString();
                            String SKontragentFIOruk = KontragentFIOruk.getText().toString();
                            String SKontragentUrAddr = KontragentUrAddr.getText().toString();
                            String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
                            String SKontragentPostAddr = KontragentPostAddr.getText().toString();
                            String SKontragentPhone = KontragentPhone.getText().toString();
                            String SKontragentMobile = KontragentMobile.getText().toString();
                            String SKontragentEmail = KontragentEmail.getText().toString();
                            String SKontragentSite = KontragentSite.getText().toString();
                            String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

                            String SMyFullName = MyFullName.getText().toString();
                            String SMySokrName = MySokrName.getText().toString();
                            String SMyINN = MyINN.getText().toString();
                            String SMyKPP = MyKPP.getText().toString();
                            String SMyOGRN = MyOGRN.getText().toString();
                            String SMyBankName = MyBankName.getText().toString();
                            String SMyBankBIK = MyBankBIK.getText().toString();
                            String SMyBankKS = MyBankKS.getText().toString();
                            String SMyBankRS = MyBankRS.getText().toString();
                            String SMyRukDolzhn = MyRukDolzhn.getText().toString();
                            String SMyVlice = MyVlice.getText().toString();
                            String SMyFIOruk = MyFIOruk.getText().toString();
                            String SMyUrAddr = MyUrAddr.getText().toString();
                            String SMyFaktAddr = MyFaktAddr.getText().toString();
                            String SMyPostAddr = MyPostAddr.getText().toString();
                            String SMyPhone = MyPhone.getText().toString();
                            String SMyMobile = MyMobile.getText().toString();
                            String SMyEmail = MyEmail.getText().toString();
                            String SMySite = MySite.getText().toString();
                            String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();
                            String SMyLogo = MyLogo.getText().toString();
                            String SMyPechat = MyPechat.getText().toString();
                            String SMySlogan = MySlogan.getText().toString();

                            String DataDogovora = ETDataDogovora.getText().toString();
                            String NomerDogovora = ETNomerDogovora.getText().toString();
                            String GorodDogovora = ETGorodDogovora.getText().toString();
                            String UslugiPo = ETUslugiPo.getText().toString();
                            String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
                            String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
                            String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
                            String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
                            String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
                            String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
                            String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
                            String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
                            String Sud = ETSud.getText().toString();
                            String Status = "";

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


                            if (Step1.isChecked()) {
                                Status = "1";
                            }
                            if (Step2.isChecked()) {
                                Status = "2";
                            }
                            if (Step3.isChecked()) {
                                Status = "3";
                            }
                            if (Step4.isChecked()) {
                                Status = "4";
                            }
                            if (Step5.isChecked()) {
                                Status = "5";
                            }
                            if (Step6.isChecked()) {
                                Status = "6";
                            }
                            if (Step7.isChecked()) {
                                Status = "7";
                            }
                            if (Step8.isChecked()) {
                                Status = "8";
                            }
                            if (Step9.isChecked()) {
                                Status = "9";
                            }
                            if (Step10.isChecked()) {
                                Status = "10";
                            }
                            if (Step11.isChecked()) {
                                Status = "11";
                            }
                            if (Step12.isChecked()) {
                                Status = "12";
                            }
                            if (Step13.isChecked()) {
                                Status = "13";
                            }
                            if (Step14.isChecked()) {
                                Status = "14";
                            }
                            if (Step15.isChecked()) {
                                Status = "15";
                            }
                            if (Step16.isChecked()) {
                                Status = "16";
                            }
                            if (Step17.isChecked()) {
                                Status = "17";
                            }
                            if (Step18.isChecked()) {
                                Status = "18";
                            }
                            if (Step19.isChecked()) {
                                Status = "19";
                            }
                            if (Step20.isChecked()) {
                                Status = "20";
                            }
                            if (Step21.isChecked()) {
                                Status = "21";
                            }
                            if (Step22.isChecked()) {
                                Status = "22";
                            }
                            if (Step23.isChecked()) {
                                Status = "23";
                            }
                            if (Step24.isChecked()) {
                                Status = "24";
                            }
                            if (Step25.isChecked()) {
                                Status = "25";
                            }
                            if (Step26.isChecked()) {
                                Status = "26";
                            }


                            String KPNomer = ETKPNomer.getText().toString();
                            String KPData = ETKPData.getText().toString();
                            String SchetNomer = ETSchetNomer.getText().toString();
                            String SchetData = ETSchetData.getText().toString();
                            String AktNomer = ETAktNomer.getText().toString();
                            String AktData = ETAktData.getText().toString();
                            String NakladnajaNomer = ETNakladnajaNomer.getText().toString();
                            String NakladnajaData = ETNakladnajaData.getText().toString();
                            
                            String[] s1 = StoimostTovarov.getText().toString().split(": ");
                            String[] s2 = StoimostUslug.getText().toString().split(": ");
                            String[] s3 = StoimostItogo.getText().toString().split(": ");
                            
                            String ItogoTovarov=s1[1];
                            String ItogoUslug=s2[1];
                            String ItogoSdelka=s3[1];


                            DB.ChangeSdelka(ID,
                                    SdelkaName,
                                    SKontragentFullName,
                                    SKontragentSokrName,
                                    SKontragentINN,
                                    SKontragentKPP,
                                    SKontragentOGRN,
                                    SKontragentBankName,
                                    SKontragentBankBIK,
                                    SKontragentBankKS,
                                    SKontragentBankRS,
                                    SKontragentRukDolzhn,
                                    SKontragentVlice,
                                    SKontragentFIOruk,
                                    SKontragentUrAddr,
                                    SKontragentFaktAddr,
                                    SKontragentPostAddr,
                                    SKontragentPhone,
                                    SKontragentMobile,
                                    SKontragentEmail,
                                    SKontragentSite,
                                    SKontragentOtvetstvennij,
                                    SMyFullName,
                                    SMySokrName,
                                    SMyINN,
                                    SMyKPP,
                                    SMyOGRN,
                                    SMyBankName,
                                    SMyBankBIK,
                                    SMyBankKS,
                                    SMyBankRS,
                                    SMyRukDolzhn,
                                    SMyVlice,
                                    SMyFIOruk,
                                    SMyUrAddr,
                                    SMyFaktAddr,
                                    SMyPostAddr,
                                    SMyPhone,
                                    SMyMobile,
                                    SMyEmail,
                                    SMySite,
                                    SMyOtvetstvennij,
                                    NomerDogovora,
                                    DataDogovora,
                                    GorodDogovora,
                                    UslugiPo,
                                    SrokiPostavkiTovarov,
                                    SrokiOkazanijaUslug,
                                    UslovijaOplatiTovarov,
                                    UslovijaOplatiUslug,
                                    UslovijaPriemkiTovarov,
                                    UslovijaPriemkiUslug,
                                    UslovijaGarantii,
                                    OsobijeUslovija,
                                    Sud,
                                    Status,
                                    SMyLogo,
                                    SMyPechat,
                                    SMySlogan,
                                    KPNomer,
                                    KPData,
                                    SchetNomer,
                                    SchetData,
                                    AktNomer,
                                    AktData,
                                    NakladnajaNomer,
                                    NakladnajaData, 
                                    ItogoTovarov, 
                                    ItogoUslug, 
                                    ItogoSdelka
                                    );


                            Toast.makeText(getActivity(), "Изменения в сделке успешно сохранены в базе", Toast.LENGTH_LONG)
                                    .show();

                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.sdelkiclose();

                        } catch (SQLException mSQLException) {
                            Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

        //endregion

        //region КНОПКА ВЫБРАТЬ КОНТРАГЕНТА

        Button SelectKontragent = (Button) rootView.findViewById(R.id.buttonselectkontragent);
        SelectKontragent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            final ArrayList<Sdelki> listkontragenti = new ArrayList<Sdelki>();


                            AlertDialog.Builder selectkontragentbuilder = new AlertDialog.Builder(container.getContext());
                            selectkontragentbuilder.setCancelable(false);
                            View dialogView = inflater.inflate(R.layout.alertdialog_select_sdelka, null); //важно - inflater определен в начале кода фрагмента
                            // Привязка xml-разметки окна диалогов
                            selectkontragentbuilder.setView(dialogView);

                            final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                            final Button btn_neutral = (Button) dialogView.findViewById(R.id.dialog_neutral_btn);
                            final ListView ListViewKontragenti = (ListView) dialogView.findViewById(R.id.ListViewSDELKI);
                            TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                            Zagolovok.setText("Выберите контрагента:");
                            btn_neutral.setText("Без контрагента");

                            //   final ArrayList idArray1 = new ArrayList();
                            SQLiteDatabase db = DB.getReadableDatabase();
                            String[] dbcolumns = new String[]{"SOKRNAME", "BANKNAME", "INN", "ID"};

                            Cursor cursor = db.query("KONTRAGENTI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов


                            while (cursor.moveToNext()) {
                                listkontragenti.add(new Sdelki(
                                        cursor.getString(0),
                                        "БАНК: " + cursor.getString(1),
                                        "ИНН: " + cursor.getString(2),
                                        cursor.getString(3)));

                            }

                            final SdelkiListAdapter arrayAdapter = new SdelkiListAdapter(getActivity(), listkontragenti);

                            ListViewKontragenti.setAdapter(arrayAdapter);
                            arrayAdapter.notifyDataSetChanged();
                            final AlertDialog selectkontragentdialog = selectkontragentbuilder.create();
                            SetDListView.SetDynamicHeight(ListViewKontragenti);

                            selectkontragentdialog.show();


                            ListViewKontragenti.setOnItemClickListener(
                                    new OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                                            final String selectedID1 = listkontragenti.get(position).getidnumber().toString();

                                            // =================СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================
                                            try {

                                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                                Cursor cursor = db1.rawQuery("SELECT * FROM KONTRAGENTI WHERE ID = " + selectedID1, null);
                                                cursor.moveToNext(); // без этого exception

                                                KontragentFullName.setText(cursor.getString(1));
                                                KontragentSokrName.setText(cursor.getString(2));
                                                KontragentINN.setText(cursor.getString(3));
                                                KontragentKPP.setText(cursor.getString(4));
                                                KontragentOGRN.setText(cursor.getString(5));
                                                KontragentBankName.setText(cursor.getString(6));
                                                KontragentBankBIK.setText(cursor.getString(7));
                                                KontragentBankKS.setText(cursor.getString(8));
                                                KontragentBankRS.setText(cursor.getString(9));
                                                KontragentRukDolzhn.setText(cursor.getString(10));
                                                KontragentVlice.setText(cursor.getString(11));
                                                KontragentFIOruk.setText(cursor.getString(12));
                                                KontragentUrAddr.setText(cursor.getString(13));
                                                KontragentFaktAddr.setText(cursor.getString(14));
                                                KontragentPostAddr.setText(cursor.getString(15));
                                                KontragentPhone.setText(cursor.getString(16));
                                                KontragentMobile.setText(cursor.getString(17));
                                                KontragentEmail.setText(cursor.getString(18));
                                                KontragentSite.setText(cursor.getString(19));
                                                KontragentOtvetstvennij.setText(cursor.getString(20));

                                                db1.close();

                                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                                Toast.makeText(
                                                        getActivity(),
                                                        CursorException.toString(),
                                                        Toast.LENGTH_LONG)
                                                        .show();
                                            }

                                            // =================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================

                                            selectkontragentdialog.dismiss();
                                        }
                                    });


                            btn_negative.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    selectkontragentdialog.dismiss();

                                }
                            });

                            btn_neutral.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    KontragentFullName.setText("");
                                    KontragentSokrName.setText("");
                                    KontragentINN.setText("");
                                    KontragentKPP.setText("");
                                    KontragentOGRN.setText("");
                                    KontragentBankName.setText("");
                                    KontragentBankBIK.setText("");
                                    KontragentBankKS.setText("");
                                    KontragentBankRS.setText("");
                                    KontragentRukDolzhn.setText("");
                                    KontragentVlice.setText("");
                                    KontragentFIOruk.setText("");
                                    KontragentUrAddr.setText("");
                                    KontragentFaktAddr.setText("");
                                    KontragentPostAddr.setText("");
                                    KontragentPhone.setText("");
                                    KontragentMobile.setText("");
                                    KontragentEmail.setText("");
                                    KontragentSite.setText("");
                                    KontragentOtvetstvennij.setText("");
                                    selectkontragentdialog.dismiss();
                                }
                            });


                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
                        }


                    }
                });

        //endregion

        //region КНОПКА ВЫБРАТЬ МОЮ ОРГАНИЗАЦИЮ

        Button SelectMyOrg = (Button) rootView.findViewById(R.id.buttonselectmyorg);
        SelectMyOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    final ArrayList<Sdelki> listmyorg = new ArrayList<Sdelki>();


                    AlertDialog.Builder selectmyorgbuilder = new AlertDialog.Builder(container.getContext());
                    selectmyorgbuilder.setCancelable(false);
                    View dialogView = inflater.inflate(R.layout.alertdialog_select_sdelka, null); //важно - inflater определен в начале кода фрагмента
                    // Привязка xml-разметки окна диалогов
                    selectmyorgbuilder.setView(dialogView);

                    final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                    final Button btn_neutral = (Button) dialogView.findViewById(R.id.dialog_neutral_btn);
                    final ListView ListViewKontragenti = (ListView) dialogView.findViewById(R.id.ListViewSDELKI);
                    TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                    Zagolovok.setText("Выберите свою организацию:");
                    btn_neutral.setText("Без своей организации");

                    //   final ArrayList idArray1 = new ArrayList();
                    SQLiteDatabase db = DB.getReadableDatabase();
                    String[] dbcolumns = new String[]{"SOKRNAME", "BANKNAME", "INN", "ID"};

                    Cursor cursor = db.query("MYFIRMREKVIZITI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов


                    while (cursor.moveToNext()) {
                        listmyorg.add(new Sdelki(
                                cursor.getString(0),
                                "БАНК: " + cursor.getString(1),
                                "ИНН: " + cursor.getString(2),
                                cursor.getString(3)));

                    }

                    final SdelkiListAdapter arrayAdapter = new SdelkiListAdapter(getActivity(), listmyorg);

                    ListViewKontragenti.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    final AlertDialog selectmyorgdialog = selectmyorgbuilder.create();
                    SetDListView.SetDynamicHeight(ListViewKontragenti);

                    selectmyorgdialog.show();


                    ListViewKontragenti.setOnItemClickListener(
                            new OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                                    final String selectedID2 = listmyorg.get(position).getidnumber().toString();

                                    // =================СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================
                                    try {

                                        SQLiteDatabase db1 = DB.getReadableDatabase();
                                        Cursor cursor = db1.rawQuery("SELECT * FROM MYFIRMREKVIZITI WHERE ID = " + selectedID2, null);
                                        cursor.moveToNext(); // без этого exception

                                        MyFullName.setText(cursor.getString(1));
                                        MySokrName.setText(cursor.getString(2));
                                        MyINN.setText(cursor.getString(3));
                                        MyKPP.setText(cursor.getString(4));
                                        MyOGRN.setText(cursor.getString(5));
                                        MyBankName.setText(cursor.getString(6));
                                        MyBankBIK.setText(cursor.getString(7));
                                        MyBankKS.setText(cursor.getString(8));
                                        MyBankRS.setText(cursor.getString(9));
                                        MyRukDolzhn.setText(cursor.getString(10));
                                        MyVlice.setText(cursor.getString(11));
                                        MyFIOruk.setText(cursor.getString(12));
                                        MyUrAddr.setText(cursor.getString(13));
                                        MyFaktAddr.setText(cursor.getString(14));
                                        MyPostAddr.setText(cursor.getString(15));
                                        MyPhone.setText(cursor.getString(16));
                                        MyMobile.setText(cursor.getString(17));
                                        MyEmail.setText(cursor.getString(18));
                                        MySite.setText(cursor.getString(19));
                                        MyLogo.setText(cursor.getString(20));
                                        MyPechat.setText(cursor.getString(21));
                                        MySlogan.setText(cursor.getString(22));

                                        db1.close();

                                    } catch (CursorIndexOutOfBoundsException CursorException) {
                                        Toast.makeText(
                                                getActivity(),
                                                CursorException.toString(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }

                                    // =================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================

                                    selectmyorgdialog.dismiss();
                                }
                            });


                    btn_negative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            selectmyorgdialog.dismiss();

                        }
                    });

                    btn_neutral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyFullName.setText("");
                            MySokrName.setText("");
                            MyINN.setText("");
                            MyKPP.setText("");
                            MyOGRN.setText("");
                            MyBankName.setText("");
                            MyBankBIK.setText("");
                            MyBankKS.setText("");
                            MyBankRS.setText("");
                            MyRukDolzhn.setText("");
                            MyVlice.setText("");
                            MyFIOruk.setText("");
                            MyUrAddr.setText("");
                            MyFaktAddr.setText("");
                            MyPostAddr.setText("");
                            MyPhone.setText("");
                            MyMobile.setText("");
                            MyEmail.setText("");
                            MySite.setText("");
                            MyLogo.setText("");
                            MyPechat.setText("");
                            MySlogan.setText("");

                            selectmyorgdialog.dismiss();
                        }
                    });

                    db.close();

                } catch (CursorIndexOutOfBoundsException CursorException) {
                    Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
                }


            }
        });

//endregion

        //region КНОПКА УДАЛИТЬ СДЕЛКУ

        Button DBdelSdelka = (Button) rootView.findViewById(R.id.butondelsdelka);

        DBdelSdelka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addel = new AlertDialog.Builder(container.getContext());
                addel.setCancelable(true);
                View dialogView = inflater.inflate(R.layout.alertdialog_delete, null); //важно - inflater определен в начале кода фрагмента
                // Привязка xml-разметки окна диалогов
                addel.setView(dialogView);
                final AlertDialog deldialog = addel.create();

                final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
                Zagolovok.setText("Удалить сделку и все связанные документы из базы?");

                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        deldialog.cancel();

                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DB.DelSdelka(ID);

                        Toast.makeText(getActivity(), "Сделка и все связанные документы успешно удалены из базы", Toast.LENGTH_LONG).show();
                        deldialog.cancel();
                        MainActivity rootActivity = (MainActivity) getActivity();
                        rootActivity.sdelkiclose();

                    }
                });


                deldialog.show();

            }
        });
        //endregion

        //region КНОПКА ДОБАВИТЬ ЗАМЕТКУ

        Button ButtonAddZametka = (Button) rootView.findViewById(R.id.buttonaddzametka);

        ButtonAddZametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder addzametkabuilder = new AlertDialog.Builder(container.getContext());

                View dialogView = inflater.inflate(R.layout.alertdialog_add_zametka, null); //важно - inflater определен в начале кода фрагмента

                addzametkabuilder.setCancelable(false);

                // Привязка xml-разметки окна диалогов
                addzametkabuilder.setView(dialogView);


                Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                final EditText DialogAddZametkaName = (EditText) dialogView.findViewById(R.id.dialogadd_zametka_name);
                final EditText DialogAddZametkaOpisanie = (EditText) dialogView.findViewById(R.id.dialogadd_zametka_opisanie);
                final EditText DialogAddZametkaData = (EditText) dialogView.findViewById(R.id.dialogadd_zametka_data);

                // Создание диалога
                final AlertDialog addzametkadialog = addzametkabuilder.create();


                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss the alert dialog


                        try {

                            String Zname = DialogAddZametkaName.getText().toString();
                            String Zopisanie = DialogAddZametkaOpisanie.getText().toString();
                            String Zdata = DialogAddZametkaData.getText().toString();

                            DB.AddZAMETKA(Zname, Zopisanie, ID, Zdata);


                            listzametki.clear();
                            idArrayZametki.clear();

                            Toast.makeText(getActivity(), "Заметка успешно добавлена в базу", Toast.LENGTH_LONG).show();

                            SQLiteDatabase db = DB.getReadableDatabase();

                            Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null);

                            while (cursor.moveToNext()) {
                                listzametki.add(new ZAMETKI(cursor.getString(1), "", "", "Дата: " + cursor.getString(3), "", ""));
                                idArrayZametki.add(cursor.getString(0));
                            }

                            Zadapter.notifyDataSetChanged();

                            SetDListView.SetDynamicHeight(ListViewZAMETKI);


                            db.close();

                        } catch (SQLException mSQLException) {
                            Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
                        }

                        addzametkadialog.cancel();

                    }
                });


                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss/cancel the alert dialog
                        //dialog.cancel();
                        addzametkadialog.dismiss();
                        //Toast.makeText(getContext(),"No button clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                addzametkadialog.show();
            }
        });

        //endregion

        //region КНОПКА ДОБАВИТЬ ТОВАР-УСЛУГУ

        Button ButtonAddTovarUsluga = (Button) rootView.findViewById(R.id.buttonaddtovarusluga);

        ButtonAddTovarUsluga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder addtovaruslugabuilder = new AlertDialog.Builder(container.getContext());

                View dialogView = inflater.inflate(R.layout.alertdialog_add_tovarusluga, null); //важно - inflater определен в начале кода фрагмента

                addtovaruslugabuilder.setCancelable(false);

                // Привязка xml-разметки окна диалогов
                addtovaruslugabuilder.setView(dialogView);

                Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                Button btn_neutral = (Button) dialogView.findViewById(R.id.dialog_neutral_btn);

                final EditText DialogAddTovarUslugaName = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_name);
                final EditText DialogAddTovarUslugaEdizm = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_edizm);
                final EditText DialogAddTovarUslugaKolvo = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_kolvo);
                final EditText DialogAddTovarUslugaCenaed = (EditText) dialogView.findViewById(R.id.dialog_addtovarusluga_cenaed);
                final TextView DialogAddTovarUslugaStoimost = (TextView) dialogView.findViewById(R.id.dialog_addtovarusluga_stoimost);

                final Switch TovarUslugaSwitch = (Switch)dialogView.findViewById(R.id.tovarusluga_switch);


                // Создание диалога
                final AlertDialog addtovaruslugadialog = addtovaruslugabuilder.create();


                btn_neutral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Sst.setTextStr("");
                        try {
                            Sst.setTextStr("");
                            float f1 = Float.parseFloat(DialogAddTovarUslugaKolvo.getText().toString());
                            float f2 = Float.parseFloat(DialogAddTovarUslugaCenaed.getText().toString());
                            float f3 = f1 * f2;
                            DialogAddTovarUslugaStoimost.setText("Стоимость: " + String.valueOf(f3));

                            Sst.setTextStr(String.valueOf(f3));
                        } catch (NumberFormatException ex) {
                        }
                    }
                });

                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss the alert dialog

                        Sst.setTextStr("");



                            try {
                                Sst.setTextStr("");
                                float f1 = Float.parseFloat(DialogAddTovarUslugaKolvo.getText().toString());
                                float f2 = Float.parseFloat(DialogAddTovarUslugaCenaed.getText().toString());
                                float f3 = f1 * f2;
                                DialogAddTovarUslugaStoimost.setText("Стоимость: " + String.valueOf(f3));
                                Sst.setTextStr(String.valueOf(f3));
                            } catch (NumberFormatException ex) {
                            }

                            if (TovarUslugaSwitch.isChecked() == true) {
                                TUaddStatus.setTextStr("Товар");
                            } else {
                                TUaddStatus.setTextStr("Услуга");
                            }

                            //public void AddTOVARUSLUGA(String IDD, String Name, String Cena, String Kolvo, String EdIzm, String Stoimost)
                            DB.AddTOVARUSLUGA(ID, DialogAddTovarUslugaName.getText().toString(), DialogAddTovarUslugaCenaed.getText().toString(), DialogAddTovarUslugaKolvo.getText().toString(), DialogAddTovarUslugaEdizm.getText().toString(), Sst.getTextStr(), TUaddStatus.getTextStr());
                            tovariuslugilist.clear();
                            idArrayTovariUslugi.clear();


                            SQLiteDatabase db = DB.getReadableDatabase();
                            Cursor cursor = db.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);

                            while (cursor.moveToNext()) {
                                tovariuslugilist.add(new TOVARIUSLUGI(cursor.getString(2), cursor.getString(8), cursor.getString(3), "", cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(0)));
                                idArrayTovariUslugi.add(cursor.getString(0));

                                addtovaruslugadialog.dismiss();
                            }

                            TUadapter.notifyDataSetChanged();
                            SetDListView.SetDynamicHeight(ListViewTOVARIUSLUGI);

                            try {

                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Tovar' OR DOCUMENT='Товар');", null);
                                String s4 = "";
                                float Summ = 0;
                                SummTovari = new TextStr();
                                SummTovari.setTextStr("0");

                                while (cursor1.moveToNext()) {
                                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                    try {
                                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                        SummTovari.setTextStr(Float.toString(Summ));
                                    } catch (NumberFormatException ex) {
                                    }
                                }

                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                            } catch (SQLException ex) {
                            } catch (StaleDataException ex) {
                            }

                            try {
                                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                SummTovari.setTextStr(Integer.toString(SummInt));
                                StoimostTovarov.setText("Стоимость товаров: "+Integer.toString(SummInt));
                            } catch (NumberFormatException ex) {
                            } catch (ArithmeticException e) {
                            } catch (ArrayStoreException ex) {
                            } catch (ClassCastException ex) {
                            } catch (NullPointerException ex) {
                            }

                            try {

                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID + " AND (DOCUMENT='Usluga' OR DOCUMENT='Услуга');", null);
                                String s4 = "";
                                float Summ = 0;
                                SummTovari = new TextStr();
                                SummTovari.setTextStr("0");

                                while (cursor1.moveToNext()) {
                                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                    try {
                                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                        SummTovari.setTextStr(Float.toString(Summ));
                                    } catch (NumberFormatException ex) {
                                    }
                                }

                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                            } catch (SQLException ex) {
                            } catch (StaleDataException ex) {
                            }

                            try {
                                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                SummTovari.setTextStr(Integer.toString(SummInt));
                                StoimostUslug.setText("Стоимость услуг: "+Integer.toString(SummInt));
                            } catch (NumberFormatException ex) {
                            } catch (ArithmeticException e) {
                            } catch (ArrayStoreException ex) {
                            } catch (ClassCastException ex) {
                            } catch (NullPointerException ex) {
                            }

                            try {

                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                Cursor cursor1 = db1.rawQuery("SELECT * FROM TOVARIUSLUGI_SDELKI WHERE IDD = " + ID, null);
                                String s4 = "";
                                float Summ = 0;
                                SummTovari = new TextStr();
                                SummTovari.setTextStr("0");

                                while (cursor1.moveToNext()) {
                                    //public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String Kolvo, String EdIzm, String Stoimost, String idnumber)

                                    try {
                                        Summ = Summ + Float.parseFloat(cursor1.getString(7));
                                        SummTovari.setTextStr(Float.toString(Summ));
                                    } catch (NumberFormatException ex) {
                                    }
                                }

                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
                            } catch (SQLException ex) {
                            } catch (StaleDataException ex) {
                            }

                            try {
                                int SummInt = Math.round((int) Float.parseFloat(SummTovari.getTextStr()));
                                SummTovari.setTextStr(Integer.toString(SummInt));
                                StoimostItogo.setText("ИТОГО: "+Integer.toString(SummInt));
                            } catch (NumberFormatException ex) {
                            } catch (ArithmeticException e) {
                            } catch (ArrayStoreException ex) {
                            } catch (ClassCastException ex) {
                            } catch (NullPointerException ex) {
                            }

                        addtovaruslugadialog.cancel();

                    }
                });


                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss/cancel the alert dialog
                        //dialog.cancel();
                        addtovaruslugadialog.dismiss();
                        //Toast.makeText(getContext(),"No button clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                addtovaruslugadialog.show();
            }
        });

        //endregion

        //region ОБРАБОТКА ВЫБОРА - ЗАПИСЬ В БД ДЛЯ РАДИОГРУППЫ СТАТУСА СДЕЛКИ

        try {
            SQLiteDatabase db = DB.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM SDELKI WHERE ID = '" + ID + "';", null);
            cursor.moveToNext(); //без этого exception

            db.close();

        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.radio_step1:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db1 = DB.getReadableDatabase();
                        db1.execSQL("UPDATE SDELKI SET STATUS = '1' WHERE ID ='" + ID + "'; ");
                        db1.close();
                        break;


                    case R.id.radio_step2:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db2 = DB.getReadableDatabase();
                        db2.execSQL("UPDATE SDELKI SET STATUS = '2' WHERE ID ='" + ID + "'; ");
                        db2.close();
                        break;


                    case R.id.radio_step3:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db3 = DB.getReadableDatabase();
                        db3.execSQL("UPDATE SDELKI SET STATUS = '3' WHERE ID ='" + ID + "'; ");
                        db3.close();
                        break;


                    case R.id.radio_step4:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db4 = DB.getReadableDatabase();
                        db4.execSQL("UPDATE SDELKI SET STATUS = '4' WHERE ID ='" + ID + "'; ");
                        db4.close();
                        break;


                    case R.id.radio_step5:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db5 = DB.getReadableDatabase();
                        db5.execSQL("UPDATE SDELKI SET STATUS = '5' WHERE ID ='" + ID + "'; ");
                        db5.close();
                        break;


                    case R.id.radio_step6:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db6 = DB.getReadableDatabase();
                        db6.execSQL("UPDATE SDELKI SET STATUS = '6' WHERE ID ='" + ID + "'; ");
                        db6.close();
                        break;


                    case R.id.radio_step7:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db7 = DB.getReadableDatabase();
                        db7.execSQL("UPDATE SDELKI SET STATUS = '7' WHERE ID ='" + ID + "'; ");
                        db7.close();
                        break;

                    case R.id.radio_step8:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db8 = DB.getReadableDatabase();
                        db8.execSQL("UPDATE SDELKI SET STATUS = '8' WHERE ID ='" + ID + "'; ");
                        db8.close();
                        break;

                    case R.id.radio_step9:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db9 = DB.getReadableDatabase();
                        db9.execSQL("UPDATE SDELKI SET STATUS = '9' WHERE ID ='" + ID + "'; ");
                        db9.close();
                        break;

                    case R.id.radio_step10:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db10 = DB.getReadableDatabase();
                        db10.execSQL("UPDATE SDELKI SET STATUS = '10' WHERE ID ='" + ID + "'; ");
                        db10.close();
                        break;

                    case R.id.radio_step11:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db11 = DB.getReadableDatabase();
                        db11.execSQL("UPDATE SDELKI SET STATUS = '11' WHERE ID ='" + ID + "'; ");
                        db11.close();
                        break;

                    case R.id.radio_step12:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db12 = DB.getReadableDatabase();
                        db12.execSQL("UPDATE SDELKI SET STATUS = '12' WHERE ID ='" + ID + "'; ");
                        db12.close();
                        break;

                    case R.id.radio_step13:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db13 = DB.getReadableDatabase();
                        db13.execSQL("UPDATE SDELKI SET STATUS = '13' WHERE ID ='" + ID + "'; ");
                        db13.close();
                        break;

                    case R.id.radio_step14:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db14 = DB.getReadableDatabase();
                        db14.execSQL("UPDATE SDELKI SET STATUS = '14' WHERE ID ='" + ID + "'; ");
                        db14.close();
                        break;

                    case R.id.radio_step15:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db15 = DB.getReadableDatabase();
                        db15.execSQL("UPDATE SDELKI SET STATUS = '15' WHERE ID ='" + ID + "'; ");
                        db15.close();
                        break;

                    case R.id.radio_step16:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db16 = DB.getReadableDatabase();
                        db16.execSQL("UPDATE SDELKI SET STATUS = '16' WHERE ID ='" + ID + "'; ");
                        db16.close();
                        break;

                    case R.id.radio_step17:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db17 = DB.getReadableDatabase();
                        db17.execSQL("UPDATE SDELKI SET STATUS = '17' WHERE ID ='" + ID + "'; ");
                        db17.close();
                        break;

                    case R.id.radio_step18:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db18 = DB.getReadableDatabase();
                        db18.execSQL("UPDATE SDELKI SET STATUS = '18' WHERE ID ='" + ID + "'; ");
                        db18.close();
                        break;

                    case R.id.radio_step19:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db19 = DB.getReadableDatabase();
                        db19.execSQL("UPDATE SDELKI SET STATUS = '19' WHERE ID ='" + ID + "'; ");
                        db19.close();
                        break;

                    case R.id.radio_step20:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db20 = DB.getReadableDatabase();
                        db20.execSQL("UPDATE SDELKI SET STATUS = '20' WHERE ID ='" + ID + "'; ");
                        db20.close();
                        break;

                    case R.id.radio_step21:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db21 = DB.getReadableDatabase();
                        db21.execSQL("UPDATE SDELKI SET STATUS = '21' WHERE ID ='" + ID + "'; ");
                        db21.close();
                        break;

                    case R.id.radio_step22:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db22 = DB.getReadableDatabase();
                        db22.execSQL("UPDATE SDELKI SET STATUS = '22' WHERE ID ='" + ID + "'; ");
                        db22.close();
                        break;

                    case R.id.radio_step23:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db23 = DB.getReadableDatabase();
                        db23.execSQL("UPDATE SDELKI SET STATUS = '23' WHERE ID ='" + ID + "'; ");
                        db23.close();
                        break;

                    case R.id.radio_step24:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db24 = DB.getReadableDatabase();
                        db24.execSQL("UPDATE SDELKI SET STATUS = '24' WHERE ID ='" + ID + "'; ");
                        db24.close();
                        break;

                    case R.id.radio_step25:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db25 = DB.getReadableDatabase();
                        db25.execSQL("UPDATE SDELKI SET STATUS = '25' WHERE ID ='" + ID + "'; ");
                        db25.close();
                        break;

                    case R.id.radio_step26:
                        DB = new SQLiteConnect(getActivity());
                        SQLiteDatabase db26 = DB.getReadableDatabase();
                        db26.execSQL("UPDATE SDELKI SET STATUS = '26' WHERE ID ='" + ID + "'; ");
                        db26.close();
                        break;

                    default:
                        break;
                }
            }
        });

        //endregion


        return rootView;
    }
}
