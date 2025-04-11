package org.example;

import org.openjdk.jmh.annotations.*;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.engine.UnleashEngine;
import io.getunleash.repository.ToggleBootstrapProvider;
import io.getunleash.engine.Context;
import io.getunleash.UnleashContext;
import io.getunleash.util.UnleashConfig;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@Fork(1)
@State(Scope.Thread)
public class Benchmarks {

    private WasmEngine engine;
    private WasmContext context;

    private UnleashEngine ffiEngine;
    private Context ffiContext;

    // 9.3.2 client
    private Unleash pureClient;
    private UnleashContext pureContext;

    private static final String FEATURE_NAME = "test-flag";

    @Setup(Level.Trial)
    public void setup() throws Exception {

        engine = new WasmEngine();
        context = new WasmContext();

        ffiEngine = new UnleashEngine();
        ffiContext = new Context();

        context.userId = "7";
        context.properties = new HashMap<String, String>();

        String path = "../../../yggdrasil/test-data/delta_base.json";
        String json = loadBootstrap(path);

        UnleashConfig config = UnleashConfig.builder()
                .appName("my.java-app")
                .unleashAPI("http://localhost:4242/api/")
                .apiKey("does-not-matter")
                .toggleBootstrapProvider(new ToggleBootstrapProvider() {
                    @Override
                    public String read() {
                        return json;
                    }
                })
                .build();

        pureClient = new DefaultUnleash(config);


        pureContext = UnleashContext.builder()
                .userId("user@mail.com").build();

        engine.takeState(json);
        ffiEngine.takeState(json);
    }

    private String loadBootstrap(String path) {
        try {
            return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public boolean benchWasm() throws Exception {
        return engine.checkEnabled(FEATURE_NAME, context);
    }

    @Benchmark
    public boolean benchFFI() throws Exception {
        return ffiEngine.isEnabled(FEATURE_NAME, ffiContext);
    }

    @Benchmark
    public boolean benchPureClient() {
        return pureClient.isEnabled(FEATURE_NAME, pureContext);
    }
}