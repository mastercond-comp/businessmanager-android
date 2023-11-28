package ru.mastercond;

//region ДАННЫЕ АВТОМОБИЛЯ ИЗ АВТОПАРКА

//  1.  МАРКА, ГОСНОМЕР, ЦВЕТ
//          MARKA, GOSNOMER, COLOR
//  2.  НОМЕР ДИАГНОСТИЧЕСКОЙ КАРТЫ, ДАТА ТЕХОСМОТРА, ДАТА СЛЕДУЮЩЕГО ТЕХОСМОТРА
//          TEHOSMOTRNOMER, TEHOSMOTRDATA, TEHOSMOTRSLEDDATA
//  3.  ДАТА ТЕКУЩЕЙ СТРАХОВКИ ОСАГО, НОМЕР СТРАХОВКИ ОСАГО, СТРАХОВАЯ КОМПАНИЯ, ДАТА ОКОНЧАНИЯ СТРАХОВКИ ОСАГО
//          OSAGODATA, OSAGONOMER, OSAGOCOMPANY, OSAGODATAEND
//  4.  ДАТА ПРЕДЫДУЩЕЙ СТРАХОВКИ ОСАГО, НОМЕР ПРЕДЫДУЩЕЙ СТРАХОВКИ ОСАГО, СТРАХОВАЯ КОМПАНИЯ, ДАТА ОКОНЧАНИЯ ПРЕДЫДУЩЕЙ СТРАХОВКИ ОСАГО
//          OSAGOPREVDATA, OSAGOPREVNOMER, OSAGOPREVCOMPANY, OSAGOPREVDATAEND
//  5.  ДАТА ТЕКУЩЕЙ СТРАХОВКИ КАСКО, НОМЕР СТРАХОВКИ КАСКО, СТРАХОВАЯ КОМПАНИЯ КАСКО, ДАТА ОКОНЧАНИЯ СТРАХОВКИ КАСКО
//          KASKODATA, KASKONOMER, KASKOCOMPANY, KASKODATAEND
//  6.  ДАТА ПРЕДЫДУЩЕЙ СТРАХОВКИ КАСКО, НОМЕР ПРЕДЫДУЩЕЙ СТРАХОВКИ КАСКО, СТРАХОВАЯ КОМПАНИЯ КАСКО, ДАТА ОКОНЧАНИЯ ПРЕДЫДУЩЕЙ СТРАХОВКИ КАСКО
//          KASKOPREVDATA, KASKOPREVNOMER, KASKOPREVCOMPANY, KASKOPREVDATAEND
//  7,  ДАТА ПРИОБРЕТЕНИЯ АВТО ИЛИ ПОСТАНОВКА НА УЧЕТ В ОРГАНИЗАЦИЮ, ДОКУМЕНТ (ДОГОВОР КУПЛИ-ПРОДАЖИ), ДАТА ДОКУМЕНТА, ПРОБЕГ ПРИ ПРИОБРЕТЕНИИ
//          AUTOpokupkaDATA, AUTOdocument, AUTOdocumentDATA, AUTOzeroPROBEG
//  8.  ПТС, СТС, ВЛАДЕЛЕЦ (ФИО или наименование орг-ции), VIN, номер двигателя
//          AUTOPTS,AUTOSTS,AUTOVLADELEC,AUTOVIN, AUTOENGINE
//  9.  ПРИМЕЧАНИЕ (ДОП.ИНФОРМАЦИЯ)
//          PRIMECHANIE

//ВСЕ ДАТЫ В МИЛЛИСЕКУНДАХ



//endregion


public class AUTO {

