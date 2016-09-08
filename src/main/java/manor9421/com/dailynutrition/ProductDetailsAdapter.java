package manor9421.com.dailynutrition;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by manor on 8/31/16.
 */
public class ProductDetailsAdapter extends BaseAdapter {

    ArrayList<ProductDetails> productDetails;
    Context c;

    public ProductDetailsAdapter(ArrayList<ProductDetails> productDetails, Context c) {
        this.productDetails = productDetails;
        this.c = c;
    }

    @Override
    public int getCount() {
        return productDetails.size();
    }

    @Override
    public ProductDetails getItem(int i) {
        return productDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View prodView = view;
        if(prodView == null){//если первый раз вызвали
            // получаем  объект  LayoutInflater  из  объекта Context
            prodView = LayoutInflater.from(c).inflate(R.layout.product_details_item,null);
        }

        ProductDetails pd = getItem(i);

        TextView tvName = (TextView) prodView.findViewById(R.id.paramName);
        tvName.setText(pd.name);
        tvName.setTextColor(getColorName(i));
        TextView tvKcal = (TextView) prodView.findViewById(R.id.paramVal);
        tvKcal.setText(pd.val);
        tvKcal.setTextColor(getColorName(i));

        return prodView;

    }


    private int getColorName(int n){
        int k;
        if(productDetails.size() == 47){
            k = n+1;
        }else{
            k = n;
        }


        switch(k){
            case 1:
                return ContextCompat.getColor(c,R.color.name);
            case 2:
                return ContextCompat.getColor(c,R.color.water);
            case 3:
                return ContextCompat.getColor(c,R.color.kcal);
            case 4:
                return ContextCompat.getColor(c,R.color.protein);
            case 5:
                return ContextCompat.getColor(c,R.color.lipid_tot);
            case 6:
                return ContextCompat.getColor(c,R.color.ash);
            case 7:
                return ContextCompat.getColor(c,R.color.carbohydrt);
            case 8:
                return ContextCompat.getColor(c,R.color.fiber_TD);
            case 9:
                return ContextCompat.getColor(c,R.color.sugar_Tot);
            case 10:
                return ContextCompat.getColor(c,R.color.calcium);
            case 11:
                return ContextCompat.getColor(c,R.color.iron);
            case 12:
                return ContextCompat.getColor(c,R.color.magnesium);
            case 13:
                return ContextCompat.getColor(c,R.color.phosphorus);
            case 14:
                return ContextCompat.getColor(c,R.color.potassium);
            case 15:
                return ContextCompat.getColor(c,R.color.sodium);
            case 16:
                return ContextCompat.getColor(c,R.color.zinc);
            case 17:
                return ContextCompat.getColor(c,R.color.copper);
            case 18:
                return ContextCompat.getColor(c,R.color.manganese);
            case 19:
                return ContextCompat.getColor(c,R.color.selenium);
            case 20:
                return ContextCompat.getColor(c,R.color.vit_C);
            case 21:
                return ContextCompat.getColor(c,R.color.thiamin);
            case 22:
                return ContextCompat.getColor(c,R.color.riboflavin);
            case 23:
                return ContextCompat.getColor(c,R.color.niacin);
            case 24:
                return ContextCompat.getColor(c,R.color.panto_Acid);
            case 25:
                return ContextCompat.getColor(c,R.color.vit_B6);
            case 26:
                return ContextCompat.getColor(c,R.color.folate_Tot);
            case 27:
                return ContextCompat.getColor(c,R.color.folic_Acid);
            case 28:
                return ContextCompat.getColor(c,R.color.food_Folate);
            case 29:
                return ContextCompat.getColor(c,R.color.folate_DFE);
            case 30:
                return ContextCompat.getColor(c,R.color.choline_Tot);
            case 31:
                return ContextCompat.getColor(c,R.color.vit_B12);
            case 32:
                return ContextCompat.getColor(c,R.color.vit_A_IU);
            case 33:
                return ContextCompat.getColor(c,R.color.vit_A_RAE);
            case 34:
                return ContextCompat.getColor(c,R.color.retinol);
            case 35:
                return ContextCompat.getColor(c,R.color.alpha_Carot);
            case 36:
                return ContextCompat.getColor(c,R.color.beta_Carot);
            case 37:
                return ContextCompat.getColor(c,R.color.beta_Crypt);
            case 38:
                return ContextCompat.getColor(c,R.color.lycopene);
            case 39:
                return ContextCompat.getColor(c,R.color.lutZea);
            case 40:
                return ContextCompat.getColor(c,R.color.vit_E);
            case 41:
                return ContextCompat.getColor(c,R.color.vit_D_µg);
            case 42:
                return ContextCompat.getColor(c,R.color.vit_D_IU);
            case 43:
                return ContextCompat.getColor(c,R.color.vit_K);
            case 44:
                return ContextCompat.getColor(c,R.color.FA_Sat);
            case 45:
                return ContextCompat.getColor(c,R.color.FA_Mono);
            case 46:
                return ContextCompat.getColor(c,R.color.FA_Poly);
            case 47:
                return ContextCompat.getColor(c,R.color.cholestrl);

            default:
                return ContextCompat.getColor(c,R.color.date);//for Date
        }

    }

}

/*
public class ProductDetailsAdapter extends CursorAdapter {


    public ProductDetailsAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.product_details_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView paramName = (TextView) view.findViewById(R.id.paramName);
        TextView paramVal = (TextView) view.findViewById(R.id.paramVal);

        String name = cursor.getString(cursor.getColumnIndexOrThrow("body"));
        int val = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));
        // Populate fields with extracted properties
        paramName.setText(name);
        paramVal.setText(String.valueOf(val));


    }
}
*/



