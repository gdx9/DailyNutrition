package manor9421.com.dailynutrition;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by manor on 9/5/16.
 */
public class DailyNutritionAsyncTask extends AsyncTask<Void, Void, ProductAdapter> {

    Context c;
    ListView listView;
    ArrayList<Product> allProducts;

    DBHelper dbH;

    public DailyNutritionAsyncTask(Context c, ListView listView) {
        this.c = c;
        this.listView = listView;
    }

    @Override
    protected ProductAdapter doInBackground(Void... voids) {

        dbH = new DBHelper(c);
        Cursor detailsInfo = dbH.selectDetailsKcalProtFatCarb(dbH.getReadableDatabase());
        allProducts = new ArrayList<>();

        if(detailsInfo.moveToFirst()){
            do{
                //String date = detailsInfo.getLong(1)+"";
                String date = new SimpleDateFormat("dd.MM.yyyy 'at' h:mm a").format(new Date(detailsInfo.getLong(1)));
                int number = detailsInfo.getInt(0);
                float kcal = detailsInfo.getFloat(2);
                float protein = detailsInfo.getFloat(3);
                float lipid_tot = detailsInfo.getFloat(4);
                float carbohydrt = detailsInfo.getFloat(5);

                Product product = new Product();
                product.name = date;
                product.number = number;
                product.calories = kcal;
                product.proteins =protein;
                product.fats = lipid_tot;
                product.carbohydrates = carbohydrt;
                allProducts.add(product);
            }while (detailsInfo.moveToNext());
        }
        ProductAdapter adapter = new ProductAdapter(allProducts,c);


        return adapter;
    }

    @Override
    protected void onPostExecute(ProductAdapter adapter) {
        super.onPostExecute(adapter);
        listView.setAdapter(adapter);
    }
}
