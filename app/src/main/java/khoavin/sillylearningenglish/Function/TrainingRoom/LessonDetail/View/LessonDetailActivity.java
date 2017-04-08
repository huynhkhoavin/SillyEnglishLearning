package khoavin.sillylearningenglish.Function.TrainingRoom.LessonDetail.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import khoavin.sillylearningenglish.NetworkService.NetworkModels.Lesson;
import khoavin.sillylearningenglish.Pattern.FragmentPattern;
import khoavin.sillylearningenglish.Pattern.ViewPagerAdapter;
import khoavin.sillylearningenglish.R;

/**
 * Created by KhoaVin on 2/18/2017.
 */

public class LessonDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager viewPager;
    private ViewPagerAdapter tabPagerAdapter;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    public Lesson lesson;

    //region METHOD
    private void setUpTabAdapter(){
        String[] TabTitle = {"Play","Tiến Trình"};
        FragmentPattern[] FragmentList = {new LessonPlayFragment(),new LessonProgressFragment()};
        tabPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),TabTitle,FragmentList);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
    }
    //endregion
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_lesson_detail);
        ButterKnife.bind(this);
        setUpTabAdapter();
    }
}
