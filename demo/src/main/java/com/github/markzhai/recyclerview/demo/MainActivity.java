/*
 * Copyright (C) 2016 MarkZhai (http://zhaiyifan.cn).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.markzhai.recyclerview.demo;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.BindingViewHolder;
import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.github.markzhai.recyclerview.demo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private SingleTypeAdapter<Bean1> mSingleTypeAdapter;
    private static final ArrayList<Bean1> list1 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mSingleTypeAdapter = new SingleTypeAdapter<>(this, R.layout.item1);
        mSingleTypeAdapter.setDecorator(new DemoAdapterDecorator());
        mBinding.setLayoutManager(new LinearLayoutManager(this));
        mBinding.setAdapter(mSingleTypeAdapter);
        mSingleTypeAdapter.addAll(list1);
        final List<Object> data = new ArrayList<Object>();
        data.addAll(list1);
        Collections.shuffle(data);

        mSingleTypeAdapter.setPresenter(new DemoAdapterPresenter());
        // you can use the built-in presenter
//        mSingleTypeAdapter.setPresenter(new SingleTypeAdapter.Presenter<Bean1>() {
//
//            @Override
//            public void onItemClick(Bean1 model) {
//                Toast.makeText(MainActivity.this, model.name, Toast.LENGTH_SHORT).show();
//            }
//        });
    }



    public void click(View view) {
        startActivity(new Intent(this,MainActivity2.class));
    }


    public class DemoAdapterPresenter implements BaseViewAdapter.Presenter {
        public void onItemClick(Bean1 model) {
            Toast.makeText(MainActivity.this,  model.name, Toast.LENGTH_SHORT).show();
        }

        public void onItemClick(Bean2 model) {
            Toast.makeText(MainActivity.this,  model.name, Toast.LENGTH_SHORT).show();
        }
    }

    public class DemoAdapterDecorator implements BaseViewAdapter.Decorator {

        @Override
        public void decorator(BindingViewHolder holder, int position, int viewType) {
            // you may do something according to position or view type
        }
    }
    public void initData() {
        Bean1 model1 = new Bean1("markzhai", 26);
        Bean1 model2 = new Bean1("dim", 25);
        Bean1 model3 = new Bean1("abner", 25);
        Bean1 model4 = new Bean1("cjj", 26);
        list1.add(model1);
        list1.add(model2);
        list1.add(model3);
        list1.add(model4);
    }

}
