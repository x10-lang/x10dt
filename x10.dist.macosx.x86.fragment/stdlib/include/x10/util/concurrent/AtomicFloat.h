#ifndef __X10_UTIL_CONCURRENT_ATOMICFLOAT_H
#define __X10_UTIL_CONCURRENT_ATOMICFLOAT_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace util { namespace concurrent { 
class AtomicInteger;
} } } 
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace util { namespace concurrent { 

class AtomicFloat : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::concurrent::AtomicInteger> FMGL(v);
    
    void _constructor();
    
    static x10aux::ref<x10::util::concurrent::AtomicFloat> _make();
    
    void _constructor(x10_float v);
    
    static x10aux::ref<x10::util::concurrent::AtomicFloat> _make(x10_float v);
    
    virtual x10_float get();
    virtual void set(x10_float v);
    virtual x10_boolean compareAndSet(x10_float expect, x10_float update);
    virtual x10_boolean weakCompareAndSet(x10_float expect, x10_float update);
    virtual x10_float getAndIncrement();
    virtual x10_float getAndDecrement();
    virtual x10_float getAndAdd(x10_float delta);
    virtual x10_float incrementAndGet();
    virtual x10_float decrementAndGet();
    virtual x10_float addAndGet(x10_float delta);
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10_int intValue();
    virtual x10_long longValue();
    virtual x10_float floatValue();
    virtual x10_double doubleValue();
    virtual x10aux::ref<x10::util::concurrent::AtomicFloat> x10__util__concurrent__AtomicFloat____x10__util__concurrent__AtomicFloat__this(
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
#endif // X10_UTIL_CONCURRENT_ATOMICFLOAT_H

namespace x10 { namespace util { namespace concurrent { 
class AtomicFloat;
} } } 

#ifndef X10_UTIL_CONCURRENT_ATOMICFLOAT_H_NODEPS
#define X10_UTIL_CONCURRENT_ATOMICFLOAT_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/concurrent/AtomicInteger.h>
#include <x10/lang/Float.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#include <x10/lang/String.h>
#include <x10/lang/Long.h>
#include <x10/lang/Double.h>
#ifndef X10_UTIL_CONCURRENT_ATOMICFLOAT_H_GENERICS
#define X10_UTIL_CONCURRENT_ATOMICFLOAT_H_GENERICS
template<class __T> x10aux::ref<__T> x10::util::concurrent::AtomicFloat::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::concurrent::AtomicFloat> this_ = new (memset(x10aux::alloc<x10::util::concurrent::AtomicFloat>(), 0, sizeof(x10::util::concurrent::AtomicFloat))) x10::util::concurrent::AtomicFloat();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_CONCURRENT_ATOMICFLOAT_H_GENERICS
#endif // __X10_UTIL_CONCURRENT_ATOMICFLOAT_H_NODEPS
