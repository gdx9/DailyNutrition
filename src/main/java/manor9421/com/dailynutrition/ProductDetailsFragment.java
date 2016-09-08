package manor9421.com.dailynutrition;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    int id;
    ArrayList<ProductDetails> details;
    Cursor cursor;
    DBHelper dbHelper;

    /*public ProductDetailsFragment(int id) {
        this.id = id;
    }*/

    public static ProductDetailsFragment newInstance(int n) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("id", n);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getArguments().getInt("id", 1);// 1 - дефолт

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_product_details, container, false);

        ListView prodDetailsListView = (ListView) v.findViewById(R.id.prodDetailsListView);

        new ProductDetailsAsyncTask(getActivity(), id, prodDetailsListView).execute();


        /*dbHelper = new DBHelper(getActivity());
        cursor = dbHelper.selectProdDetails(dbHelper.getReadableDatabase(),id);
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
        ProductDetailsAdapter adapter = new ProductDetailsAdapter(details,getActivity());

        prodDetailsListView.setAdapter(adapter);
*/


        return v;
    }

    private String getName(int n){
        switch(n){
            case 1:
                return "Name";
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
