package ru.mastercond;

import android.app.Fragment;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;


public class fragment_EMPTYPAGE extends Fragment {

        private SQLiteConnect DB;
        public fragment_EMPTYPAGE() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


   @Override
   public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

   final View rootView = inflater.inflate(R.layout.fragment_add_sotrudnik, container, false);
   final MainActivity appActivity=(MainActivity)getActivity();
   appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

   DB = new SQLiteConnect(getActivity());


//=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

   final LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.LLContainer);
   fragRoot.setOnClickListener(new OnClickListener() {
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

     LayoutParams param = new LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), ViewGroup.LayoutParams.WRAP_CONTENT);
     fragRoot.setLayoutParams(param);
      
    }
    db.close();
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    
    
    
    
    return rootView;
}


}
