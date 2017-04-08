package khoavin.sillylearningenglish.Depdency;

import android.app.Application;

import khoavin.sillylearningenglish.Depdency.DaggerDependencyComponent;

public class  SillyApp extends Application {

    private DependencyComponent mDependencyComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mDependencyComponent = DaggerDependencyComponent.builder()
                .dependencyModule(new DependencyModule())
                .build();
    }

    public DependencyComponent getDependencyComponent() {

        return this.mDependencyComponent;
    }
}
