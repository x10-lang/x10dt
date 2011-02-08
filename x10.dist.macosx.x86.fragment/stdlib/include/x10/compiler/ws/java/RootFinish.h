#ifndef __X10_COMPILER_WS_JAVA_ROOTFINISH_H
#define __X10_COMPILER_WS_JAVA_ROOTFINISH_H

#include <x10rt.h>


#define X10_COMPILER_WS_JAVA_FINISHFRAME_H_NODEPS
#include <x10/compiler/ws/java/FinishFrame.h>
#undef X10_COMPILER_WS_JAVA_FINISHFRAME_H_NODEPS
namespace x10 { namespace compiler { namespace ws { namespace java { 
class Frame;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 
class RootFrame;
} } } } 
namespace x10 { namespace compiler { namespace ws { namespace java { 

class RootFinish : public x10::compiler::ws::java::FinishFrame   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::compiler::ws::java::RootFinish> _make();
    
    virtual x10aux::ref<x10::compiler::ws::java::RootFinish> init();
    virtual x10aux::ref<x10::compiler::ws::java::RootFinish> x10__compiler__ws__java__RootFinish____x10__compiler__ws__java__RootFinish__this(
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

} } } } 
#endif // X10_COMPILER_WS_JAVA_ROOTFINISH_H

namespace x10 { namespace compiler { namespace ws { namespace java { 
class RootFinish;
} } } } 

#ifndef X10_COMPILER_WS_JAVA_ROOTFINISH_H_NODEPS
#define X10_COMPILER_WS_JAVA_ROOTFINISH_H_NODEPS
#include <x10/compiler/ws/java/FinishFrame.h>
#include <x10/compiler/ws/java/Frame.h>
#include <x10/compiler/ws/java/RootFrame.h>
#ifndef X10_COMPILER_WS_JAVA_ROOTFINISH_H_GENERICS
#define X10_COMPILER_WS_JAVA_ROOTFINISH_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::ws::java::RootFinish::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::ws::java::RootFinish> this_ = new (memset(x10aux::alloc<x10::compiler::ws::java::RootFinish>(), 0, sizeof(x10::compiler::ws::java::RootFinish))) x10::compiler::ws::java::RootFinish();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_WS_JAVA_ROOTFINISH_H_GENERICS
#endif // __X10_COMPILER_WS_JAVA_ROOTFINISH_H_NODEPS
