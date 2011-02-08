#ifndef __X10_COMPILER_WS_ASYNCFRAME_H
#define __X10_COMPILER_WS_ASYNCFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_FRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#undef X10_COMPILER_WS_FRAME_H_NODEPS
namespace x10 { namespace compiler { 
class Header;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { namespace ws { 
class FinishFrame;
} } } 
namespace x10 { namespace compiler { namespace ws { 
class Worker;
} } } 
namespace x10 { namespace compiler { namespace ws { 
class Stolen;
} } } 
namespace x10 { namespace compiler { 
class Inline;
} } 
namespace x10 { namespace compiler { namespace ws { 

class AsyncFrame : public x10::compiler::ws::Frame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor(x10aux::ref<x10::compiler::ws::Frame> up) {
        this->::x10::compiler::ws::Frame::_constructor(up);
        {
         
        }
        
    }
    void _constructor(x10_int id__86, x10aux::ref<x10::compiler::ws::AsyncFrame> o);
    
    virtual void move(x10aux::ref<x10::compiler::ws::FinishFrame> ff) = 0;
    virtual void poll(x10aux::ref<x10::compiler::ws::Worker> worker);
    virtual x10aux::ref<x10::compiler::ws::AsyncFrame> x10__compiler__ws__AsyncFrame____x10__compiler__ws__AsyncFrame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } 
#endif // X10_COMPILER_WS_ASYNCFRAME_H

namespace x10 { namespace compiler { namespace ws { 
class AsyncFrame;
} } } 

#ifndef X10_COMPILER_WS_ASYNCFRAME_H_NODEPS
#define X10_COMPILER_WS_ASYNCFRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#include <x10/compiler/Header.h>
#include <x10/lang/Int.h>
#include <x10/compiler/ws/FinishFrame.h>
#include <x10/compiler/ws/Worker.h>
#include <x10/compiler/ws/Stolen.h>
#include <x10/compiler/Inline.h>
#ifndef X10_COMPILER_WS_ASYNCFRAME_H_GENERICS
#define X10_COMPILER_WS_ASYNCFRAME_H_GENERICS
#endif // X10_COMPILER_WS_ASYNCFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_ASYNCFRAME_H_NODEPS
