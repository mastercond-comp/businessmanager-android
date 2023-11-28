package ru.mastercond;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AUTO_RASHODY_ListAdapter extends BaseAdapter {

   private List<AUTO_RASHODY> listData;
   private LayoutInflater layoutInflater;
   private Context context;

   public AUTO_RASHODY_ListAdapter(Context aContext, List<AUTO_RASHODY> listData) {
       this.context = aContext;
       this.listData = listData;
       layoutInflater = LayoutInflater.from(aContext);
   }

    static class ViewHolder {

        ImageView autorashodyIMGType;
        TextView autorashodyNameView;
        TextView autorashodyPriceView;
        TextView autorashodyDataView;
        TextView autorashodyProbegView;
    }

   @Override
   public int getCount() {
       return listData.size();
   }
 
   @Override
   public Object getItem(int position) {
       return listData.get(position);
   }
 
   @Override
   public long getItemId(int position) {
       return position;
   }
 
   public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
       if (convertView == null) {

           convertView = layoutInflater.inflate(R.layout.autorashody_list_item_layout, null);
           holder = new ViewHolder();
           holder.autorashodyIMGType = (ImageView)convertView.findViewById(R.id.ImageView_autorashodyType);
           holder.autorashodyNameView = (TextView)convertView.findViewById(R.id.textView_autorashodyName);
           holder.autorashodyPriceView = (TextView)convertView.findViewById(R.id.textView_autorashodyPrice);
           holder.autorashodyProbegView = (TextView)convertView.findViewById(R.id.textView_autorashodyProbeg);
           holder.autorashodyDataView = (TextView)convertView.findViewById(R.id.textView_autorashodyData);

           convertView.setTag(holder);
       } else {
           holder = (ViewHolder) convertView.getTag();
       }
 
       AUTO_RASHODY element = this.listData.get(position);
       holder.autorashodyNameView.setText(element.getautoNAIMENOVANIE());
       holder.autorashodyPriceView.setText("Стоимость: "+ String.valueOf(element.getautoSTOIMOST())+" \u20BD");
       holder.autorashodyProbegView.setText("Пробег: " + String.valueOf(element.getautoPROBEG()) + " км");

       if (element.getautoTYPE().equals("Топливо")) {holder.autorashodyIMGType.setImageResource(R.drawable.toplivo_rashodylist);}
       if (element.getautoTYPE().equals("Расходка")) {holder.autorashodyIMGType.setImageResource(R.drawable.rashodnik_rashodylist);}
       if (element.getautoTYPE().equals("Запчасть")) {holder.autorashodyIMGType.setImageResource(R.drawable.zapchasti_rashodylist);}
       if (element.getautoTYPE().equals("УслугиРемонт")) {holder.autorashodyIMGType.setImageResource(R.drawable.uslugiremont_rashodylist);}
       if (element.getautoTYPE().equals("РегулярныеРасходы")) {holder.autorashodyIMGType.setImageResource(R.drawable.regularrashodi_rashodylist);}
       if (element.getautoTYPE().equals("Штраф")) {holder.autorashodyIMGType.setImageResource(R.drawable.shtrafi_rashodylist);}
       if (element.getautoTYPE().equals("Налог")) {holder.autorashodyIMGType.setImageResource(R.drawable.nalogi_rashodylist);}
       if (element.getautoTYPE().equals("Страховка")) {holder.autorashodyIMGType.setImageResource(R.drawable.strahovka_rashodylist);}
       if (element.getautoTYPE().equals("Парковка")) {holder.autorashodyIMGType.setImageResource(R.drawable.parkovka_rashodylist);}
       if (element.getautoTYPE().equals("")) {holder.autorashodyIMGType.setImageResource(R.drawable.toplivo_rashodylist);}

       holder.autorashodyDataView.setText("Дата: "+String.valueOf(element.getautoDATA_D())+"-"+String.valueOf(element.getautoDATA_M())+"-"+String.valueOf(element.getautoDATA_Y()));
 
       return convertView;
   }
 
 

 

}
