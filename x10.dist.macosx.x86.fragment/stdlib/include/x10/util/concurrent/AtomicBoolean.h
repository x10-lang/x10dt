#ifndef __X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H
#define __X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { 
class Volatile;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace compiler { 
class NativeRep;
} } 
namespace x10 { namespace util { namespace concurrent { 

class AtomicBoolean : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    volatile x10_int FMGL(value);
    
    void _constructor();
    
    static x10aux::ref<x10::util::concurrent::AtomicBoolean> _make();
    
    void _constructor(x10_boolean v);
    
    static x10aux::ref<x10::util::concurrent::AtomicBoolean> _make(x10_boolean v);
    
    virtual x10_boolean get();
    virtual void set(x10_boolean v);
    virtual x10_boolean getAndSet(x10_boolean v);
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10aux::ref<x10::util::concurrent::AtomicBoolean> x10__util__concurrent__AtomicBoolean____x10__util__concurrent__AtomicBoolean__this(
      );
    void __fieldInitializers2236();
    
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
#endif // X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H

namespace x10 { namespace util { namespace concurrent { 
class AtomicBoolean;
} } } 

#ifndef X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H_NODEPS
#define X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Int.h>
#include <x10/compiler/Volatile.h>
#include <x10/lang/Boolean.h>
#include <x10/compiler/Native.h>
#include <x10/lang/String.h>
#include <x10/compiler/NativeRep.h>
#ifndef X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H_GENERICS
#define X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H_GENERICS
template<class __T> x10aux::ref<__T> x10::util::concurrent::AtomicBoolean::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::concurrent::AtomicBoolean> this_ = new (memset(x10aux::alloc<x10::util::concurrent::AtomicBoolean>(), 0, sizeof(x10::util::concurrent::AtomicBoolean))) x10::util::concurrent::AtomicBoolean();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H_GENERICS
#endif // __X10_UTIL_CONCURRENT_ATOMICBOOLEAN_H_NODEPS
