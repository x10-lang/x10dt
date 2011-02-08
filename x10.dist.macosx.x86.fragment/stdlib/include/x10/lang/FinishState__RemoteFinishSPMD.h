#ifndef __X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H
#define __X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_NODEPS
#include <x10/lang/FinishState__RemoteFinishSkeleton.h>
#undef X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_NODEPS
#define X10_UTIL_CONCURRENT_ATOMICINTEGER_H_NODEPS
#include <x10/util/concurrent/AtomicInteger.h>
#undef X10_UTIL_CONCURRENT_ATOMICINTEGER_H_NODEPS
#define X10_LANG_LOCK_H_NODEPS
#include <x10/lang/Lock.h>
#undef X10_LANG_LOCK_H_NODEPS
namespace x10 { namespace compiler { 
class Embed;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Stack;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class MultipleExceptions;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace compiler { 
class RemoteInvocation;
} } 
namespace x10 { namespace lang { 

class FinishState__RemoteFinishSPMD : public x10::lang::FinishState__RemoteFinishSkeleton
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10::util::concurrent::AtomicInteger _Embed_count;
    x10aux::ref<x10::util::concurrent::AtomicInteger> FMGL(count);
    
    x10aux::ref<x10::util::Stack<x10aux::ref<x10::lang::Throwable> > >
      FMGL(exceptions);
    
    x10::lang::Lock _Embed_lock;
    x10aux::ref<x10::lang::Lock> FMGL(lock);
    
    void _constructor(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref);
    
    static x10aux::ref<x10::lang::FinishState__RemoteFinishSPMD> _make(
             x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref);
    
    virtual void notifySubActivitySpawn(x10::lang::Place place);
    virtual void notifyActivityCreation();
    virtual void notifyActivityTermination();
    virtual void pushException(x10aux::ref<x10::lang::Throwable> t);
    virtual x10aux::ref<x10::lang::FinishState__RemoteFinishSPMD>
      x10__lang__FinishState__RemoteFinishSPMD____x10__lang__FinishState__RemoteFinishSPMD__this(
      );
    void __fieldInitializers1923();
    
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
#endif // X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H

namespace x10 { namespace lang { 
class FinishState__RemoteFinishSPMD;
} } 

#ifndef X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H_NODEPS
#define X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H_NODEPS
#include <x10/lang/FinishState__RemoteFinishSkeleton.h>
#include <x10/util/concurrent/AtomicInteger.h>
#include <x10/lang/Lock.h>
#include <x10/compiler/Embed.h>
#include <x10/util/Stack.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/Place.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/MultipleExceptions.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/compiler/RemoteInvocation.h>
#ifndef X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H_GENERICS
#define X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::FinishState__RemoteFinishSPMD::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__RemoteFinishSPMD> this_ = new (memset(x10aux::alloc<x10::lang::FinishState__RemoteFinishSPMD>(), 0, sizeof(x10::lang::FinishState__RemoteFinishSPMD))) x10::lang::FinishState__RemoteFinishSPMD();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__REMOTEFINISHSPMD_H_NODEPS
