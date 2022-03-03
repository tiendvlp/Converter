package com.prm.converter.view.convert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.prm.converter.R;

public class MainActivity extends AppCompatActivity implements MainScreenComponentProvider {
    private FrameLayout inputContainer;
    private FrameLayout outputContainer;
    private ConvertInputFragment convertInputFragment;
    private ConvertOutputFragment convertOutputFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        bindFragments();
        addEvents();
    }

    private void bindFragments() {
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        convertInputFragment = new ConvertInputFragment();
        convertOutputFragment = new ConvertOutputFragment();
        transaction.add(R.id.inputContainer, convertInputFragment, ConvertInputFragment.class.getSimpleName());
        transaction.add(R.id.outputContainer, convertOutputFragment, ConvertOutputFragment.class.getSimpleName());
        transaction.commit();
    }

    private void addControls() {
        inputContainer = findViewById(R.id.inputContainer);
        outputContainer = findViewById(R.id.outputContainer);
    }

    private void addEvents() {

    }

    @Override
    public ConvertInputScreen getConvertInputScreen() {
        return convertInputFragment;
    }

    @Override
    public ConvertOutputScreen getConvertOutputScreen() {
        return convertOutputFragment;
    }
}