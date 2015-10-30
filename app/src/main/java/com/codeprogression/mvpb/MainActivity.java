package com.codeprogression.mvpb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codeprogression.mvpb.core.PerActivity;

public class MainActivity extends AppCompatActivity {


    @PerActivity
    @dagger.Component()
    public interface Component{
        void inject(MainActivity view);
        void inject(MainView view);
    }

    public static Component component(){
        return component;
    }

    private static Component component;

    private void buildComponent() {
        if (component == null) {
            component = DaggerMainActivity_Component.builder()
                    .build();
            component.inject(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildComponent();
        setContentView(R.layout.main_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        component = null;
    }
}
