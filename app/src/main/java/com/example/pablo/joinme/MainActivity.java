package com.example.pablo.joinme;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Time's screen of the splash screen
    public  static int SPLASH_TIME = 3000 ;

    TabLayout alltabs ;
    ViewPager viewPager ;
    Fragment fragment_1 ;
    Fragment fragment_2 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alltabs = (TabLayout)findViewById(R.id.tab) ;



        // Print the logo in the menu
        ActionBar actionBar = getSupportActionBar() ;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        viewPager = (ViewPager)findViewById(R.id.viewpager) ;
        viewPager.setAdapter(new pageAdapter(getSupportFragmentManager(),this));

        alltabs.setupWithViewPager(viewPager);


    }


    /***************************************************************/
    /*
                         Function to manage the TabLayout
     */
    private void bindWidgetsWithAnEvent()
    {
        alltabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(fragment_1);
                break;
            case 1 :
                replaceFragment(fragment_2);
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }


    /***************************************************************/
    /*
                         Creation of the menu
    */
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater() ;
        menuInflater.inflate(R.menu.menu_action, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // Action when click on the menu :
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast("refresh");

                return true;
            case R.id.settings:
                Toast("settings");
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
*/
    /***************************************************************/

    public void Toast(String message)
    {
        Toast.makeText(getBaseContext(),message,Toast.LENGTH_SHORT).show();
    }



    /*
    // Action when we click on an item
    public void onListItemClick(ListView l, View view, int position, long id){

        // Here we just print the text
        ViewGroup viewg=(ViewGroup)view;
        TextView txt = (TextView)findViewById(R.id.txtList1) ;
       // TextView tv=(TextView)viewg.findViewById(R.id.txtList1);
        Toast.makeText(getBaseContext(), txt.getText().toString(),Toast.LENGTH_LONG).show();
    }
    */
}
