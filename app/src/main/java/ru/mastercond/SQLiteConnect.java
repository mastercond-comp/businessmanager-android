package ru.mastercond;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class SQLiteConnect extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;

    public static final String DATABASE_NAME = "dbbusinessmanagement.db"; //имя базы данных


    public SQLiteConnect(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 7, DATABASE_VERSION);
        db.close();
    }

    //region СОЗДАНИЕ БАЗЫ ДАННЫХ

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS MYFIRMREKVIZITI (ID Integer Primary Key Autoincrement NOT NULL, FULLNAME Varchar(255),SOKRNAME Varchar(255),INN Varchar(255),KPP Varchar(255),OGRN Varchar(255),BANKNAME Varchar(255),BANKBIK Varchar(255),BANKKS Varchar(255),BANKRS Varchar(255),RUKDOLZHN Varchar(255),VLICE Varchar(255),FIORUK Varchar(255),URADDR Varchar(255),FACTADDR Varchar(255),POSTADDR Varchar(255),PHONE Varchar(255),MOBILE Varchar(255),EMAIL Varchar(255),SITE Varchar(255), MY_LOGO VARCHAR(2000), MY_PECHAT VARCHAR(2000),MY_SLOGAN VARCHAR(2000));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS KONTRAGENTI (ID Integer Primary Key Autoincrement NOT NULL, FULLNAME Varchar(255),SOKRNAME Varchar(255),INN Varchar(255),KPP Varchar(255),OGRN Varchar(255),BANKNAME Varchar(255),BANKBIK Varchar(255),BANKKS Varchar(255),BANKRS Varchar(255),RUKDOLZHN Varchar(255),VLICE Varchar(255),FIORUK Varchar(255),URADDR Varchar(255),FACTADDR Varchar(255),POSTADDR Varchar(255),PHONE Varchar(255),MOBILE Varchar(255),EMAIL Varchar(255),SITE Varchar(255), OTVETSTVENNIJ Varchar(255));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS TOVARI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(10000), NAME Varchar(10000), MODEL Varchar(10000), OPISANIE Varchar(10000), PRICE Varchar(10000), VALUTA Varchar(10000), MYCSS Varchar(10000), NAV1 Varchar(10000), LINKNAV1 Varchar(10000), NAV2 Varchar(10000), LINKNAV2 Varchar(10000), NAV3 Varchar(10000), LINKNAV3 Varchar(10000), ZAGOLOVOK Varchar(10000), KEYWORDS Varchar(10000), METADESCRIPTION Varchar(10000), FONT Varchar(10000),DOPFONT Varchar(10000), ZAGOLOVOKTEXTCOLOR Varchar(10000), KATEGORIITEXTCOLOR Varchar(10000), PRICETEXTCOLOR Varchar(10000), BACKGROUNDCOLOR Varchar(10000), NAVTEXTCOLOR Varchar(10000), LINK Varchar(10000), CHNAME1 Varchar(10000), CHZNACHENIE1 Varchar(10000), CHNAME2 Varchar(10000), CHZNACHENIE2 Varchar(10000), CHNAME3 Varchar(10000), CHZNACHENIE3 Varchar(10000), CHNAME4 Varchar(10000), CHZNACHENIE4 Varchar(10000), CHNAME5 Varchar(10000), CHZNACHENIE5 Varchar(10000), CHNAME6 Varchar(10000), CHZNACHENIE6 Varchar(10000), CHNAME7 Varchar(10000), CHZNACHENIE7 Varchar(10000), CHNAME8 Varchar(10000), CHZNACHENIE8 Varchar(10000), CHNAME9 Varchar(10000), CHZNACHENIE9 Varchar(10000), CHNAME10 Varchar(10000), CHZNACHENIE10 Varchar(10000), CHNAME11 Varchar(10000), CHZNACHENIE11 Varchar(10000), CHNAME12 Varchar(10000), CHZNACHENIE12 Varchar(10000), CHNAME13 Varchar(10000), CHZNACHENIE13 Varchar(10000), CHNAME14 Varchar(10000), CHZNACHENIE14 Varchar(10000), CHNAME15 Varchar(10000), CHZNACHENIE15 Varchar(10000), CHNAME16 Varchar(10000), CHZNACHENIE16 Varchar(10000), CHNAME17 Varchar(10000), CHZNACHENIE17 Varchar(10000), CHNAME18 Varchar(10000), CHZNACHENIE18 Varchar(10000), CHNAME19 Varchar(10000), CHZNACHENIE19 Varchar(10000), CHNAME20 Varchar(10000), CHZNACHENIE20 Varchar(10000), IMGTOVARA1 Varchar(10000), IMGTOVARA2 Varchar(10000), IMGTOVARA3 Varchar(10000), IMGTOVARA4 Varchar(10000), IMGTOVARA5 Varchar(10000), IMGTOVARA6 Varchar(10000), IMGTOVARA7 Varchar(10000), IMGTOVARA8 Varchar(10000), IMGTOVARA9 Varchar(10000), IMGTOVARA10 Varchar(10000), VIDEOTOVARA1 Varchar(10000), VIDEOTOVARA2 Varchar(10000), VIDEOTOVARA3  Varchar(10000), OSTATOK  Varchar(10000), SKLADMESTO  Varchar(10000), SHABLON Varchar(10000), VISIBILITY Varchar(10000), ZAKUPCENA Varchar(10000), FILENAME Varchar(128), INDEXTOP Varchar(20));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS USLUGI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS SDELKI (ID Integer Primary Key Autoincrement NOT NULL, SDELKA_NAME Varchar(2000), K_FULLNAME Varchar(1000),K_SOKRNAME Varchar(255), K_INN Varchar(255), K_KPP Varchar(255), K_OGRN Varchar(255), K_BANKNAME Varchar(255), K_BANKBIK Varchar(255), K_BANKKS Varchar(255), K_BANKRS Varchar(255), K_RUKDOLZHN Varchar(255), K_VLICE Varchar(255), K_FIORUK Varchar(255), K_URADDR Varchar(1000), K_FACTADDR Varchar(1000), K_POSTADDR Varchar(1000), K_PHONE Varchar(255), K_MOBILE Varchar(255), K_EMAIL Varchar(255), K_SITE Varchar(255), K_OTVETSTVENNIJ Varchar(255), MY_FULLNAME Varchar(1000), MY_SOKRNAME Varchar(255), MY_INN Varchar(255), MY_KPP Varchar(255), MY_OGRN Varchar(255), MY_BANKNAME Varchar(255), MY_BANKBIK Varchar(255), MY_BANKKS Varchar(255), MY_BANKRS Varchar(255), MY_RUKDOLZHN Varchar(255), MY_VLICE Varchar(255), MY_FIORUK Varchar(255), MY_URADDR Varchar(255), MY_FACTADDR Varchar(1000), MY_POSTADDR Varchar(1000), MY_PHONE Varchar(255), MY_MOBILE Varchar(255), MY_EMAIL Varchar(255), MY_SITE Varchar(255), MY_OTVETSTVENNIJ Varchar(255), MY_SLOGAN Varchar(1000), MY_LOGOIMG Varchar(2000), MY_PECHATIMG Varchar(2000), SDELKA_NOMER Varchar(255), DOGOVOR_DATA Varchar(2000), GOROD_SDELKI Varchar(1000), DOGOVOR_PO Varchar(2000), TOVAR_SROKIPOSTAVKI Varchar (2000), USLUGI_SROKIOKAZANIJA Varchar(2000), TOVAR_USLOVIJAOPLATI Varchar(2000),USLUGI_USLOVIJAOPLATI Varchar(2000), TOVAR_USLOVIJAPRIEMKI Varchar(2000), USLUGI_USLOVIJAPRIEMKI Varchar(2000), GARANTIJA_USLOVIJA Varchar(2000), OSOBIJE_USLOVIJA Varchar(2000), SUD Varchar(1000), STATUS Varchar(255), KP_NOMER Varchar(255), KP_DATA Varchar(255),  SCHET_NOMER Varchar(255), SCHET_DATA Varchar(255), AKT_NOMER Varchar(255), AKT_DATA Varchar(255), NAKLADNAJA_NOMER Varchar(255), NAKLADNAJA_DATA Varchar(255), TEHZAKL_NOMER Varchar (255), TEHZAKL_DATA Varchar(255), TEHZAKL_TEXT Varchar(2000), DATASDELKI_DAY VARCHAR(20), DATASDELKI_MONTH VARCHAR(20), DATASDELKI_YEAR VARCHAR(50), DATAKP_DAY VARCHAR(20), DATAKP_MONTH VARCHAR(20), DATAKP_YEAR VARCHAR(50), DATASCHET_DAY VARCHAR(20), DATASCHET_MONTH VARCHAR(20), DATASCHET_YEAR VARCHAR(50), DATAAKT_DAY VARCHAR(20), DATAAKT_MONTH VARCHAR(20), DATAAKT_YEAR VARCHAR(50), DATANAKLADNAJA_DAY VARCHAR(20), DATANAKLADNAJA_MONTH VARCHAR(20), DATANAKLADNAJA_YEAR VARCHAR(50), DATATEHZAKL_DAY VARCHAR(20), DATATEHZAKL_MONTH VARCHAR(20), DATATEHZAKL_YEAR VARCHAR(50), STOIMOSTSDELKI VARCHAR(1000), STOIMOSTSDELKI_TOVARI VARCHAR(1000), STOIMOSTSDELKI_USLUGI VARCHAR(1000));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS TOVARIUSLUGI_SDELKI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(255), NAME Varchar(255),CENA Varchar(255), VALUTA Varchar(255), KOLVO Varchar(255),EDIZM Varchar(255), STOIMOST Varchar(255), DOCUMENT Varchar(255), NAKLADNAJA_NOMER Varchar(255), NAKLADNAJA_DATA Varchar(255), AKT_NOMER Varchar(255), AKT_DATA Varchar(255));"); //SQL-запрос на создание БД


        db.execSQL("CREATE TABLE IF NOT EXISTS NORMDOC (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), OPISANIE Varchar(1000), PRIMECHANIE Varchar(1000),FILENAME Varchar(1000));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS ZAMETKI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), OPISANIE Varchar(1000), DATA Varchar(1000),SDELKAIDD Varchar(1000), IZBR Varchar(1000));"); //SQL-запрос на создание БД


        db.execSQL("CREATE TABLE IF NOT EXISTS AUTO (ID Integer Primary Key Autoincrement NOT NULL, MARKA Varchar(255), GOSNOMER Varchar(255), COLOR Varchar(255), TEHOSMOTRNOMER Varchar(255), TEHOSMOTRDATA_D Integer, TEHOSMOTRDATA_M Integer, TEHOSMOTRDATA_Y Integer, TEHOSMOTRSLEDDATA_D Integer, TEHOSMOTRSLEDDATA_M Integer, TEHOSMOTRSLEDDATA_Y Integer, OSAGODATA_D Integer, OSAGODATA_M Integer, OSAGODATA_Y Integer, OSAGONOMER Varchar(255), OSAGOCOMPANY Varchar(255), OSAGODATAEND_D Integer, OSAGODATAEND_M Integer, OSAGODATAEND_Y Integer, OSAGOPREVDATA_D Integer, OSAGOPREVDATA_M Integer, OSAGOPREVDATA_Y Integer, OSAGOPREVNOMER Varchar(255), OSAGOPREVCOMPANY Varchar(255), OSAGOPREVDATAEND_D Integer, OSAGOPREVDATAEND_M Integer, OSAGOPREVDATAEND_Y Integer, KASKODATA_D Integer, KASKODATA_M Integer, KASKODATA_Y Integer, KASKONOMER Integer, KASKOCOMPANY Varchar(255), KASKODATAEND_D Integer, KASKODATAEND_M Integer, KASKODATAEND_Y Integer, KASKOPREVDATA_D Integer, KASKOPREVDATA_M Integer, KASKOPREVDATA_Y Integer, KASKOPREVNOMER Varchar(255), KASKOPREVCOMPANY Varchar(255), KASKOPREVDATAEND_D Integer, KASKOPREVDATAEND_M Integer, KASKOPREVDATAEND_Y Integer, AUTOpokupkaDATA_D Integer, AUTOpokupkaDATA_M Integer, AUTOpokupkaDATA_Y Integer, AUTOdocument Varchar(255),  AUTOdocumentDATA_D Integer, AUTOdocumentDATA_M Integer, AUTOdocumentDATA_Y Integer, AUTOzeroPROBEG Integer, AUTOPTS Varchar(255), AUTOSTS Varchar(255), AUTOVLADELEC Varchar(255), AUTOVIN Varchar(255), AUTOENGINE Varchar(255), VODITEL1_IDD Varchar(255), VODITEL2_IDD Varchar(255),  PRIMECHANIE Varchar(10000));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS SOTRUDNIKI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), SOTRUDNIK_FIO Varchar(255), SOTRUDNIK_DATAROZHDENIJA Integer, SOTRUDNIK_INN Varchar(255), SOTRUDNIK_INNDATA Integer, SOTRUDNIK_SNILS Varchar(255), SOTRUDNIK_SNILSDATA Integer, SOTRUDNIK_OPIT Varchar(10000), SOTRUDNIK_SEMJA Varchar(10000), SOTRUDNIK_OBRAZOVANIJE Varchar(10000), SOTRUDNIK_DOLZHNOST Varchar(255), SOTRUDNIK_DOLZHNOSTNAJAINSTRUKCIJA Varchar(10000), SOTRUDNIK_DOGOVOR Varchar(255), SOTRUDNIK_DOGOVORTYPE Varchar(255), SOTRUDNIK_DOGOVORDATA Integer, SOTRUDNIK_IDMYCOMPANY Varchar(255), SOTRUDNIK_VODITEL Varchar(255), SOTRUDNIK_PRAVA Varchar(255), SOTRUDNIK_PRAVADATA Integer, SOTRUDNIK_PRAVADATAOKONCHANIJA Integer, SOTRUDNIK_PRAVAKATEGORIJA Varchar(255), SOTRUDNIK_CHARAKTERISTIKA Varchar(10000), SOTRUDNIK_EFFEKTIVNOST Varchar(255), SOTRUDNIK_DATAUVOLNENIJA Integer, SOTRUDNIK_OSNOVANIJEUVOLNENIJA Varchar(10000), SOTRUDNIK_DOCUMENTUVOLNENIJE Varchar(10000), SOTRUDNIK_PRIMECHANIE Varchar(10000));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS AUTORASHODY (ID Integer Primary Key Autoincrement NOT NULL, autoTYPE Varchar(255), autoEDIZM Varchar(255), autoKOLVO Real,  autoCENAED Real,  autoSTOIMOST Real,  autoNAIMENOVANIE Varchar(255),  autoNOMERCHEKA Varchar(255),  autoNOMERZAKAZNARJADA Varchar(255),  autoDATA_D Integer, autoDATA_M Integer, autoDATA_Y Integer,  autoSROKKONCAGARANTII_D Integer, autoSROKKONCAGARANTII_M Integer, autoSROKKONCAGARANTII_Y Integer,  autoPROBEG Integer,  IDV Varchar(255),  autoENDRESURSDATA_D Integer, autoENDRESURSDATA_M Integer, autoENDRESURSDATA_Y Integer,  autoENDRESURSPROBEG Integer,  IDD Varchar(255));"); //SQL-запрос на создание БД


        db.execSQL("CREATE TABLE IF NOT EXISTS SETTINGS (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), ZVALUE Varchar(1000));"); //SQL-запрос на создание БД

        db.execSQL("CREATE TABLE IF NOT EXISTS SITES (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), ADDRES Varchar(1000), FTPADDRES Varchar(1000), FTPPORT Varchar(20), FTPUSER Varchar(255), FTPPASS Varchar(255), FTPSECURE Varchar(20));"); //SQL-запрос на создание БД


    }

    //endregion

    //region ОБНОВЛЕНИЕ БАЗЫ ДАННЫХ

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) //действия при обновлении базы данных
    {
        

        if (oldVersion <= 7) {

            //db.execSQL("DROP TABLE TOVARI;");

            db.execSQL("CREATE TABLE IF NOT EXISTS SETTINGS (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), ZVALUE Varchar(1000));"); //SQL-запрос на создание БД

            // db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID], [NAME],[ZVALUE]) VALUES ('1','VIEWTYPE','TABLET'); ");

            db.execSQL("CREATE TABLE IF NOT EXISTS SDELKI (ID Integer Primary Key Autoincrement NOT NULL, SDELKA_NAME Varchar(2000), K_FULLNAME Varchar(1000),K_SOKRNAME Varchar(255), K_INN Varchar(255), K_KPP Varchar(255), K_OGRN Varchar(255), K_BANKNAME Varchar(255), K_BANKBIK Varchar(255), K_BANKKS Varchar(255), K_BANKRS Varchar(255), K_RUKDOLZHN Varchar(255), K_VLICE Varchar(255), K_FIORUK Varchar(255), K_URADDR Varchar(1000), K_FACTADDR Varchar(1000), K_POSTADDR Varchar(1000), K_PHONE Varchar(255), K_MOBILE Varchar(255), K_EMAIL Varchar(255), K_SITE Varchar(255), K_OTVETSTVENNIJ Varchar(255), MY_FULLNAME Varchar(1000), MY_SOKRNAME Varchar(255), MY_INN Varchar(255), MY_KPP Varchar(255), MY_OGRN Varchar(255), MY_BANKNAME Varchar(255), MY_BANKBIK Varchar(255), MY_BANKKS Varchar(255), MY_BANKRS Varchar(255), MY_RUKDOLZHN Varchar(255), MY_VLICE Varchar(255), MY_FIORUK Varchar(255), MY_URADDR Varchar(255), MY_FACTADDR Varchar(1000), MY_POSTADDR Varchar(1000), MY_PHONE Varchar(255), MY_MOBILE Varchar(255), MY_EMAIL Varchar(255), MY_SITE Varchar(255), MY_OTVETSTVENNIJ Varchar(255), MY_SLOGAN Varchar(1000), MY_LOGOIMG Varchar(2000), MY_PECHATIMG Varchar(2000), SDELKA_NOMER Varchar(255), DOGOVOR_DATA Varchar(2000), GOROD_SDELKI Varchar(1000), DOGOVOR_PO Varchar(2000), TOVAR_SROKIPOSTAVKI Varchar (2000), USLUGI_SROKIOKAZANIJA Varchar(2000), TOVAR_USLOVIJAOPLATI Varchar(2000),USLUGI_USLOVIJAOPLATI Varchar(2000), TOVAR_USLOVIJAPRIEMKI Varchar(2000), USLUGI_USLOVIJAPRIEMKI Varchar(2000), GARANTIJA_USLOVIJA Varchar(2000), OSOBIJE_USLOVIJA Varchar(2000), SUD Varchar(1000), STATUS Varchar(255), KP_NOMER Varchar(255), KP_DATA Varchar(255),  SCHET_NOMER Varchar(255), SCHET_DATA Varchar(255), AKT_NOMER Varchar(255), AKT_DATA Varchar(255), NAKLADNAJA_NOMER Varchar(255), NAKLADNAJA_DATA Varchar(255), TEHZAKL_NOMER Varchar (255), TEHZAKL_DATA Varchar(255), TEHZAKL_TEXT Varchar(2000), DATASDELKI_DAY VARCHAR(20), DATASDELKI_MONTH VARCHAR(20), DATASDELKI_YEAR VARCHAR(50), DATAKP_DAY VARCHAR(20), DATAKP_MONTH VARCHAR(20), DATAKP_YEAR VARCHAR(50), DATASCHET_DAY VARCHAR(20), DATASCHET_MONTH VARCHAR(20), DATASCHET_YEAR VARCHAR(50), DATAAKT_DAY VARCHAR(20), DATAAKT_MONTH VARCHAR(20), DATAAKT_YEAR VARCHAR(50), DATANAKLADNAJA_DAY VARCHAR(20), DATANAKLADNAJA_MONTH VARCHAR(20), DATANAKLADNAJA_YEAR VARCHAR(50), DATATEHZAKL_DAY VARCHAR(20), DATATEHZAKL_MONTH VARCHAR(20), DATATEHZAKL_YEAR VARCHAR(50), STOIMOSTSDELKI VARCHAR(1000), STOIMOSTSDELKI_TOVARI VARCHAR(1000), STOIMOSTSDELKI_USLUGI VARCHAR(1000));"); //SQL-запрос на создание БД

            db.execSQL("CREATE TABLE IF NOT EXISTS AUTO (ID Integer Primary Key Autoincrement NOT NULL, MARKA Varchar(255), GOSNOMER Varchar(255), COLOR Varchar(255), TEHOSMOTRNOMER Varchar(255), TEHOSMOTRDATA_D Integer, TEHOSMOTRDATA_M Integer, TEHOSMOTRDATA_Y Integer, TEHOSMOTRSLEDDATA_D Integer, TEHOSMOTRSLEDDATA_M Integer, TEHOSMOTRSLEDDATA_Y Integer, OSAGODATA_D Integer, OSAGODATA_M Integer, OSAGODATA_Y Integer, OSAGONOMER Varchar(255), OSAGOCOMPANY Varchar(255), OSAGODATAEND_D Integer, OSAGODATAEND_M Integer, OSAGODATAEND_Y Integer, OSAGOPREVDATA_D Integer, OSAGOPREVDATA_M Integer, OSAGOPREVDATA_Y Integer, OSAGOPREVNOMER Varchar(255), OSAGOPREVCOMPANY Varchar(255), OSAGOPREVDATAEND_D Integer, OSAGOPREVDATAEND_M Integer, OSAGOPREVDATAEND_Y Integer, KASKODATA_D Integer, KASKODATA_M Integer, KASKODATA_Y Integer, KASKONOMER Integer, KASKOCOMPANY Varchar(255), KASKODATAEND_D Integer, KASKODATAEND_M Integer, KASKODATAEND_Y Integer, KASKOPREVDATA_D Integer, KASKOPREVDATA_M Integer, KASKOPREVDATA_Y Integer, KASKOPREVNOMER Varchar(255), KASKOPREVCOMPANY Varchar(255), KASKOPREVDATAEND_D Integer, KASKOPREVDATAEND_M Integer, KASKOPREVDATAEND_Y Integer, AUTOpokupkaDATA_D Integer, AUTOpokupkaDATA_M Integer, AUTOpokupkaDATA_Y Integer, AUTOdocument Varchar(255),  AUTOdocumentDATA_D Integer, AUTOdocumentDATA_M Integer, AUTOdocumentDATA_Y Integer, AUTOzeroPROBEG Integer, AUTOPTS Varchar(255), AUTOSTS Varchar(255), AUTOVLADELEC Varchar(255), AUTOVIN Varchar(255), AUTOENGINE Varchar(255), VODITEL1_IDD Varchar(255), VODITEL2_IDD Varchar(255),  PRIMECHANIE Varchar(10000));"); //SQL-запрос на создание БД

            db.execSQL("CREATE TABLE IF NOT EXISTS SOTRUDNIKI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), SOTRUDNIK_FIO Varchar(255), SOTRUDNIK_DATAROZHDENIJA Integer, SOTRUDNIK_INN Varchar(255), SOTRUDNIK_INNDATA Integer, SOTRUDNIK_SNILS Varchar(255), SOTRUDNIK_SNILSDATA Integer, SOTRUDNIK_OPIT Varchar(10000), SOTRUDNIK_SEMJA Varchar(10000), SOTRUDNIK_OBRAZOVANIJE Varchar(10000), SOTRUDNIK_DOLZHNOST Varchar(255), SOTRUDNIK_DOLZHNOSTNAJAINSTRUKCIJA Varchar(10000), SOTRUDNIK_DOGOVOR Varchar(255), SOTRUDNIK_DOGOVORTYPE Varchar(255), SOTRUDNIK_DOGOVORDATA Integer, SOTRUDNIK_IDMYCOMPANY Varchar(255), SOTRUDNIK_VODITEL Varchar(255), SOTRUDNIK_PRAVA Varchar(255), SOTRUDNIK_PRAVADATA Integer, SOTRUDNIK_PRAVADATAOKONCHANIJA Integer, SOTRUDNIK_PRAVAKATEGORIJA Varchar(255), SOTRUDNIK_CHARAKTERISTIKA Varchar(10000), SOTRUDNIK_EFFEKTIVNOST Varchar(255), SOTRUDNIK_DATAUVOLNENIJA Integer, SOTRUDNIK_OSNOVANIJEUVOLNENIJA Varchar(10000), SOTRUDNIK_DOCUMENTUVOLNENIJE Varchar(10000), SOTRUDNIK_PRIMECHANIE Varchar(10000));"); //SQL-запрос на создание БД

            db.execSQL("CREATE TABLE IF NOT EXISTS AUTORASHODY (ID Integer Primary Key Autoincrement NOT NULL, autoTYPE Varchar(255), autoEDIZM Varchar(255), autoKOLVO Real,  autoCENAED Real,  autoSTOIMOST Real,  autoNAIMENOVANIE Varchar(255),  autoNOMERCHEKA Varchar(255),  autoNOMERZAKAZNARJADA Varchar(255),  autoDATA_D Integer, autoDATA_M Integer, autoDATA_Y Integer,  autoSROKKONCAGARANTII_D Integer, autoSROKKONCAGARANTII_M Integer, autoSROKKONCAGARANTII_Y Integer,  autoPROBEG Integer,  IDV Varchar(255),  autoENDRESURSDATA_D Integer, autoENDRESURSDATA_M Integer, autoENDRESURSDATA_Y Integer,  autoENDRESURSPROBEG Integer,  IDD Varchar(255));"); //SQL-запрос на создание БД

            db.execSQL("CREATE TABLE IF NOT EXISTS TOVARI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(10000), NAME Varchar(10000), MODEL Varchar(10000), OPISANIE Varchar(10000), PRICE Varchar(10000), VALUTA Varchar(10000), MYCSS Varchar(10000), NAV1 Varchar(10000), LINKNAV1 Varchar(10000), NAV2 Varchar(10000), LINKNAV2 Varchar(10000), NAV3 Varchar(10000), LINKNAV3 Varchar(10000), ZAGOLOVOK Varchar(10000), KEYWORDS Varchar(10000), METADESCRIPTION Varchar(10000), FONT Varchar(10000),DOPFONT Varchar(10000), ZAGOLOVOKTEXTCOLOR Varchar(10000), KATEGORIITEXTCOLOR Varchar(10000), PRICETEXTCOLOR Varchar(10000), BACKGROUNDCOLOR Varchar(10000), NAVTEXTCOLOR Varchar(10000), LINK Varchar(10000), CHNAME1 Varchar(10000), CHZNACHENIE1 Varchar(10000), CHNAME2 Varchar(10000), CHZNACHENIE2 Varchar(10000), CHNAME3 Varchar(10000), CHZNACHENIE3 Varchar(10000), CHNAME4 Varchar(10000), CHZNACHENIE4 Varchar(10000), CHNAME5 Varchar(10000), CHZNACHENIE5 Varchar(10000), CHNAME6 Varchar(10000), CHZNACHENIE6 Varchar(10000), CHNAME7 Varchar(10000), CHZNACHENIE7 Varchar(10000), CHNAME8 Varchar(10000), CHZNACHENIE8 Varchar(10000), CHNAME9 Varchar(10000), CHZNACHENIE9 Varchar(10000), CHNAME10 Varchar(10000), CHZNACHENIE10 Varchar(10000), CHNAME11 Varchar(10000), CHZNACHENIE11 Varchar(10000), CHNAME12 Varchar(10000), CHZNACHENIE12 Varchar(10000), CHNAME13 Varchar(10000), CHZNACHENIE13 Varchar(10000), CHNAME14 Varchar(10000), CHZNACHENIE14 Varchar(10000), CHNAME15 Varchar(10000), CHZNACHENIE15 Varchar(10000), CHNAME16 Varchar(10000), CHZNACHENIE16 Varchar(10000), CHNAME17 Varchar(10000), CHZNACHENIE17 Varchar(10000), CHNAME18 Varchar(10000), CHZNACHENIE18 Varchar(10000), CHNAME19 Varchar(10000), CHZNACHENIE19 Varchar(10000), CHNAME20 Varchar(10000), CHZNACHENIE20 Varchar(10000), IMGTOVARA1 Varchar(10000), IMGTOVARA2 Varchar(10000), IMGTOVARA3 Varchar(10000), IMGTOVARA4 Varchar(10000), IMGTOVARA5 Varchar(10000), IMGTOVARA6 Varchar(10000), IMGTOVARA7 Varchar(10000), IMGTOVARA8 Varchar(10000), IMGTOVARA9 Varchar(10000), IMGTOVARA10 Varchar(10000), VIDEOTOVARA1 Varchar(10000), VIDEOTOVARA2 Varchar(10000), VIDEOTOVARA3  Varchar(10000), OSTATOK  Varchar(10000), SKLADMESTO  Varchar(10000), SHABLON Varchar(10000), VISIBILITY Varchar(10000), ZAKUPCENA Varchar(10000), FILENAME Varchar(128), OPISANIE_POLNOE Varchar(5000), KATALOG_IMAGE VARCHAR(1000), INDEXTOP VARCHAR(20));"); //SQL-запрос на создание БД
            
            db.execSQL("CREATE TABLE IF NOT EXISTS SITES (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), ADDRES Varchar(1000), FTPADDRES Varchar(1000), FTPPORT Varchar(20), FTPUSER Varchar(255), FTPPASS Varchar(255), FTPSECURE Varchar(20));"); //SQL-запрос на создание БД

            db.execSQL("CREATE TABLE IF NOT EXISTS TOVAR_POHOZHIE (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(255), IDD_POHOZHIJ Varchar(255));"); //SQL-запрос на создание БД

            try {
              
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATASDELKI_DAY VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATASDELKI_MONTH VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATASDELKI_YEAR VARCHAR(50);");
              
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATAKP_DAY VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATAKP_MONTH VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATAKP_YEAR VARCHAR(50);");
              
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATASCHET_DAY VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATASCHET_MONTH VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATASCHET_YEAR VARCHAR(50);");
              
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATAAKT_DAY VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATAAKT_MONTH VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATAAKT_YEAR VARCHAR(50);");
              
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATANAKLADNAJA_DAY VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATANAKLADNAJA_MONTH VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATANAKLADNAJA_YEAR VARCHAR(50);");
              
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATATEHZAKL_DAY VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATATEHZAKL_MONTH VARCHAR(20);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN DATATEHZAKL_YEAR VARCHAR(50);");
              
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN STOIMOSTSDELKI VARCHAR(1000);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN STOIMOSTSDELKI_TOVARI VARCHAR(1000);");
              db.execSQL("ALTER TABLE SDELKI ADD COLUMN STOIMOSTSDELKI_USLUGI VARCHAR(1000);");
              
            }
            
            catch (Exception ex) {}
            
            try {
              db.execSQL("ALTER TABLE ZAMETKI ADD COLUMN ZAMETKA_DAY VARCHAR(20);");
              db.execSQL("ALTER TABLE ZAMETKI ADD COLUMN ZAMETKA_MONTH VARCHAR(20);");
              db.execSQL("ALTER TABLE ZAMETKI ADD COLUMN ZAMETKA_YEAR VARCHAR(50);");
            }
            
            catch (Exception ex1) {}

            try {
                db.execSQL("ALTER TABLE TOVARI ADD COLUMN OPISANIE_POLNOE VARCHAR(5000);");
                db.execSQL("ALTER TABLE TOVARI ADD COLUMN KATALOG_IMAGE VARCHAR(1000);");
            }

            catch (Exception ex2) {}


            try {
                db.execSQL("ALTER TABLE TOVARI ADD COLUMN INDEXTOP VARCHAR(20);"); //отображать товар на главной
            }

            catch (Exception ex2) {}

        }
    }

    //endregion

    //region МОИ ОРГАНИЗАЦИИ

    public void AddMyFirm(String MyFullName, String MySokrName, String MyINN, String MyKPP, String MyOGRN, String MyBankName, String MyBankBIK, String MyBankKS, String MyBankRS, String MyRukDolzhn, String MyVlice, String MyFIOruk, String MyUrAddr, String MyFaktAddr, String MyPostAddr, String MyPhone, String MyMobile, String MyEmail, String MySite) {
        SQLiteDatabase db = this.getWritableDatabase();
        String id = "1";
        db.execSQL("INSERT OR REPLACE INTO MYFIRMREKVIZITI ([ID],[FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE]) VALUES ('" + id + "' , '" + MyFullName + "' , '" + MySokrName + "', '" + MyINN + "', '" + MyKPP + "', '" + MyOGRN + "', '" + MyBankName + "', '" + MyBankBIK + "', '" + MyBankKS + "', '" + MyBankRS + "','" + MyRukDolzhn + "', '" + MyVlice + "', '" + MyFIOruk + "', '" + MyUrAddr + "', '" + MyFaktAddr + "', '" + MyPostAddr + "', '" + MyPhone + "', '" + MyMobile + "','" + MyEmail + "','" + MySite + "'); ");

        db.close();
    }

    public void AddMyFirmDB(String MyFullName, String MySokrName, String MyINN, String MyKPP, String MyOGRN, String MyBankName, String MyBankBIK, String MyBankKS, String MyBankRS, String MyRukDolzhn, String MyVlice, String MyFIOruk, String MyUrAddr, String MyFaktAddr, String MyPostAddr, String MyPhone, String MyMobile, String MyEmail, String MySite, String LogoImg, String PechatImg, String Slogan) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO MYFIRMREKVIZITI ([FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE], [MY_LOGO], [MY_PECHAT], [MY_SLOGAN]) VALUES ('" + MyFullName + "' , '" + MySokrName + "', '" + MyINN + "', '" + MyKPP + "', '" + MyOGRN + "', '" + MyBankName + "', '" + MyBankBIK + "', '" + MyBankKS + "', '" + MyBankRS + "','" + MyRukDolzhn + "', '" + MyVlice + "', '" + MyFIOruk + "', '" + MyUrAddr + "', '" + MyFaktAddr + "', '" + MyPostAddr + "', '" + MyPhone + "', '" + MyMobile + "','" + MyEmail + "','" + MySite + "','" + LogoImg + "','" + PechatImg + "','" + Slogan + "'); ");

        db.close();
    }


    public void ChangeMyFirm(String ID, String MyFullName, String MySokrName, String MyINN, String MyKPP, String MyOGRN, String MyBankName, String MyBankBIK, String MyBankKS, String MyBankRS, String MyRukDolzhn, String MyVlice, String MyFIOruk, String MyUrAddr, String MyFaktAddr, String MyPostAddr, String MyPhone, String MyMobile, String MyEmail, String MySite, String LogoImg, String PechatImg, String Slogan) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO MYFIRMREKVIZITI ([ID],[FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE], [MY_LOGO], [MY_PECHAT], [MY_SLOGAN]) VALUES ('" + ID + "' , '" + MyFullName + "' , '" + MySokrName + "', '" + MyINN + "', '" + MyKPP + "', '" + MyOGRN + "', '" + MyBankName + "', '" + MyBankBIK + "', '" + MyBankKS + "', '" + MyBankRS + "','" + MyRukDolzhn + "', '" + MyVlice + "', '" + MyFIOruk + "', '" + MyUrAddr + "', '" + MyFaktAddr + "', '" + MyPostAddr + "', '" + MyPhone + "', '" + MyMobile + "','" + MyEmail + "','" + MySite + "','" + LogoImg + "','" + PechatImg + "','" + Slogan + "'); ");

        db.close();
    }

    public void DelMyFirm(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM MYFIRMREKVIZITI WHERE ID=" + ID + ";");
        db.close();

    }

    //endregion

    //region НОРМАТИВНЫЕ ДОКУМЕНТЫ

    public void AddNORMDOC(String Name, String Opisanie, String Primechanie, String FileName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO NORMDOC ([NAME],[OPISANIE],[PRIMECHANIE],[FILENAME]) VALUES ('" + Name + "' , '" + Opisanie + "', '" + Primechanie + "', '" + FileName + "'); ");

        db.close();
    }


    public void ChangeNORMDOC(String ID, String Name, String Opisanie, String Primechanie, String FileName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO NORMDOC ([ID], [NAME],[OPISANIE],[PRIMECHANIE],[FILENAME]) VALUES ('" + ID + "' , '" + Name + "' , '" + Opisanie + "', '" + Primechanie + "', '" + FileName + "'); ");

        db.close();
    }

    public void DelNORMDOC(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM NORMDOC WHERE ID=" + ID + ";");

        db.close();
    }

    //endregion

    //region ЗАМЕТКИ

    public void AddZAMETKA(String Name, String Opisanie, String Sdelka, String Data) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO ZAMETKI ([NAME],[OPISANIE],[SDELKAIDD],[DATA]) VALUES ('" + Name + "' , '" + Opisanie + "', '" + Sdelka + "', '" + Data + "'); ");

        db.close();
    }


    public void ChangeZAMETKA(String ID, String Name, String Opisanie, String Sdelka, String Data) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO ZAMETKI ([ID], [NAME],[OPISANIE],[SDELKAIDD],[DATA]) VALUES ('" + ID + "' , '" + Name + "' , '" + Opisanie + "', '" + Sdelka + "', '" + Data + "'); ");

        db.close();
    }

    public void DelZAMETKA(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM ZAMETKI WHERE ID=" + ID + ";");

        db.close();
    }

