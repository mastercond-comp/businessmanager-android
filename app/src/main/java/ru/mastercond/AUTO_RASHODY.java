package ru.mastercond;

//region УЧЕТ РАСХОДОВ НА АВТОМОБИЛИ АВТОПАРКА ОРГАНИЗАЦИИ / ИП

//  1.  ТИП РАСХОДОВ:
//
// - топливо; ("Топливо")
// - расходные материалы (масла; фильтра, колодки, масла, прокладки и т.д.); ("Расходка")
// - запасные части; ("Запчасть")
// - работы и услуги - диагностика и ремонт; ("УслугиРемонт")
// - работы и услуги - регулярные (шиномонтаж, автомойка и т.д.); ("РегулярныеРасходы")
// - платная парковка ("Парковка")
// - штрафы; ("Штраф")
// - страховка (КАСКО, ОСАГО) ("Страховка")
// - транспортный налог и прочие сборы; ("Налог")
//          autoTYPE

//  2.  Кол-во, цена за ед, общая стоимость
//          autoKOLVO, autoCENAED, autoSTOIMOST
//  3.  Название расходов в базе (наименование)
//          autoNAIMENOVANIE
//  4.  Номер чека (УИН/УИП)
//          autoNOMERCHEKA
//  5.  Номер заказ-наряда (для ремонта в автосервисе)
//          autoNOMERZAKAZNARJADA
//  6.  Дата
//          autoDATA_D, autoDATA_M, autoDATA_Y,
//  7.  Дата окончания гарантии (для ремонта или запасных частей)
//          autoSROKKONCAGARANTII_D, autoSROKKONCAGARANTII_M, autoSROKKONCAGARANTII_Y,
//  8.  Пробег, при котором проводится заправка или ремонтируется авто, в км
//          autoPROBEG
//  9.  Водитель
//          IDV
//  10. Примерная дата окончания ресурса замененной детали или пробег, при котором следующая замена
//          autoENDRESURSDATA_D, autoENDRESURSDATA_M, autoENDRESURSDATA_Y, autoENDRESURSPROBEG
//  11. IDD автомобиля
//          IDD

// ВСЕ ДАТЫ INTEGER - DAY, MONTH, YEAR (три столбца в БД для удобства и скорости выборки по датам)
// ПРОБЕГ INTEGER (для удобстав выборки по БД)

//endregion


public class AUTO_RASHODY {

    private String autoTYPE;
    private String autoEDIZM;
    private Float autoKOLVO;
    private Float autoCENAED;
    private Float autoSTOIMOST;
    private String autoNAIMENOVANIE;
    private String autoNOMERCHEKA;
    private String autoNOMERZAKAZNARJADA;
    private Integer autoDATA_D;
    private Integer autoDATA_M;
    private Integer autoDATA_Y;
    private Integer autoSROKKONCAGARANTII_D;
    private Integer autoSROKKONCAGARANTII_M;
    private Integer autoSROKKONCAGARANTII_Y;
    private Integer autoPROBEG;
    private String IDV;
    private Integer autoENDRESURSDATA_D;
    private Integer autoENDRESURSDATA_M;
    private Integer autoENDRESURSDATA_Y;
    private Integer autoENDRESURSPROBEG;
    private String IDD;
    private String idnumber;

    public AUTO_RASHODY(String autoTYPE, String autoEDIZM, Float autoKOLVO, Float autoCENAED, Float autoSTOIMOST, String autoNAIMENOVANIE, String autoNOMERCHEKA, String autoNOMERZAKAZNARJADA, Integer autoDATA_D, Integer autoDATA_M, Integer autoDATA_Y, Integer autoSROKKONCAGARANTII_D, Integer autoSROKKONCAGARANTII_M, Integer autoSROKKONCAGARANTII_Y, Integer autoPROBEG, String IDV, Integer autoENDRESURSDATA_D, Integer autoENDRESURSDATA_M, Integer autoENDRESURSDATA_Y, Integer autoENDRESURSPROBEG, String IDD, String idnumber) {

        this.autoTYPE=autoTYPE;
        this.autoEDIZM=autoEDIZM;
        this.autoKOLVO=autoKOLVO;
        this.autoCENAED=autoCENAED;
        this.autoSTOIMOST=autoSTOIMOST;
        this.autoNAIMENOVANIE=autoNAIMENOVANIE;
        this.autoNOMERCHEKA=autoNOMERCHEKA;
        this.autoNOMERZAKAZNARJADA=autoNOMERZAKAZNARJADA;
        this.autoDATA_D=autoDATA_D;
        this.autoDATA_M=autoDATA_M;
        this.autoDATA_Y=autoDATA_Y;
        this.autoSROKKONCAGARANTII_D=autoSROKKONCAGARANTII_D;
        this.autoSROKKONCAGARANTII_M=autoSROKKONCAGARANTII_D;
        this.autoSROKKONCAGARANTII_Y=autoSROKKONCAGARANTII_D;
        this.autoPROBEG=autoPROBEG;
        this.IDV=IDV;
        this.autoENDRESURSDATA_D=autoENDRESURSDATA_D;
        this.autoENDRESURSDATA_M=autoENDRESURSDATA_M;
        this.autoENDRESURSDATA_Y=autoENDRESURSDATA_Y;
        this.autoENDRESURSPROBEG=autoENDRESURSPROBEG;
        this.IDD=IDD;
        this.idnumber = idnumber;
    }

