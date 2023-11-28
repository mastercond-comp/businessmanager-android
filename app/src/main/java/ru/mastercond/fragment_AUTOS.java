package ru.mastercond;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ru.mastercond.AutoID;
import java.util.ArrayList;


public class fragment_AUTOS extends Fragment {


    private SQLiteConnect DB;
    private ListView ListViewSdelki1;
    private ArrayList<Sdelki> list;
    private ArrayList<Sdelki> kontragentlist;
    AlertDialog.Builder dialogkontragent;
    private SetDynamicHeightListView SetDListView;

    public fragment_AUTOS() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(
            final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_autos, container, false);
        final MainActivity appActivity=(MainActivity)getActivity();
        appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

        DB = new SQLiteConnect(getActivity());
        SetDListView = new SetDynamicHeightListView();

        //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

        final LinearLayout fragRoot = (LinearLayout) rootView.findViewById(R.id.LLContainer);
        fragRoot.setOnClickListener(new OnClickListener() {
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

                LayoutParams param = new LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density * 350), ViewGroup.LayoutParams.WRAP_CONTENT);
                fragRoot.setLayoutParams(param);

                ListView LVK = (ListView) rootView.findViewById(R.id.ListViewAutos);
                LayoutParams paramHeight = new LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density * 350), Math.round(getActivity().getResources().getDisplayMetrics().density * 400)); //LayoutParams(width, height) в px
                LVK.setLayoutParams(paramHeight);

            }

            db.close();
        } catch (CursorIndexOutOfBoundsException CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG),"show");
        }

        //endregion

        //region КНОПКА ДОБАВИТЬ АВТОМОБИЛЬ

        Button DBaddAUTO = (Button) rootView.findViewById(R.id.buttonaddautoautos);
        DBaddAUTO.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.addauto();

            }
        });

        //endregion


        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД

        /*

        db.execSQL("CREATE TABLE IF NOT EXISTS
        AUTO (ID Integer Primary Key Autoincrement NOT NULL,
        MARKA Varchar(255),
        GOSNOMER Varchar(255),
        COLOR Varchar(255),
        TEHOSMOTRNOMER Varchar(255),
        TEHOSMOTRDATA_D Integer,
        TEHOSMOTRDATA_M Integer,
        TEHOSMOTRDATA_Y Integer,
        TEHOSMOTRSLEDDATA_D Integer,
        TEHOSMOTRSLEDDATA_M Integer,
        TEHOSMOTRSLEDDATA_Y Integer,
        OSAGODATA_D Integer,
        OSAGODATA_M Integer,
        OSAGODATA_Y Integer,
        OSAGONOMER Varchar(255),
        OSAGOCOMPANY Varchar(255),
        OSAGODATAEND_D Integer,
        OSAGODATAEND_M Integer,
        OSAGODATAEND_Y Integer,
        OSAGOPREVDATA_D Integer,
        OSAGOPREVDATA_M Integer,
        OSAGOPREVDATA_Y Integer,
        OSAGOPREVNOMER Varchar(255),
        OSAGOPREVCOMPANY Varchar(255),
        OSAGOPREVDATAEND_D Integer,
        OSAGOPREVDATAEND_M Integer,
        OSAGOPREVDATAEND_Y Integer,
        KASKODATA_D Integer,
        KASKODATA_M Integer,
        KASKODATA_Y Integer,
        KASKONOMER Integer,
        KASKOCOMPANY Varchar(255),
        KASKODATAEND_D Integer,
        KASKODATAEND_M Integer,
        KASKODATAEND_Y Integer,
        KASKOPREVDATA_D Integer,
        KASKOPREVDATA_M Integer,
        KASKOPREVDATA_Y Integer,
        KASKOPREVNOMER Varchar(255),
        KASKOPREVCOMPANY Varchar(255),
        KASKOPREVDATAEND_D Integer,
        KASKOPREVDATAEND_M Integer,
        KASKOPREVDATAEND_Y Integer,
        AUTOpokupkaDATA_D Integer,
        AUTOpokupkaDATA_M Integer,
        AUTOpokupkaDATA_Y Integer,
        AUTOdocument Varchar(255),
        AUTOdocumentDATA_D Integer,
        AUTOdocumentDATA_M Integer,
        AUTOdocumentDATA_Y Integer,
        AUTOzeroPROBEG Integer,
        AUTOPTS Varchar(255),
        AUTOSTS Varchar(255),
        AUTOVLADELEC Varchar(255),
        AUTOVIN Varchar(255),
        AUTOENGINE Varchar(255),
        VODITEL1_IDD Varchar(255),
        VODITEL2_IDD Varchar(255),
        PRIMECHANIE Varchar(10000));"); //SQL-запрос на создание БД


         */


        try {

            final ArrayList<Sdelki> list = new ArrayList<Sdelki>();

            SQLiteDatabase db = DB.getReadableDatabase();

            String[] dbcolumns = new String[]{"MARKA", "GOSNOMER", "COLOR", "ID"};

            Cursor cursor = db.query("AUTO", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов


            while (cursor.moveToNext()) {
                list.add(
                        new Sdelki(
                                cursor.getString(0),
                                "Госномер: " + cursor.getString(1),
                                "Цвет: " + cursor.getString(2),
                                cursor.getString(3)));
            }


            final ListView ListViewAutos = (ListView) rootView.findViewById(R.id.ListViewAutos);

            ListViewAutos.setAdapter(new SdelkiListAdapter(getActivity(), list));


            ListViewAutos.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();

                            MainActivity rootActivity = (MainActivity) getActivity();
                            rootActivity.editauto((list.get(position)).getidnumber());

                            //AutoID autoid = new AutoID();
                            //autoid.setAutoID((list.get(position)).getidnumber());
                        }
                    });

            db.close();

        } catch (CursorIndexOutOfBoundsException CursorException) {
            appActivity.Toasts(Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG),"show");
        }
        //endregion



        return rootView;
    }




}
