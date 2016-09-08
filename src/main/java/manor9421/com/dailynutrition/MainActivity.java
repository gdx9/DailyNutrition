package manor9421.com.dailynutrition;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends Activity implements MenuNavigationFragment.Callbacks,ProductListFragment.ProdListCallback,DailyNutritionFragment.ProdDetailsListCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+"FOOD"+"'", null);

        if(cursor!=null && cursor.getCount()>0) {//если таблица есть (запуск не первый)
            cursor.close();
        }else{
            try {
                InputStream myInput = this.getAssets().open("sq");//открыли бд
                String outFileName = this.getApplicationInfo().dataDir+"/databases/" +  "foods";
                OutputStream myOutput = new FileOutputStream(outFileName);//открыли бд приложения для записи
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer))>0){
                    myOutput.write(buffer, 0, length);
                }
                //закрываем потоки
                myOutput.flush();
                myOutput.close();
                myInput.close();

                Toast.makeText(this,"Database created",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(this,"Database creation error"+e,Toast.LENGTH_LONG).show();
                System.out.println(e+"");
            }
        }

        if(findViewById(R.id.infoFragment) == null){
        }else{
            Fragment welcomeFragment = new WelcomeFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.infoFragment,welcomeFragment)
                    .commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(findViewById(R.id.infoFragment) == null){
            switch(id){
                case android.R.id.home:
                    //do nothing
                    return true;
                case R.id.action_add_product:
                    Intent addProductActivityIntent = new Intent(this,AddProductActivity.class);
                    startActivity(addProductActivityIntent);
                    return true;
                case R.id.action_product_list:
                    Intent productListActivityIntent = new Intent(this,ProductListActivity.class);
                    startActivity(productListActivityIntent);
                    return true;
                case R.id.action_new_ingestion:
                    Intent newIngestionActivityIntent = new Intent(this,NewIngestionActivity.class);
                    startActivity(newIngestionActivityIntent);
                    return true;
                case R.id.action_your_ration:
                    Intent dailyNutritionIntent = new Intent(this,DailyNutritionActivity.class);
                    startActivity(dailyNutritionIntent);
                    return true;
                case R.id.action_info:
                    Intent infoIntent = new Intent(this,InfoActivity.class);
                    startActivity(infoIntent);
                    return true;
                case R.id.action_home:
                    Toast.makeText(this,"You're here!",Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }else{
            switch(id){
                case android.R.id.home:
                    finish();
                    return true;
                case R.id.action_add_product:
                    Fragment addProductFragment = new AddProductFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.infoFragment,addProductFragment)
                            .commit();
                    return true;
                case R.id.action_product_list:
                    Fragment productListFragment = new ProductListFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.infoFragment,productListFragment)
                            .commit();
                    return true;
                case R.id.action_new_ingestion:
                    Fragment newIngestionFragment = new NewIngestionFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.infoFragment, newIngestionFragment)
                            .commit();
                    return true;
                case R.id.action_your_ration:
                    Fragment dailyNutritionFragment = new DailyNutritionFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.infoFragment, dailyNutritionFragment)
                            .commit();
                    return true;
                case R.id.action_info:
                    Intent infoIntent = new Intent(this,InfoActivity.class);
                    startActivity(infoIntent);
                    return true;
                case R.id.action_home:
                    Toast.makeText(this,"You're here!",Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }


    @Override
    public void onMenuItemSelected(int b) {
        if(findViewById(R.id.infoFragment) == null){
            //начинаем интент
            if(b == 1){
                Intent intent = new Intent(this,AddProductActivity.class);
                startActivity(intent);
            }else if(b == 2){
                Intent intent = new Intent(this,ProductListActivity.class);
                startActivity(intent);
            }else if(b == 3) {
                Intent intent = new Intent(this,NewIngestionActivity.class);
                startActivity(intent);
            }else if(b == 4) {
                Intent intent = new Intent(this,DailyNutritionActivity.class);
                startActivity(intent);
            }
        }else{
            if(b == 1){
                Fragment addProductFragment = new AddProductFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.infoFragment,addProductFragment)
                        .commit();
            }else if(b == 2){
                Fragment productListFragment = new ProductListFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.infoFragment,productListFragment)
                        .commit();
            }else if(b == 3) {
                Fragment newIngestionFragment = new NewIngestionFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.infoFragment, newIngestionFragment)
                        .commit();
            }else if(b == 4) {
                Fragment dailyNutritionFragment = new DailyNutritionFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.infoFragment, dailyNutritionFragment)
                        .commit();
            }
        }


    }

    @Override
    public void onListItemClick(int item) {
        Fragment productDetailsFragment = ProductDetailsFragment.newInstance(item);
        getFragmentManager().beginTransaction()
                .replace(R.id.infoFragment,productDetailsFragment)
                .commit();
    }

    @Override
    public void onDetailsListItemClick(int item) {
        Fragment dailyNutritionDetailsFragment = DailyNutritionDetailsFragment.newInstance(item);
        getFragmentManager().beginTransaction()
                .replace(R.id.infoFragment,dailyNutritionDetailsFragment)
                .commit();
    }


    /*@Override
    public void onBackPressed() {

    }*/
}