    private String MARKA;
    private String GOSNOMER;
    private String COLOR;
    private String TEHOSMOTRNOMER;
    private String TEHOSMOTRDATA;
    private String TEHOSMOTRSLEDDATA;
    private String OSAGODATA;
    private String OSAGONOMER;
    private String OSAGOCOMPANY;
    private String OSAGODATAEND;
    private String OSAGOPREVDATA;
    private String OSAGOPREVNOMER;
    private String OSAGOPREVCOMPANY;
    private String OSAGOPREVDATAEND;
    private String KASKODATA;
    private String KASKONOMER;
    private String KASKOCOMPANY;
    private String KASKODATAEND;
    private String KASKOPREVDATA;
    private String KASKOPREVNOMER;
    private String KASKOPREVCOMPANY;
    private String KASKOPREVDATAEND;
    private String AUTOpokupkaDATA;
    private String AUTOdocument;
    private String AUTOdocumentDATA;
    private String AUTOzeroPROBEG;
    private String AUTOPTS;
    private String AUTOSTS;
    private String AUTOVLADELEC;
    private String AUTOVIN;
    private String AUTOENGINE;
    private String PRIMECHANIE;
    private String VODITEL_IDD;
    private String idnumber;

    public AUTO (String MARKA,
                 String GOSNOMER,
                 String COLOR,
                 String TEHOSMOTRNOMER,
                 String TEHOSMOTRDATA,
                 String TEHOSMOTRSLEDDATA,
                 String OSAGODATA,
                 String OSAGONOMER,
                 String OSAGOCOMPANY,
                 String OSAGODATAEND,
                 String OSAGOPREVDATA,
                 String OSAGOPREVNOMER,
                 String OSAGOPREVCOMPANY,
                 String OSAGOPREVDATAEND,
                 String KASKODATA,
                 String KASKONOMER,
                 String KASKOCOMPANY,
                 String KASKODATAEND,
                 String KASKOPREVDATA,
                 String KASKOPREVNOMER,
                 String KASKOPREVCOMPANY,
                 String KASKOPREVDATAEND,
                 String AUTOpokupkaDATA,
                 String AUTOdocument,
                 String AUTOdocumentDATA,
                 String AUTOzeroPROBEG,
                 String AUTOPTS,
                 String AUTOSTS,
                 String AUTOVLADELEC,
                 String AUTOVIN,
                 String AUTOENGINE,
                 String PRIMECHANIE,
                 String VODITEL_IDD,
                 String idnumber) {


                this.MARKA=MARKA;
                this.GOSNOMER=GOSNOMER;
                this.COLOR=COLOR;
                this.TEHOSMOTRNOMER=TEHOSMOTRNOMER;
                this.TEHOSMOTRDATA=TEHOSMOTRDATA;
                this.TEHOSMOTRSLEDDATA=TEHOSMOTRSLEDDATA;
                this.OSAGODATA=OSAGODATA;
                this.OSAGONOMER=OSAGONOMER;
                this.OSAGOCOMPANY=OSAGOCOMPANY;
                this.OSAGODATAEND=OSAGODATAEND;
                this.OSAGOPREVDATA=OSAGOPREVDATA;
                this.OSAGOPREVNOMER=OSAGOPREVNOMER;
                this.OSAGOPREVCOMPANY=OSAGOPREVCOMPANY;
                this.OSAGOPREVDATAEND=OSAGOPREVDATAEND;
                this.KASKODATA=KASKODATA;
                this.KASKONOMER=KASKONOMER;
                this.KASKOCOMPANY=KASKOCOMPANY;
                this.KASKODATAEND=KASKODATAEND;
                this.KASKOPREVDATA=KASKOPREVDATA;
                this.KASKOPREVNOMER=KASKOPREVNOMER;
                this.KASKOPREVCOMPANY=KASKOPREVCOMPANY;
                this.KASKOPREVDATAEND=KASKOPREVDATAEND;
                this.AUTOpokupkaDATA=AUTOpokupkaDATA;
                this.AUTOdocument=AUTOdocument;
                this.AUTOdocumentDATA=AUTOdocumentDATA;
                this.AUTOzeroPROBEG=AUTOzeroPROBEG;
                this.AUTOPTS=AUTOPTS;
                this.AUTOSTS=AUTOSTS;
                this.AUTOVLADELEC=AUTOVLADELEC;
                this.AUTOVIN=AUTOVIN;
                this.AUTOENGINE=AUTOENGINE;
                this.PRIMECHANIE=PRIMECHANIE;
                this.VODITEL_IDD=VODITEL_IDD;
                this.idnumber = idnumber;
    }

