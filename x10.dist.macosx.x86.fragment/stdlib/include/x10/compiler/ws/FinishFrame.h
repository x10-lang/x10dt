#ifndef __X10_COMPILER_WS_FINISHFRAME_H
#define __X10_COMPILER_WS_FINISHFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_FRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#undef X10_COMPILER_WS_FRAME_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { 
class Uninitialized;
} } 
namespace x10 { namespace compiler { 
class Header;
} } 
namespace x10 { namespace compiler { namespace ws { 

class FinishFrame : public x10::compiler::ws::Frame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10_int FMGL(asyncs);
    
    x10aux::ref<x10::compiler::ws::FinishFrame> FMGL(redirect);
    
    void _constructor(x10aux::ref<x10::compiler::ws::Frame> up) {
        this->::x10::compiler::ws::Frame::_constructor(up);
        {
         
        }
        
        //#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/ws/FinishFrame.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::compiler::ws::FinishFrame>)this)->FMGL(redirect) =
          NULL;
        
    }
    void _constructor(x10_int id__87, x10aux::ref<x10::compiler::ws::FinishFrame> o);
    
    virtual x10aux::ref<x10::compiler::ws::Frame> remap() = 0;
    virtual x10aux::ref<x10::compiler::ws::Frame> realloc();
    virtual x10aux::ref<x10::compiler::ws::FinishFrame> x10__compiler__ws__FinishFrame____x10__compiler__ws__FinishFrame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } 
#endif // X10_COMPILER_WS_FINISHFRAME_H

namespace x10 { namespace compiler { namespace ws { 
class FinishFrame;
} } } 

#ifndef X10_COMPILER_WS_FINISHFRAME_H_NODEPS
#define X10_COMPILER_WS_FINISHFRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#include <x10/lang/Int.h>
#include <x10/compiler/Uninitialized.h>
#include <x10/compiler/Header.h>
#ifndef X10_COMPILER_WS_FINISHFRAME_H_GENERICS
#define X10_COMPILER_WS_FINISHFRAME_H_GENERICS
#endif // X10_COMPILER_WS_FINISHFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_FINISHFRAME_H_NODEPS
