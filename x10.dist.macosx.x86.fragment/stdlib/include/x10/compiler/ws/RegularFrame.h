#ifndef __X10_COMPILER_WS_REGULARFRAME_H
#define __X10_COMPILER_WS_REGULARFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_FRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#undef X10_COMPILER_WS_FRAME_H_NODEPS
namespace x10 { namespace compiler { namespace ws { 
class FinishFrame;
} } } 
namespace x10 { namespace compiler { 
class Header;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { namespace ws { 
class Worker;
} } } 
namespace x10 { namespace lang { 
class Object;
} } 
namespace x10 { namespace compiler { 
class Inline;
} } 
namespace x10 { namespace compiler { namespace ws { 
class Stolen;
} } } 
namespace x10 { namespace compiler { namespace ws { 

class RegularFrame : public x10::compiler::ws::Frame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::compiler::ws::FinishFrame> FMGL(ff);
    
    void _constructor(x10aux::ref<x10::compiler::ws::Frame> up, x10aux::ref<x10::compiler::ws::FinishFrame> ff)
    {
        this->::x10::compiler::ws::Frame::_constructor(
          up);
        {
         
        }
        
        //#line 12 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/ws/RegularFrame.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::compiler::ws::RegularFrame>)this)->
          FMGL(ff) =
          ff;
        
    }
    void _constructor(
      x10_int id__89,
      x10aux::ref<x10::compiler::ws::RegularFrame> o);
    
    virtual x10aux::ref<x10::compiler::ws::Frame>
      remap(
      ) = 0;
    virtual void
      push(
      x10aux::ref<x10::compiler::ws::Worker> worker);
    virtual void
      redo(
      x10aux::ref<x10::compiler::ws::Worker> worker);
    virtual x10aux::ref<x10::compiler::ws::RegularFrame>
      x10__compiler__ws__RegularFrame____x10__compiler__ws__RegularFrame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } 
#endif // X10_COMPILER_WS_REGULARFRAME_H

namespace x10 { namespace compiler { namespace ws { 
class RegularFrame;
} } } 

#ifndef X10_COMPILER_WS_REGULARFRAME_H_NODEPS
#define X10_COMPILER_WS_REGULARFRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#include <x10/compiler/ws/FinishFrame.h>
#include <x10/compiler/Header.h>
#include <x10/lang/Int.h>
#include <x10/compiler/ws/Worker.h>
#include <x10/lang/Object.h>
#include <x10/compiler/Inline.h>
#include <x10/compiler/ws/Stolen.h>
#ifndef X10_COMPILER_WS_REGULARFRAME_H_GENERICS
#define X10_COMPILER_WS_REGULARFRAME_H_GENERICS
#endif // X10_COMPILER_WS_REGULARFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_REGULARFRAME_H_NODEPS
