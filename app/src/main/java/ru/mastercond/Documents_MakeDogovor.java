package ru.mastercond;

public class Documents_MakeDogovor {



//ДЛЯ ПОЛУЧЕНИЯ ФАЙЛА ДОГОВОРА НЕОБХОДИМО СТРОГО СОБЛЮДАТЬ ПОСЛЕДОВАТЕЛЬНОСТЬ ФОРМИРОВАНИЯ ДОКУМЕНТА:
//Documents_MakeDogovor.getOsnova1 ()
//Documents_MakeDogovor.getShapka2 (String Imgaddr, String Slogan, String Site, String Phone)
//Documents_MakeDogovor.getNachaloDogovoraSection3 (String DogovorNomer, String DogovorData, String UslugiPo, String GorodZaklDogovora, String MyFullName, String MyVlice, String KontrFullName, String KontrVlice, String SrokiPostavkiTovarov,String SrokiOkazanijaUslug, String UslovijaOplatiTovarov,String UslovijaOplatiUslug, String Stoimost, String StoimostPropis)

//Documents_MakeDogovor.getTovarUslugа4 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)

//Documents_MakeDogovor.getSectionDogovora5 (String Garantija, String UslugiPo, String OsobieUslovijaDogovora, String UslovijaPriemkiTovarov, String UslovijaPriemkiUslug, String Sud) 
//Documents_MakeDogovor.getRekvizitiStoron6 (String MySokrName, String MyUrAddr, String MyFactAddr, String MyOGRN, String MyINN, String MyKPP, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS, String MyPhone, String MyMobile, String MyEmail, String MySite, String MyOtvetstvennij, String KontrSokrName, String KontrUrAddr, String KontrFactAddr, String KontrINN, String KontrKPP, String KontrOGRN, String KontrBankRekv, String KontrPhone, String KontrMobile, String KontrEmail, String KontrSite, String KontrOtvetstvennij) 

//ИЛИ 7 ИЛИ 8 В ЗАВИСИМОСТИ ОТ ТИПА ДОКУМЕНТА
//Documents_MakeDogovor.getDogovorEnd7 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora) - без печати
//Documents_MakeDogovor.getDogovorEndPechat8 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora, String MoiPechatPodpis) - с печатью



// ------1-------- ОСНОВА ДОГОВОРА (ВНУТРЕННЯЯ РАЗМЕТКА)

public String getOsnova1()
{

                String s1 = "<!DOCTYPE HTML PUBLIC \" -//W3C//DTD HTML 4.0 Transitional//EN \" > \r\n";
                String s2 =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n ";
                String s3 =  "<html><head> \r\n";
                String s4 =  "<meta http-equiv=\"Content-Type\" content=\"text/html; charset = UTF-8\">\r\n";
                String s5 =  "<style type=\"text/css\"> \r\n";
              
                String s6 = "@page {} table {border-collapse: collapse;border-spacing: 0;empty-cells: show;} td, th {vertical-align: top;font-size: 12pt;}\r\n";
                String s7 = "h1, h2, h3, h4, h5, h6 {clear: both;} * {margin: 0;} .P10 {font-size: 8pt;font-family: Tahoma;writing-mode: page;text-align: left !important;color: #000000;font-weight: bold;}\r\n";
                String s8 = ".P13 {font-size: 8pt;font-family: Tahoma;writing-mode: page;text-align: center !important;color: #000000;font-weight: bold;} .P25 {font-size: 5pt;font-family: Tahoma;writing-mode: page;text-align: justify !important;color: #000000;}\r\n";
                String s9 = ".P31 {font-size: 8pt;font-family: Tahoma;writing-mode: page;text-align: justify !important;} .P50 {font-size: 8pt; text-align: justify !important;font-family: Tahoma; writing-mode: page;color: #000000;} .P150 {font-size: 8pt; text-align: center !important; font-family: Tahoma; writing-mode: page;color: #000000;} .P250 {font-size: 8pt; text-align: right !important; font-family: Tahoma; writing-mode: page;color: #000000;} .P450 {font-size: 8pt; text-align: left !important; font-family: Tahoma; writing-mode: page;color: #000000; margin-left:10px;} .P550 {font-size: 8pt; text-align: left !important; vertical-align:middle; font-family: Tahoma; writing-mode: page;color: #000000; margin-left:10px;} .P550 img {vertical-align:middle;} .P550 p {vertical-align:middle;}\r\n";
                String s10 = ".P54 {font-size: 9.5pt;line-height: 100%;margin-left: 0.762cm;margin-right: 0cm;text-align: center !important;text-indent: 0cm;font-family: Tahoma;writing-mode: page;}\r\n";
                String s11 = ".T5 {color: #000000;font-family: Tahoma;font-size: 8pt;font-weight: bold;} .T7 {color: #000000;font-family: Tahoma;font-size: 8pt;font-weight: bold;background-color: transparent;}</style></head>\r\n";
                String s12 ="</head><body style=\"width: 19cm; \"> \r\n";

String osn=s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12;

return osn;

}




//------2-------- ШАПКА ДОГОВОРА С ЛОГОТИПОМ И СЛОГАНОМ

public String getShapka2 (String Imgaddr, String Slogan, String Site, String Phone)
{

                    String s1 =  "<table width=\"100%\"><tr><td><p align = \"left\"><img width=\"180px\" src=\"" + Imgaddr + "\">";
                    String s2 =  "</p></td><td><p class=\"P250\">" + Slogan + "</p><p class=\"P250\">Сайт: " + Site + "</p><p class=\"P250\">Телефон: " + Phone + "</p></td></tr></table><br><br> \r\n";
                    String osn=s1+s2;
                    return osn;
                    
}

//------3-------- СЕКЦИЯ НАЧАЛА ДОГОВОРА

public String getNachaloDogovoraSection3 (String DogovorNomer, String DogovorData, String UslugiPo, String GorodZaklDogovora, String MyFullName, String MyVlice, String KontrFullName, String KontrVlice, String SrokiPostavkiTovarov,String SrokiOkazanijaUslug, String UslovijaOplatiTovarov,String UslovijaOplatiUslug, String Stoimost, String StoimostPropis)
{


                String s1 = "<h1 class=\"P54\">ДОГОВОР № " + DogovorNomer + "</h1><p class=\"P25\"> </p><p style=\"font-family:Tahoma; font-size:8.5pt; text-align:center; font-weight:bold; \">на оказание услуг по " + UslugiPo + "</p>\r\n";
                String s2 = "<p class=\"P25\"> </p><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr>\r\n";
                String s3 = "<td style=\"text-align:left;\"><span class=\"T7\">" + GorodZaklDogovora + "</span></td>\r\n";
                String s4 = "<td style=\"text-align:right; \"><span class=\"T7\">" + DogovorData + "</span></td>\r\n";
                String s5 = "</tr></table><p class=\"P25\"> </p><p class=\"P25\"> </p>\r\n";
                String s6 = "<p class=\"P50\">" + MyFullName + ", именуемый(ое) в дальнейшем <span class=\"T5\">Исполнитель</span>, в лице " + MyVlice + ", с одной стороны и " + KontrFullName + ", в лице " + KontrVlice + ", именуемый(ое) в дальнейшем <span class=\"T5\">Заказчик</span>, с другой стороны, вместе именуемые в дальнейшем «Стороны» и по отдельности «Сторона», заключили настоящий договор, в дальнейшем Договор, о нижеследующем:</p><p class=\"P25\"> </p>\r\n";
                String s7 = "<p class=\"P13\">1. ПРЕДМЕТ ДОГОВОРА</p>\r\n";
                String s8 = "<p class=\"P50\">1.1 В соответствии с условиями настоящего Договора <span class=\"T6\">Исполнитель</span> обязуется поставить товары и оказать услуги, а <span class=\"T6\">Заказчик</span> принять и оплатить товары и услуги по " + UslugiPo + " в соответствии с Перечнем товаров и оказываемых услуг.</p><p class=\"P25\"> </p>\r\n";
                String s9 = "<p class=\"P13\">2. СТОИМОСТЬ И ПОРЯДОК РАСЧЕТОВ</p>\r\n";
                String s10 = "<p class=\"P50\">2.1 Стоимость товаров и услуг по " + UslugiPo + " составляет <span class=\"T7\">" + Stoimost + " рублей (" + StoimostPropis + "), НДС не облагается в соответствии с п.2 ст.346.11 Налогового кодекса Российской Федерации</span></p>\r\n";
                String s11 = "<p class=\"P50\">2.2 Сроки поставки товаров по настоящему Договору: "+SrokiPostavkiTovarov+".</p>\r\n";
                String s12 = "<p class=\"P50\">2.3 Сроки оказания услуг по настоящему Договору: "+SrokiOkazanijaUslug+".</p>\r\n";
                String s13 = "<p class=\"P50\">2.4 Условия оплаты товаров по настоящему Договору: "+ UslovijaOplatiTovarov + ".</p>\r\n";
                String s14 = "<p class=\"P50\">2.5 Условия оплаты услуг по настоящему Договору: "+UslovijaOplatiUslug+".</p>\r\n";
                String s15 = "<p class=\"P25\"> </p><p class=\"P13\">3. ПЕРЕЧЕНЬ ТОВАРОВ И ОКАЗЫВАЕМЫХ УСЛУГ</p><p class=\"P25\"> </p>\r\n";
                String s16 = "<table width=\"100%\" border=\"1px\" cellspacing=\"0\" cellpadding=\"0\" width=\"100% \" style=\"border-color:#999999;\"><tr><td width=\"10px\"><p class=\"P150\">№</p></td><td width=\"10cm\"><p class=\"P150\">Наименование товаров и услуг</p></td><td width=\"30px\"><p class=\"P150\">Кол-во</p></td><td width=\"40px\"><p class=\"P150\">Ед.изм.</p></td><td width=\"50px\"><p class=\"P150\">Цена за ед,₽</p></td><td width=\"60px\"><p class=\"P150\">Стоимость,₽</p></td></tr>\r\n";
                
                
                String osn=s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12+s13+s14+s15+s16;
                
                return osn; 
}



//------4-------- ТАБЛИЦА ТОВАРОВ И УСЛУГ

public String getTovarUslugа4 (String Number, String Naimenovanie, String EdIzm, String Kolvo, String CenaZaEd, String Stoimost)
{


                  String osn= "<tr><td width=\"5%\"><p class=\"P150\">" + Number + "</p></td><td width=\"55%\"><p class=\"P450\">" + Naimenovanie + "</p></td><td width=\"10%\"><p class=\"P150\">" + Kolvo + "</p></td><td width=\"10%\"><p class=\"P150\">" + EdIzm + "</p></td><td width=\"10%\"><p class=\"P150\">" + CenaZaEd + "</p></td><td width=\"10%\"><p class=\"P150\">" + Stoimost + "</p></td></tr>\r\n";
                    
                
                return osn;    
                
}




//------5-------- СЕКЦИЯ ДОГОВОРА ОТ ТАБЛИЦЫ ТОВАРОВ И УСЛУГ ДО РАЗДЕЛА С РЕКВИЗИТАМИ (РАЗДЕЛЫ ДОГОВОРА С 4 ПО 7)

public String getSectionDogovora5 (String Garantija, String UslugiPo, String OsobieUslovijaDogovora, String UslovijaPriemkiTovarov, String UslovijaPriemkiUslug, String Sud) 
{

                String s1 =  "</table><p class=\"P25\"> </p>\r\n";
                String s2 = "<p class=\"P13\">4. ГАРАНТИЙНЫЕ ОБЯЗАТЕЛЬСТВА</p>\r\n";
                String s3 =  "<p class=\"P50\">4.1 " + Garantija + "</p><p class=\"P25\"> </p>\r\n";
                String s4 =  "<p class=\"P13\">5. ОТВЕТСТВЕННОСТЬ СТОРОН</p>\r\n";
                String s5 = "<p class=\"P50\">5.1 Исполнитель обязуется оказать услуги по " + UslugiPo + " в соответствии с нормативно-технической документацией, установленной для данного вида работ (СНиП, ПУЭ, ППБ, ГОСТ).</p>\r\n";
                String s6 = "<p class=\"P50\">5.2 Исполнитель обязуется оказать услуги лично или с помощью иных квалифицированных лиц, с использованием своих расходных материалов или материалов Заказчика в соответствии с Перечнем товаров и оказываемых услуг (раздел 3 настоящего Договора). Ответственность за оказанные услуги, осуществленные с привлечением третьих лиц, несет Исполнитель.</p>\r\n";
                String s8 = "<p class=\"P50\">5.3 Исполнитель обязан до заключения настоящего договора предоставить Заказчику необходимую и достоверную информацию об оказываемых услугах, видах и об особенностях, о цене и форме оплаты, а также сообщить Заказчику по его просьбе другие относящиеся к договору и соответствующим услугам сведения.</p>\r\n";
                String s9 = "<p class=\"P50\">5.4 <strong>Порядок приемки товаров:</strong> " + UslovijaPriemkiTovarov + "</p>\r\n";
                String s10 = "<p class=\"P50\">5.5 <strong>Порядок приемки оказанных услуг:</strong> " + UslovijaPriemkiUslug + "</p>\r\n";
                String s12 = "<p class=\"P50\">5.6 Заказчик обязан принять и оплатить товары и услуги Исполнителя в соответствии с условиями п. 2.1, 2.4, 2.5, 5.4, 5.5 настоящего Договора.</p>\r\n";
                String s13 = "<p class=\"P50\">5.7 Заказчик обязуется обеспечить Исполнителю доступ к месту проведения работ.</p>\r\n";
                String s14 = "<p class=\"P50\">5.8 Заказчик вправе в любое время проверять ход и качество оказываемых услуг Исполнителем, не вмешиваясь при этом в его деятельность.</p>\r\n";
                String s15 = "<p class=\"P50\">5.9 Заказчик вправе в любое время до завершения оказания услуг отказаться от исполнения договора, уплатив Исполнителю часть установленной цены пропорционально части услуг, оказанных до уведомления об отказе от исполнения договора, и возместив Исполнителю расходы, произведенные до этого момента в целях исполнения договора, если они не входят в указанную часть цены оказанных услуг.</p>\r\n";
                String s16 = "<p class=\"P50\">5.10 Все изменения и дополнения к настоящему Договору производятся Сторонами путем заключения Дополнительных Соглашений.</p><p class=\"P25\"> </p>\r\n";
                String s17 = "<p class=\"P13\">6. ФОРС-МАЖОР</p>\r\n";
                String s18 = "<p class=\"P31\">6.1 В случае пожара; наводнения; землетрясения; стихийных бедствий; аварий на транспорте; мятежей; гражданских беспорядков; забастовок; войны и военных действий; публикаций нормативных актов запрещающего характера; действий (бездействий) сотрудников государственных органов, препятствующих выполнению Сторонами взятых на себя обязательств; а также других чрезвычайных обстоятельств непреодолимой силы, возникших после подписания настоящего Договора, которые Стороны не могли предвидеть или предотвратить, срок исполнения обязательств по Договору отодвигается соразмерно времени, в течение которого будут действовать эти обстоятельства, но не более чем на 3 (три) месяца.</p>\r\n";
                String s19 = "<p class=\"P31\">6.2 Указанные обстоятельства должны носить чрезвычайный, непредвиденный и непредотвратимый характер и возникнуть после заключения Договора.</p>\r\n";
                String s20 = "<p class=\"P31\">6.3 Если действие обстоятельств непреодолимой силы продлится более 6 (шести) месяцев, любая из Сторон вправе расторгнуть Договор в одностороннем порядке, направив другой Стороне соответствующее уведомление.</p>\r\n";
                String s21 = "<p class=\"P31\">6.4 Сторона, для которой создалась невозможность выполнения обязательства по Договору, должна немедленно, но в любом случае не позднее 3 (трех) дней с момента, когда Стороне стало известно о наступлении обстоятельств непреодолимой силы известить другую Сторону о наступлении обстоятельств непреодолимой силы, препятствующих выполнению обязательств. Такое уведомление должно содержать данные о характере обстоятельств и их оценку, чтобы определить возможные потери и время, необходимое для их устранения.</p>\r\n";
                String s22 = "<p class=\"P31\">6.5 Возникновение и (или) существование обстоятельств непреодолимой силы должно быть подтверждено документами, выданными компетентными органами.</p>\r\n";
                String s23 = "<p class=\"P31\">6.6 В случае возникновения обстоятельств непреодолимой силы Стороны обязуются консультироваться друг с другом относительно дальнейшего выполнения действий по Договору и прилагать все усилия к скорейшему исполнению своих обязательств.</p>\r\n";
                String s24 = "<p class=\"P31\">6.7 После прекращения обстоятельств непреодолимой силы Сторона, ссылавшаяся на такие обстоятельства, должна немедленно, но в любом случае не позднее 3 (трех) дней после прекращения действия обстоятельств непреодолимой силы, известить другую Сторону об этом и исполнить соответствующие обязательства по Договору. Сторона, не известившая или несвоевременно известившая другую Сторону о прекращении обстоятельств непреодолимой силы, обязана возместить другой Стороне все убытки, связанные с таким не извещением или несвоевременным извещением.</p>\r\n";
                String s25 = "<p class=\"P31\">6.8 Обязанность доказывания наличия обстоятельств непреодолимой силы возлагается на заявившую об этом Сторону.</p><p class=\"P25\"> </p>\r\n";
                String s26 = "<p class=\"P13\">7.  ОСОБЫЕ УСЛОВИЯ</p>\r\n";
                String s27 = "<p class=\"P50\">" + OsobieUslovijaDogovora + "<p class=\"P25\"> </p>\r\n";
                String s28 = "<p class=\"P13\">8.  ПОРЯДОК РАЗРЕШЕНИЯ СПОРОВ</p>\r\n";
                String s29 = "<p class=\"P50\">Все споры, вытекающие  из условий настоящего  Договора, Стороны будут стремиться  разрешать путем переговоров. При этом заинтересованная сторона направляет другой Стороне письменную претензию.  Ответ на претензию должен быть направлен не позднее 10 дней с момента её получения. Если Стороны в ходе переговоров не смогут достичь соглашения, то спор подлежит рассмотрению в суде. Место рассмотрения спора " + Sud + ".</p><p class=\"P50\">Настоящий Договор составлен в двух одинаковых экземплярах, имеющих равную юридическую силу, по одному экземпляру для каждой из Сторон.</p><p class=\"P25\"> </p>\r\n";
                String s30 = "<p class=\"P50\">Стороны договорились о том, что факсимильные и электронные копии договоров и иных документов, отправленные посредством электронной почты со стороны Исполнителя Заказчику и со стороны Заказчика Исполнителю имеют силу оригинала.</p><p class=\"P25\"> </p>\r\n";

                String osn=s1+s2+s3+s4+s5+s6+s8+s9+s10+s12+s13+s14+s15+s16+s17+s18+s19+s20+s21+s22+s23+s24+s25+s26+s27+s28+s29+s30;
                
                return osn; 
  }              
                
                
                
                
public String getRekvizitiStoron6 (String MySokrName, String MyUrAddr, String MyFactAddr, String MyOGRN, String MyINN, String MyKPP, String MyRS, String MyBankName, String MyBankBIK, String MyBankKS, String MyPhone, String MyMobile, String MyEmail, String MySite, String MyOtvetstvennij, String KontrSokrName, String KontrUrAddr, String KontrFactAddr, String KontrINN, String KontrKPP, String KontrOGRN, String KontrBankRekv, String KontrPhone, String KontrMobile, String KontrEmail, String KontrSite, String KontrOtvetstvennij) 
{               
                
                
                String s1 = "<p class=\"P13\">9.  АДРЕСА МЕСТОНАХОЖДЕНИЯ, БАНКОВСКИЕ РЕКВИЗИТЫ И ПОДПИСИ СТОРОН</p><p class=\"P25\"> </p>\r\n";
                String s2 = "<table border=\"1px\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-color:#999999;\"><tr><td width=\"50%\" style=\"text-align:center;\" ><p class=\"P13\">ИСПОЛНИТЕЛЬ:</p><p class=\"P25\"> </p></td><td width = \"50%\" style=\"text-align:center;\"><p class=\"P13\">ЗАКАЗЧИК:</p><p class=\"P25\"> </p></td></tr>\r\n";
                String s3 = "<tr><td width=\"50%\" style = \"text-align:center;\"><p class=\"T5\">" + MySokrName + "</p><p class=\"P25\"> </p></td><td width=\"50%\" style=\"text-align:center;\"><p class=\"T5\">" + KontrSokrName + "</p><p class=\"P25\"> </p></td></tr>\r\n";
                String s4 = "<tr><td width=\"50%\"><p class=\"P50\" style=\"margin-left:7px;\">Юридический адрес: " + MyUrAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Фактический адрес: " + MyFactAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Телефон/моб.: " + MyPhone + " / " + MyMobile + "</p><p class=\"P50\" style=\"margin-left:7px;\">Электронная почта: " + MyEmail + "</p><p class=\"P50\" style=\"margin-left:7px;\">Сайт: " + MySite + "</p><p class=\"P50\" style=\"margin-left:7px;\">ОГРН " + MyOGRN + "</p><p class=\"P50\" style=\"margin-left:7px;\">ИНН/КПП " + MyINN + " / " + MyKPP + "</p><p class=\"P50\" style=\"margin-left:7px;\">Банковские реквизиты: p/c " + MyRS + " в " + MyBankName + ", БИК " + MyBankBIK + ", к/с " + MyBankKS + "</p></td>\r\n";
                String s5 = "<td width=\"50%\"><p class=\"P50\" style=\"margin-left:7px;\">Юридический адрес: " + KontrUrAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Фактический адрес: " + KontrFactAddr + "</p><p class=\"P50\" style=\"margin-left:7px;\">Телефон: " + KontrPhone + "</p><p class=\"P50\" style=\"margin-left:7px;\">Ответственный за исполнение Договора: " + KontrOtvetstvennij + ", моб.телефон " + KontrMobile + "</p><p class=\"P50\" style=\"margin-left:7px;\">Электронная почта: " + KontrEmail + "</p><p class=\"P50\" style=\"margin-left:7px;\">Сайт: " + KontrSite + "</p><p class=\"P50\" style=\"margin-left:7px;\">ОГРН " + KontrOGRN + "</p><p class=\"P50\" style=\"margin-left:7px;\">ИНН/КПП " + KontrINN + " / " + KontrKPP + "</p><p class=\"P50\" style=\"margin-left:7px;\">Банковские реквизиты: " + KontrBankRekv + "</p></td>\r\n";

String osn=s1+s2+s3+s4+s5;
                
                return osn; 
} 

public String getDogovorEnd7 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora)

