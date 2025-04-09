package org.example;

import com.dylibso.chicory.runtime.ExportFunction;
import com.dylibso.chicory.wasm.Parser;
import com.dylibso.chicory.runtime.Store;
import com.dylibso.chicory.runtime.HostFunction;
import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.runtime.Memory;
import com.dylibso.chicory.wasm.types.ValueType;

import java.io.File;
import java.security.SecureRandom;
import java.util.List;

public class UnleashEngine {

    private Instance wasmModule;
    private long enginePointer;
    private ExportFunction alloc;
    private ExportFunction dealloc;

    public UnleashEngine() {
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

        this.enginePointer = newEngine.apply()[0];
    }

    public void takeState(String message) {

        Memory memory = wasmModule.memory();
        int len = message.getBytes().length;

        int ptr = (int) alloc.apply(len)[0];
        // We can now write the message to the module's memory:
        memory.writeString(ptr, message);

        ExportFunction takeState = wasmModule.export("take_state");
        ExportFunction getReturnLen = wasmModule.export("get_return_len");

        int resultPtr = (int)takeState.apply(this.enginePointer, ptr, len)[0];
        int resultLen = (int) getReturnLen.apply()[0];

        dealloc.apply(ptr, len);

        String result = memory.readString(resultPtr, resultLen);
        System.out.println("WASM returned: " + result);

        dealloc.apply(resultPtr, resultLen);
    }
}
