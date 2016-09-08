package manor9421.com.dailynutrition;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {

    private ProdListCallback mCallbacks;

    private ArrayList<Product> allProducts;
    private ProductAdapter adapter;
    private DBHelper dbH;
    private ListView listView;

    private Cursor startSearch(String text){
        return dbH.findProducts(dbH.getReadableDatabase(),text);
    }

    public interface ProdListCallback{
        void onListItemClick(int item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {

            mCallbacks = (ProdListCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_product_list, container, false);

        final EditText searchArea = (EditText) v.findViewById(R.id.searchArea);
        listView = (ListView) v.findViewById(R.id.productsListView);

        allProducts = new ArrayList<>();

        //зfполняем ListView
        new ProductListAsyncTask(getActivity(),listView,"",allProducts).execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int n = Integer.parseInt(view.getTag().toString());
                if(n == 0){
                    Toast.makeText(getActivity(),"No products",Toast.LENGTH_SHORT).show();
                }else{
                    mCallbacks.onListItemClick(n);
                }
                //Toast.makeText(getActivity(),n+"",Toast.LENGTH_SHORT).show();
            }
        });


        Button startSearchButton = (Button) v.findViewById(R.id.startSearchButton);
        startSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchArea.getText()+"";
                if(!searchText.isEmpty()){
                    if(!searchText.trim().isEmpty()){
                        new ProductListAsyncTask(getActivity(),listView,searchText,allProducts).execute();
                    }
                }

            }
        });

        return v;
    }

}
