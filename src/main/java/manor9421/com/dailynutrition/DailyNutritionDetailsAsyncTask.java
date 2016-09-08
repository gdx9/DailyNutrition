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
public class DailyNutritionDetailsAsyncTask extends AsyncTask<Void, Void,ProductDetailsAdapter> {

    Context c;
    int id;
    ListView listView;
    ArrayList<ProductDetails> details;

    DBHelper dbHelper;

    public DailyNutritionDetailsAsyncTask(Context c, int id, ListView listView, ArrayList<ProductDetails> details) {
        this.c = c;
        this.id = id;
        this.listView = listView;
        this.details = details;
    }

    @Override
    protected ProductDetailsAdapter doInBackground(Void... voids) {

        dbHelper = new DBHelper(c);
        Cursor cursor = dbHelper.selectDailyDetails(dbHelper.getReadableDatabase(),id);
        details.clear();
        details = new ArrayList<>(cursor.getColumnCount());

        if(cursor.moveToFirst()){
            // берем дату
            ProductDetails pdDate = new ProductDetails();
            pdDate.name = getName(0);
            pdDate.val = new SimpleDateFormat("dd.MM.yyyy 'at' h:mm a").format(new Date(cursor.getLong(0)));
            details.add(pdDate);
            // берем продукты
            ProductDetails pdName = new ProductDetails();
            pdName.name = getName(1);
            pdName.val = getProdNames(cursor.getString(1));
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
        listView.setAdapter(adapter);
    }

    private String getProdNames(String a){// строка вида продукт1|продукт2|продукт3...
        if(a.contains("|")){
            //если строка имеет несколько продуктов
            String[] separated = a.split("\\|");
            Cursor c = dbHelper.selectNames(dbHelper.getReadableDatabase(),separated);

            //


            // В СТРИНГБИЛДЕР

            //
            StringBuilder s = new StringBuilder();
            int i = 1;
            if(c.moveToFirst()){
                do{
                    s.append(i+")" + c.getString(0)+"\n");
                    i++;
                }while (c.moveToNext());
            }
            //String p = separated[0]+" "+separated[1];
            //Toast.makeText(getActivity(), p, Toast.LENGTH_SHORT).show();
            return s+"";
        }else{
            //если в строке 1 продукт
            String[] separated = new String[]{a};
            Cursor c = dbHelper.selectNames(dbHelper.getReadableDatabase(),separated);
            String s = "";
            if(c.moveToFirst()){
                do{
                    s += c.getString(0);
                }while (c.moveToNext());
            }
            return s;
        }
    }

    private String getName(int n) {
        switch (n) {
            case 0:
                return "Date";
            case 1:
                return "Products";
            case 2:
                return "Water (g/100 g)";
            case 3:
                return "Food energy (kcal/100 g)";
            case 4:
                return "Protein (g/100 g)";
            case 5:
                return "Total lipid(fat) (g/100 g)";
            case 6:
                return "Ash (g/100 g)";
            case 7:
                return "Carbohydrate(g/100 g)";
            case 8:
                return "Total dietary fiber (g/100 g)";
            case 9:
                return "Total sugars (g/100 g)";
            case 10:
                return "Calcium (mg/100 g)";
            case 11:
                return "Iron (mg/100 g)";
            case 12:
                return "Magnesium (mg/100 g)";
            case 13:
                return "Phosphorus (mg/100 g)";
            case 14:
                return "Potassium (mg/100 g)";
            case 15:
                return "Sodium (mg/100 g)";
            case 16:
                return "Zinc (mg/100 g)";
            case 17:
                return "Copper (mg/100 g)";
            case 18:
                return "Manganese (mg/100 g)";
            case 19:
                return "Selenium (μg/100 g)";
            case 20:
                return "Vitamin C (mg/100 g)";
            case 21:
                return "Thiamin (mg/100 g)";
            case 22:
                return "Riboflavin (mg/100 g)";
            case 23:
                return "Niacin (mg/100 g)";
            case 24:
                return "Pantothenic acid (mg/100 g)";
            case 25:
                return "Vitamin B6 (mg/100 g)";
            case 26:
                return "Folate, total (μg/100 g)";
            case 27:
                return "Folic acid (μg/100 g)";
            case 28:
                return "Food folate (μg/100 g)";
            case 29:
                return "Folate (μg dietary folate equivalents/100 g)";
            case 30:
                return "Choline, total (mg/100 g)";
            case 31:
                return "Vitamin B12 (μg/100 g)";
            case 32:
                return "Vitamin A (IU/100 g)";
            case 33:
                return "Vitamin A (μg retinol activity equivalents/100g)";
            case 34:
                return "Retinol (μg/100 g)";
            case 35:
                return "Alpha-carotene (μg/100 g)";
            case 36:
                return "Beta-carotene (μg/100 g)";
            case 37:
                return "Beta-cryptoxanthin (μg/100 g)";
            case 38:
                return "Lycopene (μg/100 g)";
            case 39:
                return "Lutein+zeazanthin (μg/100 g)";
            case 40:
                return "Vitamin E (alpha-tocopherol) (mg/100 g)";
            case 41:
                return "Vitamin D (μg/100 g)";
            case 42:
                return "Vitamin D (IU/100 g)";
            case 43:
                return "Vitamin K (phylloquinone) (μg/100 g)";
            case 44:
                return "Saturated fatty acid (g/100 g)";
            case 45:
                return "Monounsaturated fatty acids (g/100 g)";
            case 46:
                return "Polyunsaturated fatty acids (g/100 g)";
            case 47:
                return "Cholesterol (mg/100 g)";
            default:
                return "Zero";
        }
    }


}
