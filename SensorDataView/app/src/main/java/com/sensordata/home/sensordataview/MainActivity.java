package com.sensordata.home.sensordataview;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView tv_btn_register;       //TextView作为注册的按钮
    private Button btn_login;               //登录按钮
    private EditText et_username;           //登录输入框
    private EditText et_password;           //密码输入框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_btn_register = findViewById(R.id.tv_register);
        tv_btn_register.setOnClickListener(new View.OnClickListener() {     //创建注册监听函数
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);      //打开注册页
            }
        });
        et_username = findViewById(R.id.etUserName);
        et_password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!OperationFun.isFastClick()){
                    if(et_username.getText().toString().length() == 0){
                        Toast.makeText(MainActivity.this,"用户名输入为空", Toast.LENGTH_SHORT).show();
                    }else {
                        MySQLOperation.userLoginSQL(new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                Bundle data = msg.getData();
                                String state = data.getString("state");
                                System.out.println(state);
                                if(state.equals("OK")){
                                    final Data userNameData = (Data) getApplication();
                                    userNameData.setUserName(et_username.getText().toString());
                                    Toast.makeText(MainActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this,SensorMainActivity.class);
                                    startActivity(intent);
                                }else if(state.equals("ERROR")){
                                    Toast.makeText(MainActivity.this,"密码错误", Toast.LENGTH_SHORT).show();
                                }else if(state.equals("NOEXIST")){
                                    Toast.makeText(MainActivity.this,"账号不存在", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, et_username.getText().toString(), et_password.getText().toString());
                    }
                }else {
                    return;
                }

            }
        });
    }
}
