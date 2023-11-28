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

import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;
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


public class fragment_TOVARI extends Fragment {


  private SQLiteConnect DB;
  private SQLiteDatabase db;
  private SQLiteDatabase db1;
  private ListView ListViewTovari;
  private ArrayList<Sdelki> list_tovari;

  public fragment_TOVARI() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setRetainInstance(true);
  }



  @Override
  public View onCreateView(
      final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_tovari, container, false);
    final MainActivity appActivity=(MainActivity)getActivity();
    appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

//=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

   LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.LLContainer); 
   fragRoot.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.opencloseMenu(true);
             } 
           }   
   );
//=================КОНЕЦ СЕКЦИИ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН================= 


    DB = new SQLiteConnect(getActivity());



 //region ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ
    
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursorS = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null);
    cursorS.moveToNext(); //без этого exception
    String result=cursorS.getString(2);

    if (result.equals("PHONE")) 
    { 
      
     LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), ViewGroup.LayoutParams.WRAP_CONTENT);
     fragRoot.setLayoutParams(param);
      
    }
    cursorS.close();
    db.close();

     } 
     
     catch (Exception CursorException) {
        Log.d("ru.mastercond",CursorException.toString());
            }

            
    //endregion






   //=================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД=================


    try {

      final ArrayList<Sdelki> list_tovari = new ArrayList<Sdelki>(); //один список для контрагентов, моих организаций и сделок

      SQLiteDatabase db1 = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"NAME", "MODEL", "PRICE", "VALUTA", "ID"};

      Cursor cursor1 = db1.query("TOVARI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

     
      while (cursor1.moveToNext()) {
          list_tovari.add(
            new Sdelki(cursor1.getString(0),
                    "Модель: "+cursor1.getString(1),
                    "Цена: "+cursor1.getString(2)+" ("+cursor1.getString(3)+")" ,
                cursor1.getString(4)));

      }
      
      cursor1.close();

      ListView ListViewTovari = (ListView)rootView.findViewById(R.id.ListViewTovariWWWList);

      ListViewTovari.setAdapter(new SdelkiListAdapter(getActivity(), list_tovari));

      ListViewTovari.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.edittovar((list_tovari.get(position)).getidnumber());
              
              TovarID tovarid=new TovarID();
              tovarid.setTovarID((list_tovari.get(position)).getidnumber());
            }
          });
          
          db1.close();

    } catch (Exception CursorException) {
      appActivity.Toasts(Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG),"show");
    }
    
    
    
    Button DBaddTOVAR=(Button)rootView.findViewById(R.id.TOVARI_buttonaddtovar);
      DBaddTOVAR.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.addtovar();
              
             } 
    }) ;


      Button DBshowTOVARRAZDEL=(Button)rootView.findViewById(R.id.TOVARI_buttonselecttovarrazdel);
      DBshowTOVARRAZDEL.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              MainActivity rootActivity = (MainActivity)getActivity();
              //rootActivity.addkontragent();

          }
      }) ;

      Button uploadTOVARI=(Button)rootView.findViewById(R.id.TOVARI_buttonuploadwww);
      uploadTOVARI.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              MainActivity rootActivity = (MainActivity)getActivity();
              //rootActivity.addkontragent();

          }
      }) ;
    

    return rootView;
  }

}