//endregion

    //region ПОХОЖИЕ ТОВАРЫ В РАЗДЕЛЕ ТОВАРЫ САЙТА

    public void AddPTovar(String IDD, String IDDPohozhij) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO TOVAR_POHOZHIE ([IDD],[IDD_POHOZHIJ]) VALUES ('" + IDD + "' , '" + IDDPohozhij + "'); ");

        db.close();
    }



    public void DelPTovar(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM TOVAR_POHOZHIE WHERE ID=" + ID + ";");

        db.close();
    }

//endregion


    //region ТОВАРЫ И УСЛУГИ (В СДЕЛКАХ)

    // TOVARIUSLUGI_SDELKI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(255), NAME Varchar(255),CENA Varchar(255), VALUTA Varchar(255), KOLVO Varchar(255),EDIZM Varchar(255), STOIMOST Varchar(255), NAKLADNAJA_NOMER Varchar(255), NAKLADNAJA_DATA Varchar(255), AKT_NOMER Varchar(255), AKT_DATA Varchar(255));

    public void AddTOVARUSLUGA(String IDD, String Name, String Cena, String Kolvo, String EdIzm, String Stoimost, String Type) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO TOVARIUSLUGI_SDELKI ([IDD],[NAME],[CENA],[KOLVO], [EDIZM], [STOIMOST], [DOCUMENT]) VALUES ('" + IDD + "' , '" + Name + "', '" + Cena + "', '" + Kolvo + "' , '" + EdIzm + "' , '" + Stoimost + "' , '" + Type + "'); ");

        db.close();
    }


    public void ChangeTOVARUSLUGA(String ID, String IDD, String Name, String Cena, String Kolvo, String EdIzm, String Stoimost, String Type) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO TOVARIUSLUGI_SDELKI ([ID], [IDD],[NAME],[CENA],[KOLVO], [EDIZM], [STOIMOST], [DOCUMENT]) VALUES ('" + ID + "' , '" + IDD + "' , '" + Name + "', '" + Cena + "', '" + Kolvo + "' , '" + EdIzm + "' , '" + Stoimost + "' , '" + Type + "'); ");

        db.close();
    }

    public void DelTOVARUSLUGA(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM TOVARIUSLUGI_SDELKI WHERE ID=" + ID + ";");

        db.close();
    }

    //endregion

    //region ТОВАРЫ (САЙТЫ)


    public void AddTOVARI(String IDD, String NAME, String MODEL, String OPISANIE, String PRICE, String VALUTA, String MYCSS, String NAV1, String LINKNAV1, String NAV2, String LINKNAV2, String NAV3, String LINKNAV3, String ZAGOLOVOK, String KEYWORDS, String METADESCRIPTION, String FONT, String DOPFONT, String ZAGOLOVOKTEXTCOLOR, String KATEGORIITEXTCOLOR, String PRICETEXTCOLOR, String BACKGROUNDCOLOR, String NAVTEXTCOLOR, String LINK, String CHNAME1, String CHZNACHENIE1, String CHNAME2, String CHZNACHENIE2, String CHNAME3, String CHZNACHENIE3, String CHNAME4, String CHZNACHENIE4, String CHNAME5, String CHZNACHENIE5, String CHNAME6, String CHZNACHENIE6, String CHNAME7, String CHZNACHENIE7, String CHNAME8, String CHZNACHENIE8, String CHNAME9, String CHZNACHENIE9, String CHNAME10, String CHZNACHENIE10, String CHNAME11, String CHZNACHENIE11, String CHNAME12, String CHZNACHENIE12, String CHNAME13, String CHZNACHENIE13, String CHNAME14, String CHZNACHENIE14, String CHNAME15, String CHZNACHENIE15, String CHNAME16, String CHZNACHENIE16, String CHNAME17, String CHZNACHENIE17, String CHNAME18, String CHZNACHENIE18, String CHNAME19, String CHZNACHENIE19, String CHNAME20, String CHZNACHENIE20, String IMGTOVARA1, String IMGTOVARA2, String IMGTOVARA3, String IMGTOVARA4, String IMGTOVARA5, String IMGTOVARA6, String IMGTOVARA7, String IMGTOVARA8, String IMGTOVARA9, String IMGTOVARA10, String VIDEOTOVARA1, String VIDEOTOVARA2, String VIDEOTOVARA3, String OSTATOK, String SKLADMESTO, String SHABLON, String VISIBILITY, String ZAKUPCENA, String FILENAME, String OPISANIE_POLNOE, String KATALOG_IMAGE, String INDEXTOP) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO TOVARI ([IDD],[NAME],[MODEL],[OPISANIE],[PRICE],[VALUTA],[MYCSS],[NAV1],[LINKNAV1],[NAV2],[LINKNAV2],[NAV3],[LINKNAV3],[ZAGOLOVOK],[KEYWORDS],[METADESCRIPTION],[FONT],[DOPFONT],[ZAGOLOVOKTEXTCOLOR],[KATEGORIITEXTCOLOR],[PRICETEXTCOLOR],[BACKGROUNDCOLOR],[NAVTEXTCOLOR],[LINK],[CHNAME1],[CHZNACHENIE1],[CHNAME2],[CHZNACHENIE2],[CHNAME3],[CHZNACHENIE3],[CHNAME4],[CHZNACHENIE4],[CHNAME5],[CHZNACHENIE5],[CHNAME6],[CHZNACHENIE6],[CHNAME7],[CHZNACHENIE7],[CHNAME8],[CHZNACHENIE8],[CHNAME9],[CHZNACHENIE9],[CHNAME10],[CHZNACHENIE10],[CHNAME11],[CHZNACHENIE11],[CHNAME12],[CHZNACHENIE12],[CHNAME13],[CHZNACHENIE13],[CHNAME14],[CHZNACHENIE14],[CHNAME15],[CHZNACHENIE15],[CHNAME16],[CHZNACHENIE16],[CHNAME17],[CHZNACHENIE17],[CHNAME18],[CHZNACHENIE18],[CHNAME19],[CHZNACHENIE19],[CHNAME20],[CHZNACHENIE20],[IMGTOVARA1],[IMGTOVARA2],[IMGTOVARA3],[IMGTOVARA4],[IMGTOVARA5],[IMGTOVARA6],[IMGTOVARA7],[IMGTOVARA8],[IMGTOVARA9],[IMGTOVARA10],[VIDEOTOVARA1],[VIDEOTOVARA2],[VIDEOTOVARA3],[OSTATOK],[SKLADMESTO],[SHABLON],[VISIBILITY],[ZAKUPCENA],[FILENAME], [OPISANIE_POLNOE], [KATALOG_IMAGE], [INDEXTOP]) VALUES ('" + IDD+ "' , '" +  NAME+ "' , '" +  MODEL+ "' , '" +  OPISANIE+ "' , '" +  PRICE+ "' , '" +  VALUTA+ "' , '" +  MYCSS+ "' , '" +  NAV1+ "' , '" +  LINKNAV1+ "' , '" +  NAV2+ "' , '" +  LINKNAV2+ "' , '" +  NAV3+ "' , '" +  LINKNAV3+ "' , '" +  ZAGOLOVOK+ "' , '" +  KEYWORDS+ "' , '" +  METADESCRIPTION+ "' , '" +  FONT+ "' , '" + DOPFONT+ "' , '" +  ZAGOLOVOKTEXTCOLOR+ "' , '" +  KATEGORIITEXTCOLOR+ "' , '" +  PRICETEXTCOLOR+ "' , '" +  BACKGROUNDCOLOR+ "' , '" +  NAVTEXTCOLOR+ "' , '" +  LINK+ "' , '" +  CHNAME1+ "' , '" +  CHZNACHENIE1+ "' , '" +  CHNAME2+ "' , '" +  CHZNACHENIE2+ "' , '" +  CHNAME3+ "' , '" +  CHZNACHENIE3+ "' , '" +  CHNAME4+ "' , '" +  CHZNACHENIE4+ "' , '" +  CHNAME5+ "' , '" +  CHZNACHENIE5+ "' , '" +  CHNAME6+ "' , '" +  CHZNACHENIE6+ "' , '" + CHNAME7+ "' , '" +  CHZNACHENIE7+ "' , '" +  CHNAME8+ "' , '" +  CHZNACHENIE8+ "' , '" +  CHNAME9+ "' , '" +  CHZNACHENIE9+ "' , '" +  CHNAME10+ "' , '" +  CHZNACHENIE10+ "' , '" +  CHNAME11+ "' , '" +  CHZNACHENIE11+ "' , '" +  CHNAME12+ "' , '" +  CHZNACHENIE12+ "' , '" +  CHNAME13+ "' , '" +  CHZNACHENIE13+ "' , '" +  CHNAME14+ "' , '" +  CHZNACHENIE14+ "' , '" +  CHNAME15+ "' , '" +  CHZNACHENIE15+ "' , '" +  CHNAME16+ "' , '" +  CHZNACHENIE16+ "' , '" +  CHNAME17+ "' , '" +  CHZNACHENIE17+ "' , '" +  CHNAME18+ "' , '" +  CHZNACHENIE18+ "' , '" +  CHNAME19+ "' , '" +  CHZNACHENIE19+ "' , '" +  CHNAME20+ "' , '" +  CHZNACHENIE20+ "' , '" +  IMGTOVARA1+ "' , '" +  IMGTOVARA2+ "' , '" +  IMGTOVARA3+ "' , '" +  IMGTOVARA4+ "' , '" +  IMGTOVARA5+ "' , '" +  IMGTOVARA6+ "' , '" +  IMGTOVARA7+ "' , '" +  IMGTOVARA8+ "' , '" +  IMGTOVARA9+ "' , '" +  IMGTOVARA10+ "' , '" +  VIDEOTOVARA1+ "' , '" +  VIDEOTOVARA2+ "' , '" +  VIDEOTOVARA3+ "' , '" + OSTATOK+ "' , '" +SKLADMESTO+ "' , '" +SHABLON+ "' , '" +VISIBILITY+ "' , '" +ZAKUPCENA+ "' , '" +FILENAME+ "' , '" +OPISANIE_POLNOE+ "' , '" + KATALOG_IMAGE + "' , '" + INDEXTOP +"'); ");

        db.close();
    }

    public void ChangeTOVARI(String ID, String  IDD, String NAME, String  MODEL, String  OPISANIE, String  PRICE, String  VALUTA, String  MYCSS, String  NAV1, String  LINKNAV1, String  NAV2, String  LINKNAV2, String  NAV3, String  LINKNAV3, String  ZAGOLOVOK, String  KEYWORDS, String  METADESCRIPTION, String  FONT, String DOPFONT, String  ZAGOLOVOKTEXTCOLOR, String  KATEGORIITEXTCOLOR, String  PRICETEXTCOLOR, String  BACKGROUNDCOLOR, String  NAVTEXTCOLOR, String  LINK, String  CHNAME1, String  CHZNACHENIE1, String  CHNAME2, String  CHZNACHENIE2, String  CHNAME3, String  CHZNACHENIE3, String  CHNAME4, String  CHZNACHENIE4, String  CHNAME5, String  CHZNACHENIE5, String  CHNAME6, String  CHZNACHENIE6, String CHNAME7, String  CHZNACHENIE7, String  CHNAME8, String  CHZNACHENIE8, String  CHNAME9, String  CHZNACHENIE9, String  CHNAME10, String  CHZNACHENIE10, String  CHNAME11, String  CHZNACHENIE11, String  CHNAME12, String  CHZNACHENIE12, String  CHNAME13, String  CHZNACHENIE13, String  CHNAME14, String  CHZNACHENIE14, String  CHNAME15, String  CHZNACHENIE15, String  CHNAME16, String  CHZNACHENIE16, String  CHNAME17, String  CHZNACHENIE17, String  CHNAME18, String  CHZNACHENIE18, String  CHNAME19, String  CHZNACHENIE19, String  CHNAME20, String  CHZNACHENIE20, String  IMGTOVARA1, String  IMGTOVARA2, String  IMGTOVARA3, String  IMGTOVARA4, String  IMGTOVARA5, String  IMGTOVARA6, String  IMGTOVARA7, String  IMGTOVARA8, String  IMGTOVARA9, String  IMGTOVARA10, String  VIDEOTOVARA1, String  VIDEOTOVARA2, String  VIDEOTOVARA3, String OSTATOK, String SKLADMESTO, String SHABLON, String VISIBILITY, String ZAKUPCENA, String FILENAME, String OPISANIE_POLNOE, String KATALOG_IMAGE, String INDEXTOP) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO TOVARI ([ID], [IDD],[NAME],[MODEL],[OPISANIE],[PRICE],[VALUTA],[MYCSS],[NAV1],[LINKNAV1],[NAV2],[LINKNAV2],[NAV3],[LINKNAV3],[ZAGOLOVOK],[KEYWORDS],[METADESCRIPTION],[FONT],[DOPFONT],[ZAGOLOVOKTEXTCOLOR],[KATEGORIITEXTCOLOR],[PRICETEXTCOLOR],[BACKGROUNDCOLOR],[NAVTEXTCOLOR],[LINK],[CHNAME1],[CHZNACHENIE1],[CHNAME2],[CHZNACHENIE2],[CHNAME3],[CHZNACHENIE3],[CHNAME4],[CHZNACHENIE4],[CHNAME5],[CHZNACHENIE5],[CHNAME6],[CHZNACHENIE6],[CHNAME7],[CHZNACHENIE7],[CHNAME8],[CHZNACHENIE8],[CHNAME9],[CHZNACHENIE9],[CHNAME10],[CHZNACHENIE10],[CHNAME11],[CHZNACHENIE11],[CHNAME12],[CHZNACHENIE12],[CHNAME13],[CHZNACHENIE13],[CHNAME14],[CHZNACHENIE14],[CHNAME15],[CHZNACHENIE15],[CHNAME16],[CHZNACHENIE16],[CHNAME17],[CHZNACHENIE17],[CHNAME18],[CHZNACHENIE18],[CHNAME19],[CHZNACHENIE19],[CHNAME20],[CHZNACHENIE20],[IMGTOVARA1],[IMGTOVARA2],[IMGTOVARA3],[IMGTOVARA4],[IMGTOVARA5],[IMGTOVARA6],[IMGTOVARA7],[IMGTOVARA8],[IMGTOVARA9],[IMGTOVARA10],[VIDEOTOVARA1],[VIDEOTOVARA2],[VIDEOTOVARA3] ,[OSTATOK],[SKLADMESTO],[SHABLON],[VISIBILITY],[ZAKUPCENA],[FILENAME], [OPISANIE_POLNOE], [KATALOG_IMAGE], [INDEXTOP]) VALUES ('" + ID + "' , '" + IDD+ "' , '" +  NAME+ "' , '" +  MODEL+ "' , '" +  OPISANIE+ "' , '" +  PRICE+ "' , '" +  VALUTA+ "' , '" +  MYCSS+ "' , '" +  NAV1+ "' , '" +  LINKNAV1+ "' , '" +  NAV2+ "' , '" +  LINKNAV2+ "' , '" +  NAV3+ "' , '" +  LINKNAV3+ "' , '" +  ZAGOLOVOK+ "' , '" +  KEYWORDS+ "' , '" +  METADESCRIPTION+ "' , '" +  FONT+ "' , '" + DOPFONT+ "' , '" +  ZAGOLOVOKTEXTCOLOR+ "' , '" +  KATEGORIITEXTCOLOR+ "' , '" +  PRICETEXTCOLOR+ "' , '" +  BACKGROUNDCOLOR+ "' , '" +  NAVTEXTCOLOR+ "' , '" +  LINK+ "' , '" +  CHNAME1+ "' , '" +  CHZNACHENIE1+ "' , '" +  CHNAME2+ "' , '" +  CHZNACHENIE2+ "' , '" +  CHNAME3+ "' , '" +  CHZNACHENIE3+ "' , '" +  CHNAME4+ "' , '" +  CHZNACHENIE4+ "' , '" +  CHNAME5+ "' , '" +  CHZNACHENIE5+ "' , '" +  CHNAME6+ "' , '" +  CHZNACHENIE6+ "' , '" + CHNAME7+ "' , '" +  CHZNACHENIE7+ "' , '" +  CHNAME8+ "' , '" +  CHZNACHENIE8+ "' , '" +  CHNAME9+ "' , '" +  CHZNACHENIE9+ "' , '" +  CHNAME10+ "' , '" +  CHZNACHENIE10+ "' , '" +  CHNAME11+ "' , '" +  CHZNACHENIE11+ "' , '" +  CHNAME12+ "' , '" +  CHZNACHENIE12+ "' , '" +  CHNAME13+ "' , '" +  CHZNACHENIE13+ "' , '" +  CHNAME14+ "' , '" +  CHZNACHENIE14+ "' , '" +  CHNAME15+ "' , '" +  CHZNACHENIE15+ "' , '" +  CHNAME16+ "' , '" +  CHZNACHENIE16+ "' , '" +  CHNAME17+ "' , '" +  CHZNACHENIE17+ "' , '" +  CHNAME18+ "' , '" +  CHZNACHENIE18+ "' , '" +  CHNAME19+ "' , '" +  CHZNACHENIE19+ "' , '" +  CHNAME20+ "' , '" +  CHZNACHENIE20+ "' , '" +  IMGTOVARA1+ "' , '" +  IMGTOVARA2+ "' , '" +  IMGTOVARA3+ "' , '" +  IMGTOVARA4+ "' , '" +  IMGTOVARA5+ "' , '" +  IMGTOVARA6+ "' , '" +  IMGTOVARA7+ "' , '" +  IMGTOVARA8+ "' , '" +  IMGTOVARA9+ "' , '" +  IMGTOVARA10+ "' , '" +  VIDEOTOVARA1+ "' , '" +  VIDEOTOVARA2+ "' , '" +  VIDEOTOVARA3+ "' , '" +OSTATOK+ "' , '" +SKLADMESTO+ "' , '" +SHABLON+ "' , '" +VISIBILITY+ "' , '" +ZAKUPCENA+ "' , '"+FILENAME+ "' , '"  +OPISANIE_POLNOE+ "' , '" +KATALOG_IMAGE + "' , '" + INDEXTOP +"'); ");

        db.close();
    }

    public void DelTOVARI(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM TOVARI WHERE ID=" + ID + ";");

        db.close();
    }

    //endregion


    //region КОНТРАГЕНТЫ


    public void AddKontragent(String FullName, String SokrName, String INN, String KPP, String OGRN, String BankName, String BankBIK, String BankKS, String BankRS, String RukDolzhn, String Vlice, String FIOruk, String UrAddr, String FaktAddr, String PostAddr, String Phone, String Mobile, String Email, String Site, String Otvetstvennij) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO KONTRAGENTI ([FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE], [OTVETSTVENNIJ]) VALUES ('" + FullName + "' , '" + SokrName + "', '" + INN + "', '" + KPP + "', '" + OGRN + "', '" + BankName + "', '" + BankBIK + "', '" + BankKS + "', '" + BankRS + "','" + RukDolzhn + "', '" + Vlice + "', '" + FIOruk + "', '" + UrAddr + "', '" + FaktAddr + "', '" + PostAddr + "', '" + Phone + "', '" + Mobile + "','" + Email + "','" + Site + "','" + Otvetstvennij + "'); ");

        db.close();
    }

    public void ChangeKontragent(String ID, String FullName, String SokrName, String INN, String KPP, String OGRN, String BankName, String BankBIK, String BankKS, String BankRS, String RukDolzhn, String Vlice, String FIOruk, String UrAddr, String FaktAddr, String PostAddr, String Phone, String Mobile, String Email, String Site, String Otvetstvennij) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO KONTRAGENTI ([ID],[FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE], [OTVETSTVENNIJ]) VALUES ('" + ID + "' , '" + FullName + "' , '" + SokrName + "', '" + INN + "', '" + KPP + "', '" + OGRN + "', '" + BankName + "', '" + BankBIK + "', '" + BankKS + "', '" + BankRS + "','" + RukDolzhn + "', '" + Vlice + "', '" + FIOruk + "', '" + UrAddr + "', '" + FaktAddr + "', '" + PostAddr + "', '" + Phone + "', '" + Mobile + "','" + Email + "','" + Site + "','" + Otvetstvennij + "'); ");

        db.close();
    }

    public void DelKontragent(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM KONTRAGENTI WHERE ID=" + ID + ";");

        db.close();
    }

