package com.indianapp.woodworks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class FragmentActivity extends AppCompatActivity {
    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        chipNavigationBar = findViewById(R.id.chipNavigation);

        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers, new HomeFrag()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        fragment = new HomeFrag();
                        break;
                    case R.id.store:
                        fragment = new CartFrag();
                        break;
                    case R.id.history:
                        fragment = new PrevOrderFrag();
                        break;
                    case R.id.donate:
                        fragment = new ProfileFrag();
                        break;

                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragment).commit();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {

        if(fragment==null){
            finishAffinity();
        }else{
            Intent intent = new Intent(getApplicationContext(),FragmentActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        }
    }

}