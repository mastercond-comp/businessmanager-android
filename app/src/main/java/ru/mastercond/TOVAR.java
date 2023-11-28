package ru.mastercond;

//region ТОВАР

//ТОВАРЫ

// 1. НАЗВАНИЕ ТОВАРА, МОДЕЛЬ, КРАТКОЕ ОПИСАНИЕ, ЦЕНА, ВАЛЮТА, ПРИНАДЛЕЖНОСТЬ К САЙТУ (IDD)
//      Краткое описание товара: (например, Для помещений площадью до 20 кв.м. )
//      ID, IDD, NAME, MODEL, OPISANIE, PRICE, VALUTA,
// 2. НАВИГАЦИЯ ПО САЙТУ:
//      Ссылка на файл со своими CSS-стилями:(например, /css/my.css) - MYCSS
//      Уровень 1 (после Главной):(например, Каталог теплых полов) - NAV1
//      Ссылка на уровень 1 (после Главной): (например, catalog.php) - LINKNAV1
//      Уровень 2 (после Главной):(например, Каталог теплых полов) - NAV2
//      Ссылка на уровень 2 (после Главной): (например, catalog.php) - LINKNAV2
//      Уровень 3 (после Главной):(например, Каталог теплых полов) - NAV3
//      Ссылка на уровень 3 (после Главной): (например, catalog.php) - LINKNAV3
//      Заголовок страницы в браузере: (например, Монтаж теплых полов в компании TheBestCompany) - ZAGOLOVOK
//      Ключевые слова страницы для поисковиков (meta name=&quot;keywords&quot;): (например, теплые полы, терморегулятор [указываются через запятую]) - KEYWORDS
//      Описание страницы для поисковиков (meta name=&quot;description&quot;): (например, Каталог лучших теплых полов от компании TheBestCompany) - METADESCRIPTION
//      Основной шрифт страницы: (например, Tahoma) - FONT
//      Дополнительный шрифт текста на странице (модель и цена товара): (например, Tahoma) - DOPFONT
//      Цвет текста заголовка страницы: (например, green, или цветовой код) - ZAGOLOVOKTEXTCOLOR
//      Цвет текста категорий товаров: (например, green, или цветовой код) - KATEGORIITEXTCOLOR
//      Цвет цены товара: (например, green, или цветовой код) - PRICETEXTCOLOR
//      Цвет фона страницы: (например, white, или цветовой код) - BACKGROUNDCOLOR
//      Цвет текста навигационных ссылок: (например, green, или цветовой код) - NAVTEXTCOLOR
//      ССЫЛКА НА ЭТУ СТРАНИЦУ ТОВАРА: (например, /tovar1.php) - LINK
//      ОТОБРАЖАТЬ НА САЙТЕ ИЛИ НЕТ - VISIBILITY
//      ЗАКУПОЧНАЯ ЦЕНА - ZAKUPCENA (валюта закупки и продажи совпадают), продажа конвертируется в рубли по курсу ЦБ
//
//  3. ХАРАКТЕРИСТИКИ ТОВАРА
//      НАЗВАНИЕ ХАРАКТЕРИСТИКИ 1, ЗНАЧЕНИЕ ХАРАКТЕРИСТИКИ 1 - 20 характеристик
//      CHNAME1, CHZNACHENIE1 - 20шт
//  4. ИЗОБРАЖЕНИЯ ТОВАРА (до 10), ВИДЕО ПО ТОВАРУ (ДО 3)
//      IMGTOVARA1...10, VIDEOTOVARA 1..3
//      Изображение №1 будет основным для разделов &quot;Перечень товаров&quot;, &quot;Товары на главной&quot;"
//      Формат изображений товаров (ширина : высота):  4:3, размер произвольный.
//  5. ТОВАР-СКЛАД
//      Текущий остаток (без учета резерва) - OSTATOK
//      Складское место - SKLADMESTO
//      Шаблон (является ли шаблоном) - SHABLON


