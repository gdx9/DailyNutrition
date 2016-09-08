package manor9421.com.dailynutrition;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ProductListActivity extends Activity implements ProductListFragment.ProdListCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("Product List");
        getActionBar().setIcon(R.drawable.ic_action_back);//ContextCompat.getDrawable(this,R.drawable.ic_action_back)
        findViewById(android.R.id.home).setPadding(15, 0, 15, 0);
    }

    @Override
    public void onListItemClick(int item) {
        Intent intent = new Intent(this,ProductDetailsActivity.class);
        intent.putExtra("id",item);
        startActivity(intent);
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
                Toast.makeText(this,"You're here",Toast.LENGTH_SHORT).show();
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
