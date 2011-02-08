#ifndef __X10_COMPILER_WS_FRAME_H
#define __X10_COMPILER_WS_FRAME_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace compiler { namespace ws { 
class FinishFrame;
} } } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 
class Header;
} } 
namespace x10 { namespace compiler { namespace ws { 
class Worker;
} } } 
namespace x10 { namespace compiler { namespace ws { 

class Frame : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::compiler::ws::Frame> FMGL(up);
    
    void _constructor(x10aux::ref<x10::compiler::ws::Frame> up) {
        this->::x10::lang::Object::_constructor();
        {
         
        }
        
        //#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/ws/Frame.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::compiler::ws::Frame>)this)->FMGL(up) = up;
        
    }
    virtual x10aux::ref<x10::compiler::ws::Frame> remap() = 0;
    virtual x10aux::ref<x10::compiler::ws::Frame> realloc();
    virtual void back(x10aux::ref<x10::compiler::ws::Worker> worker, x10aux::ref<x10::compiler::ws::Frame> frame);
    virtual void resume(x10aux::ref<x10::compiler::ws::Worker> worker);
    virtual x10aux::ref<x10::compiler::ws::Frame> x10__compiler__ws__Frame____x10__compiler__ws__Frame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } 
#endif // X10_COMPILER_WS_FRAME_H

namespace x10 { namespace compiler { namespace ws { 
class Frame;
} } } 

#ifndef X10_COMPILER_WS_FRAME_H_NODEPS
#define X10_COMPILER_WS_FRAME_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/ws/FinishFrame.h>
#include <x10/lang/Boolean.h>
#include <x10/compiler/Header.h>
#include <x10/compiler/ws/Worker.h>
#ifndef X10_COMPILER_WS_FRAME_H_GENERICS
#define X10_COMPILER_WS_FRAME_H_GENERICS
#endif // X10_COMPILER_WS_FRAME_H_GENERICS
#endif // __X10_COMPILER_WS_FRAME_H_NODEPS
