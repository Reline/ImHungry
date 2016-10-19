package com.github.funnygopher.imhungry.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.flow.MainKeyChanger;
import com.github.funnygopher.imhungry.flow.keys.FindFoodKey;
import com.github.funnygopher.imhungry.flow.keys.MyPlacesKey;
import com.github.funnygopher.imhungry.injection.DaggerService;
import com.github.funnygopher.imhungry.injection.components.AppComponent;
import com.github.funnygopher.imhungry.injection.components.DaggerMainComponent;
import com.github.funnygopher.imhungry.injection.components.MainComponent;
import com.github.funnygopher.imhungry.mortarflow.DaggerServiceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import flow.Flow;
import flow.KeyDispatcher;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_tablayout)
    TabLayout tabLayout;

    private MortarScope mortarScope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mortarScope = MortarScope.findChild(getApplicationContext(), getClass().getName());

        if (mortarScope == null) {
            MainComponent component = DaggerMainComponent.builder()
                    .appComponent(DaggerService.<AppComponent>getDaggerComponent(getApplicationContext()))
                    .build();

            mortarScope = MortarScope.buildChild(getApplicationContext())
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME, component)
                    .build(getClass().getName());
        }

        DaggerService.<MainComponent>getDaggerComponent(this).inject(this);

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Object key;
                switch (tab.getPosition()) {
                    default:
                    case 0:
                        key = new FindFoodKey();
                        break;
                    case 1:
                        key = new MyPlacesKey();
                        break;
                }
                Flow.get(getBaseContext()).set(key);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context baseContext) {
        baseContext = Flow.configure(baseContext, this)
                .addServicesFactory(new DaggerServiceFactory(this))
                .dispatcher(KeyDispatcher.configure(this, new MainKeyChanger(this)).build())
                .defaultKey(new FindFoodKey())
                .install();
        super.attachBaseContext(baseContext);
    }

    @Override
    public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            if (mortarScope != null) {
                mortarScope.destroy();
            }
        }

        super.onDestroy();
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        // see: https://github.com/square/mortar/issues/155
        if (getApplication() == null) {
            return super.getSystemService(name);
        }

        Object service = Flow.getService(name, getApplicationContext());
        if (service == null && mortarScope != null && mortarScope.hasService(name)) {
            service = mortarScope.getService(name);
        }

        return service != null ? service : super.getSystemService(name);
    }

}
