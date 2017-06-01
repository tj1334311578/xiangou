package com.example.administrator.xiangou.net;

import com.example.administrator.xiangou.goods_sort.storehome.HomePageBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.CommentBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.SimpleGoodsDetialBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.commenttempBean;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mine.ToApplyStoreBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.EditAddressEnterBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.mine.setting.personal.PersonalDetialsBean;
import com.example.administrator.xiangou.mine.store_application.ApplicantInfoBean;
import com.example.administrator.xiangou.mine.store_application.model.CategoryListDataBean;
import com.example.administrator.xiangou.nearby.apimodel.GoodsListDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyBenifitDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDetailDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyStoreApiDataBean;

import org.json.JSONObject;

import okhttp3.MultipartBody;
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
    public static final String mBASEURL = "http://192.168.0.123/";
    public static final String BASEURL = "http://192.168.0.123";


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
    // TODO: 2017/5/16 出现请求未知异常，可获得数据，但不能进入rxjava的next代码，直接跳转到error方法异常为code:1000未知异常（待解决）
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
    Observable<PersonalDetialsBean> uploadUserDetials(@Part("user_id") int user_id,
                                                      @Part("sex") int sex,
                                                      @Part("nickname") String nickname,
                                                      @Part MultipartBody.Part file
                                                    );
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



}
