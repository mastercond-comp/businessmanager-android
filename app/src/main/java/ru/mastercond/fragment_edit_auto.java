package ru.mastercond;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.StaleDataException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import ru.mastercond.TextStr;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;


public class fragment_edit_auto extends Fragment {

    private SQLiteConnect DB;
    private SdelkaID IDD;
    private SetDynamicHeightListView SetDListView;

    private TextStr Summ1;
    private TextStr Summ2;
    private TextStr Summ3;
    private TextStr Summ4;
    private TextStr Summ5;
    private TextStr Summ6;
    private TextStr Summ7;
    private TextStr Summ8;
    private TextStr Summ9;

    AlertDialog.Builder addautorashodydialog;
    AlertDialog.Builder editautorashodydialog;
    AlertDialog.Builder delautodialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


   @Override
   public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

   final View rootView = inflater.inflate(R.layout.fragment_edit_auto, container, false);
   final MainActivity appActivity=(MainActivity)getActivity();
   appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

       //region ПЕРЕМЕННЫЕ

    final String ID=appActivity.getautoid();

    final ArrayList idArrayAutoRashody = new ArrayList();

    final ArrayList<AUTO_RASHODY> autorashodylist = new ArrayList<AUTO_RASHODY>();
    final AUTO_RASHODY_ListAdapter ARadapter = new AUTO_RASHODY_ListAdapter(getActivity(), autorashodylist);


       DB = new SQLiteConnect(getActivity());

       final Button SaveAutoButton=(Button)rootView.findViewById(R.id.buttonsaveauto);
       final Button DelAutoButton=(Button)rootView.findViewById(R.id.buttondelauto);
       final Button AddAutoRashodyButton=(Button)rootView.findViewById(R.id.buttonaddrashody);

       final LinearLayout LinearLayoutTehOsmotr=(LinearLayout)rootView.findViewById(R.id.sectiontehosmotr);
       final LinearLayout LinearLayoutStrahovka=(LinearLayout)rootView.findViewById(R.id.sectionstrahovanie);
       final LinearLayout LinearLayoutAutoDocuments=(LinearLayout)rootView.findViewById(R.id.sectionautodocuments);
       final LinearLayout LinearLayoutAutoRashody=(LinearLayout)rootView.findViewById(R.id.sectionrashody);

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

       final ListView ListViewAutoRashody=(ListView)rootView.findViewById(R.id.ListViewAUTORASHODY);

       //endregion

       //region СКРЫТЬ ВСЕ РАСКРЫВАЮЩИЕСЯ LINEARLAYOUT

       LinearLayoutTehOsmotr.setVisibility(View.GONE);
       LinearLayoutStrahovka.setVisibility(View.GONE);
       LinearLayoutAutoDocuments.setVisibility(View.GONE);
       LinearLayoutAutoRashody.setVisibility(View.GONE);

