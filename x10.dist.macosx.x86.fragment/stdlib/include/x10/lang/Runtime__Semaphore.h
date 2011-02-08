#ifndef __X10_LANG_RUNTIME__SEMAPHORE_H
#define __X10_LANG_RUNTIME__SEMAPHORE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Lock;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Runtime__Worker;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 

class Runtime__Semaphore : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Lock> FMGL(lock);
    
    x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Runtime__Worker> > >
      FMGL(threads);
    
    x10_int FMGL(size);
    
    x10_int FMGL(permits);
    
    void _constructor(x10_int n);
    
    static x10aux::ref<x10::lang::Runtime__Semaphore> _make(x10_int n);
    
    static x10_int min(x10_int i, x10_int j);
    virtual void release(x10_int n);
    virtual void release();
    virtual void reduce(x10_int n);
    virtual void acquire(x10aux::ref<x10::lang::Runtime__Worker> worker);
    virtual void yield(x10aux::ref<x10::lang::Runtime__Worker> worker);
    virtual x10_int available();
    virtual x10aux::ref<x10::lang::Runtime__Semaphore> x10__lang__Runtime__Semaphore____x10__lang__Runtime__Semaphore__this(
      );
    void __fieldInitializers2002();
    
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
#endif // X10_LANG_RUNTIME__SEMAPHORE_H

namespace x10 { namespace lang { 
class Runtime__Semaphore;
} } 

#ifndef X10_LANG_RUNTIME__SEMAPHORE_H_NODEPS
#define X10_LANG_RUNTIME__SEMAPHORE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Lock.h>
#include <x10/array/Array.h>
#include <x10/lang/Runtime__Worker.h>
#include <x10/lang/Int.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Runtime.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_LANG_RUNTIME__SEMAPHORE_H_GENERICS
#define X10_LANG_RUNTIME__SEMAPHORE_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Runtime__Semaphore::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Runtime__Semaphore> this_ = new (memset(x10aux::alloc<x10::lang::Runtime__Semaphore>(), 0, sizeof(x10::lang::Runtime__Semaphore))) x10::lang::Runtime__Semaphore();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_RUNTIME__SEMAPHORE_H_GENERICS
#endif // __X10_LANG_RUNTIME__SEMAPHORE_H_NODEPS
