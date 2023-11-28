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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;

import java.util.ArrayList;

public class fragment_add_tovar extends Fragment {

  private SQLiteConnect DB;
  private SetDynamicHeightListView SetDListView;

  public fragment_add_tovar() {}

  private ArrayList<Sdelki> kontragentlist;
  private ArrayList<Sdelki> myorglist;
  private ArrayList<Sdelki> uslovijalist;

  private ArrayList idArray1;
  private ArrayList idArray2;
  private ArrayList idArray3;

  AlertDialog.Builder dialogkontragent;
  AlertDialog.Builder dialogmyorg;
  AlertDialog.Builder sdelkauslovija;

  AlertDialog.Builder ad;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override
  public View onCreateView(
      final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_add_tovar, container, false);
    final MainActivity appActivity=(MainActivity)getActivity();
    appActivity.Toasts(new Toast(getActivity()),"hide-all"); //скрыть все Toast

    final ArrayList<Sdelki> list = new ArrayList<Sdelki>();
    final SdelkiListAdapter Zadapter = new SdelkiListAdapter(getActivity(), list);

    SetDListView=new SetDynamicHeightListView();
    DB = new SQLiteConnect(getActivity());

   //region ПЕРЕМЕННЫЕ

    final LinearLayout LinearLayoutTovarSklad = (LinearLayout) rootView.findViewById(R.id.sectiontovarsklad);
    final LinearLayout LinearLayoutTovarCharakteristiki = (LinearLayout) rootView.findViewById(R.id.sectioncharakteristiki);
    final LinearLayout LinearLayoutTovarIMGVIDEO = (LinearLayout) rootView.findViewById(R.id.sectionshowmedia);
    final LinearLayout LinearLayoutTovarPosotionSite = (LinearLayout) rootView.findViewById(R.id.sectiontovarwww);
    final LinearLayout LinearLayoutTovarPohozhie=(LinearLayout)rootView.findViewById(R.id.sectiontovarpohozhie);

    final EditText ETTovarName=(EditText)rootView.findViewById(R.id.tovar_name);
    final EditText ETTovarModel=(EditText)rootView.findViewById(R.id.tovar_model);
    final EditText ETTovarOpisanie=(EditText)rootView.findViewById(R.id.tovar_opisanie);
    final EditText ETTovarOpisanieFull=(EditText)rootView.findViewById(R.id.tovar_opisanie_polnoe);

    final EditText ETTovarPrice=(EditText)rootView.findViewById(R.id.tovar_price);
    final EditText ETTovarZakupPrice=(EditText)rootView.findViewById(R.id.tovar_zakupcena);
    final EditText ETTovarSkladMesto=(EditText)rootView.findViewById(R.id.tovar_skladmesto);
    final EditText ETTovarOstatok=(EditText)rootView.findViewById(R.id.tovar_ostatok);

    final EditText ETTovarCHNAME1=(EditText)rootView.findViewById(R.id.tovar_chname1);
    final EditText ETTovarCHZN1=(EditText)rootView.findViewById(R.id.tovar_zn1);

    final EditText ETTovarCHNAME2=(EditText)rootView.findViewById(R.id.tovar_chname2);
    final EditText ETTovarCHZN2=(EditText)rootView.findViewById(R.id.tovar_zn2);

    final EditText ETTovarCHNAME3=(EditText)rootView.findViewById(R.id.tovar_chname3);
    final EditText ETTovarCHZN3=(EditText)rootView.findViewById(R.id.tovar_zn3);

    final EditText ETTovarCHNAME4=(EditText)rootView.findViewById(R.id.tovar_chname4);
    final EditText ETTovarCHZN4=(EditText)rootView.findViewById(R.id.tovar_zn4);

    final EditText ETTovarCHNAME5=(EditText)rootView.findViewById(R.id.tovar_chname5);
    final EditText ETTovarCHZN5=(EditText)rootView.findViewById(R.id.tovar_zn5);

    final EditText ETTovarCHNAME6=(EditText)rootView.findViewById(R.id.tovar_chname6);
    final EditText ETTovarCHZN6=(EditText)rootView.findViewById(R.id.tovar_zn6);

    final EditText ETTovarCHNAME7=(EditText)rootView.findViewById(R.id.tovar_chname7);
    final EditText ETTovarCHZN7=(EditText)rootView.findViewById(R.id.tovar_zn7);

    final EditText ETTovarCHNAME8=(EditText)rootView.findViewById(R.id.tovar_chname8);
    final EditText ETTovarCHZN8=(EditText)rootView.findViewById(R.id.tovar_zn8);

    final EditText ETTovarCHNAME9=(EditText)rootView.findViewById(R.id.tovar_chname9);
    final EditText ETTovarCHZN9=(EditText)rootView.findViewById(R.id.tovar_zn9);

    final EditText ETTovarCHNAME10=(EditText)rootView.findViewById(R.id.tovar_chname10);
    final EditText ETTovarCHZN10=(EditText)rootView.findViewById(R.id.tovar_zn10);

    final EditText ETTovarCHNAME11=(EditText)rootView.findViewById(R.id.tovar_chname11);
    final EditText ETTovarCHZN11=(EditText)rootView.findViewById(R.id.tovar_zn11);

    final EditText ETTovarCHNAME12=(EditText)rootView.findViewById(R.id.tovar_chname12);
    final EditText ETTovarCHZN12=(EditText)rootView.findViewById(R.id.tovar_zn12);

    final EditText ETTovarCHNAME13=(EditText)rootView.findViewById(R.id.tovar_chname13);
    final EditText ETTovarCHZN13=(EditText)rootView.findViewById(R.id.tovar_zn13);

    final EditText ETTovarCHNAME14=(EditText)rootView.findViewById(R.id.tovar_chname14);
    final EditText ETTovarCHZN14=(EditText)rootView.findViewById(R.id.tovar_zn14);

    final EditText ETTovarCHNAME15=(EditText)rootView.findViewById(R.id.tovar_chname15);
    final EditText ETTovarCHZN15=(EditText)rootView.findViewById(R.id.tovar_zn15);

    final EditText ETTovarCHNAME16=(EditText)rootView.findViewById(R.id.tovar_chname16);
    final EditText ETTovarCHZN16=(EditText)rootView.findViewById(R.id.tovar_zn16);

    final EditText ETTovarCHNAME17=(EditText)rootView.findViewById(R.id.tovar_chname17);
    final EditText ETTovarCHZN17=(EditText)rootView.findViewById(R.id.tovar_zn17);

    final EditText ETTovarCHNAME18=(EditText)rootView.findViewById(R.id.tovar_chname18);
    final EditText ETTovarCHZN18=(EditText)rootView.findViewById(R.id.tovar_zn18);

    final EditText ETTovarCHNAME19=(EditText)rootView.findViewById(R.id.tovar_chname19);
    final EditText ETTovarCHZN19=(EditText)rootView.findViewById(R.id.tovar_zn19);

    final EditText ETTovarCHNAME20=(EditText)rootView.findViewById(R.id.tovar_chname20);
    final EditText ETTovarCHZN20=(EditText)rootView.findViewById(R.id.tovar_zn20);

    final EditText ETTovarIMGkatalog=(EditText)rootView.findViewById(R.id.imgtovara_katalog);
    final EditText ETTovarIMG1=(EditText)rootView.findViewById(R.id.imgtovara1);
    final EditText ETTovarIMG2=(EditText)rootView.findViewById(R.id.imgtovara2);
    final EditText ETTovarIMG3=(EditText)rootView.findViewById(R.id.imgtovara3);
    final EditText ETTovarIMG4=(EditText)rootView.findViewById(R.id.imgtovara4);
    final EditText ETTovarIMG5=(EditText)rootView.findViewById(R.id.imgtovara5);
    final EditText ETTovarIMG6=(EditText)rootView.findViewById(R.id.imgtovara6);
    final EditText ETTovarIMG7=(EditText)rootView.findViewById(R.id.imgtovara7);
    final EditText ETTovarIMG8=(EditText)rootView.findViewById(R.id.imgtovara8);
    final EditText ETTovarIMG9=(EditText)rootView.findViewById(R.id.imgtovara9);
    final EditText ETTovarIMG10=(EditText)rootView.findViewById(R.id.imgtovara10);

    final EditText ETTovarVIDEO1=(EditText)rootView.findViewById(R.id.videotovara1);
    final EditText ETTovarVIDEO2=(EditText)rootView.findViewById(R.id.videotovara2);
    final EditText ETTovarVIDEO3=(EditText)rootView.findViewById(R.id.videotovara3);

