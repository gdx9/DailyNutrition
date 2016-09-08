package manor9421.com.dailynutrition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by manor on 8/29/16.
 */
public class ProductAdapter extends BaseAdapter {

    private ArrayList<Product> products;
    private Context c;// static соханит память о старом контексте, что забьет память

    public ProductAdapter(ArrayList<Product> products, Context c) {
        this.products = products;
        this.c = c;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //convertView - кэшированный  View,  который используем повторно
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View prodView = convertView;
        if(prodView == null){//если первый раз вызвали
            // получаем  объект  LayoutInflater  из  объекта Context
            prodView = LayoutInflater.from(c).inflate(R.layout.listitem_product,null);
        }

        Product p = getItem(position);
        prodView.setTag(p.number+"");

        TextView tvName = (TextView) prodView.findViewById(R.id.tvName);
        tvName.setText(p.name+"");
        TextView tvKcal = (TextView) prodView.findViewById(R.id.tvKcal);
        tvKcal.setText(p.calories+"");
        TextView tvProteins = (TextView) prodView.findViewById(R.id.tvProteins);
        tvProteins.setText(p.proteins+"");
        TextView tvFats = (TextView) prodView.findViewById(R.id.tvFats);
        tvFats.setText(p.fats+"");
        TextView tvCarb = (TextView) prodView.findViewById(R.id.tvCarb);
        tvCarb.setText(p.carbohydrates+"");


        return prodView;
    }

    private void fillView(View v, int position){
        Product p = getItem(position);

        TextView tvName = (TextView) v.findViewById(R.id.tvName);
        tvName.setText(p.name+"");
        TextView tvKcal = (TextView) v.findViewById(R.id.tvKcal);
        tvKcal.setText(p.calories+"");
        TextView tvProteins = (TextView) v.findViewById(R.id.tvProteins);
        tvProteins.setText(p.proteins+"");
        TextView tvFats = (TextView) v.findViewById(R.id.tvFats);
        tvFats.setText(p.fats+"");
        TextView tvCarb = (TextView) v.findViewById(R.id.tvCarb);
        tvCarb.setText(p.carbohydrates+"");



    }
}
