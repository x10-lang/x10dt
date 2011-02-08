#ifndef __X10_LANG_RUNTIME__WORKER_H
#define __X10_LANG_RUNTIME__WORKER_H

#include <x10rt.h>


#define X10_LANG_THREAD_H_NODEPS
#include <x10/lang/Thread.h>
#undef X10_LANG_THREAD_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Activity;
} } 
namespace x10 { namespace lang { 
class Deque;
} } 
namespace x10 { namespace util { 
class Random;
} } 
namespace x10 { namespace lang { 
class Runtime__Pool;
} } 
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace compiler { 
class TempNoInline_1;
} } 
namespace x10 { namespace lang { 
class SimpleLatch;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 

class Runtime__Worker : public x10::lang::Thread   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10_int FMGL(BOUND);
    
    static inline x10_int FMGL(BOUND__get)() {
        return x10::lang::Runtime__Worker::FMGL(BOUND);
    }
    x10aux::ref<x10::lang::Activity> FMGL(activity);
    
    x10aux::ref<x10::lang::Deque> FMGL(queue);
    
    x10aux::ref<x10::util::Random> FMGL(random);
    
    x10_int FMGL(workerId);
    
    x10aux::ref<x10::lang::Runtime__Pool> FMGL(pool);
    
    void _constructor(x10_int workerId);
    
    static x10aux::ref<x10::lang::Runtime__Worker> _make(x10_int workerId);
    
    virtual x10_int size();
    virtual x10aux::ref<x10::lang::Activity> activity();
    virtual x10aux::ref<x10::lang::Activity> poll();
    virtual x10aux::ref<x10::lang::Activity> steal();
    virtual void push(x10aux::ref<x10::lang::Activity> activity);
    virtual void __apply();
    x10_boolean loop();
    virtual void probe();
    virtual void join(x10aux::ref<x10::lang::SimpleLatch> latch);
    x10_boolean loop2(x10aux::ref<x10::lang::SimpleLatch> latch);
    static void park();
    virtual void unpark();
    virtual x10aux::ref<x10::lang::Runtime__Worker> x10__lang__Runtime__Worker____x10__lang__Runtime__Worker__this(
      );
    void __fieldInitializers2003();
    
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
#endif // X10_LANG_RUNTIME__WORKER_H

namespace x10 { namespace lang { 
class Runtime__Worker;
} } 

#ifndef X10_LANG_RUNTIME__WORKER_H_NODEPS
#define X10_LANG_RUNTIME__WORKER_H_NODEPS
#include <x10/lang/Thread.h>
#include <x10/lang/Int.h>
#include <x10/lang/Activity.h>
#include <x10/lang/Deque.h>
#include <x10/util/Random.h>
#include <x10/lang/Runtime__Pool.h>
#include <x10/lang/Long.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/Runtime.h>
#include <x10/compiler/Finalization.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/compiler/TempNoInline_1.h>
#include <x10/lang/SimpleLatch.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_LANG_RUNTIME__WORKER_H_GENERICS
#define X10_LANG_RUNTIME__WORKER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Runtime__Worker::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Runtime__Worker> this_ = new (memset(x10aux::alloc<x10::lang::Runtime__Worker>(), 0, sizeof(x10::lang::Runtime__Worker))) x10::lang::Runtime__Worker();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_RUNTIME__WORKER_H_GENERICS
#endif // __X10_LANG_RUNTIME__WORKER_H_NODEPS