    final EditText ETTovarLink=(EditText)rootView.findViewById(R.id.tovarlink);
    final EditText ETTovarCustomCSS=(EditText)rootView.findViewById(R.id.tovarcustomcss);
    final EditText ETTovarNavLevel1=(EditText)rootView.findViewById(R.id.tovarnavlevel1);
    final EditText ETTovarNavLevel1Link=(EditText)rootView.findViewById(R.id.tovarnavlevel1link);
    final EditText ETTovarNavLevel2=(EditText)rootView.findViewById(R.id.tovarnavlevel2);
    final EditText ETTovarNavLevel2Link=(EditText)rootView.findViewById(R.id.tovarnavlevel2link);
    final EditText ETTovarNavLevel3=(EditText)rootView.findViewById(R.id.tovarnavlevel3);
    final EditText ETTovarNavLevel3Link=(EditText)rootView.findViewById(R.id.tovarnavlevel3link);
    final EditText ETTovarZagolovokWWW=(EditText)rootView.findViewById(R.id.tovarzagolovokwww);
    final EditText ETTovarKluchevieSlova=(EditText)rootView.findViewById(R.id.tovarklslova);
    final EditText ETTovarMetaDescription=(EditText)rootView.findViewById(R.id.tovarmetadescription);
    final EditText ETTovarMainFont=(EditText)rootView.findViewById(R.id.tovarmainfont);
    final EditText ETTovarDopFont=(EditText)rootView.findViewById(R.id.tovardopfont);
    final EditText ETTovarZagolovokTextColor=(EditText)rootView.findViewById(R.id.tovarzagolovoktextcolor);
    final EditText ETTovarKategoriiTextColor=(EditText)rootView.findViewById(R.id.tovarkategoriitextcolor);
    final EditText ETTovarPriceTextColor=(EditText)rootView.findViewById(R.id.tovarpricetextcolor);
    final EditText ETTovarBackgroundColor=(EditText)rootView.findViewById(R.id.backgroundcolor);
    final EditText ETTovarNavLinkTextColor=(EditText)rootView.findViewById(R.id.navlinktextcolor);

    final EditText ETShablon=(EditText)rootView.findViewById(R.id.tovar_etswitch_shablon);
    final EditText ETWWW=(EditText)rootView.findViewById(R.id.tovar_etswitch_www);
    final EditText ETValuta=(EditText)rootView.findViewById(R.id.tovar_valuta);
    final EditText ShablonID=(EditText)rootView.findViewById(R.id.shablontovaraid);
    final EditText ETIndexTop=(EditText)rootView.findViewById(R.id.tovar_etswitch_indextop);

    final EditText ETTovarFileName=(EditText)rootView.findViewById(R.id.tovar_file);

    final EditText ETTovarDocsAccordion=(EditText)rootView.findViewById(R.id.tovar_documentsaccordion);
    final EditText ETTovarNalichie=(EditText)rootView.findViewById(R.id.tovar_nalichie);

    final EditText ETTovarCHZName1=(EditText)rootView.findViewById(R.id.chzname1);
    final EditText ETTovarCHZImg1=(EditText)rootView.findViewById(R.id.chzlink1);
    final EditText ETTovarCHZName2=(EditText)rootView.findViewById(R.id.chzname2);
    final EditText ETTovarCHZImg2=(EditText)rootView.findViewById(R.id.chzlink2);
    final EditText ETTovarCHZName3=(EditText)rootView.findViewById(R.id.chzname3);
    final EditText ETTovarCHZImg3=(EditText)rootView.findViewById(R.id.chzlink3);
    final EditText ETTovarCHZName4=(EditText)rootView.findViewById(R.id.chzname4);
    final EditText ETTovarCHZImg4=(EditText)rootView.findViewById(R.id.chzlink4);
    final EditText ETTovarCHZName5=(EditText)rootView.findViewById(R.id.chzname5);
    final EditText ETTovarCHZImg5=(EditText)rootView.findViewById(R.id.chzlink5);
    final EditText ETTovarCHZName6=(EditText)rootView.findViewById(R.id.chzname6);
    final EditText ETTovarCHZImg6=(EditText)rootView.findViewById(R.id.chzlink6);


      final Switch SWitchWWW=(Switch)rootView.findViewById(R.id.switchwww);
    final Switch SWitchShablon=(Switch)rootView.findViewById(R.id.switchshablon);
    final Switch SWitchIndexTop=(Switch)rootView.findViewById(R.id.switchindextop);

    final RadioGroup RadioGroupValuta=(RadioGroup)rootView.findViewById(R.id.radiogroupVALUTA);
    final RadioButton RBRub = (RadioButton) rootView.findViewById(R.id.radio_ruble);
    final RadioButton RBdollar = (RadioButton) rootView.findViewById(R.id.radio_dollar);
    final RadioButton RBeuro = (RadioButton) rootView.findViewById(R.id.radio_euro);


    final Button ButtonFromShablon=(Button)rootView.findViewById(R.id.buttonaddtovarShablon);

    final Button ButtonSaveDB=(Button)rootView.findViewById(R.id.buttonaddtovar);
    final Button ButtonSaveUpload=(Button)rootView.findViewById(R.id.buttonsaveupload);
    final Button ButtonSaveDBDisk=(Button)rootView.findViewById(R.id.buttonsavedisk);


    //endregion

   //region СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН

