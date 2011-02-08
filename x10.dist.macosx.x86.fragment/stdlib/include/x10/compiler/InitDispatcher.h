#ifndef __X10_COMPILER_INITDISPATCHER_H
#define __X10_COMPILER_INITDISPATCHER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace compiler { 
class NativeRep;
} } 
namespace x10 { namespace compiler { 

class InitDispatcher : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual x10aux::ref<x10::compiler::InitDispatcher> x10__compiler__InitDispatcher____x10__compiler__InitDispatcher__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::compiler::InitDispatcher> _make();
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_COMPILER_INITDISPATCHER_H

namespace x10 { namespace compiler { 
class InitDispatcher;
} } 

#ifndef X10_COMPILER_INITDISPATCHER_H_NODEPS
#define X10_COMPILER_INITDISPATCHER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/compiler/NativeRep.h>
#ifndef X10_COMPILER_INITDISPATCHER_H_GENERICS
#define X10_COMPILER_INITDISPATCHER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::InitDispatcher::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::InitDispatcher> this_ = new (memset(x10aux::alloc<x10::compiler::InitDispatcher>(), 0, sizeof(x10::compiler::InitDispatcher))) x10::compiler::InitDispatcher();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_INITDISPATCHER_H_GENERICS
#endif // __X10_COMPILER_INITDISPATCHER_H_NODEPS
