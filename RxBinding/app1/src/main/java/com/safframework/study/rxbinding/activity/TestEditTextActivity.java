package com.safframework.study.rxbinding.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.rxbinding.R;
import com.safframework.study.rxbinding.app.BaseActivity;
import com.safframework.study.rxbinding.domain.ValidationResult;
import com.safframework.study.rxbinding.utils.AppUtils;
import com.safframework.study.rxbinding.utils.RxUtils;
import com.safframework.tony.common.utils.Preconditions;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by tony on 2017/9/18.
 */

public class TestEditTextActivity extends BaseActivity {

    @InjectView(R.id.phone)
    EditText phone;

    @InjectView(R.id.password)
    EditText password;

    @InjectView(R.id.login)
    TextView login;

    private ValidationResult result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edittext);

        initViews();
    }

    private void initViews() {

        Observable<CharSequence> ObservablePhone = RxTextView.textChanges(phone);
        Observable<CharSequence> ObservablePassword = RxTextView.textChanges(password);

        Observable.combineLatest(ObservablePhone, ObservablePassword, new BiFunction<CharSequence,CharSequence,ValidationResult>() {

            @Override
            public ValidationResult apply(@NonNull CharSequence o1, @NonNull CharSequence o2) throws Exception {

                if (o1.length()>0 || o2.length()>0) {

                    login.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_login_pressed));
                } else {

                    login.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_login_normal));
                }

                ValidationResult result = new ValidationResult();

                if (o1.length()==0) {

                    result.flag = false;
                    result.message = "手机号码不能为空";
                } else if (o1.length()!=11) {

                    result.flag = false;
                    result.message = "手机号码需要11位";
                } else if (o1 !=null && !AppUtils.isPhoneNumber(o1.toString())) {

                    result.flag = false;
                    result.message = "手机号码需要数字";
                } else if(o2.length()==0) {

                    result.flag = false;
                    result.message = "密码不能为空";
                }

                return result;
            }
        }).subscribe(new Consumer<ValidationResult>() {

            @Override
            public void accept(@NonNull ValidationResult r) throws Exception {

                result = r;
            }
        });

        RxView.clicks(login)
                .compose(RxUtils.useRxViewTransformer(TestEditTextActivity.this))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        if (result==null) return;

                        if (result.flag) {

                            Toast.makeText(TestEditTextActivity.this,"模拟登录成功",Toast.LENGTH_SHORT).show();
                        } else if (Preconditions.isNotBlank(result.message)) {

                            Toast.makeText(TestEditTextActivity.this,result.message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
