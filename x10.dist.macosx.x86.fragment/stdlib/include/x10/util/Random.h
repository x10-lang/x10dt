#ifndef __X10_UTIL_RANDOM_H
#define __X10_UTIL_RANDOM_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace util { 
class Timer;
} } 
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace compiler { 
class NonEscaping;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 

class Random : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::util::Random> _make();
    
    void _constructor(x10_long seed);
    
    static x10aux::ref<x10::util::Random> _make(x10_long seed);
    
    virtual void setSeed(x10_long seed);
    virtual x10_int nextInt();
    virtual x10_int nextInt(x10_int maxPlus1);
    virtual void nextBytes(x10aux::ref<x10::lang::Rail<x10_byte > > buf);
    virtual x10_long nextLong();
    virtual x10_long nextLong(x10_long maxPlus1);
    virtual x10_boolean nextBoolean();
    virtual x10_float nextFloat();
    virtual x10_double nextDouble();
    static x10_int FMGL(N);
    
    static inline x10_int FMGL(N__get)() {
        return x10::util::Random::FMGL(N);
    }
    static x10_int FMGL(M);
    
    static inline x10_int FMGL(M__get)() {
        return x10::util::Random::FMGL(M);
    }
    x10_int FMGL(index);
    
    x10aux::ref<x10::lang::Rail<x10_int > > FMGL(MT);
    
    virtual void init(x10_long seed);
    virtual x10_int random();
    static void twist(x10aux::ref<x10::lang::Rail<x10_int > > MT);
    virtual x10aux::ref<x10::util::Random> x10__util__Random____x10__util__Random__this(
      );
    void __fieldInitializers2171();
    
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
#endif // X10_UTIL_RANDOM_H

namespace x10 { namespace util { 
class Random;
} } 

#ifndef X10_UTIL_RANDOM_H_NODEPS
#define X10_UTIL_RANDOM_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/Timer.h>
#include <x10/lang/Long.h>
#include <x10/compiler/NonEscaping.h>
#include <x10/lang/Int.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Byte.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Float.h>
#include <x10/lang/Double.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_2.h>
#ifndef X10_UTIL_RANDOM_H_GENERICS
#define X10_UTIL_RANDOM_H_GENERICS
template<class __T> x10aux::ref<__T> x10::util::Random::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::Random> this_ = new (memset(x10aux::alloc<x10::util::Random>(), 0, sizeof(x10::util::Random))) x10::util::Random();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_RANDOM_H_GENERICS
#endif // __X10_UTIL_RANDOM_H_NODEPS
