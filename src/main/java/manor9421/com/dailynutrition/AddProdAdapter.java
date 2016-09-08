package manor9421.com.dailynutrition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by manor on 8/31/16.
 */
public class AddProdAdapter extends BaseAdapter {

    ArrayList<String> etHints = new ArrayList<String>(){{
        add("Name");
        add("Water");
        add("Food energy");
        add("Protein");
        add("Total lipid(fat)");
        add("Ash");
        add("Carbohydrate");
        add("Total dietary fiber");
        add("Total sugars");
        add("Calcium");
        add("Iron");
        add("Magnesium");
        add("Phosphorus");
        add("Potassium");
        add("Sodium");
        add("Zinc");
        add("Copper");
        add("Manganese");
        add("Selenium");
        add("Vitamin C");
        add("Thiamin");
        add("Riboflavin");
        add("Niacin");
        add("Pantothenic acid");
        add("Vitamin B6");
        add("Folate, total");
        add("Folic acid");
        add("Food");
        add("Folate");
        add("Choline");
        add("Vitamin B12");
        add("Vitamin A");
        add("Vitamin A");
        add("Retinol");
        add("Alpha-carotene");
        add("Beta-carotene");
        add("Beta-cryptoxanthin");
        add("Lycopene");
        add("Lutein+zeazanthin");
        add("Vitamin E");
        add("Vitamin D");
        add("Vitamin D");
        add("Vitamin K (phylloquinone)");
        add("Saturated fatty acid");
        add("Monounsaturated fatty acids");
        add("Polyunsaturated fatty acids");
        add("Cholesterol");
    }};
    Context c;

    public AddProdAdapter(Context c) {
        this.c = c;
    }

    @Override
    public int getCount() {
        return etHints.size();
    }

    @Override
    public String getItem(int i) {
        return etHints.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View etView = view;
        if(etView == null){//если первый раз вызвали
            // получаем  объект  LayoutInflater  из  объекта Context
            etView = LayoutInflater.from(c).inflate(R.layout.add_prod_edittext,null);
        }

        EditText et = (EditText) etView.findViewById(R.id.addProdEditText);
        et.setHint(getItem(i));
        et.setTag(getItem(i));

        return etView;

    }

}
