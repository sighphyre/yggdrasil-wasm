package org.example;

import com.dylibso.chicory.runtime.ExportFunction;
import com.dylibso.chicory.wasm.Parser;
import com.dylibso.chicory.runtime.Store;
import com.dylibso.chicory.runtime.HostFunction;
import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.runtime.Memory;
import com.dylibso.chicory.wasm.types.ValueType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.security.SecureRandom;
import java.util.List;

public class WasmEngine {

    private Instance wasmModule;
    private long enginePointer;
    private ExportFunction alloc;
    private ExportFunction dealloc;
    private Memory memory;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize to JSON", e);
        }
    }

    public WasmEngine() {
        var store = new Store();

        // this is better expressed as a host module but that requires some fiddling to
        // get working and I want to make progress on the spike
        var fillRandom = new HostFunction(
                "env",
                "fill_random",
                List.of(ValueType.I32, ValueType.I32),
                List.of(ValueType.I32),
                (Instance instance, long... args) -> {
                    int ptr = (int) args[0];
                    int len = (int) args[1];

                    if (len <= 0 || ptr < 0) {
                        return new long[] { 1 };
                    }

                    byte[] randomBytes = new byte[len];
                    new SecureRandom().nextBytes(randomBytes);

                    instance.memory().write(ptr, randomBytes);

                    return new long[] { 0 };
                });

        store.addFunction(fillRandom);

        this.wasmModule = store.instantiate("yggdrasil", Parser
                .parse(new File("/home/simon/dev/yggdrasil/target/wasm32-unknown-unknown/release/pure_wasm.wasm")));
        ExportFunction newEngine = wasmModule.export("new_engine");
        this.alloc = wasmModule.export("alloc");
        this.dealloc = wasmModule.export("dealloc");
        this.memory = wasmModule.memory();

        this.enginePointer = newEngine.apply()[0];
    }

    public void takeState(String message) {

        int len = message.getBytes().length;

        int ptr = (int) alloc.apply(len)[0];
        memory.writeString(ptr, message);

        ExportFunction takeState = wasmModule.export("take_state");

        int resultPtr = (int)takeState.apply(this.enginePointer, ptr, len)[0];

        String result = memory.readCString(resultPtr);

        System.out.println(result);
        dealloc.apply(ptr, len);
    }

    public boolean checkEnabled(String toggleName, WasmContext context) throws JsonMappingException, JsonProcessingException {
        int toggleNameLen = toggleName.getBytes().length;
        int toggleNamePtr = (int) alloc.apply(toggleNameLen)[0];
        memory.writeString(toggleNamePtr, toggleName);

        ExportFunction checkEnabled = wasmModule.export("check_enabled");
        String contextString = toJson(context);

        int contextLen = contextString.getBytes().length;
        int contextPtr = (int) alloc.apply(contextLen)[0];
        memory.writeString(contextPtr, contextString);

        int resultPtr = (int)checkEnabled.apply(this.enginePointer, toggleNamePtr, toggleNameLen, contextPtr, contextLen)[0];

        String result = memory.readCString(resultPtr);
        // WasmResponse<Boolean> response = objectMapper.readValue(result, WasmResponse.class);

        dealloc.apply(toggleNamePtr, toggleNameLen);
        // return response.value;
        return false;
    }
}
