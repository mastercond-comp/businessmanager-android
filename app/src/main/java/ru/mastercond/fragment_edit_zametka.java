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
import android.widget.Spinner;
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




public class fragment_edit_zametka extends Fragment {


  private SQLiteConnect DB;
  private SdelkaID sdelkaid;
  private SetDynamicHeightListView SetDListView;
  
  private EditText ZAMETKAname;
  private EditText ZAMETKAopisanie;
  private EditText ZAMETKAdata;
        
  private SdelkaID IDD;
  
  AlertDialog.Builder ad;
  
        
  public fragment_edit_zametka() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setRetainInstance(true);
  }
  

 
 @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
  {

    View rootView = inflater.inflate(R.layout.fragment_edit_zametka, container, false);
    final MainActivity rootActivity = (MainActivity)getActivity();
    rootActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

    DB=new SQLiteConnect(getActivity());
    IDD=new SdelkaID();
    final Button SelectSdelkaButton=(Button) rootView.findViewById(R.id.button_select_sdelka);
    SetDListView=new SetDynamicHeightListView();
    final ArrayList<Sdelki> list = new ArrayList<Sdelki>();
    final SdelkiListAdapter Zadapter = new SdelkiListAdapter(getActivity(), list);
    final TextView ZSdelkaTV=(TextView) rootView.findViewById(R.id.zametka_sdelka);

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
    
    
        final EditText ZAMETKAname=(EditText)rootView.findViewById(R.id.zametka_name);
        final EditText ZAMETKAopisanie=(EditText)rootView.findViewById(R.id.zametka_opisanie);
        final TextView ZAMETKAsdelka=(TextView)rootView.findViewById(R.id.zametka_sdelka);
        final EditText ZAMETKAdata=(EditText)rootView.findViewById(R.id.zametka_data);
        
    

    String ID = rootActivity.getsdelkaid();
   
   
    
    //СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    final Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE ID = " + ID, null); 
    cursor.moveToNext(); //без этого exception 
    
    ZAMETKAname.setText(cursor.getString(1));
    ZAMETKAopisanie.setText(cursor.getString(2));
    ZAMETKAsdelka.setText("Сделка: ");
    ZAMETKAdata.setText(cursor.getString(3));
    
    try {  
      SQLiteDatabase db1 = DB.getReadableDatabase();
      Cursor cursor1 = db1.rawQuery("SELECT SDELKA_NAME FROM SDELKI WHERE ID = " + cursor.getString(4), null); 
      cursor1.moveToNext(); //без этого exception 
      ZAMETKAsdelka.setText("Сделка: "+cursor1.getString(0)); 
      db1.close(); 
      }
      
      catch (CursorIndexOutOfBoundsException CursorException) {}
      catch (SQLException mSQLException){}
    
    db.close();
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
    
    
    SelectSdelkaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            list.clear();
            
            AlertDialog.Builder selectsdelkabuilder = new AlertDialog.Builder(container. getContext());
            selectsdelkabuilder.setCancelable(false);
            View dialogView = inflater.inflate(R.layout.alertdialog_select_sdelka, null); //важно - inflater определен в начале кода фрагмента
            // Привязка xml-разметки окна диалогов
            selectsdelkabuilder.setView(dialogView);
            
            final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
            final Button btn_neutral = (Button) dialogView.findViewById(R.id.dialog_neutral_btn);
            final ListView ListViewSdelki = (ListView)dialogView.findViewById(R.id.ListViewSDELKI);
            
            
      try {


      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"SDELKA_NAME", "K_SOKRNAME", "MY_SOKRNAME", "ID"};

      Cursor cursor = db.query("SDELKI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов


      while (cursor.moveToNext()) { 
      list.add(new Sdelki(cursor.getString(0), "Контрагент: " + cursor.getString(1), "Моя организация: " + cursor.getString(2),cursor.getString(3)));     
      }

 
      ListViewSdelki.setAdapter(Zadapter);
      Zadapter.notifyDataSetChanged();
      final AlertDialog selectsdelkadialog = selectsdelkabuilder.create();
      SetDListView.SetDynamicHeight(ListViewSdelki);
      
      selectsdelkadialog.show();
      
      
      ListViewSdelki.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
             IDD.setSdelkaID(list.get(position).getidnumber());
             ZSdelkaTV.setText("Сделка: "+list.get(position).getkontragentName());
             selectsdelkadialog.dismiss();
           } 
          });
          
          
          btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        selectsdelkadialog.dismiss();
                       
                    }
                });
                
                btn_neutral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ZSdelkaTV.setText("Сделка:");
                        IDD.setSdelkaID("");
                        selectsdelkadialog.dismiss();
                    }
                });
          
          
db.close();
    } 
    
      catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
}});

    
    
    
    
    
    Button DBchangezametka=(Button)rootView.findViewById(R.id.butonchangezametka);
        DBchangezametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            String ID = rootActivity.getsdelkaid();
            
            String Zname=ZAMETKAname.getText().toString();
            String Zopisanie=ZAMETKAopisanie.getText().toString();
            String Zdata=ZAMETKAdata.getText().toString();
                
            
            
            DB.ChangeZAMETKA(ID,Zname,Zopisanie,IDD.getSdelkaID(), Zdata);
             
             
            Toast.makeText(getActivity(),"заметка успешно изменена и записана в базу",Toast.LENGTH_LONG).show();
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.closezametka();
              
            
            } 
            } );
            
            
            Button DBdelzametka=(Button)rootView.findViewById(R.id.butondelzametka);
            
            DBdelzametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
           
            ad = new AlertDialog.Builder(container. getContext());
            ad.setCancelable(true);
            View dialogView = inflater.inflate(R.layout.alertdialog_delete, null); //важно - inflater определен в начале кода фрагмента
            // Привязка xml-разметки окна диалогов
            ad.setView(dialogView);
            final AlertDialog deldialog = ad.create();
             
            final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
            final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
            final TextView Zagolovok=(TextView) dialogView.findViewById(R.id.Zagolovok);
            Zagolovok.setText("Удалить заметку из базы?");
            
            btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        deldialog.cancel();
                       
                    }
                });
                
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                     String ID = rootActivity.getsdelkaid();
                     DB.DelZAMETKA(ID);
              
                     Toast.makeText(getActivity(),"Заметка удалена из базы",Toast.LENGTH_LONG).show();
                     deldialog.cancel();
                     MainActivity rootActivity = (MainActivity)getActivity(); 
                     rootActivity.closezametka();
                       
                    }
                });
                    
             
             deldialog.show();
              
            } 
            } );
    
    return rootView;
  }

   

}