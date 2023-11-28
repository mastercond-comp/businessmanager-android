package ru.mastercond;

public class Documents_MakeAkt {


//ДЛЯ ПОЛУЧЕНИЯ ФАЙЛА АКТА НЕОБХОДИМО СТРОГО СОБЛЮДАТЬ ПОСЛЕДОВАТЕЛЬНОСТЬ ФОРМИРОВАНИЯ ДОКУМЕНТА:
//Documents_MakeAkt.getOsnova1 ()
//Documents_MakeAkt.getShapka2 (String Imgaddr, String Slogan, String Site, String Phone)
//Documents_MakeAkt.getNachaloAktaSection3 (String AktNomer, String AktData, String DogovorNomer, String DogovorData, String UslugiPo, String GorodZaklDogovora, String MyFullName, String MyVlice, String KontrFullName, String KontrVlice, String Stoimost, String StoimostPropis)

//Documents_MakeAkt.getTovarUslugа4 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)

//Documents_MakeAkt.getAktEndTable5 (String Stoimost, String StoimostPropis)
//Documents_MakeAkt.getRekvizitiStoron6 (String MySokrName, String MyUrAddr, String MyFactAddr, String MyOGRN, String MyINN, String MyKPP, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS, String MyPhone, String MyMobile, String MyEmail, String MySite, String MyOtvetstvennij, String KontrSokrName, String KontrUrAddr, String KontrFactAddr, String KontrINN, String KontrKPP, String KontrOGRN, String KontrBankRekv, String KontrPhone, String KontrMobile, String KontrEmail, String KontrSite, String KontrOtvetstvennij) 

//ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
//Documents_MakeAkt.getDogovorEnd7 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora) - без печати
//Documents_MakeAkt.getDogovorEndPechat8 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora, String MoiPechatPodpis) - с печатью




// ------1-------- ОСНОВА АКТА (ВНУТРЕННЯЯ РАЗМЕТКА)

public String getOsnova1()
{

                String s1 = "<!DOCTYPE HTML PUBLIC \" -//W3C//DTD HTML 4.0 Transitional//EN \" > \r\n";
                String s2 =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n ";
                String s3 =  "<html><head> \r\n";
                String s4 =  "<meta http-equiv=\"Content-Type\" content=\"text/html; charset = UTF-8\">\r\n";
                String s5 =  "<style type=\"text/css\"> \r\n";
              
                String s6 = "@page {} table {border-collapse: collapse;border-spacing: 0;empty-cells: show;} td, th {vertical-align: top;font-size: 12pt;}\r\n";
                String s7 = "h1, h2, h3, h4, h5, h6 {clear: both;} * {margin: 0;} .P10 {font-size: 8pt;font-family: Tahoma;writing-mode: page;text-align: left !important;color: #000000;font-weight: bold;}\r\n";
                String s8 = ".P13 {font-size: 8pt;font-family: Tahoma;writing-mode: page;text-align: center !important;color: #000000;font-weight: bold;} .P113 {font-size: 8pt;font-family: Tahoma;writing-mode: page;text-align: left !important;color: #000000;font-weight: bold;} .P25 {font-size: 5pt;font-family: Tahoma;writing-mode: page;text-align: justify !important;color: #000000;}\r\n";
                String s9 = ".P31 {font-size: 8pt;font-family: Tahoma;writing-mode: page;text-align: justify !important;} .P50 {font-size: 8pt; text-align: justify !important;font-family: Tahoma; writing-mode: page;color: #000000;} .P150 {font-size: 8pt; text-align: center !important; font-family: Tahoma; writing-mode: page;color: #000000;} .P250 {font-size: 8pt; text-align: right !important; font-family: Tahoma; writing-mode: page;color: #000000;} .P450 {font-size: 8pt; text-align: left !important; font-family: Tahoma; writing-mode: page;color: #000000; margin-left:10px;} .P550 {font-size: 8pt; text-align: left !important; vertical-align:middle; font-family: Tahoma; writing-mode: page;color: #000000; margin-left:10px;} .P550 img {vertical-align:middle;} .P550 p {vertical-align:middle;}\r\n";
                String s10 = ".P54 {font-size: 9.5pt;line-height: 100%;margin-left: 0.762cm;margin-right: 0cm;text-align: center !important;text-indent: 0cm;font-family: Tahoma;writing-mode: page;}\r\n";
                String s11 = ".T5 {color: #000000;font-family: Tahoma;font-size: 8pt;font-weight: bold;} .T7 {color: #000000;font-family: Tahoma;font-size: 8pt;font-weight: bold;background-color: transparent;}</style></head>\r\n";
                String s12 ="</head><body style=\"width: 19cm; \"> \r\n";

String osn=s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12;

return osn;

}




//------2-------- ШАПКА АКТА С ЛОГОТИПОМ И СЛОГАНОМ

public String getShapka2 (String Imgaddr, String Slogan, String Site, String Phone)
{

                    String s1 =  "<table width=\"100%\"><tr><td><p align = \"left\"><img width=\"180px\" src=\"" + Imgaddr + "\">";
                    String s2 =  "</p></td><td><p class=\"P250\">" + Slogan + "</p><p class=\"P250\">Сайт: " + Site + "</p><p class=\"P250\">Телефон: " + Phone + "</p></td></tr></table><br><br> \r\n";
                    String osn=s1+s2;
                    return osn;
                    
}

//------3-------- СЕКЦИЯ НАЧАЛА АКТА

public String getNachaloAktaSection3 (String AktNomer, String AktData, String DogovorNomer, String DogovorData, String UslugiPo, String GorodZaklDogovora, String MyFullName, String MyVlice, String KontrFullName, String KontrVlice, String Stoimost, String StoimostPropis)
{


                String s1 = "<h1 class=\"P54\">АКТ № А-" + AktNomer + "</h1><p class=\"P25\"> </p><p style=\"font-family:Tahoma; font-size:8.5pt; text-align:center; font-weight:bold; \">об оказанных услугах по " + UslugiPo + "</h1><p class=\"P25\"> </p><p style=\"font-family:Tahoma; font-size:8.5pt; text-align:center; font-weight:bold; \">к Договору № "+DogovorNomer+" от "+DogovorData+"</p>\r\n";
                String s2 = "<p class=\"P25\"> </p><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr>\r\n";
                String s3 = "<td style=\"text-align:left;\"><span class=\"T7\">" + GorodZaklDogovora + "</span></td>\r\n";
                String s4 = "<td style=\"text-align:right; \"><span class=\"T7\">" + AktData + "</span></td>\r\n";
                String s5 = "</tr></table><p class=\"P25\"> </p><p class=\"P25\"> </p>\r\n";
                String s6 = "<p class=\"P50\"><span class=\"T5\">Исполнитель</span> " + MyFullName + ", в лице " + MyVlice + ", с одной стороны, и <span class=\"T5\">Заказчик</span> " + KontrFullName + ", в лице " + KontrVlice + ", с другой стороны, составили настоящий Акт о нижеследующем:</p><p class=\"P25\"> </p>\r\n";
                String s7 = "<p class=\"P25\"> </p><p class=\"P13\">ПЕРЕЧЕНЬ УСЛУГ</p><p class=\"P25\"> </p>\r\n";
                String s8 = "<table width=\"100%\" border=\"1px\" cellspacing=\"0\" cellpadding=\"0\" width=\"100% \" style=\"border-color:#999999;\"><tr><td width=\"10px\"><p class=\"P150\">№</p></td><td width=\"10cm\"><p class=\"P150\">Наименование товаров и услуг</p></td><td width=\"30px\"><p class=\"P150\">Кол-во</p></td><td width=\"40px\"><p class=\"P150\">Ед.изм.</p></td><td width=\"50px\"><p class=\"P150\">Цена за ед,₽</p></td><td width=\"60px\"><p class=\"P150\">Стоимость,₽</p></td></tr>\r\n";
                
                
                String osn=s1+s2+s3+s4+s5+s6+s7+s8;
                
                return osn; 
}

//------4-------- ТАБЛИЦА ТОВАРОВ И УСЛУГ

public String getTovarUslugа4 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
{


                  String osn= "<tr><td width=\"5%\"><p class=\"P150\">" + Number + "</p></td><td width=\"55%\"><p class=\"P450\">" + Naimenovanie + "</p></td><td width=\"10%\"><p class=\"P150\">" + Kolvo + "</p></td><td width=\"10%\"><p class=\"P150\">" + EdIzm + "</p></td><td width=\"10%\"><p class=\"P150\">" + CenaZaEd + "</p></td><td width=\"10%\"><p class=\"P150\">" + Stoimost + "</p></td></tr>\r\n";
                    
                
                return osn;    
                
}


public String getAktEndTable5 (String Stoimost, String StoimostPropis) {

String s1 = "</table><p class=\"P25\"> </p><p style=\"font-family:Tahoma; font-size:7.5pt; text-align:left; font-weight:bold; \">Общая стоимость оказанных услуг: " + Stoimost + " рублей (" + StoimostPropis + "), НДС не облагается в соответствии с п.2 ст.346.11 Налогового кодекса Российской Федерации</p><p class=\"P25\"> </p><p class=\"P25\"> </p>\r\n";
                String s2 = "<p class=\"P50\">Перечисленные выше услуги со стороны Исполнителя Заказчику в полном объеме и в оговоренные Сторонами сроки оказаны.</p><p class=\"P25\"> </p><p class=\"P113\">ЗАКАЗЧИК К ИСПОЛНИТЕЛЮ ПРЕТЕНЗИЙ НЕ ИМЕЕТ.</p><p class=\"P25\"> </p><br>";

String osn=s1+s2;

return osn;

}

public String getRekvizitiStoron6 (String MySokrName, String MyUrAddr, String MyFactAddr, String MyOGRN, String MyINN, String MyKPP, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS, String MyPhone, String MyMobile, String MyEmail, String MySite, String MyOtvetstvennij, String KontrSokrName, String KontrUrAddr, String KontrFactAddr, String KontrINN, String KontrKPP, String KontrOGRN, String KontrBankRekv, String KontrPhone, String KontrMobile, String KontrEmail, String KontrSite, String KontrOtvetstvennij) 
{               
                
                
                String s1 = "<p class=\"P13\">АДРЕСА МЕСТОНАХОЖДЕНИЯ, БАНКОВСКИЕ РЕКВИЗИТЫ И ПОДПИСИ СТОРОН</p><p class=\"P25\"> </p>\r\n";
                String s2 = "<table border=\"1px\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-color:#999999;\"><tr><td width=\"50%\" style=\"text-align:center;\" ><p class=\"P13\">ИСПОЛНИТЕЛЬ:</p><p class=\"P25\"> </p></td><td width = \"50%\" style=\"text-align:center;\"><p class=\"P13\">ЗАКАЗЧИК:</p><p class=\"P25\"> </p></td></tr>\r\n";
                String s3 = "<tr><td width=\"50%\" style = \"text-align:center;\"><p class=\"T5\">" + MySokrName + "</p><p class=\"P25\"> </p></td><td width=\"50%\" style=\"text-align:center;\"><p class=\"T5\">" + KontrSokrName + "</p><p class=\"P25\"> </p></td></tr>\r\n";
                String s4 = "<tr><td width=\"50%\"><p class=\"P50\" style=\"margin-left:7px;\">Юридический адрес: " + MyUrAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Фактический адрес: " + MyFactAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Телефон/моб.: " + MyPhone + " / " + MyMobile + "</p><p class=\"P50\" style=\"margin-left:7px;\">Электронная почта: " + MyEmail + "</p><p class=\"P50\" style=\"margin-left:7px;\">Сайт: " + MySite + "</p><p class=\"P50\" style=\"margin-left:7px;\">ОГРН " + MyOGRN + "</p><p class=\"P50\" style=\"margin-left:7px;\">ИНН/КПП " + MyINN + " / " + MyKPP + "</p><p class=\"P50\" style=\"margin-left:7px;\">Банковские реквизиты: p/c " + MyRS + " в " + MyBankName + ", БИК " + MyBankBIK + ", к/с " + MyBankKS + "</p></td>\r\n";
                String s5 = "<td width=\"50%\"><p class=\"P50\" style=\"margin-left:7px;\">Юридический адрес: " + KontrUrAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Фактический адрес: " + KontrFactAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Телефон: " + KontrPhone + "</p><p class=\"P50\" style=\"margin-left:7px;\">Ответственный за исполнение Договора: " + KontrOtvetstvennij + ", моб.телефон " + KontrMobile + "</p><p class=\"P50\" style=\"margin-left:7px;\">Электронная почта: " + KontrEmail + "</p><p class=\"P50\" style=\"margin-left:7px;\">Сайт: " + KontrSite + "</p><p class=\"P50\" style=\"margin-left:7px;\">ОГРН " + KontrOGRN + "</p><p class=\"P50\" style=\"margin-left:7px;\">ИНН/КПП " + KontrINN + " / " + KontrKPP + "</p><p class=\"P50\" style=\"margin-left:7px;\">Банковские реквизиты: " + KontrBankRekv + "</p></td>\r\n";

String osn=s1+s2+s3+s4+s5;
                
                return osn; 
} 

public String getAktEnd7 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora)

{

 
                String s1 = "</tr><tr><td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + MyRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">_________________________ /" + MyFIORuk + "/  </p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;М.П.</p></td>\r\n";
                String s2 = "<td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + KontrRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">_________________________ /" + KontrFIOruk + "/  </p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;М.П.</p></td>\r\n";
               
                String s3 = "</tr></table></body></html>\r\n";
                
                String osn=s1+s2+s3;
                
                return osn; 
                
                }
                
                
                
public String getAktEndPechat8 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora, String MoiPechatPodpis)

{
                
                String s1 = "</tr><tr><td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + MyRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"P550\"><img src=\"" + MoiPechatPodpis + "\">" + " / " + MyFIORuk + "</p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p></td>\r\n";
                String s2 = "<td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + KontrRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">_________________________ /" + KontrFIOruk + "/  </p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;М.П.</p></td>\r\n";
               
                String s3 = "</tr></table></body></html>\r\n";
                
                String osn=s1+s2+s3;
                
                return osn; 
                
                }


}
