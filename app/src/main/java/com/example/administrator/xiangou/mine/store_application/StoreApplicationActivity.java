package com.example.administrator.xiangou.mine.store_application;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.ImageUtils;
import com.example.administrator.xiangou.tool.MyRefreshLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class StoreApplicationActivity extends PopupWindowsBaseActivity implements View.OnClickListener{
    private RelativeLayout ID_positive,ID_opposite,logo_potato,Business_license,Lease_contract;
    private ImageView backBtn;
    private Spinner province,city,districts;
    private ArrayList<String> imagepaths=new ArrayList<>();
    private List<Province> provinceList=new ArrayList<>();
    private MyRefreshLayout myRefreshLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_store);
        initView();
    }

    private void initView() {
        provinceList=parse();
        ID_positive= (RelativeLayout) findViewById(R.id.ID_positive);
        ID_positive.setOnClickListener(this);
        ID_opposite= (RelativeLayout) findViewById(R.id.ID_opposite);
        ID_opposite.setOnClickListener(this);
        logo_potato= (RelativeLayout) findViewById(R.id.logo_potato);
        logo_potato.setOnClickListener(this);
        Business_license= (RelativeLayout) findViewById(R.id.Business_license);
        Business_license.setOnClickListener(this);
        Lease_contract= (RelativeLayout) findViewById(R.id.Lease_contract);
        Lease_contract.setOnClickListener(this);
        backBtn= (ImageView)(findViewById(R.id.store_head_layout).findViewById(R.id.store_headback));
        backBtn.setOnClickListener(this);

        province= (Spinner) findViewById(R.id.store_address_province);
        city= (Spinner) findViewById(R.id.store_address_city);
        districts= (Spinner) findViewById(R.id.store_address_districts);

        ArrayAdapter<Province> provinceArrayAdapter=new ArrayAdapter<Province>(this,android.R.layout.simple_spinner_item);

    }

    /**
     * 解析xml文件
     * @return
     */
    private List<Province> parse() {
        List<Province> list=null;
        Province province=null;
        List<City> cities=null;

        List<District> districts=null;
        City city=null;
        List<District> district=null;
        // 创建解析器，并制定解析的xml文件
        XmlResourceParser parser = getResources().getXml(R.xml.provinces);
        try{
            int type = parser.getEventType();
            while(type!=1) {
                String tag = parser.getName();//获得标签名
                switch (type) {
                    case XmlResourceParser.START_DOCUMENT:
                        list = new ArrayList<Province>();
                        break;
                    case XmlResourceParser.START_TAG:
//                        if ("City".equals(tag)) {
//                            province=new Province();
//                            cities = new ArrayList<City>();
//                            int n =parser.getAttributeCount();
//                            for(int i=0 ;i<n;i++){
//                                //获得属性的名和值
//                                String name = parser.getAttributeName(i);
//                                String value = parser.getAttributeValue(i);
//                                if("p_id".equals(name)){
//                                    province.setID(Integer.parseInt(value));
//                                }
//                            }
//                        }
//                        if ("pn".equals(tag)){//省名字
//                            province.setName(parser.nextText());
//                        }
//                        if ("c".equals(tag)){//城市
//                            city = new City();
//                            districts = new ArrayList<District>();
//                            int n =parser.getAttributeCount();
//                            for(int i=0 ;i<n;i++){
//                                String name = parser.getAttributeName(i);
//                                String value = parser.getAttributeValue(i);
//                                if("c_id".equals(name)){
//                                    city.setId(value);
//                                }
//                            }
//                        }
//                        if ("cn".equals(tag)){
//                            city.setName(parser.nextText());
//                        }
//                        if ("d".equals(tag)){
//                            district = new District();
//                            int n =parser.getAttributeCount();
//                            for(int i=0 ;i<n;i++){
//                                String name = parser.getAttributeName(i);
//                                String value = parser.getAttributeValue(i);
//                                if("d_id".equals(name)){
//                                    district.setId(value);
//                                }
//                            }
//                            district.setName(parser.nextText());
//                            districts.add(district);
//                        }
                        break;
                    case XmlResourceParser.END_TAG:
//                        if ("c".equals(tag)){
//                            city.setDistricts(districts);
//                            cities.add(city);
//                        }
//                        if("p".equals(tag)){
//                            province.setCitys(cities);
//                            list.add(province);
//                        }
                        break;
                    default:
                        break;
                }
                type = parser.next();
            }
        }catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } */
        catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ID_positive:
                showPicturePopupWindow();
                break;
            case R.id.ID_opposite:
                showPicturePopupWindow();
                break;
            case R.id.logo_potato:
                showPicturePopupWindow();
                break;
            case R.id.Business_license:
                showPicturePopupWindow();
                break;
            case R.id.Lease_contract:
                showPicturePopupWindow();
                break;
            case R.id.store_headback:
                finish();
                break;
         }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_IMAGE_RESULT_CODE && resultCode == RESULT_OK){
            String imagePath = "";
            if(data != null && data.getData() != null){//有数据返回直接使用返回的图片地址
                imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
            }else{//无数据使用指定的图片路径
                imagePath = mImagePath;
            }
            imagepaths.add(imagePath);
             update(imagepaths);// 刷新图片
            Log.e("onActivityResult", "onActivityResult: "+imagepaths.toString() );
        }
    }

    private void update(ArrayList<String> imagepaths) {
        Drawable drawable;
        ImageView img;
        for (int i = 0; i <imagepaths.size() ; i++) {
            switch (i){
                case 0:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(0),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    ID_positive.addView(img);
                    break;
                case 1:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(1),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    ID_opposite.addView(img);
                    break;
                case 2:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(2),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    logo_potato.addView(img);
                    break;
                case 3:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(3),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    logo_potato.addView(img);
                    break;
                case 4:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(4),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    logo_potato.addView(img);
                    break;
            }
        }
    }
}
