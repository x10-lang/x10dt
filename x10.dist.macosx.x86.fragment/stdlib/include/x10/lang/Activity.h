#ifndef __X10_LANG_ACTIVITY_H
#define __X10_LANG_ACTIVITY_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace lang { 
class Activity__ClockPhases;
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
class IllegalOperationException;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 

class Activity : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::lang::FinishState> FMGL(finishState);
    
    x10aux::ref<x10::lang::VoidFun_0_0> FMGL(body);
    
    x10aux::ref<x10::lang::Activity__ClockPhases> FMGL(clockPhases);
    
    x10_int FMGL(atomicDepth);
    
    x10_int FMGL(home);
    
    void _constructor(x10aux::ref<x10::lang::VoidFun_0_0> body, x10aux::ref<x10::lang::FinishState> finishState);
    
    static x10aux::ref<x10::lang::Activity> _make(x10aux::ref<x10::lang::VoidFun_0_0> body,
                                                  x10aux::ref<x10::lang::FinishState> finishState);
    
    void _constructor(x10aux::ref<x10::lang::VoidFun_0_0> body, x10aux::ref<x10::lang::FinishState> finishState,
                      x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases);
    
    static x10aux::ref<x10::lang::Activity> _make(x10aux::ref<x10::lang::VoidFun_0_0> body,
                                                  x10aux::ref<x10::lang::FinishState> finishState,
                                                  x10aux::ref<x10::lang::Activity__ClockPhases> clockPhases);
    
    virtual x10aux::ref<x10::lang::Activity__ClockPhases>
      clockPhases(
      );
    virtual x10aux::ref<x10::lang::FinishState>
      finishState(
      );
    virtual x10aux::ref<x10::lang::FinishState>
      swapFinish(
      x10aux::ref<x10::lang::FinishState> f);
    virtual void pushAtomic();
    virtual void popAtomic();
    virtual void ensureNotInAtomic();
    virtual void run();
    virtual x10aux::ref<x10::lang::Activity>
      x10__lang__Activity____x10__lang__Activity__this(
      );
    void __fieldInitializers1863();
    
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
#endif // X10_LANG_ACTIVITY_H

namespace x10 { namespace lang { 
class Activity;
} } 

#ifndef X10_LANG_ACTIVITY_H_NODEPS
#define X10_LANG_ACTIVITY_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/Activity__ClockPhases.h>
#include <x10/lang/Int.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/IllegalOperationException.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/Runtime.h>
#ifndef X10_LANG_ACTIVITY_H_GENERICS
#define X10_LANG_ACTIVITY_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Activity::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Activity> this_ = new (memset(x10aux::alloc<x10::lang::Activity>(), 0, sizeof(x10::lang::Activity))) x10::lang::Activity();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_ACTIVITY_H_GENERICS
#endif // __X10_LANG_ACTIVITY_H_NODEPS
