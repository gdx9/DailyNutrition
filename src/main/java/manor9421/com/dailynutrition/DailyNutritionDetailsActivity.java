package manor9421.com.dailynutrition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DailyNutritionDetailsActivity extends Activity {

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_nutrition_details);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("Daily Nutrition");
        getActionBar().setIcon(R.drawable.ic_action_back);//ContextCompat.getDrawable(this,R.drawable.ic_action_back)
        findViewById(android.R.id.home).setPadding(15, 0, 15, 0);

        if(savedInstanceState!=null){
            id = savedInstanceState.getInt("savedId");
        }else {
            id = (int) getIntent().getExtras().get("id");
        }

        DailyNutritionDetailsFragment dailyNutritionDetailsFragment = DailyNutritionDetailsFragment.newInstance(id);
        getFragmentManager().beginTransaction()
                .replace(R.id.dailyNutrDetailsFragment,dailyNutritionDetailsFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedId",id);
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

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_add_product:
                Intent addProductActivityIntent = new Intent(this, AddProductActivity.class);
                startActivity(addProductActivityIntent);
                return true;
            case R.id.action_product_list:
                Intent productListActivityIntent = new Intent(this, ProductListActivity.class);
                startActivity(productListActivityIntent);
                return true;
            case R.id.action_new_ingestion:
                Intent newIngestionActivityIntent = new Intent(this, NewIngestionActivity.class);
                startActivity(newIngestionActivityIntent);
                return true;
            case R.id.action_your_ration:
                Intent dailyNutritionIntent = new Intent(this,DailyNutritionActivity.class);
                startActivity(dailyNutritionIntent);
                return true;
            case R.id.action_info:
                Intent infoIntent = new Intent(this, InfoActivity.class);
                startActivity(infoIntent);
                return true;
            case R.id.action_home:
                Intent mainActivityIntent = new Intent(this,MainActivity.class);
                startActivity(mainActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