    final LinearLayout fragRoot = (LinearLayout) rootView.findViewById(R.id.LLContainer);
    fragRoot.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View v) {

            MainActivity rootActivity = (MainActivity) getActivity();
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

        LayoutParams param =
            new LayoutParams(
                Math.round(getActivity().getResources().getDisplayMetrics().density * 350),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        fragRoot.setLayoutParams(param);
      }

      db.close();

    } catch (CursorIndexOutOfBoundsException CursorException) {
      // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
    }

    //endregion

   //region ЗАКРЫТЬ ВСЕ РАСКРЫВАЮЩИЕСЯ LINEARLAYOUT ПРИ СТАРТЕ

    LinearLayoutTovarSklad.setVisibility(View.GONE);
    LinearLayoutTovarCharakteristiki.setVisibility(View.GONE);
    LinearLayoutTovarIMGVIDEO.setVisibility(View.GONE);
    LinearLayoutTovarPosotionSite.setVisibility(View.GONE);
    LinearLayoutTovarPohozhie.setVisibility(View.GONE);

    //endregion

   //region КНОПКА ПОКАЗАТЬ-СКРЫТЬ СЕКЦИЮ ТОВАР-СКЛАД


    final Button ButtonShowTovarSklad = (Button) rootView.findViewById(R.id.buttonshowtovarsklad);
    ButtonShowTovarSklad.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                if (LinearLayoutTovarSklad.getVisibility() == View.VISIBLE) {

                  LinearLayoutTovarSklad.setVisibility(View.GONE);
                  ButtonShowTovarSklad.setText(">");

                } else {
                  LinearLayoutTovarSklad.setVisibility(View.VISIBLE);
                  ButtonShowTovarSklad.setText("^");
                }
              }
            });

    //endregion

   //region КНОПКА ПОКАЗАТЬ-СКРЫТЬ СЕКЦИЮ ХАРАКТЕРИСТИКИ ТОВАРА


    final Button ButtonShowCharakTovara = (Button) rootView.findViewById(R.id.buttonshowcharakteristiki);
    ButtonShowCharakTovara.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                if (LinearLayoutTovarCharakteristiki.getVisibility() == View.VISIBLE) {

                  LinearLayoutTovarCharakteristiki.setVisibility(View.GONE);
                  ButtonShowCharakTovara.setText(">");

                } else {
                  LinearLayoutTovarCharakteristiki.setVisibility(View.VISIBLE);
                  ButtonShowCharakTovara.setText("^");
                }
              }
            });

    //endregion

   //region КНОПКА ПОКАЗАТЬ-СКРЫТЬ СЕКЦИЮ ИЗОБРАЖЕНИЕ И ВИДЕО ТОВАРА


    final Button ButtonShowIMGVIDEOSection = (Button) rootView.findViewById(R.id.buttonshowmedia);
    ButtonShowIMGVIDEOSection.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                if (LinearLayoutTovarIMGVIDEO.getVisibility() == View.VISIBLE) {

                  LinearLayoutTovarIMGVIDEO.setVisibility(View.GONE);
                  ButtonShowIMGVIDEOSection.setText(">");

                } else {
                  LinearLayoutTovarIMGVIDEO.setVisibility(View.VISIBLE);
                  ButtonShowIMGVIDEOSection.setText("^");
                }
              }
            });

    //endregion

   //region КНОПКА ПОКАЗАТЬ-СКРЫТЬ СЕКЦИЮ РАСПОЛОЖЕНИЕ И ОТОБРАЖЕНИЕ НА САЙТЕ


    final Button ButtonShowPositionSite = (Button) rootView.findViewById(R.id.buttonshowwww);
    ButtonShowPositionSite.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                if (LinearLayoutTovarPosotionSite.getVisibility() == View.VISIBLE) {

                  LinearLayoutTovarPosotionSite.setVisibility(View.GONE);
                  ButtonShowPositionSite.setText(">");

                } else {
                  LinearLayoutTovarPosotionSite.setVisibility(View.VISIBLE);
                  ButtonShowPositionSite.setText("^");
                }
              }
            });

    //endregion

   //region КНОПКА ПОКАЗАТЬ-СКРЫТЬ СЕКЦИЮ ПОХОЖИЕ ТОВАРЫ


      final Button ButtonShowPohozhie = (Button) rootView.findViewById(R.id.buttonshowpohozhtovari);
      ButtonShowPohozhie.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      if (LinearLayoutTovarPohozhie.getVisibility() == View.VISIBLE) {

                          LinearLayoutTovarPohozhie.setVisibility(View.GONE);
                          ButtonShowPohozhie.setText(">");

                      } else {
                          LinearLayoutTovarPohozhie.setVisibility(View.VISIBLE);
                          ButtonShowPohozhie.setText("^");
                      }
                  }
              });

      //endregion

   //region РАДИОГРУППА ВАЛЮТА

    RadioGroupValuta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

          case R.id.radio_ruble:
            ETValuta.setText("Рубль");
            break;

          case R.id.radio_dollar:
            ETValuta.setText("Доллар");
            break;

          case R.id.radio_euro:
            ETValuta.setText("Евро");
            break;
          default:
            break;
        }
      }
    });

    //endregion

   //region КНОПКА СОХРАНИТЬ ТОВАР В БАЗУ

    ButtonSaveDB.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {

   //public void AddTOVARI(String IDD,
    // String NAME,
    // String MODEL,
    // String OPISANIE,
    // String PRICE,
    // String VALUTA,
    // String MYCSS,
    // String NAV1,
    // String LINKNAV1,
    // String NAV2,
    // String LINKNAV2,
    // String NAV3,
    // String LINKNAV3,
    // String ZAGOLOVOK,
    // String KEYWORDS,
    // String METADESCRIPTION,
    // String FONT,
    // String DOPFONT,
    // String ZAGOLOVOKTEXTCOLOR,
    // String KATEGORIITEXTCOLOR,
    // String PRICETEXTCOLOR,
    // String BACKGROUNDCOLOR,
    // String NAVTEXTCOLOR,
    // String LINK,
    // String CHNAME1,
    // String CHZNACHENIE1,
    // String CHNAME2,
    // String CHZNACHENIE2,
    // String CHNAME3,
    // String CHZNACHENIE3,
    // String CHNAME4,
    // String CHZNACHENIE4,
    // String CHNAME5,
    // String CHZNACHENIE5,
    // String CHNAME6,
    // String CHZNACHENIE6,
    // String CHNAME7,
    // String CHZNACHENIE7,
    // String CHNAME8,
    // String CHZNACHENIE8,
    // String CHNAME9,
    // String CHZNACHENIE9,
    // String CHNAME10,
    // String CHZNACHENIE10,
    // String CHNAME11,
    // String CHZNACHENIE11,
    // String CHNAME12,
    // String CHZNACHENIE12,
    // String CHNAME13,
    // String CHZNACHENIE13,
    // String CHNAME14,
    // String CHZNACHENIE14,
    // String CHNAME15,
    // String CHZNACHENIE15,
    // String CHNAME16,
    // String CHZNACHENIE16,
    // String CHNAME17,
    // String CHZNACHENIE17,
    // String CHNAME18,
    // String CHZNACHENIE18,
    // String CHNAME19,
    // String CHZNACHENIE19,
    // String CHNAME20,
    // String CHZNACHENIE20,
    // String IMGTOVARA1,
    // String IMGTOVARA2,
    // String IMGTOVARA3,
    // String IMGTOVARA4,
    // String IMGTOVARA5,
    // String IMGTOVARA6,
    // String IMGTOVARA7,
    // String IMGTOVARA8,
    // String IMGTOVARA9,
    // String IMGTOVARA10,
    // String VIDEOTOVARA1,
    // String VIDEOTOVARA2,
    // String VIDEOTOVARA3,
    // String OSTATOK,
    // String SKLADMESTO,
    // String SHABLON,
    // String VISIBILITY,
    // String ZAKUPCENA
    // String FILENAME
    // String OPISANIE_POLNOE
    // String KATALOG_IMAGE)
    // String INDEXTOP {

      try {

        if (SWitchShablon.isChecked()) {ETShablon.setText("Шаблон");}
        else {ETShablon.setText("");}

        if (SWitchWWW.isChecked()) {ETWWW.setText("Выгружать");}
        else {ETWWW.setText("Не выгружать");}

        if (SWitchIndexTop.isChecked()) {ETIndexTop.setText("Да");}
        else {ETIndexTop.setText("Нет");}

        DB.AddTOVARI("",
                ETTovarName.getText().toString(),
                ETTovarModel.getText().toString(),
                ETTovarOpisanie.getText().toString(),
                ETTovarPrice.getText().toString(),
                ETValuta.getText().toString(),
                ETTovarCustomCSS.getText().toString(),
                ETTovarNavLevel1.getText().toString(),
                ETTovarNavLevel1Link.getText().toString(),
                ETTovarNavLevel2.getText().toString(),
                ETTovarNavLevel2Link.getText().toString(),
                ETTovarNavLevel3.getText().toString(),
                ETTovarNavLevel3Link.getText().toString(),
                ETTovarZagolovokWWW.getText().toString(),
                ETTovarKluchevieSlova.getText().toString(),
                ETTovarMetaDescription.getText().toString(),
                ETTovarMainFont.getText().toString(),
                ETTovarDopFont.getText().toString(),
                ETTovarZagolovokTextColor.getText().toString(),
                ETTovarKategoriiTextColor.getText().toString(),
                ETTovarPriceTextColor.getText().toString(),
                ETTovarBackgroundColor.getText().toString(),
                ETTovarNavLinkTextColor.getText().toString(),
                ETTovarLink.getText().toString(),
                ETTovarCHNAME1.getText().toString(),
                ETTovarCHZN1.getText().toString(),
                ETTovarCHNAME2.getText().toString(),
                ETTovarCHZN2.getText().toString(),
                ETTovarCHNAME3.getText().toString(),
                ETTovarCHZN3.getText().toString(),
                ETTovarCHNAME4.getText().toString(),
                ETTovarCHZN4.getText().toString(),
                ETTovarCHNAME5.getText().toString(),
                ETTovarCHZN5.getText().toString(),
                ETTovarCHNAME6.getText().toString(),
                ETTovarCHZN6.getText().toString(),
                ETTovarCHNAME7.getText().toString(),
                ETTovarCHZN7.getText().toString(),
                ETTovarCHNAME8.getText().toString(),
                ETTovarCHZN8.getText().toString(),
                ETTovarCHNAME9.getText().toString(),
                ETTovarCHZN9.getText().toString(),
                ETTovarCHNAME10.getText().toString(),
                ETTovarCHZN10.getText().toString(),
                ETTovarCHNAME11.getText().toString(),
                ETTovarCHZN11.getText().toString(),
                ETTovarCHNAME12.getText().toString(),
                ETTovarCHZN12.getText().toString(),
                ETTovarCHNAME13.getText().toString(),
                ETTovarCHZN13.getText().toString(),
                ETTovarCHNAME14.getText().toString(),
                ETTovarCHZN14.getText().toString(),
                ETTovarCHNAME15.getText().toString(),
                ETTovarCHZN15.getText().toString(),
                ETTovarCHNAME16.getText().toString(),
                ETTovarCHZN16.getText().toString(),
                ETTovarCHNAME17.getText().toString(),
                ETTovarCHZN17.getText().toString(),
                ETTovarCHNAME18.getText().toString(),
                ETTovarCHZN18.getText().toString(),
                ETTovarCHNAME19.getText().toString(),
                ETTovarCHZN19.getText().toString(),
                ETTovarCHNAME20.getText().toString(),
                ETTovarCHZN20.getText().toString(),
                ETTovarIMG1.getText().toString(),
                ETTovarIMG2.getText().toString(),
                ETTovarIMG3.getText().toString(),
                ETTovarIMG4.getText().toString(),
                ETTovarIMG5.getText().toString(),
                ETTovarIMG6.getText().toString(),
                ETTovarIMG7.getText().toString(),
                ETTovarIMG8.getText().toString(),
                ETTovarIMG9.getText().toString(),
                ETTovarIMG10.getText().toString(),
                ETTovarVIDEO1.getText().toString(),
                ETTovarVIDEO2.getText().toString(),
                ETTovarVIDEO3.getText().toString(),
                ETTovarOstatok.getText().toString(),
                ETTovarSkladMesto.getText().toString(),
                ETShablon.getText().toString(),
                ETWWW.getText().toString(),
                ETTovarZakupPrice.getText().toString(),
                ETTovarFileName.getText().toString(),
                ETTovarOpisanieFull.getText().toString(),
                ETTovarIMGkatalog.getText().toString(),
                ETIndexTop.getText().toString()
        );

        MainActivity rootActivity = (MainActivity) getActivity();
        rootActivity.tovarclose();

      }
      catch (SQLException mSQLException) {
        Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
      }

              }
            });
      //endregion

   //region КНОПКА СОХРАНИТЬ ТОВАР В БАЗУ И ВЫГРУЗИТЬ НА ДИСК

    ButtonSaveDBDisk.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        try {


          if (SWitchShablon.isChecked()) {ETShablon.setText("Шаблон");}
          else {ETShablon.setText("");}

          if (SWitchWWW.isChecked()) {ETWWW.setText("Выгружать");}
          else {ETWWW.setText("Не выгружать");}

          if (SWitchIndexTop.isChecked()) {ETIndexTop.setText("Да");}
          else {ETIndexTop.setText("Нет");}

          DB.AddTOVARI("",
                  ETTovarName.getText().toString(),
                  ETTovarModel.getText().toString(),
                  ETTovarOpisanie.getText().toString(),
                  ETTovarPrice.getText().toString(),
                  ETValuta.getText().toString(),
                  ETTovarCustomCSS.getText().toString(),
                  ETTovarNavLevel1.getText().toString(),
                  ETTovarNavLevel1Link.getText().toString(),
                  ETTovarNavLevel2.getText().toString(),
                  ETTovarNavLevel2Link.getText().toString(),
                  ETTovarNavLevel3.getText().toString(),
                  ETTovarNavLevel3Link.getText().toString(),
                  ETTovarZagolovokWWW.getText().toString(),
                  ETTovarKluchevieSlova.getText().toString(),
                  ETTovarMetaDescription.getText().toString(),
                  ETTovarMainFont.getText().toString(),
                  ETTovarDopFont.getText().toString(),
                  ETTovarZagolovokTextColor.getText().toString(),
                  ETTovarKategoriiTextColor.getText().toString(),
                  ETTovarPriceTextColor.getText().toString(),
                  ETTovarBackgroundColor.getText().toString(),
                  ETTovarNavLinkTextColor.getText().toString(),
                  ETTovarLink.getText().toString(),
                  ETTovarCHNAME1.getText().toString(),
                  ETTovarCHZN1.getText().toString(),
                  ETTovarCHNAME2.getText().toString(),
                  ETTovarCHZN2.getText().toString(),
                  ETTovarCHNAME3.getText().toString(),
                  ETTovarCHZN3.getText().toString(),
                  ETTovarCHNAME4.getText().toString(),
                  ETTovarCHZN4.getText().toString(),
                  ETTovarCHNAME5.getText().toString(),
                  ETTovarCHZN5.getText().toString(),
                  ETTovarCHNAME6.getText().toString(),
                  ETTovarCHZN6.getText().toString(),
                  ETTovarCHNAME7.getText().toString(),
                  ETTovarCHZN7.getText().toString(),
                  ETTovarCHNAME8.getText().toString(),
                  ETTovarCHZN8.getText().toString(),
                  ETTovarCHNAME9.getText().toString(),
                  ETTovarCHZN9.getText().toString(),
                  ETTovarCHNAME10.getText().toString(),
                  ETTovarCHZN10.getText().toString(),
                  ETTovarCHNAME11.getText().toString(),
                  ETTovarCHZN11.getText().toString(),
                  ETTovarCHNAME12.getText().toString(),
                  ETTovarCHZN12.getText().toString(),
                  ETTovarCHNAME13.getText().toString(),
                  ETTovarCHZN13.getText().toString(),
                  ETTovarCHNAME14.getText().toString(),
                  ETTovarCHZN14.getText().toString(),
                  ETTovarCHNAME15.getText().toString(),
                  ETTovarCHZN15.getText().toString(),
                  ETTovarCHNAME16.getText().toString(),
                  ETTovarCHZN16.getText().toString(),
                  ETTovarCHNAME17.getText().toString(),
                  ETTovarCHZN17.getText().toString(),
                  ETTovarCHNAME18.getText().toString(),
                  ETTovarCHZN18.getText().toString(),
                  ETTovarCHNAME19.getText().toString(),
                  ETTovarCHZN19.getText().toString(),
                  ETTovarCHNAME20.getText().toString(),
                  ETTovarCHZN20.getText().toString(),
                  ETTovarIMG1.getText().toString(),
                  ETTovarIMG2.getText().toString(),
                  ETTovarIMG3.getText().toString(),
                  ETTovarIMG4.getText().toString(),
                  ETTovarIMG5.getText().toString(),
                  ETTovarIMG6.getText().toString(),
                  ETTovarIMG7.getText().toString(),
                  ETTovarIMG8.getText().toString(),
                  ETTovarIMG9.getText().toString(),
                  ETTovarIMG10.getText().toString(),
                  ETTovarVIDEO1.getText().toString(),
                  ETTovarVIDEO2.getText().toString(),
                  ETTovarVIDEO3.getText().toString(),
                  ETTovarOstatok.getText().toString(),
                  ETTovarSkladMesto.getText().toString(),
                  ETShablon.getText().toString(),
                  ETWWW.getText().toString(),
                  ETTovarZakupPrice.getText().toString(),
                  ETTovarFileName.getText().toString(),
                  ETTovarOpisanieFull.getText().toString(),
                  ETTovarIMGkatalog.getText().toString(),
                  ETIndexTop.getText().toString()
          );

          String s1= "<?php \r\n";
          String s2= "$name=\"" + ETTovarName.getText().toString() + "\";\r\n";
          String s3= "$model=\"" + ETTovarModel.getText().toString() + "\";\r\n";
          String s4= "$price=\"" + ETTovarPrice.getText().toString() + "\";\r\n?>\r\n";
          String s5= "<?php require $_SERVER['DOCUMENT_ROOT'].\"/header.tpl\"; ?>\r\n";
          String s6= "<title>" + ETTovarZagolovokWWW.getText().toString() + "</title>\r\n";
          String s7= "<link href=\"/css/customstyle.css\" rel=\"stylesheet\">\r\n";

          final String s8;

          if (! ETTovarCustomCSS.getText().toString().equals("")) { s8= "<link href=\"" + ETTovarCustomCSS.getText().toString() + "\" rel=\"stylesheet\">\r\n"; } else { s8=""; }

          String s9= "<meta name=\"keywords\" content=\""+ETTovarKluchevieSlova.getText().toString()+"\">\r\n";
          String s10= "<meta name=\"description\" content=\"" + ETTovarMetaDescription.getText().toString() + "\"></head>\r\n";
          String s11= "<body style=\"font-family:"+ ETTovarMainFont.getText().toString()+";\">\r\n";
          String s12= "<?php require $_SERVER['DOCUMENT_ROOT'].\"/navigation.tpl\"; ?>\r\n";
          String s13= "<!-- СКРИПТ ЧТЕНИЯ КУРСА ВАЛЮТ--><?php $file1=$_SERVER['DOCUMENT_ROOT'].\"/dollar.ini\"; $fd1=fopen($file1,\"r\"; $dollar=fgets($fd1,6; fclose ($fd1; $file2=$_SERVER['DOCUMENT_ROOT'].\"/euro.ini\"; $fd2=fopen($file2,\"r\"; $euro=fgets($fd2,6; fclose ($fd2; ?>\r\n<!-- КОНЕЦ СКРИПТ ЧТЕНИЯ КУРСА ВАЛЮТ-->\r\n";
          String s14= "<!--ОСНОВНОЙ КОНТЕНТ СТРАНИЦЫ--><div class=\"container\" style=\"margin-top:92px;\"><p><a href=\"index.php\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">Главная</a>\r\n";

          final String s15;
          final String s16;
          final String s17;
          if (! ETTovarNavLevel1.getText().toString().equals("")) { s15 = " -> <a href=\""+ ETTovarNavLevel1Link.getText().toString() + "\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">"+ ETTovarNavLevel1.getText().toString() + "</a>\r\n"; } else {s15="";}
          if (! ETTovarNavLevel2.getText().toString().equals("")) { s16 = " -> <a href=\"" + ETTovarNavLevel2Link.getText().toString() + "\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">" + ETTovarNavLevel2.getText().toString() + "</a>\r\n"; } else {s16="";}
          if (! ETTovarNavLevel3.getText().toString().equals("")) { s17 = " -> <a href=\"" + ETTovarNavLevel3Link.getText().toString() + "\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">" + ETTovarNavLevel1.getText().toString() + "</a>\r\n"; } else {s17="";}

          String s18 = "</p><div class=\"row featurette\" style=\"margin-top:20px;\"><div class=\"col-md-7 col-md-push-5\"><h3 class=\"featurette-heading\" style=\"color:" + ETTovarZagolovokTextColor.getText().toString() + ";\">" + ETTovarName.getText().toString() + "<br><span style=\"font-family:" + ETTovarMainFont.getText().toString() + "; font-size:inherit;\">" + ETTovarModel.getText().toString() + "</span></h3>\r\n";
          String s19 = "<p class=\"lead\">"+ETTovarOpisanie.getText().toString()+"</p>";
          String s20 = "<p class=\"lead\">Цена: <span style=\"font-family:" + ETTovarMainFont.getText().toString() + "; font-size:inherit; font-weight:bold; color:" + ETTovarPriceTextColor.getText().toString() + ";\">\r\n";

          final String s21;
          final String s22;
          final String s23;
          if (ETValuta.getText().toString().equals("Рубль") || ETValuta.getText().toString().equals("")) { s21= "<?php echo $price; ?></span> рублей</p>"; } else {s21="";}
          if (ETValuta.getText().toString().equals("Доллар")) { s22 = "<?php echo ceil($price*$dollar; ?></span> рублей</p>\r\n"; } else {s22="";}
          if (ETValuta.getText().toString().equals("Евро")) { s23 = "<?php echo ceil($price*$euro; ?></span> рублей</p>\r\n"; } else {s23="";}

          final String s24;
          if (ETValuta.getText().toString().equals("Доллар") || ETValuta.getText().toString().equals("Евро")) {s24= "<p>Внимание! Цена на этот товар меняется ежедневно в зависимости от курса доллара/евро ЦБ РФ</p>\r\n"; } else {s24="";}

          final String s24_;
          if (! ETTovarOpisanieFull.getText().toString().equals("")) { s24_= "<hr><h4>Описание:</h4><br>"+ETTovarOpisanieFull.getText().toString()+"<hr>\r\n"; } else {s24_="";}


          String s25= "<hr><h4>Характеристики:</h4><br><table class=\"table table-striped\">\r\n";

          final String s26;
          final String s27;
          final String s28;
          final String s29;
          final String s30;
          final String s31;
          final String s32;
          final String s33;
          final String s34;
          final String s35;
          final String s36;
          final String s37;
          final String s38;
          final String s39;
          final String s40;
          final String s41;
          final String s42;
          final String s43;
          final String s44;
          final String s45;
          if ((! ETTovarCHNAME1.getText().toString().equals("")) || (! ETTovarCHZN1.getText().toString().equals(""))) { s26= "<tr><td><p>" + ETTovarCHNAME1.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN1.getText().toString() + "</span></p></td></tr>\r\n"; } else {s26="";}
          if ((! ETTovarCHNAME2.getText().toString().equals("")) || (! ETTovarCHZN2.getText().toString().equals(""))) { s27= "<tr><td><p>" + ETTovarCHNAME2.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN2.getText().toString() + "</span></p></td></tr>\r\n"; } else {s27="";}
          if ((! ETTovarCHNAME3.getText().toString().equals("")) || (! ETTovarCHZN3.getText().toString().equals(""))) { s28= "<tr><td><p>" + ETTovarCHNAME3.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN3.getText().toString() + "</span></p></td></tr>\r\n"; } else {s28="";}
          if ((! ETTovarCHNAME4.getText().toString().equals("")) || (! ETTovarCHZN4.getText().toString().equals(""))) { s29= "<tr><td><p>" + ETTovarCHNAME4.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN4.getText().toString() + "</span></p></td></tr>\r\n"; } else {s29="";}
          if ((! ETTovarCHNAME5.getText().toString().equals("")) || (! ETTovarCHZN5.getText().toString().equals(""))) { s30= "<tr><td><p>" + ETTovarCHNAME5.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN5.getText().toString() + "</span></p></td></tr>\r\n"; } else {s30="";}
          if ((! ETTovarCHNAME6.getText().toString().equals("")) || (! ETTovarCHZN6.getText().toString().equals(""))) { s31= "<tr><td><p>" + ETTovarCHNAME6.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN6.getText().toString() + "</span></p></td></tr>\r\n"; } else {s31="";}
          if ((! ETTovarCHNAME7.getText().toString().equals("")) || (! ETTovarCHZN7.getText().toString().equals(""))) { s32= "<tr><td><p>" + ETTovarCHNAME7.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN7.getText().toString() + "</span></p></td></tr>\r\n"; } else {s32="";}
          if ((! ETTovarCHNAME8.getText().toString().equals("")) || (! ETTovarCHZN8.getText().toString().equals(""))) { s33= "<tr><td><p>" + ETTovarCHNAME8.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN8.getText().toString() + "</span></p></td></tr>\r\n"; } else {s33="";}
          if ((! ETTovarCHNAME9.getText().toString().equals("")) || (! ETTovarCHZN9.getText().toString().equals(""))) { s34= "<tr><td><p>" + ETTovarCHNAME9.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN9.getText().toString() + "</span></p></td></tr>\r\n"; } else {s34="";}
          if ((! ETTovarCHNAME10.getText().toString().equals("")) || (! ETTovarCHZN10.getText().toString().equals(""))) { s35= "<tr><td><p>" + ETTovarCHNAME10.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN10.getText().toString() + "</span></p></td></tr>\r\n"; } else {s35="";}
          if ((! ETTovarCHNAME11.getText().toString().equals("")) || (! ETTovarCHZN11.getText().toString().equals(""))) { s36= "<tr><td><p>" + ETTovarCHNAME11.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN11.getText().toString() + "</span></p></td></tr>\r\n"; } else {s36="";}
          if ((! ETTovarCHNAME12.getText().toString().equals("")) || (! ETTovarCHZN12.getText().toString().equals(""))) { s37= "<tr><td><p>" + ETTovarCHNAME12.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN12.getText().toString() + "</span></p></td></tr>\r\n"; } else {s37="";}
          if ((! ETTovarCHNAME13.getText().toString().equals("")) || (! ETTovarCHZN13.getText().toString().equals(""))) { s38= "<tr><td><p>" + ETTovarCHNAME13.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN13.getText().toString() + "</span></p></td></tr>\r\n"; } else {s38="";}
          if ((! ETTovarCHNAME14.getText().toString().equals("")) || (! ETTovarCHZN14.getText().toString().equals(""))) { s39= "<tr><td><p>" + ETTovarCHNAME14.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN14.getText().toString() + "</span></p></td></tr>\r\n"; } else {s39="";}
          if ((! ETTovarCHNAME15.getText().toString().equals("")) || (! ETTovarCHZN15.getText().toString().equals(""))) { s40= "<tr><td><p>" + ETTovarCHNAME15.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN15.getText().toString() + "</span></p></td></tr>\r\n"; } else {s40="";}
          if ((! ETTovarCHNAME16.getText().toString().equals("")) || (! ETTovarCHZN16.getText().toString().equals(""))) { s41= "<tr><td><p>" + ETTovarCHNAME16.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN16.getText().toString() + "</span></p></td></tr>\r\n"; } else {s41="";}
          if ((! ETTovarCHNAME17.getText().toString().equals("")) || (! ETTovarCHZN17.getText().toString().equals(""))) { s42= "<tr><td><p>" + ETTovarCHNAME17.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN17.getText().toString() + "</span></p></td></tr>\r\n"; } else {s42="";}
          if ((! ETTovarCHNAME18.getText().toString().equals("")) || (! ETTovarCHZN18.getText().toString().equals(""))) { s43= "<tr><td><p>" + ETTovarCHNAME18.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN18.getText().toString() + "</span></p></td></tr>\r\n"; } else {s43="";}
          if ((! ETTovarCHNAME19.getText().toString().equals("")) || (! ETTovarCHZN19.getText().toString().equals(""))) { s44= "<tr><td><p>" + ETTovarCHNAME19.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN19.getText().toString() + "</span></p></td></tr>\r\n"; } else {s44="";}
          if ((! ETTovarCHNAME20.getText().toString().equals("")) || (! ETTovarCHZN20.getText().toString().equals(""))) { s45= "<tr><td><p>" + ETTovarCHNAME20.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN20.getText().toString() + "</span></p></td></tr>\r\n"; } else {s45="";}


          String s46= "</table></div><div class=\"col-md-5 col-md-pull-7\">";

          final String s47;
          final String s48;
          final String s49;
          final String s50;
          final String s51;
          final String s52;
          final String s53;
          final String s54;
          final String s55;
          final String s56;
          final String s57;
          final String s58;
          final String s59;
          if (! ETTovarIMG1.getText().toString().equals("")) { s47= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG1.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s47="";}
          if (! ETTovarIMG2.getText().toString().equals("")) { s48= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG2.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s48="";}
          if (! ETTovarIMG3.getText().toString().equals("")) { s49= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG3.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s49="";}
          if (! ETTovarIMG4.getText().toString().equals("")) { s50= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG4.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s50="";}
          if (! ETTovarIMG5.getText().toString().equals("")) { s51= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG5.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s51="";}
          if (! ETTovarIMG6.getText().toString().equals("")) { s52= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG6.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s52="";}
          if (! ETTovarIMG7.getText().toString().equals("")) { s53= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG7.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s53="";}
          if (! ETTovarIMG8.getText().toString().equals("")) { s54= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG8.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s54="";}
          if (! ETTovarIMG9.getText().toString().equals("")) { s55= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG9.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s55="";}
          if (! ETTovarIMG10.getText().toString().equals("")) { s56= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG10.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; }else {s56="";}


          if (! ETTovarVIDEO1.getText().toString().equals("")) { s57= "<video class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarVIDEO1.getText().toString() + "\" controls alt=\"" + ETTovarName.getText().toString() +" "+ ETTovarModel.getText().toString() + "\"></video><br>\r\n"; } else {s57="";}
          if (! ETTovarVIDEO2.getText().toString().equals("")) { s58= "<video class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarVIDEO2.getText().toString() + "\" controls alt=\"" + ETTovarName.getText().toString() +" "+ ETTovarModel.getText().toString() + "\"></video><br>\r\n"; } else {s58="";}
          if (! ETTovarVIDEO3.getText().toString().equals("")) { s59= "<video class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarVIDEO3.getText().toString() + "\" controls alt=\"" + ETTovarName.getText().toString() +" "+ ETTovarModel.getText().toString() + "\"></video><br>\r\n"; } else {s59="";}



          String s60= "</div></div></div><!--КОНЕЦ ОСНОВНОЙ КОНТЕНТ СТРАНИЦЫ--><?php require $_SERVER['DOCUMENT_ROOT'].\"/footer.tpl\"; ?><?php require $_SERVER['DOCUMENT_ROOT'].\"/footerscripts.tpl\"; ?>\r\n";


          String TovarPHPCode=s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12+s13+s14+s15+s16+s17+s18+s19+s20+s21+s22+s23+s24+s24_+s25+s26+s27+s28+s29+s30+s31+s32+s33+s34+s35+s36+s37+s38+s39+s40+s41+s42+s43+s44+s45+s46+s47+s48+s49+s50+s51+s52+s53+s54+s55+s56+s57+s58+s59+s60;

          MainActivity rootActivity = (MainActivity) getActivity();
          //rootActivity.savePHPFile(String FolderName, String SubFolderName, String FileName, String str)
          rootActivity.savePHPFile("Мастерконд", "", ETTovarFileName.getText().toString(), TovarPHPCode);
        }
        catch (SQLException mSQLException) {
          Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
        }

      }
    });

    //endregion

   //region КНОПКА СОХРАНИТЬ ТОВАР В БАЗУ И ВЫГРУЗИТЬ НА САЙТ

    ButtonSaveUpload.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        try {


          if (SWitchShablon.isChecked()) {ETShablon.setText("Шаблон");}
          else {ETShablon.setText("");}

          if (SWitchWWW.isChecked()) {ETWWW.setText("Выгружать");}
          else {ETWWW.setText("Не выгружать");}

          if (SWitchIndexTop.isChecked()) {ETIndexTop.setText("Да");}
          else {ETIndexTop.setText("Нет");}

          DB.AddTOVARI("",
                  ETTovarName.getText().toString(),
                  ETTovarModel.getText().toString(),
                  ETTovarOpisanie.getText().toString(),
                  ETTovarPrice.getText().toString(),
                  ETValuta.getText().toString(),
                  ETTovarCustomCSS.getText().toString(),
                  ETTovarNavLevel1.getText().toString(),
                  ETTovarNavLevel1Link.getText().toString(),
                  ETTovarNavLevel2.getText().toString(),
                  ETTovarNavLevel2Link.getText().toString(),
                  ETTovarNavLevel3.getText().toString(),
                  ETTovarNavLevel3Link.getText().toString(),
                  ETTovarZagolovokWWW.getText().toString(),
                  ETTovarKluchevieSlova.getText().toString(),
                  ETTovarMetaDescription.getText().toString(),
                  ETTovarMainFont.getText().toString(),
                  ETTovarDopFont.getText().toString(),
                  ETTovarZagolovokTextColor.getText().toString(),
                  ETTovarKategoriiTextColor.getText().toString(),
                  ETTovarPriceTextColor.getText().toString(),
                  ETTovarBackgroundColor.getText().toString(),
                  ETTovarNavLinkTextColor.getText().toString(),
                  ETTovarLink.getText().toString(),
                  ETTovarCHNAME1.getText().toString(),
                  ETTovarCHZN1.getText().toString(),
                  ETTovarCHNAME2.getText().toString(),
                  ETTovarCHZN2.getText().toString(),
                  ETTovarCHNAME3.getText().toString(),
                  ETTovarCHZN3.getText().toString(),
                  ETTovarCHNAME4.getText().toString(),
                  ETTovarCHZN4.getText().toString(),
                  ETTovarCHNAME5.getText().toString(),
                  ETTovarCHZN5.getText().toString(),
                  ETTovarCHNAME6.getText().toString(),
                  ETTovarCHZN6.getText().toString(),
                  ETTovarCHNAME7.getText().toString(),
                  ETTovarCHZN7.getText().toString(),
                  ETTovarCHNAME8.getText().toString(),
                  ETTovarCHZN8.getText().toString(),
                  ETTovarCHNAME9.getText().toString(),
                  ETTovarCHZN9.getText().toString(),
                  ETTovarCHNAME10.getText().toString(),
                  ETTovarCHZN10.getText().toString(),
                  ETTovarCHNAME11.getText().toString(),
                  ETTovarCHZN11.getText().toString(),
                  ETTovarCHNAME12.getText().toString(),
                  ETTovarCHZN12.getText().toString(),
                  ETTovarCHNAME13.getText().toString(),
                  ETTovarCHZN13.getText().toString(),
                  ETTovarCHNAME14.getText().toString(),
                  ETTovarCHZN14.getText().toString(),
                  ETTovarCHNAME15.getText().toString(),
                  ETTovarCHZN15.getText().toString(),
                  ETTovarCHNAME16.getText().toString(),
                  ETTovarCHZN16.getText().toString(),
                  ETTovarCHNAME17.getText().toString(),
                  ETTovarCHZN17.getText().toString(),
                  ETTovarCHNAME18.getText().toString(),
                  ETTovarCHZN18.getText().toString(),
                  ETTovarCHNAME19.getText().toString(),
                  ETTovarCHZN19.getText().toString(),
                  ETTovarCHNAME20.getText().toString(),
                  ETTovarCHZN20.getText().toString(),
                  ETTovarIMG1.getText().toString(),
                  ETTovarIMG2.getText().toString(),
                  ETTovarIMG3.getText().toString(),
                  ETTovarIMG4.getText().toString(),
                  ETTovarIMG5.getText().toString(),
                  ETTovarIMG6.getText().toString(),
                  ETTovarIMG7.getText().toString(),
                  ETTovarIMG8.getText().toString(),
                  ETTovarIMG9.getText().toString(),
                  ETTovarIMG10.getText().toString(),
                  ETTovarVIDEO1.getText().toString(),
                  ETTovarVIDEO2.getText().toString(),
                  ETTovarVIDEO3.getText().toString(),
                  ETTovarOstatok.getText().toString(),
                  ETTovarSkladMesto.getText().toString(),
                  ETShablon.getText().toString(),
                  ETWWW.getText().toString(),
                  ETTovarZakupPrice.getText().toString(),
                  ETTovarFileName.getText().toString(),
                  ETTovarOpisanieFull.getText().toString(),
                  ETTovarIMGkatalog.getText().toString(),
                  ETIndexTop.getText().toString()
          );

          String s1= "<?php \r\n";
          String s2= "$name=\"" + ETTovarName.getText().toString() + "\";\r\n";
          String s3= "$model=\"" + ETTovarModel.getText().toString() + "\";\r\n";
          String s4= "$price=\"" + ETTovarPrice.getText().toString() + "\";\r\n?>\r\n";
          String s5= "<?php require $_SERVER['DOCUMENT_ROOT'].\"/header.tpl\"; ?>\r\n";
          String s6= "<title>" + ETTovarZagolovokWWW.getText().toString() + "</title>\r\n";
          String s7= "<link href=\"/css/customstyle.css\" rel=\"stylesheet\">\r\n";

          final String s8;

          if (! ETTovarCustomCSS.getText().toString().equals("")) { s8= "<link href=\"" + ETTovarCustomCSS.getText().toString() + "\" rel=\"stylesheet\">\r\n"; } else { s8=""; }

          String s9= "<meta name=\"keywords\" content=\""+ETTovarKluchevieSlova.getText().toString()+"\">\r\n";
          String s10= "<meta name=\"description\" content=\"" + ETTovarMetaDescription.getText().toString() + "\"></head>\r\n";
          String s11= "<body style=\"font-family:"+ ETTovarMainFont.getText().toString()+";\">\r\n";
          String s12= "<?php require $_SERVER['DOCUMENT_ROOT'].\"/navigation.tpl\"; ?>\r\n";
          String s13= "<!-- СКРИПТ ЧТЕНИЯ КУРСА ВАЛЮТ--><?php $file1=$_SERVER['DOCUMENT_ROOT'].\"/dollar.ini\"; $fd1=fopen($file1,\"r\"; $dollar=fgets($fd1,6; fclose ($fd1; $file2=$_SERVER['DOCUMENT_ROOT'].\"/euro.ini\"; $fd2=fopen($file2,\"r\"; $euro=fgets($fd2,6; fclose ($fd2; ?>\r\n<!-- КОНЕЦ СКРИПТ ЧТЕНИЯ КУРСА ВАЛЮТ-->\r\n";
          String s14= "<!--ОСНОВНОЙ КОНТЕНТ СТРАНИЦЫ--><div class=\"container\" style=\"margin-top:92px;\"><p><a href=\"index.php\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">Главная</a>\r\n";

          final String s15;
          final String s16;
          final String s17;
          if (! ETTovarNavLevel1.getText().toString().equals("")) { s15 = " -> <a href=\""+ ETTovarNavLevel1Link.getText().toString() + "\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">"+ ETTovarNavLevel1.getText().toString() + "</a>\r\n"; } else {s15="";}
          if (! ETTovarNavLevel2.getText().toString().equals("")) { s16 = " -> <a href=\"" + ETTovarNavLevel2Link.getText().toString() + "\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">" + ETTovarNavLevel2.getText().toString() + "</a>\r\n"; } else {s16="";}
          if (! ETTovarNavLevel3.getText().toString().equals("")) { s17 = " -> <a href=\"" + ETTovarNavLevel3Link.getText().toString() + "\" style=\"color:" + ETTovarNavLinkTextColor.getText().toString() + ";\">" + ETTovarNavLevel1.getText().toString() + "</a>\r\n"; } else {s17="";}

          String s18 = "</p><div class=\"row featurette\" style=\"margin-top:20px;\"><div class=\"col-md-7 col-md-push-5\"><h3 class=\"featurette-heading\" style=\"color:" + ETTovarZagolovokTextColor.getText().toString() + ";\">" + ETTovarName.getText().toString() + "<br><span style=\"font-family:" + ETTovarMainFont.getText().toString() + "; font-size:inherit;\">" + ETTovarModel.getText().toString() + "</span></h3>\r\n";
          String s19 = "<p class=\"lead\">"+ETTovarOpisanie.getText().toString()+"</p>";
          String s20 = "<p class=\"lead\">Цена: <span style=\"font-family:" + ETTovarMainFont.getText().toString() + "; font-size:inherit; font-weight:bold; color:" + ETTovarPriceTextColor.getText().toString() + ";\">\r\n";

          final String s21;
          final String s22;
          final String s23;
          if (ETValuta.getText().toString().equals("Рубль") || ETValuta.getText().toString().equals("")) { s21= "<?php echo $price; ?></span> рублей</p>"; }  else {s21="";}
          if (ETValuta.getText().toString().equals("Доллар")) { s22 = "<?php echo ceil($price*$dollar; ?></span> рублей</p>\r\n"; } else {s22="";}
          if (ETValuta.getText().toString().equals("Евро")) { s23 = "<?php echo ceil($price*$euro; ?></span> рублей</p>\r\n"; } else {s23="";}

          final String s24;
          if (ETValuta.getText().toString().equals("Доллар") || ETValuta.getText().toString().equals("Евро")) {s24= "<p>Внимание! Цена на этот товар меняется ежедневно в зависимости от курса доллара/евро ЦБ РФ</p>\r\n"; } else {s24="";}

          final String s24_;
          if (! ETTovarOpisanieFull.getText().toString().equals("")) { s24_= "<hr><h4>Описание:</h4><br>"+ETTovarOpisanieFull.getText().toString()+"<hr>\r\n"; } else {s24_="";}

            String s25= "<hr><h4>Характеристики:</h4><br><table class=\"table table-striped\">\r\n";

          final String s26;
          final String s27;
          final String s28;
          final String s29;
          final String s30;
          final String s31;
          final String s32;
          final String s33;
          final String s34;
          final String s35;
          final String s36;
          final String s37;
          final String s38;
          final String s39;
          final String s40;
          final String s41;
          final String s42;
          final String s43;
          final String s44;
          final String s45;
          if ((! ETTovarCHNAME1.getText().toString().equals("")) || (! ETTovarCHZN1.getText().toString().equals(""))) { s26= "<tr><td><p>" + ETTovarCHNAME1.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN1.getText().toString() + "</span></p></td></tr>\r\n"; } else {s26="";}
          if ((! ETTovarCHNAME2.getText().toString().equals("")) || (! ETTovarCHZN2.getText().toString().equals(""))) { s27= "<tr><td><p>" + ETTovarCHNAME2.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN2.getText().toString() + "</span></p></td></tr>\r\n"; } else {s27="";}
          if ((! ETTovarCHNAME3.getText().toString().equals("")) || (! ETTovarCHZN3.getText().toString().equals(""))) { s28= "<tr><td><p>" + ETTovarCHNAME3.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN3.getText().toString() + "</span></p></td></tr>\r\n"; } else {s28="";}
          if ((! ETTovarCHNAME4.getText().toString().equals("")) || (! ETTovarCHZN4.getText().toString().equals(""))) { s29= "<tr><td><p>" + ETTovarCHNAME4.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN4.getText().toString() + "</span></p></td></tr>\r\n"; } else {s29="";}
          if ((! ETTovarCHNAME5.getText().toString().equals("")) || (! ETTovarCHZN5.getText().toString().equals(""))) { s30= "<tr><td><p>" + ETTovarCHNAME5.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN5.getText().toString() + "</span></p></td></tr>\r\n"; } else {s30="";}
          if ((! ETTovarCHNAME6.getText().toString().equals("")) || (! ETTovarCHZN6.getText().toString().equals(""))) { s31= "<tr><td><p>" + ETTovarCHNAME6.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN6.getText().toString() + "</span></p></td></tr>\r\n"; } else {s31="";}
          if ((! ETTovarCHNAME7.getText().toString().equals("")) || (! ETTovarCHZN7.getText().toString().equals(""))) { s32= "<tr><td><p>" + ETTovarCHNAME7.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN7.getText().toString() + "</span></p></td></tr>\r\n"; } else {s32="";}
          if ((! ETTovarCHNAME8.getText().toString().equals("")) || (! ETTovarCHZN8.getText().toString().equals(""))) { s33= "<tr><td><p>" + ETTovarCHNAME8.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN8.getText().toString() + "</span></p></td></tr>\r\n"; } else {s33="";}
          if ((! ETTovarCHNAME9.getText().toString().equals("")) || (! ETTovarCHZN9.getText().toString().equals(""))) { s34= "<tr><td><p>" + ETTovarCHNAME9.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN9.getText().toString() + "</span></p></td></tr>\r\n"; } else {s34="";}
          if ((! ETTovarCHNAME10.getText().toString().equals("")) || (! ETTovarCHZN10.getText().toString().equals(""))) { s35= "<tr><td><p>" + ETTovarCHNAME10.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN10.getText().toString() + "</span></p></td></tr>\r\n"; } else {s35="";}
          if ((! ETTovarCHNAME11.getText().toString().equals("")) || (! ETTovarCHZN11.getText().toString().equals(""))) { s36= "<tr><td><p>" + ETTovarCHNAME11.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN11.getText().toString() + "</span></p></td></tr>\r\n"; } else {s36="";}
          if ((! ETTovarCHNAME12.getText().toString().equals("")) || (! ETTovarCHZN12.getText().toString().equals(""))) { s37= "<tr><td><p>" + ETTovarCHNAME12.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN12.getText().toString() + "</span></p></td></tr>\r\n"; } else {s37="";}
          if ((! ETTovarCHNAME13.getText().toString().equals("")) || (! ETTovarCHZN13.getText().toString().equals(""))) { s38= "<tr><td><p>" + ETTovarCHNAME13.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN13.getText().toString() + "</span></p></td></tr>\r\n"; } else {s38="";}
          if ((! ETTovarCHNAME14.getText().toString().equals("")) || (! ETTovarCHZN14.getText().toString().equals(""))) { s39= "<tr><td><p>" + ETTovarCHNAME14.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN14.getText().toString() + "</span></p></td></tr>\r\n"; } else {s39="";}
          if ((! ETTovarCHNAME15.getText().toString().equals("")) || (! ETTovarCHZN15.getText().toString().equals(""))) { s40= "<tr><td><p>" + ETTovarCHNAME15.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN15.getText().toString() + "</span></p></td></tr>\r\n"; } else {s40="";}
          if ((! ETTovarCHNAME16.getText().toString().equals("")) || (! ETTovarCHZN16.getText().toString().equals(""))) { s41= "<tr><td><p>" + ETTovarCHNAME16.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN16.getText().toString() + "</span></p></td></tr>\r\n"; } else {s41="";}
          if ((! ETTovarCHNAME17.getText().toString().equals("")) || (! ETTovarCHZN17.getText().toString().equals(""))) { s42= "<tr><td><p>" + ETTovarCHNAME17.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN17.getText().toString() + "</span></p></td></tr>\r\n"; } else {s42="";}
          if ((! ETTovarCHNAME18.getText().toString().equals("")) || (! ETTovarCHZN18.getText().toString().equals(""))) { s43= "<tr><td><p>" + ETTovarCHNAME18.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN18.getText().toString() + "</span></p></td></tr>\r\n"; } else {s43="";}
          if ((! ETTovarCHNAME19.getText().toString().equals("")) || (! ETTovarCHZN19.getText().toString().equals(""))) { s44= "<tr><td><p>" + ETTovarCHNAME19.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN19.getText().toString() + "</span></p></td></tr>\r\n"; } else {s44="";}
          if ((! ETTovarCHNAME20.getText().toString().equals("")) || (! ETTovarCHZN20.getText().toString().equals(""))) { s45= "<tr><td><p>" + ETTovarCHNAME20.getText().toString() + "</p></td><td><p><span style=\"font-family:" + ETTovarKategoriiTextColor.getText().toString() + "; font-size:inherit; font-weight:normal;\">" + ETTovarCHZN20.getText().toString() + "</span></p></td></tr>\r\n"; } else {s45="";}


          String s46= "</table></div><div class=\"col-md-5 col-md-pull-7\">";

          final String s47;
          final String s48;
          final String s49;
          final String s50;
          final String s51;
          final String s52;
          final String s53;
          final String s54;
          final String s55;
          final String s56;
          final String s57;
          final String s58;
          final String s59;
          if (! ETTovarIMG1.getText().toString().equals("")) { s47= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG1.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s47="";}
          if (! ETTovarIMG2.getText().toString().equals("")) { s48= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG2.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s48="";}
          if (! ETTovarIMG3.getText().toString().equals("")) { s49= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG3.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s49="";}
          if (! ETTovarIMG4.getText().toString().equals("")) { s50= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG4.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s50="";}
          if (! ETTovarIMG5.getText().toString().equals("")) { s51= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG5.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s51="";}
          if (! ETTovarIMG6.getText().toString().equals("")) { s52= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG6.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s52="";}
          if (! ETTovarIMG7.getText().toString().equals("")) { s53= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG7.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s53="";}
          if (! ETTovarIMG8.getText().toString().equals("")) { s54= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG8.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s54="";}
          if (! ETTovarIMG9.getText().toString().equals("")) { s55= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG9.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; } else {s55="";}
          if (! ETTovarIMG10.getText().toString().equals("")) { s56= "<img class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarIMG10.getText().toString() + "\" alt=\"" + ETTovarName.getText().toString() +" "+ETTovarModel.getText().toString() +"\"><br>\r\n"; }else {s56="";}


          if (! ETTovarVIDEO1.getText().toString().equals("")) { s57= "<video class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarVIDEO1.getText().toString() + "\" controls alt=\"" + ETTovarName.getText().toString() +" "+ ETTovarModel.getText().toString() + "\"></video><br>\r\n"; } else {s57="";}
          if (! ETTovarVIDEO2.getText().toString().equals("")) { s58= "<video class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarVIDEO2.getText().toString() + "\" controls alt=\"" + ETTovarName.getText().toString() +" "+ ETTovarModel.getText().toString() + "\"></video><br>\r\n"; } else {s58="";}
          if (! ETTovarVIDEO3.getText().toString().equals("")) { s59= "<video class=\"featurette-image img-responsive center-block\" src=\"" + ETTovarVIDEO3.getText().toString() + "\" controls alt=\"" + ETTovarName.getText().toString() +" "+ ETTovarModel.getText().toString() + "\"></video><br>\r\n"; } else {s59="";}



          String s60= "</div></div></div><!--КОНЕЦ ОСНОВНОЙ КОНТЕНТ СТРАНИЦЫ--><?php require $_SERVER['DOCUMENT_ROOT'].\"/footer.tpl\"; ?><?php require $_SERVER['DOCUMENT_ROOT'].\"/footerscripts.tpl\"; ?>\r\n";


          String TovarPHPCode=s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12+s13+s14+s15+s16+s17+s18+s19+s20+s21+s22+s23+s24+s24_+s25+s26+s27+s28+s29+s30+s31+s32+s33+s34+s35+s36+s37+s38+s39+s40+s41+s42+s43+s44+s45+s46+s47+s48+s49+s50+s51+s52+s53+s54+s55+s56+s57+s58+s59+s60;

          MainActivity rootActivity = (MainActivity) getActivity();
          //rootActivity.savePHPFile(String FolderName, String SubFolderName, String FileName, String str)
          //rootActivity.savePHPFile("Мастерконд", "", ETTovarFileName.getText().toString(), TovarPHPCode);
        }
        catch (SQLException mSQLException) {
          Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
        }

      }
    });


    //endregion

   //region КНОПКА ДОБАВИТЬ ДАННЫЕ ТОВАРА ИЗ ШАБЛОНА

   ButtonFromShablon.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {

           ShablonID.setText("");

           list.clear();

           AlertDialog.Builder selectshablonbuilder = new AlertDialog.Builder(container. getContext());
           selectshablonbuilder.setCancelable(true);
           View dialogView = inflater.inflate(R.layout.alertdialog_select_sdelka, null); //важно - inflater определен в начале кода фрагмента
           // Привязка xml-разметки окна диалогов
           selectshablonbuilder.setView(dialogView);

           final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
           final Button btn_neutral = (Button) dialogView.findViewById(R.id.dialog_neutral_btn);
           final TextView ZagolovokDialog = (TextView) dialogView.findViewById(R.id.Zagolovok);
           final ListView ListViewShabloni = (ListView)dialogView.findViewById(R.id.ListViewSDELKI);

           ZagolovokDialog.setText("Выбрать шаблон товара:");
           btn_neutral.setVisibility(View.GONE);

           try {


               SQLiteDatabase db = DB.getReadableDatabase();

               Cursor cursor = db.rawQuery("SELECT [ID],[NAME],[MODEL],[PRICE],[VALUTA] FROM TOVARI WHERE SHABLON = 'Шаблон'", null);


               while (cursor.moveToNext()) {
                   list.add(new Sdelki(cursor.getString(1), "Модель: " + cursor.getString(2), "Цена: " + cursor.getString(3)+" "+cursor.getString(4),cursor.getString(0)));
               }


               ListViewShabloni.setAdapter(Zadapter);
               Zadapter.notifyDataSetChanged();
               final AlertDialog selectshablondialog = selectshablonbuilder.create();
               SetDListView.SetDynamicHeight(ListViewShabloni);

               selectshablondialog.show();


               ListViewShabloni.setOnItemClickListener(
                       new OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                               ShablonID.setText(list.get(position).getidnumber());

                               try {

                                   SQLiteDatabase db = DB.getReadableDatabase();
                                   Cursor cursor = db.rawQuery("SELECT * FROM TOVARI WHERE ID = " + ShablonID.getText().toString(), null);
                                   cursor.moveToNext(); //без этого exception
                                   //0 - это ID 1- IDD

                                   ETTovarName.setText(cursor.getString(2));
                                   ETTovarModel.setText(cursor.getString(3));
                                   ETTovarOpisanie.setText(cursor.getString(4));
                                   ETTovarPrice.setText(cursor.getString(5));
                                   ETValuta.setText(cursor.getString(6));
                                   ETTovarCustomCSS.setText(cursor.getString(7));
                                   ETTovarNavLevel1.setText(cursor.getString(8));
                                   ETTovarNavLevel1Link.setText(cursor.getString(9));
                                   ETTovarNavLevel2.setText(cursor.getString(10));
                                   ETTovarNavLevel2Link.setText(cursor.getString(11));
                                   ETTovarNavLevel3.setText(cursor.getString(12));
                                   ETTovarNavLevel3Link.setText(cursor.getString(13));
                                   ETTovarZagolovokWWW.setText(cursor.getString(14));
                                   ETTovarKluchevieSlova.setText(cursor.getString(15));
                                   ETTovarMetaDescription.setText(cursor.getString(16));
                                   ETTovarMainFont.setText(cursor.getString(17));
                                   ETTovarDopFont.setText(cursor.getString(18));
                                   ETTovarZagolovokTextColor.setText(cursor.getString(19));
                                   ETTovarKategoriiTextColor.setText(cursor.getString(20));
                                   ETTovarPriceTextColor.setText(cursor.getString(21));
                                   ETTovarBackgroundColor.setText(cursor.getString(22));
                                   ETTovarNavLinkTextColor.setText(cursor.getString(23));
                                   ETTovarLink.setText(cursor.getString(24));

                                   ETTovarCHNAME1.setText(cursor.getString(25));
                                   ETTovarCHZN1.setText(cursor.getString(26));

                                   ETTovarCHNAME2.setText(cursor.getString(27));
                                   ETTovarCHZN2.setText(cursor.getString(28));

                                   ETTovarCHNAME3.setText(cursor.getString(29));
                                   ETTovarCHZN3.setText(cursor.getString(30));

                                   ETTovarCHNAME4.setText(cursor.getString(31));
                                   ETTovarCHZN4.setText(cursor.getString(32));

                                   ETTovarCHNAME5.setText(cursor.getString(33));
                                   ETTovarCHZN5.setText(cursor.getString(34));

                                   ETTovarCHNAME6.setText(cursor.getString(35));
                                   ETTovarCHZN6.setText(cursor.getString(36));

                                   ETTovarCHNAME7.setText(cursor.getString(37));
                                   ETTovarCHZN7.setText(cursor.getString(38));

                                   ETTovarCHNAME8.setText(cursor.getString(39));
                                   ETTovarCHZN8.setText(cursor.getString(40));

                                   ETTovarCHNAME9.setText(cursor.getString(41));
                                   ETTovarCHZN9.setText(cursor.getString(42));

                                   ETTovarCHNAME10.setText(cursor.getString(43));
                                   ETTovarCHZN10.setText(cursor.getString(44));

                                   ETTovarCHNAME11.setText(cursor.getString(45));
                                   ETTovarCHZN11.setText(cursor.getString(46));

                                   ETTovarCHNAME12.setText(cursor.getString(47));
                                   ETTovarCHZN12.setText(cursor.getString(48));

                                   ETTovarCHNAME13.setText(cursor.getString(49));
                                   ETTovarCHZN13.setText(cursor.getString(50));

                                   ETTovarCHNAME14.setText(cursor.getString(51));
                                   ETTovarCHZN14.setText(cursor.getString(52));

                                   ETTovarCHNAME15.setText(cursor.getString(53));
                                   ETTovarCHZN15.setText(cursor.getString(54));

                                   ETTovarCHNAME16.setText(cursor.getString(55));
                                   ETTovarCHZN16.setText(cursor.getString(56));

                                   ETTovarCHNAME17.setText(cursor.getString(57));
                                   ETTovarCHZN17.setText(cursor.getString(58));

                                   ETTovarCHNAME18.setText(cursor.getString(59));
                                   ETTovarCHZN18.setText(cursor.getString(60));

                                   ETTovarCHNAME19.setText(cursor.getString(61));
                                   ETTovarCHZN19.setText(cursor.getString(62));

                                   ETTovarCHNAME20.setText(cursor.getString(63));
                                   ETTovarCHZN20.setText(cursor.getString(64));

                                   ETTovarIMG1.setText(cursor.getString(65));
                                   ETTovarIMG2.setText(cursor.getString(66));
                                   ETTovarIMG3.setText(cursor.getString(67));
                                   ETTovarIMG4.setText(cursor.getString(68));
                                   ETTovarIMG5.setText(cursor.getString(69));
                                   ETTovarIMG6.setText(cursor.getString(70));
                                   ETTovarIMG7.setText(cursor.getString(71));
                                   ETTovarIMG8.setText(cursor.getString(72));
                                   ETTovarIMG9.setText(cursor.getString(73));
                                   ETTovarIMG10.setText(cursor.getString(74));

                                   ETTovarVIDEO1.setText(cursor.getString(75));
                                   ETTovarVIDEO2.setText(cursor.getString(76));
                                   ETTovarVIDEO3.setText(cursor.getString(77));


                                   ETTovarOstatok.setText(cursor.getString(78));
                                   ETTovarSkladMesto.setText(cursor.getString(79));
                                   ETShablon.setText(cursor.getString(80));
                                   ETWWW.setText(cursor.getString(81));
                                   ETTovarZakupPrice.setText(cursor.getString(82));
                                   ETTovarFileName.setText(cursor.getString(83));
                                   ETTovarOpisanieFull.setText(cursor.getString(84));
                                   ETTovarIMGkatalog.setText(cursor.getString(85));
                                   ETIndexTop.setText(cursor.getString(86));

                                   RadioGroupValuta.clearCheck();

                                   if (ETValuta.getText().toString().equals("Рубль")) {
                                       RBRub.setChecked(true);
                                   }
                                   if (ETValuta.getText().toString().equals("Доллар")) {
                                       RBdollar.setChecked(true);
                                   }
                                   if (ETValuta.getText().toString().equals("Евро")) {
                                       RBeuro.setChecked(true);
                                   }

                                   if (ETShablon.getText().toString().equals("Шаблон")) { SWitchShablon.setChecked(true);} else {SWitchShablon.setChecked(false);}
                                   if (ETWWW.getText().toString().equals("Выгружать")) {SWitchWWW.setChecked(true);} else {SWitchWWW.setChecked(false);}
                                   if (ETIndexTop.getText().toString().equals("Да")) {SWitchIndexTop.setChecked(true);} else {SWitchIndexTop.setChecked(false);}

                                   db.close();
                               }

                               catch (SQLException ex) {
                               } catch (StaleDataException ex) {
                               }

                               selectshablondialog.dismiss();
                           }
                       });


               btn_negative.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       selectshablondialog.dismiss();

                   }
               });

               btn_neutral.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       selectshablondialog.dismiss();
                   }
               });


               db.close();
           }

           catch (CursorIndexOutOfBoundsException CursorException) {
               appActivity.Toasts(Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG),"show");
           }





       }
   });

   //endregion

    return rootView;
  }
}
