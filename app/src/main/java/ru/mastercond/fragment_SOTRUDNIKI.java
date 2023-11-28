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

import java.util.ArrayList;


public class fragment_SOTRUDNIKI extends Fragment {


    private SQLiteConnect DB;
    private ListView ListViewSdelki1;
    private ArrayList<Sdelki> list;
    private ArrayList<Sdelki> kontragentlist;
    AlertDialog.Builder dialogkontragent;
    private SetDynamicHeightListView SetDListView;

    public fragment_SOTRUDNIKI() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(
            final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sdelki, container, false);
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

                ListView LVK = (ListView) rootView.findViewById(R.id.ListViewSdelki);
                LayoutParams paramHeight = new LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density * 350), Math.round(getActivity().getResources().getDisplayMetrics().density * 400)); //LayoutParams(width, height) в px
                LVK.setLayoutParams(paramHeight);

            }

            db.close();
        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }

        //endregion

        //region КНОПКА ДОБАВИТЬ СДЕЛКУ

        Button DBaddSDELKA = (Button) rootView.findViewById(R.id.butonaddsdelka);
        DBaddSDELKA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.addsdelka();

            }
        });

        //endregion


        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД

        try {

            final ArrayList<Sdelki> list = new ArrayList<Sdelki>();

            SQLiteDatabase db = DB.getReadableDatabase();

            String[] dbcolumns = new String[]{"SDELKA_NAME", "K_SOKRNAME", "MY_SOKRNAME", "ID"};

            Cursor cursor =
                    db.query(
                            "SDELKI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

            // cursor.moveToNext(); //без этого exception

            // String name1 = cursor.getString(cursor.getColumnIndex("FULLNAME")); // ноль в индексе
            // запроса cursor
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

            // list.add(new Sdelki("ООО МЕТЕОР", "ИНН: 7728000000000", "БАНК: Авангард", "12")) ;

            final ListView ListViewSdelki1 = (ListView) rootView.findViewById(R.id.ListViewSdelki);

            ListViewSdelki1.setAdapter(new SdelkiListAdapter(getActivity(), list));


            ListViewSdelki1.setOnItemClickListener(
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

            db.close();

        } catch (CursorIndexOutOfBoundsException CursorException) {
            Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
        }
        //endregion

        //region КНОПКА ВЫБРАТЬ КОНТРАГЕНТА ДЛЯ ОТОБРАЖЕНИЯ СДЕЛОК ПО ВЫБРАННОМУ КОНТРАГЕНТУ

        Button SelectKontragent = (Button) rootView.findViewById(R.id.butonselectsdelkakontragent);
        SelectKontragent.setOnClickListener(
                new OnClickListener() {
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
                                                Cursor cursor1 = db1.rawQuery("SELECT * FROM KONTRAGENTI WHERE ID = " + selectedID1, null);
                                                cursor1.moveToNext(); // без этого exception


                                                String SK = cursor1.getString(2);

                                                MainActivity rootActivity = (MainActivity) getActivity();
                                                rootActivity.SdelkiPoImeniKontragenta(SK);

                                                db1.close();


                                            } catch (CursorIndexOutOfBoundsException CursorException) {
                                                Toast.makeText(
                                                        getActivity(),
                                                        CursorException.toString(),
                                                        Toast.LENGTH_LONG)
                                                        .show();
                                            } catch (Exception exx) {
                                                Toast.makeText(
                                                        getActivity(),
                                                        exx.toString(),
                                                        Toast.LENGTH_LONG)
                                                        .show();
                                            }

                                            // =================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================

                                            selectkontragentdialog.dismiss();
                                        }
                                    });


                            btn_negative.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    selectkontragentdialog.dismiss();

                                }
                            });

                            btn_neutral.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    selectkontragentdialog.dismiss();
                                }
                            });

                            db.close();


                        } catch (CursorIndexOutOfBoundsException CursorException) {
                            Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
                        }


                    }
                });
//endregion

        return rootView;
    }



}
