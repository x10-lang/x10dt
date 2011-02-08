#ifndef __X10_COMPILER_WS_JAVA_MAINFRAME_H
#define __X10_COMPILER_WS_JAVA_MAINFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_JAVA_REGULARFRAME_H_NODEPS
#include <x10/compiler/ws/java/RegularFrame.h>
#undef X10_COMPILER_WS_JAVA_REGULARFRAME_H_NODEPS
namespace x10 { namespace compiler { namespace ws { namespace java { 
class Frame;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 
class FinishFrame;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 
class Worker;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 

class MainFrame : public x10::compiler::ws::java::RegularFrame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor(x10aux::ref<x10::compiler::ws::java::Frame> up, x10aux::ref<x10::compiler::ws::java::FinishFrame> ff);
    
    virtual void fast(x10aux::ref<x10::compiler::ws::java::Worker> worker) = 0;
    virtual void run();
    virtual x10aux::ref<x10::compiler::ws::java::MainFrame> x10__compiler__ws__java__MainFrame____x10__compiler__ws__java__MainFrame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } } 
#endif // X10_COMPILER_WS_JAVA_MAINFRAME_H

namespace x10 { namespace compiler { namespace ws { namespace java { 
class MainFrame;
} } } } 

#ifndef X10_COMPILER_WS_JAVA_MAINFRAME_H_NODEPS
#define X10_COMPILER_WS_JAVA_MAINFRAME_H_NODEPS
#include <x10/compiler/ws/java/RegularFrame.h>
#include <x10/compiler/ws/java/Frame.h>
#include <x10/compiler/ws/java/FinishFrame.h>
#include <x10/compiler/ws/java/Worker.h>
#ifndef X10_COMPILER_WS_JAVA_MAINFRAME_H_GENERICS
#define X10_COMPILER_WS_JAVA_MAINFRAME_H_GENERICS
#endif // X10_COMPILER_WS_JAVA_MAINFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_JAVA_MAINFRAME_H_NODEPS
