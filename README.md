You will need to generate the flat buffer files from Yggdrasil to update the defs if you're working on this.

You absolutely need flatc version 23.1.21.

You can generate them like so, assuming Yggdrasil is in a folder below the root of this project.

`flatc --java -o src/main/java ../../yggdrasil/flat-buffer-defs/enabled-message.fbs`
`flatc --java -o src/main/java ../../yggdrasil/flat-buffer-defs/enabled-response.fbs`


