// automatically generated by the FlatBuffers compiler, do not modify

package messaging;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.BooleanVector;
import com.google.flatbuffers.ByteVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.DoubleVector;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.FloatVector;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.ShortVector;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Struct;
import com.google.flatbuffers.Table;
import com.google.flatbuffers.UnionVector;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class Context extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_23_1_21(); }
  public static Context getRootAsContext(ByteBuffer _bb) { return getRootAsContext(_bb, new Context()); }
  public static Context getRootAsContext(ByteBuffer _bb, Context obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public Context __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String toggleName() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer toggleNameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer toggleNameInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  public String userId() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer userIdAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer userIdInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  public String sessionId() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer sessionIdAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public ByteBuffer sessionIdInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 1); }
  public String environment() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer environmentAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public ByteBuffer environmentInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 10, 1); }
  public String appName() { int o = __offset(12); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer appNameAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }
  public ByteBuffer appNameInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 12, 1); }
  public String currentTime() { int o = __offset(14); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer currentTimeAsByteBuffer() { return __vector_as_bytebuffer(14, 1); }
  public ByteBuffer currentTimeInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 14, 1); }
  public String remoteAddress() { int o = __offset(16); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer remoteAddressAsByteBuffer() { return __vector_as_bytebuffer(16, 1); }
  public ByteBuffer remoteAddressInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 16, 1); }
  public messaging.PropertyEntry properties(int j) { return properties(new messaging.PropertyEntry(), j); }
  public messaging.PropertyEntry properties(messaging.PropertyEntry obj, int j) { int o = __offset(18); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int propertiesLength() { int o = __offset(18); return o != 0 ? __vector_len(o) : 0; }
  public messaging.PropertyEntry propertiesByKey(String key) { int o = __offset(18); return o != 0 ? messaging.PropertyEntry.__lookup_by_key(null, __vector(o), key, bb) : null; }
  public messaging.PropertyEntry propertiesByKey(messaging.PropertyEntry obj, String key) { int o = __offset(18); return o != 0 ? messaging.PropertyEntry.__lookup_by_key(obj, __vector(o), key, bb) : null; }
  public messaging.PropertyEntry.Vector propertiesVector() { return propertiesVector(new messaging.PropertyEntry.Vector()); }
  public messaging.PropertyEntry.Vector propertiesVector(messaging.PropertyEntry.Vector obj) { int o = __offset(18); return o != 0 ? obj.__assign(__vector(o), 4, bb) : null; }

  public static int createContext(FlatBufferBuilder builder,
      int toggleNameOffset,
      int userIdOffset,
      int sessionIdOffset,
      int environmentOffset,
      int appNameOffset,
      int currentTimeOffset,
      int remoteAddressOffset,
      int propertiesOffset) {
    builder.startTable(8);
    Context.addProperties(builder, propertiesOffset);
    Context.addRemoteAddress(builder, remoteAddressOffset);
    Context.addCurrentTime(builder, currentTimeOffset);
    Context.addAppName(builder, appNameOffset);
    Context.addEnvironment(builder, environmentOffset);
    Context.addSessionId(builder, sessionIdOffset);
    Context.addUserId(builder, userIdOffset);
    Context.addToggleName(builder, toggleNameOffset);
    return Context.endContext(builder);
  }

  public static void startContext(FlatBufferBuilder builder) { builder.startTable(8); }
  public static void addToggleName(FlatBufferBuilder builder, int toggleNameOffset) { builder.addOffset(0, toggleNameOffset, 0); }
  public static void addUserId(FlatBufferBuilder builder, int userIdOffset) { builder.addOffset(1, userIdOffset, 0); }
  public static void addSessionId(FlatBufferBuilder builder, int sessionIdOffset) { builder.addOffset(2, sessionIdOffset, 0); }
  public static void addEnvironment(FlatBufferBuilder builder, int environmentOffset) { builder.addOffset(3, environmentOffset, 0); }
  public static void addAppName(FlatBufferBuilder builder, int appNameOffset) { builder.addOffset(4, appNameOffset, 0); }
  public static void addCurrentTime(FlatBufferBuilder builder, int currentTimeOffset) { builder.addOffset(5, currentTimeOffset, 0); }
  public static void addRemoteAddress(FlatBufferBuilder builder, int remoteAddressOffset) { builder.addOffset(6, remoteAddressOffset, 0); }
  public static void addProperties(FlatBufferBuilder builder, int propertiesOffset) { builder.addOffset(7, propertiesOffset, 0); }
  public static int createPropertiesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startPropertiesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endContext(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }
  public static void finishContextBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedContextBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public Context get(int j) { return get(new Context(), j); }
    public Context get(Context obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

