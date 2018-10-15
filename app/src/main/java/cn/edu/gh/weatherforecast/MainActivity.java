package cn.edu.gh.weatherforecast;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private ImageView mCitySelected;
    private String updateCity=null;
    private TextView tv_area;
    private ViewPager mViewPager;
    private List<View> mViewList = new ArrayList<View>();//存放待滑动的view
    private LayoutInflater mInflater;
    private View view_01, view_02;//2个待滑动的view
    private ImageView iv_point;
    private ImageView []ivPointArray;
    private ViewGroup vg;//放置圆点
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCitySelected = findViewById(R.id.img_area_city);
        tv_area=findViewById(R.id.todayInfo_cityName);
        mCitySelected.setOnClickListener(this);
        initViews();
        //加载底部圆点
        initPoint();
    }

    private void initPoint() {
        //这里实例化LinearLayout
        vg = findViewById(R.id.guide_point);
//根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[mViewList.size()];
//循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = mViewList.size();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(this);
            iv_point.setLayoutParams(new ViewGroup.LayoutParams(40, 40));
            iv_point.setPadding(30, 0, 30, 0);//left,top,right,bottom
            ivPointArray[i] = iv_point;
//第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0) {
                iv_point.setBackgroundResource(R.mipmap.round_true );
            } else {
                iv_point.setBackgroundResource(R.mipmap.round_false);
            }
//将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }


    }

    private void initViews() {
        mInflater = getLayoutInflater();
        view_01 = mInflater.inflate(R.layout.layout_weilaisantian1, null);
        view_02 = mInflater.inflate(R.layout.layout_weilaisantian2, null);
        mViewList.add(view_01);
        mViewList.add(view_02);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setCurrentItem(0);//设置当前pager
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
//循环设置当前页的标记图
        int length = 2;
        for (int j = 0;j<length;j++) {
             ivPointArray[i].setBackgroundResource(R.mipmap.round_true);
                if (i != j) {
                    ivPointArray[j].setBackgroundResource(R.mipmap.round_false);
                 }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    class MyPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {//返回view数量
            return mViewList.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position), 0);
            return mViewList.get(position);
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == 1 && resultCode == RESULT_OK) {
            updateCity=data.getStringExtra("city");
            Log.d("myWeather", "选择的城市为" + updateCity);
            if (updateCity!=null){
                tv_area.setText(updateCity);
            }
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_area_city) {
            Intent intent = new Intent(this, SelectCityActivity.class);
            // startActivity(intent);
            startActivityForResult(intent, 1);
        }

    }
}