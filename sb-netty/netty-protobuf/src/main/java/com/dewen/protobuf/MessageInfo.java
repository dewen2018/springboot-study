// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package com.dewen.protobuf;

public final class MessageInfo {
  private MessageInfo() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Message)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string requestId = 1;</code>
     * @return The requestId.
     */
    java.lang.String getRequestId();
    /**
     * <code>string requestId = 1;</code>
     * @return The bytes for requestId.
     */
    com.google.protobuf.ByteString
        getRequestIdBytes();

    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    java.lang.String getName();
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <pre>
     * int32 id = 1;
     * </pre>
     *
     * <code>.Message.CommandType cmd = 3;</code>
     * @return The enum numeric value on the wire for cmd.
     */
    int getCmdValue();
    /**
     * <pre>
     * int32 id = 1;
     * </pre>
     *
     * <code>.Message.CommandType cmd = 3;</code>
     * @return The cmd.
     */
    com.dewen.protobuf.MessageInfo.Message.CommandType getCmd();

    /**
     * <code>string content = 4;</code>
     * @return The content.
     */
    java.lang.String getContent();
    /**
     * <code>string content = 4;</code>
     * @return The bytes for content.
     */
    com.google.protobuf.ByteString
        getContentBytes();
  }
  /**
   * Protobuf type {@code Message}
   */
  public static final class Message extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Message)
      MessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Message.newBuilder() to construct.
    private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Message() {
      requestId_ = "";
      name_ = "";
      cmd_ = 0;
      content_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Message();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.dewen.protobuf.MessageInfo.internal_static_Message_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.dewen.protobuf.MessageInfo.internal_static_Message_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.dewen.protobuf.MessageInfo.Message.class, com.dewen.protobuf.MessageInfo.Message.Builder.class);
    }

    /**
     * Protobuf enum {@code Message.CommandType}
     */
    public enum CommandType
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <pre>
       * 常规业务消息
       * </pre>
       *
       * <code>NORMAL = 0;</code>
       */
      NORMAL(0),
      /**
       * <pre>
       * 客户端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_REQUEST = 1;</code>
       */
      HEARTBEAT_REQUEST(1),
      /**
       * <pre>
       * 服务端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_RESPONSE = 2;</code>
       */
      HEARTBEAT_RESPONSE(2),
      UNRECOGNIZED(-1),
      ;

      /**
       * <pre>
       * 常规业务消息
       * </pre>
       *
       * <code>NORMAL = 0;</code>
       */
      public static final int NORMAL_VALUE = 0;
      /**
       * <pre>
       * 客户端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_REQUEST = 1;</code>
       */
      public static final int HEARTBEAT_REQUEST_VALUE = 1;
      /**
       * <pre>
       * 服务端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_RESPONSE = 2;</code>
       */
      public static final int HEARTBEAT_RESPONSE_VALUE = 2;


      public final int getNumber() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalArgumentException(
              "Can't get the number of an unknown enum value.");
        }
        return value;
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static CommandType valueOf(int value) {
        return forNumber(value);
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       */
      public static CommandType forNumber(int value) {
        switch (value) {
          case 0: return NORMAL;
          case 1: return HEARTBEAT_REQUEST;
          case 2: return HEARTBEAT_RESPONSE;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<CommandType>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          CommandType> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<CommandType>() {
              public CommandType findValueByNumber(int number) {
                return CommandType.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalStateException(
              "Can't get the descriptor of an unrecognized enum value.");
        }
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return com.dewen.protobuf.MessageInfo.Message.getDescriptor().getEnumTypes().get(0);
      }

      private static final CommandType[] VALUES = values();

      public static CommandType valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
          return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private CommandType(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:Message.CommandType)
    }

    public static final int REQUESTID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object requestId_ = "";
    /**
     * <code>string requestId = 1;</code>
     * @return The requestId.
     */
    @java.lang.Override
    public java.lang.String getRequestId() {
      java.lang.Object ref = requestId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        requestId_ = s;
        return s;
      }
    }
    /**
     * <code>string requestId = 1;</code>
     * @return The bytes for requestId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getRequestIdBytes() {
      java.lang.Object ref = requestId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        requestId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NAME_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CMD_FIELD_NUMBER = 3;
    private int cmd_ = 0;
    /**
     * <pre>
     * int32 id = 1;
     * </pre>
     *
     * <code>.Message.CommandType cmd = 3;</code>
     * @return The enum numeric value on the wire for cmd.
     */
    @java.lang.Override public int getCmdValue() {
      return cmd_;
    }
    /**
     * <pre>
     * int32 id = 1;
     * </pre>
     *
     * <code>.Message.CommandType cmd = 3;</code>
     * @return The cmd.
     */
    @java.lang.Override public com.dewen.protobuf.MessageInfo.Message.CommandType getCmd() {
      com.dewen.protobuf.MessageInfo.Message.CommandType result = com.dewen.protobuf.MessageInfo.Message.CommandType.forNumber(cmd_);
      return result == null ? com.dewen.protobuf.MessageInfo.Message.CommandType.UNRECOGNIZED : result;
    }

    public static final int CONTENT_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object content_ = "";
    /**
     * <code>string content = 4;</code>
     * @return The content.
     */
    @java.lang.Override
    public java.lang.String getContent() {
      java.lang.Object ref = content_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        content_ = s;
        return s;
      }
    }
    /**
     * <code>string content = 4;</code>
     * @return The bytes for content.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getContentBytes() {
      java.lang.Object ref = content_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        content_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(requestId_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, requestId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
      }
      if (cmd_ != com.dewen.protobuf.MessageInfo.Message.CommandType.NORMAL.getNumber()) {
        output.writeEnum(3, cmd_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(content_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, content_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(requestId_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, requestId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
      }
      if (cmd_ != com.dewen.protobuf.MessageInfo.Message.CommandType.NORMAL.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, cmd_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(content_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, content_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.dewen.protobuf.MessageInfo.Message)) {
        return super.equals(obj);
      }
      com.dewen.protobuf.MessageInfo.Message other = (com.dewen.protobuf.MessageInfo.Message) obj;

      if (!getRequestId()
          .equals(other.getRequestId())) return false;
      if (!getName()
          .equals(other.getName())) return false;
      if (cmd_ != other.cmd_) return false;
      if (!getContent()
          .equals(other.getContent())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + REQUESTID_FIELD_NUMBER;
      hash = (53 * hash) + getRequestId().hashCode();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + CMD_FIELD_NUMBER;
      hash = (53 * hash) + cmd_;
      hash = (37 * hash) + CONTENT_FIELD_NUMBER;
      hash = (53 * hash) + getContent().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.dewen.protobuf.MessageInfo.Message parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.dewen.protobuf.MessageInfo.Message prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Message}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Message)
        com.dewen.protobuf.MessageInfo.MessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.dewen.protobuf.MessageInfo.internal_static_Message_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.dewen.protobuf.MessageInfo.internal_static_Message_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.dewen.protobuf.MessageInfo.Message.class, com.dewen.protobuf.MessageInfo.Message.Builder.class);
      }

      // Construct using com.dewen.protobuf.MessageInfo.Message.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        requestId_ = "";
        name_ = "";
        cmd_ = 0;
        content_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.dewen.protobuf.MessageInfo.internal_static_Message_descriptor;
      }

      @java.lang.Override
      public com.dewen.protobuf.MessageInfo.Message getDefaultInstanceForType() {
        return com.dewen.protobuf.MessageInfo.Message.getDefaultInstance();
      }

      @java.lang.Override
      public com.dewen.protobuf.MessageInfo.Message build() {
        com.dewen.protobuf.MessageInfo.Message result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.dewen.protobuf.MessageInfo.Message buildPartial() {
        com.dewen.protobuf.MessageInfo.Message result = new com.dewen.protobuf.MessageInfo.Message(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.dewen.protobuf.MessageInfo.Message result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.requestId_ = requestId_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.name_ = name_;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.cmd_ = cmd_;
        }
        if (((from_bitField0_ & 0x00000008) != 0)) {
          result.content_ = content_;
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.dewen.protobuf.MessageInfo.Message) {
          return mergeFrom((com.dewen.protobuf.MessageInfo.Message)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.dewen.protobuf.MessageInfo.Message other) {
        if (other == com.dewen.protobuf.MessageInfo.Message.getDefaultInstance()) return this;
        if (!other.getRequestId().isEmpty()) {
          requestId_ = other.requestId_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          bitField0_ |= 0x00000002;
          onChanged();
        }
        if (other.cmd_ != 0) {
          setCmdValue(other.getCmdValue());
        }
        if (!other.getContent().isEmpty()) {
          content_ = other.content_;
          bitField0_ |= 0x00000008;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                requestId_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                name_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
              case 24: {
                cmd_ = input.readEnum();
                bitField0_ |= 0x00000004;
                break;
              } // case 24
              case 34: {
                content_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000008;
                break;
              } // case 34
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.lang.Object requestId_ = "";
      /**
       * <code>string requestId = 1;</code>
       * @return The requestId.
       */
      public java.lang.String getRequestId() {
        java.lang.Object ref = requestId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          requestId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string requestId = 1;</code>
       * @return The bytes for requestId.
       */
      public com.google.protobuf.ByteString
          getRequestIdBytes() {
        java.lang.Object ref = requestId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          requestId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string requestId = 1;</code>
       * @param value The requestId to set.
       * @return This builder for chaining.
       */
      public Builder setRequestId(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        requestId_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>string requestId = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearRequestId() {
        requestId_ = getDefaultInstance().getRequestId();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>string requestId = 1;</code>
       * @param value The bytes for requestId to set.
       * @return This builder for chaining.
       */
      public Builder setRequestIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        requestId_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>string name = 2;</code>
       * @return The name.
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       * @return The bytes for name.
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       * @param value The name to set.
       * @return This builder for chaining.
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        name_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearName() {
        name_ = getDefaultInstance().getName();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       * @param value The bytes for name to set.
       * @return This builder for chaining.
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        name_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }

      private int cmd_ = 0;
      /**
       * <pre>
       * int32 id = 1;
       * </pre>
       *
       * <code>.Message.CommandType cmd = 3;</code>
       * @return The enum numeric value on the wire for cmd.
       */
      @java.lang.Override public int getCmdValue() {
        return cmd_;
      }
      /**
       * <pre>
       * int32 id = 1;
       * </pre>
       *
       * <code>.Message.CommandType cmd = 3;</code>
       * @param value The enum numeric value on the wire for cmd to set.
       * @return This builder for chaining.
       */
      public Builder setCmdValue(int value) {
        cmd_ = value;
        bitField0_ |= 0x00000004;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * int32 id = 1;
       * </pre>
       *
       * <code>.Message.CommandType cmd = 3;</code>
       * @return The cmd.
       */
      @java.lang.Override
      public com.dewen.protobuf.MessageInfo.Message.CommandType getCmd() {
        com.dewen.protobuf.MessageInfo.Message.CommandType result = com.dewen.protobuf.MessageInfo.Message.CommandType.forNumber(cmd_);
        return result == null ? com.dewen.protobuf.MessageInfo.Message.CommandType.UNRECOGNIZED : result;
      }
      /**
       * <pre>
       * int32 id = 1;
       * </pre>
       *
       * <code>.Message.CommandType cmd = 3;</code>
       * @param value The cmd to set.
       * @return This builder for chaining.
       */
      public Builder setCmd(com.dewen.protobuf.MessageInfo.Message.CommandType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000004;
        cmd_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * int32 id = 1;
       * </pre>
       *
       * <code>.Message.CommandType cmd = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearCmd() {
        bitField0_ = (bitField0_ & ~0x00000004);
        cmd_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object content_ = "";
      /**
       * <code>string content = 4;</code>
       * @return The content.
       */
      public java.lang.String getContent() {
        java.lang.Object ref = content_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          content_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string content = 4;</code>
       * @return The bytes for content.
       */
      public com.google.protobuf.ByteString
          getContentBytes() {
        java.lang.Object ref = content_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          content_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string content = 4;</code>
       * @param value The content to set.
       * @return This builder for chaining.
       */
      public Builder setContent(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        content_ = value;
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      /**
       * <code>string content = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearContent() {
        content_ = getDefaultInstance().getContent();
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
        return this;
      }
      /**
       * <code>string content = 4;</code>
       * @param value The bytes for content to set.
       * @return This builder for chaining.
       */
      public Builder setContentBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        content_ = value;
        bitField0_ |= 0x00000008;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Message)
    }

    // @@protoc_insertion_point(class_scope:Message)
    private static final com.dewen.protobuf.MessageInfo.Message DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.dewen.protobuf.MessageInfo.Message();
    }

    public static com.dewen.protobuf.MessageInfo.Message getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Message>
        PARSER = new com.google.protobuf.AbstractParser<Message>() {
      @java.lang.Override
      public Message parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Message> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Message> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.dewen.protobuf.MessageInfo.Message getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Message_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmessage.proto\"\250\001\n\007Message\022\021\n\trequestId" +
      "\030\001 \001(\t\022\014\n\004name\030\002 \001(\t\022!\n\003cmd\030\003 \001(\0162\024.Mess" +
      "age.CommandType\022\017\n\007content\030\004 \001(\t\"H\n\013Comm" +
      "andType\022\n\n\006NORMAL\020\000\022\025\n\021HEARTBEAT_REQUEST" +
      "\020\001\022\026\n\022HEARTBEAT_RESPONSE\020\002B!\n\022com.dewen." +
      "protobufB\013MessageInfob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Message_descriptor,
        new java.lang.String[] { "RequestId", "Name", "Cmd", "Content", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
