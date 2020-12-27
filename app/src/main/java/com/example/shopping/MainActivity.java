package com.example.shopping;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopping.bean.ShopBean;
import com.example.shopping.contract.HomeContract;
import com.example.shopping.fragment.HomeFragment;
import com.example.shopping.fragment.MeFragment;
import com.example.shopping.fragment.ShopFragment;
import com.example.shopping.fragment.SortFragment;
import com.example.shopping.fragment.SpecialFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Fragment mCurrentFragment = null;
    private FrameLayout frame;
    private BottomNavigationView bnv;
    private ArrayList<Fragment> fragments;
    private HomeFragment homeFragment;
    private SpecialFragment specialFragment;
    private SortFragment sortFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        bnv = (BottomNavigationView) findViewById(R.id.bnv);
        homeFragment = new HomeFragment();
        specialFragment = new SpecialFragment();
        sortFragment = new SortFragment();
        shopFragment = new ShopFragment();
        meFragment = new MeFragment();

        initFragment();

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_one:
                        showFragment(0);
                        break;
                    case R.id.item_two:
                        showFragment(1);
                        break;
                    case R.id.item_three:
                        showFragment(2);
                        break;
                    case R.id.item_four:
                        showFragment(3);
                        break;
                    case R.id.item_five:
                        showFragment(4);
                        break;
                }
                return true;
            }
        });
        bnv.getMenu().getItem(2).setChecked(true);
    }

    private void initFragment() {
        if (fragments != null) {
            fragments.clear();
        } else {
            fragments = new ArrayList<>();

        }
        fragments.add(homeFragment);
        fragments.add(specialFragment);
        fragments.add(sortFragment);
        fragments.add(shopFragment);
        fragments.add(meFragment);
    }

    private void showFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            Fragment fragment = fragments.get(position);

            if (null != fragment && mCurrentFragment != fragment) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment);
                }
                mCurrentFragment = fragment;
                if (!fragment.isAdded()) {
                    transaction.add(R.id.frame, fragment);
                } else {
                    transaction.show(fragment);
                }
                transaction.commit();
            }
        }
    }
}