#ifndef __X10_COMPILER_WS_JAVA_FINISHFRAME_H
#define __X10_COMPILER_WS_JAVA_FINISHFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_JAVA_FRAME_H_NODEPS
#include <x10/compiler/ws/java/Frame.h>
#undef X10_COMPILER_WS_JAVA_FRAME_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { namespace ws { namespace java { 

class FinishFrame : public x10::compiler::ws::java::Frame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10_int FMGL(asyncs);
    
    void _constructor(x10aux::ref<x10::compiler::ws::java::Frame> up);
    
    virtual x10aux::ref<x10::compiler::ws::java::FinishFrame> x10__compiler__ws__java__FinishFrame____x10__compiler__ws__java__FinishFrame__this(
      );
    void __fieldInitializers1818();
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } } 
#endif // X10_COMPILER_WS_JAVA_FINISHFRAME_H

namespace x10 { namespace compiler { namespace ws { namespace java { 
class FinishFrame;
} } } } 

#ifndef X10_COMPILER_WS_JAVA_FINISHFRAME_H_NODEPS
#define X10_COMPILER_WS_JAVA_FINISHFRAME_H_NODEPS
#include <x10/compiler/ws/java/Frame.h>
#include <x10/lang/Int.h>
#ifndef X10_COMPILER_WS_JAVA_FINISHFRAME_H_GENERICS
#define X10_COMPILER_WS_JAVA_FINISHFRAME_H_GENERICS
#endif // X10_COMPILER_WS_JAVA_FINISHFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_JAVA_FINISHFRAME_H_NODEPS
