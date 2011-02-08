#ifndef __X10_UTIL_CONCURRENT_ATOMICDOUBLE_H
#define __X10_UTIL_CONCURRENT_ATOMICDOUBLE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace util { namespace concurrent { 
class AtomicLong;
} } } 
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace util { namespace concurrent { 

class AtomicDouble : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::concurrent::AtomicLong> FMGL(v);
    
    void _constructor();
    
    static x10aux::ref<x10::util::concurrent::AtomicDouble> _make();
    
    void _constructor(x10_double v);
    
    static x10aux::ref<x10::util::concurrent::AtomicDouble> _make(x10_double v);
    
    virtual x10_double get();
    virtual void set(x10_double v);
    virtual x10_boolean compareAndSet(x10_double expect, x10_double update);
    virtual x10_boolean weakCompareAndSet(x10_double expect, x10_double update);
    virtual x10_double getAndIncrement();
    virtual x10_double getAndDecrement();
    virtual x10_double getAndAdd(x10_double delta);
    virtual x10_double incrementAndGet();
    virtual x10_double decrementAndGet();
    virtual x10_double addAndGet(x10_double delta);
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10_int intValue();
    virtual x10_long longValue();
    virtual x10_float floatValue();
    virtual x10_double doubleValue();
    virtual x10aux::ref<x10::util::concurrent::AtomicDouble> x10__util__concurrent__AtomicDouble____x10__util__concurrent__AtomicDouble__this(
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
#endif // X10_UTIL_CONCURRENT_ATOMICDOUBLE_H

namespace x10 { namespace util { namespace concurrent { 
class AtomicDouble;
} } } 

#ifndef X10_UTIL_CONCURRENT_ATOMICDOUBLE_H_NODEPS
#define X10_UTIL_CONCURRENT_ATOMICDOUBLE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/concurrent/AtomicLong.h>
#include <x10/lang/Double.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Long.h>
#include <x10/lang/String.h>
#include <x10/lang/Int.h>
#include <x10/lang/Float.h>
#ifndef X10_UTIL_CONCURRENT_ATOMICDOUBLE_H_GENERICS
#define X10_UTIL_CONCURRENT_ATOMICDOUBLE_H_GENERICS
template<class __T> x10aux::ref<__T> x10::util::concurrent::AtomicDouble::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::concurrent::AtomicDouble> this_ = new (memset(x10aux::alloc<x10::util::concurrent::AtomicDouble>(), 0, sizeof(x10::util::concurrent::AtomicDouble))) x10::util::concurrent::AtomicDouble();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_CONCURRENT_ATOMICDOUBLE_H_GENERICS
#endif // __X10_UTIL_CONCURRENT_ATOMICDOUBLE_H_NODEPS
