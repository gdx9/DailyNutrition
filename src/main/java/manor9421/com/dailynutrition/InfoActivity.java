package manor9421.com.dailynutrition;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends Activity {

    Button playStoreLink;
    Button developerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("Info");
        getActionBar().setIcon(R.drawable.ic_action_back);//ContextCompat.getDrawable(this,R.drawable.ic_action_back)
        findViewById(android.R.id.home).setPadding(15, 0, 15, 0);
        findViewById(android.R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        playStoreLink = (Button) findViewById(R.id.playStoreLink);
        playStoreLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=manor9421.com.dailynutrition"));
                startActivity(intent);
            }
        });

        developerEmail = (Button) findViewById(R.id.developerEmail);
        developerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"manor9421@yahoo.co.jp"});
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }
    /*
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
        switch(id){
            case android.R.id.home:
                finish();
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
                Toast.makeText(this,"You're here",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_home:
                Intent mainActivityIntent = new Intent(this,MainActivity.class);
                startActivity(mainActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */
}
