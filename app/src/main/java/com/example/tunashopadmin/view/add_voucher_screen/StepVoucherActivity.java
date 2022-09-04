package com.example.tunashopadmin.view.add_voucher_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

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
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.step_container, stepOneFragment, "1").commit();
    }

    public void nextStep(int stepIndex) {
        FragmentManager manager = getSupportFragmentManager();
        switch (stepIndex) {
            case 0:
                manager.beginTransaction().replace(R.id.step_container, stepTwoFragment).commit();
                binding.stepView.go(1, true);
                break;
            case 1:
                manager.beginTransaction().replace(R.id.step_container, stepThreeFragment).commit();
                binding.stepView.go(2, true);
                break;
        }
    }

    public String getType() {
        return type;
    }
}