       //endregion

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
               new OnClickListener() {
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
               new OnClickListener() {
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
               new OnClickListener() {
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

       //region СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В РАЗДЕЛЕ АВТОРАСХОДЫ

       final Button ButtonShowAutoRashody = (Button) rootView.findViewById(R.id.buttonshowrashody);
       ButtonShowAutoRashody.setOnClickListener(
               new OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       if (LinearLayoutAutoRashody.getVisibility() == View.VISIBLE) {

                           LinearLayoutAutoRashody.setVisibility(View.GONE);
                           ButtonShowAutoRashody.setText(">");

                       } else {
                           LinearLayoutAutoRashody.setVisibility(View.VISIBLE);
                           ButtonShowAutoRashody.setText("^");
                       }
                   }
               });

       //endregion


       //region ПОЛУЧЕНИЕ ИНФОРМАЦИИ ИЗ БАЗЫ ДАННЫХ

       try {

           SQLiteDatabase db=DB.getReadableDatabase();
           Cursor cursor = db.rawQuery("SELECT * FROM AUTO WHERE ID = " + ID, null);
           cursor.moveToNext(); //без этого exception
/*
           VODITEL1_IDD Varchar(255),
           VODITEL2_IDD Varchar(255),
           PRIMECHANIE Varchar(10000));"); //SQL-запрос на создание БД
*/

           AutoMarka.setText(cursor.getString(1));
           AutoGosNomer.setText(cursor.getString(2));
           AutoColor.setText(cursor.getString(3));
           AutoDiagKartNomer.setText(cursor.getString(4));

           datatehosmotraSpinnerDay.setSelection(cursor.getInt(5)-1);
           datatehosmotraSpinnerMonth.setSelection(cursor.getInt(6)-1);
           datatehosmotraSpinnerYear.setSelection(cursor.getInt(7)-1980);

           datatehosmotraTextViewDay.setText(Integer.toString(cursor.getInt(5)));
           datatehosmotraTextViewMonth.setText(Integer.toString(cursor.getInt(6)));
           datatehosmotraTextViewYear.setText(Integer.toString(cursor.getInt(7)));

           datasledtehosmotraSpinnerDay.setSelection(cursor.getInt(8)-1);
           datasledtehosmotraSpinnerMonth.setSelection(cursor.getInt(9)-1);
           datasledtehosmotraSpinnerYear.setSelection(cursor.getInt(10)-1980);

           datasledtehosmotraTextViewDay.setText(Integer.toString(cursor.getInt(8)));
           datasledtehosmotraTextViewMonth.setText(Integer.toString(cursor.getInt(9)));
           datasledtehosmotraTextViewYear.setText(Integer.toString(cursor.getInt(10)));

           dataosagoSpinnerDay.setSelection(cursor.getInt(11)-1);
           dataosagoSpinnerMonth.setSelection(cursor.getInt(12)-1);
           dataosagoSpinnerYear.setSelection(cursor.getInt(13)-1980);

           dataosagoTextViewDay.setText(Integer.toString(cursor.getInt(11)));
           dataosagoTextViewMonth.setText(Integer.toString(cursor.getInt(12)));
           dataosagoTextViewYear.setText(Integer.toString(cursor.getInt(13)));

           AutoOsagoNomer.setText(cursor.getString(14));
           AutoOsagoStrahCompany.setText(cursor.getString(15));

           dataendosagoSpinnerDay.setSelection(cursor.getInt(16)-1);
           dataendosagoSpinnerMonth.setSelection(cursor.getInt(17)-1);
           dataendosagoSpinnerYear.setSelection(cursor.getInt(18)-1980);

           dataendosagoTextViewDay.setText(Integer.toString(cursor.getInt(16)));
           dataendosagoTextViewMonth.setText(Integer.toString(cursor.getInt(17)));
           dataendosagoTextViewYear.setText(Integer.toString(cursor.getInt(18)));

           dataPREVosagoSpinnerDay.setSelection(cursor.getInt(19)-1);
           dataPREVosagoSpinnerMonth.setSelection(cursor.getInt(20)-1);
           dataPREVosagoSpinnerYear.setSelection(cursor.getInt(21)-1980);

           dataPREVosagoTextViewDay.setText(Integer.toString(cursor.getInt(19)));
           dataPREVosagoTextViewMonth.setText(Integer.toString(cursor.getInt(20)));
           dataPREVosagoTextViewYear.setText(Integer.toString(cursor.getInt(21)));

           AutoOsagoPrevNomer.setText(cursor.getString(22));
           AutoOsagoPrevStrahCompany.setText(cursor.getString(23));

           dataendPREVosagoSpinnerDay.setSelection(cursor.getInt(24)-1);
           dataendPREVosagoSpinnerMonth.setSelection(cursor.getInt(25)-1);
           dataendPREVosagoSpinnerYear.setSelection(cursor.getInt(26)-1980);

           dataendPREVosagoTextViewDay.setText(Integer.toString(cursor.getInt(24)));
           dataendPREVosagoTextViewMonth.setText(Integer.toString(cursor.getInt(25)));
           dataendPREVosagoTextViewYear.setText(Integer.toString(cursor.getInt(26)));

           datakaskoSpinnerDay.setSelection(cursor.getInt(27)-1);
           datakaskoSpinnerMonth.setSelection(cursor.getInt(28)-1);
           datakaskoSpinnerYear.setSelection(cursor.getInt(29)-1980);

           datakaskoTextViewDay.setText(Integer.toString(cursor.getInt(27)));
           datakaskoTextViewMonth.setText(Integer.toString(cursor.getInt(28)));
           datakaskoTextViewYear.setText(Integer.toString(cursor.getInt(29)));

           AutoKaskoNomer.setText(String.valueOf(cursor.getInt(30)));
           AutoKaskoStrahCompany.setText(cursor.getString(31));

           dataendkaskoSpinnerDay.setSelection(cursor.getInt(32)-1);
           dataendkaskoSpinnerMonth.setSelection(cursor.getInt(33)-1);
           dataendkaskoSpinnerYear.setSelection(cursor.getInt(34)-1980);

           dataendkaskoTextViewDay.setText(Integer.toString(cursor.getInt(32)));
           dataendkaskoTextViewMonth.setText(Integer.toString(cursor.getInt(33)));
           dataendkaskoTextViewYear.setText(Integer.toString(cursor.getInt(34)));

           dataPREVkaskoSpinnerDay.setSelection(cursor.getInt(35)-1);
           dataPREVkaskoSpinnerMonth.setSelection(cursor.getInt(36)-1);
           dataPREVkaskoSpinnerYear.setSelection(cursor.getInt(37)-1980);

           dataPREVkaskoTextViewDay.setText(Integer.toString(cursor.getInt(35)));
           dataPREVkaskoTextViewMonth.setText(Integer.toString(cursor.getInt(36)));
           dataPREVkaskoTextViewYear.setText(Integer.toString(cursor.getInt(37)));

           AutoKaskoPrevNomer.setText(cursor.getString(38));
           AutoKaskoPrevStrahCompany.setText(cursor.getString(39));

           dataendPREVkaskoSpinnerDay.setSelection(cursor.getInt(40)-1);
           dataendPREVkaskoSpinnerMonth.setSelection(cursor.getInt(41)-1);
           dataendPREVkaskoSpinnerYear.setSelection(cursor.getInt(42)-1980);

           dataendPREVkaskoTextViewDay.setText(Integer.toString(cursor.getInt(40)));
           dataendPREVkaskoTextViewMonth.setText(Integer.toString(cursor.getInt(41)));
           dataendPREVkaskoTextViewYear.setText(Integer.toString(cursor.getInt(42)));

           datauchetaSpinnerDay.setSelection(cursor.getInt(43)-1);
           datauchetaSpinnerMonth.setSelection(cursor.getInt(44)-1);
           datauchetaSpinnerYear.setSelection(cursor.getInt(45)-1980);

           datauchetaTextViewDay.setText(Integer.toString(cursor.getInt(43)));
           datauchetaTextViewMonth.setText(Integer.toString(cursor.getInt(44)));
           datauchetaTextViewYear.setText(Integer.toString(cursor.getInt(45)));

           AutoDokumentPostUchet.setText(cursor.getString(46));

           datadokumentauchetaSpinnerDay.setSelection(cursor.getInt(47)-1);
           datadokumentauchetaSpinnerMonth.setSelection(cursor.getInt(48)-1);
           datadokumentauchetaSpinnerYear.setSelection(cursor.getInt(49)-1980);

           datadokumentauchetaTextViewDay.setText(Integer.toString(cursor.getInt(47)));
           datadokumentauchetaTextViewMonth.setText(Integer.toString(cursor.getInt(48)));
           datadokumentauchetaTextViewYear.setText(Integer.toString(cursor.getInt(49)));

           AutoProbegZero.setText(cursor.getString(50));
           AutoPTSnomer.setText(cursor.getString(51));
           AutoSTSnomer.setText(cursor.getString(52));
           AutoVladelec.setText(cursor.getString(53));
           AutoVIN.setText(cursor.getString(54));

           AutoDvigNomer.setText(cursor.getString(55));

           // Voditel1_IDD - 56
           // Voditel2_IDD - 57

           AutoPrimechanie.setText(cursor.getString(58));


           db.close();
       }

       catch (Exception exx) {
           appActivity.Toasts(Toast.makeText(getActivity(), exx.toString(), Toast.LENGTH_LONG),"show");
       }

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


       //region КНОПКА СОХРАНИТЬ ИЗМЕНЕНИЯ В АВТО

       SaveAutoButton.setOnClickListener(new OnClickListener() {
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


                   DB.ChangeAUTO(ID,Marka,
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

                   appActivity.Toasts(Toast.makeText(getActivity(), "Автомобиль "+Marka+" "+Gosnomer+" - изменения успешно внесены в базу", Toast.LENGTH_LONG),"show");

                   appActivity.autoslist();

               }

               catch (Exception ex) {
                appActivity.Toasts(Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG),"show");
               }
                                            }
          }
       );

       //endregion

       //region КНОПКА УДАЛИТЬ АВТОМОБИЛЬ

       DelAutoButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               delautodialog = new AlertDialog.Builder(container.getContext());
               delautodialog.setCancelable(true);
               View dialogView = inflater.inflate(R.layout.alertdialog_delete, null); //важно - inflater определен в начале кода фрагмента
               // Привязка xml-разметки окна диалогов
               delautodialog.setView(dialogView);
               final AlertDialog deldialog = delautodialog.create();

               final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
               final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
               TextView Zagolovok = (TextView) dialogView.findViewById(R.id.Zagolovok);
               Zagolovok.setText("Удалить автомобиль из базы?");

               btn_negative.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       deldialog.cancel();

                   }
               });

               btn_positive.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       DB.DelAUTO(ID);

                       appActivity.Toasts(Toast.makeText(getActivity(), "Автомобиль успешно удален из базы", Toast.LENGTH_LONG),"show");
                       deldialog.cancel();
                       appActivity.autoslist();

                   }
               });


               deldialog.show();

           }
       });

       //endregion


       //region СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (РАЗДЕЛ АВТОРАСХОДЫ)



       try {

           autorashodylist.clear();
           idArrayAutoRashody.clear();
           SQLiteDatabase db = DB.getReadableDatabase();
           Cursor cursor = db.rawQuery("SELECT * FROM AUTORASHODY WHERE IDD = " + ID + " ORDER BY ID DESC", null);

           while (cursor.moveToNext()) {
               autorashodylist.add(new AUTO_RASHODY(cursor.getString(1),cursor.getString(2),cursor.getFloat(3),cursor.getFloat(4),cursor.getFloat(5),cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getInt(9),cursor.getInt(10), cursor.getInt(11), cursor.getInt(12),cursor.getInt(13), cursor.getInt(14), cursor.getInt(15),"",cursor.getInt(17),cursor.getInt(18), cursor.getInt(19),cursor.getInt(20), ID, cursor.getString(0)));
               idArrayAutoRashody.add(cursor.getString(0));
           }

           ARadapter.notifyDataSetChanged();
           ListViewAutoRashody.setAdapter(ARadapter);

           //ВЫСТАВЛЕНИЕ LISTVIEW ПО ВЫСОТЕ В ЗАВИСИМОСТИ ОТ КОЛИЧЕСТВА ЭЛЕМЕНТОВ
           if ((Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density))<Math.round(560*getActivity().getResources().getDisplayMetrics().density))
           {
               ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
               params.height=Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density);
               ListViewAutoRashody.setLayoutParams(params);
           }

           else {
               ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
               params.height=Math.round(560*getActivity().getResources().getDisplayMetrics().density);
               ListViewAutoRashody.setLayoutParams(params);
           }




           try {
               SQLiteDatabase db1 = DB.getReadableDatabase();
               Cursor cursor1 = db1.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR))+") AND (autoTYPE='Топливо');", null);
               float Summ = 0;
               Summ1 = new TextStr();
               Summ1.setTextStr("0");

               while (cursor1.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor1.getString(0));
                       Summ1.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost1=(TextView)rootView.findViewById(R.id.stoimost_toplivo_tekyear);
               Stoimost1.setText("Стоимость топлива (текущий год): "+Summ1.getTextStr()+" \u20BD");

           } catch (Exception CursorException) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db2 = DB.getReadableDatabase();
               Cursor cursor2 = db2.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR)-1)+") AND (autoTYPE='Топливо');", null);
               float Summ = 0;
               Summ2 = new TextStr();
               Summ2.setTextStr("0");

               while (cursor2.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor2.getString(0));
                       Summ2.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost2=(TextView)rootView.findViewById(R.id.stoimost_toplivo_prevyear);
               Stoimost2.setText("Стоимость топлива (предыдущий год): "+Summ2.getTextStr()+" \u20BD");

           } catch (Exception CursorException2) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException2.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db3 = DB.getReadableDatabase();
               Cursor cursor3 = db3.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR))+") AND (autoTYPE='Запчасть');", null);
               float Summ = 0;
               Summ3 = new TextStr();
               Summ3.setTextStr("0");

               while (cursor3.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor3.getString(0));
                       Summ3.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost3=(TextView)rootView.findViewById(R.id.stoimost_zip_tekyear);
               Stoimost3.setText("Стоимость запасных частей (текущий год): "+Summ3.getTextStr()+" \u20BD");

           } catch (Exception CursorException3) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException3.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db4 = DB.getReadableDatabase();
               Cursor cursor4 = db4.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR)-1)+") AND (autoTYPE='Запчасть');", null);
               float Summ = 0;
               Summ4 = new TextStr();
               Summ4.setTextStr("0");

               while (cursor4.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor4.getString(0));
                       Summ4.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost4=(TextView)rootView.findViewById(R.id.stoimost_zip_prevyear);
               Stoimost4.setText("Стоимость запасных частей (предыдущий год): "+Summ4.getTextStr()+" \u20BD");

           } catch (Exception CursorException4) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException4.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db5 = DB.getReadableDatabase();
               Cursor cursor5 = db5.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR))+") AND ((autoTYPE='УслугиРемонт') OR (autoTYPE='РегулярныеРасходы'));", null);
               float Summ = 0;
               Summ5 = new TextStr();
               Summ5.setTextStr("0");

               while (cursor5.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor5.getString(0));
                       Summ5.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost5=(TextView)rootView.findViewById(R.id.stoimost_rabotuslug_tekyear);
               Stoimost5.setText("Стоимость работ/услуг, вкл. регулярные (текущий год): "+Summ5.getTextStr()+" \u20BD");

           } catch (Exception CursorException5) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException5.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db6 = DB.getReadableDatabase();
               Cursor cursor6 = db6.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR)-1)+") AND ((autoTYPE='УслугиРемонт') OR (autoTYPE='РегулярныеРасходы'));", null);
               float Summ = 0;
               Summ6 = new TextStr();
               Summ6.setTextStr("0");

               while (cursor6.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor6.getString(0));
                       Summ6.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost6=(TextView)rootView.findViewById(R.id.stoimost_rabotuslug_prevyear);
               Stoimost6.setText("Стоимость работ/услуг, вкл. регулярные (предыдущий год): "+Summ6.getTextStr()+" \u20BD");

           } catch (Exception CursorException6) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException6.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db7 = DB.getReadableDatabase();
               Cursor cursor7 = db7.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR))+");", null);
               float Summ = 0;
               Summ7 = new TextStr();
               Summ7.setTextStr("0");

               while (cursor7.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor7.getString(0));
                       Summ7.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost7=(TextView)rootView.findViewById(R.id.stoimost_itogo_tekyear);
               Stoimost7.setText("ИТОГО (текущий год): "+Summ7.getTextStr()+" \u20BD");

           } catch (Exception CursorException7) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException7.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db8 = DB.getReadableDatabase();
               Cursor cursor8 = db8.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + " AND (autoDATA_Y="+Integer.toString(today.get(Calendar.YEAR)-1)+");", null);
               float Summ = 0;
               Summ8 = new TextStr();
               Summ8.setTextStr("0");

               while (cursor8.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor8.getString(0));
                       Summ8.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost8=(TextView)rootView.findViewById(R.id.stoimost_itogo_prevyear);
               Stoimost8.setText("ИТОГО (предыдущий год): "+Summ8.getTextStr()+" \u20BD");

           } catch (Exception CursorException8) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException8.toString(),Toast.LENGTH_LONG),"show");
           }


           try {
               SQLiteDatabase db9 = DB.getReadableDatabase();
               Cursor cursor9 = db9.rawQuery("SELECT autoSTOIMOST FROM AUTORASHODY WHERE IDD = " + ID + ";", null);
               float Summ = 0;
               Summ9 = new TextStr();
               Summ9.setTextStr("0");

               while (cursor9.moveToNext()) {

                   try {
                       Summ = Summ + Float.parseFloat(cursor9.getString(0));
                       Summ9.setTextStr(Float.toString(Summ));
                   } catch (NumberFormatException ex) {
                   }
               }

               TextView Stoimost9=(TextView)rootView.findViewById(R.id.stoimost_itogo_all);
               Stoimost9.setText("ИТОГО (за всё время): "+Summ9.getTextStr()+" \u20BD");

           } catch (Exception CursorException9) {
               appActivity.Toasts(Toast.makeText(getActivity(),CursorException9.toString(),Toast.LENGTH_LONG),"show");
           }





           ListViewAutoRashody.setOnItemClickListener(
                   new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                           final String selectedIDautorashody = idArrayAutoRashody.get(position).toString();

                           final AlertDialog.Builder editautorashodydialog = new AlertDialog.Builder(container.getContext());

                           View dialogView = inflater.inflate(R.layout.alertdialog_edit_autorashody, null); //важно - inflater определен в начале кода фрагмента

                           editautorashodydialog.setCancelable(false);

                           // Привязка xml-разметки окна диалогов
                           editautorashodydialog.setView(dialogView);


                           final RadioGroup RadioAutoRashodyType=(RadioGroup)dialogView.findViewById(R.id.radiogroupTYPEAUTORASHODY);
                           final RadioButton RadioAutoRashodyTypeButton1=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_toplivo);
                           final RadioButton RadioAutoRashodyTypeButton2=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_rashodka);
                           final RadioButton RadioAutoRashodyTypeButton3=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_zapchast);
                           final RadioButton RadioAutoRashodyTypeButton4=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_uslugiremont);
                           final RadioButton RadioAutoRashodyTypeButton5=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_uslugiregular);
                           final RadioButton RadioAutoRashodyTypeButton6=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_parkovka);
                           final RadioButton RadioAutoRashodyTypeButton7=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_shtrafi);
                           final RadioButton RadioAutoRashodyTypeButton8=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_strahovanie);
                           final RadioButton RadioAutoRashodyTypeButton9=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_nalogi);

                           final EditText DialogEditAutoRashodyName=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_name);
                           final EditText DialogEditAutoRashodyEdIzm=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_edizm);
                           final EditText DialogEditAutoRashodyKolvo=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_kolvo);
                           final EditText DialogEditAutoRashodyCenaEd=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_cenaed);
                           final TextView DialogEditAutoRashodyStoimost=(TextView)dialogView.findViewById(R.id.dialog_addtorashody_stoimost);
                           final EditText DialogEditAutoRashodyTekProbeg=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_probeg);
                           final EditText DialogEditAutoRashodyNomerCheka=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_nomercheka);
                           final EditText DialogEditAutoRashodyNomerZakaznarjada=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_nomerzakaznarjada);
                           final Spinner DialogEditAutoRashodyData_D=(Spinner)dialogView.findViewById(R.id.autorashodydialogdata_spinnerday);
                           final Spinner DialogEditAutoRashodyData_M=(Spinner)dialogView.findViewById(R.id.autorashodydialogdata_spinnermonth);
                           final Spinner DialogEditAutoRashodyData_Y=(Spinner)dialogView.findViewById(R.id.autorashodydialogdata_spinneryear);
                           final Spinner DialogEditAutoRashodySrokKoncaGarantii_D=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokgarantiidata_spinnerday);
                           final Spinner DialogEditAutoRashodySrokKoncaGarantii_M=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokgarantiidata_spinnermonth);
                           final Spinner DialogEditAutoRashodySrokKoncaGarantii_Y=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokgarantiidata_spinneryear);
                           final Spinner DialogEditAutoRashodySrokKoncaResursa_D=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokresursadata_spinnerday);
                           final Spinner DialogEditAutoRashodySrokKoncaResursa_M=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokresursadata_spinnermonth);
                           final Spinner DialogEditAutoRashodySrokKoncaResursa_Y=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokresursadata_spinneryear);
                           final EditText DialogEditAutoRashodyEndResurseProbeg=(EditText) dialogView.findViewById(R.id.dialog_addautorashody_probegendresurs);

                           final Button DialogEditAutoRashodyBtn_positive = (Button) dialogView.findViewById(R.id.autorashodyeditdialog_positive_btn);
                           final Button DialogEditAutoRashodyBtn_negative = (Button) dialogView.findViewById(R.id.autorashodyeditdialog_negative_btn);
                           final Button DialogEditAutoRashodyBtn_del = (Button) dialogView.findViewById(R.id.autorashodyeditdialog_del_btn);
                           final Button DialogEditAutoRashodyBtn_del_go = (Button) dialogView.findViewById(R.id.dialog_addautorashody_del_btn_go);

                           final TextView DialogEditAutoRashodyRadioSelectedTextView=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_radioselecteddb);

                           final TextView DialogEditAutoRashodyTextViewData_D=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_d);
                           final TextView DialogEditAutoRashodyTextViewData_M=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_m);
                           final TextView DialogEditAutoRashodyTextViewData_Y=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_y);

                           final TextView DialogEditAutoRashodyTextViewSrokGarantii_D=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokgarantii_d);
                           final TextView DialogEditAutoRashodyTextViewSrokGarantii_M=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokgarantii_m);
                           final TextView DialogEditAutoRashodyTextViewSrokGarantii_Y=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokgarantii_y);

                           final TextView DialogEditAutoRashodyTextViewSrokResursa_D=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokresursa_d);
                           final TextView DialogEditAutoRashodyTextViewSrokResursa_M=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokresursa_m);
                           final TextView DialogEditAutoRashodyTextViewSrokResursa_Y=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokresursa_y);

                           final Calendar today = Calendar.getInstance();

                           DialogEditAutoRashodyRadioSelectedTextView.setText("");

                           SQLiteDatabase db = DB.getReadableDatabase();
                           Cursor cursor = db.rawQuery("SELECT * FROM AUTORASHODY WHERE ID = " + selectedIDautorashody, null);
                           cursor.moveToNext(); //без этого exception

                           String autorashodytype = cursor.getString(1);
                           RadioAutoRashodyType.clearCheck();
                           DialogEditAutoRashodyRadioSelectedTextView.setText(autorashodytype);

                           if (autorashodytype.equals("Топливо")) {
                               RadioAutoRashodyTypeButton1.setChecked(true);
                           }

                           if (autorashodytype.equals("Расходка")) {
                               RadioAutoRashodyTypeButton2.setChecked(true);
                           }

                           if (autorashodytype.equals("Запчасть")) {
                               RadioAutoRashodyTypeButton3.setChecked(true);
                           }

                           if (autorashodytype.equals("УслугиРемонт")) {
                               RadioAutoRashodyTypeButton4.setChecked(true);
                           }

                           if (autorashodytype.equals("РегулярныеРасходы")) {
                               RadioAutoRashodyTypeButton5.setChecked(true);
                           }

                           if (autorashodytype.equals("Парковка")) {
                               RadioAutoRashodyTypeButton6.setChecked(true);
                           }

                           if (autorashodytype.equals("Штраф")) {
                               RadioAutoRashodyTypeButton7.setChecked(true);
                           }

                           if (autorashodytype.equals("Страховка")) {
                               RadioAutoRashodyTypeButton8.setChecked(true);
                           }

                           if (autorashodytype.equals("Налог")) {
                               RadioAutoRashodyTypeButton9.setChecked(true);
                           }


                           DialogEditAutoRashodyEdIzm.setText(cursor.getString(2));
                           DialogEditAutoRashodyKolvo.setText(Float.toString(cursor.getFloat(3)));
                           DialogEditAutoRashodyCenaEd.setText(Float.toString(cursor.getFloat(4)));
                           DialogEditAutoRashodyStoimost.setText(Float.toString(cursor.getFloat(5)));
                           DialogEditAutoRashodyName.setText(cursor.getString(6));
                           DialogEditAutoRashodyNomerCheka.setText(cursor.getString(7));
                           DialogEditAutoRashodyNomerZakaznarjada.setText(cursor.getString(8));

                           DialogEditAutoRashodyData_D.setSelection(cursor.getInt(9)-1);
                           DialogEditAutoRashodyData_M.setSelection(cursor.getInt(10)-1);
                           DialogEditAutoRashodyData_Y.setSelection(cursor.getInt(11)-1980);

                           DialogEditAutoRashodySrokKoncaGarantii_D.setSelection(cursor.getInt(12)-1);
                           DialogEditAutoRashodySrokKoncaGarantii_M.setSelection(cursor.getInt(13)-1);
                           DialogEditAutoRashodySrokKoncaGarantii_Y.setSelection(cursor.getInt(14)-1980);

                           DialogEditAutoRashodyTekProbeg.setText(Integer.toString(cursor.getInt(15)));

                           //IDV - cursor.getString(16)

                           DialogEditAutoRashodySrokKoncaResursa_D.setSelection(cursor.getInt(17)-1);
                           DialogEditAutoRashodySrokKoncaResursa_M.setSelection(cursor.getInt(18)-1);
                           DialogEditAutoRashodySrokKoncaResursa_Y.setSelection(cursor.getInt(19)-1980);

                           DialogEditAutoRashodyEndResurseProbeg.setText(Integer.toString(cursor.getInt(20)));

                           RadioAutoRashodyType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                               @Override
                               public void onCheckedChanged(RadioGroup group, int checkedId) {
                                   switch (checkedId) {

                                       case R.id.radio_typeautorashody_toplivo:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db1 = DB.getReadableDatabase();
                                           db1.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'Топливо' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db1.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("Топливо");
                                           break;


                                       case R.id.radio_typeautorashody_rashodka:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db2 = DB.getReadableDatabase();
                                           db2.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'Расходка' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db2.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("Расходка");
                                           break;


                                       case R.id.radio_typeautorashody_zapchast:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db3 = DB.getReadableDatabase();
                                           db3.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'Запчасть' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db3.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("Запчасть");
                                           break;


                                       case R.id.radio_typeautorashody_uslugiremont:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db4 = DB.getReadableDatabase();
                                           db4.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'УслугиРемонт' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db4.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("УслугиРемонт");
                                           break;


                                       case R.id.radio_typeautorashody_uslugiregular:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db5 = DB.getReadableDatabase();
                                           db5.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'РегулярныеРасходы' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db5.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("РегулярныеРасходы");
                                           break;


                                       case R.id.radio_typeautorashody_parkovka:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db6 = DB.getReadableDatabase();
                                           db6.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'Парковка' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db6.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("Парковка");
                                           break;


                                       case R.id.radio_typeautorashody_shtrafi:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db7 = DB.getReadableDatabase();
                                           db7.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'Штраф' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db7.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("Штраф");
                                           break;

                                       case R.id.radio_typeautorashody_strahovanie:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db8 = DB.getReadableDatabase();
                                           db8.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'Страховка' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db8.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("Страховка");
                                           break;

                                       case R.id.radio_typeautorashody_nalogi:
                                           DB = new SQLiteConnect(getActivity());
                                           SQLiteDatabase db9 = DB.getReadableDatabase();
                                           db9.execSQL("UPDATE AUTORASHODY SET autoTYPE = 'Налог' WHERE ID ='" + selectedIDautorashody + "'; ");
                                           db9.close();
                                           DialogEditAutoRashodyRadioSelectedTextView.setText("Налог");
                                           break;


                                       default:
                                           break;
                                   }
                               }
                           });





                           DialogEditAutoRashodyData_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewData_D.setText(DialogEditAutoRashodyData_D.getSelectedItem().toString());
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewData_D.setText(DialogEditAutoRashodyData_D.getSelectedItem().toString());
                               }
                           });

                           DialogEditAutoRashodyData_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewData_M.setText(String.valueOf(DialogEditAutoRashodyData_M.getSelectedItemPosition()+1));
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewData_M.setText(String.valueOf(DialogEditAutoRashodyData_M.getSelectedItemPosition()+1));
                               }
                           });


                           DialogEditAutoRashodyData_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewData_Y.setText(DialogEditAutoRashodyData_Y.getSelectedItem().toString());
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewData_Y.setText(DialogEditAutoRashodyData_Y.getSelectedItem().toString());
                               }
                           });



                           DialogEditAutoRashodySrokKoncaGarantii_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewSrokGarantii_D.setText(DialogEditAutoRashodySrokKoncaGarantii_D.getSelectedItem().toString());
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewSrokGarantii_D.setText(DialogEditAutoRashodySrokKoncaGarantii_D.getSelectedItem().toString());
                               }
                           });

                           DialogEditAutoRashodySrokKoncaGarantii_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewSrokGarantii_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaGarantii_M.getSelectedItemPosition()+1));
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewSrokGarantii_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaGarantii_M.getSelectedItemPosition()+1));
                               }
                           });


                           DialogEditAutoRashodySrokKoncaGarantii_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewSrokGarantii_Y.setText(DialogEditAutoRashodySrokKoncaGarantii_Y.getSelectedItem().toString());
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewSrokGarantii_Y.setText(DialogEditAutoRashodySrokKoncaGarantii_Y.getSelectedItem().toString());
                               }
                           });



                           DialogEditAutoRashodySrokKoncaResursa_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewSrokResursa_D.setText(DialogEditAutoRashodySrokKoncaResursa_D.getSelectedItem().toString());
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewSrokResursa_D.setText(DialogEditAutoRashodySrokKoncaResursa_D.getSelectedItem().toString());
                               }
                           });

                           DialogEditAutoRashodySrokKoncaResursa_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewSrokResursa_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaResursa_M.getSelectedItemPosition()+1));
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewSrokResursa_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaResursa_M.getSelectedItemPosition()+1));
                               }
                           });


                           DialogEditAutoRashodySrokKoncaResursa_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                               {
                                   DialogEditAutoRashodyTextViewSrokResursa_Y.setText(DialogEditAutoRashodySrokKoncaResursa_Y.getSelectedItem().toString());
                               }
                               public void onNothingSelected(AdapterView<?> parent)
                               {
                                   DialogEditAutoRashodyTextViewSrokResursa_Y.setText(DialogEditAutoRashodySrokKoncaResursa_Y.getSelectedItem().toString());
                               }
                           });

                           // Создание диалога
                           final AlertDialog editautorashodyAD = editautorashodydialog.create();


                           DialogEditAutoRashodyBtn_positive.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   // Dismiss the alert dialog


                                   try {
                                       String autoTYPE=DialogEditAutoRashodyRadioSelectedTextView.getText().toString();
                                       String autoEDIZM=DialogEditAutoRashodyEdIzm.getText().toString();
                                       Float autoKOLVO=Float.parseFloat(DialogEditAutoRashodyKolvo.getText().toString());
                                       Float autoCENAED=Float.parseFloat(DialogEditAutoRashodyCenaEd.getText().toString());
                                       Float autoSTOIMOST=Float.parseFloat(DialogEditAutoRashodyKolvo.getText().toString())*Float.parseFloat(DialogEditAutoRashodyCenaEd.getText().toString());
                                       String autoNAIMENOVANIE=DialogEditAutoRashodyName.getText().toString();
                                       String autoNOMERCHEKA=DialogEditAutoRashodyNomerCheka.getText().toString();
                                       String autoNOMERZAKAZNARJADA=DialogEditAutoRashodyNomerZakaznarjada.getText().toString();
                                       Integer autoDATA_D=Integer.parseInt(DialogEditAutoRashodyTextViewData_D.getText().toString());
                                       Integer autoDATA_M=Integer.parseInt(DialogEditAutoRashodyTextViewData_M.getText().toString());
                                       Integer autoDATA_Y=Integer.parseInt(DialogEditAutoRashodyTextViewData_Y.getText().toString());
                                       Integer autoSROKKONCAGARANTII_D=Integer.parseInt(DialogEditAutoRashodyTextViewSrokGarantii_D.getText().toString());
                                       Integer autoSROKKONCAGARANTII_M=Integer.parseInt(DialogEditAutoRashodyTextViewSrokGarantii_M.getText().toString());
                                       Integer autoSROKKONCAGARANTII_Y=Integer.parseInt(DialogEditAutoRashodyTextViewSrokGarantii_Y.getText().toString());
                                       Integer autoPROBEG=Integer.parseInt(DialogEditAutoRashodyTekProbeg.getText().toString());
                                       //String IDV
                                       Integer autoENDRESURSDATA_D=Integer.parseInt(DialogEditAutoRashodyTextViewSrokResursa_D.getText().toString());
                                       Integer autoENDRESURSDATA_M=Integer.parseInt(DialogEditAutoRashodyTextViewSrokResursa_M.getText().toString());
                                       Integer autoENDRESURSDATA_Y=Integer.parseInt(DialogEditAutoRashodyTextViewSrokResursa_Y.getText().toString());
                                       Integer autoENDRESURSPROBEG=Integer.parseInt(DialogEditAutoRashodyEndResurseProbeg.getText().toString());

                                       DB.ChangeAUTORASHODY(selectedIDautorashody,autoTYPE,autoEDIZM,autoKOLVO,autoCENAED,autoSTOIMOST,autoNAIMENOVANIE,autoNOMERCHEKA,autoNOMERZAKAZNARJADA,autoDATA_D,autoDATA_M,autoDATA_Y,autoSROKKONCAGARANTII_D,autoSROKKONCAGARANTII_M,autoSROKKONCAGARANTII_Y,autoPROBEG,"",autoENDRESURSDATA_D,autoENDRESURSDATA_M,autoENDRESURSDATA_Y,autoENDRESURSPROBEG,ID);



                                       /*
                                       try {
                                           Sst.setTextStr("");
                                           float f1 = Float.parseFloat(DialogEditTovaUslugaKolvo.getText().toString());
                                           float f2 = Float.parseFloat(DialogEditTovaUslugaCenaEd.getText().toString());
                                           float f3 = f1 * f2;
                                           TovarUslugaStoimost.setText("Стоимость: " + String.valueOf(f3));

                                           Sst.setTextStr(String.valueOf(f3));
                                       } catch (NumberFormatException ex) {
                                       }
                                       */

                                       appActivity.Toasts(Toast.makeText(getActivity(), "Изменение авторасходов произведено успешно", Toast.LENGTH_LONG),"show");

                                       autorashodylist.clear();

                                       SQLiteDatabase db = DB.getReadableDatabase();
                                       Cursor cursor = db.rawQuery("SELECT * FROM AUTORASHODY WHERE IDD = " + ID + " ORDER BY ID DESC", null);

                                       while (cursor.moveToNext()) {
                                           autorashodylist.add(new AUTO_RASHODY(cursor.getString(1),cursor.getString(2),cursor.getFloat(3),cursor.getFloat(4),cursor.getFloat(5),cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getInt(9),cursor.getInt(10), cursor.getInt(11), cursor.getInt(12),cursor.getInt(13), cursor.getInt(14), cursor.getInt(15),"",cursor.getInt(17),cursor.getInt(18), cursor.getInt(19),cursor.getInt(20), ID, cursor.getString(0)));
                                           idArrayAutoRashody.add(cursor.getString(0));
                                       }

                                       ARadapter.notifyDataSetChanged();

                                       //ВЫСТАВЛЕНИЕ LISTVIEW ПО ВЫСОТЕ В ЗАВИСИМОСТИ ОТ КОЛИЧЕСТВА ЭЛЕМЕНТОВ
                                       if ((Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density))<(560*getActivity().getResources().getDisplayMetrics().density))
                                       {
                                           ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
                                           params.height=Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density);
                                           ListViewAutoRashody.setLayoutParams(params);
                                       }

                                       else {
                                           ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
                                           params.height=Math.round(560*getActivity().getResources().getDisplayMetrics().density);
                                           ListViewAutoRashody.setLayoutParams(params);
                                       }



                                       DialogEditAutoRashodyBtn_del_go.setVisibility(View.GONE);
                                       editautorashodyAD.dismiss();


                                   } catch (SQLException mSQLException) {
                                       Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
                                   }

                                   editautorashodyAD.cancel();

                               }
                           });


                           DialogEditAutoRashodyBtn_negative.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {

                                   editautorashodyAD.dismiss();

                               }
                           });


                           DialogEditAutoRashodyBtn_del.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   // Dismiss/cancel the alert dialog
                                   //dialog.cancel();

                                   DialogEditAutoRashodyBtn_del_go.setVisibility(View.VISIBLE);

                                   DialogEditAutoRashodyBtn_del_go.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           DB.DelAUTORASHODY(selectedIDautorashody);

                                           autorashodylist.clear();
                                           idArrayAutoRashody.clear();

                                           appActivity.Toasts(Toast.makeText(getActivity(), "Запись из базы авторасходов успешно удалена", Toast.LENGTH_LONG),"show");

                                           SQLiteDatabase db = DB.getReadableDatabase();
                                           Cursor cursor = db.rawQuery("SELECT * FROM AUTORASHODY WHERE IDD = " + ID + " ORDER BY ID DESC", null);

                                           while (cursor.moveToNext()) {
                                               autorashodylist.add(new AUTO_RASHODY(cursor.getString(1),cursor.getString(2),cursor.getFloat(3),cursor.getFloat(4),cursor.getFloat(5),cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getInt(9),cursor.getInt(10), cursor.getInt(11), cursor.getInt(12),cursor.getInt(13), cursor.getInt(14), cursor.getInt(15),"",cursor.getInt(17),cursor.getInt(18), cursor.getInt(19),cursor.getInt(20), ID, cursor.getString(0)));
                                               idArrayAutoRashody.add(cursor.getString(0));
                                           }

                                           ARadapter.notifyDataSetChanged();


                                           //ВЫСТАВЛЕНИЕ LISTVIEW ПО ВЫСОТЕ В ЗАВИСИМОСТИ ОТ КОЛИЧЕСТВА ЭЛЕМЕНТОВ
                                           if ((Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density))<(560*getActivity().getResources().getDisplayMetrics().density))
                                           {
                                               ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
                                               params.height=Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density);
                                               ListViewAutoRashody.setLayoutParams(params);
                                           }

                                           else {
                                               ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
                                               params.height=Math.round(560*getActivity().getResources().getDisplayMetrics().density);
                                               ListViewAutoRashody.setLayoutParams(params);
                                           }


                                           DialogEditAutoRashodyBtn_del_go.setVisibility(View.GONE);
                                           editautorashodyAD.dismiss();

                                       }
                                   });

                               }
                           });


                           editautorashodyAD.show();
                       }
                   });


           db.close();


       } catch (Exception CursorException) {
           appActivity.Toasts(Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG),"show");
       }


       //endregion

       //region КНОПКА ДОБАВИТЬ АВТОРАСХОДЫ

       AddAutoRashodyButton.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {

               final AlertDialog.Builder addautorashodydialog = new AlertDialog.Builder(container.getContext());
               View dialogView = inflater.inflate(R.layout.alertdialog_add_autorashody, null);
               addautorashodydialog.setCancelable(false);
               addautorashodydialog.setView(dialogView);
               final AlertDialog addautorashodyAD = addautorashodydialog.create();

               final RadioGroup RadioAutoRashodyType=(RadioGroup)dialogView.findViewById(R.id.radiogroupTYPEAUTORASHODY);
               final RadioButton RadioAutoRashodyTypeButton1=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_toplivo);
               final RadioButton RadioAutoRashodyTypeButton2=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_rashodka);
               final RadioButton RadioAutoRashodyTypeButton3=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_zapchast);
               final RadioButton RadioAutoRashodyTypeButton4=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_uslugiremont);
               final RadioButton RadioAutoRashodyTypeButton5=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_uslugiregular);
               final RadioButton RadioAutoRashodyTypeButton6=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_parkovka);
               final RadioButton RadioAutoRashodyTypeButton7=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_shtrafi);
               final RadioButton RadioAutoRashodyTypeButton8=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_strahovanie);
               final RadioButton RadioAutoRashodyTypeButton9=(RadioButton)dialogView.findViewById(R.id.radio_typeautorashody_nalogi);

               final EditText DialogEditAutoRashodyName=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_name);
               final EditText DialogEditAutoRashodyEdIzm=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_edizm);
               final EditText DialogEditAutoRashodyKolvo=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_kolvo);
               final EditText DialogEditAutoRashodyCenaEd=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_cenaed);
               final TextView DialogEditAutoRashodyStoimost=(TextView)dialogView.findViewById(R.id.dialog_addtorashody_stoimost);
               final EditText DialogEditAutoRashodyTekProbeg=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_probeg);
               final EditText DialogEditAutoRashodyNomerCheka=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_nomercheka);
               final EditText DialogEditAutoRashodyNomerZakaznarjada=(EditText)dialogView.findViewById(R.id.dialog_addautorashody_nomerzakaznarjada);
               final Spinner DialogEditAutoRashodyData_D=(Spinner)dialogView.findViewById(R.id.autorashodydialogdata_spinnerday);
               final Spinner DialogEditAutoRashodyData_M=(Spinner)dialogView.findViewById(R.id.autorashodydialogdata_spinnermonth);
               final Spinner DialogEditAutoRashodyData_Y=(Spinner)dialogView.findViewById(R.id.autorashodydialogdata_spinneryear);
               final Spinner DialogEditAutoRashodySrokKoncaGarantii_D=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokgarantiidata_spinnerday);
               final Spinner DialogEditAutoRashodySrokKoncaGarantii_M=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokgarantiidata_spinnermonth);
               final Spinner DialogEditAutoRashodySrokKoncaGarantii_Y=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokgarantiidata_spinneryear);
               final Spinner DialogEditAutoRashodySrokKoncaResursa_D=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokresursadata_spinnerday);
               final Spinner DialogEditAutoRashodySrokKoncaResursa_M=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokresursadata_spinnermonth);
               final Spinner DialogEditAutoRashodySrokKoncaResursa_Y=(Spinner)dialogView.findViewById(R.id.autorashodydialogsrokresursadata_spinneryear);
               final EditText DialogEditAutoRashodyEndResurseProbeg=(EditText) dialogView.findViewById(R.id.dialog_addautorashody_probegendresurs);

               final Button DialogAddAutoRashodyBtn_positive = (Button) dialogView.findViewById(R.id.autorashodyadddialog_positive_btn);
               final Button DialogAddAutoRashodyBtn_negative = (Button) dialogView.findViewById(R.id.autorashodyadddialog_negative_btn);

               final TextView DialogEditAutoRashodyRadioSelectedTextView=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_radioselecteddb);

               final TextView DialogEditAutoRashodyTextViewData_D=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_d);
               final TextView DialogEditAutoRashodyTextViewData_M=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_m);
               final TextView DialogEditAutoRashodyTextViewData_Y=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_y);

               final TextView DialogEditAutoRashodyTextViewSrokGarantii_D=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokgarantii_d);
               final TextView DialogEditAutoRashodyTextViewSrokGarantii_M=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokgarantii_m);
               final TextView DialogEditAutoRashodyTextViewSrokGarantii_Y=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokgarantii_y);

               final TextView DialogEditAutoRashodyTextViewSrokResursa_D=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokresursa_d);
               final TextView DialogEditAutoRashodyTextViewSrokResursa_M=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokresursa_m);
               final TextView DialogEditAutoRashodyTextViewSrokResursa_Y=(TextView)dialogView.findViewById(R.id.dialog_addautorashody_textview_data_srokresursa_y);

               //выставляем текущие и будущие даты для удобства
               final Calendar today = Calendar.getInstance();
               DialogEditAutoRashodyData_D.setSelection(today.get(Calendar.DAY_OF_MONTH)-1);
               DialogEditAutoRashodyData_M.setSelection(today.get(Calendar.MONTH));
               DialogEditAutoRashodyData_Y.setSelection(today.get(Calendar.YEAR)-1980);

               DialogEditAutoRashodyTextViewData_D.setText(DialogEditAutoRashodyData_D.getSelectedItem().toString());
               DialogEditAutoRashodyTextViewData_D.setText(DialogEditAutoRashodyData_M.getSelectedItem().toString());
               DialogEditAutoRashodyTextViewData_D.setText(DialogEditAutoRashodyData_Y.getSelectedItem().toString());

               DialogEditAutoRashodySrokKoncaGarantii_D.setSelection(today.get(Calendar.DAY_OF_MONTH)-1);
               DialogEditAutoRashodySrokKoncaGarantii_M.setSelection(today.get(Calendar.MONTH));
               DialogEditAutoRashodySrokKoncaGarantii_Y.setSelection(today.get(Calendar.YEAR)-1979); //обычно гарантия 1 год

               DialogEditAutoRashodyTextViewSrokGarantii_D.setText(DialogEditAutoRashodySrokKoncaGarantii_D.getSelectedItem().toString());
               DialogEditAutoRashodyTextViewSrokGarantii_M.setText(DialogEditAutoRashodySrokKoncaGarantii_D.getSelectedItem().toString());
               DialogEditAutoRashodyTextViewSrokGarantii_Y.setText(DialogEditAutoRashodySrokKoncaGarantii_D.getSelectedItem().toString());

               DialogEditAutoRashodySrokKoncaResursa_D.setSelection(today.get(Calendar.DAY_OF_MONTH)-1);
               DialogEditAutoRashodySrokKoncaResursa_M.setSelection(today.get(Calendar.MONTH));
               DialogEditAutoRashodySrokKoncaResursa_Y.setSelection(today.get(Calendar.YEAR)-1977); //обычно ресурс 3 года

               DialogEditAutoRashodyTextViewSrokResursa_D.setText(DialogEditAutoRashodySrokKoncaResursa_D.getSelectedItem().toString());
               DialogEditAutoRashodyTextViewSrokResursa_M.setText(DialogEditAutoRashodySrokKoncaResursa_D.getSelectedItem().toString());
               DialogEditAutoRashodyTextViewSrokResursa_Y.setText(DialogEditAutoRashodySrokKoncaResursa_D.getSelectedItem().toString());


               DialogEditAutoRashodyData_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewData_D.setText(DialogEditAutoRashodyData_D.getSelectedItem().toString());
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewData_D.setText(DialogEditAutoRashodyData_D.getSelectedItem().toString());
                   }
               });

               DialogEditAutoRashodyData_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewData_M.setText(String.valueOf(DialogEditAutoRashodyData_M.getSelectedItemPosition()+1));
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewData_M.setText(String.valueOf(DialogEditAutoRashodyData_M.getSelectedItemPosition()+1));
                   }
               });


               DialogEditAutoRashodyData_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewData_Y.setText(DialogEditAutoRashodyData_Y.getSelectedItem().toString());
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewData_Y.setText(DialogEditAutoRashodyData_Y.getSelectedItem().toString());
                   }
               });



               DialogEditAutoRashodySrokKoncaGarantii_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewSrokGarantii_D.setText(DialogEditAutoRashodySrokKoncaGarantii_D.getSelectedItem().toString());
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewSrokGarantii_D.setText(DialogEditAutoRashodySrokKoncaGarantii_D.getSelectedItem().toString());
                   }
               });

               DialogEditAutoRashodySrokKoncaGarantii_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewSrokGarantii_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaGarantii_M.getSelectedItemPosition()+1));
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewSrokGarantii_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaGarantii_M.getSelectedItemPosition()+1));
                   }
               });


               DialogEditAutoRashodySrokKoncaGarantii_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewSrokGarantii_Y.setText(DialogEditAutoRashodySrokKoncaGarantii_Y.getSelectedItem().toString());
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewSrokGarantii_Y.setText(DialogEditAutoRashodySrokKoncaGarantii_Y.getSelectedItem().toString());
                   }
               });



               DialogEditAutoRashodySrokKoncaResursa_D.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewSrokResursa_D.setText(DialogEditAutoRashodySrokKoncaResursa_D.getSelectedItem().toString());
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewSrokResursa_D.setText(DialogEditAutoRashodySrokKoncaResursa_D.getSelectedItem().toString());
                   }
               });

               DialogEditAutoRashodySrokKoncaResursa_M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewSrokResursa_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaResursa_M.getSelectedItemPosition()+1));
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewSrokResursa_M.setText(String.valueOf(DialogEditAutoRashodySrokKoncaResursa_M.getSelectedItemPosition()+1));
                   }
               });


               DialogEditAutoRashodySrokKoncaResursa_Y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
                   {
                       DialogEditAutoRashodyTextViewSrokResursa_Y.setText(DialogEditAutoRashodySrokKoncaResursa_Y.getSelectedItem().toString());
                   }
                   public void onNothingSelected(AdapterView<?> parent)
                   {
                       DialogEditAutoRashodyTextViewSrokResursa_Y.setText(DialogEditAutoRashodySrokKoncaResursa_Y.getSelectedItem().toString());
                   }
               });


               RadioAutoRashodyType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                   @Override
                   public void onCheckedChanged(RadioGroup group, int checkedId) {
                       switch (checkedId) {

                           case R.id.radio_typeautorashody_toplivo:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Топливо");
                               break;


                           case R.id.radio_typeautorashody_rashodka:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Расходка");
                               break;


                           case R.id.radio_typeautorashody_zapchast:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Запчасть");
                               break;


                           case R.id.radio_typeautorashody_uslugiremont:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("УслугиРемонт");
                               break;


                           case R.id.radio_typeautorashody_uslugiregular:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("РегулярныеРасходы");
                               break;


                           case R.id.radio_typeautorashody_parkovka:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Парковка");
                               break;


                           case R.id.radio_typeautorashody_shtrafi:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Штраф");
                               break;

                           case R.id.radio_typeautorashody_strahovanie:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Страховка");
                               break;

                           case R.id.radio_typeautorashody_nalogi:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Налог");
                               break;


                           default:
                               DialogEditAutoRashodyRadioSelectedTextView.setText("Топливо");
                               break;
                       }
                   }
               });


               DialogAddAutoRashodyBtn_positive.setOnClickListener(new OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       try {
                           String autoTYPE = DialogEditAutoRashodyRadioSelectedTextView.getText().toString();
                           String autoEDIZM = DialogEditAutoRashodyEdIzm.getText().toString();
                           Float autoKOLVO = Float.parseFloat(DialogEditAutoRashodyKolvo.getText().toString());
                           Float autoCENAED = Float.parseFloat(DialogEditAutoRashodyCenaEd.getText().toString());
                           Float autoSTOIMOST = Float.parseFloat(DialogEditAutoRashodyKolvo.getText().toString()) * Float.parseFloat(DialogEditAutoRashodyCenaEd.getText().toString());
                           String autoNAIMENOVANIE = DialogEditAutoRashodyName.getText().toString();
                           String autoNOMERCHEKA = DialogEditAutoRashodyNomerCheka.getText().toString();
                           String autoNOMERZAKAZNARJADA = DialogEditAutoRashodyNomerZakaznarjada.getText().toString();
                           Integer autoDATA_D = Integer.parseInt(DialogEditAutoRashodyTextViewData_D.getText().toString());
                           Integer autoDATA_M = Integer.parseInt(DialogEditAutoRashodyTextViewData_M.getText().toString());
                           Integer autoDATA_Y = Integer.parseInt(DialogEditAutoRashodyTextViewData_Y.getText().toString());
                           Integer autoSROKKONCAGARANTII_D = Integer.parseInt(DialogEditAutoRashodyTextViewSrokGarantii_D.getText().toString());
                           Integer autoSROKKONCAGARANTII_M = Integer.parseInt(DialogEditAutoRashodyTextViewSrokGarantii_M.getText().toString());
                           Integer autoSROKKONCAGARANTII_Y = Integer.parseInt(DialogEditAutoRashodyTextViewSrokGarantii_Y.getText().toString());
                           Integer autoPROBEG = Integer.parseInt(DialogEditAutoRashodyTekProbeg.getText().toString());
                           //String IDV,
                           Integer autoENDRESURSDATA_D = Integer.parseInt(DialogEditAutoRashodyTextViewSrokResursa_D.getText().toString());
                           Integer autoENDRESURSDATA_M = Integer.parseInt(DialogEditAutoRashodyTextViewSrokResursa_M.getText().toString());
                           Integer autoENDRESURSDATA_Y = Integer.parseInt(DialogEditAutoRashodyTextViewSrokResursa_Y.getText().toString());
                           Integer autoENDRESURSPROBEG = Integer.parseInt(DialogEditAutoRashodyEndResurseProbeg.getText().toString());

                           DB.AddAUTORASHODY(autoTYPE,autoEDIZM,autoKOLVO,autoCENAED,autoSTOIMOST,autoNAIMENOVANIE,autoNOMERCHEKA,autoNOMERZAKAZNARJADA,autoDATA_D,autoDATA_M,autoDATA_Y,autoSROKKONCAGARANTII_D,autoSROKKONCAGARANTII_M,autoSROKKONCAGARANTII_Y,autoPROBEG,"",autoENDRESURSDATA_D,autoENDRESURSDATA_M,autoENDRESURSDATA_Y,autoENDRESURSPROBEG,ID);

                           autorashodylist.clear();
                           idArrayAutoRashody.clear();

                           SQLiteDatabase db = DB.getReadableDatabase();
                           Cursor cursor = db.rawQuery("SELECT * FROM AUTORASHODY WHERE IDD = " + ID + " ORDER BY ID DESC", null);

                           while (cursor.moveToNext()) {
                               autorashodylist.add(new AUTO_RASHODY(cursor.getString(1),cursor.getString(2),cursor.getFloat(3),cursor.getFloat(4),cursor.getFloat(5),cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getInt(9),cursor.getInt(10), cursor.getInt(11), cursor.getInt(12),cursor.getInt(13), cursor.getInt(14), cursor.getInt(15),"",cursor.getInt(17),cursor.getInt(18), cursor.getInt(19),cursor.getInt(20), ID, cursor.getString(0)));
                               idArrayAutoRashody.add(cursor.getString(0));
                           }

                           ARadapter.notifyDataSetChanged();


                           //ВЫСТАВЛЕНИЕ LISTVIEW ПО ВЫСОТЕ В ЗАВИСИМОСТИ ОТ КОЛИЧЕСТВА ЭЛЕМЕНТОВ
                           if ((Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density))<(560*getActivity().getResources().getDisplayMetrics().density))
                           {
                               ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
                               params.height=Math.round(autorashodylist.size()*90*getActivity().getResources().getDisplayMetrics().density);
                               ListViewAutoRashody.setLayoutParams(params);
                           }

                           else {
                               ViewGroup.LayoutParams params = ListViewAutoRashody.getLayoutParams();
                               params.height=Math.round(560*getActivity().getResources().getDisplayMetrics().density);
                               ListViewAutoRashody.setLayoutParams(params);
                           }



                           addautorashodyAD.dismiss();

                       } catch (Exception ex) {
                           appActivity.Toasts(Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG), "show");
                       }

                   }
               });

               DialogAddAutoRashodyBtn_negative.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       addautorashodyAD.dismiss();

                   }
               });


               addautorashodyAD.show();

           }
       });

       //endregion


       return rootView;
}


}