    public String getautoTYPE() {
        return autoTYPE;
    }

    public void setautoTYPE(String autoTYPE) {
        this.autoTYPE = autoTYPE;
    }

    public String getautoEDIZM() {
        return autoEDIZM;
    }

    public void setautoEDIZM(String autoEDIZM) {
        this.autoEDIZM = autoEDIZM;
    }

    public Float getautoKOLVO() {
        return autoKOLVO;
    }

    public void setautoKOLVO(Float autoKOLVO) {
        this.autoKOLVO = autoKOLVO;
    }

    public Float getautoCENAED() {
        return autoCENAED;
    }

    public void setautoCENAED(Float autoCENAED) {
        this.autoCENAED = autoCENAED;
    }

    public Float getautoSTOIMOST() {
        return autoSTOIMOST;
    }

    public void setautoSTOIMOST(Float autoSTOIMOST) {
        this.autoSTOIMOST = autoSTOIMOST;
    }

    public String getautoNAIMENOVANIE() {
        return autoNAIMENOVANIE;
    }

    public void setautoNAIMENOVANIE(String autoNAIMENOVANIE) {
        this.autoNAIMENOVANIE = autoNAIMENOVANIE;
    }


    public String getautoNOMERCHEKA() {
        return autoNOMERCHEKA;
    }

    public void setautoNOMERCHEKA(String autoNOMERCHEKA) {
        this.autoNOMERCHEKA = autoNOMERCHEKA;
    }


    public String getautoNOMERZAKAZNARJADA() {
        return autoNOMERZAKAZNARJADA;
    }

    public void setautoNOMERZAKAZNARJADA(String autoNOMERZAKAZNARJADA) {
        this.autoNOMERZAKAZNARJADA = autoNOMERZAKAZNARJADA;
    }


    public Integer getautoDATA_D() {
        return autoDATA_D;
    }
    public Integer getautoDATA_M() {
        return autoDATA_M;
    }
    public Integer getautoDATA_Y() {
        return autoDATA_Y;
    }

    public void setautoDATA_D(Integer autoDATA_D) {
        this.autoDATA_D = autoDATA_D;
    }
    public void setautoDATA_M(Integer autoDATA_M) {
        this.autoDATA_M = autoDATA_M;
    }
    public void setautoDATA_Y(Integer autoDATA_Y) {
        this.autoDATA_Y = autoDATA_Y;
    }

    public Integer getautoSROKKONCAGARANTII_D() {
        return autoSROKKONCAGARANTII_D;
    }
    public Integer getautoSROKKONCAGARANTII_M() {
        return autoSROKKONCAGARANTII_M;
    }
    public Integer getautoSROKKONCAGARANTII_Y() {
        return autoSROKKONCAGARANTII_Y;
    }


    public void setautoSROKKONCAGARANTII_D(Integer autoSROKKONCAGARANTII_D) {
        this.autoSROKKONCAGARANTII_D = autoSROKKONCAGARANTII_D;
    }

    public void setautoSROKKONCAGARANTII_M(Integer autoSROKKONCAGARANTII_M) {
        this.autoSROKKONCAGARANTII_M = autoSROKKONCAGARANTII_M;
    }

    public void setautoSROKKONCAGARANTII_Y(Integer autoSROKKONCAGARANTII_Y) {
        this.autoSROKKONCAGARANTII_Y = autoSROKKONCAGARANTII_Y;
    }


    public Integer getautoPROBEG() {
        return autoPROBEG;
    }

    public void setautoPROBEG(Integer autoPROBEG) {
        this.autoPROBEG = autoPROBEG;
    }


    public String getIDV() {
        return IDV;
    }

    public void setIDV(String IDV) {
        this.IDV = IDV;
    }


    public Integer getautoENDRESURSDATA_D() {
        return autoENDRESURSDATA_D;
    }
    public Integer getautoENDRESURSDATA_M() {
        return autoENDRESURSDATA_M;
    }
    public Integer getautoENDRESURSDATA_Y() {
        return autoENDRESURSDATA_Y;
    }


    public void setautoENDRESURSDATA_D (Integer autoENDRESURSDATA_D) {
        this.autoENDRESURSDATA_D = autoENDRESURSDATA_D;
    }

    public void setautoENDRESURSDATA_M (Integer autoENDRESURSDATA_M) {
        this.autoENDRESURSDATA_M = autoENDRESURSDATA_M;
    }

    public void setautoENDRESURSDATA_Y (Integer autoENDRESURSDATA_Y) {
        this.autoENDRESURSDATA_Y = autoENDRESURSDATA_Y;
    }


    public Integer getautoENDRESURSPROBEG() {
        return autoENDRESURSPROBEG;
    }

    public void setautoENDRESURSPROBEG(Integer autoENDRESURSPROBEG) {
        this.autoENDRESURSPROBEG = autoENDRESURSPROBEG;
    }


    public String getIDD() {
        return IDD;
    }

    public void setIDD(String IDD) {
        this.IDD = IDD;
    }


    public String getidnumber() {
        return idnumber;
    }

}
