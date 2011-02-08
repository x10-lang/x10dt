#ifndef __X10_COMPILER_WS_WORKER_H
#define __X10_COMPILER_WS_WORKER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
class Random;
} } 
namespace x10 { namespace compiler { namespace ws { 
class BoxedBoolean;
} } } 
namespace x10 { namespace lang { 
class Deque;
} } 
namespace x10 { namespace lang { 
class Lock;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace compiler { namespace ws { 
class RegularFrame;
} } } 
namespace x10 { namespace compiler { namespace ws { 
class Frame;
} } } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace compiler { namespace ws { 
class FinishFrame;
} } } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace compiler { namespace ws { 
class Stolen;
} } } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace compiler { namespace ws { 
class MainFrame;
} } } 
namespace x10 { namespace compiler { namespace ws { 
class RootFinish;
} } } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace compiler { 
class AsyncClosure;
} } 
namespace x10 { namespace compiler { namespace ws { 

class Worker : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Rail<x10aux::ref<x10::compiler::ws::Worker> > >
      FMGL(workers);
    
    x10aux::ref<x10::util::Random> FMGL(random);
    
    x10aux::ref<x10::compiler::ws::BoxedBoolean> FMGL(finished);
    
    x10aux::ref<x10::lang::Deque> FMGL(deque);
    
    x10aux::ref<x10::lang::Deque> FMGL(fifo);
    
    x10aux::ref<x10::lang::Lock> FMGL(lock);
    
    void _constructor(x10_int i, x10aux::ref<x10::lang::Rail<x10aux::ref<x10::compiler::ws::Worker> > > workers,
                      x10aux::ref<x10::compiler::ws::BoxedBoolean> finished);
    
    static x10aux::ref<x10::compiler::ws::Worker> _make(x10_int i,
                                                        x10aux::ref<x10::lang::Rail<x10aux::ref<x10::compiler::ws::Worker> > > workers,
                                                        x10aux::ref<x10::compiler::ws::BoxedBoolean> finished);
    
    virtual void migrate();
    virtual void run();
    virtual x10aux::ref<x10::compiler::ws::RegularFrame> find(
      );
    static void purge(x10aux::ref<x10::compiler::ws::Frame> frame,
                      x10aux::ref<x10::compiler::ws::FinishFrame> ff);
    virtual void unroll(x10aux::ref<x10::compiler::ws::Frame> frame);
    virtual void unstack(x10aux::ref<x10::compiler::ws::Frame> frame);
    static void main(x10aux::ref<x10::compiler::ws::MainFrame> frame);
    virtual x10aux::ref<x10::compiler::ws::Worker> x10__compiler__ws__Worker____x10__compiler__ws__Worker__this(
      );
    void __fieldInitializers1807();
    
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
#endif // X10_COMPILER_WS_WORKER_H

namespace x10 { namespace compiler { namespace ws { 
class Worker;
} } } 

#ifndef X10_COMPILER_WS_WORKER_H_NODEPS
#define X10_COMPILER_WS_WORKER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Rail.h>
#include <x10/util/Random.h>
#include <x10/compiler/ws/BoxedBoolean.h>
#include <x10/lang/Deque.h>
#include <x10/lang/Lock.h>
#include <x10/lang/Int.h>
#include <x10/lang/Long.h>
#include <x10/compiler/ws/RegularFrame.h>
#include <x10/compiler/ws/Frame.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/compiler/ws/FinishFrame.h>
#include <x10/compiler/Finalization.h>
#include <x10/compiler/ws/Stolen.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/compiler/ws/MainFrame.h>
#include <x10/compiler/ws/RootFinish.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Rail.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/compiler/AsyncClosure.h>
#ifndef X10_COMPILER_WS_WORKER_H_GENERICS
#define X10_COMPILER_WS_WORKER_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::ws::Worker::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::ws::Worker> this_ = new (memset(x10aux::alloc<x10::compiler::ws::Worker>(), 0, sizeof(x10::compiler::ws::Worker))) x10::compiler::ws::Worker();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_WS_WORKER_H_GENERICS
#endif // __X10_COMPILER_WS_WORKER_H_NODEPS