{

 
                String s1 = "</tr><tr><td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + MyRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">_________________________ /" + MyFIORuk + "/  </p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;М.П.</p></td>\r\n";
                String s2 = "<td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + KontrRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">_________________________ /" + KontrFIOruk + "/  </p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;М.П.</p></td>\r\n";
               
                String s3 = "</tr></table></body></html>\r\n";
                
                String osn=s1+s2+s3;
                
                return osn; 
                
                }
                
                
                
public String getDogovorEndPechat8 (String MyRukDolzhnost, String MyFIORuk, String KontrRukDolzhnost, String KontrFIOruk, String DataDogovora, String MoiPechatPodpis)

{
                
                String s1 = "</tr><tr><td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + MyRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"P550\"><img src=\"" + MoiPechatPodpis + "\">" + " / " + MyFIORuk + "</p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p></td>\r\n";
                String s2 = "<td width=\"50%\"><p class=\"T5\" style=\"margin-left:7px;\">" + KontrRukDolzhnost + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">_________________________ /" + KontrFIOruk + "/  </p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">" + DataDogovora + "</p><p class=\"P25\"> </p><p class=\"T5\" style=\"margin-left:7px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;М.П.</p></td>\r\n";
               
                String s3 = "</tr></table></body></html>\r\n";
                
                String osn=s1+s2+s3;
                
                return osn; 
                
                }
                
                
}
