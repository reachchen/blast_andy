package retrofit2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Retrofit新的Builder {@link Retrofit.Builder}
 * @author xuhaijiang on 2018/10/9.
 */
public class RetrofitBuilder {
    private Platform platform;
    private okhttp3.Call.Factory callFactory;
    private HttpUrl baseUrl;
    private List<Converter.Factory> converterFactories = new ArrayList<>();
    private List<CallAdapter.Factory> adapterFactories = new ArrayList<>();
    private Executor callbackExecutor;
    private boolean validateEagerly;

    public Retrofit build() {
        if (baseUrl == null) {
            throw new IllegalStateException("Base URL required.");
        }

        okhttp3.Call.Factory callFactory = this.callFactory;
        if (callFactory == null) {
            callFactory = new OkHttpClient();
        }

        Executor callbackExecutor = this.callbackExecutor;
        if (callbackExecutor == null) {
            callbackExecutor = platform.defaultCallbackExecutor();
        }

        // Make a defensive copy of the adapters and add the default Call adapter.
        List<CallAdapter.Factory> adapterFactories = new ArrayList<>(this.adapterFactories);
        adapterFactories.add(platform.defaultCallAdapterFactory(callbackExecutor));

        // Make a defensive copy of the converters.
        List<Converter.Factory> converterFactories = new ArrayList<>(this.converterFactories);

        return new Retrofit(callFactory, baseUrl, converterFactories, adapterFactories,
                callbackExecutor, validateEagerly);
    }
}
