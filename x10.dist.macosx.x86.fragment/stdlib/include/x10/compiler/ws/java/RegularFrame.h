#ifndef __X10_COMPILER_WS_JAVA_REGULARFRAME_H
#define __X10_COMPILER_WS_JAVA_REGULARFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_JAVA_FRAME_H_NODEPS
#include <x10/compiler/ws/java/Frame.h>
#undef X10_COMPILER_WS_JAVA_FRAME_H_NODEPS
namespace x10 { namespace compiler { namespace ws { namespace java { 
class FinishFrame;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 
class Worker;
} } } } 
namespace x10 { namespace compiler { 
class Inline;
} } 
namespace x10 { namespace compiler { namespace ws { namespace java { 
class Stolen;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 

class RegularFrame : public x10::compiler::ws::java::Frame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::compiler::ws::java::FinishFrame> FMGL(ff);
    
    void _constructor(x10aux::ref<x10::compiler::ws::java::Frame> up, x10aux::ref<x10::compiler::ws::java::FinishFrame> ff);
    
    virtual void push(x10aux::ref<x10::compiler::ws::java::Worker> worker);
    virtual void redo(x10aux::ref<x10::compiler::ws::java::Worker> worker);
    virtual x10aux::ref<x10::compiler::ws::java::RegularFrame> x10__compiler__ws__java__RegularFrame____x10__compiler__ws__java__RegularFrame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } } 
#endif // X10_COMPILER_WS_JAVA_REGULARFRAME_H

namespace x10 { namespace compiler { namespace ws { namespace java { 
class RegularFrame;
} } } } 

#ifndef X10_COMPILER_WS_JAVA_REGULARFRAME_H_NODEPS
#define X10_COMPILER_WS_JAVA_REGULARFRAME_H_NODEPS
#include <x10/compiler/ws/java/Frame.h>
#include <x10/compiler/ws/java/FinishFrame.h>
#include <x10/compiler/ws/java/Worker.h>
#include <x10/compiler/Inline.h>
#include <x10/compiler/ws/java/Stolen.h>
#ifndef X10_COMPILER_WS_JAVA_REGULARFRAME_H_GENERICS
#define X10_COMPILER_WS_JAVA_REGULARFRAME_H_GENERICS
#endif // X10_COMPILER_WS_JAVA_REGULARFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_JAVA_REGULARFRAME_H_NODEPS
