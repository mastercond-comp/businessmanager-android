package ru.mastercond;



public class Documents_MakeSchet {


//ДЛЯ ПОЛУЧЕНИЯ ФАЙЛА СЧЕТА НЕОБХОДИМО СТРОГО СОБЛЮДАТЬ ПОСЛЕДОВАТЕЛЬНОСТЬ ФОРМИРОВАНИЯ ДОКУМЕНТА:
//Documents_MakeSchet.getOsnova1 ()
//Documents_MakeSchet.getShapka2 (String Imgaddr, String Slogan, String Site, String Phone)
//Documents_MakeSchet.getTablePP3 (String MyFullName, String MyINN, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS)
//Documents_MakeSchet.getProdPokupSection4 (String SchetNomer, String SchetData, String DogovorNomer, String DogovorData, String MyFullName, String MyUrAddr, String MyFactAddr, String MyOGRN, String MyINN, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS, String MyPhone, String MyMobile, String MyEmail, String MySite, String KontrFullName, String KontrUrAddr, String KontrFactAddr, String KontrINN, String KontrKPP, String KontrOGRN, String KontrBankRekv, String KontrPhone, String KontrMobile, String KontrEmail, String KontrSite)
//Documents_MakeSchet.getNachaloTabliciTovarovUslug5 ()

//Documents_MakeSchet.getTovarUslugа6 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)

//ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
//Documents_MakeSchet.getSchetEnd7 (String Stoimost, String StoimostPropis, String MojaRukDolzhn, String MojFioRuk) - без печати
//Documents_MakeSchet.getSchetEndPechat8 (String Stoimost, String StoimostPropis, String MojaRukDolzhn, String MojFioRuk, String MoiPechatPodpis) - с печатью





// ------1-------- ОСНОВА СЧЕТА (ВНУТРЕННЯЯ РАЗМЕТКА)
public String getOsnova1()
{

                String s1 = "<!DOCTYPE HTML PUBLIC \" -//W3C//DTD HTML 4.0 Transitional//EN \" > \r\n";
                String s2 =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n ";
                String s3 =  "<html><head> \r\n";
                String s4 =  "<meta http-equiv=\"Content-Type\" content=\"text/html; charset = UTF-8\">\r\n";
                String s5 =  "<style type=\"text/css\"> \r\n";
                String s6 =  "table { border-collapse:collapse; border-spacing:0; empty-cells:show } td, th { vertical-align:top; font-size:12pt;} h1, h2, h3, h4, h5, h6 { clear: both } ol, ul { margin: 0; padding: 0;} li { list-style: none; margin: 0; padding: 0;} li span. { clear: both; line-height:0;width: 0; height: 0; margin: 0; padding: 0;} span.footnodeNumber { padding-right:1em;} span.annotation_style_by_filter {font-size:95 %; font-family:Arial; background-color:#fff000;  margin:0; border:0; padding:0;} * { margin: 0;}  \r\n";
                String s7 =  ".P1 {color:#00000a; font-size:14.5pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:center ! important; font-family:Tahoma; writing-mode:lr-tb; font-weight:bold; } .P10 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; } .P101 {color:#00000a; font-size:9pt; line-height:120%; margin-bottom:0cm; margin-top:0cm; text-align:right ! important; font-family:Tahoma; writing-mode:lr-tb; }.P11 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:right ! important; font-family:Tahoma; writing-mode:lr-tb; }.P12 {color:#00000a; font-size:9pt; line-height:140%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P13 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:center ! important; font-family:Tahoma; writing-mode:lr-tb; }.P14 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:right ! important; font-family:Tahoma; writing-mode:lr-tb; }.P15 {color:#00000a; font-size:9pt; line-height:120%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P16 {color:#00000a; font-size:9pt; line-height:120%; margin-bottom:0cm; margin-top:0cm; text-align:center ! important; font-family:Tahoma; writing-mode:lr-tb; }.P17 {color:#00000a; font-size:9pt; line-height:120%; margin-bottom:0cm; margin-top:0cm; text-align:right ! important; font-family:Tahoma; writing-mode:lr-tb; }.P18 {color:#00000a; font-size:9pt; line-height:120%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; font-weight:bold; } .P18r {color:#00000a; font-size:9pt; line-height:120%; margin-bottom:0cm; margin-top:0cm; text-align:right ! important; font-family:Tahoma; writing-mode:lr-tb; font-weight:bold; } .P19 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; margin-left:0cm; margin-right:0cm; text-indent:0cm; }.P2 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P20 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; margin-left:0cm; margin-right:0cm; text-indent:0cm; font-weight:bold; }.P21 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0.353cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; margin-left:0cm; margin-right:0cm; text-indent:0cm; } /r/n .P22 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0.529cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P23 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0.529cm; text-align:right ! important; font-family:Tahoma; writing-mode:lr-tb; }.P24 {color:#00000a; font-size:9pt; line-height:120%; margin-bottom:0cm; margin-top:0.529cm; text-align:right ! important; font-family:Tahoma; writing-mode:lr-tb; }.P25 {color:#00000a; font-size:10pt; line-height:100%; margin-bottom:0cm; margin-top:0.529cm; text-align:center ! important; font-family:Tahoma; writing-mode:lr-tb; }.P26 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P27 {color:#00000a; font-size:10pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P28 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Arial; writing-mode:lr-tb; }.P29 {color:#000000; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Arial; writing-mode:lr-tb; font-style:normal; font-weight:normal; }.P3 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P30 {color:#00000a; font-size:8pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Arial; writing-mode:lr-tb; }.P31 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Arial; writing-mode:lr-tb; } \r\n";
                String s8 =  ".P4 {color:#00000a; font-size:10pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P5 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:center ! important; font-family:Tahoma; writing-mode:lr-tb; }.P6 {color:#000000; font-size:8pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P7 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Arial; writing-mode:lr-tb; }.P8 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Tahoma; writing-mode:lr-tb; }.P9 {color:#00000a; font-size:9pt; line-height:100%; margin-bottom:0cm; margin-top:0cm; text-align:left ! important; font-family:Arial; writing-mode:lr-tb; } \r\n";
                String s9 =  ".Standard {font-size:9pt; font-family:Arial; writing-mode:lr-tb; margin-top:0cm; margin-bottom:0cm; line-height:100 %; text-align:left !important; color:#00000a; }.Таблица1 { width: 18.706cm; margin-left:-0.191cm; margin-right:-1.515cm; margin-top:0cm; margin-bottom:0cm; float:none; writing-mode:lr-tb;}.Таблица2 { width: 18.694cm; margin-left:-0.191cm; margin-top:0cm; margin-bottom:0cm; writing-mode:lr-tb;}.Таблица3 { width: 18.694cm; margin-left:-0.191cm; margin-top:0cm; margin-bottom:0cm; writing-mode:lr-tb;}.Таблица1_A1 {background-color:#ffffff; padding-left:0.173cm; padding-right:0.191cm; padding-top:0.101cm; padding-bottom:0.101cm; border-left-width:0.018cm; border-left-style:solid; border-left-color:#00000a; border-right-width:0.018cm; border-right-style:solid; border-right-color:#00000a; border-top-width:0.018cm; border-top-style:solid; border-top-color:#00000a; border-bottom-style:none; }.Таблица1_A3 {background-color:#ffffff; padding-left:0.173cm; padding-right:0.191cm; padding-top:0.101cm; padding-bottom:0.101cm; border-width:0.018cm; border-style:solid; border-color:#00000a; }.Таблица1_E1 {vertical-align:middle; background-color:#ffffff; padding-left:0.173cm; padding-right:0.191cm; padding-top:0.101cm; padding-bottom:0.101cm; border-width:0.018cm; border-style:solid; border-color:#00000a; }.Таблица1_F2 {background-color:#ffffff; padding-left:0.173cm; padding-right:0.191cm; padding-top:0.101cm; padding-bottom:0.101cm; border-left-width:0.018cm; border-left-style:solid; border-left-color:#00000a; border-right-width:0.018cm; border-right-style:solid; border-right-color:#00000a; border-top-style:none; border-bottom-style:none; }.Таблица1_F4 {background-color:#ffffff; padding-left:0.173cm; padding-right:0.191cm; padding-top:0.101cm; padding-bottom:0.101cm; border-left-width:0.018cm; border-left-style:solid; border-left-color:#00000a; border-right-width:0.018cm; border-right-style:solid; border-right-color:#00000a; border-top-width:0.018cm; border-top-style:solid; border-top-color:#00000a; border-bottom-style:none; }.Таблица2_A1 {background-color:#ffffff; padding-left:0.191cm; padding-right:0.191cm; padding-top:0cm; padding-bottom:0cm; border-style:none; }.Таблица3_A1 {vertical-align:middle; background-color:#ffffff; padding-left:0.155cm; padding-right:0.191cm; padding-top:0cm; padding-bottom:0cm; border-width:0.018cm; border-style:solid; border-color:#00000a; } \r\n";
                String s10 =  ".Таблица3_A4 {vertical-align:middle; background-color:#ffffff; padding-left:0.155cm; padding-right:0.191cm; padding-top:0cm; padding-bottom:0cm; border-left-width:0.018cm; border-left-style:solid; border-left-color:#00000a; border-right-width:0.018cm; border-right-style:solid; border-right-color:#00000a; border-top-style:none; border-bottom-width:0.018cm; border-bottom-style:solid; border-bottom-color:#00000a; }.Таблица3_B4 {vertical-align:middle; background-color:#ffffff; padding-left:0.155cm; padding-right:0.191cm; padding-top:0cm; padding-bottom:0cm; border-left-width:0.018cm; border-left-style:solid; border-left-color:#00000a; border-right-width:0.018cm; border-right-style:solid; border-right-color:#00000a; border-top-style:none; border-bottom-width:0.018cm; border-bottom-style:solid; border-bottom-color:#00000a; }.Таблица3_C3 {vertical-align:middle; background-color:#ffffff; padding-left:0.155cm; padding-right:0.191cm; padding-top:0cm; padding-bottom:0cm; border-width:0.018cm; border-style:solid; border-color:#00000a; }.Таблица1_A { width: 1.723cm; }.Таблица1_B { width: 6.145cm;}.Таблица1_C { width: 1.812cm;}.Таблица1_D { width: 1.194cm;}.Таблица1_E { width: 1.482cm;}.Таблица1_F { width: 6.352cm;}.Таблица2_A { width: 2.193cm;}.Таблица2_B { width: 16.501cm;}.Таблица3_A { width: 0.93cm;}.Таблица3_B { width: 9.745cm;}.Таблица3_C { width: 1.535cm;}.Таблица3_D { width: 1.334cm;}.Таблица3_E { width: 2.35cm;}.Таблица3_F { width: 2.801cm; }.Internet_20_link {color:#000080; text-decoration:underline; }.T1 { font-size:10pt;}.T13 {color:#000000; font-size:9pt; font-style:normal; font-weight:normal; }.T15 { font-size:14.5pt; font-weight:bold;}.T16 { font-size:14.5pt; font-weight:bold;}.T17 { font-family:Tahoma; font-size:10pt;}.T18 {font-family:Tahoma; font-size:10pt;}.T19 { font-family:Tahoma; font-weight:normal;}.T2 { font-size:10pt;}.T3 { font-size:10pt;}.T4 {font-size:10pt; text-decoration:none !important;}.T6 { font-size:9pt;}.T7 { font-size:9pt;}.Таблица1.1.Таблица2.1.Таблица2.2.Таблица3.1.Таблица3.2.Таблица3.5.T10.T11.T12.T14.T5.T8.T9 {}       </style> \r\n";
                String s11 =  "</head><body style=\"max-width:19cm; margin-left:0.3cm;\"> \r\n";

String osn=s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11;

return osn;

}



//------2-------- ШАПКА СЧЕТА С ЛОГОТИПОМ И СЛОГАНОМ

public String getShapka2 (String Imgaddr, String Slogan, String Site, String Phone)
{
  
                    String s1 =  "<table width=\"100%\"><tr><td><p align = \"left\"><img width=\"180px\" src=\"" + Imgaddr + "\">";
                    String s2 =  "</p></td><td><p class=\"P101\">" + Slogan + "</p><p class=\"P101\">Сайт: " + Site + "</p><p class=\"P101\">Телефон: " + Phone + "</p></td></tr></table><br><br> \r\n";
                    String osn=s1+s2;
                    return osn;
                    
}


//------3-------- ТАБЛИЦА ОБРАЗЦА ПЛАТЕЖНОГО ПОРУЧЕНИЯ

public String getTablePP3 (String MyFullName, String MyINN, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS)
{


                String osn="<p class=\"P6\">Образец заполнения платежного поручения</p><p class=\"P26\"> </p><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\" class=\"Таблица1\"><colgroup><col width=\"75\"/><col width=\"269\"/><col width=\"79\"/><col width=\"52\"/><col width=\"65\"/><col width=\"278\"/></colgroup><tr class=\"Таблица11\"><td colspan=\"4\" rowspan=\"2\" style=\"text-align:left; width: 1.723cm;\" class=\"Таблица1_A1\"><p class=\"P7\"><span class=\"T14\">" + MyBankName + "</span></p></td><td style=\"text-align:left; width: 1.482cm;\" class=\"Таблица1_E1\"><p class=\"P7\">БИК</p></td><td style=\"text-align:left; width: 6.352cm; \" class=\"Таблица1_A1\"><p class=\"P28\">" + MyBankBIK + "</p></td></tr><tr class=\"Таблица11\"><td style=\"text-align:left; width: 1.482cm;\" class=\"Таблица1_A1\"><p class=\"P7\">Сч. №</p></td><td style=\"text-align:left; width: 6.352cm; \" class=\"Таблица1_F2\"><p class=\"P29\">" + MyBankKS + "</p></td></tr><tr class=\"Таблица11\"><td colspan=\"4\" style=\"text-align:left; width: 1.723cm; \" class=\"Таблица1_A3\"><p class=\"P30\">Банк получателя</p></td><td style=\"text-align:left; width: 1.482cm; \" class=\"Таблица1_A3\"><p class=\"P9\"></p></td><td style=\"text-align:left; width: 6.352cm; \" class=\"Таблица1_A3\"><p class=\"P9\"></p></td></tr><tr class=\"Таблица11\"><td style=\"text-align:left; width: 1.723cm; \" class=\"Таблица1_E1\"><p class=\"P7\">ИНН</p></td><td style=\"text-align:left; width: 6.145cm; \" class=\"Таблица1_A3\"><p class=\"P7\">" + MyINN + "</p></td><td style=\"text-align:left; width: 1.812cm; \" class=\"Таблица1_E1\"><p class=\"P7\">КПП</p></td><td style=\"text-align:left; width: 1.194cm; \" class=\"Таблица1_A3\"><p class=\"P9\"> </p></td><td style=\"text-align:left; width: 1.482cm; \" class=\"Таблица1_A1\"><p class=\"P7\">Сч. №</p></td><td style=\"text-align:right; width: 6.352cm; \" class=\"Таблица1_F4\"><p class=\"P31\">" + MyRS + "</p></td></tr><tr class=\"Таблица11\"><td colspan=\"4\" style=\"text-align:left; width: 1.723cm; \" class=\"Таблица1_A1\"><p class=\"P7\">" + MyFullName + "</p></td><td style=\"text-align:left; width: 1.482cm; \" class=\"Таблица1_F2\"><p class=\"P9\"> </p></td><td style=\"text-align:left; width: 6.352cm; \" class=\"Таблица1_F2\"><p class=\"P9\"> </p></td></tr><tr class=\"Таблица11\"><td colspan=\"4\" style=\"text-align:left; width: 1.723cm; \" class=\"Таблица1_A3\"><p class=\"P30\">Получатель</p></td><td style=\"text-align:left; width: 1.482cm; \" class=\"Таблица1_A3\"><p class=\"P9\"> </p></td><td style=\"text-align:left; width: 6.352cm; \" class=\"Таблица1_A3\"><p class=\"P9\"> </p></td></tr></table><br> \r\n";
         return osn;   
            
}


//------4-------- СЕКЦИИ РЕКВИЗИТОВ ПРОДАВЦА И ПОКУПАТЕЛЯ

public String getProdPokupSection4 (String SchetNomer, String SchetData, String DogovorNomer, String DogovorData, String MyFullName, String MyUrAddr, String MyFactAddr, String MyOGRN, String MyINN, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS, String MyPhone, String MyMobile, String MyEmail, String MySite, String KontrFullName, String KontrUrAddr, String KontrFactAddr, String KontrINN, String KontrKPP, String KontrOGRN, String KontrBankRekv, String KontrPhone, String KontrMobile, String KontrEmail, String KontrSite)
{


                String s1="<p class=\"P5\"><span class=\"T16\">Счет на оплату № C-" + SchetNomer + " от " + SchetData + "<br><br>к Договору № "+ DogovorNomer+" от " + DogovorData + "</span></p><br> \r\n";
                String s2 = "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"Таблица2\"><colgroup><col width=\"96\"/><col width=\"721\"/></colgroup><tr class=\"Таблица21\"><td style=\"text-align:left;width:2.193cm; \" class=\"Таблица2_A1\"><p class=\"P15\">Поставщик:</p></td><td style=\"text-align:left;width:16.501cm; \" class=\"Таблица2_A1\"> \r\n";
                String s3 = "<p class=\"P18\">" + MyFullName + "</p><p class=\"P15\">Юридический адрес: " + MyUrAddr + "</p><p class=\"P15\">Фактический адрес: " + MyFactAddr + "</p><p class=\"P15\">ОГРН " + MyOGRN + "</p><p class=\"P15\">ИНН " + MyINN + "</p><p class=\"P15\">Р/cчет " + MyRS + " в " + MyBankName + "</p><p class=\"P15\">БИК " + MyBankBIK + " корр.счет " + MyBankKS + "</p> \r\n";
                String s4 = "<p class=\"P15\">Тел.: " + MyPhone + "</p>" + "<p class=\"P15\">Моб.: " + MyMobile + "</p><p class=\"P15\">Эл.почта: " + MyEmail + "</p><p class=\"P15\"><span class=\"T19\">Сайт: " + MySite + "</span></p><br> \r\n";
                String s5 ="<p class=\"P8\"></p></td></tr><tr class=\"Таблица22\"><td style=\"text-align:left; width: 2.193cm; \" class=\"Таблица2_A1\"><p class=\"P15\">Покупатель:</p></td><td style=\"text-align:left;width:16.501cm; \" class=\"Таблица2_A1\"> \r\n";
                String s6 = "<p class=\"P18\">" + KontrFullName + "</p><p class=\"P15\">Юридический адрес: " + KontrUrAddr + "</p><p class=\"P15\">Фактический адрес: " + KontrFactAddr + "</p><p class=\"P15\">ИНН " + KontrINN + " КПП " + KontrKPP + " ОГРН " + KontrOGRN + "</p><p class=\"P15\">" + KontrBankRekv + "</p><p class=\"P15\">Тел: " + KontrPhone + "</p><p class=\"P15\">Моб.: " + KontrMobile + "</p><p class=\"P15\">Эл.почта: " + KontrEmail + "</p><p class=\"P15\">Сайт: " + KontrSite + "</p></td></tr></table><br> \r\n";
                
                String osn=s1+s2+s3+s4+s5+s6;
                
                return osn; 
}


//------5-------- НАЧАЛО ТАБЛИЦЫ ТОВАРОВ И УСЛУГ

public String getNachaloTabliciTovarovUslug5 ()
{


                String s1= "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"Таблица3\"><colgroup><col width=\"41\"/><col width=\"426\"/><col width=\"67\"/><col width=\"58\"/><col width=\"103\"/><col width=\"122\"/></colgroup>";
                String s2 = "<tr class=\"Таблица31\"><td style =\"text-align:left;width:0.93cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>№</strong></p></td> \r\n";
                String s3 = "<td style=\"text-align:left;width:9.745cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>Товары(работы, услуги)</strong></p></td> \r\n";
                String s4 = "<td style=\"text-align:left;width:1.535cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>Кол-во</strong></p></td> \r\n";
                String s5 = "<td style =\"text-align:left;width:1.334cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>Ед.</strong></p></td> \r\n";
                String s6 = "<td style = \"text-align:left;width:2.35cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>Цена, ₽</strong></p></td> \r\n";
                String s7 = "<td style =\"text-align:left;width:2.801cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>Сумма, ₽</strong></p></td></tr> \r\n";
                
                String osn=s1+s2+s3+s4+s5+s6+s7;
                
                return osn; 
}


//------6--------  ДОБАВЛЕНИЕ В ТАБЛИЦУ ТОВАРОВ И УСЛУГ

public String getTovarUslugа6 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
{


                String s1= "<tr class=\"Таблица31\"><td style =\"text-align:left;width:0.93cm; \" class=\"Таблица3_A1\"><p class=\"P16\">" + Number + "</p></td> \r\n";
                    String s2= "<td style=\"text-align:left;width:9.745cm; \" class=\"Таблица3_A1\"><p class=\"P12\">" + Naimenovanie + "</p></td> \r\n";
                    String s3= "<td style=\"text-align:left;width:1.535cm; \" class=\"Таблица3_A1\"><p class=\"P16\">" + Kolvo + "</p></td> \r\n";
                    String s4= "<td style =\"text-align:left;width:1.334cm; \" class=\"Таблица3_A1\"><p class=\"P16\">" + EdIzm + "</p></td> \r\n";
                    String s5= "<td style = \"text-align:left;width:2.35cm; \" class=\"Таблица3_A1\"><p class=\"P16\">" + CenaZaEd + "</p></td> \r\n";
                    String s6= "<td style =\"text-align:left;width:2.801cm; \" class=\"Таблица3_A1\"><p class=\"P16\">" + Stoimost + "</p></td></tr> \r\n";
                
                String osn=s1+s2+s3+s4+s5+s6;
                
                return osn; 
}


//------7--------  СФОРМИРОВАТЬ КОНЕЦ КОДА СЧЕТА (без печати)
public String getSchetEnd7 (String Stoimost, String StoimostPropis, String MojaRukDolzhn, String MojFioRuk)
{
                    String s01= "<tr class=\"Таблица31\"><td style=\"text-align:left;width:0.93cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s02= "<td style=\"text-align:left;width:9.745cm; \" class=\"Таблица3_A1\"><p class=\"P12\"><strong>ИТОГО</strong></p></td> \r\n";
                    String s03= "<td style=\"text-align:left;width:1.535cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s04= "<td style=\"text-align:left;width:1.334cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s05= "<td style= \"text-align:left;width:2.35cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s06= "<td style=\"text-align:left;width:2.801cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>" + Stoimost + "</strong></p></td></tr> \r\n";
                

                String s1= "</table><br><p class=\"P18\">Всего к оплате: " + Stoimost +" рублей 00 копеек"+ " (" + StoimostPropis + "), НДС не облагается в соответствии с п.2 ст.346.11 Налогового кодекса Российской Федерации</p><p class=\"P25\"> </p>\r\n";
                String s2= "<p class=\"P18r\"><span class=\"T3\" style=\"font-size:8.5pt;\">__________________________________" + MojaRukDolzhn + " " + MojFioRuk + "</span></p><p class=\"P25\">                                           МП</p></body></html> \r\n";
               
                
              
                String osn=s01+s02+s03+s04+s05+s06+s1+s2;
                
                return osn; 
}

//------8--------  СФОРМИРОВАТЬ КОНЕЦ КОДА СЧЕТА (с печатью)
public String getSchetEndPechat8 (String Stoimost, String StoimostPropis, String MojaRukDolzhn, String MojFioRuk, String MoiPechatPodpis)
{
                    String s01= "<tr class=\"Таблица31\"><td style=\"text-align:left;width:0.93cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s02= "<td style=\"text-align:left;width:9.745cm; \" class=\"Таблица3_A1\"><p class=\"P12\"><strong>ИТОГО</strong></p></td> \r\n";
                    String s03= "<td style=\"text-align:left;width:1.535cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s04= "<td style=\"text-align:left;width:1.334cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s05= "<td style= \"text-align:left;width:2.35cm; \" class=\"Таблица3_A1\"><p class=\"P16\"></p></td> \r\n";
                    String s06= "<td style=\"text-align:left;width:2.801cm; \" class=\"Таблица3_A1\"><p class=\"P16\"><strong>" + Stoimost + "</strong></p></td></tr> \r\n";

                String s1= "</table><br><p class=\"P18\">Всего к оплате: " + Stoimost +" рублей 00 копеек"+ " (" + StoimostPropis + "), НДС не облагается в соответствии с п.2 ст.346.11 Налогового кодекса Российской Федерации</p> \r\n";
                String s2= "<p class=\"P18r\"><span><img src=\""+ MoiPechatPodpis + "\"></span><span class=\"T3\"> / " + MojaRukDolzhn + " " + MojFioRuk + "</span></p></body></html> \r\n";
                
                
              
                String osn=s01+s02+s03+s04+s05+s06+s1+s2;
                
                return osn; 
}

}