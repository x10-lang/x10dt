#ifndef __X10_COMPILER_WS_ROOTFRAME_H
#define __X10_COMPILER_WS_ROOTFRAME_H

#include <x10rt.h>


#define X10_COMPILER_WS_FRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#undef X10_COMPILER_WS_FRAME_H_NODEPS
namespace x10 { namespace compiler { 
class Header;
} } 
namespace x10 { namespace compiler { namespace ws { 
class Worker;
} } } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace compiler { namespace ws { 

class RootFrame : public x10::compiler::ws::Frame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor() {
        this->::x10::compiler::ws::Frame::_constructor(X10_NULL);
        {
         
        }
        
    }
    static x10aux::ref<x10::compiler::ws::RootFrame> _make();
    
    virtual x10aux::ref<x10::compiler::ws::Frame> remap();
    virtual void resume(x10aux::ref<x10::compiler::ws::Worker> worker);
    virtual x10aux::ref<x10::compiler::ws::RootFrame> x10__compiler__ws__RootFrame____x10__compiler__ws__RootFrame__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } } 
#endif // X10_COMPILER_WS_ROOTFRAME_H

namespace x10 { namespace compiler { namespace ws { 
class RootFrame;
} } } 

#ifndef X10_COMPILER_WS_ROOTFRAME_H_NODEPS
#define X10_COMPILER_WS_ROOTFRAME_H_NODEPS
#include <x10/compiler/ws/Frame.h>
#include <x10/compiler/Header.h>
#include <x10/compiler/ws/Worker.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/Runtime.h>
#include <x10/compiler/Finalization.h>
#ifndef X10_COMPILER_WS_ROOTFRAME_H_GENERICS
#define X10_COMPILER_WS_ROOTFRAME_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::ws::RootFrame::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::ws::RootFrame> this_ = new (memset(x10aux::alloc<x10::compiler::ws::RootFrame>(), 0, sizeof(x10::compiler::ws::RootFrame))) x10::compiler::ws::RootFrame();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_WS_ROOTFRAME_H_GENERICS
#endif // __X10_COMPILER_WS_ROOTFRAME_H_NODEPS
