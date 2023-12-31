package com.example.homework2_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), getLifecycle());
        pager.setAdapter(adapter);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("이메일"));
        tabs.addTab(tabs.newTab().setText("정보"));
        tabs.addTab(tabs.newTab().setText("위치"));
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch(position) {
                    case 0:
                        tabs.setScrollPosition(0, 0f, true);
                        bottomNavigation.setSelectedItemId(R.id.tab1);
                        return;
                    case 1:
                        tabs.setScrollPosition(1, 0f, true);
                        bottomNavigation.setSelectedItemId(R.id.tab2);
                        return;
                    case 2:
                        tabs.setScrollPosition(2, 0f, true);
                        bottomNavigation.setSelectedItemId(R.id.tab3);
                }
            }
        });
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    pager.setCurrentItem(0);
                    bottomNavigation.setSelectedItemId(R.id.tab1);
                }
                else if(position == 1){
                    pager.setCurrentItem(1);
                    bottomNavigation.setSelectedItemId(R.id.tab2);
                }
                else if(position == 2){
                    pager.setCurrentItem(2);
                    bottomNavigation.setSelectedItemId(R.id.tab3);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tab1:
                                pager.setCurrentItem(0);
                                tabs.setScrollPosition(0, 0f, true);
                                return true;
                            case R.id.tab2:
                                pager.setCurrentItem(1);
                                tabs.setScrollPosition(1, 0f, true);
                                return true;
                            case R.id.tab3:
                                pager.setCurrentItem(2);
                                tabs.setScrollPosition(2, 0f, true);
                                return true;
                        }
                        return false;
                    }
                }
        );
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pager.setCurrentItem(1);
//            }
//        });
    }

    class MyPagerAdapter extends FragmentStateAdapter {
        int itemCount = 3;
        public MyPagerAdapter(FragmentManager fm, Lifecycle cycle) {super(fm, cycle);}
        @Override
        public int getItemCount() {return itemCount;}
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position){
                case 0:{
                    return new Fragment1();
                }
                case 1:{
                    return new Fragment2();
                }
                case 2:{
                    return new Fragment3();
                }
            }
            return new Fragment1();
        }
    }
}