// ID, IDD, NAME, MODEL, OPISANIE, PRICE, VALUTA, MYCSS, NAV1, LINKNAV1, NAV2, LINKNAV2, NAV3, LINKNAV3, ZAGOLOVOK, KEYWORDS, METADESCRIPTION, FONT,DOPFONT, ZAGOLOVOKTEXTCOLOR, KATEGORIITEXTCOLOR, PRICETEXTCOLOR, BACKGROUNDCOLOR, NAVTEXTCOLOR, LINK,
// CHNAME1, CHZNACHENIE1, CHNAME2, CHZNACHENIE2, CHNAME3, CHZNACHENIE3, CHNAME4, CHZNACHENIE4, CHNAME5, CHZNACHENIE5, CHNAME6, CHZNACHENIE6,CHNAME7, CHZNACHENIE7, CHNAME8, CHZNACHENIE8, CHNAME9, CHZNACHENIE9, CHNAME10, CHZNACHENIE10, CHNAME11, CHZNACHENIE11, CHNAME12, CHZNACHENIE12, CHNAME13, CHZNACHENIE13, CHNAME14, CHZNACHENIE14, CHNAME15, CHZNACHENIE15, CHNAME16, CHZNACHENIE16, CHNAME17, CHZNACHENIE17, CHNAME18, CHZNACHENIE18, CHNAME19, CHZNACHENIE19, CHNAME20, CHZNACHENIE20,
// IMGTOVARA1, IMGTOVARA2, IMGTOVARA3, IMGTOVARA4, IMGTOVARA5, IMGTOVARA6, IMGTOVARA7, IMGTOVARA8, IMGTOVARA9, IMGTOVARA10, VIDEOTOVARA1, VIDEOTOVARA2, VIDEOTOVARA3,
// OSTATOK, SKLADMESTO, SHABLON, VISIBILITY, ZAKUPCENA



//endregion


public class TOVAR {


    private String IDD;
    private String NAME;
    private String MODEL;
    private String OPISANIE;
    private String PRICE;
    private String VALUTA;
    private String MYCSS;
    private String NAV1;
    private String LINKNAV1;
    private String NAV2;
    private String LINKNAV2;
    private String NAV3;
    private String LINKNAV3;
    private String ZAGOLOVOK;
    private String KEYWORDS;
    private String METADESCRIPTION;
    private String FONT;
    private String DOPFONT;
    private String ZAGOLOVOKTEXTCOLOR;
    private String KATEGORIITEXTCOLOR;
    private String PRICETEXTCOLOR;
    private String BACKGROUNDCOLOR;
    private String NAVTEXTCOLOR;
    private String LINK;
    private String CHNAME1;
    private String CHZNACHENIE1;
    private String CHNAME2;
    private String CHZNACHENIE2;
    private String CHNAME3;
    private String CHZNACHENIE3;
    private String CHNAME4;
    private String CHZNACHENIE4;
    private String CHNAME5;
    private String CHZNACHENIE5;
    private String CHNAME6;
    private String CHZNACHENIE6;
    private String CHNAME7;
    private String CHZNACHENIE7;
    private String CHNAME8;
    private String CHZNACHENIE8;
    private String CHNAME9;
    private String CHZNACHENIE9;
    private String CHNAME10;
    private String CHZNACHENIE10;
    private String CHNAME11;
    private String CHZNACHENIE11;
    private String CHNAME12;
    private String CHZNACHENIE12;
    private String CHNAME13;
    private String CHZNACHENIE13;
    private String CHNAME14;
    private String CHZNACHENIE14;
    private String CHNAME15;
    private String CHZNACHENIE15;
    private String CHNAME16;
    private String CHZNACHENIE16;
    private String CHNAME17;
    private String CHZNACHENIE17;
    private String CHNAME18;
    private String CHZNACHENIE18;
    private String CHNAME19;
    private String CHZNACHENIE19;
    private String CHNAME20;
    private String CHZNACHENIE20;
    private String IMGTOVARA1;
    private String IMGTOVARA2;
    private String IMGTOVARA3;
    private String IMGTOVARA4;
    private String IMGTOVARA5;
    private String IMGTOVARA6;
    private String IMGTOVARA7;
    private String IMGTOVARA8;
    private String IMGTOVARA9;
    private String IMGTOVARA10;
    private String VIDEOTOVARA1;
    private String VIDEOTOVARA2;
    private String VIDEOTOVARA3;
    private String OSTATOK;
    private String SKLADMESTO;
    private String SHABLON;
    private String VISIBILITY;
    private String ZAKUPCENA;
    private String idnumber;

