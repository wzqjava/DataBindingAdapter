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

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Toast;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.BindingViewHolder;
import com.github.markzhai.recyclerview.MultiTypeAdapter;
import com.github.markzhai.recyclerview.demo.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding mBinding;
    private MultiTypeAdapter mMultiTypeAdapter;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_EMPLOYEE = 1;
    private static final int VIEW_TYPE_EMPLOYER = 2;

    private static final ArrayList<Bean1> list1 = new ArrayList<>();
    private static final ArrayList<Bean2> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        mMultiTypeAdapter = new MultiTypeAdapter(this);
        mMultiTypeAdapter.setPresenter(new DemoAdapterPresenter());

        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_HEADER, R.layout.item_header);
        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_EMPLOYEE, R.layout.item2_1);
        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_EMPLOYER, R.layout.item2_2);
        mBinding.setLayoutManager(new LinearLayoutManager(this));

        mMultiTypeAdapter.add(null, VIEW_TYPE_HEADER);
        mMultiTypeAdapter.addAll(list1, VIEW_TYPE_EMPLOYEE);
        mMultiTypeAdapter.addAll(list2, VIEW_TYPE_EMPLOYER);

        final List<Object> data = new ArrayList<Object>();
        data.addAll(list1);
        data.addAll(list2);
        Collections.shuffle(data);

        mMultiTypeAdapter.addAll(data, new MultiTypeAdapter.MultiViewTyper() {
            @Override
            public int getViewType(Object item) {
                if (item instanceof Bean2) {
                    return VIEW_TYPE_EMPLOYER;
                }

                if (item instanceof Bean1) {
                    return VIEW_TYPE_EMPLOYEE;
                }

                return 0;
            }
        });
        mBinding.setAdapter(mMultiTypeAdapter);
        mMultiTypeAdapter.setPresenter(new DemoAdapterPresenter());
    }

    private  void initData() {
        Bean1 model1 = new Bean1("markzhai", 26);
        Bean1 model2 = new Bean1("dim", 25);
        Bean1 model3 = new Bean1("abner", 25);
        Bean1 model4 = new Bean1("cjj", 26);

        list1.add(model1);
        list1.add(model2);
        list1.add(model3);
        list1.add(model4);

        Bean2 model5 = new Bean2("boss1", 30,
                "https://avatars2.githubusercontent.com/u/1106500?v=3&s=150", "CEO");

        Bean2 model6 = new Bean2("boss2", 31,
                "https://avatars3.githubusercontent.com/u/11629640?v=3&s=150", "CTO");

        Bean2 model7 = new Bean2("boss3", 38,
                "https://avatars2.githubusercontent.com/u/1468623?v=3&s=150", "CAO");

        list2.add(model5);
        list2.add(model6);
        list2.add(model7);
    }


    public class DemoAdapterPresenter implements BaseViewAdapter.Presenter {
        public void onItemClick(Bean1 model) {
            Toast.makeText(MainActivity2.this, "employee " + model.name, Toast.LENGTH_SHORT).show();

        }

        public void onItemClick(Bean2 model) {
            Toast.makeText(MainActivity2.this, "employer " + model.name, Toast.LENGTH_SHORT).show();
        }
    }

    public class DemoAdapterDecorator implements BaseViewAdapter.Decorator {

        @Override
        public void decorator(BindingViewHolder holder, int position, int viewType) {
            // you may do something according to position or view type
        }
    }
}
