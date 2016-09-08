package manor9421.com.dailynutrition;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class AddProductFragment extends Fragment {

    View view;

    // true - если НЕ пуста
    public boolean checkEmptyStringData(String data){
        if(!data.isEmpty()){
            if(!data.trim().isEmpty()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        ListView lv = (ListView) view.findViewById(R.id.addProdListView);
        AddProdAdapter apa = new AddProdAdapter(getActivity());
        lv.setAdapter(apa);



        return view;
    }*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_add_product, container, false);

        Button addbutton = (Button) view.findViewById(R.id.addConfirm);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String addName = ((EditText) view.findViewById(R.id.addName)).getText().toString();
                String water = ((EditText) view.findViewById(R.id.water)).getText().toString();
                String kcal = ((EditText) view.findViewById(R.id.kcal)).getText().toString();
                String protein = ((EditText) view.findViewById(R.id.protein)).getText().toString();
                String lipid_tot = ((EditText) view.findViewById(R.id.lipid_tot)).getText().toString();
                String ash = ((EditText) view.findViewById(R.id.ash)).getText().toString();
                String carbohydrt = ((EditText) view.findViewById(R.id.carbohydrt)).getText().toString();
                String fiber_TD = ((EditText) view.findViewById(R.id.fiber_TD)).getText().toString();
                String sugar_Tot = ((EditText) view.findViewById(R.id.sugar_Tot)).getText().toString();
                String calcium = ((EditText) view.findViewById(R.id.calcium)).getText().toString();
                String iron = ((EditText) view.findViewById(R.id.iron)).getText().toString();
                String magnesium = ((EditText) view.findViewById(R.id.magnesium)).getText().toString();
                String phosphorus = ((EditText) view.findViewById(R.id.phosphorus)).getText().toString();
                String potassium = ((EditText) view.findViewById(R.id.potassium)).getText().toString();
                String sodium = ((EditText) view.findViewById(R.id.sodium)).getText().toString();
                String zinc = ((EditText) view.findViewById(R.id.zinc)).getText().toString();
                String copper = ((EditText) view.findViewById(R.id.copper)).getText().toString();
                String manganese = ((EditText) view.findViewById(R.id.manganese)).getText().toString();
                String selenium = ((EditText) view.findViewById(R.id.selenium)).getText().toString();
                String vit_C = ((EditText) view.findViewById(R.id.vit_C)).getText().toString();
                String thiamin = ((EditText) view.findViewById(R.id.thiamin)).getText().toString();
                String riboflavin = ((EditText) view.findViewById(R.id.riboflavin)).getText().toString();
                String niacin = ((EditText) view.findViewById(R.id.niacin)).getText().toString();
                String panto_Acid = ((EditText) view.findViewById(R.id.panto_Acid)).getText().toString();
                String vit_B6 = ((EditText) view.findViewById(R.id.vit_B6)).getText().toString();
                String folate_Tot = ((EditText) view.findViewById(R.id.folate_Tot)).getText().toString();
                String folic_Acid = ((EditText) view.findViewById(R.id.folic_Acid)).getText().toString();
                String food_Folate = ((EditText) view.findViewById(R.id.food_Folate)).getText().toString();
                String folate_DFE = ((EditText) view.findViewById(R.id.folate_DFE)).getText().toString();
                String choline_Tot = ((EditText) view.findViewById(R.id.choline_Tot)).getText().toString();
                String vit_B12 = ((EditText) view.findViewById(R.id.vit_B12)).getText().toString();
                String vit_A_IU = ((EditText) view.findViewById(R.id.vit_A_IU)).getText().toString();
                String vit_A_RAE = ((EditText) view.findViewById(R.id.vit_A_RAE)).getText().toString();
                String retinol = ((EditText) view.findViewById(R.id.retinol)).getText().toString();
                String alpha_Carot = ((EditText) view.findViewById(R.id.alpha_Carot)).getText().toString();
                String beta_Carot = ((EditText) view.findViewById(R.id.beta_Carot)).getText().toString();
                String beta_Crypt = ((EditText) view.findViewById(R.id.beta_Crypt)).getText().toString();
                String lycopene = ((EditText) view.findViewById(R.id.lycopene)).getText().toString();
                String lutZea = ((EditText) view.findViewById(R.id.lutZea)).getText().toString();
                String vit_E = ((EditText) view.findViewById(R.id.vit_E)).getText().toString();
                String vit_D_µg = ((EditText) view.findViewById(R.id.vit_D_µg)).getText().toString();
                String vit_D_IU = ((EditText) view.findViewById(R.id.vit_D_IU)).getText().toString();
                String vit_K = ((EditText) view.findViewById(R.id.vit_K)).getText().toString();
                String FA_Sat = ((EditText) view.findViewById(R.id.FA_Sat)).getText().toString();
                String FA_Mono = ((EditText) view.findViewById(R.id.FA_Mono)).getText().toString();
                String FA_Poly = ((EditText) view.findViewById(R.id.FA_Poly)).getText().toString();
                String cholestrl = ((EditText) view.findViewById(R.id.cholestrl)).getText().toString();

                if(checkEmptyStringData(addName)){
                    String addNameData = addName.trim();

                    float waterData = getZeroOrData(water);
                    float kcalData = getZeroOrData(kcal);
                    float proteinData = getZeroOrData(protein);
                    float lipid_totData = getZeroOrData(lipid_tot);
                    float ashData = getZeroOrData(ash);
                    float carbohydrtData = getZeroOrData(carbohydrt);
                    float fiber_TDData = getZeroOrData(fiber_TD);
                    float sugar_TotData = getZeroOrData(sugar_Tot);
                    float calciumData = getZeroOrData(calcium);
                    float ironData = getZeroOrData(iron);
                    float magnesiumData = getZeroOrData(magnesium);
                    float phosphorusData = getZeroOrData(phosphorus);
                    float potassiumData = getZeroOrData(potassium);
                    float sodiumData = getZeroOrData(sodium);
                    float zincData = getZeroOrData(zinc);
                    float copperData = getZeroOrData(copper);
                    float manganeseData = getZeroOrData(manganese);
                    float seleniumData = getZeroOrData(selenium);
                    float vit_CData = getZeroOrData(vit_C);
                    float thiaminData = getZeroOrData(thiamin);
                    float riboflavinData = getZeroOrData(riboflavin);
                    float niacinData = getZeroOrData(niacin);
                    float panto_AcidData = getZeroOrData(panto_Acid);
                    float vit_B6Data = getZeroOrData(vit_B6);
                    float folate_TotData = getZeroOrData(folate_Tot);
                    float folic_AcidData = getZeroOrData(folic_Acid);
                    float food_FolateData = getZeroOrData(food_Folate);
                    float folate_DFEData = getZeroOrData(folate_DFE);
                    float choline_TotData = getZeroOrData(choline_Tot);
                    float vit_B12Data = getZeroOrData(vit_B12);
                    float vit_A_IUData = getZeroOrData(vit_A_IU);
                    float vit_A_RAEData = getZeroOrData(vit_A_RAE);
                    float retinolData = getZeroOrData(retinol);
                    float alpha_CarotData = getZeroOrData(alpha_Carot);
                    float beta_CarotData = getZeroOrData(beta_Carot);
                    float beta_CryptData = getZeroOrData(beta_Crypt);
                    float lycopeneData = getZeroOrData(lycopene);
                    float lutZeaData = getZeroOrData(lutZea);
                    float vit_EData = getZeroOrData(vit_E);
                    float vit_D_µgData = getZeroOrData(vit_D_µg);
                    float vit_D_IUData = getZeroOrData(vit_D_IU);
                    float vit_KData = getZeroOrData(vit_K);
                    float FA_SatData = getZeroOrData(FA_Sat);
                    float FA_MonoData = getZeroOrData(FA_Mono);
                    float FA_PolyData = getZeroOrData(FA_Poly);
                    float cholestrlData = getZeroOrData(cholestrl);

                    try{
                        DBHelper dbH = new DBHelper(getActivity());
                        dbH.insertFood(dbH.getWritableDatabase(), addNameData, waterData,kcalData,proteinData,lipid_totData,ashData,carbohydrtData,fiber_TDData,sugar_TotData,
                                calciumData,ironData,magnesiumData,phosphorusData,potassiumData,sodiumData,zincData,copperData,manganeseData,seleniumData,
                                vit_CData,thiaminData,riboflavinData,niacinData,panto_AcidData,vit_B6Data,folate_TotData,folic_AcidData,food_FolateData,
                                folate_DFEData,choline_TotData,vit_B12Data,vit_A_IUData,vit_A_RAEData,retinolData,alpha_CarotData,beta_CarotData,beta_CryptData,
                                lycopeneData,lutZeaData,vit_EData,vit_D_µgData,vit_D_IUData,vit_KData,FA_SatData,FA_MonoData,FA_PolyData,cholestrlData);

                        dbH.close();




                        Toast.makeText(getActivity(),"Added successfully",Toast.LENGTH_SHORT) .show();
                    }catch (SQLiteException e){
                        Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT).show();
                    }
                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.addProdLinearLayout);
                    cleanAllEditTextFields(linearLayout);
                    /*//Reload fragment
                    Fragment frg = null;
                    frg = new AddProductFragment();//getFragmentManager().findFragmentByTag("visible_fragment");
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    //ft.detach(frg);
                    //ft.attach(frg);
                    //ft.replace(R.id.addProductFragment,frg);
                    ft.commit();*/

                }else {
                    Toast.makeText(getActivity(), "Write Name", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }

    public void cleanAllEditTextFields(LinearLayout ll){
        int childCount = ll.getChildCount();
        for(int i=0;i<childCount;i++){
            ((EditText) ll.getChildAt(i)).setText("");
        }

    }

    public float getZeroOrData(String dataString){
            if(!dataString.isEmpty()){
                if(!dataString.trim().isEmpty()){
                    return Float.parseFloat(dataString.trim());
                }else{
                    return 0f;
                }
            }else{
                return 0f;
            }
    }
}
