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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ClassCastException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import ru.mastercond.MainActivity;
import ru.mastercond.MenuOpened;
import ru.mastercond.R;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.AutoID;
import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;
import ru.mastercond.SetDynamicHeightListView;



public class fragment_add_auto extends Fragment {

    private SQLiteConnect DB;
    private SdelkaID IDD;
    private SetDynamicHeightListView SetDListView;

    AlertDialog.Builder selectsotrudnikbuilder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


   @Override
   public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

   final View rootView = inflater.inflate(R.layout.fragment_add_auto, container, false);
   final MainActivity appActivity=(MainActivity)getActivity();
   appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

   DB = new SQLiteConnect(getActivity());

       final Button AddAutoButton=(Button)rootView.findViewById(R.id.buttonaddauto);

       final LinearLayout LinearLayoutTehOsmotr=(LinearLayout)rootView.findViewById(R.id.sectiontehosmotr);
       final LinearLayout LinearLayoutStrahovka=(LinearLayout)rootView.findViewById(R.id.sectionstrahovanie);
       final LinearLayout LinearLayoutAutoDocuments=(LinearLayout)rootView.findViewById(R.id.sectionautodocuments);

       final Spinner datauchetaSpinnerDay=(Spinner)rootView.findViewById(R.id.dataucheta_spinnerday);
       final Spinner datauchetaSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataucheta_spinnermonth);
       final Spinner datauchetaSpinnerYear=(Spinner)rootView.findViewById(R.id.dataucheta_spinneryear);

       final Spinner datadokumentauchetaSpinnerDay=(Spinner)rootView.findViewById(R.id.datadokumentaucheta_spinnerday);
       final Spinner datadokumentauchetaSpinnerMonth=(Spinner)rootView.findViewById(R.id.datadokumentaucheta_spinnermonth);
       final Spinner datadokumentauchetaSpinnerYear=(Spinner)rootView.findViewById(R.id.datadokumentaucheta_spinneryear);

       final Spinner dataPTSSpinnerDay=(Spinner)rootView.findViewById(R.id.dataPTS_spinnerday);
       final Spinner dataPTSSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataPTS_spinnermonth);
       final Spinner dataPTSSpinnerYear=(Spinner)rootView.findViewById(R.id.dataPTS_spinneryear);

       final Spinner dataSTSSpinnerDay=(Spinner)rootView.findViewById(R.id.dataSTS_spinnerday);
       final Spinner dataSTSSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataSTS_spinnermonth);
       final Spinner dataSTSSpinnerYear=(Spinner)rootView.findViewById(R.id.dataSTS_spinneryear);

       final Spinner datatehosmotraSpinnerDay=(Spinner)rootView.findViewById(R.id.datatehosmotra_spinnerday);
       final Spinner datatehosmotraSpinnerMonth=(Spinner)rootView.findViewById(R.id.datatehosmotra_spinnermonth);
       final Spinner datatehosmotraSpinnerYear=(Spinner)rootView.findViewById(R.id.datatehosmotra_spinneryear);

       final Spinner datasledtehosmotraSpinnerDay=(Spinner)rootView.findViewById(R.id.datasledtehosmotra_spinnerday);
       final Spinner datasledtehosmotraSpinnerMonth=(Spinner)rootView.findViewById(R.id.datasledtehosmotra_spinnermonth);
       final Spinner datasledtehosmotraSpinnerYear=(Spinner)rootView.findViewById(R.id.datasledtehosmotra_spinneryear);

       final Spinner dataosagoSpinnerDay=(Spinner)rootView.findViewById(R.id.dataosago_spinnerday);
       final Spinner dataosagoSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataosago_spinnermonth);
       final Spinner dataosagoSpinnerYear=(Spinner)rootView.findViewById(R.id.dataosago_spinneryear);

       final Spinner dataendosagoSpinnerDay=(Spinner)rootView.findViewById(R.id.dataendosago_spinnerday);
       final Spinner dataendosagoSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataendosago_spinnermonth);
       final Spinner dataendosagoSpinnerYear=(Spinner)rootView.findViewById(R.id.dataendosago_spinneryear);

       final Spinner dataPREVosagoSpinnerDay=(Spinner)rootView.findViewById(R.id.dataPREVosago_spinnerday);
       final Spinner dataPREVosagoSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataPREVosago_spinnermonth);
       final Spinner dataPREVosagoSpinnerYear=(Spinner)rootView.findViewById(R.id.dataPREVosago_spinneryear);

       final Spinner dataendPREVosagoSpinnerDay=(Spinner)rootView.findViewById(R.id.dataendPREVosago_spinnerday);
       final Spinner dataendPREVosagoSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataendPREVosago_spinnermonth);
       final Spinner dataendPREVosagoSpinnerYear=(Spinner)rootView.findViewById(R.id.dataendPREVosago_spinneryear);

       final Spinner datakaskoSpinnerDay=(Spinner)rootView.findViewById(R.id.datakasko_spinnerday);
       final Spinner datakaskoSpinnerMonth=(Spinner)rootView.findViewById(R.id.datakasko_spinnermonth);
       final Spinner datakaskoSpinnerYear=(Spinner)rootView.findViewById(R.id.datakasko_spinneryear);

       final Spinner dataendkaskoSpinnerDay=(Spinner)rootView.findViewById(R.id.dataendkasko_spinnerday);
       final Spinner dataendkaskoSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataendkasko_spinnermonth);
       final Spinner dataendkaskoSpinnerYear=(Spinner)rootView.findViewById(R.id.dataendkasko_spinneryear);

       final Spinner dataPREVkaskoSpinnerDay=(Spinner)rootView.findViewById(R.id.dataPREVkasko_spinnerday);
       final Spinner dataPREVkaskoSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataPREVkasko_spinnermonth);
       final Spinner dataPREVkaskoSpinnerYear=(Spinner)rootView.findViewById(R.id.dataPREVkasko_spinneryear);

       final Spinner dataendPREVkaskoSpinnerDay=(Spinner)rootView.findViewById(R.id.dataendPREVkasko_spinnerday);
       final Spinner dataendPREVkaskoSpinnerMonth=(Spinner)rootView.findViewById(R.id.dataendPREVkasko_spinnermonth);
       final Spinner dataendPREVkaskoSpinnerYear=(Spinner)rootView.findViewById(R.id.dataendPREVkasko_spinneryear);

       final Calendar today = Calendar.getInstance();
       datauchetaSpinnerDay.setSelection(today.get(Calendar.DAY_OF_MONTH)-1);
       datauchetaSpinnerMonth.setSelection(today.get(Calendar.MONTH));
       datauchetaSpinnerYear.setSelection(today.get(Calendar.YEAR)-1980); // выбрать 2020 год (нулевой элемент 1980 год)

       datadokumentauchetaSpinnerDay.setSelection(today.get(Calendar.DAY_OF_MONTH)-1);
       datadokumentauchetaSpinnerMonth.setSelection(today.get(Calendar.MONTH));
       datadokumentauchetaSpinnerYear.setSelection(today.get(Calendar.YEAR)-1980); // выбрать 2020 год (нулевой элемент 1980 год)


       final EditText AutoMarka=(EditText)rootView.findViewById(R.id.auto_marka);
       final EditText AutoGosNomer=(EditText)rootView.findViewById(R.id.auto_gosnomer);
       final EditText AutoColor=(EditText)rootView.findViewById(R.id.auto_color);
       final EditText AutoDokumentPostUchet=(EditText)rootView.findViewById(R.id.auto_dokumentpostanovkinauchet);
       final EditText AutoProbegZero=(EditText)rootView.findViewById(R.id.auto_probegzero);
       final EditText AutoPrimechanie=(EditText)rootView.findViewById(R.id.auto_primechanie);
       final EditText AutoPTSnomer=(EditText)rootView.findViewById(R.id.auto_PTSnomer);
       final EditText AutoSTSnomer=(EditText)rootView.findViewById(R.id.auto_STSnomer);
       final EditText AutoVIN=(EditText)rootView.findViewById(R.id.auto_vin);
       final EditText AutoDvigNomer=(EditText)rootView.findViewById(R.id.auto_dvignomer);
       final EditText AutoVladelec=(EditText)rootView.findViewById(R.id.auto_vladelec);
       final EditText AutoDiagKartNomer=(EditText)rootView.findViewById(R.id.auto_diagkartanomer);
       final EditText AutoOsagoNomer=(EditText)rootView.findViewById(R.id.auto_osagonomer);
       final EditText AutoOsagoStrahCompany=(EditText)rootView.findViewById(R.id.auto_osagostrahcompany);
       final EditText AutoOsagoPrevNomer=(EditText)rootView.findViewById(R.id.auto_osagoprevnomer);
       final EditText AutoOsagoPrevStrahCompany=(EditText)rootView.findViewById(R.id.auto_osagoprevstrahcompany);
       final EditText AutoKaskoNomer=(EditText)rootView.findViewById(R.id.auto_kaskonomer);
       final EditText AutoKaskoStrahCompany=(EditText)rootView.findViewById(R.id.auto_kaskostrahcompany);
       final EditText AutoKaskoPrevNomer=(EditText)rootView.findViewById(R.id.auto_kaskoprevnomer);
       final EditText AutoKaskoPrevStrahCompany=(EditText)rootView.findViewById(R.id.auto_kaskoprevstrahcompany);



       final TextView datauchetaTextViewDay=(TextView)rootView.findViewById(R.id.dataucheta_spinnerday_textview);
       final TextView datauchetaTextViewMonth=(TextView)rootView.findViewById(R.id.dataucheta_spinnermonth_textview);
       final TextView datauchetaTextViewYear=(TextView)rootView.findViewById(R.id.dataucheta_spinneryear_textview);

       final TextView datadokumentauchetaTextViewDay=(TextView)rootView.findViewById(R.id.datadokumentaucheta_spinnerday_textview);
       final TextView datadokumentauchetaTextViewMonth=(TextView)rootView.findViewById(R.id.datadokumentaucheta_spinnermonth_textview);
       final TextView datadokumentauchetaTextViewYear=(TextView)rootView.findViewById(R.id.datadokumentaucheta_spinneryear_textview);

       final TextView dataPTSTextViewDay=(TextView)rootView.findViewById(R.id.dataPTS_spinnerday_textview);
       final TextView dataPTSTextViewMonth=(TextView)rootView.findViewById(R.id.dataPTS_spinnermonth_textview);
       final TextView dataPTSTextViewYear=(TextView)rootView.findViewById(R.id.dataPTS_spinneryear_textview);

       final TextView dataSTSTextViewDay=(TextView)rootView.findViewById(R.id.dataSTS_spinnerday_textview);
       final TextView dataSTSTextViewMonth=(TextView)rootView.findViewById(R.id.dataSTS_spinnermonth_textview);
       final TextView dataSTSTextViewYear=(TextView)rootView.findViewById(R.id.dataSTS_spinneryear_textview);

       final TextView datatehosmotraTextViewDay=(TextView)rootView.findViewById(R.id.datatehosmotra_spinnerday_textview);
       final TextView datatehosmotraTextViewMonth=(TextView)rootView.findViewById(R.id.datatehosmotra_spinnermonth_textview);
       final TextView datatehosmotraTextViewYear=(TextView)rootView.findViewById(R.id.datatehosmotra_spinneryear_textview);

       final TextView datasledtehosmotraTextViewDay=(TextView)rootView.findViewById(R.id.datasledtehosmotra_spinnerday_textview);
       final TextView datasledtehosmotraTextViewMonth=(TextView)rootView.findViewById(R.id.datasledtehosmotra_spinnermonth_textview);
       final TextView datasledtehosmotraTextViewYear=(TextView)rootView.findViewById(R.id.datasledtehosmotra_spinneryear_textview);

       final TextView dataosagoTextViewDay=(TextView)rootView.findViewById(R.id.dataosago_spinnerday_textview);
       final TextView dataosagoTextViewMonth=(TextView)rootView.findViewById(R.id.dataosago_spinnermonth_textview);
       final TextView dataosagoTextViewYear=(TextView)rootView.findViewById(R.id.dataosago_spinneryear_textview);

       final TextView dataendosagoTextViewDay=(TextView)rootView.findViewById(R.id.dataendosago_spinnerday_textview);
       final TextView dataendosagoTextViewMonth=(TextView)rootView.findViewById(R.id.dataendosago_spinnermonth_textview);
       final TextView dataendosagoTextViewYear=(TextView)rootView.findViewById(R.id.dataendosago_spinneryear_textview);

       final TextView dataPREVosagoTextViewDay=(TextView)rootView.findViewById(R.id.dataPREVosago_spinnerday_textview);
       final TextView dataPREVosagoTextViewMonth=(TextView)rootView.findViewById(R.id.dataPREVosago_spinnermonth_textview);
       final TextView dataPREVosagoTextViewYear=(TextView)rootView.findViewById(R.id.dataPREVosago_spinneryear_textview);

       final TextView dataendPREVosagoTextViewDay=(TextView)rootView.findViewById(R.id.dataendPREVosago_spinnerday_textview);
       final TextView dataendPREVosagoTextViewMonth=(TextView)rootView.findViewById(R.id.dataendPREVosago_spinnermonth_textview);
       final TextView dataendPREVosagoTextViewYear=(TextView)rootView.findViewById(R.id.dataendPREVosago_spinneryear_textview);

       final TextView datakaskoTextViewDay=(TextView)rootView.findViewById(R.id.datakasko_spinnerday_textview);
       final TextView datakaskoTextViewMonth=(TextView)rootView.findViewById(R.id.datakasko_spinnermonth_textview);
       final TextView datakaskoTextViewYear=(TextView)rootView.findViewById(R.id.datakasko_spinneryear_textview);

       final TextView dataendkaskoTextViewDay=(TextView)rootView.findViewById(R.id.dataendkasko_spinnerday_textview);
       final TextView dataendkaskoTextViewMonth=(TextView)rootView.findViewById(R.id.dataendkasko_spinnermonth_textview);
       final TextView dataendkaskoTextViewYear=(TextView)rootView.findViewById(R.id.dataendkasko_spinneryear_textview);

       final TextView dataPREVkaskoTextViewDay=(TextView)rootView.findViewById(R.id.dataPREVkasko_spinnerday_textview);
       final TextView dataPREVkaskoTextViewMonth=(TextView)rootView.findViewById(R.id.dataPREVkasko_spinnermonth_textview);
       final TextView dataPREVkaskoTextViewYear=(TextView)rootView.findViewById(R.id.dataPREVkasko_spinneryear_textview);

       final TextView dataendPREVkaskoTextViewDay=(TextView)rootView.findViewById(R.id.dataendPREVkasko_spinnerday_textview);
       final TextView dataendPREVkaskoTextViewMonth=(TextView)rootView.findViewById(R.id.dataendPREVkasko_spinnermonth_textview);
       final TextView dataendPREVkaskoTextViewYear=(TextView)rootView.findViewById(R.id.dataendPREVkasko_spinneryear_textview);



       LinearLayoutTehOsmotr.setVisibility(View.GONE);
       LinearLayoutStrahovka.setVisibility(View.GONE);
       LinearLayoutAutoDocuments.setVisibility(View.GONE);




       //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

   final LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.LLContainer);
   fragRoot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            MainActivity rootActivity = (MainActivity)getActivity();
              rootActivity.opencloseMenu(true);
             }
           }
   );
    //endregion



       //region ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ

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

    //endregion


       //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В РАЗДЕЛЕ ТЕХ.ОСМОТР

       final Button ButtonShowTehOsmotr = (Button) rootView.findViewById(R.id.buttonshowtehosmotr);
       ButtonShowTehOsmotr.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       if (LinearLayoutTehOsmotr.getVisibility() == View.VISIBLE) {

                           LinearLayoutTehOsmotr.setVisibility(View.GONE);
                           ButtonShowTehOsmotr.setText(">");

                       } else {
                           LinearLayoutTehOsmotr.setVisibility(View.VISIBLE);
                           ButtonShowTehOsmotr.setText("^");
                       }
                   }
               });

       //endregion


       //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В РАЗДЕЛЕ СТРАХОВАНИЕ

       final Button ButtonShowStrahovka = (Button) rootView.findViewById(R.id.buttonshowstrahovka);
       ButtonShowStrahovka.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       if (LinearLayoutStrahovka.getVisibility() == View.VISIBLE) {

                           LinearLayoutStrahovka.setVisibility(View.GONE);
                           ButtonShowStrahovka.setText(">");

                       } else {
                           LinearLayoutStrahovka.setVisibility(View.VISIBLE);
                           ButtonShowStrahovka.setText("^");
                       }
                   }
               });

       //endregion


       //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В РАЗДЕЛЕ ДОКУМЕНТЫ АВТО

       final Button ButtonShowAutoDocs = (Button) rootView.findViewById(R.id.buttonshowregdocs);
       ButtonShowAutoDocs.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       if (LinearLayoutAutoDocuments.getVisibility() == View.VISIBLE) {

                           LinearLayoutAutoDocuments.setVisibility(View.GONE);
                           ButtonShowAutoDocs.setText(">");

                       } else {
                           LinearLayoutAutoDocuments.setVisibility(View.VISIBLE);
                           ButtonShowAutoDocs.setText("^");
                       }
                   }
               });

       //endregion




       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataucheta

       datauchetaSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datauchetaTextViewDay.setText(datauchetaSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datauchetaTextViewDay.setText(datauchetaSpinnerDay.getSelectedItem().toString());
           }
       });

       datauchetaSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datauchetaTextViewMonth.setText(String.valueOf(datauchetaSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datauchetaTextViewMonth.setText(String.valueOf(datauchetaSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       datauchetaSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datauchetaTextViewYear.setText(datauchetaSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datauchetaTextViewYear.setText(datauchetaSpinnerYear.getSelectedItem().toString());
           }
       });
//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - datadokumentaucheta


       datadokumentauchetaSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datadokumentauchetaTextViewDay.setText(datadokumentauchetaSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datadokumentauchetaTextViewDay.setText(datadokumentauchetaSpinnerDay.getSelectedItem().toString());
           }
       });

       datadokumentauchetaSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datadokumentauchetaTextViewMonth.setText(String.valueOf(datadokumentauchetaSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datadokumentauchetaTextViewMonth.setText(String.valueOf(datadokumentauchetaSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       datadokumentauchetaSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datadokumentauchetaTextViewYear.setText(datadokumentauchetaSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datadokumentauchetaTextViewYear.setText(datadokumentauchetaSpinnerYear.getSelectedItem().toString());
           }
       });
//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataPTS


       dataPTSSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPTSTextViewDay.setText(dataPTSSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPTSTextViewDay.setText(dataPTSSpinnerDay.getSelectedItem().toString());
           }
       });

       dataPTSSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPTSTextViewMonth.setText(String.valueOf(dataPTSSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPTSTextViewMonth.setText(String.valueOf(dataPTSSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataPTSSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPTSTextViewYear.setText(dataPTSSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPTSTextViewYear.setText(dataPTSSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataSTS


       dataSTSSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataSTSTextViewDay.setText(dataSTSSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataSTSTextViewDay.setText(dataSTSSpinnerDay.getSelectedItem().toString());
           }
       });

       dataSTSSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataSTSTextViewMonth.setText(String.valueOf(dataSTSSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataSTSTextViewMonth.setText(String.valueOf(dataSTSSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataSTSSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataSTSTextViewYear.setText(dataSTSSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataSTSTextViewYear.setText(dataSTSSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - datatehosmotra


       datatehosmotraSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datatehosmotraTextViewDay.setText(datatehosmotraSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datatehosmotraTextViewDay.setText(datatehosmotraSpinnerDay.getSelectedItem().toString());
           }
       });

       datatehosmotraSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datatehosmotraTextViewMonth.setText(String.valueOf(datatehosmotraSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datatehosmotraTextViewMonth.setText(String.valueOf(datatehosmotraSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       datatehosmotraSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datatehosmotraTextViewYear.setText(datatehosmotraSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datatehosmotraTextViewYear.setText(datatehosmotraSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - datasledtehosmotra


       datasledtehosmotraSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datasledtehosmotraTextViewDay.setText(datasledtehosmotraSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datasledtehosmotraTextViewDay.setText(datasledtehosmotraSpinnerDay.getSelectedItem().toString());
           }
       });

       datasledtehosmotraSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datasledtehosmotraTextViewMonth.setText(String.valueOf(datasledtehosmotraSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datasledtehosmotraTextViewMonth.setText(String.valueOf(datasledtehosmotraSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       datasledtehosmotraSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datasledtehosmotraTextViewYear.setText(datasledtehosmotraSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datasledtehosmotraTextViewYear.setText(datasledtehosmotraSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataosago


       dataosagoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataosagoTextViewDay.setText(dataosagoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataosagoTextViewDay.setText(dataosagoSpinnerDay.getSelectedItem().toString());
           }
       });

       dataosagoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataosagoTextViewMonth.setText(String.valueOf(dataosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataosagoTextViewMonth.setText(String.valueOf(dataosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataosagoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataosagoTextViewYear.setText(dataosagoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataosagoTextViewYear.setText(dataosagoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataendosago


       dataendosagoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendosagoTextViewDay.setText(dataendosagoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendosagoTextViewDay.setText(dataendosagoSpinnerDay.getSelectedItem().toString());
           }
       });

       dataendosagoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendosagoTextViewMonth.setText(String.valueOf(dataendosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendosagoTextViewMonth.setText(String.valueOf(dataendosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataendosagoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendosagoTextViewYear.setText(dataendosagoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendosagoTextViewYear.setText(dataendosagoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataPREVosago


       dataPREVosagoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPREVosagoTextViewDay.setText(dataPREVosagoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPREVosagoTextViewDay.setText(dataPREVosagoSpinnerDay.getSelectedItem().toString());
           }
       });

       dataPREVosagoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPREVosagoTextViewMonth.setText(String.valueOf(dataPREVosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPREVosagoTextViewMonth.setText(String.valueOf(dataPREVosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataPREVosagoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPREVosagoTextViewYear.setText(dataPREVosagoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPREVosagoTextViewYear.setText(dataPREVosagoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataendPREVosago


       dataendPREVosagoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendPREVosagoTextViewDay.setText(dataendPREVosagoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendPREVosagoTextViewDay.setText(dataendPREVosagoSpinnerDay.getSelectedItem().toString());
           }
       });

       dataendPREVosagoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendPREVosagoTextViewMonth.setText(String.valueOf(dataendPREVosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendPREVosagoTextViewMonth.setText(String.valueOf(dataendPREVosagoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataendPREVosagoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendPREVosagoTextViewYear.setText(dataendPREVosagoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendPREVosagoTextViewYear.setText(dataendPREVosagoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - datakasko


       datakaskoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datakaskoTextViewDay.setText(datakaskoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datakaskoTextViewDay.setText(datakaskoSpinnerDay.getSelectedItem().toString());
           }
       });

       datakaskoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datakaskoTextViewMonth.setText(String.valueOf(datakaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datakaskoTextViewMonth.setText(String.valueOf(datakaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       datakaskoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               datakaskoTextViewYear.setText(datakaskoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               datakaskoTextViewYear.setText(datakaskoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataendkasko


       dataendkaskoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendkaskoTextViewDay.setText(dataendkaskoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendkaskoTextViewDay.setText(dataendkaskoSpinnerDay.getSelectedItem().toString());
           }
       });

       dataendkaskoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendkaskoTextViewMonth.setText(String.valueOf(dataendkaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendkaskoTextViewMonth.setText(String.valueOf(dataendkaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataendkaskoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendkaskoTextViewYear.setText(dataendkaskoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendkaskoTextViewYear.setText(dataendkaskoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataPREVkasko


       dataPREVkaskoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPREVkaskoTextViewDay.setText(dataPREVkaskoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPREVkaskoTextViewDay.setText(dataPREVkaskoSpinnerDay.getSelectedItem().toString());
           }
       });

       dataPREVkaskoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPREVkaskoTextViewMonth.setText(String.valueOf(dataPREVkaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPREVkaskoTextViewMonth.setText(String.valueOf(dataPREVkaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataPREVkaskoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataPREVkaskoTextViewYear.setText(dataPREVkaskoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataPREVkaskoTextViewYear.setText(dataPREVkaskoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion

       //region СЕКЦИЯ ПОЛУЧЕНИЕ ДАТ - dataendPREVkasko


       dataendPREVkaskoSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendPREVkaskoTextViewDay.setText(dataendPREVkaskoSpinnerDay.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendPREVkaskoTextViewDay.setText(dataendPREVkaskoSpinnerDay.getSelectedItem().toString());
           }
       });

       dataendPREVkaskoSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendPREVkaskoTextViewMonth.setText(String.valueOf(dataendPREVkaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendPREVkaskoTextViewMonth.setText(String.valueOf(dataendPREVkaskoSpinnerMonth.getSelectedItemPosition()+1));
           }
       });


       dataendPREVkaskoSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
           {
               dataendPREVkaskoTextViewYear.setText(dataendPREVkaskoSpinnerYear.getSelectedItem().toString());
           }
           public void onNothingSelected(AdapterView<?> parent)
           {
               dataendPREVkaskoTextViewYear.setText(dataendPREVkaskoSpinnerYear.getSelectedItem().toString());
           }
       });

//endregion




       AddAutoButton.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {

               try {

                   String Marka=AutoMarka.getText().toString();
                   String Gosnomer=AutoGosNomer.getText().toString();
                   String Color=AutoColor.getText().toString();
                   String Tehosmotrnomer=AutoDiagKartNomer.getText().toString();

                   Integer Tehosmotrdata_D=Integer.parseInt(datatehosmotraTextViewDay.getText().toString());
                   Integer Tehosmotrdata_M=Integer.parseInt(datatehosmotraTextViewMonth.getText().toString());
                   Integer Tehosmotrdata_Y=Integer.parseInt(datatehosmotraTextViewYear.getText().toString());

                   Integer Tehosmotrsleddata_D=Integer.parseInt(datasledtehosmotraTextViewDay.getText().toString());
                   Integer Tehosmotrsleddata_M=Integer.parseInt(datasledtehosmotraTextViewMonth.getText().toString());
                   Integer Tehosmotrsleddata_Y=Integer.parseInt(datasledtehosmotraTextViewYear.getText().toString());

                   Integer Osagodata_D=Integer.parseInt(dataosagoTextViewDay.getText().toString());
                   Integer Osagodata_M=Integer.parseInt(dataosagoTextViewMonth.getText().toString());
                   Integer Osagodata_Y=Integer.parseInt(dataosagoTextViewYear.getText().toString());

                   String Osagonomer=AutoOsagoNomer.getText().toString();
                   String Osagocompany=AutoOsagoStrahCompany.getText().toString();

                   Integer Osagodataend_D=Integer.parseInt(dataendosagoTextViewDay.getText().toString());
                   Integer Osagodataend_M=Integer.parseInt(dataendosagoTextViewMonth.getText().toString());
                   Integer Osagodataend_Y=Integer.parseInt(dataendosagoTextViewYear.getText().toString());

                   Integer Osagoprevdata_D=Integer.parseInt(dataPREVosagoTextViewDay.getText().toString());
                   Integer Osagoprevdata_M=Integer.parseInt(dataPREVosagoTextViewMonth.getText().toString());
                   Integer Osagoprevdata_Y=Integer.parseInt(dataPREVosagoTextViewYear.getText().toString());

                   String Osagoprevnomer=AutoOsagoPrevNomer.getText().toString();
                   String Osagoprevcompany=AutoOsagoPrevStrahCompany.getText().toString();

                   Integer Osagoprevdataend_D=Integer.parseInt(dataendPREVosagoTextViewDay.getText().toString());
                   Integer Osagoprevdataend_M=Integer.parseInt(dataendPREVosagoTextViewMonth.getText().toString());
                   Integer Osagoprevdataend_Y=Integer.parseInt(dataendPREVosagoTextViewYear.getText().toString());

                   Integer Kaskodata_D=Integer.parseInt(datakaskoTextViewDay.getText().toString());
                   Integer Kaskodata_M=Integer.parseInt(datakaskoTextViewMonth.getText().toString());
                   Integer Kaskodata_Y=Integer.parseInt(datakaskoTextViewYear.getText().toString());

                   String Kaskonomer=AutoKaskoNomer.getText().toString();
                   String Kaskocompany=AutoKaskoStrahCompany.getText().toString();

                   Integer Kaskodataend_D=Integer.parseInt(dataendkaskoTextViewDay.getText().toString());
                   Integer Kaskodataend_M=Integer.parseInt(dataendkaskoTextViewMonth.getText().toString());
                   Integer Kaskodataend_Y=Integer.parseInt(dataendkaskoTextViewYear.getText().toString());

                   Integer Kaskoprevdata_D=Integer.parseInt(dataPREVkaskoTextViewDay.getText().toString());
                   Integer Kaskoprevdata_M=Integer.parseInt(dataPREVkaskoTextViewMonth.getText().toString());
                   Integer Kaskoprevdata_Y=Integer.parseInt(dataPREVkaskoTextViewYear.getText().toString());

                   String Kaskoprevnomer=AutoKaskoPrevNomer.getText().toString();
                   String Kaskoprevcompany=AutoKaskoPrevStrahCompany.getText().toString();

                   Integer Kaskoprevdataend_D=Integer.parseInt(dataendPREVkaskoTextViewDay.getText().toString());
                   Integer Kaskoprevdataend_M=Integer.parseInt(dataendPREVkaskoTextViewMonth.getText().toString());
                   Integer Kaskoprevdataend_Y=Integer.parseInt(dataendPREVkaskoTextViewYear.getText().toString());

                   Integer Autopokupkadata_D=Integer.parseInt(datauchetaTextViewDay.getText().toString());
                   Integer Autopokupkadata_M=Integer.parseInt(datauchetaTextViewMonth.getText().toString());
                   Integer Autopokupkadata_Y=Integer.parseInt(datauchetaTextViewYear.getText().toString());

                   String Autodocument=AutoDokumentPostUchet.getText().toString();

                   Integer Autodocumentdata_D=Integer.parseInt(datauchetaTextViewDay.getText().toString());
                   Integer Autodocumentdata_M=Integer.parseInt(datauchetaTextViewMonth.getText().toString());
                   Integer Autodocumentdata_Y=Integer.parseInt(datauchetaTextViewYear.getText().toString());

                   if (AutoProbegZero.getText().toString().equals("")) {AutoProbegZero.setText("0");}

                   Integer Autozeroprobeg=Integer.parseInt(AutoProbegZero.getText().toString());
                   String Autopts=AutoPTSnomer.getText().toString();
                   String Autosts=AutoSTSnomer.getText().toString();
                   String Autovladelec=AutoVladelec.getText().toString();
                   String Autovin=AutoVIN.getText().toString();
                   String Autoengine=AutoDvigNomer.getText().toString();
                   String Primechanie=AutoPrimechanie.getText().toString();

                   String Voditel1IDD="0";
                   String Voditel2IDD="0";


                   DB.AddAUTO(Marka,
                           Gosnomer,
                           Color,
                           Tehosmotrnomer,
                           Tehosmotrdata_D,
                           Tehosmotrdata_M,
                           Tehosmotrdata_Y,
                           Tehosmotrsleddata_D,
                           Tehosmotrsleddata_M,
                           Tehosmotrsleddata_Y,
                           Osagodata_D,
                           Osagodata_M,
                           Osagodata_Y,
                           Osagonomer,
                           Osagocompany,
                           Osagodataend_D,
                           Osagodataend_M,
                           Osagodataend_Y,
                           Osagoprevdata_D,
                           Osagoprevdata_M,
                           Osagoprevdata_Y,
                           Osagoprevnomer,
                           Osagoprevcompany,
                           Osagoprevdataend_D,
                           Osagoprevdataend_M,
                           Osagoprevdataend_Y,
                           Kaskodata_D,
                           Kaskodata_M,
                           Kaskodata_Y,
                           Kaskonomer,
                           Kaskocompany,
                           Kaskodataend_D,
                           Kaskodataend_M,
                           Kaskodataend_Y,
                           Kaskoprevdata_D,
                           Kaskoprevdata_M,
                           Kaskoprevdata_Y,
                           Kaskoprevnomer,
                           Kaskoprevcompany,
                           Kaskoprevdataend_D,
                           Kaskoprevdataend_M,
                           Kaskoprevdataend_Y,
                           Autopokupkadata_D,
                           Autopokupkadata_M,
                           Autopokupkadata_Y,
                           Autodocument,
                           Autodocumentdata_D,
                           Autodocumentdata_M,
                           Autodocumentdata_Y,
                           Autozeroprobeg,
                           Autopts,
                           Autosts,
                           Autovladelec,
                           Autovin,
                           Autoengine,
                           Primechanie,
                           Voditel1IDD,
                           Voditel2IDD);

                   appActivity.Toasts(Toast.makeText(getActivity(), "Автомобиль"+Marka+" "+Gosnomer+" успешно добавлен в базу", Toast.LENGTH_LONG),"show");

                   appActivity.autoslist();

               }

               catch (Exception ex) {
                appActivity.Toasts(Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG),"show");
               }
                                            }
          }
       );

    
    return rootView;
}


}
