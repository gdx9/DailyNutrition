package manor9421.com.dailynutrition;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class NewIngestionFragment extends Fragment {

    GridLayout grid;
    SQLiteDatabase thisDB;
    DBHelper dbH;
    Cursor prodInfoCursor;
    String[] namesOfAll;

    MediaPlayer btnSound;

    int height = 80;


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View thisFragView = inflater.inflate(R.layout.fragment_new_ingestion, container,false);

        grid = (GridLayout) thisFragView.findViewById(R.id.newILayout);

        btnSound = MediaPlayer.create(getActivity(),R.raw.new_ing_sound);

        dbH = new DBHelper(getActivity());
        thisDB = dbH.getReadableDatabase();
        prodInfoCursor = dbH.selectAllProdNames(thisDB);
        //выборка айди и имен
        namesOfAll = getAllNames();
        prodInfoCursor.close();
        //Create first Line
        createNewLine();

        Button plusLineButton = (Button) thisFragView.findViewById(R.id.plusLineButton);
        plusLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewLine();
            }
        });

        Button checkIngestionButton = (Button) thisFragView.findViewById(R.id.toConfirmIngestion);
        checkIngestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllInIngestion();
            }
        });

        dbH.close();


        return thisFragView;
    }

    public String[] getAllNames(){
        if(prodInfoCursor.moveToFirst()){
            List<String> hints = new ArrayList<>();
            do{
                hints.add(prodInfoCursor.getString(1));
            }while(prodInfoCursor.moveToNext());
            String[] hint = hints.toArray(new String[0]);
            return hint;
        }
        return new String[0];
    }

    public void createNewLine(){
        Random rn = new Random();
        final int newTag = rn.nextInt();

        AutoCompleteTextView au = new AutoCompleteTextView(getActivity());
        GridLayout.LayoutParams auPar = new GridLayout.LayoutParams();
        au.setWidth(100);//чтобы не растягивалось до бесконечности
        au.setDropDownBackgroundResource(R.color.itemBackground);
        //au.setText("abc");
        auPar.columnSpec = GridLayout.spec(0,2);
        auPar.setGravity(Gravity.FILL_HORIZONTAL);
        auPar.setMargins(4,2,4,2);
        auPar.height = height;
        au.setBackgroundResource(R.drawable.edittext_style);
        au.setLayoutParams(auPar);
        au.setTextColor(ContextCompat.getColor(getActivity(),R.color.mainTextColor));
        au.setHint("Product name");
        au.setTag(newTag+"au");
        au.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,namesOfAll));

        grid.addView(au);

        EditText et = new EditText(getActivity());
        GridLayout.LayoutParams etPar = new GridLayout.LayoutParams();
        etPar.setMargins(4,2,4,2);
        etPar.height = height;
        etPar.width = 100;
        et.setLayoutParams(etPar);
        //et.setWidth(80);
        et.setGravity(Gravity.CENTER);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        et.setTextColor(ContextCompat.getColor(getActivity(),R.color.mainTextColor));
        et.setHint("g");
        et.setTag(newTag+"et");
        et.setBackgroundResource(R.drawable.edittext_style);
        grid.addView(et);

        Button delB = new Button(getActivity());
        GridLayout.LayoutParams delBPar = new GridLayout.LayoutParams();
        //delBPar.setMargins(4,4,4,4);
        delB.setBackgroundResource(R.drawable.round_button);
        delB.setGravity(Gravity.CENTER);
        delB.setText("✘");
        //et.setWidth(60);
        delB.setTextColor(Color.RED);
        delB.setMinHeight(0);
        delB.setMinWidth(0);
        delBPar.height = height;
        delBPar.width = height;
        delBPar.setMargins(4,2,4,2);
        delB.setTextSize(15);
        delB.setPadding(0,0,0,0);
        delB.setLayoutParams(delBPar);

        delB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid.removeView(grid.findViewWithTag(newTag+"au"));
                grid.removeView(grid.findViewWithTag(newTag+"et"));
                grid.removeView(grid.findViewWithTag(newTag));
            }
        });
        delB.setTag(newTag);
        grid.addView(delB);

        //при заполнении поля меняется фокус на поле граммов
        au.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                EditText g = (EditText) grid.findViewWithTag(newTag+"et");
                g.setHeight(view.getHeight());
                g.requestFocus();
            }
        });

    }

    public boolean checkDataExist(String string){
        if(!string.isEmpty()){//есть что-то
            if(!string.trim().isEmpty()){
                return true;
            }else{
                return false;
            }
        }else{//все пусто
            return false;
        }

    }


    public void checkAllInIngestion(){
        int count = grid.getChildCount();//узнаем количество элементов
        if(count > 0) { //если есть дети
            ArrayList<String> prodNames = new ArrayList<>();//для названий
            ArrayList<Integer> prodWeights = new ArrayList<>();//для веса
            for (int i = 0; i < count / 3; i++) {//ili i+=3,[i/3]//количество строк
                //обновление текущих данных
                String av = ((AutoCompleteTextView) grid.getChildAt(i * 3)).getText().toString();
                String et = ((EditText) grid.getChildAt(i * 3 + 1)).getText().toString();

                //Проверяем на пустоту
                if (checkDataExist(av) && checkDataExist(et)) {
                    //есть значения
                    String prodName = av.trim();//значение названия
                    int prodWeight = Integer.parseInt(et.trim());//значение веса
                    if ( prodWeight >= 1 ) {//если нет нулей в полях
                        if(Arrays.asList(namesOfAll).contains(prodName)){//если есть данное значение в массиве из базы
                            //делаем запись в массив
                            prodNames.add(prodName);
                            prodWeights.add(prodWeight);
                        }else{
                            //Toast.makeText(getActivity(), "Есть нули в полях", Toast.LENGTH_SHORT).show();
                        }
                    } else {//All is bad
                        //Toast.makeText(getActivity(), "Massivi pustie", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //все пусто
                    //Toast.makeText(getActivity(),"Data is clear in 1 field", Toast.LENGTH_SHORT).show();
                }

            }//end of for.
            // Проверили все поля и одинаковая ли длина
            if (prodNames.size() >= 1 && prodWeights.size() >= 1 && prodNames.size() == prodWeights.size()
                    && prodNames.size() == count/3 && prodWeights.size() == count/3) {
                String[] elNames = prodNames.toArray(new String[prodNames.size()]);
                Integer[] gWei = prodWeights.toArray(new Integer[prodWeights.size()]);


                //ищем данные
                //DBHelper db = new DBHelper(getActivity());
                Cursor allElData = dbH.selectProd(dbH.getReadableDatabase(),elNames);
                if(allElData.moveToFirst()){//если курсор не пуст
                    StringBuilder totalNames = new StringBuilder();
                    float totalwater = 0.f;
                    float totalkcal = 0.f;
                    float totalprotein = 0.f;
                    float totallipid_tot = 0.f;
                    float totalash = 0.f;
                    float totalcarbohydrt = 0.f;
                    float totalfiber_TD = 0.f;
                    float totalsugar_Tot = 0.f;
                    float totalcalcium = 0.f;
                    float totaliron = 0.f;
                    float totalmagnesium = 0.f;
                    float totalphosphorus = 0.f;
                    float totalpotassium = 0.f;
                    float totalsodium = 0.f;
                    float totalzinc = 0.f;
                    float totalcopper = 0.f;
                    float totalmanganese = 0.f;
                    float totalselenium = 0.f;
                    float totalvit_C = 0.f;
                    float totalthiamin = 0.f;
                    float totalriboflavin = 0.f;
                    float totalniacin = 0.f;
                    float totalpanto_Acid = 0.f;
                    float totalvit_B6 = 0.f;
                    float totalfolate_Tot = 0.f;
                    float totalfolic_Acid = 0.f;
                    float totalfood_Folate = 0.f;
                    float totalfolate_DFE = 0.f;
                    float totalcholine_Tot = 0.f;
                    float totalvit_B12 = 0.f;
                    float totalvit_A_IU = 0.f;
                    float totalvit_A_RAE = 0.f;
                    float totalretinol = 0.f;
                    float totalalpha_Carot = 0.f;
                    float totalbeta_Carot = 0.f;
                    float totalbeta_Crypt = 0.f;
                    float totallycopene = 0.f;
                    float totallutZea = 0.f;
                    float totalvit_E = 0.f;
                    float totalvit_D_µg = 0.f;
                    float totalvit_D_IU = 0.f;
                    float totalvit_K = 0.f;
                    float totalFA_Sat = 0.f;
                    float totalFA_Mono = 0.f;
                    float totalFA_Poly = 0.f;
                    float totalcholestrl = 0.f;

                    for (int i=0; !allElData.isAfterLast(); allElData.moveToNext()) {
                        totalNames.append(allElData.getInt(0)+"|");//names
                        //allElData.getString(1);// NAME
                        totalwater += allElData.getFloat(2)*gWei[i]/100;
                        totalkcal += allElData.getFloat(3)*gWei[i]/100;
                        totalprotein += allElData.getFloat(4)*gWei[i]/100;
                        totallipid_tot += allElData.getFloat(5)*gWei[i]/100;
                        totalash += allElData.getFloat(6)*gWei[i]/100;
                        totalcarbohydrt += allElData.getFloat(7)*gWei[i]/100;
                        totalfiber_TD += allElData.getFloat(8)*gWei[i]/100;
                        totalsugar_Tot += allElData.getFloat(9)*gWei[i]/100;
                        totalcalcium += allElData.getFloat(10)*gWei[i]/100;
                        totaliron += allElData.getFloat(11)*gWei[i]/100;
                        totalmagnesium += allElData.getFloat(12)*gWei[i]/100;
                        totalphosphorus += allElData.getFloat(13)*gWei[i]/100;
                        totalpotassium += allElData.getFloat(14)*gWei[i]/100;
                        totalsodium += allElData.getFloat(15)*gWei[i]/100;
                        totalzinc += allElData.getFloat(16)*gWei[i]/100;
                        totalcopper += allElData.getFloat(17)*gWei[i]/100;
                        totalmanganese += allElData.getFloat(18)*gWei[i]/100;
                        totalselenium += allElData.getFloat(19)*gWei[i]/100;
                        totalvit_C += allElData.getFloat(20)*gWei[i]/100;
                        totalthiamin += allElData.getFloat(21)*gWei[i]/100;
                        totalriboflavin += allElData.getFloat(22)*gWei[i]/100;
                        totalniacin += allElData.getFloat(23)*gWei[i]/100;
                        totalpanto_Acid += allElData.getFloat(24)*gWei[i]/100;
                        totalvit_B6 += allElData.getFloat(25)*gWei[i]/100;
                        totalfolate_Tot += allElData.getFloat(26)*gWei[i]/100;
                        totalfolic_Acid += allElData.getFloat(27)*gWei[i]/100;
                        totalfood_Folate += allElData.getFloat(28)*gWei[i]/100;
                        totalfolate_DFE += allElData.getFloat(29)*gWei[i]/100;
                        totalcholine_Tot += allElData.getFloat(30)*gWei[i]/100;
                        totalvit_B12 += allElData.getFloat(31)*gWei[i]/100;
                        totalvit_A_IU += allElData.getFloat(32)*gWei[i]/100;
                        totalvit_A_RAE += allElData.getFloat(33)*gWei[i]/100;
                        totalretinol += allElData.getFloat(34)*gWei[i]/100;
                        totalalpha_Carot += allElData.getFloat(35)*gWei[i]/100;
                        totalbeta_Carot += allElData.getFloat(36)*gWei[i]/100;
                        totalbeta_Crypt += allElData.getFloat(37)*gWei[i]/100;
                        totallycopene += allElData.getFloat(38)*gWei[i]/100;
                        totallutZea += allElData.getFloat(39)*gWei[i]/100;
                        totalvit_E += allElData.getFloat(40)*gWei[i]/100;
                        totalvit_D_µg += allElData.getFloat(41)*gWei[i]/100;
                        totalvit_D_IU += allElData.getFloat(42)*gWei[i]/100;
                        totalvit_K += allElData.getFloat(43)*gWei[i]/100;
                        totalFA_Sat += allElData.getFloat(44)*gWei[i]/100;
                        totalFA_Mono += allElData.getFloat(45)*gWei[i]/100;
                        totalFA_Poly += allElData.getFloat(46)*gWei[i]/100;
                        totalcholestrl += allElData.getFloat(47)*gWei[i]/100;
                    }
                    allElData.close();
                    totalNames.deleteCharAt(totalNames.length()-1);
                    //для округления
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                    // float total1Proteins = df.format(totalProteins);
                    float newtotalwater = Float.parseFloat(df.format(totalwater));
                    float newtotalkcal = Float.parseFloat(df.format(totalkcal));
                    float newtotalprotein = Float.parseFloat(df.format(totalprotein));
                    float newtotallipid_tot = Float.parseFloat(df.format(totallipid_tot));
                    float newtotalash = Float.parseFloat(df.format(totalash));
                    float newtotalcarbohydrt = Float.parseFloat(df.format(totalcarbohydrt));
                    float newtotalfiber_TD = Float.parseFloat(df.format(totalfiber_TD));
                    float newtotalsugar_Tot = Float.parseFloat(df.format(totalsugar_Tot));
                    float newtotalcalcium = Float.parseFloat(df.format(totalcalcium));
                    float newtotaliron = Float.parseFloat(df.format(totaliron));
                    float newtotalmagnesium = Float.parseFloat(df.format(totalmagnesium));
                    float newtotalphosphorus = Float.parseFloat(df.format(totalphosphorus));
                    float newtotalpotassium = Float.parseFloat(df.format(totalpotassium));
                    float newtotalsodium = Float.parseFloat(df.format(totalsodium));
                    float newtotalzinc = Float.parseFloat(df.format(totalzinc));
                    float newtotalcopper = Float.parseFloat(df.format(totalcopper));
                    float newtotalmanganese = Float.parseFloat(df.format(totalmanganese));
                    float newtotalselenium = Float.parseFloat(df.format(totalselenium));
                    float newtotalvit_C = Float.parseFloat(df.format(totalvit_C));
                    float newtotalthiamin = Float.parseFloat(df.format(totalthiamin));
                    float newtotalriboflavin = Float.parseFloat(df.format(totalriboflavin));
                    float newtotalniacin = Float.parseFloat(df.format(totalniacin));
                    float newtotalpanto_Acid = Float.parseFloat(df.format(totalpanto_Acid));
                    float newtotalvit_B6 = Float.parseFloat(df.format(totalvit_B6));
                    float newtotalfolate_Tot = Float.parseFloat(df.format(totalfolate_Tot));
                    float newtotalfolic_Acid = Float.parseFloat(df.format(totalfolic_Acid));
                    float newtotalfood_Folate = Float.parseFloat(df.format(totalfood_Folate));
                    float newtotalfolate_DFE = Float.parseFloat(df.format(totalfolate_DFE));
                    float newtotalcholine_Tot = Float.parseFloat(df.format(totalcholine_Tot));
                    float newtotalvit_B12 = Float.parseFloat(df.format(totalvit_B12));
                    float newtotalvit_A_IU = Float.parseFloat(df.format(totalvit_A_IU));
                    float newtotalvit_A_RAE = Float.parseFloat(df.format(totalvit_A_RAE));
                    float newtotalretinol = Float.parseFloat(df.format(totalretinol));
                    float newtotalalpha_Carot = Float.parseFloat(df.format(totalalpha_Carot));
                    float newtotalbeta_Carot = Float.parseFloat(df.format(totalbeta_Carot));
                    float newtotalbeta_Crypt = Float.parseFloat(df.format(totalbeta_Crypt));
                    float newtotallycopene = Float.parseFloat(df.format(totallycopene));
                    float newtotallutZea = Float.parseFloat(df.format(totallutZea));
                    float newtotalvit_E = Float.parseFloat(df.format(totalvit_E));
                    float newtotalvit_D_µg = Float.parseFloat(df.format(totalvit_D_µg));
                    float newtotalvit_D_IU = Float.parseFloat(df.format(totalvit_D_IU));
                    float newtotalvit_K = Float.parseFloat(df.format(totalvit_K));
                    float newtotalFA_Sat = Float.parseFloat(df.format(totalFA_Sat));
                    float newtotalFA_Mono = Float.parseFloat(df.format(totalFA_Mono));
                    float newtotalFA_Poly = Float.parseFloat(df.format(totalFA_Poly));
                    float newtotalcholestrl = Float.parseFloat(df.format(totalcholestrl));

                    //показываем Dialog с тем, что получилось

                    NewIngDialog nid = new NewIngDialog(getActivity(),newtotalkcal,newtotalprotein,newtotallipid_tot,newtotalcarbohydrt);

                    nid.nif = this;
                    nid.show();

                    btnSound.start();// звук

                    //запись в базу
                    dbH.insertNewMeal(dbH.getWritableDatabase(),totalNames.toString(),newtotalwater,newtotalkcal,
                            newtotalprotein,newtotallipid_tot,newtotalash,newtotalcarbohydrt,newtotalfiber_TD,
                            newtotalsugar_Tot,newtotalcalcium,newtotaliron,newtotalmagnesium,newtotalphosphorus,
                            newtotalpotassium,newtotalsodium,newtotalzinc,newtotalcopper,newtotalmanganese,
                            newtotalselenium,newtotalvit_C,newtotalthiamin,newtotalriboflavin,newtotalniacin,
                            newtotalpanto_Acid,newtotalvit_B6,newtotalfolate_Tot,newtotalfolic_Acid,newtotalfood_Folate,
                            newtotalfolate_DFE,newtotalcholine_Tot,newtotalvit_B12,newtotalvit_A_IU,newtotalvit_A_RAE,
                            newtotalretinol,newtotalalpha_Carot,newtotalbeta_Carot,newtotalbeta_Crypt,newtotallycopene,
                            newtotallutZea,newtotalvit_E,newtotalvit_D_µg,newtotalvit_D_IU,newtotalvit_K,newtotalFA_Sat,
                            newtotalFA_Mono,newtotalFA_Poly,newtotalcholestrl);
                    //Toast.makeText(getActivity(),"Added", Toast.LENGTH_SHORT).show();

                    //Toast.makeText(getActivity(), totalNames, Toast.LENGTH_SHORT).show();

                    // удаляем все поля
                    grid.removeAllViews();

                    //закрыть курсор, выйти в главное меню

                }else{
                    //all is bad
                    Toast.makeText(getActivity(), "No product", Toast.LENGTH_SHORT).show();
                }
                /*String p = " " + allElData.getCount();
                Toast a = Toast.makeText(getActivity(), p, Toast.LENGTH_SHORT);
                a.show();*/
                String p = elNames.length + " " + gWei.length;
                //Toast.makeText(getActivity(), p, Toast.LENGTH_SHORT).show();
            } else {// пустые массивы
                Toast.makeText(getActivity(), "Check all data", Toast.LENGTH_SHORT).show();
            }
        }else{// нет детей
            Toast.makeText(getActivity(),"Add smth", Toast.LENGTH_SHORT).show();
        }


    }

    /*@Override
    public void onPause() {
        super.onPause();

    }*/
}
