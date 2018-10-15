package cn.edu.gh.weatherforecast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class SelectCityActivity extends AppCompatActivity implements View.OnClickListener{
private ImageView mBackBtn;
private ListView lv_city;
private List<City> mCityList;
private MyApplication myApplication;
private ArrayList<String> mArrayList;
private String updateCity=null;
private TextView tv_area;
private ImageView mSearchBtn;
private EditText mSearchET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_selected);
        mBackBtn=findViewById(R.id.title_back);
        mSearchBtn=findViewById(R.id.search_button);
        mSearchET=findViewById(R.id.search_et);
        lv_city=findViewById(R.id.lv_city);
        myApplication= (MyApplication) getApplication();
        mCityList=myApplication.getCityList();
        mArrayList=new ArrayList<String>();
        for(int i=0;i<mCityList.size();i++){
            String cityName=mCityList.get(i).getCity();
            mArrayList.add(cityName);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCityActivity.this,android.R.layout.simple_list_item_1,mArrayList);
        lv_city.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateCity=mCityList.get(position).getCity();
                Log.d("update city code",updateCity);
                tv_area=findViewById(R.id.title_name);
                tv_area.setText("当前城市："+mCityList.get(position).getCity());
            }
        };
        lv_city.setOnItemClickListener(itemClickListener);
        mBackBtn.setOnClickListener(this);
        mSearchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.title_back:
                Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra("city",updateCity);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.search_button:
                String SearchCityName=mSearchET.getText().toString();
                Log.d("搜索的城市名为",SearchCityName);
                ArrayList<String> mSearchList=new ArrayList<String>();
                for(int i=0;i<mCityList.size();i++){
                    String No_=Integer.toString(i);
                    String number=mCityList.get(i).getNumber();
                    String cityName=mCityList.get(i).getCity();
                    if(cityName.equals(SearchCityName)){
                        mSearchList.add("No."+No_+":"+number+"-"+cityName);
                        Log.d("SearchCity data","No."+No_+":"+number+"-"+cityName);
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCityActivity.this,android.R.layout.simple_list_item_1,mSearchList);
                    lv_city.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            default:
                break;
        }
    }
}