    public String getMARKA() {
        return MARKA;
    }

    public void setMARKA(String MARKA) {
        this.MARKA = MARKA;
    }


    public String getGOSNOMER() {
        return GOSNOMER;
    }

    public void setGOSNOMER(String GOSNOMER) {
        this.GOSNOMER = GOSNOMER;
    }


    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }


    public String getTEHOSMOTRNOMER() {
        return TEHOSMOTRNOMER;
    }

    public void setTEHOSMOTRNOMER(String TEHOSMOTRNOMER) {
        this.TEHOSMOTRNOMER = TEHOSMOTRNOMER;
    }


    public String getTEHOSMOTRDATA() {
        return TEHOSMOTRDATA;
    }

    public void setTEHOSMOTRDATA(String TEHOSMOTRDATA) {
        this.TEHOSMOTRDATA = TEHOSMOTRDATA;
    }


    public String getTEHOSMOTRSLEDDATA() {
        return TEHOSMOTRSLEDDATA;
    }

    public void setTEHOSMOTRSLEDDATA(String TEHOSMOTRSLEDDATA) {
        this.TEHOSMOTRSLEDDATA = TEHOSMOTRSLEDDATA;
    }


    public String getOSAGODATA() {
        return OSAGODATA;
    }

    public void setOSAGODATA(String OSAGODATA) {
        this.OSAGODATA = OSAGODATA;
    }


    public String getOSAGONOMER() {
        return OSAGONOMER;
    }

    public void setOSAGONOMER(String OSAGONOMER) {
        this.OSAGONOMER = OSAGONOMER;
    }


    public String getOSAGOCOMPANY() {
        return OSAGOCOMPANY;
    }

    public void setOSAGOCOMPANY(String OSAGOCOMPANY) {
        this.OSAGOCOMPANY = OSAGOCOMPANY;
    }


    public String getOSAGODATAEND() {
        return OSAGODATAEND;
    }

    public void setOSAGODATAEND(String OSAGODATAEND) {
        this.OSAGODATAEND = OSAGODATAEND;
    }


    public String getOSAGOPREVDATA() {
        return OSAGOPREVDATA;
    }

    public void setOSAGOPREVDATA(String OSAGOPREVDATA) {
        this.OSAGOPREVDATA = OSAGOPREVDATA;
    }


    public String getOSAGOPREVNOMER() {
        return OSAGOPREVNOMER;
    }

    public void setOSAGOPREVNOMER(String OSAGOPREVNOMER) {
        this.OSAGOPREVNOMER = OSAGOPREVNOMER;
    }


    public String getOSAGOPREVCOMPANY() {
        return OSAGOPREVCOMPANY;
    }

    public void setOSAGOPREVCOMPANY(String OSAGOPREVCOMPANY) {
        this.OSAGOPREVCOMPANY = OSAGOPREVCOMPANY;
    }


    public String getOSAGOPREVDATAEND() {
        return OSAGOPREVDATAEND;
    }

    public void setOSAGOPREVDATAEND(String OSAGOPREVDATAEND) {
        this.OSAGOPREVDATAEND = OSAGOPREVDATAEND;
    }


    public String getMKASKODATA() {
        return KASKODATA;
    }

    public void setKASKODATA(String KASKODATA) {
        this.KASKODATA = KASKODATA;
    }


    public String getKASKONOMER() {
        return KASKONOMER;
    }

    public void setKASKONOMER(String KASKONOMER) {
        this.KASKONOMER = KASKONOMER;
    }


    public String getKASKOCOMPANY() {
        return KASKOCOMPANY;
    }

    public void setKASKOCOMPANY(String KASKOCOMPANY) {
        this.KASKOCOMPANY = KASKOCOMPANY;
    }


    public String getKASKODATAEND() {
        return KASKODATAEND;
    }

    public void setKASKODATAEND(String KASKODATAEND) {
        this.KASKODATAEND = KASKODATAEND;
    }


    public String getKASKOPREVDATA() {
        return KASKOPREVDATA;
    }

    public void setKASKOPREVDATA(String KASKOPREVDATA) {
        this.KASKOPREVDATA = KASKOPREVDATA;
    }


    public String getKASKOPREVNOMER() {
        return KASKOPREVNOMER;
    }

    public void setKASKOPREVNOMER(String KASKOPREVNOMER) {
        this.KASKOPREVNOMER = KASKOPREVNOMER;
    }


    public String getKASKOPREVCOMPANY() {
        return KASKOPREVCOMPANY;
    }

    public void setKASKOPREVCOMPANY(String KASKOPREVCOMPANY) {
        this.KASKOPREVCOMPANY = KASKOPREVCOMPANY;
    }


    public String getKASKOPREVDATAEND() {
        return KASKOPREVDATAEND;
    }

    public void setKASKOPREVDATAEND(String KASKOPREVDATAEND) {
        this.KASKOPREVDATAEND = KASKOPREVDATAEND;
    }


    public String getAUTOpokupkaDATA() {
        return AUTOpokupkaDATA;
    }

    public void setAUTOpokupkaDATA(String AUTOpokupkaDATA) {
        this.AUTOpokupkaDATA = AUTOpokupkaDATA;
    }


    public String getAUTOdocument() {
        return AUTOdocument;
    }

    public void setAUTOdocument(String AUTOdocument) {
        this.AUTOdocument = AUTOdocument;
    }


    public String getAUTOdocumentDATA() {
        return AUTOdocumentDATA;
    }

    public void setAUTOdocumentDATA(String AUTOdocumentDATA) {
        this.AUTOdocumentDATA = AUTOdocumentDATA;
    }


    public String getMAUTOzeroPROBEG() {
        return AUTOzeroPROBEG;
    }

    public void AUTOzeroPROBEG(String AUTOzeroPROBEG) {
        this.AUTOzeroPROBEG = AUTOzeroPROBEG;
    }


    public String getAUTOPTS() {
        return AUTOPTS;
    }

    public void setAUTOPTS(String AUTOPTS) {
        this.AUTOPTS = AUTOPTS;
    }


    public String getAUTOSTS() {
        return AUTOSTS;
    }

    public void setAUTOSTS(String AUTOSTS) {
        this.AUTOSTS = AUTOSTS;
    }


    public String getAUTOVLADELEC() {
        return AUTOVLADELEC;
    }

    public void setAUTOVLADELEC(String AUTOVLADELEC) {
        this.AUTOVLADELEC = AUTOVLADELEC;
    }


    public String getAUTOVIN() {
        return AUTOVIN;
    }

    public void setAUTOVIN(String AUTOVIN) {
        this.AUTOVIN = AUTOVIN;
    }


    public String getAUTOENGINE() {
        return AUTOENGINE;
    }

    public void setAUTOENGINE(String AUTOENGINE) {
        this.AUTOENGINE = AUTOENGINE;
    }


    public String getPRIMECHANIE() {
        return PRIMECHANIE;
    }

    public void setPRIMECHANIE(String PRIMECHANIE) {
        this.PRIMECHANIE = PRIMECHANIE;
    }


    public String getVODITEL_IDD() {
        return VODITEL_IDD;
    }

    public void setVODITEL_IDD(String VODITEL_IDD) {
        this.VODITEL_IDD = VODITEL_IDD;
    }


    public String getidnumber() {
        return idnumber;
    }

}
