package com.example.android.networkconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.networkconnect.com.server.data.DataManager;
import com.example.android.networkconnect.com.server.data.datas.Keyword;
import com.example.android.networkconnect.com.server.data.datas.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class CreateEditActivity extends AppCompatActivity implements View.OnClickListener {


    private final static int VERTICAL = 1;
    MyAdapter adapter;
    Person p = new Person();
    ArrayList<BufferKeyword> buf = new ArrayList<>();
    Button b;
    EditText name;


    private class BufferKeyword{

        Integer indexFromPersonKeyword;
        Keyword keyword;

        public BufferKeyword(Integer indexFromPersonKeyword, Keyword keyword) {
            this.indexFromPersonKeyword = indexFromPersonKeyword;
            this.keyword = keyword;
        }

        public Integer getIndexFromPersonKeyword() {
            return indexFromPersonKeyword;
        }

        public void setIndexFromPersonKeyword(Integer indexFromPersonKeyword) {
            this.indexFromPersonKeyword = indexFromPersonKeyword;
        }

        public Keyword getKeyword() {
            return keyword;
        }

        public void setKeyword(Keyword keyword) {
            this.keyword = keyword;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit);


        RecyclerView itemCityRecyclerView = (RecyclerView) findViewById(R.id.recycler_view); //Найдем наш RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //Создадим LinearLayoutManager
        layoutManager.setOrientation(VERTICAL);//Обозначим ориентацию
        itemCityRecyclerView.setLayoutManager(layoutManager);//Назначим нашему RecyclerView созданный ранее layoutManager
        adapter = new MyAdapter();
        itemCityRecyclerView.setAdapter(adapter);
        //Назначим нашему RecyclerView адаптер

//        p.setId(12);
//        p.setName("Василий");
//        Keyword k = new Keyword();
//        k.setName("ВитекВитек");
//        k.setId(34);
//        Keyword k1 = new Keyword();
//        k1.setName("aaaa");
//        k1.setId(35);
//        Keyword k2 = new Keyword();
//        k2.setName("bbbbb");
//        k2.setId(36);
//        List<Keyword> arr = new ArrayList<>();
////        arr.add(k);
////        arr.add(k1);
////        arr.add(k2);

        Intent intent = getIntent();
        p = intent.getParcelableExtra("test");

        ArrayList<Keyword> addingArr = p.getKeywordList();
        if (addingArr!=null){
            for (int i = 0; i < addingArr.size(); i++)
                buf.add(new BufferKeyword(i, addingArr.get(i)));
        }
        buf.add(new BufferKeyword(null,new Keyword()));

        name = (EditText) findViewById(R.id.name);
        name.setText(p.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //name.setText(charSequence.toString());
                p.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //name.setText(editable.toString());
                //System.out.println(name.getText());
            }
        });

        b = (Button) findViewById(R.id.add_button);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == b) {

            if (TextUtils.isEmpty(name.getText().toString())){
                name.setError(getString(R.string.error_field_required));
                return;
            }
            handleBufArrayResult();

            //dataManager.addOrEditPerson(p);
            //close activity

        }

    }

    private void handleBufArrayResult() {

        String lastName = buf.get(buf.size()-1).getKeyword().getName();
        if (lastName == null || lastName.equals("")) buf.remove(buf.size() - 1);

        for(int i = 0; i < buf.size(); i++){
            Keyword k = buf.get(i).getKeyword();
            Integer index = buf.get(i).getIndexFromPersonKeyword();
            if (index == null) p.addKeyword(k);
            else p.getKeywordList().get(index).setName(k.getName());
        }
    }


//    private void hadleBufArrayResult() {
//
//        String lastName = buf.get(buf.size()-1).getName();
//        if (lastName == null || lastName.equals("")) buf.remove(buf.size() - 1);
//        if (p.getId()== null || p.getKeywordList() == null || p.getKeywordList().isEmpty()) {
//            if (buf.size() >= 1) {
//                p.setKeywordList(buf);
//                return;
//            }
//        }
//        if (buf.size() == 0) {
//            if (p.getKeywordList() == null || p.getKeywordList().isEmpty()) return;
//            for(Keyword k: p.getKeywordList())
//                k.setToDelete(true);
//            return;
//        }
//
//        ArrayList<Keyword> arrayList = p.getKeywordList();
//        for (Keyword k: buf){
//            if (k.getId() == null && k.getName()!=null && !k.getName().equals("")) {
//                arrayList.add(k);
//                continue;
//            }
//            for(Keyword per: arrayList){
//                if (k.getId().equals(per.getId())) per.setName(k.getName());
//            }
//        }
//        for(Keyword arr: arrayList){
//            if (arr.getId()!=null) {
//                if(!existInBuf(arr)) arr.setToDelete(true);
//            }
//        }
//        p.setKeywordList(arrayList);
//    }

//    boolean existInBuf(Keyword k){
//
//        for(Keyword m: buf){
//            if (k.getId().equals(m.getId())) return true;
//        }
//        return false;
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private EditText categoryNameTextView;
        public MyCustomEditTextListener myCustomEditTextListener;
        public MyCustomOnClickListener myCustomOnClickListener;
        private ImageButton imageButton;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent, MyCustomEditTextListener myCustomEditTextListener, MyCustomOnClickListener myCustomOnClickListener){
            super(inflater.inflate(R.layout.view_item,parent,false));
            this.myCustomEditTextListener = myCustomEditTextListener;
            categoryNameTextView = (EditText) itemView.findViewById(R.id.category_item_text);
            imageButton = (ImageButton) itemView.findViewById(R.id.del_button);
            imageButton.setVisibility(View.GONE);
            this.categoryNameTextView.addTextChangedListener(myCustomEditTextListener);
            categoryNameTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        String last = buf.get(buf.size()-1).getKeyword().getName();
                        if (last!=null && !last.equals("")) buf.add(new BufferKeyword(null,new Keyword()));
                        imageButton.setVisibility(View.GONE);
                    }
                    else imageButton.setVisibility(View.VISIBLE);
                }
            });
            this.myCustomOnClickListener = myCustomOnClickListener;
            imageButton.setOnClickListener(myCustomOnClickListener);
        }

        void bind(int position) {
            String category = buf.get(position).getKeyword().getName();
            categoryNameTextView.setText(category);
        }

    }

    //Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(CreateEditActivity.this);
            return new MyViewHolder(inflater, parent, new MyCustomEditTextListener(), new MyCustomOnClickListener());
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.myCustomEditTextListener.updatePosition(position);
            holder.myCustomOnClickListener.updatePosition(position);
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return buf.size();
        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            buf.get(position).getKeyword().setName(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //System.out.println(editable.toString());
        }
    }

    private class MyCustomOnClickListener implements View.OnClickListener {

        private int position;
        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (buf.size() > 1) {
                Integer index = buf.get(position).getIndexFromPersonKeyword();
                if (index!=null) p.getKeywordList().get(index).setToDelete(true);
                buf.remove(position);
            }
            adapter.notifyDataSetChanged();
        }
    }

}
