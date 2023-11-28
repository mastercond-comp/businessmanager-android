package ru.mastercond;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import ru.mastercond.SiteSName;
import ru.mastercond.ZAMETKI;
import ru.mastercond.ZAMETKIListAdapter;


public class fragment_SAJTI extends Fragment {


    private SQLiteConnect DB;
    private ListView ListViewSAJTI;
    private ArrayList<ZAMETKI> list;

    public fragment_SAJTI() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sajti, container, false);

        //region ПЕРЕМЕННЫЕ

        final MainActivity rootActivity = (MainActivity) getActivity();
        rootActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

        DB = new SQLiteConnect(getActivity());

        //endregion

        //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

        final LinearLayout fragRoot = (LinearLayout) rootView.findViewById(R.id.LLContainer);
        fragRoot.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


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

                ListView LVZ = (ListView) rootView.findViewById(R.id.ListViewSAJTI);
                LinearLayout.LayoutParams paramHeight = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density * 350), Math.round(getActivity().getResources().getDisplayMetrics().density * 400)); //LayoutParams(width, height) в px
                LVZ.setLayoutParams(paramHeight);

            }

            db.close();

        } catch (CursorIndexOutOfBoundsException CursorException) {
            //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
        }

        //endregion

        //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
        try {

            final ArrayList<ZAMETKI> list = new ArrayList<ZAMETKI>();

            SQLiteDatabase db = DB.getReadableDatabase();

            String[] dbcolumns = new String[]{"NAME", "ADDRES", "ID"};

            Cursor cursor =
                    db.query(
                            "SITES", dbcolumns, null, null, null, null, null); // запрос из базы документов

            
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
            

            ListView ListViewSAJTI = (ListView) rootView.findViewById(R.id.ListViewSAJTI);

            ListViewSAJTI.setAdapter(new ZAMETKIListAdapter(getActivity(), list));

            ListViewSAJTI.setOnItemClickListener(
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                            //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
                            SiteSName siteid = new SiteSName();
                            siteid.setSiteSName(
                                    (list.get(position)).getidnumber());

                            rootActivity.editsite((list.get(position)).getidnumber());


                        }
                    });

            db.close();

        } catch (CursorIndexOutOfBoundsException CursorException) {
            Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
        }

        //endregion

        //region КНОПКА ДОБАВИТЬ САЙТ

        Button DBaddSITE = (Button) rootView.findViewById(R.id.buttonaddsite);
        DBaddSITE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity rootActivity = (MainActivity) getActivity();
                rootActivity.addsite();

            }
        });

        //endregion


        return rootView;
    }


}
