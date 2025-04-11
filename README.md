This is a very barebones spike to test the idea of porting the Unleash Yggdrasil FFI code to a pure WASM implementation.

This repository is intended as reference code and is not expected to be production ready or even runnable. If you do want to run this code, you'll need to compile subproject pure-wasm of the Unleash Yggdrasil project against the wasm32-unknown-unknown target. That needs to be done on this branch of Yggdrasil: https://github.com/Unleash/yggdrasil/tree/pure-wasm-impl.

This project also expects Yggdrasil to be one level below it, currently a few paths are hardcoded to expect this folder structure:

/yggdrasil
/some-folder/yggdrasil-wasm

If you want to make changes to the messaging protocol, you'll need to compile the Flatbuffer files with flatc version 23.1.21.

You can generate them like so, assuming Yggdrasil is in a folder below the root of this project.

`flatc --java -o src/main/java ../../yggdrasil/flat-buffer-defs/enabled-message.fbs`
`flatc --java -o src/main/java ../../yggdrasil/flat-buffer-defs/enabled-response.fbs`