    public TOVAR(String IDD,
                 String NAME,
                 String MODEL,
                 String OPISANIE,
                 String PRICE,
                 String VALUTA,
                 String MYCSS,
                 String NAV1,
                 String LINKNAV1,
                 String NAV2,
                 String LINKNAV2,
                 String NAV3,
                 String LINKNAV3,
                 String ZAGOLOVOK,
                 String KEYWORDS,
                 String METADESCRIPTION,
                 String FONT,
                 String DOPFONT,
                 String ZAGOLOVOKTEXTCOLOR,
                 String KATEGORIITEXTCOLOR,
                 String PRICETEXTCOLOR,
                 String BACKGROUNDCOLOR,
                 String NAVTEXTCOLOR,
                 String LINK,
                 String CHNAME1,
                 String CHZNACHENIE1,
                 String CHNAME2,
                 String CHZNACHENIE2,
                 String CHNAME3,
                 String CHZNACHENIE3,
                 String CHNAME4,
                 String CHZNACHENIE4,
                 String CHNAME5,
                 String CHZNACHENIE5,
                 String CHNAME6,
                 String CHZNACHENIE6,
                 String CHNAME7,
                 String CHZNACHENIE7,
                 String CHNAME8,
                 String CHZNACHENIE8,
                 String CHNAME9,
                 String CHZNACHENIE9,
                 String CHNAME10,
                 String CHZNACHENIE10,
                 String CHNAME11,
                 String CHZNACHENIE11,
                 String CHNAME12,
                 String CHZNACHENIE12,
                 String CHNAME13,
                 String CHZNACHENIE13,
                 String CHNAME14,
                 String CHZNACHENIE14,
                 String CHNAME15,
                 String CHZNACHENIE15,
                 String CHNAME16,
                 String CHZNACHENIE16,
                 String CHNAME17,
                 String CHZNACHENIE17,
                 String CHNAME18,
                 String CHZNACHENIE18,
                 String CHNAME19,
                 String CHZNACHENIE19,
                 String CHNAME20,
                 String CHZNACHENIE20,
                 String IMGTOVARA1,
                 String IMGTOVARA2,
                 String IMGTOVARA3,
                 String IMGTOVARA4,
                 String IMGTOVARA5,
                 String IMGTOVARA6,
                 String IMGTOVARA7,
                 String IMGTOVARA8,
                 String IMGTOVARA9,
                 String IMGTOVARA10,
                 String VIDEOTOVARA1,
                 String VIDEOTOVARA2,
                 String VIDEOTOVARA3,
                 String OSTATOK,
                 String SKLADMESTO,
                 String SHABLON,
                 String VISIBILITY,
                 String ZAKUPCENA,
                 String idnumber) {


        this.IDD=IDD;
        this.NAME=NAME;
        this.MODEL=MODEL;
        this.OPISANIE=OPISANIE;
        this.PRICE=PRICE;
        this.VALUTA=VALUTA;
        this.MYCSS=MYCSS;
        this.NAV1=NAV1;
        this.LINKNAV1=LINKNAV1;
        this.NAV2=NAV2;
        this.LINKNAV2=LINKNAV2;
        this.NAV3=NAV3;
        this.LINKNAV3=LINKNAV3;
        this.ZAGOLOVOK=ZAGOLOVOK;
        this.KEYWORDS=KEYWORDS;
        this.METADESCRIPTION=METADESCRIPTION;
        this.FONT=FONT;
        this.DOPFONT=DOPFONT;
        this.ZAGOLOVOKTEXTCOLOR=ZAGOLOVOKTEXTCOLOR;
        this.KATEGORIITEXTCOLOR=KATEGORIITEXTCOLOR;
        this.PRICETEXTCOLOR=PRICETEXTCOLOR;
        this.BACKGROUNDCOLOR=BACKGROUNDCOLOR;
        this.NAVTEXTCOLOR=NAVTEXTCOLOR;
        this.LINK=LINK;
        this.CHNAME1=CHNAME1;
        this.CHZNACHENIE1=CHZNACHENIE1;
        this.CHNAME2=CHNAME2;
        this.CHZNACHENIE2=CHZNACHENIE2;
        this.CHNAME3=CHNAME3;
        this.CHZNACHENIE3=CHZNACHENIE3;
        this.CHNAME4=CHNAME4;
        this.CHZNACHENIE4=CHZNACHENIE4;
        this.CHNAME5=CHNAME5;
        this.CHZNACHENIE5=CHZNACHENIE5;
        this.CHNAME6=CHNAME6;
        this.CHZNACHENIE6=CHZNACHENIE6;
        this.CHNAME7=CHNAME7;
        this.CHZNACHENIE7=CHZNACHENIE7;
        this.CHNAME8=CHNAME8;
        this.CHZNACHENIE8=CHZNACHENIE8;
        this.CHNAME9=CHNAME9;
        this.CHZNACHENIE9=CHZNACHENIE9;
        this.CHNAME10=CHNAME10;
        this.CHZNACHENIE10=CHZNACHENIE10;
        this.CHNAME11=CHNAME11;
        this.CHZNACHENIE11=CHZNACHENIE11;
        this.CHNAME12=CHNAME12;
        this.CHZNACHENIE12=CHZNACHENIE12;
        this.CHNAME13=CHNAME13;
        this.CHZNACHENIE13=CHZNACHENIE13;
        this.CHNAME14=CHNAME14;
        this.CHZNACHENIE14=CHZNACHENIE14;
        this.CHNAME15=CHNAME15;
        this.CHZNACHENIE15=CHZNACHENIE15;
        this.CHNAME16=CHNAME16;
        this.CHZNACHENIE16=CHZNACHENIE16;
        this.CHNAME17=CHNAME17;
        this.CHZNACHENIE17=CHZNACHENIE17;
        this.CHNAME18=CHNAME18;
        this.CHZNACHENIE18=CHZNACHENIE18;
        this.CHNAME19=CHNAME19;
        this.CHZNACHENIE19=CHZNACHENIE19;
        this.CHNAME20=CHNAME20;
        this.CHZNACHENIE20=CHZNACHENIE20;
        this.IMGTOVARA1=IMGTOVARA1;
        this.IMGTOVARA2=IMGTOVARA2;
        this.IMGTOVARA3=IMGTOVARA3;
        this.IMGTOVARA4=IMGTOVARA4;
        this.IMGTOVARA5=IMGTOVARA5;
        this.IMGTOVARA6=IMGTOVARA6;
        this.IMGTOVARA7=IMGTOVARA7;
        this.IMGTOVARA8=IMGTOVARA8;
        this.IMGTOVARA9=IMGTOVARA9;
        this.IMGTOVARA10=IMGTOVARA10;
        this.VIDEOTOVARA1=VIDEOTOVARA1;
        this.VIDEOTOVARA2=VIDEOTOVARA2;
        this.VIDEOTOVARA3=VIDEOTOVARA3;
        this.OSTATOK=OSTATOK;
        this.SHABLON=SHABLON;
        this.SKLADMESTO=SKLADMESTO;
        this.VISIBILITY=VISIBILITY;
        this.ZAKUPCENA=ZAKUPCENA;
        this.idnumber=idnumber;
    }

