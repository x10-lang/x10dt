#ifndef __X10_COMPILER_WS_JAVA_FRAME_H
#define __X10_COMPILER_WS_JAVA_FRAME_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace compiler { namespace ws { namespace java { 
class Worker;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 

class Frame : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::compiler::ws::java::Frame> FMGL(up);
    
    void _constructor(x10aux::ref<x10::compiler::ws::java::Frame> up);
    
    virtual void back(x10aux::ref<x10::compiler::ws::java::Worker> worker,
                      x10aux::ref<x10::compiler::ws::java::Frame> frame);
    virtual void resume(x10aux::ref<x10::compiler::ws::java::Worker> worker);
    virtual x10aux::ref<x10::compiler::ws::java::Frame> x10__compiler__ws__java__Frame____x10__compiler__ws__java__Frame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } } 
#endif // X10_COMPILER_WS_JAVA_FRAME_H

namespace x10 { namespace compiler { namespace ws { namespace java { 
class Frame;
} } } } 

#ifndef X10_COMPILER_WS_JAVA_FRAME_H_NODEPS
#define X10_COMPILER_WS_JAVA_FRAME_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/ws/java/Worker.h>
#ifndef X10_COMPILER_WS_JAVA_FRAME_H_GENERICS
#define X10_COMPILER_WS_JAVA_FRAME_H_GENERICS
#endif // X10_COMPILER_WS_JAVA_FRAME_H_GENERICS
#endif // __X10_COMPILER_WS_JAVA_FRAME_H_NODEPS
