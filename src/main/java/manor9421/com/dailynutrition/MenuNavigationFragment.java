package manor9421.com.dailynutrition;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuNavigationFragment extends Fragment {

    private Callbacks mCallbacks;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    String clickedBTag;
    View view;

    int color;
    int pressedColor;

    public interface Callbacks{
        void onMenuItemSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {

            mCallbacks = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu_navigation, container, false);



        b1 = (Button) view.findViewById(R.id.addProductItem);
        b2 = (Button) view.findViewById(R.id.productListItem);
        b3 = (Button) view.findViewById(R.id.newIngestionItem);
        b4 = (Button) view.findViewById(R.id.dailyFoodItem);

        color = ContextCompat.getColor(getActivity(),R.color.menu_nav_background);
        pressedColor = ContextCompat.getColor(getActivity(),R.color.menu_nav_pressed_background);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changButtoneColor((Button)v);
                mCallbacks.onMenuItemSelected(1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changButtoneColor((Button)v);
                mCallbacks.onMenuItemSelected(2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changButtoneColor((Button)v);
                mCallbacks.onMenuItemSelected(3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changButtoneColor((Button)v);
                mCallbacks.onMenuItemSelected(4);
            }
        });

        return view;
    }

    public void changButtoneColor(Button n){//передаем тэг



        b1.setBackgroundColor(color);
        b2.setBackgroundColor(color);
        b3.setBackgroundColor(color);
        b4.setBackgroundColor(color);

        n.setBackgroundColor(pressedColor);

    }


}
