package com.example.administrator.xiangou.net;

import com.example.administrator.xiangou.classification.bean.FirstLevelBean;
import com.example.administrator.xiangou.goods_sort.storehome.HomePageBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.CommentBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.SimpleGoodsDetialBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.commenttempBean;
import com.example.administrator.xiangou.home.model.HomeDataBean;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mine.ToApplyStoreBean;
import com.example.administrator.xiangou.mine.mystore.datamanager.TotalDataBean;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.AddGoodsAttrBean;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.AddGoodsDataBean;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.AddGoodsSpecBean;
import com.example.administrator.xiangou.mine.mystore.storemanager.StoreManagerInfoBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.EditAddressEnterBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.mine.store_application.ApplicantInfoBean;
import com.example.administrator.xiangou.mine.store_application.model.CategoryListDataBean;
import com.example.administrator.xiangou.nearby.apimodel.GoodsListDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyBenifitDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDetailDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyStoreApiDataBean;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃     神兽保佑　　　　　　　　
//    ┃　　　┃     代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

public interface XianGouApiService {
    String mBASEURL = "http://192.168.0.123/";
    String IMGBASEURL = "http://192.168.0.123";
    //     http://192.168.0.123/
/***********首页接口************/
    @POST("api/Index/index/")
    Observable<HomeDataBean> getHomePageData(@Query("map_x") String map_x,
                                             @Query("map_y") String map_y,
                                             @Query("cityid") int cityid);

/***********登录接口************/
    //获取验证码--注册
    @POST("index.php/Api/Register/send_code/")
    Observable<Captcha> getCapture(@Query("tel") String tel);

    //验证验证码--注册
    @POST("index.php/Api/Register/register1/")
    Observable<Captcha> goRegister(@Query("tel") String tel, @Query("code") String code);

    //完成--注册
    @POST("index.php/Api/Register/register/")
    Observable<Captcha> toRegister(@Query("tel") String tel, @Query("code") String code, @Query("password") String password);

    //账号登录
//    @FormUrlEncoded
    @POST("api/Login/login/")
    Observable<LoginBean> loginID(@Query("tel") String tel, @Query("password") String password);

    /**
     * 请求验证码（登录、找回密码）
     * @param tel
     * @param method 登录 login | 找回密码 findpsw
     * @return
     */
    @POST("index.php/Api/Login/verify/")
    Observable<Captcha> sendCapture(@Query("tel") String tel, @Query("method") String method);

    //动态登录
    @POST("Api/Login/vlogin/")
    Observable<LoginBean> loginV(@Query("tel") String tel, @Query("code") String code);

    //验证验证码--找回密码
    @POST("Api/Login/check_code/")
    Observable<Captcha> verifyFindPwd(@Query("tel") String tel, @Query("code") String code);

    //设新密码--找回密码
    @POST("Api/Login/findpsw/")
    Observable<Captcha> resetPwd(@Query("tel") String tel, @Query("password") String password, @Query("code") String code);

/***********店铺接口************/

    //进入店铺申请
    @POST("Api/Stores/inter/")
    Observable<ToApplyStoreBean> toApplyShop(@Query("user_id") int user_id);

    //获取选择城市列表
    @POST("Api/Stores/area/")
    Observable<ToApplyStoreBean> chooseNextAdr(@Query("region_id") int region_id);

    //获取主营类别列表数据
    @POST("Api/Stores/getcate/")
    Observable<CategoryListDataBean> getCategoryList();


