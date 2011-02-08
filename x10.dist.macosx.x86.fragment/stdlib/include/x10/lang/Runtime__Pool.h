#ifndef __X10_LANG_RUNTIME__POOL_H
#define __X10_LANG_RUNTIME__POOL_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class SimpleLatch;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Lock;
} } 
namespace x10 { namespace lang { 
class Runtime__Semaphore;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Runtime__Worker;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
class System;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace util { 
class Random;
} } 
namespace x10 { namespace lang { 
class Activity;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 

class Runtime__Pool : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::lang::SimpleLatch> FMGL(latch);
    
    x10_int FMGL(size);
    
    x10_int FMGL(spares);
    
    x10_int FMGL(dead);
    
    x10aux::ref<x10::lang::Lock> FMGL(lock);
    
    x10aux::ref<x10::lang::Runtime__Semaphore> FMGL(semaphore);
    
    x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Runtime__Worker> > >
      FMGL(workers);
    
    void _constructor(x10_int size);
    
    static x10aux::ref<x10::lang::Runtime__Pool> _make(x10_int size);
    
    virtual void __apply();
    virtual void increase();
    virtual void decrease(x10_int n);
    virtual void release();
    virtual x10aux::ref<x10::lang::Activity> scan(x10aux::ref<x10::util::Random> random,
                                                  x10aux::ref<x10::lang::Runtime__Worker> worker);
    virtual x10_int size();
    virtual x10aux::ref<x10::lang::Runtime__Pool> x10__lang__Runtime__Pool____x10__lang__Runtime__Pool__this(
      );
    void __fieldInitializers2004();
    
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
#endif // X10_LANG_RUNTIME__POOL_H

namespace x10 { namespace lang { 
class Runtime__Pool;
} } 

#ifndef X10_LANG_RUNTIME__POOL_H_NODEPS
#define X10_LANG_RUNTIME__POOL_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/SimpleLatch.h>
#include <x10/lang/Int.h>
#include <x10/lang/Lock.h>
#include <x10/lang/Runtime__Semaphore.h>
#include <x10/array/Array.h>
#include <x10/lang/Runtime__Worker.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/System.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/util/Random.h>
#include <x10/lang/Activity.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_LANG_RUNTIME__POOL_H_GENERICS
#define X10_LANG_RUNTIME__POOL_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Runtime__Pool::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Runtime__Pool> this_ = new (memset(x10aux::alloc<x10::lang::Runtime__Pool>(), 0, sizeof(x10::lang::Runtime__Pool))) x10::lang::Runtime__Pool();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_RUNTIME__POOL_H_GENERICS
#endif // __X10_LANG_RUNTIME__POOL_H_NODEPS
