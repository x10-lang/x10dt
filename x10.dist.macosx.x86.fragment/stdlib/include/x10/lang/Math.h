#ifndef __X10_LANG_MATH_H
#define __X10_LANG_MATH_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class Complex;
} } 
#include <x10/lang/Complex.struct_h>
namespace x10 { namespace lang { 
class UInt;
} } 
#include <x10/lang/UInt.struct_h>
namespace x10 { namespace lang { 
class ULong;
} } 
#include <x10/lang/ULong.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 

class Math : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10_double FMGL(E);
    
    static inline x10_double FMGL(E__get)() {
        return x10::lang::Math::FMGL(E);
    }
    static x10_double FMGL(PI);
    
    static inline x10_double FMGL(PI__get)() {
        return x10::lang::Math::FMGL(PI);
    }
    static x10_double abs(x10_double a);
    static x10_int abs(x10_int a);
    static x10_float abs(x10_float a);
    static x10_long abs(x10_long a);
    static x10::lang::Complex pow(x10::lang::Complex a, x10::lang::Complex b);
    static x10::lang::Complex exp(x10::lang::Complex a);
    static x10::lang::Complex cos(x10::lang::Complex z);
    static x10::lang::Complex sin(x10::lang::Complex z);
    static x10::lang::Complex tan(x10::lang::Complex z);
    static x10::lang::Complex acos(x10::lang::Complex z);
    static x10::lang::Complex asin(x10::lang::Complex z);
    static x10::lang::Complex atan(x10::lang::Complex z);
    static x10::lang::Complex cosh(x10::lang::Complex z);
    static x10::lang::Complex sinh(x10::lang::Complex z);
    static x10::lang::Complex tanh(x10::lang::Complex z);
    static x10::lang::Complex sqrt(x10::lang::Complex z);
    static x10::lang::Complex log(x10::lang::Complex a);
    static x10_int max(x10_int a, x10_int b);
    static x10_int min(x10_int a, x10_int b);
    static x10_uint max(x10_uint a, x10_uint b);
    static x10_uint min(x10_uint a, x10_uint b);
    static x10_long max(x10_long a, x10_long b);
    static x10_long min(x10_long a, x10_long b);
    static x10_ulong max(x10_ulong a, x10_ulong b);
    static x10_ulong min(x10_ulong a, x10_ulong b);
    static x10_float max(x10_float a, x10_float b);
    static x10_float min(x10_float a, x10_float b);
    static x10_double max(x10_double a, x10_double b);
    static x10_double min(x10_double a, x10_double b);
    static x10_int signum(x10_int a);
    static x10_int signum(x10_long a);
    static x10_int signum(x10_float a);
    static x10_int signum(x10_double a);
    static x10_int nextPowerOf2(x10_int p);
    static x10_boolean powerOf2(x10_int p);
    static x10_int log2(x10_int p);
    static x10_int pow2(x10_int i);
    virtual x10aux::ref<x10::lang::Math> x10__lang__Math____x10__lang__Math__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::lang::Math> _make();
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_MATH_H

namespace x10 { namespace lang { 
class Math;
} } 

#ifndef X10_LANG_MATH_H_NODEPS
#define X10_LANG_MATH_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Double.h>
#include <x10/lang/Int.h>
#include <x10/lang/Float.h>
#include <x10/compiler/Native.h>
#include <x10/lang/Long.h>
#include <x10/lang/Complex.h>
#include <x10/lang/UInt.h>
#include <x10/lang/ULong.h>
#include <x10/lang/Boolean.h>
#ifndef X10_LANG_MATH_H_GENERICS
#define X10_LANG_MATH_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Math::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Math> this_ = new (memset(x10aux::alloc<x10::lang::Math>(), 0, sizeof(x10::lang::Math))) x10::lang::Math();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_MATH_H_GENERICS
#endif // __X10_LANG_MATH_H_NODEPS
