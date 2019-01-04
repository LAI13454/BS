package com.sensordata.home.sensordataview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;         //注册按钮
    private EditText etUserName,etPassword,etPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        etUserName = findViewById(R.id.etUserNameRegister);
        etPassword = findViewById(R.id.etPasswordRegister);
        etPassword2 = findViewById(R.id.etPassword2Register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //注册按钮按下
                if(etUserName.getText().toString().length() != 0){
                    //用户名不为空,判断两次密码是否一致
                    if(etPassword.getText().toString().equals(etPassword2.getText().toString())){
                        //连接数据库

                        MySQLOperation.userRegisterSQL(new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                Bundle data = msg.getData();
                                String state = data.getString("state");
                                if(state.equals("OK")){
                                    System.out.println("注册成功");
                                    Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    System.out.println("用户已注册");
                                    Toast.makeText(RegisterActivity.this,"用户名已注册,请更换用户名", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },etUserName.getText().toString(),etPassword.getText().toString());

                    }else{
                        Toast.makeText(RegisterActivity.this,"两次密码输入不一致,请重新输入密码",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"用户名为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