    //店铺申请
    @Multipart
    @POST("api/Stores/apply/")
    Observable<Captcha> applyShop(
            @Part("infos") ApplicantInfoBean infos,
            @Part MultipartBody.Part[] id_img,
            @Part MultipartBody.Part logo,
            @Part MultipartBody.Part licence,
            @Part MultipartBody.Part contract
//            @PartMap Map<String, RequestBody> imgs
            );

/***********商品接口************/
    //附近商品
    @POST("api/Good/near_goods/")
    Observable<NearbyGoodsDataBean> callNearbyGoods(@Query("map_x") String mapX,//经度 no
                                                    @Query("map_y") String mapY,//纬度 no
                                                    @Query("distance") int distance);//距离范围
    //附近商品详情
    @POST("api/Good/goods_detail/")
    Observable<NearbyGoodsDetailDataBean> callNearbyGoodsDetail(@Query("goods_id") int goodsId,//商品id  必填yes
                                                            @Query("user_id") int userId,//用户登录情况下传 no
                                                            @Query("map_x") String mapX,//经度 no
                                                            @Query("map_y") String mapY,//纬度 no
                                                            @Query("type") int type);//安卓传1
    //附近店铺
    @POST("api/User/near/")
    Observable<NearbyStoreApiDataBean> callNearbyStore(@Query("map_x") String mapX,//经度 no104.039947
                                                       @Query("map_y") String mapY,//纬度 no30.563145
                                                       @Query("distance") int distance,//查找范围: (0-1000)传1000
                                                       @Query("page_no") int page_no);//页数不传默认取第一页的数据
    //评价列表
    @POST("Api/Good/comment_list/")
    Observable<CommentBean> callCommentList(@Query("goods_id") int goodsId,//商品分类id(从哪个分类进去就传不是就不传
                                            @Query("page_no") int page_no,//页数不传默认取第一页的数据
                                            @Query("condition") String condition);//条件不传默认全部评价好评(nice)中评(ordinary)差评(poor)  //评价列表
    @POST("Api/Good/comment_list/")
    Observable<commenttempBean> callComments(@Query("goods_id") int goodsId,//商品分类id(从哪个分类进去就传不是就不传
                                            @Query("page_no") int page_no,//页数不传默认取第一页的数据
                                             @Query("condition") String condition);//条件不传默认全部评价好评(nice)中评(ordinary)差评(poor)
    //商品列表
    @POST("Api/Good/goodsList/")
    Observable<GoodsListDataBean> callGoodsList(@Query("cat_id") int catId,//商品分类id(从哪个分类进去就传不是就不传
                                            @Query("page_no") int page_no,//页数不传默认取第一页的数据
                                            @Query("is_new") int is_new,//商品列表点击新品is_new传1	Int
                                            @Query("name") String name,//搜索框填写的内容模糊查询
                                            @Query("map_x") String mapX,//经度 no
                                            @Query("map_y") String mapY,//纬度 no
                                            @Query("sort") String sort,//按照销量排序传sales_sum 价格排序传shop_price
                                            @Query("sort_asc") String sort_asc);//价格排升续asc 价格降续不用传
    // 附近优惠
    @POST("Api/benefit/near_benift/")
    Observable<NearbyBenifitDataBean> callNearbyBenifit(@Query("map_x") String mapX,//经度 no
                                                    @Query("map_y") String mapY);//纬度 no

/***********购物车接口************/
    //添加商品到购物车
    @POST("api/Cart/addCart/")
    Observable<Captcha> callCartAddGoods(@Field("user_id") int user_id,
                                         @Field("goods_id") int goods_id,
                                         @Field("goods_num") int goods_num,
                                         @Field("goods_spec") int[] goods_spec
                                         );
    //店铺首页
    @POST("api/User/store_index/")
    Observable<HomePageBean> callHomePagerData(@Query("did") int storeId,//店铺did
                                               @Query("user_id") int userId);//若用户已经登录的状态下传过来
    //商品详情页接口
    // 出现请求未知异常，可获得数据，但不能进入rxjava的next代码，直接跳转到error方法异常为code:1000未知异常（待解决）
    @POST("Api/Good/goods_detail/")
    Observable<SimpleGoodsDetialBean> callSimpleGoodsDetails(@Query("goods_id") int goods_id,
//                                                @Query("user_id") int user_id,
//                                                @Query("map_x") String map_x,
//                                                @Query("map_y") String map_y,
                                                             @Query("type") int type
                                                           );//根据货物id获取该物品信息
/************用户中心**************/
    //个人信息修改
    @Multipart
    @POST("Api/User/personals/")
    Observable<Captcha> uploadUserDetials(@Part("user_id") int user_id,
                                          @Part("sex") int sex,
                                          @Part("nickname") String nickname,
                                          @Part MultipartBody.Part file);
    //进入地址页面
    @POST("api/User/address/")
    Observable<UserAddressBean> getUserAddrApi(@Query("user_id") int user_id);