    public String getIDD() {
        return IDD;
    }

    public void setIDD (String IDD) {
        this.IDD=IDD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME (String NAME) {
        this.NAME=NAME;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL (String MODEL) {
        this.MODEL=MODEL;
    }

    public String getOPISANIE() {
        return OPISANIE;
    }

    public void setOPISANIE (String OPISANIE) {
        this.OPISANIE=OPISANIE;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE (String PRICE) {
        this.PRICE=PRICE;
    }

    public String getVALUTA() {
        return VALUTA;
    }

    public void setVALUTA (String VALUTA) {
        this.VALUTA=VALUTA;
    }

    public String getMYCSS() {
        return MYCSS;
    }

    public void setMYCSS (String MYCSS) {
        this.MYCSS=MYCSS;
    }

    public String getNAV1() {
        return NAV1;
    }

    public void setNAV1 (String NAV1) {
        this.NAV1=NAV1;
    }

    public String getLINKNAV1() {
        return LINKNAV1;
    }

    public void setLINKNAV1 (String LINKNAV1) {
        this.LINKNAV1=LINKNAV1;
    }

    public String getNAV2() {
        return NAV2;
    }

    public void setNAV2 (String NAV2) {
        this.NAV2=NAV2;
    }

    public String getLINKNAV2() {
        return LINKNAV2;
    }

    public void setLINKNAV2 (String LINKNAV2) {
        this.LINKNAV2=LINKNAV2;
    }

    public String getNAV3() {
        return NAV3;
    }

    public void setNAV3 (String NAV3) {
        this.NAV3=NAV3;
    }

    public String getLINKNAV3() {
        return LINKNAV3;
    }

    public void setLINKNAV3 (String LINKNAV3) {
        this.LINKNAV3=LINKNAV3;
    }

    public String getZAGOLOVOK() {
        return ZAGOLOVOK;
    }

    public void setZAGOLOVOK (String ZAGOLOVOK) {
        this.ZAGOLOVOK=ZAGOLOVOK;
    }

    public String getKEYWORDS() {
        return KEYWORDS;
    }

    public void setKEYWORDS (String KEYWORDS) {
        this.KEYWORDS=KEYWORDS;
    }

    public String getMETADESCRIPTION() {
        return METADESCRIPTION;
    }

    public void setMETADESCRIPTION (String METADESCRIPTION) {
        this.METADESCRIPTION=METADESCRIPTION;
    }

    public String getFONT() {
        return FONT;
    }

    public void setFONT (String FONT) {
        this.FONT=FONT;
    }

    public String getDOPFONT() {
        return DOPFONT;
    }

    public void setDOPFONT (String DOPFONT) {
        this.DOPFONT=DOPFONT;
    }

    public String getZAGOLOVOKTEXTCOLOR() {
        return ZAGOLOVOKTEXTCOLOR;
    }

    public void setZAGOLOVOKTEXTCOLOR (String ZAGOLOVOKTEXTCOLOR) {
        this.ZAGOLOVOKTEXTCOLOR=ZAGOLOVOKTEXTCOLOR;
    }

    public String getKATEGORIITEXTCOLOR() {
        return KATEGORIITEXTCOLOR;
    }

    public void setKATEGORIITEXTCOLOR (String KATEGORIITEXTCOLOR) {
        this.KATEGORIITEXTCOLOR=KATEGORIITEXTCOLOR;
    }

    public String getPRICETEXTCOLOR() {
        return PRICETEXTCOLOR;
    }

    public void setPRICETEXTCOLOR (String PRICETEXTCOLOR) {
        this.PRICETEXTCOLOR=PRICETEXTCOLOR;
    }

    public String getBACKGROUNDCOLOR() {
        return BACKGROUNDCOLOR;
    }

    public void setBACKGROUNDCOLOR (String BACKGROUNDCOLOR) {
        this.BACKGROUNDCOLOR=BACKGROUNDCOLOR;
    }

    public String getNAVTEXTCOLOR() {
        return NAVTEXTCOLOR;
    }

    public void setNAVTEXTCOLOR (String NAVTEXTCOLOR) {
        this.NAVTEXTCOLOR=NAVTEXTCOLOR;
    }

    public String getLINK() {
        return LINK;
    }

    public void setLINK (String LINK) {
        this.LINK=LINK;
    }

    public String getCHNAME1() {
        return CHNAME1;
    }

    public void setCHNAME1 (String CHNAME1) {
        this.CHNAME1=CHNAME1;
    }

    public String getCHZNACHENIE1() {
        return CHZNACHENIE1;
    }

    public void setCHZNACHENIE1 (String CHZNACHENIE1) {
        this.CHZNACHENIE1=CHZNACHENIE1;
    }

    public String getCHNAME2() {
        return CHNAME2;
    }

    public void setCHNAME2 (String CHNAME2) {
        this.CHNAME2=CHNAME2;
    }

    public String getCHZNACHENIE2() {
        return CHZNACHENIE2;
    }

    public void setCHZNACHENIE2 (String CHZNACHENIE2) {
        this.CHZNACHENIE2=CHZNACHENIE2;
    }

    public String getCHNAME3() {
        return CHNAME3;
    }

    public void setCHNAME3 (String CHNAME3) {
        this.CHNAME3=CHNAME3;
    }

    public String getCHZNACHENIE3() {
        return CHZNACHENIE3;
    }

    public void setCHZNACHENIE3 (String CHZNACHENIE3) {
        this.CHZNACHENIE3=CHZNACHENIE3;
    }

    public String getCHNAME4() {
        return CHNAME4;
    }

    public void setCHNAME4 (String CHNAME4) {
        this.CHNAME4=CHNAME4;
    }

    public String getCHZNACHENIE4() {
        return CHZNACHENIE4;
    }

    public void setCHZNACHENIE4 (String CHZNACHENIE4) {
        this.CHZNACHENIE4=CHZNACHENIE4;
    }

    public String getCHNAME5() {
        return CHNAME5;
    }

    public void setCHNAME5 (String CHNAME5) {
        this.CHNAME5=CHNAME5;
    }

    public String getCHZNACHENIE5() {
        return CHZNACHENIE5;
    }

    public void setCHZNACHENIE5 (String CHZNACHENIE5) {
        this.CHZNACHENIE5=CHZNACHENIE5;
    }

    public String getCHNAME6() {
        return CHNAME6;
    }

    public void setCHNAME6 (String CHNAME6) {
        this.CHNAME6=CHNAME6;
    }

    public String getCHZNACHENIE6() {
        return CHZNACHENIE6;
    }

    public void setCHZNACHENIE6 (String CHZNACHENIE6) {
        this.CHZNACHENIE6=CHZNACHENIE6;
    }

    public String getCHNAME7() {
        return CHNAME7;
    }

    public void setCHNAME7 (String CHNAME7) {
        this.CHNAME7=CHNAME7;
    }

    public String getCHZNACHENIE7() {
        return CHZNACHENIE7;
    }

    public void setCHZNACHENIE7 (String CHZNACHENIE7) {
        this.CHZNACHENIE7=CHZNACHENIE7;
    }

    public String getCHNAME8() {
        return CHNAME8;
    }

    public void setCHNAME8 (String CHNAME8) {
        this.CHNAME8=CHNAME8;
    }

    public String getCHZNACHENIE8() {
        return CHZNACHENIE8;
    }

    public void setCHZNACHENIE8 (String CHZNACHENIE8) {
        this.CHZNACHENIE8=CHZNACHENIE8;
    }

    public String getCHNAME9() {
        return CHNAME9;
    }

    public void setCHNAME9 (String CHNAME9) {
        this.CHNAME9=CHNAME9;
    }

    public String getCHZNACHENIE9() {
        return CHZNACHENIE9;
    }

    public void setCHZNACHENIE9 (String CHZNACHENIE9) {
        this.CHZNACHENIE9=CHZNACHENIE9;
    }

    public String getCHNAME10() {
        return CHNAME10;
    }

    public void setCHNAME10 (String CHNAME10) {
        this.CHNAME10=CHNAME10;
    }

    public String getCHZNACHENIE10() {
        return CHZNACHENIE10;
    }

    public void setCHZNACHENIE10 (String CHZNACHENIE10) {
        this.CHZNACHENIE10=CHZNACHENIE10;
    }

    public String getCHNAME11() {
        return CHNAME11;
    }

    public void setCHNAME11 (String CHNAME11) {
        this.CHNAME11=CHNAME11;
    }

    public String getCHZNACHENIE11() {
        return CHZNACHENIE11;
    }

    public void setCHZNACHENIE11 (String CHZNACHENIE11) {
        this.CHZNACHENIE11=CHZNACHENIE11;
    }

    public String getCHNAME12() {
        return CHNAME12;
    }

    public void setCHNAME12 (String CHNAME12) {
        this.CHNAME12=CHNAME12;
    }

    public String getCHZNACHENIE12() {
        return CHZNACHENIE12;
    }

    public void setCHZNACHENIE12 (String CHZNACHENIE12) {
        this.CHZNACHENIE12=CHZNACHENIE12;
    }

    public String getCHNAME13() {
        return CHNAME13;
    }

    public void setCHNAME13 (String CHNAME13) {
        this.CHNAME13=CHNAME13;
    }

    public String getCHZNACHENIE13() {
        return CHZNACHENIE13;
    }

    public void setCHZNACHENIE13 (String CHZNACHENIE13) {
        this.CHZNACHENIE13=CHZNACHENIE13;
    }

    public String getCHNAME14() {
        return CHNAME14;
    }

    public void setCHNAME14 (String CHNAME14) {
        this.CHNAME14=CHNAME14;
    }

    public String getCHZNACHENIE14() {
        return CHZNACHENIE14;
    }

    public void setCHZNACHENIE14 (String CHZNACHENIE14) {
        this.CHZNACHENIE14=CHZNACHENIE14;
    }

    public String getCHNAME15() {
        return CHNAME15;
    }

    public void setCHNAME15 (String CHNAME15) {
        this.CHNAME15=CHNAME15;
    }

    public String getCHZNACHENIE15() {
        return CHZNACHENIE15;
    }

    public void setCHZNACHENIE15 (String CHZNACHENIE15) {
        this.CHZNACHENIE15=CHZNACHENIE15;
    }

    public String getCHNAME16() {
        return CHNAME16;
    }

    public void setCHNAME16 (String CHNAME16) {
        this.CHNAME16=CHNAME16;
    }

    public String getCHZNACHENIE16() {
        return CHZNACHENIE16;
    }

    public void setCHZNACHENIE16 (String CHZNACHENIE16) {
        this.CHZNACHENIE16=CHZNACHENIE16;
    }

    public String getCHNAME17() {
        return CHNAME17;
    }

    public void setCHNAME17 (String CHNAME17) {
        this.CHNAME17=CHNAME17;
    }

    public String getCHZNACHENIE17() {
        return CHZNACHENIE17;
    }

    public void setCHZNACHENIE17 (String CHZNACHENIE17) {
        this.CHZNACHENIE17=CHZNACHENIE17;
    }

    public String getCHNAME18() {
        return CHNAME18;
    }

    public void setCHNAME18 (String CHNAME18) {
        this.CHNAME18=CHNAME18;
    }

    public String getCHZNACHENIE18() {
        return CHZNACHENIE18;
    }

    public void setCHZNACHENIE18 (String CHZNACHENIE18) {
        this.CHZNACHENIE18=CHZNACHENIE18;
    }

    public String getCHNAME19() {
        return CHNAME19;
    }

    public void setCHNAME19 (String CHNAME19) {
        this.CHNAME19=CHNAME19;
    }

    public String getCHZNACHENIE19() {
        return CHZNACHENIE19;
    }

    public void setCHZNACHENIE19 (String CHZNACHENIE19) {
        this.CHZNACHENIE19=CHZNACHENIE19;
    }

    public String getCHNAME20() {
        return CHNAME20;
    }

    public void setCHNAME20 (String CHNAME20) {
        this.CHNAME20=CHNAME20;
    }

    public String getCHZNACHENIE20() {
        return CHZNACHENIE20;
    }

    public void setCHZNACHENIE20 (String CHZNACHENIE20) {
        this.CHZNACHENIE20=CHZNACHENIE20;
    }

    public String getIMGTOVARA1() {
        return IMGTOVARA1;
    }

    public void setIMGTOVARA1 (String IMGTOVARA1) {
        this.IMGTOVARA1=IMGTOVARA1;
    }

    public String getIMGTOVARA2() {
        return IMGTOVARA2;
    }

    public void setIMGTOVARA2 (String IMGTOVARA2) {
        this.IMGTOVARA2=IMGTOVARA2;
    }

    public String getIMGTOVARA3() {
        return IMGTOVARA3;
    }

    public void setIMGTOVARA3 (String IMGTOVARA3) {
        this.IMGTOVARA3=IMGTOVARA3;
    }

    public String getIMGTOVARA4() {
        return IMGTOVARA4;
    }

    public void setIMGTOVARA4 (String IMGTOVARA4) {
        this.IMGTOVARA4=IMGTOVARA4;
    }

    public String getIMGTOVARA5() {
        return IMGTOVARA5;
    }

    public void setIMGTOVARA5 (String IMGTOVARA5) {
        this.IMGTOVARA5=IMGTOVARA5;
    }

    public String getIMGTOVARA6() {
        return IMGTOVARA6;
    }

    public void setIMGTOVARA6 (String IMGTOVARA6) {
        this.IMGTOVARA6=IMGTOVARA6;
    }

    public String getIMGTOVARA7() {
        return IMGTOVARA7;
    }

    public void setIMGTOVARA7 (String IMGTOVARA7) {
        this.IMGTOVARA7=IMGTOVARA7;
    }

    public String getIMGTOVARA8() {
        return IMGTOVARA8;
    }

    public void setIMGTOVARA8 (String IMGTOVARA8) {
        this.IMGTOVARA8=IMGTOVARA8;
    }

    public String getIMGTOVARA9() {
        return IMGTOVARA9;
    }

    public void setIMGTOVARA9 (String IMGTOVARA9) {
        this.IMGTOVARA9=IMGTOVARA9;
    }

    public String getIMGTOVARA10() {
        return IMGTOVARA10;
    }

    public void setIMGTOVARA10 (String IMGTOVARA10) {
        this.IMGTOVARA10=IMGTOVARA10;
    }

    public String getVIDEOTOVARA1() {
        return VIDEOTOVARA1;
    }

    public void setVIDEOTOVARA1 (String VIDEOTOVARA1) {
        this.VIDEOTOVARA1=VIDEOTOVARA1;
    }

    public String getVIDEOTOVARA2() {
        return VIDEOTOVARA2;
    }

    public void setVIDEOTOVARA2 (String VIDEOTOVARA2) {
        this.VIDEOTOVARA2=VIDEOTOVARA2;
    }

    public String getVIDEOTOVARA3() {
        return VIDEOTOVARA3;
    }

    public void setVIDEOTOVARA3 (String VIDEOTOVARA3) {
        this.VIDEOTOVARA3=VIDEOTOVARA3;
    }


    public String getOSTATOK() {
        return OSTATOK;
    }

    public void setOSTATOK (String OSTATOK) {
        this.OSTATOK=OSTATOK;
    }

    public String getSKLADMESTO() {
        return SKLADMESTO;
    }

    public void setSKLADMESTO (String SKLADMESTO) {
        this.SKLADMESTO=SKLADMESTO;
    }

    public String getSHABLON() {
        return SHABLON;
    }

    public void setSHABLON (String SHABLON) {
        this.SHABLON=SHABLON;
    }

    public String getVISIBILITY() {
        return VISIBILITY;
    }

    public void setVISIBILITY (String VISIBILITY) {
        this.VISIBILITY=VISIBILITY;
    }

    public String getZAKUPCENA() {
        return ZAKUPCENA;
    }

    public void setZAKUPCENA (String ZAKUPCENA) {
        this.ZAKUPCENA=ZAKUPCENA;
    }

    public String getidnumber() {
        return idnumber;
    }

}