//endregion

    //region СДЕЛКИ

    public void AddSdelka(String SdelkaName, String SKontragentFullName, String SKontragentSokrName, String SKontragentINN, String SKontragentKPP, String SKontragentOGRN, String SKontragentBankName, String SKontragentBankBIK, String SKontragentBankKS, String SKontragentBankRS, String SKontragentRukDolzhn, String SKontragentVlice, String SKontragentFIOruk, String SKontragentUrAddr, String SKontragentFaktAddr, String SKontragentPostAddr, String SKontragentPhone, String SKontragentMobile, String SKontragentEmail, String SKontragentSite, String SKontragentOtvetstvennij, String SMyFullName, String SMySokrName, String SMyINN, String SMyKPP, String SMyOGRN, String SMyBankName, String SMyBankBIK, String SMyBankKS, String SMyBankRS, String SMyRukDolzhn, String SMyVlice, String SMyFIOruk, String SMyUrAddr, String SMyFaktAddr, String SMyPostAddr, String SMyPhone, String SMyMobile, String SMyEmail, String SMySite, String SMyOtvetstvennij, String NomerSdelki, String DataSdelki, String GorodSdelki, String DogovorPo, String SrokiPostavkiTovarov, String SrokiOkazanijaUslug, String UslovijaOplatiTovarov, String UslovijaOplatiUslug, String UslovijaPriemkiTovarov, String UslovijaPriemkiUslug, String UslovijaGarantii, String OsobijeUslovija, String Sud, String LogoImg, String PechatImg, String Slogan) {

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("INSERT OR REPLACE INTO SDELKI ([SDELKA_NAME], [K_FULLNAME], [K_SOKRNAME], [K_INN], [K_KPP], [K_OGRN], [K_BANKNAME], [K_BANKBIK], [K_BANKKS], [K_BANKRS], [K_RUKDOLZHN], [K_VLICE], [K_FIORUK], [K_URADDR], [K_FACTADDR], [K_POSTADDR], [K_PHONE], [K_MOBILE], [K_EMAIL], [K_SITE], [K_OTVETSTVENNIJ], [MY_FULLNAME], [MY_SOKRNAME], [MY_INN], [MY_KPP], [MY_OGRN], [MY_BANKNAME], [MY_BANKBIK], [MY_BANKKS], [MY_BANKRS], [MY_RUKDOLZHN], [MY_VLICE], [MY_FIORUK], [MY_URADDR], [MY_FACTADDR], [MY_POSTADDR], [MY_PHONE], [MY_MOBILE], [MY_EMAIL], [MY_SITE], [MY_OTVETSTVENNIJ], [SDELKA_NOMER], [DOGOVOR_DATA], [GOROD_SDELKI], [DOGOVOR_PO], [TOVAR_SROKIPOSTAVKI], [USLUGI_SROKIOKAZANIJA], [TOVAR_USLOVIJAOPLATI], [USLUGI_USLOVIJAOPLATI], [TOVAR_USLOVIJAPRIEMKI], [USLUGI_USLOVIJAPRIEMKI], [GARANTIJA_USLOVIJA], [OSOBIJE_USLOVIJA], [SUD], [STATUS], [MY_SLOGAN], [MY_LOGOIMG], [MY_PECHATIMG]) VALUES ('" + SdelkaName + "' ,'" + SKontragentFullName + "' , '" + SKontragentSokrName + "' , '" + SKontragentINN + "' , '" + SKontragentKPP + "' , '" + SKontragentOGRN + "' , '" + SKontragentBankName + "' , '" + SKontragentBankBIK + "' , '" + SKontragentBankKS + "' , '" + SKontragentBankRS + "' , '" + SKontragentRukDolzhn + "' , '" + SKontragentVlice + "' , '" + SKontragentFIOruk + "' , '" + SKontragentUrAddr + "' , '" + SKontragentFaktAddr + "' , '" + SKontragentPostAddr + "' , '" + SKontragentPhone + "' , '" + SKontragentMobile + "' , '" + SKontragentEmail + "' , '" + SKontragentSite + "' , '" + SKontragentOtvetstvennij + "' , '" + SMyFullName + "' , '" + SMySokrName + "' , '" + SMyINN + "' , '" + SMyKPP + "' , '" + SMyOGRN + "' , '" + SMyBankName + "' , '" + SMyBankBIK + "' , '" + SMyBankKS + "' , '" + SMyBankRS + "' , '" + SMyRukDolzhn + "' , '" + SMyVlice + "' , '" + SMyFIOruk + "' , '" + SMyUrAddr + "' , '" + SMyFaktAddr + "' , '" + SMyPostAddr + "' , '" + SMyPhone + "' , '" + SMyMobile + "' , '" + SMyEmail + "' , '" + SMySite + "' , '" + SMyOtvetstvennij + "' , '" + NomerSdelki + "' , '" + DataSdelki + "' , '" + GorodSdelki + "' , '" + DogovorPo + "' , '" + SrokiPostavkiTovarov + "' , '" + SrokiOkazanijaUslug + "' , '" + UslovijaOplatiTovarov + "' , '" + UslovijaOplatiUslug + "' , '" + UslovijaPriemkiTovarov + "' , '" + UslovijaPriemkiUslug + "' , '" + UslovijaGarantii + "' , '" + OsobijeUslovija + "' , '" + Sud + "' , '" + "s" + "' , '" + Slogan + "' , '" + LogoImg + "' , '" + PechatImg + "'); "); //SQL-запрос на создание БД

        db.close();
    }

    public void ChangeSdelka(String ID, String SdelkaName, String SKontragentFullName, String SKontragentSokrName, String SKontragentINN, String SKontragentKPP, String SKontragentOGRN, String SKontragentBankName, String SKontragentBankBIK, String SKontragentBankKS, String SKontragentBankRS, String SKontragentRukDolzhn, String SKontragentVlice, String SKontragentFIOruk, String SKontragentUrAddr, String SKontragentFaktAddr, String SKontragentPostAddr, String SKontragentPhone, String SKontragentMobile, String SKontragentEmail, String SKontragentSite, String SKontragentOtvetstvennij, String SMyFullName, String SMySokrName, String SMyINN, String SMyKPP, String SMyOGRN, String SMyBankName, String SMyBankBIK, String SMyBankKS, String SMyBankRS, String SMyRukDolzhn, String SMyVlice, String SMyFIOruk, String SMyUrAddr, String SMyFaktAddr, String SMyPostAddr, String SMyPhone, String SMyMobile, String SMyEmail, String SMySite, String SMyOtvetstvennij, String NomerSdelki, String DataSdelki, String GorodSdelki, String UslugiPo, String SrokiPostavkiTovarov, String SrokiOkazanijaUslug, String UslovijaOplatiTovarov, String UslovijaOplatiUslug, String UslovijaPriemkiTovarov, String UslovijaPriemkiUslug, String UslovijaGarantii, String OsobijeUslovija, String Sud, String Status, String LogoImg, String PechatImg, String Slogan, String KPNomer, String KPData, String SchetNomer, String SchetData, String AktNomer, String AktData, String NakladnajaNomer, String NakladnajaData, String StoimostTovarov, String StoimostUslug, String StoimostItogo)

