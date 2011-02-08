#ifndef __X10_COMPILER_WS_MAINFRAME_H
#define __X10_COMPILER_WS_MAINFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_REGULARFRAME_H_NODEPS
#include <x10/compiler/ws/RegularFrame.h>
#undef X10_COMPILER_WS_REGULARFRAME_H_NODEPS
namespace x10 { namespace compiler { namespace ws { 
class Frame;
} } } 
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
namespace x10 { namespace compiler { namespace ws { 

class MainFrame : public x10::compiler::ws::RegularFrame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor(x10aux::ref<x10::compiler::ws::Frame> up, x10aux::ref<x10::compiler::ws::FinishFrame> ff)
    {
        this->::x10::compiler::ws::RegularFrame::_constructor(
          up,
          ff);
        {
         
        }
        
    }
    void _constructor(
      x10_int id__88,
      x10aux::ref<x10::compiler::ws::MainFrame> o);
    
    virtual void
      fast(
      x10aux::ref<x10::compiler::ws::Worker> worker) = 0;
    virtual void
      run(
      );
    virtual x10aux::ref<x10::compiler::ws::MainFrame>
      x10__compiler__ws__MainFrame____x10__compiler__ws__MainFrame__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } 
#endif // X10_COMPILER_WS_MAINFRAME_H

namespace x10 { namespace compiler { namespace ws { 
class MainFrame;
} } } 

#ifndef X10_COMPILER_WS_MAINFRAME_H_NODEPS
#define X10_COMPILER_WS_MAINFRAME_H_NODEPS
#include <x10/compiler/ws/RegularFrame.h>
#include <x10/compiler/ws/Frame.h>
#include <x10/compiler/ws/FinishFrame.h>
#include <x10/compiler/Header.h>
#include <x10/lang/Int.h>
#include <x10/compiler/ws/Worker.h>
#ifndef X10_COMPILER_WS_MAINFRAME_H_GENERICS
#define X10_COMPILER_WS_MAINFRAME_H_GENERICS
#endif // X10_COMPILER_WS_MAINFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_MAINFRAME_H_NODEPS
