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
import ru.mastercond.SdelkaID;


import android.app.AlertDialog;
import android.content.DialogInterface;


public class fragment_add_myfirm extends Fragment {
  
        private SQLiteConnect DB;
        
        private EditText KontragentFullName;
        private EditText KontragentSokrName;
        private EditText KontragentINN;
        private EditText KontragentKPP;
        private EditText KontragentOGRN;
        private EditText KontragentBankName;
        private EditText KontragentBankBIK;
        private EditText KontragentBankKS;
        private EditText KontragentBankRS;
        private EditText KontragentRukDolzhn;
        private EditText KontragentVlice;
        private EditText KontragentFIOruk;
        private EditText KontragentUrAddr;
        private EditText KontragentFaktAddr;
        private EditText KontragentPostAddr;
        private EditText KontragentPhone;
        private EditText KontragentMobile;
        private EditText KontragentEmail;
        private EditText KontragentSite;
        private EditText MyLogo;
        private EditText MyPechat;
        private EditText MySlogan;
        
        public fragment_add_myfirm() {}
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }    
        
          
   @Override
   public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
   
   View rootView = inflater.inflate(R.layout.fragment_add_myfirm, container, false);
   final MainActivity appActivity=(MainActivity)getActivity();
   appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

   DB=new SQLiteConnect(getActivity());
    
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
    
    
    

    
        final EditText KontragentFullName=(EditText)rootView.findViewById(R.id.kontragent_myfullname);
        final EditText KontragentSokrName=(EditText)rootView.findViewById(R.id.kontragent_mysokrname);
        final EditText KontragentINN=(EditText)rootView.findViewById(R.id.kontragent_INN);
        final EditText KontragentKPP=(EditText)rootView.findViewById(R.id.kontragent_KPP);
        final EditText KontragentOGRN=(EditText)rootView.findViewById(R.id.kontragent_OGRN);
        final EditText KontragentBankName=(EditText)rootView.findViewById(R.id.kontragent_bankname);
        final EditText KontragentBankBIK=(EditText)rootView.findViewById(R.id.kontragent_bankbik);
        final EditText KontragentBankKS=(EditText)rootView.findViewById(R.id.kontragent_bankks);
        final EditText KontragentBankRS=(EditText)rootView.findViewById(R.id.kontragent_RS);
        final EditText KontragentRukDolzhn=(EditText)rootView.findViewById(R.id.kontragent_rukdolzhnost);
        final EditText KontragentVlice=(EditText)rootView.findViewById(R.id.kontragent_vlice);
        final EditText KontragentFIOruk=(EditText)rootView.findViewById(R.id.kontragent_FIOruksokr);
        final EditText KontragentUrAddr=(EditText)rootView.findViewById(R.id.kontragent_URaddr);
        final EditText KontragentFaktAddr=(EditText)rootView.findViewById(R.id.kontragent_FACTaddr);
        final EditText KontragentPostAddr=(EditText)rootView.findViewById(R.id.kontragent_POSTaddr);
        final EditText KontragentPhone=(EditText)rootView.findViewById(R.id.kontragent_Phone);
        final EditText KontragentMobile=(EditText)rootView.findViewById(R.id.kontragent_MobPhone);
        final EditText KontragentEmail=(EditText)rootView.findViewById(R.id.kontragent_Email);
        final EditText KontragentSite=(EditText)rootView.findViewById(R.id.kontragent_www);
        final EditText MyLogo = (EditText) rootView.findViewById(R.id.my_logo);
        final EditText MyPechat = (EditText) rootView.findViewById(R.id.my_pechat);
        final EditText MySlogan = (EditText) rootView.findViewById(R.id.my_slogan);
        
        
        
        
        Button DBaddFirmKontragent=(Button)rootView.findViewById(R.id.butonaddkontragent);
        DBaddFirmKontragent.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
              
                
                
           
           try {
             
                String FullNameStr=KontragentFullName.getText().toString();
                String SokrNameStr=KontragentSokrName.getText().toString();
                String INNStr=KontragentINN.getText().toString();
                String KPPStr=KontragentKPP.getText().toString();
                String OGRNStr=KontragentOGRN.getText().toString();
                String BankNameStr=KontragentBankName.getText().toString();
                String BankBIKStr=KontragentBankBIK.getText().toString();
                String BankKSStr=KontragentBankKS.getText().toString();
                String BankRSStr=KontragentBankRS.getText().toString();
                String RukDolzhnStr=KontragentRukDolzhn.getText().toString();
                String VliceStr=KontragentVlice.getText().toString();
                String FIOrukStr=KontragentFIOruk.getText().toString();
                String UrAddrStr=KontragentUrAddr.getText().toString();
                String FaktAddrStr=KontragentFaktAddr.getText().toString();
                String PhoneStr=KontragentPhone.getText().toString();
                String MobileStr=KontragentMobile.getText().toString();
                String EmailStr=KontragentEmail.getText().toString();
                String SiteStr=KontragentSite.getText().toString();
                String PostAddrStr=KontragentPostAddr.getText().toString();
                String SMyLogo=MyLogo.getText().toString();
                String SMyPechat=MyPechat.getText().toString();
                String SMySlogan=MySlogan.getText().toString(); 
                
            //DB.AddMyFirm(FullNameStr,SokrNameStr,INNStr,KPPStr,OGRNStr,BankNameStr,BankBIKStr,BankKSStr,BankRSStr,RukDolzhnStr,VliceStr,FIOrukStr,UrAddrStr,FaktAddrStr,PostAddrStr, PhoneStr,MobileStr,EmailStr,SiteStr,OtvetstvennijStr);
             
             DB.AddMyFirmDB(FullNameStr,SokrNameStr,INNStr,KPPStr,OGRNStr,BankNameStr,BankBIKStr,BankKSStr,BankRSStr,RukDolzhnStr,VliceStr,FIOrukStr,UrAddrStr,FaktAddrStr,PostAddrStr, PhoneStr,MobileStr,EmailStr,SiteStr, SMyLogo, SMyPechat, SMySlogan);
             
             
             Toast.makeText(getActivity(),"Данные моей организации успешно записаны в базу",Toast.LENGTH_LONG).show();
             
             MainActivity rootActivity = (MainActivity)getActivity(); 
             rootActivity.closemyfirm();
     
             
             } 
            catch (SQLException mSQLException) {
            Toast.makeText(getActivity(),mSQLException.toString(),Toast.LENGTH_LONG).show();
            }
           } 
                
                
                
                
   
  }) ;
   
    return rootView;
}


}

