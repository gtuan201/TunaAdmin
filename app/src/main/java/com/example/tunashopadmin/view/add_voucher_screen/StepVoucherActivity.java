package com.example.tunashopadmin.view.add_voucher_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.tunashopadmin.R;
import com.example.tunashopadmin.databinding.ActivityStepVoucherBinding;
import com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher.StepOneFragment;
import com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher.StepThreeFragment;
import com.example.tunashopadmin.view.add_voucher_screen.fragment_step_add_voucher.StepTwoFragment;
import com.shuhart.stepview.StepView;

public class StepVoucherActivity extends AppCompatActivity {
    private ActivityStepVoucherBinding binding;
    private final StepOneFragment stepOneFragment = new StepOneFragment();
    private final StepTwoFragment stepTwoFragment = new StepTwoFragment();
    private final StepThreeFragment stepThreeFragment = new StepThreeFragment();
    private final FragmentManager manager = getSupportFragmentManager();
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_step_voucher);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        binding.stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_longAnimTime))
                .commit();
        manager.beginTransaction().add(R.id.step_container, stepOneFragment).commit();
        manager.beginTransaction().add(R.id.step_container, stepTwoFragment).hide(stepTwoFragment).commit();
        manager.beginTransaction().add(R.id.step_container, stepThreeFragment).hide(stepThreeFragment).commit();
    }

    public void nextStep() {
        FragmentManager manager = getSupportFragmentManager();
        if (stepOneFragment.isVisible()){
            manager.beginTransaction().hide(stepOneFragment).show(stepTwoFragment).commit();
            binding.stepView.go(1, true);
        }
        if (stepTwoFragment.isVisible()){
            manager.beginTransaction().hide(stepTwoFragment).show(stepThreeFragment).commit();
            binding.stepView.go(2, true);
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBackPressed() {
        if(stepThreeFragment.isVisible()){
            manager.beginTransaction().show(stepTwoFragment).hide(stepThreeFragment).commit();
            binding.tvStep.setText("Tạo mã giảm giá - Bước 2");
            binding.stepView.go(1, true);
        }
        else if (stepTwoFragment.isVisible()){
            manager.beginTransaction().show(stepOneFragment).hide(stepTwoFragment).commit();
            binding.tvStep.setText("Tạo mã giảm giá - Bước 1");
            binding.stepView.go(0, true);
        }
        else if (stepOneFragment.isVisible()){
            finish();
        }
    }

    public void setTextStep(String step){
        binding.tvStep.setText(step);
    }

    public String getType() {
        return type;
    }
}