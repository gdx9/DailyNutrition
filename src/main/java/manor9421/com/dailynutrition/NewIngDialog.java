package manor9421.com.dailynutrition;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by manor on 9/2/16.
 */
public class NewIngDialog extends Dialog{

    NewIngDialog thisDoalog = this;

    NewIngestionFragment nif;
    float kcalCount = 0f;
    float proteinsCount = 0f;
    float lipidsCount = 0f;
    float carbohydratesCount = 0f;

    public NewIngDialog(Context context, float kcalCount, float proteinsCount, float lipidsCount, float carbohydratesCount) {
        super(context);

        this.kcalCount = kcalCount;
        this.proteinsCount = proteinsCount;
        this.lipidsCount = lipidsCount;
        this.carbohydratesCount = carbohydratesCount;

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#60000000")));
        this.setContentView(R.layout.new_ing_dialog);

        ((TextView) findViewById(R.id.kcalCount)).setText(kcalCount+"");
        ((TextView) findViewById(R.id.proteinsCount)).setText(proteinsCount+"");
        ((TextView) findViewById(R.id.lipidsCount)).setText(lipidsCount+"");
        ((TextView) findViewById(R.id.carbohydratesCount)).setText(carbohydratesCount+"");

        Button newIngOkButton = (Button) findViewById(R.id.newIngOkButton);
        newIngOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisDoalog.dismiss();
            }
        });

    }

}