//KP_NOMER Varchar(255), KP_DATA Varchar(255),  SCHET_NOMER Varchar(255), SCHET_DATA Varchar(255), AKT_NOMER Varchar(255), AKT_DATA Varchar(255), NAKLADNAJA_NOMER Varchar(255), NAKLADNAJA_DATA Varchar(255), TEHZAKL_NOMER Varchar (255), TEHZAKL_DATA Varchar(255), TEHZAKL_TEXT Varchar(2000)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO SDELKI ([ID], [SDELKA_NAME], [K_FULLNAME], [K_SOKRNAME],[K_INN],[K_KPP], [K_OGRN], [K_BANKNAME], [K_BANKBIK], [K_BANKKS], [K_BANKRS], [K_RUKDOLZHN], [K_VLICE], [K_FIORUK], [K_URADDR], [K_FACTADDR], [K_POSTADDR], [K_PHONE], [K_MOBILE], [K_EMAIL], [K_SITE], [K_OTVETSTVENNIJ], [MY_FULLNAME], [MY_SOKRNAME], [MY_INN], [MY_KPP], [MY_OGRN], [MY_BANKNAME], [MY_BANKBIK], [MY_BANKKS], [MY_BANKRS], [MY_RUKDOLZHN], [MY_VLICE], [MY_FIORUK], [MY_URADDR], [MY_FACTADDR], [MY_POSTADDR], [MY_PHONE], [MY_MOBILE], [MY_EMAIL], [MY_SITE], [MY_OTVETSTVENNIJ], [SDELKA_NOMER], [DOGOVOR_DATA], [GOROD_SDELKI], [DOGOVOR_PO], [TOVAR_SROKIPOSTAVKI], [USLUGI_SROKIOKAZANIJA], [TOVAR_USLOVIJAOPLATI], [USLUGI_USLOVIJAOPLATI], [TOVAR_USLOVIJAPRIEMKI], [USLUGI_USLOVIJAPRIEMKI], [GARANTIJA_USLOVIJA], [OSOBIJE_USLOVIJA], [SUD], [STATUS], [MY_SLOGAN], [MY_LOGOIMG], [MY_PECHATIMG], [KP_NOMER], [KP_DATA], [SCHET_NOMER], [SCHET_DATA], [AKT_NOMER], [AKT_DATA], [NAKLADNAJA_NOMER], [NAKLADNAJA_DATA], [STOIMOSTSDELKI_TOVARI], [STOIMOSTSDELKI_USLUGI], [STOIMOSTSDELKI]) VALUES ('" + ID + "' ,'" + SdelkaName + "' ,'" + SKontragentFullName + "' , '" + SKontragentSokrName + "' , '" + SKontragentINN + "' , '" + SKontragentKPP + "' , '" + SKontragentOGRN + "' , '" + SKontragentBankName + "' , '" + SKontragentBankBIK + "' , '" + SKontragentBankKS + "' , '" + SKontragentBankRS + "' , '" + SKontragentRukDolzhn + "' , '" + SKontragentVlice + "' , '" + SKontragentFIOruk + "' , '" + SKontragentUrAddr + "' , '" + SKontragentFaktAddr + "' , '" + SKontragentPostAddr + "' , '" + SKontragentPhone + "' , '" + SKontragentMobile + "' , '" + SKontragentEmail + "' , '" + SKontragentSite + "' , '" + SKontragentOtvetstvennij + "' , '" + SMyFullName + "' , '" + SMySokrName + "' , '" + SMyINN + "' , '" + SMyKPP + "' , '" + SMyOGRN + "' , '" + SMyBankName + "' , '" + SMyBankBIK + "' , '" + SMyBankKS + "' , '" + SMyBankRS + "' , '" + SMyRukDolzhn + "' , '" + SMyVlice + "' , '" + SMyFIOruk + "' , '" + SMyUrAddr + "' , '" + SMyFaktAddr + "' , '" + SMyPostAddr + "' , '" + SMyPhone + "' , '" + SMyMobile + "' , '" + SMyEmail + "' , '" + SMySite + "' , '" + SMyOtvetstvennij + "' , '" + NomerSdelki + "' , '" + DataSdelki + "' , '" + GorodSdelki + "' , '" + UslugiPo + "' , '" + SrokiPostavkiTovarov + "' , '" + SrokiOkazanijaUslug + "' , '" + UslovijaOplatiTovarov + "' , '" + UslovijaOplatiUslug + "' , '" + UslovijaPriemkiTovarov + "' , '" + UslovijaPriemkiUslug + "' , '" + UslovijaGarantii + "' , '" + OsobijeUslovija + "' , '" + Sud + "' , '" + Status + "' , '" + Slogan + "' , '" + LogoImg + "' , '" + PechatImg + "' , '" + KPNomer + "' , '" + KPData + "' , '" + SchetNomer + "' , '" + SchetData + "' , '" + AktNomer + "' , '" + AktData + "' , '" + NakladnajaNomer + "' , '" + NakladnajaData + "' , '" + StoimostTovarov+ "' , '" +StoimostUslug+ "' , '" +StoimostItogo + "'); "); //SQL-запрос на создание БД

        db.close();
    }

    public void DelSdelka(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM SDELKI WHERE ID=" + ID + ";");

        db.close();
    }


    //endregion

    //region АВТОМОБИЛИ

    public void AddAUTO(String Marka,
                        String Gosnomer,
                        String Color,
                        String Tehosmotrnomer,
                        Integer Tehosmotrdata_D,
                        Integer Tehosmotrdata_M,
                        Integer Tehosmotrdata_Y,
                        Integer Tehosmotrsleddata_D,
                        Integer Tehosmotrsleddata_M,
                        Integer Tehosmotrsleddata_Y,
                        Integer Osagodata_D,
                        Integer Osagodata_M,
                        Integer Osagodata_Y,
                        String Osagonomer,
                        String Osagocompany,
                        Integer Osagodataend_D,
                        Integer Osagodataend_M,
                        Integer Osagodataend_Y,
                        Integer Osagoprevdata_D,
                        Integer Osagoprevdata_M,
                        Integer Osagoprevdata_Y,
                        String Osagoprevnomer,
                        String Osagoprevcompany,
                        Integer Osagoprevdataend_D,
                        Integer Osagoprevdataend_M,
                        Integer Osagoprevdataend_Y,
                        Integer Kaskodata_D,
                        Integer Kaskodata_M,
                        Integer Kaskodata_Y,
                        String Kaskonomer,
                        String Kaskocompany,
                        Integer Kaskodataend_D,
                        Integer Kaskodataend_M,
                        Integer Kaskodataend_Y,
                        Integer Kaskoprevdata_D,
                        Integer Kaskoprevdata_M,
                        Integer Kaskoprevdata_Y,
                        String Kaskoprevnomer,
                        String Kaskoprevcompany,
                        Integer Kaskoprevdataend_D,
                        Integer Kaskoprevdataend_M,
                        Integer Kaskoprevdataend_Y,
                        Integer Autopokupkadata_D,
                        Integer Autopokupkadata_M,
                        Integer Autopokupkadata_Y,
                        String Autodocument,
                        Integer Autodocumentdata_D,
                        Integer Autodocumentdata_M,
                        Integer Autodocumentdata_Y,
                        Integer Autozeroprobeg,
                        String Autopts,
                        String Autosts,
                        String Autovladelec,
                        String Autovin,
                        String Autoengine,
                        String Primechanie,
                        String Voditel1IDD,
                        String Voditel2IDD) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO AUTO ([MARKA],[GOSNOMER],[COLOR],[TEHOSMOTRNOMER],[TEHOSMOTRDATA_D],[TEHOSMOTRDATA_M],[TEHOSMOTRDATA_Y],[TEHOSMOTRSLEDDATA_D],[TEHOSMOTRSLEDDATA_M],[TEHOSMOTRSLEDDATA_Y],[OSAGODATA_D],[OSAGODATA_M],[OSAGODATA_Y],[OSAGONOMER],[OSAGOCOMPANY],[OSAGODATAEND_D],[OSAGODATAEND_M],[OSAGODATAEND_Y],[OSAGOPREVDATA_D],[OSAGOPREVDATA_M],[OSAGOPREVDATA_Y],[OSAGOPREVNOMER],[OSAGOPREVCOMPANY],[OSAGOPREVDATAEND_D],[OSAGOPREVDATAEND_M],[OSAGOPREVDATAEND_Y],[KASKODATA_D],[KASKODATA_M],[KASKODATA_Y],[KASKONOMER],[KASKOCOMPANY],[KASKODATAEND_D],[KASKODATAEND_M],[KASKODATAEND_Y],[KASKOPREVDATA_D],[KASKOPREVDATA_M],[KASKOPREVDATA_Y],[KASKOPREVNOMER],[KASKOPREVCOMPANY],[KASKOPREVDATAEND_D],[KASKOPREVDATAEND_M],[KASKOPREVDATAEND_Y],[AUTOpokupkaDATA_D],[AUTOpokupkaDATA_M],[AUTOpokupkaDATA_Y],[AUTOdocument],[AUTOdocumentDATA_D],[AUTOdocumentDATA_M],[AUTOdocumentDATA_Y],[AUTOzeroPROBEG],[AUTOPTS],[AUTOSTS],[AUTOVLADELEC],[AUTOVIN],[AUTOENGINE],[PRIMECHANIE],[VODITEL1_IDD],[VODITEL2_IDD]) VALUES ('" + Marka+ "' , '" +Gosnomer+ "' , '" +Color+ "' , '" +Tehosmotrnomer+ "' , '" +Tehosmotrdata_D+ "' , '"+Tehosmotrdata_M+ "' , '"+ Tehosmotrdata_Y+ "' , '"  +Tehosmotrsleddata_D+ "' , '"+Tehosmotrsleddata_M+ "' , '"+Tehosmotrsleddata_Y+ "' , '" +Osagodata_D+ "' , '"+Osagodata_M+ "' , '"+Osagodata_Y+ "' , '" +Osagonomer+ "' , '" +Osagocompany+ "' , '" +Osagodataend_D+ "' , '"+Osagodataend_M+ "' , '"+Osagodataend_Y+ "' , '" +Osagoprevdata_D+ "' , '"+Osagoprevdata_M+ "' , '"+Osagoprevdata_Y+ "' , '" +Osagoprevnomer+ "' , '" +Osagoprevcompany+ "' , '" +Osagoprevdataend_D+ "' , '"+Osagoprevdataend_M+ "' , '"+Osagoprevdataend_Y+ "' , '" +Kaskodata_D+ "' , '"+Kaskodata_M+ "' , '"+Kaskodata_Y+ "' , '" +Kaskonomer+ "' , '" +Kaskocompany+ "' , '" +Kaskodataend_D+ "' , '"+Kaskodataend_M+ "' , '"+Kaskodataend_Y+ "' , '" +Kaskoprevdata_D+ "' , '"+Kaskoprevdata_M+ "' , '"+Kaskoprevdata_Y+ "' , '" +Kaskoprevnomer+ "' , '" +Kaskoprevcompany+ "' , '" +Kaskoprevdataend_D+ "' , '"+Kaskoprevdataend_M+ "' , '" +Kaskoprevdataend_Y+ "' , '" +Autopokupkadata_D+ "' , '"+Autopokupkadata_M+ "' , '"+Autopokupkadata_Y+ "' , '" +Autodocument+ "' , '" +Autodocumentdata_D+ "' , '"+Autodocumentdata_M+ "' , '"+Autodocumentdata_Y+ "' , '" +Autozeroprobeg+ "' , '" +Autopts+ "' , '" +Autosts+ "' , '" +Autovladelec+ "' , '" +Autovin+ "' , '" +Autoengine+ "' , '" +Primechanie+ "' , '" +Voditel1IDD+ "' , '" + Voditel2IDD+"'); ");

        db.close();
    }


    public void ChangeAUTO(String Id,
                           String Marka,
                           String Gosnomer,
                           String Color,
                           String Tehosmotrnomer,
                           Integer Tehosmotrdata_D,
                           Integer Tehosmotrdata_M,
                           Integer Tehosmotrdata_Y,
                           Integer Tehosmotrsleddata_D,
                           Integer Tehosmotrsleddata_M,
                           Integer Tehosmotrsleddata_Y,
                           Integer Osagodata_D,
                           Integer Osagodata_M,
                           Integer Osagodata_Y,
                           String Osagonomer,
                           String Osagocompany,
                           Integer Osagodataend_D,
                           Integer Osagodataend_M,
                           Integer Osagodataend_Y,
                           Integer Osagoprevdata_D,
                           Integer Osagoprevdata_M,
                           Integer Osagoprevdata_Y,
                           String Osagoprevnomer,
                           String Osagoprevcompany,
                           Integer Osagoprevdataend_D,
                           Integer Osagoprevdataend_M,
                           Integer Osagoprevdataend_Y,
                           Integer Kaskodata_D,
                           Integer Kaskodata_M,
                           Integer Kaskodata_Y,
                           String Kaskonomer,
                           String Kaskocompany,
                           Integer Kaskodataend_D,
                           Integer Kaskodataend_M,
                           Integer Kaskodataend_Y,
                           Integer Kaskoprevdata_D,
                           Integer Kaskoprevdata_M,
                           Integer Kaskoprevdata_Y,
                           String Kaskoprevnomer,
                           String Kaskoprevcompany,
                           Integer Kaskoprevdataend_D,
                           Integer Kaskoprevdataend_M,
                           Integer Kaskoprevdataend_Y,
                           Integer Autopokupkadata_D,
                           Integer Autopokupkadata_M,
                           Integer Autopokupkadata_Y,
                           String Autodocument,
                           Integer Autodocumentdata_D,
                           Integer Autodocumentdata_M,
                           Integer Autodocumentdata_Y,
                           Integer Autozeroprobeg,
                           String Autopts,
                           String Autosts,
                           String Autovladelec,
                           String Autovin,
                           String Autoengine,
                           String Primechanie,
                           String Voditel1IDD,
                           String Voditel2IDD) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO AUTO ([ID],[MARKA],[GOSNOMER],[COLOR],[TEHOSMOTRNOMER],[TEHOSMOTRDATA_D],[TEHOSMOTRDATA_M],[TEHOSMOTRDATA_Y],[TEHOSMOTRSLEDDATA_D],[TEHOSMOTRSLEDDATA_M],[TEHOSMOTRSLEDDATA_Y],[OSAGODATA_D],[OSAGODATA_M],[OSAGODATA_Y],[OSAGONOMER],[OSAGOCOMPANY],[OSAGODATAEND_D],[OSAGODATAEND_M],[OSAGODATAEND_Y],[OSAGOPREVDATA_D],[OSAGOPREVDATA_M],[OSAGOPREVDATA_Y],[OSAGOPREVNOMER],[OSAGOPREVCOMPANY],[OSAGOPREVDATAEND_D],[OSAGOPREVDATAEND_M],[OSAGOPREVDATAEND_Y],[KASKODATA_D],[KASKODATA_M],[KASKODATA_Y],[KASKONOMER],[KASKOCOMPANY],[KASKODATAEND_D],[KASKODATAEND_M],[KASKODATAEND_Y],[KASKOPREVDATA_D],[KASKOPREVDATA_M],[KASKOPREVDATA_Y],[KASKOPREVNOMER],[KASKOPREVCOMPANY],[KASKOPREVDATAEND_D],[KASKOPREVDATAEND_M],[KASKOPREVDATAEND_Y],[AUTOpokupkaDATA_D],[AUTOpokupkaDATA_M],[AUTOpokupkaDATA_Y],[AUTOdocument],[AUTOdocumentDATA_D],[AUTOdocumentDATA_M],[AUTOdocumentDATA_Y],[AUTOzeroPROBEG],[AUTOPTS],[AUTOSTS],[AUTOVLADELEC],[AUTOVIN],[AUTOENGINE],[PRIMECHANIE],[VODITEL1_IDD],[VODITEL2_IDD]) VALUES ('" + Id + "' , '" +Marka+ "' , '" +Gosnomer+ "' , '" +Color+ "' , '" +Tehosmotrnomer+ "' , '" +Tehosmotrdata_D+ "' , '"+Tehosmotrdata_M+ "' , '"+ Tehosmotrdata_Y+ "' , '"  +Tehosmotrsleddata_D+ "' , '"+Tehosmotrsleddata_M+ "' , '"+Tehosmotrsleddata_Y+ "' , '" +Osagodata_D+ "' , '"+Osagodata_M+ "' , '"+Osagodata_Y+ "' , '" +Osagonomer+ "' , '" +Osagocompany+ "' , '" +Osagodataend_D+ "' , '"+Osagodataend_M+ "' , '"+Osagodataend_Y+ "' , '" +Osagoprevdata_D+ "' , '"+Osagoprevdata_M+ "' , '"+Osagoprevdata_Y+ "' , '" +Osagoprevnomer+ "' , '" +Osagoprevcompany+ "' , '" +Osagoprevdataend_D+ "' , '"+Osagoprevdataend_M+ "' , '"+Osagoprevdataend_Y+ "' , '" +Kaskodata_D+ "' , '"+Kaskodata_M+ "' , '"+Kaskodata_Y+ "' , '" +Kaskonomer+ "' , '" +Kaskocompany+ "' , '" +Kaskodataend_D+ "' , '"+Kaskodataend_M+ "' , '"+Kaskodataend_Y+ "' , '" +Kaskoprevdata_D+ "' , '"+Kaskoprevdata_M+ "' , '"+Kaskoprevdata_Y+ "' , '" +Kaskoprevnomer+ "' , '" +Kaskoprevcompany+ "' , '" +Kaskoprevdataend_D+ "' , '"+Kaskoprevdataend_M+ "' , '" +Kaskoprevdataend_Y+ "' , '" +Autopokupkadata_D+ "' , '"+Autopokupkadata_M+ "' , '"+Autopokupkadata_Y+ "' , '" +Autodocument+ "' , '" +Autodocumentdata_D+ "' , '"+Autodocumentdata_M+ "' , '"+Autodocumentdata_Y+ "' , '" +Autozeroprobeg+ "' , '" +Autopts+ "' , '" +Autosts+ "' , '" +Autovladelec+ "' , '" +Autovin+ "' , '" +Autoengine+ "' , '" +Primechanie+ "' , '" +Voditel1IDD+ "' , '" + Voditel2IDD+"'); ");

        db.close();
    }

    public void DelAUTO(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM AUTO WHERE ID=" + Id + ";");

        db.close();
    }


    //endregion

    //region АВТОРАСХОДЫ

    public void AddAUTORASHODY (String AUTOTYPE, String AUTOEDIZM, Float AUTOKOLVO, Float AUTOCENAED, Float AUTOSTOIMOST, String AUTONAIMENOVANIE, String AUTONOMERCHEKA, String AUTONOMERZAKAZNARJADA, Integer AUTODATA_D, Integer AUTODATA_M, Integer AUTODATA_Y, Integer AUTOSROKKONCAGARANTII_D, Integer AUTOSROKKONCAGARANTII_M, Integer AUTOSROKKONCAGARANTII_Y, Integer AUTOPROBEG, String AUTOIDV, Integer AUTOENDRESURSDATA_D, Integer AUTOENDRESURSDATA_M, Integer AUTOENDRESURSDATA_Y, Integer AUTOENDRESURSPROBEG, String AUTOIDD) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO AUTORASHODY ([autoTYPE], [autoEDIZM], [autoKOLVO], [autoCENAED], [autoSTOIMOST], [autoNAIMENOVANIE], [autoNOMERCHEKA], [autoNOMERZAKAZNARJADA], [autoDATA_D], [autoDATA_M], [autoDATA_Y], [autoSROKKONCAGARANTII_D], [autoSROKKONCAGARANTII_M], [autoSROKKONCAGARANTII_Y], [autoPROBEG], [IDV], [autoENDRESURSDATA_D], [autoENDRESURSDATA_M], [autoENDRESURSDATA_Y], [autoENDRESURSPROBEG], [IDD]) VALUES ('" + AUTOTYPE + "' , '"+ AUTOEDIZM + "' , '" + AUTOKOLVO + "' , '" + AUTOCENAED + "' , '" + AUTOSTOIMOST + "' , '" + AUTONAIMENOVANIE + "' , '" + AUTONOMERCHEKA + "' , '" + AUTONOMERZAKAZNARJADA + "' , '" + AUTODATA_D + "' , '" + AUTODATA_M + "' , '" + AUTODATA_Y + "' , '" +  AUTOSROKKONCAGARANTII_D + "' , '" + AUTOSROKKONCAGARANTII_M + "' , '" + AUTOSROKKONCAGARANTII_Y + "' , '" + AUTOPROBEG + "' , '" + AUTOIDV + "' , '" + AUTOENDRESURSDATA_D + "' , '" + AUTOENDRESURSDATA_M + "' , '" +  AUTOENDRESURSDATA_Y + "' , '" + AUTOENDRESURSPROBEG + "' , '" + AUTOIDD + "'); ");

        db.close();
    }


    public void ChangeAUTORASHODY(String Id, String AUTOTYPE, String AUTOEDIZM, Float AUTOKOLVO, Float AUTOCENAED, Float AUTOSTOIMOST, String AUTONAIMENOVANIE, String AUTONOMERCHEKA, String AUTONOMERZAKAZNARJADA, Integer AUTODATA_D, Integer AUTODATA_M, Integer AUTODATA_Y, Integer AUTOSROKKONCAGARANTII_D, Integer AUTOSROKKONCAGARANTII_M, Integer AUTOSROKKONCAGARANTII_Y, Integer AUTOPROBEG, String AUTOIDV, Integer AUTOENDRESURSDATA_D, Integer AUTOENDRESURSDATA_M, Integer AUTOENDRESURSDATA_Y, Integer AUTOENDRESURSPROBEG, String AUTOIDD) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO AUTORASHODY ([ID], [autoTYPE], [autoEDIZM], [autoKOLVO], [autoCENAED], [autoSTOIMOST], [autoNAIMENOVANIE], [autoNOMERCHEKA], [autoNOMERZAKAZNARJADA], [autoDATA_D], [autoDATA_M], [autoDATA_Y], [autoSROKKONCAGARANTII_D], [autoSROKKONCAGARANTII_M], [autoSROKKONCAGARANTII_Y], [autoPROBEG], [IDV], [autoENDRESURSDATA_D], [autoENDRESURSDATA_M], [autoENDRESURSDATA_Y], [autoENDRESURSPROBEG], [IDD]) VALUES ('" + Id+ "' , '" +AUTOTYPE + "' , '" + AUTOEDIZM + "' , '" + AUTOKOLVO + "' , '" + AUTOCENAED + "' , '" + AUTOSTOIMOST + "' , '" + AUTONAIMENOVANIE + "' , '" + AUTONOMERCHEKA + "' , '" + AUTONOMERZAKAZNARJADA + "' , '" + AUTODATA_D + "' , '" + AUTODATA_M + "' , '" + AUTODATA_Y + "' , '" + AUTOSROKKONCAGARANTII_D + "' , '" + AUTOSROKKONCAGARANTII_M + "' , '" + AUTOSROKKONCAGARANTII_Y + "' , '" + AUTOPROBEG + "' , '" + AUTOIDV + "' , '" + AUTOENDRESURSDATA_D + "' , '" + AUTOENDRESURSDATA_M + "' , '" + AUTOENDRESURSDATA_Y + "' , '" + AUTOENDRESURSPROBEG + "' , '" + AUTOIDD + "'); ");

        db.close();
    }

    public void DelAUTORASHODY (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM AUTORASHODY WHERE ID=" + Id + ";");

        db.close();
    }

    //endregion

    //region СОТРУДНИКИ

    public void AddSOTRUDNIK (String sotrudnik_FIO,
                              String sotrudnik_DATAROZHDENIJA,
                              String sotrudnik_INN,
                              String sotrudnik_INNDATA,
                              String sotrudnik_SNILS,
                              String sotrudnik_SNILSDATA,
                              String sotrudnik_OPIT,
                              String sotrudnik_SEMJA,
                              String sotrudnik_OBRAZOVANIJE,
                              String sotrudnik_DOLZHNOST,
                              String sotrudnik_DOLZHNOSTNAJAINSTRUKCIJA,
                              String sotrudnik_DOGOVOR,
                              String sotrudnik_DOGOVORTYPE,
                              String sotrudnik_DOGOVORDATA,
                              String sotrudnik_IDMYCOMPANY,
                              String sotrudnik_VODITEL,
                              String sotrudnik_PRAVA,
                              String sotrudnik_PRAVADATA,
                              String sotrudnik_PRAVADATAOKONCHANIJA,
                              String sotrudnik_PRAVAKATEGORIJA,
                              String sotrudnik_CHARAKTERISTIKA,
                              String sotrudnik_EFFEKTIVNOST,
                              String sotrudnik_DATAUVOLNENIJA,
                              String sotrudnik_OSNOVANIJEUVOLNENIJA,
                              String sotrudnik_DOCUMENTUVOLNENIJE,
                              String sotrudnik_PRIMECHANIE) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO SOTRUDNIKI ([SOTRUDNIK_FIO],[SOTRUDNIK_DATAROZHDENIJA],[SOTRUDNIK_INN],[SOTRUDNIK_INNDATA],[SOTRUDNIK_SNILS],[SOTRUDNIK_SNILSDATA],[SOTRUDNIK_OPIT],[SOTRUDNIK_SEMJA],[SOTRUDNIK_OBRAZOVANIJE],[SOTRUDNIK_DOLZHNOST],[SOTRUDNIK_DOLZHNOSTNAJAINSTRUKCIJA],[SOTRUDNIK_DOGOVOR],[SOTRUDNIK_DOGOVORTYPE],[SOTRUDNIK_DOGOVORDATA],[SOTRUDNIK_IDMYCOMPANY],[SOTRUDNIK_VODITEL],[SOTRUDNIK_PRAVA],[SOTRUDNIK_PRAVADATA],[SOTRUDNIK_PRAVADATAOKONCHANIJA],[SOTRUDNIK_PRAVAKATEGORIJA],[SOTRUDNIK_CHARAKTERISTIKA],[SOTRUDNIK_EFFEKTIVNOST],[SOTRUDNIK_DATAUVOLNENIJA],[SOTRUDNIK_OSNOVANIJEUVOLNENIJA],[SOTRUDNIK_DOCUMENTUVOLNENIJE],[SOTRUDNIK_PRIMECHANIE]) VALUES ('" + sotrudnik_FIO + "' , '" + sotrudnik_DATAROZHDENIJA+ "' , '" + sotrudnik_INN+ "' , '" + sotrudnik_INNDATA+ "' , '" + sotrudnik_SNILS+ "' , '" + sotrudnik_SNILSDATA+ "' , '" + sotrudnik_OPIT+ "' , '" + sotrudnik_SEMJA+ "' , '" + sotrudnik_OBRAZOVANIJE+ "' , '" + sotrudnik_DOLZHNOST+ "' , '" + sotrudnik_DOLZHNOSTNAJAINSTRUKCIJA+ "' , '" + sotrudnik_DOGOVOR+ "' , '" + sotrudnik_DOGOVORTYPE+ "' ,'" + sotrudnik_DOGOVORDATA+ "' , '" + sotrudnik_IDMYCOMPANY+ "' , '" + sotrudnik_VODITEL+ "' , '" + sotrudnik_PRAVA+ "' , '" + sotrudnik_PRAVADATA+ "' , '" + sotrudnik_PRAVADATAOKONCHANIJA+ "' , '" + sotrudnik_PRAVAKATEGORIJA+ "' , '" + sotrudnik_CHARAKTERISTIKA+ "' , '" + sotrudnik_EFFEKTIVNOST+ "' , '" + sotrudnik_DATAUVOLNENIJA+ "' , '" + sotrudnik_OSNOVANIJEUVOLNENIJA+ "' , '" + sotrudnik_DOCUMENTUVOLNENIJE+ "' , '" + sotrudnik_PRIMECHANIE + "'); ");

        db.close();
    }


    public void ChangeSOTRUDNIK(String Id,
                                String sotrudnik_FIO,
                                String sotrudnik_DATAROZHDENIJA,
                                String sotrudnik_INN,
                                String sotrudnik_INNDATA,
                                String sotrudnik_SNILS,
                                String sotrudnik_SNILSDATA,
                                String sotrudnik_OPIT,
                                String sotrudnik_SEMJA,
                                String sotrudnik_OBRAZOVANIJE,
                                String sotrudnik_DOLZHNOST,
                                String sotrudnik_DOLZHNOSTNAJAINSTRUKCIJA,
                                String sotrudnik_DOGOVOR,
                                String sotrudnik_DOGOVORTYPE,
                                String sotrudnik_DOGOVORDATA,
                                String sotrudnik_IDMYCOMPANY,
                                String sotrudnik_VODITEL,
                                String sotrudnik_PRAVA,
                                String sotrudnik_PRAVADATA,
                                String sotrudnik_PRAVADATAOKONCHANIJA,
                                String sotrudnik_PRAVAKATEGORIJA,
                                String sotrudnik_CHARAKTERISTIKA,
                                String sotrudnik_EFFEKTIVNOST,
                                String sotrudnik_DATAUVOLNENIJA,
                                String sotrudnik_OSNOVANIJEUVOLNENIJA,
                                String sotrudnik_DOCUMENTUVOLNENIJE,
                                String sotrudnik_PRIMECHANIE) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO SOTRUDNIKI ([ID], [SOTRUDNIK_FIO],[SOTRUDNIK_DATAROZHDENIJA],[SOTRUDNIK_INN],[SOTRUDNIK_INNDATA],[SOTRUDNIK_SNILS],[SOTRUDNIK_SNILSDATA],[SOTRUDNIK_OPIT],[SOTRUDNIK_SEMJA],[SOTRUDNIK_OBRAZOVANIJE],[SOTRUDNIK_DOLZHNOST],[SOTRUDNIK_DOLZHNOSTNAJAINSTRUKCIJA],[SOTRUDNIK_DOGOVOR],[SOTRUDNIK_DOGOVORTYPE],[SOTRUDNIK_DOGOVORDATA],[SOTRUDNIK_IDMYCOMPANY],[SOTRUDNIK_VODITEL],[SOTRUDNIK_PRAVA],[SOTRUDNIK_PRAVADATA],[SOTRUDNIK_PRAVADATAOKONCHANIJA],[SOTRUDNIK_PRAVAKATEGORIJA],[SOTRUDNIK_CHARAKTERISTIKA],[SOTRUDNIK_EFFEKTIVNOST],[SOTRUDNIK_DATAUVOLNENIJA],[SOTRUDNIK_OSNOVANIJEUVOLNENIJA],[SOTRUDNIK_DOCUMENTUVOLNENIJE],[SOTRUDNIK_PRIMECHANIE]) VALUES ('" + Id + "' , '"  + sotrudnik_FIO + "' , '" + sotrudnik_DATAROZHDENIJA+ "' , '" + sotrudnik_INN+ "' , '" + sotrudnik_INNDATA+ "' , '" + sotrudnik_SNILS+ "' , '" + sotrudnik_SNILSDATA+ "' , '" + sotrudnik_OPIT+ "' , '" + sotrudnik_SEMJA+ "' , '" + sotrudnik_OBRAZOVANIJE+ "' , '" + sotrudnik_DOLZHNOST+ "' , '" + sotrudnik_DOLZHNOSTNAJAINSTRUKCIJA+ "' , '" + sotrudnik_DOGOVOR+ "' , '" + sotrudnik_DOGOVORTYPE+ "' ,'" + sotrudnik_DOGOVORDATA+ "' , '" + sotrudnik_IDMYCOMPANY+ "' , '" + sotrudnik_VODITEL+ "' , '" + sotrudnik_PRAVA+ "' , '" + sotrudnik_PRAVADATA+ "' , '" + sotrudnik_PRAVADATAOKONCHANIJA+ "' , '" + sotrudnik_PRAVAKATEGORIJA+ "' , '" + sotrudnik_CHARAKTERISTIKA+ "' , '" + sotrudnik_EFFEKTIVNOST+ "' , '" + sotrudnik_DATAUVOLNENIJA+ "' , '" + sotrudnik_OSNOVANIJEUVOLNENIJA+ "' , '" + sotrudnik_DOCUMENTUVOLNENIJE+ "' , '" + sotrudnik_PRIMECHANIE + "'); ");

        db.close();
    }

    public void DelSOTRUDNIK(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM SOTRUDNIKI WHERE ID=" + Id + ";");

        db.close();
    }

    //endregion
    
    //region САЙТЫ

    public void AddSite(String Name, String Addres, String FTPaddres, String FTPport, String FTPuser, String FTPpass) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO SITES ([NAME],[ADDRES],[FTPADDRES],[FTPPORT], [FTPUSER], [FTPPASS]) VALUES ('" + Name + "' , '" + Addres + "' , '" + FTPaddres + "', '" + FTPport + "', '" + FTPuser + "', '" + FTPpass+ "'); ");

        db.close();
    }


    public void ChangeSite(String Id, String Name, String Addres, String FTPaddres, String FTPport, String FTPuser, String FTPpass) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT OR REPLACE INTO SITES ([ID], [NAME],[ADDRES],[FTPADDRES],[FTPPORT], [FTPUSER], [FTPPASS]) VALUES ('"+ Id + "' , '" +  Name + "' , '" + Addres + "' , '" + FTPaddres + "', '" + FTPport + "', '" + FTPuser + "', '" + FTPpass+ "'); ");
        db.close();
    }

    public void DelSite(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM SITES WHERE ID=" + ID + ";");

        db.close();
    }

//endregion

    


    //region ЭКСПОРТ БАЗЫ ДАННЫХ

    public void ExportDB() {
        String inputPath = "";
        String inputFile = "";
        String outputPath = "";
        copyFile(inputPath, inputFile, outputPath);
    }

    //endregion

    //region КОПИРОВАТЬ ФАЙЛ

    private void copyFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        } catch (FileNotFoundException fnfe1) {
            Log.e("copyerrortag", fnfe1.getMessage());
        } catch (Exception e) {
            Log.e("copyerrortag", e.getMessage());
        }

    }

    //endregion

}
