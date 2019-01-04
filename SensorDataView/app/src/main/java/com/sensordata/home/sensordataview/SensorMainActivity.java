package com.sensordata.home.sensordataview;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SensorMainActivity extends AppCompatActivity {
    private EditText et_add_dev_name,et_add_dev_mac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.app_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_dev:
                System.out.println("添加设备被点击");
                final AlertDialog.Builder builder = new AlertDialog.Builder(SensorMainActivity.this);
                View view = LayoutInflater.from(SensorMainActivity.this).inflate(R.layout.dialog_add_dev,null);

                final EditText et_add_dev_name = view.findViewById(R.id.etAddDevName);;
                final EditText et_add_dev_mac = view.findViewById(R.id.etAddDevMAC);
                builder.setTitle("绑定设备").setView(view).setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(et_add_dev_name.getText().toString());
                        System.out.println("绑定设备确定被点击");
                        if(et_add_dev_name.getText().toString().length() == 0){
                            Toast.makeText(SensorMainActivity.this,"设备名称为空", Toast.LENGTH_SHORT).show();
                        }else{
                            if(et_add_dev_mac.getText().toString().length() == 0){
                                Toast.makeText(SensorMainActivity.this,"设备MAC为空", Toast.LENGTH_SHORT).show();
                            }else{
                                char[] arr = et_add_dev_mac.getText().toString().toCharArray();
                                if((arr[2] == ':') && (arr[5] == ':') && (arr[8] == ':') && (arr[11] == ':') && (arr[14] == ':')){
                                    System.out.println("格式正确");
                                    final Data userNameData = (Data)getApplication();
                                    System.out.println(userNameData.getUserName());
                                    MySQLOperation.userDevAdd(new Handler(){
                                                                  @Override
                                                                  public void handleMessage(Message msg) {
                                                                      super.handleMessage(msg);
                                                                      Bundle data = msg.getData();
                                                                      String state = data.getString("state");
                                                                      if(state.equals("OK")){
                                                                          System.out.println("添加设备成功");
                                                                          Toast.makeText(SensorMainActivity.this,"添加设备成功", Toast.LENGTH_SHORT).show();
                                                                      }else{
                                                                          System.out.println("添加设备失败");
                                                                          Toast.makeText(SensorMainActivity.this,"添加设备失败", Toast.LENGTH_SHORT).show();
                                                                      }
                                                                  }
                                                              },userNameData.getUserName(),et_add_dev_name.getText().toString(),
                                            et_add_dev_mac.getText().toString());
                                }else{
                                    System.out.println("格式错误");
                                    Toast.makeText(SensorMainActivity.this,"MAC格式错误xx:xx:xx:xx:xx:xx", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("绑定设备取消被点击");
                    }
                });
                builder.show();
                break;
            case R.id.menu_about:
                System.out.println("关于被点击");
                break;
            default:
                System.out.println("MENU被点击");
                break;
        }
        return true;
    }
}
