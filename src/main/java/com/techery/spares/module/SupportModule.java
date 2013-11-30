package com.techery.spares.module;

import com.techery.spares.utils.AnnotationsHelper;
import com.techery.spares.utils.ObjectStateHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class SupportModule {
    @Provides
    @Singleton
    AnnotationsHelper provideAnnotationsHelper() {
        return new AnnotationsHelper();
    }

    @Provides
    @Singleton
    ObjectStateHelper provideAnnotationsHelper(AnnotationsHelper annotationsHelper) {
        return new ObjectStateHelper(annotationsHelper);
    }
}
