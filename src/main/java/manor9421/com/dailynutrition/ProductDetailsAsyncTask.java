package manor9421.com.dailynutrition;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by manor on 9/5/16.
 */
public class ProductDetailsAsyncTask extends AsyncTask<Void,Void,ProductDetailsAdapter> {

    Context c;
    int id;
    ListView prodDetailsListView;

    DBHelper dbHelper;
    ArrayList<ProductDetails> details;

    public ProductDetailsAsyncTask(Context c, int id, ListView prodDetailsListView) {
        this.c = c;
        this.id = id;
        this.prodDetailsListView = prodDetailsListView;
    }

    @Override
    protected ProductDetailsAdapter doInBackground(Void... voids) {

        dbHelper = new DBHelper(c);
        Cursor cursor = dbHelper.selectProdDetails(dbHelper.getReadableDatabase(),id);
        details = new ArrayList<>(cursor.getColumnCount());
        if(cursor.moveToFirst()){
            // берем имя
            ProductDetails pdName = new ProductDetails();
            pdName.name = getName(1);
            pdName.val = cursor.getString(1);
            details.add(pdName);

            //берем остальные значения
            for(int i=2;i<cursor.getColumnCount();i++) {
                ProductDetails pd = new ProductDetails();
                pd.name=getName(i);
                pd.val = cursor.getFloat(i)+"";
                details.add(pd);
            }
        }
        ProductDetailsAdapter adapter = new ProductDetailsAdapter(details,c);

        return adapter;
    }


    @Override
    protected void onPostExecute(ProductDetailsAdapter adapter) {
        super.onPostExecute(adapter);

        prodDetailsListView.setAdapter(adapter);

    }


    private String getName(int n){
        switch(n){
            case 1:
                return c.getResources().getString(R.string.name);
            case 2:
                return c.getResources().getString(R.string.water);
            case 3:
                return c.getResources().getString(R.string.kcal);
            case 4:
                return c.getResources().getString(R.string.protein);
            case 5:
                return c.getResources().getString(R.string.lipid_tot);
            case 6:
                return c.getResources().getString(R.string.ash);
            case 7:
                return c.getResources().getString(R.string.carbohydrt);
            case 8:
                return c.getResources().getString(R.string.fiber_TD);
            case 9:
                return c.getResources().getString(R.string.sugar_Tot);
            case 10:
                return c.getResources().getString(R.string.calcium);
            case 11:
                return c.getResources().getString(R.string.iron);
            case 12:
                return c.getResources().getString(R.string.magnesium);
            case 13:
                return c.getResources().getString(R.string.phosphorus);
            case 14:
                return c.getResources().getString(R.string.potassium);
            case 15:
                return c.getResources().getString(R.string.sodium);
            case 16:
                return c.getResources().getString(R.string.zinc);
            case 17:
                return c.getResources().getString(R.string.copper);
            case 18:
                return c.getResources().getString(R.string.manganese);
            case 19:
                return c.getResources().getString(R.string.selenium);
            case 20:
                return c.getResources().getString(R.string.vit_C);
            case 21:
                return c.getResources().getString(R.string.thiamin);
            case 22:
                return c.getResources().getString(R.string.riboflavin);
            case 23:
                return c.getResources().getString(R.string.niacin);
            case 24:
                return c.getResources().getString(R.string.panto_Acid);
            case 25:
                return c.getResources().getString(R.string.vit_B6);
            case 26:
                return c.getResources().getString(R.string.folate_Tot);
            case 27:
                return c.getResources().getString(R.string.folic_Acid);
            case 28:
                return c.getResources().getString(R.string.food_Folate);
            case 29:
                return c.getResources().getString(R.string.folate_DFE);
            case 30:
                return c.getResources().getString(R.string.choline_Tot);
            case 31:
                return c.getResources().getString(R.string.vit_B12);
            case 32:
                return c.getResources().getString(R.string.vit_A_IU);
            case 33:
                return c.getResources().getString(R.string.vit_A_RAE);
            case 34:
                return c.getResources().getString(R.string.retinol);
            case 35:
                return c.getResources().getString(R.string.alpha_Carot);
            case 36:
                return c.getResources().getString(R.string.beta_Carot);
            case 37:
                return c.getResources().getString(R.string.beta_Crypt);
            case 38:
                return c.getResources().getString(R.string.lycopene);
            case 39:
                return c.getResources().getString(R.string.lutZea);
            case 40:
                return c.getResources().getString(R.string.vit_E);
            case 41:
                return c.getResources().getString(R.string.vit_D_µg);
            case 42:
                return c.getResources().getString(R.string.vit_D_IU);
            case 43:
                return c.getResources().getString(R.string.vit_K);
            case 44:
                return c.getResources().getString(R.string.FA_Sat);
            case 45:
                return c.getResources().getString(R.string.FA_Mono);
            case 46:
                return c.getResources().getString(R.string.FA_Poly);
            case 47:
                return c.getResources().getString(R.string.cholestrl);
            default:
                return c.getResources().getString(R.string.date);
        }

    }

}
