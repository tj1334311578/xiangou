package com.example.administrator.xiangou.net;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.nearby.apimodel.CommentDataBean;
import com.example.administrator.xiangou.nearby.apimodel.GoodsListDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyBenifitDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDetailDataBean;
import com.example.administrator.xiangou.nearby.apimodel.NearbyStoreApiDataBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

//┏┓　　　┏┓
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
    public static final String mBASEURL = "http://192.168.0.106/";
    public static final String BASEURL = "http://192.168.0.106";

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


    //店铺申请
    @Multipart
    @POST("api/Stores/apply/")
    Observable<ResponseBody> applyShop(@Body RequestBody infos,
                                       @PartMap Map<String,RequestBody> id_img,
                                       @Part RequestBody logo,
                                       @Part RequestBody licence,
                                       @Part RequestBody contract);

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
    Observable<CommentDataBean> callCommentList(@Query("goods_id") int goodsId,//商品分类id(从哪个分类进去就传不是就不传
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

}