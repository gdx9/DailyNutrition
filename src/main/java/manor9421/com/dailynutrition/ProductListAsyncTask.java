package manor9421.com.dailynutrition;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by manor on 9/5/16.
 */
public class ProductListAsyncTask extends AsyncTask<Void,Void,ProductAdapter> {

    Context c;
    ListView listView;
    String text;

    DBHelper dbH;
    ArrayList<Product> allProducts;


    public ProductListAsyncTask(Context c, ListView listView,String text,ArrayList<Product> allProducts) {
        this.c = c;
        this.listView = listView;
        this.text = text;
        this.allProducts = allProducts;
    }

    @Override
    protected ProductAdapter doInBackground(Void... voids) {
        ProductAdapter adapter;
        dbH = new DBHelper(c);
        allProducts.clear();
        Cursor prodInfo;
        if(text.isEmpty()) {
            prodInfo = dbH.selectKcalProtFatCarb(dbH.getReadableDatabase());
        }else{
            prodInfo = dbH.findProducts(dbH.getReadableDatabase(),text);
        }

        //allProducts.clear();

        if (prodInfo.moveToFirst()) {
            do {
                String name = prodInfo.getString(1);
                int number = prodInfo.getInt(0);
                float kcal = prodInfo.getFloat(2);
                float protein = prodInfo.getFloat(3);
                float lipid_tot = prodInfo.getFloat(4);
                float carbohydrt = prodInfo.getFloat(5);

                Product product = new Product();
                product.name = name;
                product.number = number;
                product.calories = kcal;
                product.proteins = protein;
                product.fats = lipid_tot;
                product.carbohydrates = carbohydrt;
                allProducts.add(product);
            } while (prodInfo.moveToNext());
        } else {
            Product product = new Product();
            product.name = "No products";
            product.number = 0;
            product.calories = 0;
            product.proteins = 0;
            product.fats = 0;
            product.carbohydrates = 0;

            allProducts.add(product);
            //Toast.makeText(c, "No products", Toast.LENGTH_SHORT).show();
        }
        adapter = new ProductAdapter(allProducts, c);



        return adapter;
    }

    @Override
    protected void onPostExecute(ProductAdapter adapter) {
        super.onPostExecute(adapter);
        listView.setAdapter(adapter);
    }

}
