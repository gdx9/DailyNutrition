package manor9421.com.dailynutrition;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


public class DailyNutritionFragment extends Fragment {

    private ProdDetailsListCallback mCallbacks;

    private ArrayList<Product> allProducts;
    private ProductAdapter adapter;
    private DBHelper dbH;
    private ListView listView;


    public interface ProdDetailsListCallback{
        void onDetailsListItemClick(int item);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (ProdDetailsListCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_daily_nutrition, container, false);

        listView = (ListView) v.findViewById(R.id.dailyProductsListView);

        new DailyNutritionAsyncTask(getActivity(),listView).execute();

        /*dbH = new DBHelper(getActivity());
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

        adapter = new ProductAdapter(allProducts,getActivity());
        listView.setAdapter(adapter);
*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int n = Integer.parseInt(view.getTag().toString());
                //Toast.makeText(getActivity(),""+i,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),n+"",Toast.LENGTH_SHORT).show();
                mCallbacks.onDetailsListItemClick(n);
            }
        });




        return v;
    }


}