    //进入编辑地址页面
    @POST("api/User/into_address/")
    Observable<EditAddressEnterBean> enterEditAddrApi(@Query("user_id") int user_id, @Query("address_id") int address_id);

    //设置默认地址
    @FormUrlEncoded
    @POST("api/User/set_default/")
    Observable<Captcha> setUserDefaultAddrApi(@Field("user_id") int user_id,@Field("address_id") int address_id);


    //获取选择省市区列表
    @POST("Api/User/area/")
    Observable<ToApplyStoreBean> chooseAreaAddrApi(@Query("region_id") int region_id);

    //添加/编辑收货地址
    @FormUrlEncoded
    @POST("Api/User/edit_address/")
    Observable<Captcha> saveUserAddrApi(@Field("info") JSONObject info);

    //删除地址
    @FormUrlEncoded
    @POST("api/User/del_address/")
    Observable<Captcha> delUserAddrApi(@Field("user_id") int user_id, @Field("address_id") int address_id);


    //店铺数据统计
    @POST("api/stores/data_manage/")
    Observable<TotalDataBean> callTotalDataApi(@Query("did") int did,
                                               @Query("Time") String Time);
    //测试数据模块
    @POST("api/order/ppay/")
    Observable<ResponseBody> callceshiApi(@Query("channel") String alipay,
                                                @Query("order_sn") String order_sn,
                                                @Query("amount") int amount);
    //店铺信息请求
    @POST("api/stores/edit/")
    Observable<StoreManagerInfoBean> callStoreInfo(@Query("did") int store_id);
    //店铺信息修改请求
    @Multipart
    @POST("api/stores/do_edit/")
    Observable<Captcha> callEditStoreInfo(@Part("did") int store_id,//店铺id
                                              @Part("map_x") String map_x,//店铺经度
                                              @Part("map_y") String map_y,//店铺纬度
                                              @Part("address") String address,//店铺地址
                                              @Part("province") int province_id,//店铺省份
                                              @Part("city") int city_id,//店铺城市id
                                              @Part("district") int district_id,//店铺区域id
                                              @Part("synopsis") String synopsis,//店铺简介
                                              @Part MultipartBody.Part logo,//店铺头像
                                              @Part("tel") String tel);//店铺电话

    //店铺管理商品列表
    @POST("Api/Stores/goodslist/")
    Observable<ResponseBody> callStoreGoodsList(@Query("did") int store_id,//店铺id
                                                 @Query("key_word") String key_word,//根据名称查找相关商品
                                                 @Query("page_no") int page_no,//分页不传查找第一页商品
                                                 @Query("type") int type);//必填1

    //分类列表
    //    @GET("/Api/Stores/getcate")
    @POST("api/good/goods_cate")
    Observable<FirstLevelBean> callClassification(@Query("cat_id") int cat_id );//一级分类不传默认为推荐


    //进入修改或添加商品
    @POST("Api/Stores/into_add/")
    Observable<ResponseBody> callIntoAddGoodsPage(@Query("did") int did,//店铺id
                                                  @Query("goods_id") int goods_id);//商品id（编辑商品时传，添加商品时不传）

    //店铺添加商品/Api/Stores/add_goods
    @Multipart
    @POST("Api/Stores/add_goods/")
    Observable<ResponseBody> callAddGoods(@Part("data") AddGoodsDataBean data,//商品基本信息
                                                 @Part("specs")AddGoodsSpecBean specs,//传产品当前页面若未传默认第一页
                                                 @Part("goods_attr")AddGoodsAttrBean goods_attr,//商品属性(若用户选择了模型填写属性传)
                                                 @Part MultipartBody.Part original_img,//商品列表图(单图)
                                                 @Part MultipartBody.Part[] goods_img//商品图片(多图)若用户只上传一张也用表单多图上传
                                          );

}
