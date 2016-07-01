package com.paylibrary.pay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;

import com.commonslibrary.commons.net.RequestCallBack;
import com.commonslibrary.commons.utils.ToastUtils;

import java.util.Map;

/**
 * @author duanhui
 * @desc
 * @time 2016/2/24$ 11:22$
 */
public abstract class IPay {

    protected Activity activity;

    public static final int ALI_SDK_PAY_FLAG = 1;//ali 支付成功 handler 标识
    public static final int ALI_SDK_CHECK_FLAG = 2;//ali 支付成功，检查标识
    public static final int WX_SDK_PAY_FLAG = 3;//微信支付成功 handler 标识


    public ProgressDialog progressDialog;


    public void init(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载...");
        this.activity = activity;
    }

    public void showProgress() {
        progressDialog.show();//显示进度条
    }

    protected void hideProgress(String errorMessage) {
        if (!TextUtils.isEmpty(errorMessage)) {
            ToastUtils.show(activity, errorMessage);
        }
        progressDialog.dismiss();
    }

    public boolean isEmpty(Object object){
        if (object == null){
            return true;
        }
        if (object instanceof Integer){
            return  (int)object == 0;
        }

        if (object instanceof String && TextUtils.isEmpty(object.toString())){
            return true;
        }

        return false;
    }

    protected void hideProgress() {
        hideProgress("");
    }


    /**
     * 支付结果内部自己实现
     */
    public abstract void pay(Map<String, Object> params,RequestCallBack<String> callBack);

    /**
     * 请求订单号
     */
    public abstract void requestOrder(Map<String, Object> params,RequestCallBack<String> callBack);

    /**
     * 请求支付参数
     */
    public abstract void getPayParam(Map<String, Object> params,RequestCallBack<String> callBack);


    /**
     * 请求后台服务器得到支付结果
     */
    public abstract void getPayResult(Map<String, Object> params,RequestCallBack<String> callBack);
}
